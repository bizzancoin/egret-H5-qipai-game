class ButtonComponent extends eui.Component {
	private lbMsg: eui.Label;
	private btnCancel: eui.Button;
	private btnSure: eui.Button;
    private cancelListener: any;
	private sureListener: any;

	public constructor() {
		super();
		this.addEventListener(eui.UIEvent.COMPLETE,this.onUIComplete,this);
		this.skinName = "resource/skins/ButtonTipSkin.exml"
	}

	protected createChildren(): void {
		super.createChildren();
		this.percentWidth = 100;
		this.percentHeight = 100;
	}

	private onUIComplete(): void {
		this.btnCancel.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onCancelHandler, this);
		this.btnSure.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onSurelHandler, this);
	}

	private onCancelHandler():void {
		if(this.cancelListener) {
			this.cancelListener[0].apply(this.cancelListener[1]);
		} 
	}
	
	private onSurelHandler():void {
		if (this.sureListener) {
			this.sureListener[0].apply(this.sureListener[1]);
		}
	}

	public SetCancelHandler(method:Function, methodObj:any) {
		if (method && methodObj) {
			this.cancelListener = [method, methodObj];
		} else {
			this.cancelListener = null;
		}
	}
	
	public SetSureHandler(method:Function, methodObj:any) {
		if (method && methodObj) {
			this.sureListener = [method, methodObj];
		} else {
			this.sureListener = null;
		}
	}

	public SetCancelVisible(visible:boolean):void {
		this.btnCancel.visible = visible;
		if(visible) {
			this.btnCancel.right = 0;
			this.btnSure.left = 0;
		} else {
			this.btnSure.left = 145;
		}
	}

	public SetMsg(msg:string): void {
		this.lbMsg.text = msg;
	}
}