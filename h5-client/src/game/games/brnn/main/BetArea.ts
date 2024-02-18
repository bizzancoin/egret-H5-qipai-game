module brnn {
	// 下注区域
	export class BetArea extends eui.Component {

		private groupChips: eui.Group;
		private bmpMyBet: eui.BitmapLabel;
		private bmpTotalBet: eui.BitmapLabel;
		private groupParent: eui.Group;
		private imgWin: eui.Image;

		public constructor() {
			super();
			this.skinName = 'resource/skins/brnn/BrnnBetAreaSkin.exml';
		}

		public registerEvent(area,method: Function, methodObj: any): void {
			this.groupParent.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt: egret.TouchEvent) => {
				method.apply(methodObj, [area]);
			}, this);

			this.imgWin.visible = false;
			this.reset();
		}

		public setWin(isWin: boolean): void {
			this.imgWin.visible = isWin;
			if (isWin) {
				egret.Tween.get(this.imgWin, { loop: true }).to({ alpha: 0.1 }, 500)
					.to({ alpha: 1 }, 500);
			}
		}

		public setMyBetChips(chips: number): void {
			this.bmpMyBet.text = chips.toString();
			this.bmpMyBet.visible = chips > 0;
		}

		public setTotalBetChips(chips: number): void {
			this.bmpTotalBet.text = chips.toString();
			this.bmpTotalBet.visible = chips > 0;
		}

		public reset(): void {
			this.bmpMyBet.text = '0';
			this.bmpTotalBet.text = '0';
			this.bmpMyBet.visible = false;
			this.bmpTotalBet.visible = false;
			this.imgWin.visible = false;
		}

	}
}