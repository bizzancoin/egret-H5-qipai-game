class LoginMgr {

	private static loginStart: LoginStart;

	// 是否已经加载过
	public static isLoading: boolean;

	public static start(): void {
		if (!LoginMgr.loginStart) {
			LoginMgr.loginStart = new LoginStart();
		}
		LoginMgr.loginStart.start();
	}

	public static finish(): void {
		if (LoginMgr.loginStart) {
			//LoginMgr.loginStart.finish();
			//LoginMgr.loginStart = null;
		}
	}
}