module fish {
	export class FishNetManager extends BaseClass {

		private parentObj: egret.DisplayObjectContainer;
		private fishNetList: FishNet[] = [];

		public constructor() {
			super();
		}

		public init(parentObj: egret.DisplayObjectContainer) {
			this.parentObj = parentObj;
			this.fishNetList = []
		}

		public destroy() {
			this.parentObj = null;
		}

		public preCreated(info: any): FishNet {
			let fishNet = new FishNet(info);
			return fishNet;
		}

		public clear(): void {
			this.fishNetList.forEach((e: FishNet) => {
				e.resumeOrgin();
				e.setUseState(false);
				if (e.parent) {
					e.parent.removeChild(e);
				}
			});
			this.fishNetList = [];
		}

		// {id, pos{x,y}}
		public play(info: any): void {
			if (!this.parentObj) {
				return;
			}
			let fishNet: FishNet = FishMgr.FishObjectPool.getObject(ObjectClassType.TypeNet, info.id)
			if (fishNet) {
				this.parentObj.addChild(fishNet);
				fishNet.setFinishCall(function (obj: FishNet) {
					obj.resumeOrgin();
					obj.setUseState(false);
					if (obj.parent) {
						obj.parent.removeChild(obj);
					}
				}, this);
				fishNet.startPlay(info.pos);
				this.fishNetList.push(fishNet);
			}
		}
	}
}