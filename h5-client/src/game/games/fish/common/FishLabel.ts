module fish {
	/**
	 * 得分票子
	 */
	export class FishLabel extends eui.BitmapLabel {

		private info: any;
		private objId: number;
		private dataId: number;
		private isUse: boolean;
		private finishCall: any;

		public constructor(info: any) {
			super();
			this.info = info;
			this.init();
		}

		private init() {
			this.dataId = this.info.id;
			this.objId = this.info.objId;
			let fontSrc = FishMgr.Config.LabelFnt[this.info.id];
			let font = RES.getRes(fontSrc.src);
			this.font = font;
			this.setUseState(false);
			this.visible = false;
			this.width = 500;
			this.height = 120;
			this.anchorOffsetX = this.width / 2;
			this.anchorOffsetY = this.height / 2;
			this.x = 0;
			this.y = 0;
			this.touchEnabled = false;
		}

		public resumeOrgin(): void {
			this.visible = false;
			this.finishCall = null;
			this.text = '0';
			this.x = 0;
			this.y = 0;
		}

		public play(pos: any, number: any): void {
			this.setUseState(true);
			this.text = number;
			this.x = pos.x;
			this.y = pos.y;
			this.textAlign = egret.HorizontalAlign.CENTER;
			this.visible = true;
			let orgY = this.y;
			egret.Tween.get(this)
				.to({ y: orgY + 120 }, 600)
				.to({ y: orgY }, 400)
				.call(function () {
					if (this.finishCall) {
						this.finishCall[0].apply(this.finishCall[1], [this]);
					}
				}, this);
		}

		public setFinishCall(finishCall: Function, finishObj: any): void {
			if (finishCall && finishObj) {
				this.finishCall = [finishCall, finishObj];
			}
		}

		public getDataId(): number {
			return this.dataId;
		}

		public getObjId(): number {
			return this.objId;
		}

		public setUseState(state: boolean): void {
			this.isUse = state;
		}

		public getUseState(): boolean {
			return this.isUse;
		}

	}
}