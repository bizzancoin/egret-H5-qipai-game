module fish {
	export class BulletManager extends BaseClass {
		private parentObj: egret.DisplayObjectContainer;
		private aliveLst: any;
		private aliveBBXLst: any;
		public constructor() {
			super();
			this.aliveLst = {};
			this.aliveBBXLst = {};
		}

		public init(parentObj: egret.DisplayObjectContainer) {
			this.parentObj = parentObj;
		}

		// {id, objId}
		public preCreated(info: any): Bullet {
			let bullet = new Bullet(info);
			return bullet;
		}

		public destroy() {
			this.parentObj = null;
			this.aliveLst = {};
			this.aliveBBXLst = {};
		}

		// {id,pos{x,y},angle,side,lockfishId,userInfo{playerId,seatId,serverId}}
		public sendBullet(info: any): void {
			if (!this.parentObj) {
				return;
			}
			let bullet: Bullet = FishMgr.FishObjectPool.getObject(ObjectClassType.TypeBullet, info.id);
			if (bullet) {
				this.parentObj.addChild(bullet);
				let point = this.parentObj.globalToLocal(info.pos.x, info.pos.y);
				info.pos = { x: point.x, y: point.y };
				bullet.setStartPosAndAngle(info.pos, info.angle, info.side, info.lockfishId);
				bullet.setUserInfo(info.userInfo);
				bullet.setUseState(true);
				this.aliveLst[bullet.getObjId()] = bullet;
				this.aliveBBXLst[bullet.getObjId()] = bullet.getBondingBox();
			}
		}

		public update(dt): void {
			for (var p in this.aliveLst) {
				let bullet: Bullet = this.aliveLst[p];
				if (bullet) {
					bullet.update(dt);
					this.aliveBBXLst[bullet.getObjId()] = bullet.getBondingBox();
				}
			}
		}

		public getBulletAliveLst(): Bullet[] {
			let lst: Bullet[] = [];
			for (var p in this.aliveLst) {
				let bullet: Bullet = this.aliveLst[p];
				if (bullet) {
					lst.push(bullet);
				}
			}
			return lst;
		}

		public removeBulletObject(objId: number): void {
			let obj: Bullet = this.aliveLst[objId];
			if (obj) {
				delete this.aliveLst[objId];
				delete this.aliveBBXLst[objId];
				obj.shotSomething();
				obj.resumeOrgin();
				obj.setUseState(false);
				if (obj.parent) {
					obj.parent.removeChild(obj);
				}
			}
		}

		public removeBulletObjectBySetId(seatId: number): void {
			for (var p in this.aliveLst) {
				let bullet: Bullet = this.aliveLst[p];
				if (bullet && seatId == bullet.getBulletSeatId()) {
					bullet.setUseState(false);
					bullet.resumeOrgin();
					delete this.aliveLst[bullet.getObjId()];
					delete this.aliveBBXLst[bullet.getObjId()];
				}
			}
		}

		public getBulletBBX(objId: number): any {
			return this.aliveBBXLst[objId];
		}
	}
}