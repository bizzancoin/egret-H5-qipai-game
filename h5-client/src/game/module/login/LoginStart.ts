class LoginStart {

	public start(): void {
		GameApp.PlayerInfo.curGameID = null;
		if (LoginMgr.isLoading) {
			this.initModule();
			App.SceneManager.runScene(SceneConsts.Login);
			// 已经登录或者不显示登录页，打开大厅页面
			if (GameApp.PlayerInfo.isLogin) {
				App.ViewManager.open(ViewConst.Home);
			} else {
				App.ViewManager.open(ViewConst.Login);
			}
		} else {
			this.runLoading();
			// 加载公共资源配置文件
			App.ResourceUtils.addConfig('resource/resource_core.json', 'resource/');
			App.ResourceUtils.addConfig('resource/proto/proto.res.json', 'resource/');
			App.ResourceUtils.loadConfig(this.onConfigComplete, this)
			App.EasyLoading.showLoading();
		}

	}

	private runLoading(): boolean {
		App.SceneManager.register(SceneConsts.Login, new LoginScene());
		App.ControllerManager.register(ControllerConst.Loading, new LoadingController());
		App.SceneManager.runScene(SceneConsts.Login);
		App.ViewManager.open(ViewConst.Loading);

		return true;
	}

	// 资源加载完毕
	private onConfigComplete(): void {
		App.EasyLoading.hideLoading();
		// 加载公共资源
		var groupName: string = 'common';
		var subGroups: Array<string> = ['preload_core', 'common_asset', 'proto_file'];
		App.ResourceUtils.loadGroups(groupName, subGroups, this.onLoadComplete, this.onLoadProgress, this);
	}

	private onLoadComplete(): void {
		LoginMgr.isLoading = true;
		App.MsgFactory.registerAll();
		App.Init();
		GameApp.Init();
		this.initModule();
		if (GameApp.PlayerInfo.isLogin) {
			App.ViewManager.open(ViewConst.Home);
		} else {
			App.ViewManager.open(ViewConst.Login);
		}
		this.connectServer();
	}

	private onLoadProgress(itemsLoaded: number, itemsTotal: number): void {
		// 发送加载进度
		App.ControllerManager.applyFunc(ControllerConst.Loading, LoadingConst.SetProgress, itemsLoaded, itemsTotal);
	}

	private initModule(): void {
		App.ControllerManager.register(ControllerConst.Login, new LoginController());
		App.ControllerManager.register(ControllerConst.Home, new HomeController());
		App.ControllerManager.register(ControllerConst.Room, new RoomController());
	}

	// 连接服务器
	private connectServer(): void {
		App.Socket.connect();
		App.MessageCenter.addListener(SocketConst.SOCKET_CONNECT, () => {
			GameApp.HeartMgr.init();
			let reloginCode = egret.localStorage.getItem('reloginCode');
			let reloginType = parseInt(egret.localStorage.getItem('reloginType'));
			if(reloginCode && reloginType) {
				this.reLogin(reloginCode, reloginType);
			}
		}, this);
		App.MessageCenter.addListener(SocketConst.SOCKET_RECONNECT, () => {
			GameApp.HeartMgr.resume();
			// 断线重连
			console.log("断线重连成功");
			if (GameApp.PlayerInfo.isLogin) {
				LoginReq.send_reLogin(GameApp.PlayerInfo.reLoginCode, GameApp.PlayerInfo.loginType, 0,
					function (success, msg) {
					}, this);
			}
		}, this);
		App.MessageCenter.addListener(SocketConst.SOCKET_START_RECONNECT, () => {
			GameApp.HeartMgr.pause();
			console.log("开始与服务器重新连接");
		}, this);
		App.MessageCenter.addListener(SocketConst.SOCKET_CLOSE, () => {
			GameApp.HeartMgr.pause();
			GameApp.PromotManager.twoButtonTip("服务器连接失败，请检查网络设置", null, () => {
				GameApp.PromotManager.hideButtonTip();
				App.Socket.connect();
			}, this, ()=>{
				location.href = "404.html?code=500&msg=服务器连接失败，请检查网络设置";
			}, this);
		}, this);
		App.MessageCenter.addListener(SocketConst.SOCKET_NOCONNECT, () => {
			GameApp.HeartMgr.pause();
			GameApp.PromotManager.twoButtonTip("服务器连接失败，请检查网络设置", null, () => {
				GameApp.PromotManager.hideButtonTip();
				App.Socket.connect();
			}, this, ()=>{
				location.href = "404.html?code=500&msg=服务器连接失败，请检查网络设置";
			}, this);
		}, this);
	}

	private reLogin(reloginCode:any, reloginType:any): void {
		App.EasyLoading.showLoading();
		let self = this;
		LoginReq.send_reLogin(reloginCode, reloginType, 1, function (success, msg) {
			App.EasyLoading.hideLoading();
			if(!success) {
				GameApp.PromotManager.oneButtonTip('登录失败')
			}
		}, this);
	}

	public finish(): void {

	}

}