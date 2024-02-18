module fish {
	export class FishStart {

		// 开始捕鱼游戏
		public start() {
			// 注册加载页面
			App.SceneManager.register(SceneConsts.Fish, new FishScene());
			// 加载捕鱼配置文件
			//App.ResourceUtils.addConfig("resource/config/" + FishMgr.fishModule + "_fish_config.res.json", "resource/");
			App.ResourceUtils.addConfig("resource/assets/games/fish_common.res.json", "resource/");
			App.ResourceUtils.addConfig("resource/assets/games/" + FishMgr.fishModule + "_fish_asset.res.json", "resource/");
			App.ResourceUtils.loadConfig(this.onConfigComplete, this);
			App.EasyLoading.showLoading("加载中 ...");
		}

		// 配置文件加载完毕， 加载捕鱼loading资源
		private onConfigComplete() {
			App.ResourceUtils.loadGroup(FishMgr.fishModule + '_loading_asset', function () {
				// 关闭加载弹窗
				App.EasyLoading.hideLoading();
				// 注册加载页面
				App.ControllerManager.register(ControllerConst.FishLoad, new FishLoadingController());
				App.SceneManager.runScene(SceneConsts.Fish);
				App.ViewManager.open(ViewConst.FishLoad);
				this.loadFishResource()
			}, function () { }, this)
		}

		// 加载捕鱼资源
		private loadFishResource() {
			// 加载资源
			var groupName: string = 'fishAssets';
			var subGroups: Array<string> = ['fish_common_asset', FishMgr.fishModule + '_fish_asset'];
			App.ResourceUtils.loadGroups(groupName, subGroups, this.onLoadComplete, this.onLoadProgress, this);
		}


		private onLoadComplete(): void {
			// 注册捕鱼主页Controller
			App.ControllerManager.register(ControllerConst.FishMain, new FishMainSceneController());
			App.ViewManager.open(ViewConst.FishMain);
		}

		// 资源加载进度
		private onLoadProgress(itemsLoaded: number, itemsTotal: number): void {
			App.ControllerManager.applyFunc(ControllerConst.FishLoad, ControllerConst.FishLoad, itemsLoaded, itemsTotal);
		}

		// 结束捕鱼游戏
		public finish() {
			// 注销页面和控制器 
			let view = App.ViewManager.getView(ViewConst.FishMain);
			if(view) {
				(<FishMainView>view).finish();
			}
			App.ViewManager.unregister(ViewConst.FishLoad);
			App.ViewManager.unregister(ViewConst.FishMain)
			App.ControllerManager.unregister(ControllerConst.FishLoad);
			App.ControllerManager.unregister(ControllerConst.FishMain);
		}
	}

}