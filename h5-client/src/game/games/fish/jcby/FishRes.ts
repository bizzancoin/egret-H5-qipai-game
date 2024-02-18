module jcby {
	export class FishRes {
		private static s2c_jc_ResOtherEnterTable_msg = '5451';      //[其他人进入桌子]
		private static s2c_jc_ResChangeChips_msg = '5452'; 			//[筹码变化]
		private static s2c_jc_ResFire_msg = '5453'; 				//[开炮]
		private static s2c_jc_ResSwitchBattery_msg = '5454'; 		//[切换大炮]
		private static s2c_jc_ResHitFish_msg = '5455'; 				//[击中鱼]
		private static s2c_jc_ResExitTable_msg = '5456'; 			//[离开桌子]
		private static s2c_jc_ResLockFish_msg = '5457'; 			//[锁定鱼]
		private static s2c_jc_ResCancelLockFish_msg = '5458'; 		//[取消锁定鱼]
		private static s2c_jc_ResInsteadPlayersUpgrade_msg = '5459';	 //[代发机器人列表更新]
		private static s2c_jc_ResProductFish_msg = '5461'; 				 //[产生鱼]
		private static s2c_jc_ResScence_msg = '5462'; 					 //[切换场景]

		// 其他人进入桌子{mem, battery}
		private static rec_ResOtherEnterTable(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResOtherEnterTable, msg.mem, msg.battery);
			} else {
				// 保存玩家数据
				let info = {
					playerId: msg.mem.playerId,
					seatId: msg.mem.order + 1,
					isLocal: GameApp.PlayerInfo.isMySelf(msg.mem.playerId),
					chips: msg.mem.chips,
				};
				fish.FishMgr.fishModel.addUserInfo(info.seatId, info);
			}
		}

		// 筹码变化 {id, order, chips}
		private static rec_ResChipsChange(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResChangeChips, msg);
			}
		}

		// 开炮 {playerId, order, bulletId, angle }
		private static rec_ResFire(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResFire, msg);
			}
		}

		// 切换大炮 {battery(BatteryInfo):playerId, order, score, num, power }
		private static rec_ResSwitchBattery(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResSwitchBattery, msg.battery);
			}
		}

		// 击中鱼通知 {id, playerId, order, dies{fishId, score}}
		private static rec_ResHitFish(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResHitFish, msg);
			}
		}

		// 玩家退出桌子 {id, playerId, order}
		private static rec_ResExitTable(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResExitTable, msg);
			}
		}

		// 锁定鱼 {id, playerId, order, fishId}
		private static rec_ResLockFish(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResLockFish, msg);
			}
		}

		// 取消锁定鱼 {id, playerId, order, fishId}
		private static rec_ResCancelLockFish(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResCancelLockFish, msg);
			}
		}

		// 代发机器人列表更新 {id, insteadPlayers}
		private static rec_ResInsteadPlayersUpgrade(msg): void {
			fish.FishMgr.fishModel.instatdSendPlayerLst = msg.insteadPlayers;
			if (fish.FishMgr.isEnterScene()) {
				//App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResInsteadPlayersUpgrade, msg);
			}
		}

		// 产生鱼{id, fishs{id, type, xcoord, ycoord, road, time, angle}}
		private static rec_ResProductFish(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResProductFish, msg);
			}
		}

		// 切换场景 {id, scene}
		private static rec_ResScence(msg): void {
			if (fish.FishMgr.isEnterScene()) {
				App.ControllerManager.applyFunc(ControllerConst.FishMain, fish.FishResConst.ResScence, msg);
			}
		}

		public static registerResListener() {
			App.MessageCenter.addListener(FishRes.s2c_jc_ResOtherEnterTable_msg, FishRes.rec_ResOtherEnterTable, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResChangeChips_msg, FishRes.rec_ResChipsChange, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResFire_msg, FishRes.rec_ResFire, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResSwitchBattery_msg, FishRes.rec_ResSwitchBattery, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResHitFish_msg, FishRes.rec_ResHitFish, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResExitTable_msg, FishRes.rec_ResExitTable, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResLockFish_msg, FishRes.rec_ResLockFish, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResCancelLockFish_msg, FishRes.rec_ResCancelLockFish, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResInsteadPlayersUpgrade_msg, FishRes.rec_ResInsteadPlayersUpgrade, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResProductFish_msg, FishRes.rec_ResProductFish, FishRes);
			App.MessageCenter.addListener(FishRes.s2c_jc_ResScence_msg, FishRes.rec_ResScence, FishRes);
		}

		public static removeResListener() {
			App.MessageCenter.removeAll(FishRes)
			/*App.MessageCenter.removeListener(FishRes.s2c_jc_ResOtherEnterTable_msg, FishRes.rec_ResOtherEnterTable, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResChangeChips_msg, FishRes.rec_ResChipsChange, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResFire_msg, FishRes.rec_ResFire, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResSwitchBattery_msg, FishRes.rec_ResSwitchBattery, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResHitFish_msg, FishRes.rec_ResHitFish, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResExitTable_msg, FishRes.rec_ResExitTable, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResLockFish_msg, FishRes.rec_ResLockFish, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResCancelLockFish_msg, FishRes.rec_ResCancelLockFish, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResInsteadPlayersUpgrade_msg, FishRes.rec_ResInsteadPlayersUpgrade, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResProductFish_msg, FishRes.rec_ResProductFish, FishRes);
			App.MessageCenter.removeListener(FishRes.s2c_jc_ResScence_msg, FishRes.rec_ResScence, FishRes);*/
		}
	}
}