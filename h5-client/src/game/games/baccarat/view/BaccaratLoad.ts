class BaccaratLoad extends BaseEuiView {
	private pbLoad: eui.ProgressBar;


	public constructor($controller, $parent) {
		super($controller, $parent);
		this.skinName = "resource/skins/game/baccarat/BaccaratLoadSkin.exml"
		// 加载资源

		var theme = new eui.Theme("resource/skins/game/baccarat/baccarat_theme.json", this.stage);
		theme.addEventListener(eui.UIEvent.COMPLETE, this.onThemeLoadComplete, this);
	}

	public initUI(): void {
		super.initUI();
		this.pbLoad.minimum = 0;
		this.pbLoad.maximum = 100;
		this.pbLoad.value = 0;
	}

	// 初始化数据
	public initData(): void {
		super.initData();
	}

	private onLoadComplete(): void {
		App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.LoadCompleted);
	}

	private onThemeLoadComplete(): void {
		var groupName: string = 'baccarat';
		var subGroups: Array<string> = ['baccarat'];
		App.ResourceUtils.loadGroups(groupName, subGroups, this.onLoadComplete, this.onLoadProgress, this);
	}

	// 资源加载进度
	private onLoadProgress(itemsLoaded: number, itemsTotal: number): void {
		// 发送加载进度
		this.pbLoad.value = itemsLoaded / itemsTotal * 100;
	}
}