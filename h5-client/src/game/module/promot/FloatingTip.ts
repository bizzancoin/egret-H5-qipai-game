/**
 * Created by ys on 4/16/18.
 */
class FloatingTip extends BaseClass {

	private content: egret.Sprite = null;
	private tipArray: any[];

	public constructor() {
		super();
		this.tipArray = [];
		this.init();
	}

	private init(): void {
		this.content = new egret.Sprite();
		this.content.graphics.beginFill(0x000000, 0);
		this.content.graphics.drawRect(0, 0, App.StageUtils.getWidth(), App.StageUtils.getHeight());
		this.content.graphics.endFill();
	}

	// 创建文本
	private createTextField(size: number, color: number = 0xffffff, VAgain: string = egret.VerticalAlign.MIDDLE,
		HAgain: string = egret.HorizontalAlign.CENTER, family: string = "Verdana"): egret.TextField {
		let textField = new egret.TextField();
		textField.size = size;
		textField.textColor = color;
		textField.textAlign = HAgain;
		textField.verticalAlign = VAgain;
		textField.fontFamily = family;
		textField.cacheAsBitmap = true;
		return textField;
	}

	public showTips(tip: string, color: number = 0xffffff): void {
		this.tipArray.push({ text: tip, color: color });
		App.StageUtils.getStage().addChild(this.content);
		App.TimerManager.doFrame(1, 0, this.enterFrame, this);
	}

	private enterFrame(): void {
		if (this.tipArray.length > 0) {
			let tip = this.tipArray.pop();
			let maxW: number = App.StageUtils.getWidth();
			let maxH: number = App.StageUtils.getHeight();
			let txt = this.createTextField(40);
			txt.text = tip.text;
			txt.textColor = tip.color;
			txt.y = maxH * 0.5;
			txt.width = maxW;

			this.content.addChild(txt);

			egret.Tween.get(txt).to({ y: maxH * 0.3, alpha: 0 }, 1000, egret.Ease.circIn)
				.call(function (target: egret.TextField) {
					if (target.parent) {
						target.parent.removeChild(target);
					}
				}, this, [txt]);
		} else {
			App.TimerManager.remove(this.enterFrame, this);
		}
	}
}