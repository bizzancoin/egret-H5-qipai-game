module baccarat {
	export class ChipItem extends eui.Component {
		private imgChipBg: eui.Image;
		private lbChip: eui.Label;

		public constructor() {
			super();
			this.skinName = "resource/skins/game/baccarat/BJLChipSkin.exml"
		}

		protected createChildren(): void {
			super.createChildren();
			this.touchEnabled = false;
		}
		public setChip(chip: number): void {
			this.lbChip.text = chip + "";
			// this.lbChip.text = "";
			// let idx = Math.round(Math.log(chip) / Math.log(10));
			let idx = BaccaratMgr.bjlModel.roomInfo.betOptions.indexOf(chip);
			this.imgChipBg.source = RES.getRes("bjl_chips_json.bjl_btn_cm" + (idx + 1) + "2");
		}
		public sendChip(x: number, y: number): void {
			let tw = egret.Tween.get(this);
			tw.to({ x: x, y: y }, 300);
			tw.call(() => {
			});
		}
		public clear(): void {
			this.parent.removeChild(this);
		}
		public recyle(): void {
			let tw = egret.Tween.get(this);
			tw.to({ x: 0, y: -200 }, 300);
			tw.call(() => {
				this.parent.removeChild(this);
			});
		}
	}
}