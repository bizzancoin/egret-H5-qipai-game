module fish {
	export class Bullet extends eui.Group {

		private config: any;  // 配置信息
		private moveSpeed = 600; //移动速度
		private bondingBox: any; //包围盒
		private frontPos = { x: 0, y: 0 };
		private curPos = { x: 0, y: 0 }; //当前位置（屏幕坐标）
		private curAngle = 0;      // 旋转角度
		private curSide = 0;       // 边 (1 left 2 right 3 top 4 bottom)
		private dataId = 0;
		private objId = 0;
		private userInfo: any = {
			playerId: 0,   //玩家id
			seatId: 0,     //坐位id
			isLocal: false, //是否为本地，区别自己与别人的炮台
			serverId: -1   //服务器子弹id标识
		};
		private curLockFishId = -1;  // 当前锁定的鱼id
		private curLineRunTime = 0;  // 当前方向移动的距离
		private isUse = false;
		private image: eui.Image;

		public constructor(info: any) {
			super();
			this.init(info);
		}

		private init(info: any) {
			this.config = FishMgr.Config.Bullet[info.id];
			this.image = new eui.Image();
			this.image.texture = RES.getRes(this.config.src_key);
			this.image.anchorOffsetX = this.image.width / 2;
			this.image.anchorOffsetY = this.image.height / 2;
			this.visible = false;
			this.moveSpeed = this.config.act_v;
			this.bondingBox = this.config.bonding_box;
			this.dataId = this.config.id;
			this.objId = info.objId;
			this.addChild(this.image);
			// this.createBoundbox();
			this.image.touchEnabled = false;
			this.touchEnabled = false;
		}

		public resumeOrgin() {
			this.visible = false;
			this.rotation = 0;
			this.x = 0;
			this.y = 0;
			this.userInfo = {
				playerId: 0,
				seatId: 0,
				isLocal: true,
				serverId: -1
			};
			this.curLineRunTime = 0;
			this.frontPos = { x: 0, y: 0 };
		}

		private getCurActPos(): any {
			return { x: this.curPos.x + this.frontPos.x, y: this.curPos.y + this.frontPos.y };
		}

		public setStartPosAndAngle(pos: any, angle: number, side: number, lockfishId: number): void {
			this.visible = true;
			this.curAngle = angle;
			this.curSide = side;
			this.curPos = pos;
			this.frontPos = { x: 0, y: 0 };
			this.curLineRunTime = 0;
			this.curLockFishId = lockfishId;
			this.setPosAngAngle(this.curPos, this.curAngle);
		}

		private createBoundbox(): void {
			for (let j = 0; j < this.bondingBox.length; j++) {
				let n = this.bondingBox[j];
				var shp: egret.Shape = new egret.Shape();
				shp.graphics.beginFill(0x00ff00);
				//shp.alpha = 0;
				shp.graphics.drawCircle(n.a, n.b, n.r);
				shp.graphics.endFill();
				shp.width = n.r;
				shp.height = n.r;
				this.addChild(shp);
			}
		}

		public getNewAngleAndSide(angle: number, side: number, pos: any): any {
			let newSide = this.getNewSide(pos, { width: 1920, height: 1080 });
			if (angle == 90 || angle == 270) {
				return { angle: 2 * 180 - angle, side: newSide };
			} else if (angle == 0 || angle == 360) {
				return { angle: 180, side: newSide };
			} else if (angle == 180) {
				return { angle: 0, side: newSide };
			}

			if (side == Side.Bottom) {
				if (newSide == Side.Right) {
					return { angle: 180 - angle, side: newSide };
				} else if (newSide == Side.Top) {
					return { angle: 2 * 180 - angle, side: newSide };
				} else if (newSide == Side.Left) {
					return { angle: 180 - angle, side: newSide };
				}
			} else if (side == Side.Top) {
				if (newSide == Side.Left) {
					return { angle: 3 * 180 - angle, side: newSide };
				} else if (newSide == Side.Right) {
					return { angle: 3 * 180 - angle, side: newSide };
				} else if (newSide == Side.Bottom) {
					return { angle: 2 * 180 - angle, side: newSide };
				}
			} else if (side == Side.Left) {
				if (newSide == Side.Top) {
					return { angle: 2 * 180 - angle, side: newSide };
				} else if (newSide == Side.Bottom) {
					return { angle: 2 * 180 - angle, side: newSide };
				} else if (newSide == Side.Right) {
					if (angle > 90) {
						return { angle: 3 * 180 - angle, side: newSide };
					}
					return { angle: 180 - angle, side: newSide };
				}
			} else if (side == Side.Right) {
				if (newSide == Side.Top) {
					return { angle: 2 * 180 - angle, side: newSide };
				} else if (newSide == Side.Bottom) {
					return { angle: 2 * 180 - angle, side: newSide };
				} else if (newSide == Side.Left) {
					if (angle > 180) {
						return { angle: 3 * 180 - angle, side: newSide };
					}
					return { angle: 180 - angle, side: newSide };
				}
			}

			return { angle: null, side: newSide };
		}

		// 计算新边 left, right, top, bottom
		public getNewSide(pos: any, bsz: any): number {
			let newSide = Side.None;
			if ((pos.x < bsz.width / 2 && pos.y > bsz.height / 2) && (pos.x <= bsz.height - pos.y)) { //at left side
				newSide = Side.Left;
			} else if ((pos.x < bsz.width / 2 && pos.y > bsz.height / 2) && (pos.x > bsz.height - pos.y)) { //at top side
				newSide = Side.Bottom;//
			} else if ((pos.x < bsz.width / 2 && pos.y < bsz.height / 2) && (pos.x <= pos.y)) { //at left side
				newSide = Side.Left;
			} else if ((pos.x < bsz.width / 2 && pos.y < bsz.height / 2) && (pos.x > pos.y)) { //at bottom sie
				newSide = Side.Top;
			} else if ((pos.x > bsz.width / 2 && pos.y > bsz.height / 2) && (bsz.width - pos.x <= bsz.height - pos.y)) { //at right side
				newSide = Side.Right;
			} else if ((pos.x > bsz.width / 2 && pos.y > bsz.height / 2) && (bsz.width - pos.x > bsz.height - pos.y)) { //at top side
				newSide = Side.Bottom;//
			} else if ((pos.x > bsz.width / 2 && pos.y < bsz.height / 2) && (bsz.width - pos.x <= pos.y)) { //at right side
				newSide = Side.Right;
			} else if ((pos.x > bsz.width / 2 && pos.y < bsz.height / 2) && (bsz.width - pos.x > pos.y)) { //at bottom side
				newSide = Side.Top
			}
			return newSide;
		}

		//是否超出屏幕
		private isBulletOutOfBondingRect(): boolean {
			let pos = this.getCurActPos();
			for (let i = 0; i < this.bondingBox.length; i++) {
				let v = this.bondingBox[i];
				let bondingItemPos = { x: v.a + pos.x, y: v.b + pos.y };
				if ((bondingItemPos.x < v.r || 1920 - bondingItemPos.x < v.r)
					|| bondingItemPos.y < v.r || 1080 - bondingItemPos.y < v.r) {
					return true;
				}
			}
			return false;
		}

		// 获取包围盒
		public getBondingBox(): any {
			let pos = this.parent ? this.parent.localToGlobal(this.x, this.y) : { x: this.x, y: this.y };
			let bbxLst = [];
			this.bondingBox.forEach(v => {
				let item: any = {};
				let angle = -this.curAngle;
				let newA = v.a * Math.cos(angle / 180 * Math.PI) + v.b * Math.sin(angle / 180 * Math.PI);
				let newB = v.b * Math.cos(angle / 180 * Math.PI) - v.a * Math.sin(angle / 180 * Math.PI);
				item.a = newA + pos.x;
				item.b = newB + pos.y;
				item.r = v.r;
				bbxLst.push(item);
			});

			return bbxLst;
		}

		public shotSomething(): void {
			let info: any = {};
			info.pos = this.parent.localToGlobal(this.x, this.y);
			if (this.userInfo.isLocal) {
				info.id = this.config.my_fnet_id;
			} else {
				info.id = this.config.oth_net_id;
			}

			FishMgr.FishNetManager.play(info);
		}

		public setUserInfo(userInfo: any): void {
			this.userInfo = userInfo;
		}

		public getUserInfo(): any {
			return this.userInfo;
		}

		private setPosAngAngle(pos: any, angle: number): void {
			if (pos) {
				this.x = pos.x;
				this.y = pos.y;
			}
			if (angle) {
				this.rotation = angle;
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

		public isMyBullet(): boolean {
			return this.userInfo.isLocal;
		}

		public getBulletSeatId(): number {
			return this.userInfo.seatId;
		}

		public getBulletPlayerId(): number {
			return this.userInfo.playerId;
		}

		public getBulletServerId(): number {
			return this.userInfo.serverId;
		}

		public getCurLockFishId(): number {
			return this.curLockFishId;
		}

		public update(dt): void {
			if (this.isBulletOutOfBondingRect()) {
				let data = this.getNewAngleAndSide(this.curAngle, this.curSide, this.getCurActPos());
				this.curLineRunTime = 0;
				this.curPos.x = this.curPos.x + this.frontPos.x;
				this.curPos.y = this.curPos.y + this.frontPos.y;
				this.frontPos = { x: 0, y: 0 };
				this.curSide = data.side;
				this.curLockFishId = -1;
				if (data.angle) {
					this.curAngle = data.angle;
				}
				this.setPosAngAngle(null, this.curAngle);
			}
			let battery: Battery = FishMgr.BatteryManager.getBatteryBySeatId(this.userInfo.seatId);
			if (battery) {
				if (battery.isLockFish() && (battery.getLockFishId() == this.curLockFishId && this.curLockFishId != -1)) {
					this.curAngle = battery.getLockAngle();
					this.setPosAngAngle(null, this.curAngle);
				}
			}
			this.curLineRunTime += dt;
			let ddis = this.moveSpeed * this.curLineRunTime;
			this.frontPos.x = ddis * Math.cos((this.curAngle / 180) * Math.PI);
			this.frontPos.y = ddis * Math.sin((this.curAngle / 180) * Math.PI);

			let tmpPos = { x: this.curPos.x + this.frontPos.x, y: this.curPos.y + this.frontPos.y };
			this.setPosAngAngle(tmpPos, null);
		}
	}

	export enum Side {
		None = 0,
		Left = 1,
		Right = 2,
		Top = 3,
		Bottom = 4
	}
}