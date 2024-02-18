module baccarat {
	export class BaccaratReq {
		private static c2s_bjl_ReqEnterGameHall_msg = 5901;        // [进入大厅]
		//private static c2s_bjl_ReqEnterRoom_msg = 5902;            // [进入房间]
		//private static c2s_bjl_ReqFastEnterTable_msg = 5904;       // [快速进入桌子]
		private static c2s_bjl_ReqExitRoom_msg = 5905;             // [离开房间]
		private static c2s_bjl_ReqExitTable_msg = 5906;            // [离开桌子]

		private static c2s_bjl_ReqBet_msg = 5907;       // [下注]
		private static c2s_bjl_ReqClearBet_msg = 5908;             // [清除下注]
		private static c2s_bjl_ReqGameInfo_msg = 5999;            // [当前游戏信息]

		private static c2s_bjl_ReqExchangeGold_msg = 5909;         // [筹码兑换金币]
		private static c2s_bjl_ReqExchangeChips_msg = 5910;        // [金币兑换筹码]
		private static c2s_bjl_ReqFastEnterTable_msg = 5911;       // [快速进入桌子]

		// 进入大厅
		public static send_ReqEnterGameHall(): void {
			let body = {
				"id": BaccaratReq.c2s_bjl_ReqEnterGameHall_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				App.EasyLoading.hideLoading();
				if (msgid == body.id) {
					// 成功
					GameApp.PlayerInfo.myRoomTypeInfo = msg.roomTypes;
					egret.log('rooms', msg.roomTypes)
					GameApp.PlayerInfo.curGameID = 18;

					App.ViewManager.open(ViewConst.Room, "百家乐");
				} else {
					// 失败
					PlayerManager.resCommonMsg(msg);
				}
			}, this);
		}
		
		// 进入房间
		public static send_ReqEnterRoom(roomid: number): void {
			/*let body = {
				"id": BaccaratReq.c2s_bjl_ReqEnterRoom_msg,
				"roomId": roomid
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
					BaccaratReq.send_ReqFastEnterTable(roomid)
				} else {
					// 失败
					PlayerManager.resCommonMsg(msg);
					GameApp.HomeManager.resFailEnterRoom();
				}
			}, this);*/
		}

		// 快速进入桌子
		public static send_ReqFastEnterTable(roomid: number): void {
			let body = {
				"id": BaccaratReq.c2s_bjl_ReqFastEnterTable_msg,
				"roomId": roomid
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
					BaccaratReq.load_scene();
					BaccaratMgr.bjlModel.setMembers(msg.mems);
				} else {
					// 失败
					PlayerManager.resCommonMsg(msg);
					// GameApp.HomeManager.resQuickEnterCurGame();
				}
			}, this);
		}

		// 离开房间
		public static send_ReqExitRoom(): void {
			let body = {
				"id": BaccaratReq.c2s_bjl_ReqExitRoom_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
					BaccaratRes.unregisterListener();
				} else {
					// 失败
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

		// 退出桌子
		public static send_ReqExitTable(): void {
			let body = {
				"id": BaccaratReq.c2s_bjl_ReqExitTable_msg
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					BaccaratReq.send_ReqExitRoom();
					App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.ExitTable, 1);
				} else {
					// 失败
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}

        // 下注，只做保存下注数据
		public static send_ReqBet(bet: number,area: number): void {
			let body = {
				"id": BaccaratReq.c2s_bjl_ReqBet_msg,
				"chips": bet,
                "area": area
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.DoBet, msg.chips,msg.area);
				} else {
					// 失败
					PlayerManager.resCommonMsg(msg);
				}
			}, this);
		}

        //清除下注，清空下注信息
		public static send_ReqClearBet(): void {
			let body = {
				"id": BaccaratReq.c2s_bjl_ReqClearBet_msg,
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					// 成功
					App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.ClearBet);
				} else {
					// 失败
					PlayerManager.resCommonMsg(msg);
				}
			}, this);
		}

        //获取房间游戏信息
		public static send_ReqGameInfo(): void {
			BaccaratMgr.setEnterScene(false);
			let body = {
				"id": BaccaratReq.c2s_bjl_ReqGameInfo_msg,
			};
			App.Socket.send(body, function (msgid, msg) {
				if (msgid == body.id) {
					if(!msg||!msg.roomInfo){
						LoginMgr.start();
						return;
					}
					// BaccaratMgr.setEnterScene(true);
					GameApp.PlayerInfo.curGameID = 18;
					BaccaratMgr.bjlModel.setMembers(msg.mems);
					BaccaratMgr.setRoomInfo(msg.roomInfo);
					App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameInfo, msg);
				} else {
					// 失败
				}
			}, this);
		}

		// 金币兑换筹码
		public static send_ReqExchangeChips(gold: number): void {
			let body = {
				id: BaccaratReq.c2s_bjl_ReqExchangeChips_msg,
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
				id: BaccaratReq.c2s_bjl_ReqExchangeGold_msg,
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

		public static set_RoomInfo(roomInfo): void {
			BaccaratMgr.setRoomInfo(roomInfo);
			egret.log('Set_RoomInfo', roomInfo);
		}

		public static load_scene() {
			if(App.ViewManager.isShow(ViewConst.Baccarat)){
				this.send_ReqGameInfo();
			}
			else{
				baccarat.BaccaratMgr.start();
				BaccaratRes.registerListener();
			}
		}

		// 注册公共模块请求
		public static registerComReq(): void {
			GameApp.HomeManager.registerModuleReq(18, CommonReq.SetRoomInfo, BaccaratReq.set_RoomInfo, BaccaratReq);
			GameApp.HomeManager.registerModuleReq(18, CommonReq.LoadGameScene, BaccaratReq.load_scene, BaccaratReq);
			GameApp.HomeManager.registerModuleReq(18, CommonReq.ReqEnterCurGame, BaccaratReq.send_ReqEnterGameHall, BaccaratReq);
			GameApp.HomeManager.registerModuleReq(18, CommonReq.ReqEnterCurGameRoom, BaccaratReq.send_ReqEnterRoom, BaccaratReq);
			GameApp.HomeManager.registerModuleReq(18, CommonReq.ReqExitCurGameRoom, BaccaratReq.send_ReqExitRoom, BaccaratReq);
			GameApp.HomeManager.registerModuleReq(18, CommonReq.ReqQuickEnterCurGame, BaccaratReq.send_ReqFastEnterTable, BaccaratReq);
			GameApp.HomeManager.registerModuleReq(18, CommonReq.ReqExitCurGameTable, BaccaratReq.send_ReqExitTable, BaccaratReq);
		}

	}
}