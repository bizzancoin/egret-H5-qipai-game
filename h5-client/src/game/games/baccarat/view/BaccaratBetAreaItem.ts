module baccarat {
	export class BaccaratBetAreaItem {
		private imgLight: eui.Image;
		private lbBet: eui.BitmapLabel;
		public x: number;
		public y: number;
		public height: number;
		public width: number;

		public constructor(img: eui.Image, lbl: eui.BitmapLabel) {
			this.imgLight = img;
			this.lbBet = lbl;

			this.imgLight.visible = false;
			this.lbBet.visible = false;
			this.x = img.x;
			this.y = img.y;
			this.width = img.width;
			this.height = img.height;
		}

		public setBet(bet: number): void {
			this.lbBet.visible = true;
			this.lbBet.text = bet.toString();
		}

		public setLight(): void {
			this.imgLight.visible = true;
			//let tw = egret.Tween.get(this.imgLight, { loop: true });
			//tw.to({ alpha: 0 }, 300);
		}

		public clear(): void {
			this.imgLight.visible = false;
			egret.Tween.get(this.imgLight).pause();
			this.lbBet.visible = false;
			this.lbBet.text = "";
		}
	}
}