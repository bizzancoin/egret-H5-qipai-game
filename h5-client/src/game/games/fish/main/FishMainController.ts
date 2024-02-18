module fish {
	export class FishMainSceneController extends BaseController {

		private mainView: FishMainView;
		public constructor() {
			super();
			this.mainView = new FishMainView(this, LayerManager.UI_Main);
			App.ViewManager.register(ViewConst.FishMain, this.mainView);
			this._registerFunc();
		}

		private _registerFunc() {
			// 进入游戏大厅
			let self = this;
			this.registerFunc(FishResConst.ResBackTable, this.backTable, this);
			this.registerFunc(FishResConst.ResEnterScene, this.enterScene, this);
			this.registerFunc(FishResConst.ResOtherEnterTable, this.otherEnterTable, this);
			this.registerFunc(FishResConst.ResChangeChips, function (msg) {
				let info = {
					seatId: msg.order + 1,
					chips: msg.chips
				};
				self.mainView.updateChips(info);
				let uInfo = FishMgr.fishModel.getUserInfo(info.seatId);
				if (uInfo && uInfo.playerId != -1) {
					uInfo.chips = info.chips;
				}
			}, this);
			this.registerFunc(FishResConst.ResFire, function (msg) {
				let info = {
					seatId: msg.order + 1,
					bulletId: msg.bulletId,
					angle: msg.angle,
					playerId: msg.playerId,
				}
				self.mainView.fire(info);
			}, this);
			this.registerFunc(FishResConst.ResSwitchBattery, this.updateBattery, this);
			this.registerFunc(FishResConst.ResHitFish, function (msg) {
				let info = {
					seatId: msg.order + 1,
					dies: msg.dies
				}
				self.mainView.fishsDead(info);
			}, this);
			this.registerFunc(FishResConst.ResExitTable, this.exitTable, this);
			this.registerFunc(FishResConst.ResLockFish, function (msg) {
				let info = {
					seatId: msg.order + 1,
					fishId: msg.fishId
				}
				self.mainView.setLockFish(info);
			}, this);
			this.registerFunc(FishResConst.ResCancelLockFish, function (msg) {
				self.mainView.unLockFish(msg.order + 1);
			}, this);
			this.registerFunc(FishResConst.ResInsteadPlayersUpgrade, function (msg) {
				FishMgr.fishModel.instatdSendPlayerLst = msg.insteadPlayers;
			}, this);
			this.registerFunc(FishResConst.ResProductFish, function (msg) {
				self.mainView.addFish(msg.fishs);
			}, this);
			this.registerFunc(FishResConst.ResScence, function (msg) {
				self.mainView.switchScene(msg.scene);
			}, this);

		}

		// 进入场景
		private enterScene(msg) {
			msg.fishs.forEach(fish => {
				if (fish.angle > 0) {
					fish.angle = 360 - fish.angler;
				}
			});
			this.mainView.addFish(msg.fishs);
			this.mainView.enterScene(msg.scenceId);
			msg.batterys.forEach(battery => {
				this.updateBattery(battery);
			});
			FishMgr.fishModel.instatdSendPlayerLst = msg.insteadPlayers;
		}

		// 其他人进入房间
		private otherEnterTable(member, battery) {
			let info = {
				playerId: battery.playerId,
				seatId: battery.order + 1,
				isLocal: GameApp.PlayerInfo.isMySelf(battery.playerId),
				chips: member.chips,
			}
			FishMgr.fishModel.addUserInfo(info.seatId, info);
			this.mainView.setOtherPlayerUserInfo(info);
			this.updateBattery(battery);
		}

		// 退出桌子
		private exitTable(msg) {
			if (App.ViewManager.isShow(ViewConst.Home)) {
				return;
			} else {
				let info = {
					seatId: msg.order + 1,
					playerId: msg.playerId
				}
				if (FishMgr.fishModel.getUserInfo(info.seatId).isLocal) {
					// do nothing
				} else {
					FishMgr.fishModel.resetUserInfo(info.seatId);
					this.mainView.removeSeat(info);
				}
			}
		}

		// 更新炮台
		private updateBattery(msg: any): void {
			let info = {
				seatId: msg.order + 1,
				num: msg.num,
				score: msg.score,
				power: msg.power,
			}
			this.mainView.updateBattery(info);
		}

		// 断线重连
		private backTable(msg): void {
			if (msg.room && msg.mems) {
				let posCfg = FishMgr.Config.BatteryPos;
				for (let p in posCfg) {
					FishMgr.fishModel.addUserInfo(p, { playerId: -1, seatId: -1, isLocal: false, chips: 0 });
				}
				FishMgr.fishModel.removeAllUserInfo();
				msg.mems.forEach(mem => {
					let info = {
						playerId: mem.playerId,
						seatId: mem.order + 1,
						isLocal: GameApp.PlayerInfo.isMySelf(mem.playerId),
						chips: mem.chips,
					};
					if (info.isLocal) {
						FishMgr.seatId = info.seatId;
					}
					FishMgr.fishModel.addUserInfo(info.seatId, info);
				});

				FishMgr.fishModel.roomInfo = msg.room;
				FishMgr.fishModel.gameInfo = msg.gameInfo;
				
				this.mainView.rebackTable();
			} else {
				this.mainView.finish();
				LoginMgr.start();
			}
		}
	}
}