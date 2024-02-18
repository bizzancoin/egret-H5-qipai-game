module fish {
	export class FishLabelManager extends BaseClass {

		private parentObj: egret.DisplayObjectContainer;
		private fishLabelList: FishLabel[] = [];

		public constructor() {
			super();
		}

		public init(parentObj: egret.DisplayObjectContainer) {
			this.parentObj = parentObj;
		}

		public destroy() {
			this.parentObj = null;
		}

		// {id, objId}
		public preCreated(info: any): FishLabel {
			let fishLabel = new FishLabel(info);
			return fishLabel;
		}

		public clear(): void {
			this.fishLabelList.forEach((e: FishLabel) => {
				e.resumeOrgin();
				e.setUseState(false);
				if (e.parent) {
					e.parent.removeChild(e);
				}
			});
			this.fishLabelList = [];
		}

		// pos{x,y}
		public play(pos: any, number: any): void {
			if (!this.parentObj) {
				return;
			}
			let id = FishMgr.Config.System['gold_fnt_index'];
			let fishLabel: FishLabel = FishMgr.FishObjectPool.getObject(ObjectClassType.TypeLabel, id);
			fishLabel.setFinishCall(function (obj: FishLabel) {
				obj.resumeOrgin();
				obj.setUseState(false);
				if (obj.parent) {
					obj.parent.removeChild(obj);
				}
			}, this);
			let globalPos = this.parentObj.globalToLocal(pos.x, pos.y);
			fishLabel.play(globalPos, number);
			this.parentObj.addChild(fishLabel);
			this.fishLabelList.push(fishLabel);
		}
	}
}