module fish {
	export class FishGoldManager extends BaseClass {

		private parentObj: egret.DisplayObjectContainer;
		private goldCfg: any;
		private goldMusicId: number;
		private fishGoldList: FishGold[] = [];

		public constructor() {
			super();
		}

		public init(parentObj: egret.DisplayObjectContainer): void {
			this.parentObj = parentObj;
			this.goldCfg = FishMgr.Config.FishGold;
			this.goldMusicId = FishMgr.Config.System['gold_mus_id'];
		}

		public destroy() {
			this.parentObj = null;
			this.goldCfg = null;
			this.goldMusicId = null;
		}

		// {id, objId}
		public preCreated(info: any): FishGold {
			let fishGold = new FishGold(info);
			return fishGold;
		}

		public clear(): void {
			this.fishGoldList.forEach((e: FishGold) => {
				e.resumeOrgin();
				e.setUseState(false);
				if (e.parent) {
					e.parent.removeChild(e);
				}
			});
			this.fishGoldList = [];
		}

		// {startPos{x,y}, endPos{x,y}, angle, number}
		public play(info: any): void {
			if (!this.parentObj) {
				return;
			}
			if (!this.goldCfg) {
				this.goldCfg = FishMgr.Config.FishGold;
			}
			let id = 1;
			for (var p in this.goldCfg) {
				if (info.number > this.goldCfg[p].gold_num) {
					id = this.goldCfg[p].id;
					break;
				}
			}
			let maxNum = Math.ceil(info.number / this.goldCfg[id].gold_num);
			if(maxNum > 10) {
				maxNum = 10;
			}
			let distance = 0;
			for (let i = 0; i < maxNum; i++) {
				let fishGold = FishMgr.FishObjectPool.getObject(ObjectClassType.TypeGold, id);
				this.parentObj.addChild(fishGold);
				this.fishGoldList.push(fishGold);
				let idx = i;
				if (idx >= maxNum / 2 && maxNum > 1) {
					idx = maxNum - i;
				}
				distance = idx * Math.sqrt(Math.pow(fishGold.width, 2) + Math.pow(fishGold.height, 2))
					+ FishMgr.Config.System['gold_distance'];
				let newPos = this.getObjPos(maxNum, i, info.angle, FishMgr.Config.System['gold_arrang_way'], distance);
				fishGold.setFinishCall(function (obj: FishGold) {
					obj.resumeOrgin();
					obj.setUseState(false);
					if (obj.parent) {
						obj.parent.removeChild(obj);
					}
				}, this);
				info.startPos = this.parentObj.localToGlobal(info.startPos.x, info.startPos.y);
				info.endPos = this.parentObj.localToGlobal(info.endPos.x, info.endPos.y);
				newPos.x = info.startPos.x - newPos.x;
				newPos.y = info.startPos.y - newPos.y;
				fishGold.startPlay(newPos, info.endPos);
			}

			FishMgr.MusicManager.play(this.goldMusicId);
		}

		private getObjPos(maxNum, curNum, angle, arrangeWay, dis): any {
			let pos = { x: 0, y: 0 };
			if (arrangeWay == 1) {
				if (curNum < maxNum / 2) {
					pos.x = dis * Math.cos(Math.PI - (angle / 180) * Math.PI);
					pos.y = dis * Math.sin(Math.PI - (angle / 180) * Math.PI);
				} else {
					pos.x = -dis * Math.cos(Math.PI - (angle / 180) * Math.PI);
					pos.y = -dis * Math.sin(Math.PI - (angle / 180) * Math.PI);
				}
			} else {
				// doNothing
			}

			return pos;
		}


	}
}