module fish {
	/**
	 * 碰撞检测
	 */
	export class CollisionManager extends BaseClass {

		private callBack: any;

		public constructor() {
			super();
		}

		public registerCallback(method: Function, methodObj: any) {
			this.callBack = [method, methodObj];
		}

		public destroy() {
			this.callBack = null;
		}

		public update(dt: number): void {
			let shotBulletLst: any[] = [];
			let bulletLst = FishMgr.BulletManager.getBulletAliveLst();
			if (bulletLst && bulletLst.length > 0) {
				bulletLst.forEach(v => {
					let bulletId = v.getObjId();
					let bbx = FishMgr.BulletManager.getBulletBBX(bulletId);
					let seatId = v.getBulletSeatId();
					let battery = FishMgr.BatteryManager.getBatteryBySeatId(seatId);
					if (battery) {
						let lockFishId = battery.getLockFishId();
						let lockFish = FishMgr.FishManager.getFishObjById(lockFishId);
						if (lockFish && v.getCurLockFishId() == lockFishId) {
							let isShot = lockFish.isShotFish(bbx);
							if (isShot) {
								let shotItem = { bullet: v, fish: lockFish };
								shotBulletLst.push(shotItem);
							}
						} else {
							let fishObj = FishMgr.FishManager.shotFish(bbx);
							if (fishObj) {
								let shotItem = { bullet: v, fish: fishObj };
								shotBulletLst.push(shotItem);
							}
						}
					} else {
						let fishObj = FishMgr.FishManager.shotFish(bbx);
						if (fishObj) {
							let shotItem = { bullet: v, fish: fishObj };
							shotBulletLst.push(shotItem);
						}
					}
				});
			}

			if (this.callBack) {
				this.callBack[0].apply(this.callBack[1], [shotBulletLst]);
			}
		}

	}
}