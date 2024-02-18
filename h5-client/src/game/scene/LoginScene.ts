// 登录场景（包含 LoadingView, LoginView）
class LoginScene extends BaseScene {
	public constructor() {
		super();
	}

	public onEnter(): void {
		super.onEnter();
		this.addLayer(LayerManager.UI_Main);
	}
}