module fish {
	export class Battery extends eui.Component {

		private piAngle = 180;

		private gpParent: eui.Group;
		private bmpGold: eui.BitmapLabel;
		private bmpLevel: eui.BitmapLabel;
		private btnMinus: eui.Button;
		private btnAdd: eui.Button;
		private imgGold: eui.Image;
		private imgBarrel: eui.Image;
		private imgGoldLimit: eui.Image; // 金币不足
		private imgBattery: eui.Image;   // 炮台

		private userInfo: any;
		private side: number;
		private sendBulletSpeed = 1100;  // 发射子弹的速度
		private isAutoLockFire = false;  // 是否锁定鱼后自动开炮
		private sumIdelSendTime = 0.25;  // 自动发射时，时间间隔之和(0.25)
		private isAutoLock = false;      // 是否可以锁定鱼（只有为true时，点击鱼才可以锁定）

		private isAutoFire = false;      // 是否自动开炮

		private curBarrelIdx = 1;        // 炮管数索引
		private isPower = false;         // 是否能量炮
		private lvNum = 0;               // 当前子弹分数
		private config: any;		     // 子弹配置信息
		private posCfg: any;             // 位置配置信息
		private inited: boolean = false; // 是否初始化结束
		private bulletSendDis = 150;     // 子弹距离炮台的距离

		private barrelMc: egret.MovieClip;
		private curAngle: number = 0;
		private lockFishObj: LockFish;
		private clearLockStateCall: Function;
		private cusSkin: number;
		private bulletAngle: number;

		private autoExitTime = 30; // 30秒未打子弹，自动退出
		private autoExitTimeSum = 0;

		public constructor(skin: number) {
			super();
			if (skin == 1) {
				this.skinName = "resource/skins/fish/BatterySkin1.exml";
			} else {
				this.skinName = "resource/skins/fish/BatterySkin2.exml";

			}
			this.cusSkin = skin;
			this.barrelMc = new egret.MovieClip();
			this.barrelMc.rotation = 270;
			this.gpParent.addChild(this.barrelMc);
			this.gpParent.swapChildren(this.barrelMc, this.imgBarrel);
			this.barrelMc.touchEnabled = false;
			this.touchEnabled = false;
			this.gpParent.touchEnabled = false;
			this.registerEvent();
		}

		// 注册事件
		public registerEvent(): void {
			this.btnAdd.addEventListener(egret.TouchEvent.TOUCH_TAP, function () {
				// 加子弹
				FishReq.send_ReqSwitchBattery(1);
			}, this);
			this.btnMinus.addEventListener(egret.TouchEvent.TOUCH_TAP, function () {
				// 减子弹
				FishReq.send_ReqSwitchBattery(0);
			}, this);
		}

		public setBatteryInfo(info: any, isInit): void {
			this.config = FishMgr.Config.Battery[info.configId];
			this.sendBulletSpeed = this.config.send_v;
			this.userInfo = info.userInfo ? info.userInfo : {};
			this.side = info.side ? info.side : 4;
			this.imgGoldLimit.visible = false;
			this.showBtns(this.userInfo.isLocal); // 显示加减炮按钮
			this.createLockFish();
			this.setBatteryByConfig();
			this.setRotationAndPosBySide(this.side);
			this.setPosition(info.posCfg.pos);
			this.bmpGold.text = info.userInfo.chips;
			this.visible = false;
			this.showEnterAnim(isInit);
			this.visible = true;
		}

		private showBtns(visible: boolean) {
			this.btnAdd.visible = visible;
			this.btnMinus.visible = visible;
		}

		// 根据配置信息设置炮台皮肤
		private setBatteryByConfig(): void {
			this.bulletSendDis = this.config.offset_dis;
			// 炮管
			let barrel = this.config.bubr_info[this.curBarrelIdx - 1][2];
			if (!this.userInfo.isLocal) {
				barrel = this.config.bubr_info[this.curBarrelIdx - 1][3];
			}
			if (this.isPower) {
				barrel = this.config.bubr_info[this.curBarrelIdx - 1][4];
			}
			let barrelSrc = FishMgr.Config.Sprite[barrel];
			this.barrelMc.movieClipData = GameApp.getMovieDataByFileName(barrelSrc.file, barrelSrc.key);
			if (barrelSrc.width && barrelSrc.height) {
				this.barrelMc.width = barrelSrc.width;
				this.barrelMc.height = barrelSrc.height;
			} else {
				this.barrelMc.width = this.imgBarrel.width;
				this.barrelMc.height = this.imgBarrel.height;
			}
			this.barrelMc.anchorOffsetX = this.barrelMc.width * 0.15;
			this.barrelMc.anchorOffsetY = this.barrelMc.height * 0.5;
			this.barrelMc.x = this.imgBarrel.x;
			this.barrelMc.y = this.imgBarrel.y;
			this.imgBarrel.visible = false;
			// 设置分数纹理
			this.imgGold.texture = RES.getRes(this.config.frame_key);
		}

