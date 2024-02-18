module brnn {
	export class WaitView extends eui.Component {

		private lstWait: eui.List;
		private scroller: eui.Scroller;
		private groupContent: eui.Component;
		private array: eui.ArrayCollection
		private groupParent: eui.Group;
		private btnXiaZ: eui.Button;
		private btnShangZ: eui.Button;

		public constructor() {
			super();
			this.skinName = 'resource/skins/brnn/BrnnWaitSkin.exml';
		}

		public init(btnCall:Function, btnCallObj:any): void {
			this.lstWait = new eui.List();
			var layout: eui.VerticalLayout = new eui.VerticalLayout();
			layout.verticalAlign = egret.VerticalAlign.MIDDLE;
			layout.horizontalAlign = egret.HorizontalAlign.CENTER;
			layout.gap = 0;
			this.lstWait.layout = layout;
			this.lstWait.itemRenderer = WaitItem;
			this.lstWait.itemRendererSkinName = 'resource/skins/brnn/BrnnWaitItemSkin.exml';
			this.lstWait.width = 380;
			this.lstWait.height = 41;
			this.scroller.viewport = this.lstWait;

			this.array = new eui.ArrayCollection();
			this.lstWait.dataProvider = this.array;
			this.groupParent.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt: egret.TouchEvent) => {
				if (this.groupParent == evt.target) {
					this.hide();
				}
			}, this);

			this.btnXiaZ.addEventListener(egret.TouchEvent.TOUCH_TAP,()=>{
				btnCall.apply(btnCallObj, [false]);
			}, this);
			this.btnShangZ.addEventListener(egret.TouchEvent.TOUCH_TAP,()=>{
				btnCall.apply(btnCallObj, [true]);
			}, this);
		}

		public show(waits: any, isApply: boolean): void {
			this.array.removeAll();
			if (waits && waits.length > 0) {
				waits.forEach(e => {
					this.array.addItem(e);
				});
			}

			if (isApply) {
				this.btnXiaZ.visible = true;
				this.btnShangZ.visible = false;
			} else {
				this.btnXiaZ.visible = false;
				this.btnShangZ.visible = true;
			}

			this.visible = true;

			egret.Tween.get(this.groupContent).to({ top: 0 }, 500);
		}

		public hide(): void {
			egret.Tween.get(this.groupContent).to({ top: -408 }, 500).call(()=>{
				this.visible = false;
			}, this);
		}

	}

	export class WaitItem extends eui.ItemRenderer {

		private lbNick: eui.Label;
		private lbGold: eui.Label;

		public constructor() {
			super();
		}

		public dataChanged() {
			super.dataChanged();
			if (this.lbNick) {
				this.lbNick.text = this.data.nickname;
			}
			if (this.lbGold) {
				this.lbGold.text = this.data.gold;
			}
		}

		public partAdded(partName: string, instance: any): void {
			super.partAdded(partName, instance);
			if (!this.data)
				return;
			if (instance == this.lbNick) {
				this.lbNick.text = this.data.nickname;
			}
			if (this.lbGold) {
				this.lbGold.text = this.data.gold;
			}
		}

	}
}