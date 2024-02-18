module fish {
	export class FishManager extends BaseClass {

		private parentObj: egret.DisplayObjectContainer;
		private aliveFishLst: any = {};
		private aliveBBXInfoLst: any = {};
		private fishLevelInfo: any = { level: 0, fishId: -1 };
		public constructor() {
			super();
			this.aliveFishLst = {};
			this.aliveBBXInfoLst = {};
			this.fishLevelInfo = { level: 0, fishId: -1 };
		}

		public init(parentObj: egret.DisplayObjectContainer): void {
			this.parentObj = parentObj;
		}

		// {objId, id}
		public preCreated(info: any) {
			let fishObj = new Fish(info);
			return fishObj;
		}

		public destroy() {
			this.parentObj = null;
			this.aliveFishLst = {};
			this.aliveBBXInfoLst = {};
		}

		public addFish(fish: any): any {
			if (!this.parentObj) {
				return;
			}
			if (this.aliveFishLst[fish.objId]) {
				let fishObj = this.aliveFishLst[fish.objId];
				fishObj.setRunTime(fish.runTime);
				return false;
			}
			let fishObj: Fish = FishMgr.FishObjectPool.getObject(ObjectClassType.TypeFish, fish.fishId);
			fishObj.setObjId(fish.objId);
			//let fishObj = new Fish({ objId: fish.objId, id: fish.fishId });
			this.parentObj.addChild(fishObj);
			fishObj.setUseState(true);
			fishObj.setFishCurveId(fish.cveId);
			fishObj.setRunTime(fish.runTime);
			fishObj.setStartPos(fish.pos);
			fishObj.setReserve(fish.breserve);
			fishObj.setCurveRotation(fish.angle);
			fishObj.setUserInfo(fish.userInfo);
			//fishObj:setUseLightModel(self.buse_light_mod)
			fishObj.setFishSceneId(BackgroundManager.getInstance().getCurrentSceneId());
			this.aliveFishLst[fishObj.getObjId()] = fishObj;
			this.aliveBBXInfoLst[fishObj.getObjId()] = fishObj.getCurrentBBX();

			fishObj.fishStart();

			if (this.fishLevelInfo.fishId == fish.fishId && this.fishLevelInfo.fishId) {
				fishObj.setFishLevel(this.fishLevelInfo.level);
				FishMgr.SkillManager.play(fishObj, 4);
			}
			return true;
		}

		public removeFish(objId: number): void {
			let fish: Fish = this.aliveFishLst[objId];
			if (fish) {
				if (fish) {
					fish.fishDead();
					delete this.aliveFishLst[objId];
					delete this.aliveBBXInfoLst[objId];
				}
			}
		}

		public update(dt: number): void {
			for (var p in this.aliveFishLst) {
				let v: Fish = this.aliveFishLst[p];
				if (!v) {
					return;
				}
				v.update(dt);
				this.aliveBBXInfoLst[p] = v.getCurrentBBX();
				if (v.isOutofTime()) {
					v.fishOut();
					delete this.aliveFishLst[p];
					delete this.aliveBBXInfoLst[p];
				}
			}
			if (FishMgr.BackgroundManager.getSceneState() == 1) {
				FishMgr.BatteryManager.resetLockFishLst(this.getFishLevelLst());
			}
		}

		public shotFish(bbx): Fish {
			for (var p in this.aliveBBXInfoLst) {
				let v = this.aliveBBXInfoLst[p];
				if (!v) {
					return null;
				}
				for (let i = 0; i < bbx.length; i++) {
					let a = bbx[i];
					for (let j = 0; j < v.length; j++) {
						let b = v[j];
						let dis = Math.sqrt(Math.pow(v[j].a - bbx[i].a, 2) + Math.pow(v[j].b - bbx[i].b, 2));
						if (v[j].r + bbx[i].r > dis) {
							return this.aliveFishLst[p];
						}
					}
				}
			}
			return null;
		}

		public clickFish(pos): Fish {
			for (var p in this.aliveBBXInfoLst) {
				let v = this.aliveBBXInfoLst[p];
				if (!v) {
					return null;
				}
				for (let j = 0; j < v.length; j++) {
					let dis = Math.sqrt(Math.pow(v[j].a - pos.x, 2) + Math.pow(v[j].b - pos.y, 2));
					if (v[j].r > dis) {
						return this.aliveFishLst[p];
					}
				}
			}
			return null;
		}

		public getAliveFishObjectLst() {
			return this.aliveFishLst;
		}

		public getAliveFish(objId: number): Fish {
			if (objId) {
				return this.aliveFishLst[objId];
			}
			return null;
		}

		public getFishLevelLst(): Fish[] {
			let lst = [];
			for (var p in this.aliveFishLst) {
				let fish: Fish = this.aliveFishLst[p];
				if (fish && fish.getLockLevel() > 0) {
					lst.push(fish);
				}
			}
			lst.sort(function (a: Fish, b: Fish) {
				return a.getLockLevel() - b.getLockLevel();
			});
			return lst;
		}

		public getFishObjById(objId: number): Fish {
			return this.aliveFishLst[objId];
		}

		public clearAllFish(): void {
			for (var p in this.aliveFishLst) {
				let fish: Fish = this.aliveFishLst[p];
				if (fish && fish.getFishSceneId() != FishMgr.BackgroundManager.getCurrentSceneId() && fish.getFishSceneId() != -1) {
					delete this.aliveFishLst[fish.getObjId()];
					delete this.aliveBBXInfoLst[fish.getObjId()];
					fish.resumeOrgin();
				}
			}
		}

		public clearFishAll(): void {
			for (var p in this.aliveFishLst) {
				let fish: Fish = this.aliveFishLst[p];
				delete this.aliveFishLst[fish.getObjId()];
				delete this.aliveBBXInfoLst[fish.getObjId()];
				fish.resumeOrgin();
			}
		}
	}
}