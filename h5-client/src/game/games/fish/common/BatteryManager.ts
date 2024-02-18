module fish {
	export class BatteryManager extends BaseClass {

		private parentObj: egret.DisplayObjectContainer;
		private lockParent: egret.DisplayObjectContainer;
		private batteryLst: any;
		private clearCallback: Function;

		public constructor() {
			super();
			this.batteryLst = {};
		}

		public init(parentObj: egret.DisplayObjectContainer, lockParent: egret.DisplayObjectContainer) {
			this.parentObj = parentObj;
			this.lockParent = lockParent;
		}

		public destroy() {
			this.parentObj = null;
			this.lockParent = null;
			this.batteryLst = {};
			this.clearCallback = null;
		}

		public getLockParent(): egret.DisplayObjectContainer {
			return this.lockParent;
		}

		// info : {userInfo:{playerId,seatId,chips,isLocal}}
		public createBattery(info: any, isInit: boolean): void {
			if (!this.parentObj) {
				return;
			}
			let relativePosition = this.getRelativePosition(info.userInfo.seatId);
			let battery: Battery = this.batteryLst[relativePosition];
			if (battery) {
				battery.setUserInfo(info.userInfo);
			} else {
				let posCfg = FishMgr.Config.BatteryPos[relativePosition];
				battery = new Battery(posCfg.skin);
				let bInfo = {
					configId: posCfg.batt_cfg_id,
					userInfo: info.userInfo,
					side: posCfg.side,
					posCfg: posCfg,
				}
				this.parentObj.addChild(battery);
				battery.setBatteryInfo(bInfo, isInit);
				battery.setUserInfo(info.userInfo);
				this.batteryLst[relativePosition] = battery;
			}
		}

		public removeBatteryBySeatId(seatId: number): void {
			if (seatId) {
				let relativePosition = this.getRelativePosition(seatId);
				let battery: Battery = this.batteryLst[relativePosition];
				if (battery) {
					battery.onDestroy();
				}
				delete this.batteryLst[relativePosition];
			}
		}

		public getBatteryBySeatId(seatId: number): Battery {
			if (seatId) {
				let relativePosition = this.getRelativePosition(seatId);
				return this.batteryLst[relativePosition];
			}
			return null;
		}

		public resetLockFishLst(lst: Fish[]): void {
			for (var p in this.batteryLst) {
				let battery: Battery = this.batteryLst[p]
				if (battery) {
					battery.resetLockFishLst(lst);
				}
			}
		}

		public clearAllLockState(): void {
			for (var p in this.batteryLst) {
				let battery: Battery = this.batteryLst[p]
				if (battery) {
					battery.resetLockFishLst([]);
					battery.unLockFish();
				}
			}
			if (this.clearCallback) {
				this.clearCallback();
			}
		}

		public getMyBattery(): Battery {
			for (var p in this.batteryLst) {
				let battery: Battery = this.batteryLst[p]
				if (battery && battery.isMyBattery()) {
					return battery;
				}
			}
			return null;
		}

		public getBatteryByPId(playerId): Battery {
			for (var p in this.batteryLst) {
				let battery: Battery = this.batteryLst[p]
				if (battery && playerId.compare(battery.getPlayerId()) == 0 ) {
					return battery;
				}
			}
			return null;
		}

		public rotateByPos(point: egret.Point): void {
			let battery = this.getMyBattery();
			if (battery) {
				battery.rotationBatteryByPos(point);
			}
		}

		public setClearLockStateCall(call: Function): void {
			this.clearCallback = call;
			for (var p in this.batteryLst) {
				let battery: Battery = this.batteryLst[p]
				if (battery && battery.isMyBattery()) {
					battery.setClearLockStateCall(call);
				}
			}
		}

		public update(dt: number): void {
			for (var p in this.batteryLst) {
				if (this.batteryLst[p]) {
					this.batteryLst[p].update(dt);
				}
			}
		}

		//获取炮台相对位置（"我" 始终在左下角， 座位顺序为 上：1、2, 下：4、3）
		private getRelativePosition(seatId: number): number {
			let position = seatId;
			if (FishMgr.seatId == seatId) {
				return 4;
			}
			if (FishMgr.seatId == 1) {
				if (seatId == 2) {
					position = 3;
				} else if (seatId == 3) {
					position = 2;
				} else if (seatId == 4) {
					position = 1;
				}
			} else if (FishMgr.seatId == 2) {
				if (seatId == 1) {
					position = 3;
				} else if (seatId == 3) {
					position = 1;
				} else if (seatId == 4) {
					position = 2;
				}
			} else if (FishMgr.seatId == 3) {
				if (seatId == 1) {
					position = 2;
				} else if (seatId == 2) {
					position = 1;
				} else if (seatId == 4) {
					position = 3;
				}
			}
			return position;
		}
	}
}