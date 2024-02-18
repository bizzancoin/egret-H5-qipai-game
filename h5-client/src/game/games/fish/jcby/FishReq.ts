module jcby {
	export class FishReq {
		private static c2s_jc_ReqEnterGameHall_msg = 5401;        // [进入大厅]
		//private static c2s_jc_ReqEnterRoom_msg = 5402;            // [进入房间]
		//private static c2s_jc_ReqFastEnterTable_msg = 5403;       // [快速进入桌子]
		private static c2s_jc_ReqFire_msg = 5406;                 // [开炮]
		private static c2s_jc_ReqSwitchBattery_msg = 5407;        // [切换大炮]
		private static c2s_jc_ReqHitFish_msg = 5408;              // [击中鱼]
		private static c2s_jc_ReqExitRoom_msg = 5409;             // [离开房间]
		private static c2s_jc_ReqExitTable_msg = 5410;            // [离开桌子]
		private static c2s_jc_ReqScence_msg = 5411;               // [请求场景数据]
		private static c2s_jc_ReqLockFish_msg = 5412;             // [锁定鱼]
		private static c2s_jc_ReqCancelLockFish_msg = 5413;       // [取消锁定鱼]
		private static c2s_jc_ReqBackTable_msg = 5414;            // [断线重连获取房间数据]
		private static c2s_jc_ReqExchangeGold_msg = 5415;         // [筹码兑换金币]
		private static c2s_jc_ReqExchangeChips_msg = 5416;        // [金币兑换筹码]
		private static c2s_jc_ReqFastEnterTable_msg = 5417;       // [快速进入桌子]

		// 进入大厅
		public static send_ReqEnterGameHall(): void {
			let body = {
				id: FishReq.c2s_jc_ReqEnterGameHall_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				App.EasyLoading.hideLoading();
				if (msgid == body.id) {
					// 成功
					GameApp.PlayerInfo.myRoomTypeInfo = msg.roomTypes;
					GameApp.PlayerInfo.curGameID = 9;
					GameApp.HomeManager.resEnterGameHall('金蟾捕鱼');
				} else {
					PlayerManager.resCommonMsg(msg);
				}
			}, this);
		}

		// 进入房间
		public static send_ReqEnterRoom(roomid: number): void {
			/*let body = {
				id: FishReq.c2s_jc_ReqEnterRoom_msg,
				roomId: roomid
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					GameApp.HomeManager.resEnterRoomMsg(msg);
				} else {
					PlayerManager.resCommonMsg(msg);
					GameApp.HomeManager.resFailEnterRoom();
				}
			}, this);*/
		}

		// 快速进入桌子
		public static send_ReqFastEnterTable(roomid: number): void {
			let body = {
				id: FishReq.c2s_jc_ReqFastEnterTable_msg,
				roomId: roomid
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					if (msg.mems) {
						msg.mems.forEach(mem => {
							let info = {
								playerId: mem.playerId,
								seatId: mem.order + 1,
								isLocal: GameApp.PlayerInfo.isMySelf(mem.playerId),
								chips: mem.chips,
							};
							if (info.isLocal) {
								fish.FishMgr.seatId = info.seatId;
							}
							fish.FishMgr.fishModel.addUserInfo(info.seatId, info);
						});
						fish.FishMgr.start('jc');
					} else {
						App.SceneManager.runScene(SceneConsts.Login);
					}
				} else {
					PlayerManager.resCommonMsg(msg);
					//GameApp.HomeManager.resQuickEnterCurGame();
				}
			}, this);
		}

		// 开炮
		public static send_ReqFire(angle: number): void {
			let body = {
				id: FishReq.c2s_jc_ReqFire_msg,
				angle: angle
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					egret.log('开炮失败');
				}
			}, this);
		}

		// 切换炮台
		public static send_ReqSwitchBattery(type: number): void {
			let body = {
				id: FishReq.c2s_jc_ReqSwitchBattery_msg,
				type: type
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					egret.log('切换炮台失败');
				}
			}, this);
		}

		// 打中鱼
		public static send_ReqHitFish(playerId: number, bulletId: number, fishId: number): void {
			let body = {
				id: FishReq.c2s_jc_ReqHitFish_msg,
				playerId: playerId,
				bulletId: bulletId,
				fishId: fishId
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					egret.log('打中鱼失败');
				}
			}, this);
		}

		// 离开房间
		public static send_ReqExitRoom(): void {
			let body = {
				id: FishReq.c2s_jc_ReqExitRoom_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 跳到大厅(通知处理)
				} else {
					// 失败
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

		// 退出桌子
		public static send_ReqExitTable(): void {
			let body = {
				id: FishReq.c2s_jc_ReqExitTable_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
					GameApp.HomeManager.reqExitCurGameRoom();
					LoginMgr.start();
				} else {
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

		// 请求场景数据
		public static send_ReqScene(): void {
			let body = {
				id: FishReq.c2s_jc_ReqScence_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					fish.FishMgr.fishModel.isEnterScene = true;
					App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResEnterScene, msg);
				} else {
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

		// 锁定鱼
		public static send_ReqLockFish(fishId: number, angle: number): void {
			let body = {
				id: FishReq.c2s_jc_ReqLockFish_msg,
				fishId: fishId,
				angle: angle,
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					egret.log('锁定鱼失败', fishId, angle);
				}
			}, this);
		}

		// 取消锁定鱼
		public static send_ReqCancelLockFish(): void {
			let body = {
				id: FishReq.c2s_jc_ReqCancelLockFish_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					egret.log('取消锁定鱼失败');
				}
			}, this);
		}

		// 断线重连
		public static send_ReqBackTable(): void {
			let body = {
				id: FishReq.c2s_jc_ReqBackTable_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
					App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResBackTable, msg);
				} else {
					// 失败
					LoginMgr.start();
				}
			}, this);
		}

		// 金币兑换筹码
		public static send_ReqExchangeChips(gold: number): void {
			let body = {
				id: FishReq.c2s_jc_ReqExchangeChips_msg,
				gold: gold,
			};
			App.Socket.send(body, function(msgid, msg) {
				if (msgid == body.id) {
					// 兑换成功
				} else {
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

		// 筹码兑换金币
		public static send_ReqExchangeGolds(chips: number): void {
			let body = {
				id: FishReq.c2s_jc_ReqExchangeGold_msg,
				chips: chips,
			};
			App.Socket.send(body, function(msgid, msg) {
				if (msgid == body.id) {
					// 兑换成功
				} else {
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

		public static setRoomInfo(roomInfo): void {
			fish.FishMgr.setRoomInfo(roomInfo);
		}

		public static pauseGame(): void {
			if (App.ViewManager.isShow(ViewConst.FishMain)) {
				let vi = App.ViewManager.getView(ViewConst.FishMain) as fish.FishMainView;
				if(vi) {
					vi.pauseGame(true);
				}
			}
		}

		public static loadScene() {
			fish.FishMgr.fishModel.backRoom = true;
			if (App.ViewManager.isShow(ViewConst.FishMain)) {
				FishReq.send_ReqBackTable();
			} else {
				fish.FishMgr.start('jc');
			}
		}

		// 注册公共模块请求
		public static registerCommonReq(): void {
			GameApp.HomeManager.registerModuleReq(9, CommonReq.SetRoomInfo, FishReq.setRoomInfo, FishReq);
			GameApp.HomeManager.registerModuleReq(9, CommonReq.LoadGameScene, FishReq.loadScene, FishReq);
			GameApp.HomeManager.registerModuleReq(9, CommonReq.ReqEnterCurGame, FishReq.send_ReqEnterGameHall, FishReq);
			GameApp.HomeManager.registerModuleReq(9, CommonReq.ReqEnterCurGameRoom, FishReq.send_ReqEnterRoom, FishReq);
			GameApp.HomeManager.registerModuleReq(9, CommonReq.ReqExitCurGameRoom, FishReq.send_ReqExitRoom, FishReq);
			GameApp.HomeManager.registerModuleReq(9, CommonReq.ReqQuickEnterCurGame, FishReq.send_ReqFastEnterTable, FishReq);
			GameApp.HomeManager.registerModuleReq(9, CommonReq.ReqExitCurGameTable, FishReq.send_ReqExitTable, FishReq);
			GameApp.HomeManager.registerModuleReq(9, CommonReq.PauseGame, FishReq.pauseGame, FishReq);
		}

		// 移除公共模块请求
		public static removeCommonReq(): void {
			GameApp.HomeManager.removeModuleReq(9);
		}

	}
}