module brnn {
	export class LoadingView extends BaseLoadingView {
		public loadingBgRes(): string {
			return 'brnn_atlas0_json.loading';
		}

		public fillBgRes(): string {
			return 'brnn_atlas0_json.loadbg';
		}

		public fillRes(): string {
			return 'brnn_atlas0_json.loadt';
		}
	}

	export class LoadingController extends BaseController {
		private loadingView: LoadingView;

		public constructor() {
			super();
			this.loadingView = new LoadingView(this, LayerManager.UI_Main);
			App.ViewManager.register(ViewConst.BrnnLoad, this.loadingView);

			this.registerFunc(ControllerConst.BrnnLoad, this.setProgress, this);
		}

		private setProgress(current: number, total: number): void {
			this.loadingView.setProgress(current, total);
		}
	}
}