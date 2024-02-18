module brnn {
	export class BrnnReq {
		private static c2s_brnn_ReqEnterGameHall_msg = 6001;        // [进入大厅]
		//private static c2s_brnn_ReqEnterRoom_msg = 6002;            // [进入房间]
		//private static c2s_brnn_ReqFastEnterTable_msg = 6003;       // [快速进入桌子]
		private static c2s_brnn_ReqExitTable_msg = 6004;            // [离开桌子]
		private static c2s_brnn_ReqExitRoom_msg = 6005;             // [离开房间]
		private static c2s_brnn_ApplyZuoZ_msg = 6006;            	// [申请坐庄]
		private static c2s_brnn_CancelZuoZ_msg = 6007;              // [取消申请做庄]
		private static c2s_brnn_ApplyCancelZuoZ_msg = 6008;         // [申请下庄]
		private static c2s_brnn_Bet_msg = 6009;        				// [请求下注]
		private static c2s_brnn_ClearingBet_msg = 6010;        		// [清除下注]
		private static c2s_brnn_ReqBackTable_msg = 6011;            // [断线重连获取房间数据]
		private static c2s_brnn_ReqExchangeGold_msg = 6012;         // [筹码兑换金币]
		private static c2s_brnn_ReqExchangeChips_msg = 6013;        // [金币兑换筹码]
		private static c2s_brnn_ReqFastEnterTable_msg = 6014;       // [快速进入桌子]


		// 请求房间数据
		public static send_ReqEnterGameHall(): void {
			let body = {
				id: BrnnReq.c2s_brnn_ReqEnterGameHall_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				App.EasyLoading.hideLoading();
				if (msgid == body.id) {
					// 成功
					GameApp.PlayerInfo.myRoomTypeInfo = msg.roomTypes;
					GameApp.PlayerInfo.curGameID = 7;
					GameApp.HomeManager.resEnterGameHall("百人牛牛");
				} else {
					PlayerManager.resCommonMsg(msg);
				}
			}, this);
		}

		// 进入房间
		public static send_ReqEnterRoom(roomid): void {
			/*let body = {
				id: BrnnReq.c2s_brnn_ReqEnterRoom_msg,
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
				id: BrnnReq.c2s_brnn_ReqFastEnterTable_msg,
				roomId: roomid
			};
			App.Socket.send(body, function (msgid, msg) {
				App.EasyLoading.hideLoading();
				if (msgid == body.id) {
					// 成功
				} else {
					PlayerManager.resCommonMsg(msg);
					//GameApp.HomeManager.resQuickEnterCurGame();
				}
			}, this);
		}

		// 离开房间
		public static send_ReqExitRoom(): void {
			let body = {
				id: BrnnReq.c2s_brnn_ReqExitRoom_msg
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
				id: BrnnReq.c2s_brnn_ReqExitTable_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
					GameApp.HomeManager.reqExitCurGameRoom();
				} else {
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

		// 申请坐庄
		public static send_ReqApplyZuoZ(): void {
			let body = {
				id: BrnnReq.c2s_brnn_ApplyZuoZ_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					PlayerManager.resCommonMsg(msg);
				}
			}, this);
		}

		// 取消申请坐庄
		public static send_ReqCancelZuoZ(): void {
			let body = {
				id: BrnnReq.c2s_brnn_CancelZuoZ_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					//
				}
			}, this);
		}

		// 申请下庄
		public static send_ReqApplyCancelZuoZ(): void {
			let body = {
				id: BrnnReq.c2s_brnn_ApplyCancelZuoZ_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
				} else {
					//
				}
			}, this);
		}

		// 请求下注
		public static send_ReqBet(area, chip): void {
			let body = {
				id: BrnnReq.c2s_brnn_Bet_msg,
				area: area,
				chips: chip
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResBet, msg);
				} else {
					PlayerManager.resCommonMsg(msg);
				}
			}, this);
		}

		// 清除下注
		public static send_ReqClearBet(): void {
			let body = {
				id: BrnnReq.c2s_brnn_ClearingBet_msg,
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResClearBet, msg);
				} else {
					//
				}
			}, this);
		}

		// 金币兑换筹码
		public static send_ReqExchangeChips(gold: number): void {
			let body = {
				id: BrnnReq.c2s_brnn_ReqExchangeChips_msg,
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
				id: BrnnReq.c2s_brnn_ReqExchangeGold_msg,
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

		// 获取游戏信息
		public static send_ReqBackTable(): void {
			let body = {
				id: BrnnReq.c2s_brnn_ReqBackTable_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResBackTable, msg);
				} else {
					//
				}
			}, this);
		}

		public static setRoomInfo(roomInfo): void {
			BrnnMgr.setRoomInfo(roomInfo);
		}

		public static loadScene(): void {
			if (App.ViewManager.isShow(ViewConst.BrnnMain)) {
				BrnnReq.send_ReqBackTable();
			} else {
				BrnnMgr.start();
			}
		}

		public static pauseGame(): void {
			if (App.ViewManager.isShow(ViewConst.BrnnMain)) {
				let vi:MainView = App.ViewManager.getView(ViewConst.BrnnMain) as MainView;
				if(vi) {
					vi.pauseGame(true);
				}
			}
		}

		public static registerCommonReq(): void {
			GameApp.HomeManager.registerModuleReq(7, CommonReq.SetRoomInfo, BrnnReq.setRoomInfo, BrnnReq);
			GameApp.HomeManager.registerModuleReq(7, CommonReq.LoadGameScene, BrnnReq.loadScene, BrnnReq);
			GameApp.HomeManager.registerModuleReq(7, CommonReq.ReqEnterCurGame, BrnnReq.send_ReqEnterGameHall, BrnnReq);
			GameApp.HomeManager.registerModuleReq(7, CommonReq.ReqEnterCurGameRoom, BrnnReq.send_ReqEnterRoom, BrnnReq);
			GameApp.HomeManager.registerModuleReq(7, CommonReq.ReqExitCurGameRoom, BrnnReq.send_ReqExitRoom, BrnnReq);
			GameApp.HomeManager.registerModuleReq(7, CommonReq.ReqQuickEnterCurGame, BrnnReq.send_ReqFastEnterTable, BrnnReq);
			GameApp.HomeManager.registerModuleReq(7, CommonReq.ReqExitCurGameTable, BrnnReq.send_ReqExitTable, BrnnReq);
			GameApp.HomeManager.registerModuleReq(7, CommonReq.PauseGame, BrnnReq.pauseGame, BrnnReq);
		}

		public static removeCommonReq(): void {
			GameApp.HomeManager.removeModuleReq(7);
		}


	}
}