module brnn {
	export class BrnnStart {
		// 开始捕鱼游戏
		public start() {
			// 注册加载页面
			App.SceneManager.register(SceneConsts.Brnn, new BrnnScene());
			// 加载配置文件
			App.ResourceUtils.addConfig("resource/assets/games/brnn/brnn.res.json", "resource/");
			App.ResourceUtils.loadConfig(this.onConfigComplete, this);
			App.EasyLoading.showLoading("加载中 ...");
		}

		// 配置文件加载完毕， 加载捕鱼loading资源
		private onConfigComplete() {
			App.ResourceUtils.loadGroup('brnn_loading_asset', function () {
				// 关闭加载弹窗
				App.EasyLoading.hideLoading();
				// 注册加载页面
				App.ControllerManager.register(ControllerConst.BrnnLoad, new LoadingController());
				App.SceneManager.runScene(SceneConsts.Brnn);
				App.ViewManager.open(ViewConst.BrnnLoad);
				this.loadResource();
			}, function () { }, this)
		}

		// 加载资源
		private loadResource() {
			// 加载资源
			var groupName: string = 'brnnAssets';
			var subGroups: Array<string> = ['brnn_asset'];
			App.ResourceUtils.loadGroups(groupName, subGroups, this.onLoadComplete, this.onLoadProgress, this);
		}


		private onLoadComplete(): void {
			// 注册捕鱼主页Controller
			App.ControllerManager.register(ControllerConst.BrnnMain, new MainController());
			App.ViewManager.open(ViewConst.BrnnMain);
		}

		// 资源加载进度
		private onLoadProgress(itemsLoaded: number, itemsTotal: number): void {
			App.ControllerManager.applyFunc(ControllerConst.BrnnLoad, ControllerConst.BrnnLoad, itemsLoaded, itemsTotal);
		}

		// 结束游戏
		public finish() {
			// 注销页面和控制器 
			let view = App.ViewManager.getView(ViewConst.BrnnMain);
			if (view) {
				(<MainView>view).finish();
			}
			App.ViewManager.unregister(ViewConst.BrnnLoad);
			App.ViewManager.unregister(ViewConst.BrnnMain)
			App.ControllerManager.unregister(ControllerConst.BrnnLoad);
			App.ControllerManager.unregister(ControllerConst.BrnnMain);
		}
	}
}