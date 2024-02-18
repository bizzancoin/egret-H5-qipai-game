class HomeController extends BaseController {
	private homeView: HomeView;
	public constructor() {
		super();
		this.homeView = new HomeView(this, LayerManager.UI_Main);
		App.ViewManager.register(ViewConst.Home, this.homeView);
	}
}