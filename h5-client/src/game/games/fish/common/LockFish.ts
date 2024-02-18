module fish {
	export class LockFish extends eui.Group {

		private info: any = {
			side: 0,
			seatId: 0,
			isLocal: false,
			offsetPos: 0,
			bulletOffset: 0, // 子弹偏移量
			key: ''
		}; // 初始数据

		private startPos: any = { x: 0, y: 0 };
		private block = false;
		private isLockSuccess = false;
		private lockFishLst: Fish[] = [];
		private curLockIdx = 0;
		private curLockObjId = -1;
		private lockItemDis = 30;
		private curLockAngle = 0;
		private lockCall = null;
		private isLockChange = false;
		private isForceLock = false;
		private frontForceLockObjId = -1;
		private lineWidth = 32;
		private lastWidth = 32;
		private lastItem: eui.Image;
		private lockLineItems: eui.Image[] = [];
		private lineTexture: egret.Texture;

		public constructor(info: any) {
			super();
			this.touchEnabled = false;
			this.height = 1080;
			this.width = 1920;
			this.info = info;
			this.touchEnabled = false;
			this.initData();
		}

		private initData(): void {
			this.startPos = { x: 0, y: 0 };

			this.block = false;
			this.isLockSuccess = false;

			this.lockFishLst = [];
			this.curLockIdx = 0;
			this.curLockObjId = -1;

			this.lockItemDis = 30;

			this.curLockAngle = 0;
			this.lockCall = null;

			this.isLockChange = false;

			this.isForceLock = false;
			this.frontForceLockObjId = -1;

			this.lastItem = new eui.Image();
			this.lastItem.texture = RES.getRes(this.info.key);
			this.lastItem.anchorOffsetX = this.lastItem.width / 2;
			this.lastItem.anchorOffsetY = this.lastItem.height / 2;
			this.lastItem.visible = false;
			this.addChild(this.lastItem);
			this.lastWidth = this.lastItem.width;

			this.lineTexture = RES.getRes(this.info.key + '_1')
			this.lineWidth = this.lineTexture.textureWidth;
			// 锁定连线项(Image)
			this.lockLineItems = [];
		}

		public lock(): void {
			this.block = true;
			this.isLockChange = false;
			this.isForceLock = false;
			this.frontForceLockObjId = -1;
			if (this.lockFishLst.length > 0) {
				this.curLockIdx += 1;
				if (this.curLockIdx > this.lockFishLst.length) {
					this.curLockIdx = -1;
				}

				let curFishObj = this.lockFishLst[this.curLockIdx];
				if (curFishObj) {
					this.curLockObjId = curFishObj.getObjId();
				}

				if (this.lockCall) {
					this.lockCall();
				}
			}
		}

		public setLockFish(fishId: number): void {
			if (!this.info.isLocal) {
				this.block = true;
			}
			if (this.block && fishId != -1) {
				this.isLockSuccess = true;
				this.curLockObjId = fishId;
			}
		}

		public forceToLockFish(fishId): void {
			if (this.info.isLocal && (fishId && fishId != -1)) {
				this.block = true;
				this.isForceLock = true;
				this.frontForceLockObjId = this.curLockObjId;
				this.curLockObjId = fishId;
				this.isLockSuccess = true;
			}
		}

		public resetLockLst(lst: Fish[]): void {
			this.lockFishLst = lst;
			if (!this.lockFishLst || this.lockFishLst.length <= 0) {
				this.lockFishLst = [];
			}
		}

		public unlock(): void {
			this.curLockIdx = 0;
			this.curLockObjId = -1;
			this.isForceLock = false;
			this.frontForceLockObjId = -1;

			this.block = false;
			this.isLockSuccess = false;
			this.lastItem.visible = false;
			this.curLockAngle = 0;

			this.lockLineItems.forEach(e => {
				e.visible = false;
			});
		}

		public selectLockFish(): any {
			if (this.isForceLock && this.info.isLocal) {
				let fishObj: Fish = FishMgr.FishManager.getFishObjById(this.curLockObjId);
				if (fishObj && this.isFishCanLock(fishObj)) {
					this.isLockSuccess = true;
					if (this.lockCall) {
						if (this.frontForceLockObjId != this.curLockObjId && this.curLockObjId != -1) {
							this.frontForceLockObjId = this.curLockObjId;
							this.isLockChange = true;
							this.lockCall(this.curLockObjId);
						}
					}
					return fishObj;
				}
				this.isLockSuccess = false;
				return null;
			}

			if (!this.info.isLocal) {
				let fishObj: Fish = FishMgr.FishManager.getFishObjById(this.curLockObjId);
				if (this.isFishCanLock(fishObj)) {
					this.isLockSuccess = true;
					this.isLockChange = true;
					return fishObj;
				}
				
				this.isLockSuccess = false;
				return null;
			}

			let newIdx = -1;
			for (let i = 0; i < this.lockFishLst.length; i++) {
				let e = this.lockFishLst[i];
				if (this.curLockObjId == e.getObjId() && this.isFishCanLock(e)) {
					this.isLockChange = true;
					return e;
				}

				if (this.isFishCanLock(e) && newIdx == -1) {
					newIdx = i;
					break;
				}
			}

			if (newIdx == -1) {
				this.curLockIdx = 0;
				this.curLockObjId = -1;
				this.isLockSuccess = false;
				this.isLockChange = false;
			} else {
				this.curLockIdx = newIdx;
				this.curLockObjId = this.lockFishLst[this.curLockIdx].getObjId();
				if (this.lockCall) {
					this.lockCall(this.curLockObjId);
				}
				this.isLockChange = true;
				this.isLockSuccess = true;
			}

			return this.lockFishLst[this.curLockIdx];
		}

		private isFishCanLock(fishObj: Fish): boolean {
			if (fishObj) {
				let fishPos = fishObj.getCurrentPos();  // 鱼的屏幕坐标
				let battery: Battery = FishMgr.BatteryManager.getBatteryBySeatId(this.info.seatId); //炮台
				let bulletPos = battery.getSendBulletPos(); //发送子弹的位置，屏幕坐标
				bulletPos = this.globalToLocal(bulletPos.x, bulletPos.y);
				fishPos = this.globalToLocal(fishPos.x, fishPos.y)
				let rect: egret.Rectangle = new egret.Rectangle(0, 0, 1920, 1080);
				if (rect.contains(fishPos.x, fishPos.y) && this.isPosValid(this.info.side, bulletPos, fishPos)) {
					return true;
				}
			}

			return false;
		}

		private isPosValid(side: number, startPos: any, endPos: any): boolean {
			let angle = this.getRotationAngle(startPos, endPos, side);
			let angleValide = false;
			if (side == 1 && ((angle >= 90 && angle <= 90) || (angle >= 270 && angle <= 360)) && startPos.x < endPos.x) {
				angleValide = true;
			} else if (side == 2 && (angle >= 90 && angle <= 90 && startPos.x > endPos.x)) {
				angleValide = true;
			} else if (side == 3 && (angle >= 0 && angle <= 180 && endPos.y > startPos.y)) {
				angleValide = true;
			} else if (side == 4 && (angle >= 180 && angle <= 360 && endPos.y < startPos.y)) {
				angleValide = true;
			}

			return angleValide;
		}

		private getRotationAngle(pos1: any, pos2: any, side: number): number {
			let angle = 0
			if (pos1.x == pos2.x) {
				if (side == 1 && pos1.y >= pos2.y) {
					angle = 90;
				} else if (side == 1 && pos1.y < pos2.y) {
					angle = 270;
				} else if (side == 2 && pos1.y >= pos2.y) {
					angle = 90;
				} else if (side == 2 && pos1.y < pos2.y) {
					angle = 270;
				} else if (side == 3) {
					angle = 270;
				} else if (side == 4) {
					angle = 90;
				}
			} else {
				let k = (pos1.y - pos2.y) / (pos1.x - pos2.x)
				angle = Math.atan(k) * 180 / Math.PI;
				if (side == 1 && angle <= 0) {
					angle = 2 * 180 + angle;
				} else if (side == 2) {
					angle = 180 + angle;
				} else if (side == 3 && angle <= 0) {
					angle = 180 + angle;
				} else if (side == 3 && angle > 0) {
					angle = angle;
				} else if (side == 4 && angle <= 0) {
					angle = 360 + angle;
				} else if (side == 4 && angle > 0) {
					angle = 180 + angle;
				}
			}
			return angle;
		}

		public update(dt): void {
			if (this.block) {
				let fishObj: Fish = this.selectLockFish();
				if (fishObj && this.isLockSuccess) {
					let battery: Battery = FishMgr.BatteryManager.getBatteryBySeatId(this.info.seatId);
					if (battery) {
						let sp = GameApp.clone(battery.getBarrelPos());
						this.startPos = this.globalToLocal(sp.x, sp.y);
					}
					let ep = GameApp.clone(fishObj.getCurrentPos());
					let endPos = this.globalToLocal(ep.x, ep.y);
					this.resetLockLineByPos(this.startPos, endPos);
				} else {
					this.lockLineItems.forEach(e => {
						e.visible = false;
					});
					this.lastItem.visible = false;
					if (this.isForceLock && this.info.isLocal) {
						this.isForceLock = false;
						this.block = false;
						this.isLockSuccess = false;
					}
				}
			}
		}

		public resetLockLineByPos(pos1: any, pos2: any): void {
			let angle = this.getRotationAngle(pos1, pos2, this.info.side);
			let distance = App.MathUtils.getDistanceByPoint(pos1, pos2);
			distance = distance - (this.lineWidth + this.lastWidth) - this.info.bulletOffset;

			let showIdx = 0;
			let distAdd = 0;

			let len = this.lockLineItems.length;
			let spacing = this.lineWidth * 2 + this.lockItemDis;

			for (let i = 0; i < len; i++) {
				distAdd = i * spacing;
				if (distAdd >= distance) {
					showIdx = i;
					break;
				}
				let curDis = i * spacing + this.info.bulletOffset;
				let pos = this.getPosByDistanceAndAngle(curDis, angle);
				this.lockLineItems[i].x = pos.x;
				this.lockLineItems[i].y = pos.y;
				this.lockLineItems[i].visible = true;
			}

			while (distAdd < distance) {
				let obj: eui.Image = new eui.Image();
				obj.texture = this.lineTexture;
				obj.anchorOffsetX = obj.width / 2;
				obj.anchorOffsetY = obj.height / 2;
				let pos = this.getPosByDistanceAndAngle(distAdd, angle);
				distAdd += spacing;
				obj.x = pos.x;
				obj.y = pos.y;
				obj.visible = true;
				obj.touchEnabled = false;
				this.lockLineItems.push(obj);
				this.addChild(obj);
				showIdx++;
			}

			this.lastItem.x = pos2.x;
			this.lastItem.y = pos2.y;
			this.lastItem.visible = true;
			for (let i = showIdx; i < this.lockLineItems.length; i++) {
				this.lockLineItems[i].visible = false;
			}
			this.curLockAngle = angle;
		}

		private getPosByDistanceAndAngle(dis: number, angle: number): any {
			let sx = dis * Math.cos(angle / 180 * Math.PI);
			let sy = dis * Math.sin(angle / 180 * Math.PI);

			return { x: this.startPos.x + sx, y: this.startPos.y + sy };
		}

		public getLockState(): boolean {
			return this.block && this.isLockSuccess;
		}

		public getCurrentLockFishId(): number {
			if (this.getLockState()) {
				return this.curLockObjId;
			}
			return -1;
		}

		public getCurrentLockAngle(): number {
			return this.curLockAngle;
		}

		public setLockCall(call: Function): void {
			this.lockCall = call;
		}

		public onDestroy(): void {
			this.lockLineItems.forEach(e => {
				if(e.parent) {
					e.parent.removeChild(e);
				}
			});
			if (this.parent) {
				this.parent.removeChild(this);
			}
		}
	}
}