module fish {
	export enum FishResConst {
		ResEnterGame = 1000001,	     //[进入游戏]
		SetRoomInfo,		 		 //[设置房间信息]
		LoadScene,				     //[加载场景]
		ResEnterTable,               //[进入桌子成功]
		ResBackTable,			     //[断线重连]
		ResEnterScene,               //[进入场景]
		ResOtherEnterTable,          //[其他人进入桌子]
		ResChangeChips,				 //[筹码变化]
		ResFire,					 //[开炮]
		ResSwitchBattery, 		     //[切换大炮]
		ResHitFish,					 //[击中鱼]
		ResExitTable,			 	 //[离开桌子]
		ResLockFish,				 //[锁定鱼]
		ResCancelLockFish,			 //[取消锁定鱼]
		ResInsteadPlayersUpgrade,	 //[代发机器人列表更新]
		ResProductFish, 			 //[产生鱼]
		ResScence 					 //[切换场景]

	}
}