class PlayerManager {

	public static resCommonMsg(msg: any) {
		App.EasyLoading.hideLoading();
		if (msg.errorCode) {
			GameApp.PromotManager.oneButtonTip(msg.errorCode, msg.args);
		}
	}

	public static resPlayerAttrChangeMsg(msg: any): void {
		switch (msg.type) {
			case 1: // 元宝
				GameApp.PlayerInfo.acer = msg.val;
				break;
			case 2: // 金币
				break;
			case 3: // 保险箱金币
				GameApp.PlayerInfo.safeGold = msg.val;
				App.MessageCenter.dispatch('event_change_gold');
				break;
			case 4: // 积分
				GameApp.PlayerInfo.integral = msg.val;
				break;
			case 5: // 奖励
				GameApp.PlayerInfo.lottery = msg.val;
				break;
			case 6: // 等级
				let level = parseInt(msg.val);
				if (level && level != GameApp.PlayerInfo.level) {
					GameApp.PromotManager.floatingTip('恭喜升级到lv.' + level);
				}
				GameApp.PlayerInfo.level = level;
				break;
			case 7: // vipLevel
				GameApp.PlayerInfo.vipLevel = msg.val;
				break;
			case 8: // vipTime
				GameApp.PlayerInfo.vipTime = msg.val;
				break;
			case 9: // 经验
				GameApp.PlayerInfo.exp = msg.val;
				break;
			case 10:
				GameApp.PlayerInfo.transferReward = msg.val;
				break;
			case 11:
				GameApp.PlayerInfo.nextLevelExp = msg.val;
				break;
			case 12:
				GameApp.PlayerInfo.superId = msg.val;
				break;
			case 13:
				GameApp.PlayerInfo.type = msg.val;
				break;
		}
	}

	private static logout_callback(code?: any, msg?: any): void {
		GameApp.PromotManager.hideButtonTip();
		egret.localStorage.removeItem('reloginCode');
		egret.localStorage.removeItem('reloginType');
		LoginMgr.start();
	}

	public static resLogoutMsg(msg: any): void {
		if (msg) {
			GameApp.PlayerInfo.updateLoginState(false);
			switch (msg.id) {
				case parseInt(PlayerRes.s2c_player_ResLogout_msg):
					PlayerManager.logout_callback();
					break;
				case parseInt(PlayerRes.s2c_player_ResRepeatLogin_msg):
					GameApp.PromotManager.oneButtonTip('129', null, ()=>{
						PlayerManager.logout_callback(PlayerRes.s2c_player_ResLogout_msg, '对不起，您的账号在另一个地方登录');
					}, PlayerManager);
					break;
				case parseInt(PlayerRes.s2c_player_ResLocked_msg):
					GameApp.PromotManager.oneButtonTip('106', null, ()=>{
						PlayerManager.logout_callback(PlayerRes.s2c_player_ResLocked_msg, '对不起，您的账号已被冻结')
					}, PlayerManager);
					break;
				case parseInt(PlayerRes.s2c_player_ResNetStuck_msg):
					GameApp.PromotManager.oneButtonTip('715', null, ()=>{
						PlayerManager.logout_callback(PlayerRes.s2c_player_ResNetStuck_msg, '对不起，当前牌桌出错，请重新登录。')
					}, PlayerManager);
					break;
				case parseInt(PlayerRes.s2c_player_ResStopService_msg):
					GameApp.PromotManager.oneButtonTip('100017', null, ()=>{
						PlayerManager.logout_callback(PlayerRes.s2c_player_ResNetStuck_msg, '对不起，游戏服务器维护中，请关注官网公告！')
					}, PlayerManager);
					break;
			}
		}
	}

	public static registerResListener(): void {
		CommonNoticeRes.registerResListener();
		PlayerRes.registerResListener();
	}
}