		// 播放初始动画
		private showEnterAnim(init: boolean): void {
			this.visible = true;
			if (this.userInfo.isLocal) {
				let seatIdx = FishMgr.Config.System['selfbat_enter_ani_id'];
				let src_seat = FishMgr.Config.Sprite[seatIdx];
				let mc: egret.MovieClip = GameApp.getClipByFileName(src_seat.file, src_seat.key);
				this.gpParent.addChild(mc);
				let setPos = null;
				if (this.cusSkin == 1) {
					setPos = { x: -200, y: -140 };
				} else {
					mc.skewY = 180;
					setPos = { x: 800, y: -140 };
				}
				GameApp.playMoveClip(this.gpParent, mc, 'act', setPos, 1, function () {
					this.inited = true;
					if (mc.parent) {
						mc.parent.removeChild(mc);
					}
				}, this);


				let effectIdx = FishMgr.Config.System['selfbat_hit_ani_id'];
				let src_eff = FishMgr.Config.Sprite[effectIdx];
				let mc1 = GameApp.getClipByFileName(src_eff.file, src_eff.key);
				this.gpParent.addChild(mc1);
				let effPos = null;
				if (this.cusSkin == 1) {
					effPos = { x: 70, y: -120 };
				} else {
					effPos = { x: 315, y: -120 };
				}
				GameApp.playMoveClip(this.gpParent, mc1, 'act', effPos, 4, function () {
					if (mc1.parent) {
						mc1.parent.removeChild(mc1);
					}
				}, this);
				this.alpha = 0;
				egret.Tween.get(this).to({ alpha: 1 }, 800);
			} else {
				if (init) {
					let seatIdx = FishMgr.Config.System['otherbat_enter_ani_id'];
					let src_seat = FishMgr.Config.Sprite[seatIdx];
					let mc: egret.MovieClip = GameApp.getClipByFileName(src_seat.file, src_seat.key);
					this.gpParent.addChild(mc);
					let pos = null;
					if (this.cusSkin == 1) {
						pos = { x: -200, y: -140 };
					} else {
						mc.skewY = 180;
						pos = { x: 800, y: -140 };
					}
					GameApp.playMoveClip(this.gpParent, mc, 'act', pos, 1, function () {
						if (mc.parent) {
							mc.parent.removeChild(mc);
						}
					}, this);
					this.alpha = 0;
					egret.Tween.get(this).to({ alpha: 1 }, 800);
				}
				this.inited = true;
			}
		}

		// 设置位置
		private setPosition(pos: any): void {
			this.x = pos.x;
			this.y = pos.y;
		}

		// 根据位置，设置炮台旋转角度
		private setRotationAndPosBySide(side: number): void {
			switch (side) {
				case 1:
				case 2:
					break;
				case 3:
					this.curAngle = 270;
					this.rotation = 180
					this.bulletAngle = 180;
					break;
				case 4:
					this.curAngle = 270;
					this.rotation = 0
					this.bulletAngle = 0;
					break
			}
		}

