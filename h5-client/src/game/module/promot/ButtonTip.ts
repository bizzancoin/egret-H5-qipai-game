class ButtonTip extends BaseClass {

	private content:egret.Sprite = null;
	private btnTip:ButtonComponent;


	public constructor() {
		super();
		this.init();
	}

	private init(): void {
		this.content = new egret.Sprite();
        this.content.graphics.beginFill(0x000000, 0.3);
        this.content.graphics.drawRect(0, 0, App.StageUtils.getWidth(), App.StageUtils.getHeight());
        this.content.graphics.endFill();
        this.content.touchEnabled = true;

		this.btnTip = new ButtonComponent();
		this.content.addChild(this.btnTip);
	}


	public oneButtonTip(tip:string, method?:Function, methodObj?: any): void {
		this.btnTip.SetCancelVisible(false);
		this.btnTip.SetMsg(tip);
		if (method && methodObj) {
			this.btnTip.SetSureHandler(method, methodObj);
		} else {
			this.btnTip.SetSureHandler(this.hideButtonTip, this);
		}
		App.StageUtils.getStage().addChild(this.content);
	}

	public twoButtonTip(tip:string, sureMethod?:Function, sureMethodObj?: any, cancelMethod?:Function, cancelMethodObj?: any): void {
		this.btnTip.SetCancelVisible(true);
		this.btnTip.SetMsg(tip);
		if (sureMethod && sureMethod) {
			this.btnTip.SetSureHandler(sureMethod, sureMethodObj);
		} else {
			this.btnTip.SetSureHandler(this.hideButtonTip, this);
		}
		if (cancelMethod && cancelMethodObj) {
			this.btnTip.SetCancelHandler(cancelMethod, cancelMethodObj);
		} else {
			this.btnTip.SetCancelHandler(this.hideButtonTip, this);
		}
		App.StageUtils.getStage().addChild(this.content);
	}

	public hideButtonTip():void {
		App.StageUtils.getStage().removeChild(this.content);
	}
	
}