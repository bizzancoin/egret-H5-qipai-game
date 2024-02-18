module brnn {
	export enum BrnnResConst {
		ResEnterGame = 100001,  	 //[进入游戏]
		SetRoomInfo,		 		 //[设置房间信息]
		LoadScene,				     //[加载场景]
		ResEnterTable,               //[进入桌子成功]
		ResBackTable,			     //[断线重连]
		ResEnterScene,               //[进入场景]
		ResOtherEnterTable,          //[其他人进入桌子]
		ResChangeChips,				 //[筹码变化]
		ResExitTable,                //[退出桌子]
		ResExitRoom,                 //[退出房间]
		ResApplyZuoZLst,		     //[申请坐庄列表]
		ResSendCard,				 //[发牌]
		ResBet,						 //[我的下注]
		ResClearBet,			     //[清除下注信息]
		ResTableBet,			     //[桌子下注信息]
		ResNewDealer,			     //[新的庄家]
		ResBill,					 //[结算]
		ResHistory,                  //[历史]
		ResGameStage,				 //[游戏通知]
	}
}