		public rotationBatteryByPos(targetPoint: egret.Point): void {
			let point: egret.Point = this.gpParent.localToGlobal(this.barrelMc.x, this.barrelMc.y);
			this.curAngle = this.getRotationAngle(targetPoint, point, this.side) + this.piAngle;
			this.curAngle = parseInt(this.curAngle.toFixed(0));
			this.barrelMc.rotation = this.curAngle;
			if(this.isAutoLock || this.isAutoFire) {
				if(this.isAutoLock) {
					this.lockFishByPos(targetPoint);
					if (!this.isLockFish()) {
						let sceneState = FishMgr.BackgroundManager.getSceneState();
						if (sceneState == 1 && this.isHaveEnoughChips()) {
							FishReq.send_ReqFire(this.curAngle);
						}
					} else {
						this.setAutoLockFire(true);
						if (this.clearLockStateCall) {
							this.clearLockStateCall();
						}
					}
				} else if(this.isAutoFire) {
					FishReq.send_ReqLockFish(0, this.curAngle);
					let sceneState = FishMgr.BackgroundManager.getSceneState();
					if (sceneState == 1 && this.isHaveEnoughChips()) {
						FishReq.send_ReqFire(this.curAngle);
					}
				}
			} else {
				let sceneState = FishMgr.BackgroundManager.getSceneState();
				if (sceneState == 1 && this.isHaveEnoughChips()) {
					FishReq.send_ReqFire(this.curAngle);
				}
			}
		}


		// 获取到为逆时针角度
		private getRotationAngle(pos1: egret.Point, pos2: egret.Point, side: number): number {
			let angle = 0;
			if (pos1.x == pos2.x) {
				if (side == 1 || side == 2) {
					if (pos1.y >= pos2.y) {
						angle = 90;
					} else if (pos1.y < pos2.y) {
						angle = 270;
					}
				} else if (side == 3) {
					angle = 270;
				} else if (side == 4) {
					angle = 90;
				}
			} else {
				let k = (pos1.y - pos2.y) / (pos1.x - pos2.x);
				angle = Math.atan(k) * this.piAngle / Math.PI;
				if (side == 1 && angle <= 0) {
					angle = 2 * this.piAngle + angle;
				} else if (side == 2) {
					angle = this.piAngle + angle;
				} else if (side == 3 && angle <= 0) {
					angle = this.piAngle + angle;
				} else if (side == 4 && angle <= 0) {
					angle = this.piAngle + angle;
				}
			}
			return angle;
		}

		// 获取炮管屏幕坐标位置
		public getBarrelPos(): egret.Point {
			return this.gpParent.localToGlobal(this.barrelMc.x, this.barrelMc.y);
		}

		// 更新炮信息 index 炮管数量, power(0 不是能量炮,1 是能量炮)
		public setBarrelIndex(index: number, score: number, isPower: boolean): void {
			this.barrelMc.gotoAndStop('act'); // 停止动画
			this.isPower = isPower;
			if (this.curBarrelIdx != index) {
				this.curBarrelIdx = index;
				this.setBatteryByConfig();
			}
			this.lvNum = score;
			this.bmpLevel.text = score.toString();
		}

		// 获取炮台中心点击坐标
		public getBatteryPos(): egret.Point {
			return this.gpParent.localToGlobal(this.imgBattery.x + this.imgBattery.width / 2,
				this.imgBattery.y + this.imgBattery.height / 2);
		}

		public setChips(chips: any): void {
			this.userInfo.chips = chips;
			this.bmpGold.text = chips;
			this.isHaveEnoughChips();
		}

		// 筹码不足
		private isHaveEnoughChips(): boolean {
			if (this.userInfo.isLocal && this.inited) {
				if (this.lvNum > this.userInfo.chips) {
					if (this.imgGoldLimit.visible) {
						return;
					}
					this.imgGoldLimit.scaleX = 0.1;
					this.imgGoldLimit.scaleY = 0.1;
					this.imgGoldLimit.visible = true;
					egret.Tween.get(this.imgGoldLimit).to({ scaleX: 1, scaleY: 1 }, 1.5);
					return false;
				} else {
					this.imgGoldLimit.visible = false;
				}
			}
			return true;
		}

		// 创建炮台
		private createLockFish(): void {
			if (!this.lockFishObj) {
				let pos = this.getBatteryPos();
				let info = {
					side: this.side,
					seatId: this.userInfo.seatId,
					isLocal: this.userInfo.isLocal,
					key: this.config.lock_line_key,
					bulletOffset: this.bulletSendDis,
					offsetPos: {
						x: pos.x,
						y: pos.y
					}
				}
				this.lockFishObj = new LockFish(info);
				let self = this;
				this.lockFishObj.setLockCall(function (fishId) {
					self.sendLockMsg(fishId)
				});
				FishMgr.BatteryManager.getLockParent().addChild(this.lockFishObj);
			}
		}

