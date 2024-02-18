class PlayerRes {
	private static s2c_player_ResPlayerAttrChange = '214';         //[玩家属性变更]
	private static s2c_player_ResPlayerInfo_msg = '201';           //[玩家信息]
	public static s2c_player_ResLogout_msg = '105';                //[主动退出]
	public static s2c_player_ResRepeatLogin_msg = '108';           //[重复登录退出]
	public static s2c_player_ResLocked_msg = '109';                //[锁定退出]
	public static s2c_player_ResNetStuck_msg = '110';              //[卡线退出]
	public static s2c_player_ResStopService_msg = '112';           //[停服退出]

	// 玩家属性改变
	private static rec_player_ResPlayerAttrChange(msg: any): void {
		PlayerManager.resPlayerAttrChangeMsg(msg);
	}

	// 重复登录 108
	private static rec_player_ResRepeatLogin(msg: any): void {
		PlayerManager.resLogoutMsg(msg);
	}

	// 玩家被踢退出 109
	private static rec_player_ResLocked(msg: any): void {
		PlayerManager.resLogoutMsg(msg);
	}

	// 网络卡线踢出 110 
	private static rec_player_ResNetStuck(msg: any): void {
		PlayerManager.resLogoutMsg(msg);
	}

	// 停服 112
	private static rec_player_ResStopService(msg: any): void {
		PlayerManager.resLogoutMsg(msg);
	}


	// 玩家信息通知
	private static rec_player_ResPlayerInfo(msg): void {
		GameApp.PlayerInfo.loginSuccess(msg.playerInfo, msg.gameInfo);
		let gameInfo: any = GameApp.PlayerInfo.gameInfo;
		if (gameInfo && gameInfo.gameType && gameInfo.gameType > 0) {
			// 如果有游戏信息
			GameApp.PlayerInfo.curGameID = gameInfo.gameType;
			GameApp.HomeManager.loadGameScene(gameInfo.gameType);
		} else {
			// 进入大厅
			if (App.SceneManager.getCurrScene() != SceneConsts.Login) {
				LoginMgr.start();
			} else {
				App.ViewManager.open(ViewConst.Home);
			}
		}
	}

	public static registerResListener(): void {
		App.MessageCenter.addListener(PlayerRes.s2c_player_ResPlayerInfo_msg, PlayerRes.rec_player_ResPlayerInfo, PlayerRes);
		App.MessageCenter.addListener(PlayerRes.s2c_player_ResPlayerAttrChange, PlayerRes.rec_player_ResPlayerAttrChange, PlayerRes);
		App.MessageCenter.addListener(PlayerRes.s2c_player_ResRepeatLogin_msg, PlayerRes.rec_player_ResRepeatLogin, PlayerRes);
		App.MessageCenter.addListener(PlayerRes.s2c_player_ResLocked_msg, PlayerRes.rec_player_ResLocked, PlayerRes);
		App.MessageCenter.addListener(PlayerRes.s2c_player_ResNetStuck_msg, PlayerRes.rec_player_ResNetStuck, PlayerRes);
		App.MessageCenter.addListener(PlayerRes.s2c_player_ResStopService_msg, PlayerRes.rec_player_ResStopService, PlayerRes);
	}

}