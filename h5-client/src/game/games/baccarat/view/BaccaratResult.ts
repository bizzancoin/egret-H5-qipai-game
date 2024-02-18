module baccarat {
	export class BaccaratResult extends eui.Component {
		private grp: eui.Panel;
		private imgResult: eui.Image;
		private lbResult: eui.BitmapLabel;

		public constructor(grp: eui.Panel) {
			super();
			this.grp = grp;
			this.imgResult = <eui.Image>grp.getChildAt(0);
			this.lbResult = <eui.BitmapLabel>grp.getChildAt(1);
			this.grp.visible = false;
		}

		public showResult(result:number,bankerChips: number, playerChips: number): void {
			let resultRes = ["bjl_wd_json.bjl_wd_zy","bjl_wd_json.bjl_wd_xy","bjl_wd_json.bjl_wd_he"];
			this.imgResult.source = RES.getRes(resultRes[result]);

			if (playerChips < 0) {
				this.lbResult.text = playerChips.toString();
			} else {
				this.lbResult.text = "+" + playerChips.toString();
			}
			this.grp.scaleX = 0.1;
			this.grp.scaleY = 0.1;
			this.grp.visible = true;
			let tw = egret.Tween.get(this.grp);
			tw.to({ scaleX: 1, scaleY: 1 }, 500).call(() => {
				// tw.wait(2000);
				// this.grp.visible = false;
			})
		}

		public clear():void{
			this.grp.visible = false;
		}
	}
}