		// 锁定鱼
		private sendLockMsg(fishId: any): void {
			if (this.userInfo.isLocal && this.isAutoLock) {
				FishReq.send_ReqLockFish(fishId, -1);
			}
		}

		// 获取发送子弹的位置
		public getSendBulletPos(): any {
			let gl = this.gpParent.localToGlobal(this.barrelMc.x, this.barrelMc.y);
			let x = this.bulletSendDis * Math.cos((this.curAngle + this.bulletAngle) / this.piAngle * Math.PI);
			let y = this.bulletSendDis * Math.sin((this.curAngle + this.bulletAngle) / this.piAngle * Math.PI);
			return { x: gl.x + x, y: gl.y + y };
		}

		// 定时发送子弹
		private sendBullet(dt) {
			if (this.sumIdelSendTime >= this.sendBulletSpeed) {
				this.sumIdelSendTime = 0;
				if (this.isCanSendBullet()) {
					this.curAngle = parseInt(this.curAngle.toFixed(0));
					FishReq.send_ReqFire(this.curAngle);
				}
			} else {
				this.sumIdelSendTime += dt;
			}
		}

		// 发送子弹
		public runSendBullet(bulletId: number, angle: number): void {
			if (this.userInfo.isLocal) {
				let musicId = this.config.mus_eff_lst[0];
				if (this.isPower) {
					musicId = this.config.mus_eff_lst[1];
				}
				// 播放音乐
				FishMgr.MusicManager.play(musicId);
			}
			if (angle > 0 && angle < 180) {
				angle = angle + 180;
			}
			// 角度变化
			if (!this.userInfo.isLocal) {
				if (!this.isLockFish()) {
					if (FishMgr.seatId == 2 || FishMgr.seatId == 4) {
						if (this.userInfo.seatId == 1 || this.userInfo.seatId == 3) {
							this.curAngle = this.piAngle - angle;
						} else {
							this.curAngle = angle;
						}
					} else if (FishMgr.seatId == 1 || FishMgr.seatId == 3) {
						if (this.userInfo.seatId == 2 || this.userInfo.seatId == 4) {
							this.curAngle = this.piAngle - angle;
						} else {
							this.curAngle = angle;
						}
					}
					this.barrelMc.rotation = this.curAngle;
				}
			}

			let cfgId = this.config.bubr_info[this.curBarrelIdx - 1][0];
			if (this.isPower) {
				cfgId = this.config.bubr_info[this.curBarrelIdx - 1][1];
			}
			let info = {
				id: cfgId,
				pos: this.getSendBulletPos(),
				angle: this.curAngle + this.bulletAngle,
				side: this.side,
				userInfo: GameApp.clone(this.userInfo),
				lockfishId: -1,
			}
			// 设置子弹服务器id
			info.userInfo.serverId = bulletId;
			// 锁定鱼
			if (this.lockFishObj) {
				info.lockfishId = this.getLockFishId();
			}
			if (this.userInfo.isLocal) {
				info.angle = this.curAngle;
				this.autoExitTimeSum = 0;
			}
			this.setBatteryByConfig();
			// 发送子弹
			FishMgr.BulletManager.sendBullet(info);
			this.barrelMc.gotoAndPlay('act', 1);
		}

		private isCanSendBullet(): boolean {
			let canSend = false;
			let sceneState = FishMgr.BackgroundManager.getSceneState();
			if ((this.isAutoLockFire || this.isAutoFire) && this.userInfo.isLocal && sceneState == 1 && this.isHaveEnoughChips()) {
				canSend = true;
			}
			return canSend;
		}

		public isLongTimeNoSendBullet(): boolean {
			let isNoSend = false;
			if (this.userInfo.isLocal) {
				isNoSend = this.autoExitTimeSum > this.autoExitTime;
			}
			return isNoSend;
		}

		public lockFish(): void {
			if (this.lockFishObj && FishMgr.BackgroundManager.getSceneState() == 1) {
				this.lockFishObj.lock();
			}
		}

		public unLockFish(): void {
			if (this.lockFishObj) {
				this.lockFishObj.unlock();
			}
		}

		public setUnLockFish(): void {
			if (this.lockFishObj && !this.userInfo.isLocal) {
				this.lockFishObj.unlock();
			}
			this.isAutoFire = false;
			this.isAutoLock = false;
		}

