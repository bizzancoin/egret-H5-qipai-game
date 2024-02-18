module baccarat {
	export class BaccaratStart {
		public start() {
			// 注册加载页面
			App.SceneManager.register(SceneConsts.Game, new BaccaratScene());

			App.ResourceUtils.addConfig("resource/assets/games/baccarat/baccarat.res.json", "resource/");
			App.ResourceUtils.loadConfig(this.onConfigComplete, this);
			App.EasyLoading.showLoading("加载中 ...");

		}
		private onConfigComplete(): void {
			App.ResourceUtils.loadGroup('baccarat_load', function () {
				// 关闭加载弹窗
				App.EasyLoading.hideLoading();
				// 注册加载页面
				App.SceneManager.runScene(SceneConsts.Game);
				App.ControllerManager.register(ControllerConst.Baccarat, new BaccaratController());
			}, function () { }, this)
		}
		public finish() {
			// 注销页面和控制器 
			let view = App.ViewManager.getView(ViewConst.Baccarat);
			if(view) {
				(<BaccaratView>view).clear(false);
			}
			App.ViewManager.unregister(ViewConst.BaccaratLoad);
			App.ViewManager.unregister(ViewConst.Baccarat)
			App.ControllerManager.unregister(ControllerConst.Baccarat);
		}
	}
}