module brnn {
	export class BrnnRes {
		private static s2c_brnn_ResExitRoom_msg = '6051'; 			  //[离开桌子]
		private static s2c_brnn_ResChangeChips_msg = '6052'; 		  //[筹码变化]
		private static s2c_brnn_ResEnterTable_msg = '6053';      	  //[自己进入桌子]
		private static s2c_brnn_ResOtherEnterTable_msg = '6054';      //[其他人进入桌子]
		private static s2c_brnn_ResExitTable_msg = '6055'; 			  //[离开桌子]
		private static s2c_brnn_ResApplyZuoZLst_msg = '6057'; 		  //[申请坐庄列表]
		private static s2c_brnn_ResSendCard_msg = '6058';			  //[发牌通知]
		private static s2c_brnn_ResBet_msg = '6059';			  	  //[桌子上下注信息-通知]
		private static s2c_brnn_ResNewDealer_msg = '6060'; 			  //[新的庄家]
		private static s2c_brnn_ResBill_msg = '6061'; 				  //[结算信息]
		private static s2c_brnn_ResHistory_msg = '6062'; 			  //[历史记录]
		private static s2c_brnn_ResGameStage_msg = '6063'; 			  //[游戏阶段]

		// 退出房间
		private static rec_ResExitRoom(msg): void {
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResExitRoom, msg)
			} else {

			}
		}

		// 筹码变化 {playerId, chips}
		private static rec_ResChipsChange(msg): void {
			if(GameApp.PlayerInfo.isMySelf(msg.playerId)) {
				BrnnMgr.mainMoel.chips = msg.chips.toNumber();
			}
			BrnnMgr.mainMoel.updateMemberChips(msg.playerId.toNumber(), msg.chips);
			if (BrnnMgr.isEnterScene() && GameApp.PlayerInfo.isMySelf(msg.playerId)) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResChangeChips, msg)
			}
		}

		// 自己进入牌桌 {mems, totalChips}
		private static rec_ResEnterTable(msg): void {
			if (msg.mems) {
				msg.mems.forEach(mem => {
					BrnnMgr.mainMoel.addMember(mem);
				});
				BrnnMgr.mainMoel.chips = msg.totalChips.toNumber();
				BrnnMgr.start();
			}
		}

		// 其他玩家进入牌桌 {mem}
		private static rec_ResOtherEnterTable(msg): void {
			BrnnMgr.mainMoel.addMember(msg.mem);
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResOtherEnterTable, msg.mem)
			}
		}

		// 玩家退出桌子 {id, playerId, order}
		private static rec_ResExitTable(msg): void {
			BrnnMgr.mainMoel.removeMember(msg.playerId.toNumber());
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResExitTable, msg);
			}
		}

		// 坐庄列表 {applicants:[is1, id2, id3]}
		private static rec_ResApplyZuoZLst(msg): void {
			BrnnMgr.mainMoel.applicants = msg.applicants;
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResApplyZuoZLst, msg);
			}
		}

		// 发牌 {cardsType, cards:[1,2,3,4,5]}
		private static rec_ResSendCard(msg): void {
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResSendCard, msg.cardsInfo);
			}
		}

		// 下注信息 {betInfo} [area, chips]
		private static rec_ResTableBet(msg): void {
			// 所有玩家的下注信息
			BrnnMgr.mainMoel.totalBetChips = msg.betInfo;
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResTableBet, msg.betInfo);
			}
		}

		// 庄家信息 {playerId, name, sex, chips, num, score}
		private static rec_ResNewDealer(msg): void {
			BrnnMgr.mainMoel.banker = msg;
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResNewDealer, msg);
			}
		}

		// 结算信息 {bankerChips, playerChips}
		private static rec_ResBill(msg): void {
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResBill, msg);
			}
		}

		// 历史输赢 {result} int
		private static rec_ResHistory(msg): void {
			BrnnMgr.mainMoel.histories = msg.result;
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResHistory, msg);
			}
		}

		// 游戏阶段 {state, time} 1:休息,2:下注, 3:发牌
		private static rec_ResGameStage(msg): void {
			BrnnMgr.mainMoel.gameStage = msg;
			if (BrnnMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.BrnnMain, BrnnResConst.ResGameStage, msg);
			}
		}

		public static registerResListener() {
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResExitRoom_msg, BrnnRes.rec_ResExitRoom, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResChangeChips_msg, BrnnRes.rec_ResChipsChange, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResEnterTable_msg, BrnnRes.rec_ResEnterTable, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResOtherEnterTable_msg, BrnnRes.rec_ResOtherEnterTable, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResExitTable_msg, BrnnRes.rec_ResExitTable, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResApplyZuoZLst_msg, BrnnRes.rec_ResApplyZuoZLst, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResSendCard_msg, BrnnRes.rec_ResSendCard, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResBet_msg, BrnnRes.rec_ResTableBet, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResNewDealer_msg, BrnnRes.rec_ResNewDealer, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResBill_msg, BrnnRes.rec_ResBill, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResHistory_msg, BrnnRes.rec_ResHistory, BrnnRes);
			App.MessageCenter.addListener(BrnnRes.s2c_brnn_ResGameStage_msg, BrnnRes.rec_ResGameStage, BrnnRes);
		}

		public static removeResListener() {
			App.MessageCenter.removeAll(BrnnRes)
		}
	}
}