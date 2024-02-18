class LoginController extends BaseController {
	private loginView: LoginView;
	public constructor() {
		super();

		// 初始化UI
		this.loginView = new LoginView(this, LayerManager.UI_Main);
		App.ViewManager.register(ViewConst.Login, this.loginView);
	}

}