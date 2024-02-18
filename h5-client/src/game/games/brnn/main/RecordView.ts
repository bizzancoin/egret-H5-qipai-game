module brnn {
	export class RecordView extends eui.Component {

		private groupContent: eui.Group;
		private ggTexture: egret.Texture;
		private xxTexture: egret.Texture;
		private groupParent: eui.Group;

		private itemLsit: any;

		private binTable = [
			[0, 0, 0, 0], [0, 0, 0, 1], [0, 0, 1, 0], [0, 0, 1, 1],
			[0, 1, 0, 0], [0, 1, 0, 1], [0, 1, 1, 0], [0, 1, 1, 1],
			[1, 0, 0, 0], [1, 0, 0, 1], [1, 0, 1, 0], [1, 0, 1, 1],
			[1, 1, 0, 0], [1, 1, 0, 1], [1, 1, 1, 0], [1, 1, 1, 1],
		]

		public constructor() {
			super();
			this.skinName = 'resource/skins/brnn/BrnnRecordSkin.exml'
			this.ggTexture = RES.getRes('brnn_atlas1_json.gg');
			this.xxTexture = RES.getRes('brnn_atlas1_json.xx');
			this.itemLsit = {};
		}

		public registerTouchEvent(): void {
			this.groupParent.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt: egret.TouchEvent) => {
				if (evt.target == this.groupParent) {
					this.hide();
				}
			}, this);

			for (let i = 0; i < 44; i++) {
				let item = new RecordItem(this.ggTexture);
				this.groupContent.addChild(item);
				item.visible = false;
				if (!this.itemLsit[i % 11]) {
					this.itemLsit[i % 11] = [];
				}
				this.itemLsit[i % 11].push(item);
			}
		}

		public show(records: any): void {
			if (records && records.length > 0) {
				let recordList = GameApp.clone(records);
				if (recordList.length > 11) {
					recordList.splice(11, recordList.length - 11);
				}
				recordList.reverse();
				for (let i = 0; i < recordList.length; i++) {
					let e = recordList[i];
					let list = this.binTable[e];
					if (i < 11) {
						for (let j = 0; j < list.length; j++) {
							let ret = list[j];
							if (ret) {
								this.itemLsit[i][j].setTexture(this.ggTexture);
							} else {
								this.itemLsit[i][j].setTexture(this.xxTexture);
							}
							this.itemLsit[i][j].visible = true;
						}
					}
				}
			}
			this.visible = true;
		}

		public hide(): void {
			this.visible = false;
		}
	}

	export class RecordItem extends eui.Group {

		private imgItem: eui.Image

		public constructor(texture: egret.Texture) {
			super();
			this.imgItem = new eui.Image();
			this.addChild(this.imgItem);
			this.imgItem.verticalCenter = 0;
			this.imgItem.horizontalCenter = 0;
			this.imgItem.texture = texture;
		}

		public setTexture(texture: egret.Texture): void {
			this.imgItem.texture = texture;
		}

	}
}