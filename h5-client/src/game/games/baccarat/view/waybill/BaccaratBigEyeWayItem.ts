module baccarat {
	export class BaccaratBigEyeWayItem extends eui.Component {
		private imgIcon: eui.Image;

		private type: number;
		private id: number;
		private loaded:boolean;

		public constructor() {
			super();
			this.loaded = false;
			this.addEventListener(eui.UIEvent.COMPLETE, this.onUIComplete, this);
			this.skinName = "resource/skins/game/baccarat/BJLBigEyeItemSkin.exml"
		}

		protected createChildren(): void {
			super.createChildren();
		}

		private onUIComplete(): void {
			this.loaded = true;
			this.touchEnabled = false;
			if (this.type && this.id) {
				this.show();
			}
		}

		public setType(type: number, id: number): void {
			this.type = type;
			this.id = id;
			if(this.loaded){
				this.show();
			}
		}

		public show(): void {
			if (this.type == 1) {
				this.imgIcon.source = this.id == 1? RES.getRes("ludan_json.bjl_a_hqd"): RES.getRes("ludan_json.bjl_a_lqd");
			}
			else {
				this.imgIcon.source = this.id == 1? RES.getRes("ludan_json.bjl_a_hqd"): RES.getRes("ludan_json.bjl_a_jqd");
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