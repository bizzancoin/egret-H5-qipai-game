class LoadingController extends BaseController {

	private loadingView: LoadingView;

	public constructor() {
		super();
		this.loadingView = new LoadingView(this, LayerManager.UI_Main);
		App.ViewManager.register(ViewConst.Loading, this.loadingView);

		this.registerFunc(LoadingConst.SetProgress, this.setProgress, this);
	}

	private setProgress(current:number, total: number):void {
		this.loadingView.setProgress(current, total);
	}
}