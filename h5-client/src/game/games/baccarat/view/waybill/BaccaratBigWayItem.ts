module baccarat {
	export class BaccaratBigWayItem extends eui.Component {
		private imgBigIcon: eui.Image;
		private imgHeIcon: eui.Image;
		private fntHeNum: eui.BitmapLabel;

		private type: number;
		private result: number;
		private drawNum: number;
		private loaded: boolean;

		public constructor() {
			super();
			this.loaded = false;
			this.addEventListener(eui.UIEvent.COMPLETE, this.onUIComplete, this);
			this.skinName = "resource/skins/game/baccarat/BJLBigWayItemSkin.exml"
		}

		protected createChildren(): void {
			super.createChildren();
		}

		private onUIComplete(): void {
			this.loaded = true;
			this.touchEnabled = false;
			if (this.type && this.result) {
				this.show();
			}
		}

		public setType(type: number, result: number, drawNum: number): void {
			this.type = type;
			this.result = result;
			this.drawNum = drawNum;
			if (this.loaded) {
				this.show();
			}
		}

		public show(): void {
			this.fntHeNum.visible = false;
			this.imgHeIcon.visible = false;
			if (this.type == 1) {
				this.imgBigIcon.source = this.result == 1 ? RES.getRes("ludan_json.bjl_d_hquan") : RES.getRes("ludan_json.bjl_d_lquan");
				this.imgHeIcon.visible = this.drawNum >= 1;
				if (this.drawNum > 1) {
					this.fntHeNum.visible = true;
					this.fntHeNum.text = this.drawNum.toString();
				}
			}
			else {
				this.imgBigIcon.source = this.result == 1 ? RES.getRes("ludan_json.bjl_d_hquan") : RES.getRes("ludan_json.bjl_d_jquan");
				this.imgHeIcon.visible = this.drawNum >= 1;
				if (this.drawNum > 1) {
					this.fntHeNum.visible = true;
					this.fntHeNum.text = this.drawNum.toString();
				}
			}
		}
		public anim(): void {
			let tw = egret.Tween.get(this, { loop: true });
			tw.to({ alpha: 0 }, 100).wait(200).to({ alpha: 1 }, 100).wait(300);
		}
		public clear(): void {
			this.parent.removeChild(this);
		}
	}
}