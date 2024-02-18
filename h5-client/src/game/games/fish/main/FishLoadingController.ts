module fish {
	export class FishLoadingController extends BaseController {
		private loadingView: FishLoadingView;

		public constructor() {
			super();
			this.loadingView = new FishLoadingView(this, LayerManager.UI_Main);
			App.ViewManager.register(ViewConst.FishLoad, this.loadingView);

			this.registerFunc(ControllerConst.FishLoad, this.setProgress, this);
		}

		private setProgress(current: number, total: number): void {
			this.loadingView.setProgress(current, total);
		}
	}
}