		public setLockFish(fishId: number): void {
			if (this.lockFishObj) {
				this.lockFishObj.setLockFish(fishId);
			}
		}

		public forceToLockFish(fishId: number) {
			if (this.lockFishObj) {
				this.lockFishObj.forceToLockFish(fishId);
			}
		}

		public resetLockFishLst(lst: Fish[]): void {
			if (this.lockFishObj) {
				this.lockFishObj.resetLockLst(lst);
			}
		}

		public isLockFish(): boolean {
			if (this.lockFishObj) {
				if (this.lockFishObj.getLockState() && this.lockFishObj.getCurrentLockFishId() != -1) {
					return true;
				}
			}
			return false;
		}

		public getLockFishId(): number {
			if (this.lockFishObj) {
				return this.lockFishObj.getCurrentLockFishId();
			}
			return -1;
		}

		public getLockAngle(): number {
			if (this.lockFishObj) {
				return this.lockFishObj.getCurrentLockAngle();
			}
			return 0;
		}

		public getSide(): number {
			return this.side;
		}

		public isMyBattery(): boolean {
			return this.userInfo.isLocal;
		}

		public setUserInfo(userInfo: any): void {
			this.userInfo = userInfo;
		}

		public getChips(): number {
			return this.userInfo.chips;
		}

		private setAutoLockFire(isAutoLockFire: boolean): void {
			if (this.userInfo.isLocal) {
				this.isAutoLockFire = isAutoLockFire;
			}
		}

		public setAutoLockFish(isAutoLock: boolean) : void {
			this.isAutoLock = isAutoLock;
			if (!this.isAutoLock) {
				this.lockFishObj.unlock();
			} else {
				this.isAutoFire = false;
			}
		}

		public setAutoFire(isAutoFire: boolean): void {
			this.isAutoFire = isAutoFire;
			if(this.isAutoFire) {
				this.lockFishObj.unlock();
				this.isAutoLockFire = false;
				this.isAutoLock = false;
				if(this.userInfo.isLocal) {
					this.curAngle = parseInt(this.curAngle.toFixed(0));
					FishReq.send_ReqLockFish(0, this.curAngle);
				}
			}
		}

		public setAutoAngle(angle: number): void {
			this.curAngle = angle;
			this.barrelMc.rotation = this.curAngle;
		}

		private lockFishByPos(pos: any): void {
			// 如果手指点中鱼
			if (!this.isAutoLock) {
				return;
			}
			let fishObj = FishMgr.FishManager.clickFish(pos);
			if (fishObj) {
				this.lockFishObj.forceToLockFish(fishObj.getObjId());
			}
		}

		public getSeatId(): number {
			return this.userInfo.seatId;
		}

		public getPlayerId() {
			return this.userInfo.playerId ? this.userInfo.playerId : 0;
		}

		public setClearLockStateCall(call: Function): void {
			this.clearLockStateCall = call;
		}

		public onDestroy(): void {
			if (this.parent) {
				this.parent.removeChild(this);
			}
			if (this.lockFishObj) {
				this.lockFishObj.onDestroy();
			}
		}


		public update(dt): void {
			if (!this.inited) {
				return;
			}
			if (this.lockFishObj) {
				this.lockFishObj.update(dt);
			}
			// 锁定鱼
			if (this.isAutoLock && this.isLockFish()) {
				this.setAutoLockFire(true);
				let angle = this.lockFishObj.getCurrentLockAngle();
				this.curAngle = angle;
				let rotateAngle = 0;
				if (this.side == 1) {
					rotateAngle = 2 * this.piAngle - angle - this.piAngle / 2
				} else if (this.side == 2) {
					rotateAngle = 2 * this.piAngle - angle - this.piAngle * 3 / 2
				} else if (this.side == 3) {
					rotateAngle = this.piAngle + angle;
					this.curAngle = angle - this.piAngle;
				} else if (this.side == 4) {
					rotateAngle = angle;
				}
				this.barrelMc.rotation = rotateAngle;
			} else {
				this.setAutoLockFire(false);
			}
			
			this.sendBullet(dt);

			if(this.userInfo.isLocal) {
				if(this.autoExitTimeSum < this.autoExitTime) {
					this.autoExitTimeSum += dt;
				}
			}
		}

		public getCusSkin(): number {
			return this.cusSkin;
		}

	}
}