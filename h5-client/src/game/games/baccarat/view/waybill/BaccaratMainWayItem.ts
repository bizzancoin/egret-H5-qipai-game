module baccarat {
	export class BaccaratMainWayItem extends eui.Component {
		private imgZX: eui.Image;
		private imgZdui: eui.Image;
		private imgXdui: eui.Image;

		private type: number;
		private id: number;
		private loaded:boolean;

		public constructor() {
			super();
			this.loaded = false;
			this.addEventListener(eui.UIEvent.COMPLETE, this.onUIComplete, this);
			this.skinName = "resource/skins/game/baccarat/BJLMainWayItemSkin.exml"
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
			this.imgZdui.visible = false;
			this.imgXdui.visible = false;
			let imgRes = ["ludan_json.z_zhuang", "ludan_json.z_he", "ludan_json.z_xian", "ludan_json.z_da", "ludan_json.z_xiao", "ludan_json.z_long", "ludan_json.z_hu"];
			if (this.type == 1) {
				let zhuang_flag = Math.floor(this.id / 4);
				let two_flag = this.id % 4;
				this.imgZX.source = RES.getRes(imgRes[zhuang_flag])
				if (two_flag == 1) {
					this.imgXdui.visible = true;
				}
				else if (two_flag == 2) {
					this.imgZdui.visible = true;
				}
				else if (two_flag == 3) {
					this.imgXdui.visible = true;
					this.imgZdui.visible = true;
				}
			}
			else if (this.type == 2) {
				if (this.id == 3) {
					this.imgZX.source = imgRes[3]
				}
				else {
					this.imgZX.source = imgRes[4]
				}
			}
			else if (this.type == 3) {
				if (this.id == 1) {
					this.imgZX.source = imgRes[5]
				}
				else if (this.id == 2) {
					this.imgZX.source = imgRes[1]
				}
				else {
					this.imgZX.source = imgRes[6]
				}
			}

		}
		public clear(): void {
			this.parent.removeChild(this);
		}
	}
}