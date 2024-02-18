module fish {
	export class FishMainView extends BaseEuiView {

		private timeOnEnterFrame: number;

		private tgLock: eui.ToggleButton;
		private tgAuto: eui.ToggleButton;

		private groupBackground: eui.Group;  // 背景层
		private groupFish: eui.Group;	     // 鱼层
		private groupWater: eui.Group;		 // 水层
		private groupBattery: eui.Group;	 // 炮台层
		private groupLock: eui.Group;		 // 锁定层
		private groupBullet: eui.Group;		 // 子弹层

		// 按钮层
		private btnLeft: eui.Button;
		private btnRight: eui.Button;
		private btnHelp: eui.Button;
		private btnSet: eui.Button;
		private btnExit: eui.Button;
		private btnClose: eui.Button;
		private btnExchange: eui.Button;
		private groupRight: eui.Group;

		private exchangeView: game.ExchangeView;

		private groupHelp: eui.Group; // 帮助层

		private groupLongTimeNoSend:eui.Group;
		private lbTimeout: eui.Label;

		private autoExitTime = 30; // 倒计时30秒退出
		private autoExitTimeSum = 0;
		private autoExit:boolean = false;
		private isTimeout: boolean = false; // 超时退出
		private setView: game.SetView;

		private isRightPlaying: boolean = false;

		public constructor($controller: BaseController, $parent: egret.DisplayObjectContainer) {
			super($controller, $parent);
			this.skinName = 'resource/skins/fish/FishMainSkin.exml';
		}

		public initUI(): void {
			super.initUI();
			this.registerEvent();
			this.timeOnEnterFrame = egret.getTimer();
		}

		public initData(): void {
			super.initData();
			this.groupLongTimeNoSend.visible = false;
			FishMgr.BackgroundManager.init(this.groupBackground, this.groupWater);
			FishMgr.FishLabelManager.init(this.groupBullet);
			FishMgr.FishNetManager.init(this.groupBullet);
			FishMgr.MusicManager.init();
			FishMgr.FishGoldManager.init(this);
			FishMgr.FishObjectPool.init();
			FishMgr.BulletManager.init(this.groupBullet);
			FishMgr.BatteryManager.init(this.groupBattery, this.groupLock);
			FishMgr.FishManager.init(this.groupFish);
			let self = this;
			FishMgr.SkillManager.init(function () {
				// App.ShockUtils.shock(App.ShockUtils.SPRITE, self, 20);
				let tw = egret.Tween.get(self);
				for (let i = 0; i < 40; i++) {
					if (i % 2 == 0) {
						tw.to({ x: self.x + 5, y: self.y + 5 }, 50, egret.Ease.bounceOut)
					} else {
						tw.to({ x: self.x - 5, y: self.y - 5 }, 50, egret.Ease.bounceIn);
					}
					if (i == 39) {
						tw.to({ x: 0, y: 0 }, 50, egret.Ease.bounceIn);
					}
				}
			});
			FishMgr.CollisionManager.registerCallback(this.onShotCallback, this);

			this.preCreate();
			// 注册update
			egret.startTick(this.update, this);

			if (FishMgr.fishModel.backRoom) {
				FishReq.send_ReqBackTable();
			} else {
				FishReq.send_ReqScene();
			}

			this.visible = false;
		}

		// 预生成对象
		private preCreate(): void {
			FishMgr.FishObjectPool.registerObjectCreater(ObjectClassType.TypeBullet, FishMgr.BulletManager);
			let bulletCfg = FishMgr.Config.Bullet;
			for (let p in bulletCfg) {
				FishMgr.FishObjectPool.newSomeObject(ObjectClassType.TypeBullet, p, 50);
			}
			FishMgr.FishObjectPool.registerObjectCreater(ObjectClassType.TypeGold, FishMgr.FishGoldManager);
			let goldCfg = FishMgr.Config.FishGold;
			for (let p in goldCfg) {
				FishMgr.FishObjectPool.newSomeObject(ObjectClassType.TypeGold, p, 20);
			}
			FishMgr.FishObjectPool.registerObjectCreater(ObjectClassType.TypeNet, FishMgr.FishNetManager);
			let netCfg = FishMgr.Config.Net;
			for (let p in netCfg) {
				FishMgr.FishObjectPool.newSomeObject(ObjectClassType.TypeNet, p, 20);
			}
			FishMgr.FishObjectPool.registerObjectCreater(ObjectClassType.TypeLabel, FishMgr.FishLabelManager);
			let labelCfg = FishMgr.Config.LabelFnt;
			for (let p in labelCfg) {
				FishMgr.FishObjectPool.newSomeObject(ObjectClassType.TypeLabel, p, 20);
			}
			FishMgr.FishObjectPool.registerObjectCreater(ObjectClassType.TypeFish, FishMgr.FishManager);
			let fishCfg = FishMgr.Config.Fish;
			for (let p in fishCfg) {
				FishMgr.FishObjectPool.newSomeObject(ObjectClassType.TypeFish, p, 20);
			}
		}

		// 注册按钮事件
		private registerEvent(): void {
			this.exchangeView.hide();
			this.exchangeView.registerEvent((exchangeType:game.ExchangeType, chips:number)=>{
				switch(exchangeType) {
					case game.ExchangeType.CashIn: // 下分
						FishReq.send_ReqExchangeChip(game.ExchangeType.CashIn, chips);
						break;
					case game.ExchangeType.CashOut: // 上分
						FishReq.send_ReqExchangeChip(game.ExchangeType.CashOut, chips);
						break;
				}
			}, this);
			this.setView.show(false);
			this.setView.registerEvent(()=>{
				FishMgr.BackgroundManager.playBkMusic();
			}, this);
			this.btnLeft.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnRight.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnHelp.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnSet.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnExit.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnClose.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnExchange.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.groupBattery.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.tgLock.addEventListener(eui.UIEvent.CHANGE, (evt: eui.UIEvent) => {
				let battery = FishMgr.BatteryManager.getMyBattery();
				if (battery) {
					battery.setAutoLockFish(evt.target.selected);
				}
				if(evt.target.selected) {
					this.tgAuto.selected = false;
				}
			}, this);

			this.tgAuto.addEventListener(eui.UIEvent.CHANGE, (evt: eui.UIEvent) =>{
				let battery = FishMgr.BatteryManager.getMyBattery();
				if(battery) {
					battery.setAutoFire(evt.target.selected);
					if(evt.target.selected == false) {
						FishReq.send_ReqCancelLockFish();
					}
				}
				if(evt.target.selected) {
					this.tgLock.selected = false;
				}
			}, this);
		}

		private onClickHandler(evt: egret.TouchEvent): void {
			switch (evt.target) {
				case this.btnLeft:
					if(this.isRightPlaying) return;
					this.isRightPlaying = true;
					egret.Tween.get(this.groupRight).to({ right: 0 }, 500).call(function () {
						this.btnLeft.visible = false;
						this.btnRight.visible = true;
						this.isRightPlaying = false;
					}, this)
					break;
				case this.btnRight:
					this.hideOpBtn();
					break;
				case this.btnHelp:
					this.groupHelp.visible = true;
					this.hideOpBtn();
					break;
				case this.btnSet:
					this.hideOpBtn();
					this.setView.show(true);
					break;
				case this.btnExit:
					FishReq.send_ReqExitTable();
					break;
				case this.btnExchange:
					let battery = FishMgr.BatteryManager.getMyBattery();
					if (battery) {
						this.exchangeView.show(GameApp.PlayerInfo.safeGold, battery.getChips(), FishMgr.fishModel.roomInfo);
					} else {
						this.exchangeView.show(GameApp.PlayerInfo.safeGold, 0, FishMgr.fishModel.roomInfo);
					}
					this.hideOpBtn();
					break;
				case this.btnClose:
					this.groupHelp.visible = false;
					break;
				case this.groupBattery:
					FishMgr.BatteryManager.rotateByPos(this.groupBattery.localToGlobal(evt.localX, evt.localY));
					break;
			}
		}

		private hideOpBtn(): void {
			if(this.isRightPlaying) return;
			this.isRightPlaying = true;
			egret.Tween.get(this.groupRight).to({ right: -178 }, 500).call(function () {
				this.btnLeft.visible = true;
				this.btnRight.visible = false;
				this.isRightPlaying = false;
			}, this);
		}

		private onShotCallback(lst: any) {
			for (let i = 0; i < lst.length; i++) {
				let e = lst[i];
				if (e.bullet.isMyBullet()) {
					FishReq.send_ReqHitFish(e.bullet.getBulletPlayerId(), e.bullet.getBulletServerId(), e.fish.getObjId())
				}

				if (FishMgr.fishModel.instatdSendPlayerLst) {
					for (let j = 0; j < FishMgr.fishModel.instatdSendPlayerLst.length; j++) {
						let playerId = FishMgr.fishModel.instatdSendPlayerLst[j];
						if (e.bullet.getBulletPlayerId().equals(playerId)) {
							FishReq.send_ReqHitFish(playerId, e.bullet.getBulletServerId(), e.fish.getObjId());
							break;
						}
					}
				}

				FishMgr.BulletManager.removeBulletObject(e.bullet.getObjId());
			}
		}

		private isAutoExitScene(): void {
			let battery = FishMgr.BatteryManager.getMyBattery();
			if (battery) {
				if(battery.isLongTimeNoSendBullet()) {
					if(!this.autoExit) {
						this.groupLongTimeNoSend.visible = true;
						this.autoExitTimeSum = 0;
						this.showLongTimeNoSendTip(30, this.autoExitTime);
					}
					this.autoExit = true;
				} else {
					this.autoExit = false;
					this.groupLongTimeNoSend.visible = false;
				}
			}
		}

		private autoExitScene(dt): void {
			this.isAutoExitScene();
			if(this.autoExit) {
				if (Math.floor(this.autoExitTimeSum) >= this.autoExitTime) {
					if(!this.isTimeout) {
						FishReq.send_ReqExitTable();
						this.isTimeout = true;
					}
				} else {
					this.autoExitTimeSum += dt;
					let countDown = this.autoExitTime - Math.floor(this.autoExitTimeSum);
					if (countDown >= 0) {
						this.showLongTimeNoSendTip(30, countDown);
					}
				}
			}
		}

		private showLongTimeNoSendTip(limit:number, timeout: number) {
			// 由于您三十秒秒未发射子弹，系统将在60离开游戏！
			if(timeout >= 0) {
				this.lbTimeout.text = timeout.toString();
			}
			/*this.lbTimeout.textFlow = <Array<egret.ITextElement>>[
				{text: '由于您' + limit + '秒未发射子弹，系统将在  ', style: {textColor:0x91dcf8, size: 45}},
				{text: timeout + '', style: {textColor:0xFFFFFF, size: 70}},
				{text: '  秒后离开游戏！', style: {textColor:0x91dcf8, size: 45}},
			];*/
		}

		private update(timeFrame): boolean {
			let dt = (timeFrame - this.timeOnEnterFrame) / 1000;
			this.timeOnEnterFrame = timeFrame;
			FishMgr.FishManager.update(dt);
			FishMgr.BulletManager.update(dt);
			FishMgr.BatteryManager.update(dt);
			FishMgr.CollisionManager.update(dt);

			this.autoExitScene(dt);
			return true;
		}

		// 逻辑处理

		// 进入场景
		public enterScene(sceneId: number): void {
			this.visible = true;
			let init = true;
			if (fish.FishMgr.fishModel.backRoom) {
				init = false;
				FishMgr.BackgroundManager.initState(0);
				fish.FishMgr.fishModel.backRoom = false;
			}
			FishMgr.BackgroundManager.switchBaground(sceneId);
			// 创建大炮
			for (let p in fish.FishMgr.fishModel.userInfoLst) {
				let info = fish.FishMgr.fishModel.userInfoLst[p];
				if (info.playerId != -1 && info.seatId != -1) {
					FishMgr.BatteryManager.createBattery({ userInfo: info }, init);
				}
			}

			let self = this;
			FishMgr.BatteryManager.setClearLockStateCall(function () {
				// self.tgLock.selected = false;
			});

			let battery = FishMgr.BatteryManager.getMyBattery();
			if(battery) {
				if(battery.getChips() <= 0) {
					this.exchangeView.show(GameApp.PlayerInfo.safeGold, battery.getChips(), FishMgr.fishModel.roomInfo);
				}
			}

			let gameInfo = FishMgr.fishModel.gameInfo;
			if(gameInfo && gameInfo.lockInfos) {
				for(let i = 0; i < gameInfo.lockInfos.length; i++) {
					let tmp = gameInfo.lockInfos[i];
					let battery = FishMgr.BatteryManager.getBatteryByPId(tmp.playerId);
					if(battery) {
						if(tmp.lockFishId > 0) {
							this.tgAuto.selected = false;
							this.tgLock.selected = true;
							battery.setAutoLockFish(true);
							if(GameApp.PlayerInfo.isMySelf(tmp.playerId)) {
								battery.forceToLockFish(tmp.lockFishId);
							} else {
								battery.setLockFish(tmp.lockFishId);
							}
						} else if(tmp.lockAngle >= 0) {
							this.tgAuto.selected = true;
							this.tgLock.selected = false;
							battery.setAutoAngle(tmp.lockAngle);
							battery.setAutoFire(true);
						}
					}
				}
			}
		}

		// 切换场景
		public switchScene(sceneId: number): void {
			FishMgr.BackgroundManager.switchBaground(sceneId);
		}

		// 其他玩家进入桌子
		public setOtherPlayerUserInfo(info: any) {
			if (info) {
				FishMgr.BatteryManager.createBattery({ userInfo: info }, false);
			}
		}

		// 更新炮台 {seatId, num, score, power}
		public updateBattery(info: any): void {
			let battery = FishMgr.BatteryManager.getBatteryBySeatId(info.seatId);
			if (battery) {
				battery.setBarrelIndex(info.num, info.score, info.power > 0);
			}
		}

		// {seatId, chips}
		public updateChips(info: any): void {
			let battery = FishMgr.BatteryManager.getBatteryBySeatId(info.seatId);
			if (battery) {
				battery.setChips(info.chips);
			}
		}

		//{seatId,bulletId,angle,playerId}
		public fire(info: any): void {
			let battery = FishMgr.BatteryManager.getBatteryBySeatId(info.seatId);
			if (battery) {
				battery.runSendBullet(info.bulletId, info.angle);
			}
		}

		// 产生鱼{fishs{id, type, xcoord, ycoord, road, time, angle}}
		public addFish(fishs: any): void {
			fishs.forEach(v => {
				let info = {
					objId: v.id,
					fishId: v.type,
					pos: { x: v.xcoord, y: v.ycoord },
					angle: v.angle,
					cveId: v.road,
					runTime: v.time / 1000,
					userInfo: {}
				};
				FishMgr.FishManager.addFish(info);
			});
		}

		// 鱼死亡  {seatId, dies{fishId, score}}
		public fishsDead(info: any): void {
			let seatId = info.seatId;
			let dies = info.dies;
			if (dies.length <= 0) {
				return;
			}
			let fishId = -1;
			for (let i = 0; i < dies.length; i++) {
				if (i == 0) {
					fishId = dies[i].fishId;
				}
				if (i > 0) {
					let fishObj = FishMgr.FishManager.getFishObjById(dies[i].fishId);
					let firstObj = FishMgr.FishManager.getFishObjById(fishId);
					if (firstObj && fishObj) {
						firstObj.addLineRelObj(fishObj);
					}
					this.fishDeath(seatId, dies[i].fishId, dies[i].score);
				}
			}
			this.fishDeath(seatId, dies[0].fishId, dies[0].score);
		}

		private fishDeath(seatId: number, fishId: number, score: number): void {
			let battery = FishMgr.BatteryManager.getBatteryBySeatId(seatId);
			let fish = FishMgr.FishManager.getFishObjById(fishId);
			if (battery && fish) {
				fish.setFishScore(score);
				fish.setGoldMoveEndPos(battery.getBatteryPos());
				fish.setShotSeatId(seatId);
				FishMgr.FishManager.removeFish(fishId);
			}
		}

		// 锁定鱼 {seatId, fishId}
		public setLockFish(info: any): void {
			let battery = FishMgr.BatteryManager.getBatteryBySeatId(info.seatId);
			if (battery) {
				if(info.fishId > 0) {
					battery.setLockFish(info.fishId);
				} else if(info.angle >= 0) {
					battery.setAutoFire(true);
				}
			}
		}

		public unLockFish(seatId): void {
			let battery = FishMgr.BatteryManager.getBatteryBySeatId(seatId);
			if (battery) {
				battery.setUnLockFish();
			}
		}

		// 移除座位{seatId,playerId}
		public removeSeat(info: any): void {
			FishMgr.BatteryManager.removeBatteryBySeatId(info.seatId);
			FishMgr.BulletManager.removeBulletObjectBySetId(info.seatId);
		}

		// 断线重连
		public rebackTable(): void {
			this.visible = true;
			this.isRightPlaying = false;
			this.tgAuto.selected = false;
			this.tgLock.selected = false;
			FishMgr.FishManager.clearFishAll();
			FishMgr.FishLabelManager.clear();
			FishMgr.FishNetManager.clear();
			FishMgr.FishGoldManager.clear();
			
			for (let p in fish.FishMgr.fishModel.userInfoLst) {
				let v = fish.FishMgr.fishModel.userInfoLst[p];
				let battery = FishMgr.BatteryManager.getBatteryBySeatId(parseInt(p));
				if (battery && v.seatId == -1) {
					this.removeSeat(v);
				}
			}

			FishReq.send_ReqScene();
		}

		public pauseGame(stopMusic: boolean): void {
			if (stopMusic) {
				App.SoundManager.stopBg();
				App.SoundManager.stopEffect();
			}
			FishMgr.FishLabelManager.clear();
			FishMgr.FishNetManager.clear();
			FishMgr.FishGoldManager.clear();
		}

		public finish() {
			egret.stopTick(this.update, this);
			egret.Tween.removeAllTweens();
			FishMgr.BackgroundManager.destroy();
			FishMgr.FishLabelManager.destroy();
			FishMgr.FishNetManager.destroy();
			FishMgr.MusicManager.destroy();
			FishMgr.FishGoldManager.destroy();
			FishMgr.FishObjectPool.destroy();
			FishMgr.BulletManager.destroy();
			FishMgr.BatteryManager.destroy();
			FishMgr.FishManager.destroy();
			FishMgr.CollisionManager.destroy();
			FishMgr.SkillManager.destroy();
		}
	}
}