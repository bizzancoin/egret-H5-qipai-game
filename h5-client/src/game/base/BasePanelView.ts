class BasePanelView extends BaseEuiView {

	public lbTitle: eui.Label;
	public btnClose: eui.Button;
	public contentGroup: eui.Group;

	private _title: string;

	public constructor(controller:BaseController, parent:eui.Group) {
		super(controller, parent);
		this.skinName = "resource/skins/PanelSkin.exml";
	}

	public initUI(): void {
		this.btnClose.addEventListener(egret.TouchEvent.TOUCH_END,this.closeClickHandler,this);
	}

	public initData(): void {
		super.initData();
		this.lbTitle.text = this._title;
	}

	public set title(value:string) {
		this._title = value;
	}

	public get title():string {
		return this._title;
	}

	private closeClickHandler(): void {
		App.ViewManager.closeView(this);
	}
}