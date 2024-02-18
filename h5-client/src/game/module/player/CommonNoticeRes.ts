class CommonNoticeRes {
	private static s2c_common_ResError_msg = '3';                            //[通用错误消息消息]
	private static s2c_common_ResShow_msg = '4';                             //[通用右下角展示消息消息]
	private static s2c_common_ResTip_msg = '5';                              //[通用提示消息消息]
	private static s2c_common_ResWarn_msg = '6';                             //[通用警告消息消息]
	private static s2c_common_ResCloseRoom_msg = '5009';               		 //[房间关闭通知]
	private static S2c_commmon_ResMarqueeNotice_msg = '501';                 //[跑马灯公告]


	private static rec_common_ResError(msg: any): void {
		PlayerManager.resCommonMsg(msg);
	}

	private static rec_common_ResWarn(msg: any): void {
		PlayerManager.resCommonMsg(msg);
	}

	private static rec_common_ResTip(msg: any): void {
		PlayerManager.resCommonMsg(msg);
	}

	private static rec_common_ResShow(msg: any): void {
		// doNothing
	}

	private static rec_common_ResCloseRoom(msg: any): void {
		if (App.SceneManager.getCurrScene() == SceneConsts.Login) {
			if (App.ViewManager.isShow(ViewConst.Room)) {
				GameApp.HomeManager.reqEnterCurGame(GameApp.PlayerInfo.curGameID);
			}
		} else {
			GameApp.PromotManager.oneButtonTip('25035', {}, () => {
				GameApp.PromotManager.hideButtonTip();
				LoginMgr.start();
			}, CommonNoticeRes);
		}
	}

	private static rec_common_ResMarqueeNotice(msg): void {
		if(!msg.isInBackground) {
			GameApp.MarqueeManager.start(msg);
		}
	}

	public static registerResListener() {
		App.MessageCenter.addListener(CommonNoticeRes.s2c_common_ResError_msg, CommonNoticeRes.rec_common_ResError, CommonNoticeRes);
		App.MessageCenter.addListener(CommonNoticeRes.s2c_common_ResWarn_msg, CommonNoticeRes.rec_common_ResWarn, CommonNoticeRes);
		App.MessageCenter.addListener(CommonNoticeRes.s2c_common_ResTip_msg, CommonNoticeRes.rec_common_ResTip, CommonNoticeRes);
		App.MessageCenter.addListener(CommonNoticeRes.s2c_common_ResShow_msg, CommonNoticeRes.rec_common_ResShow, CommonNoticeRes);
		App.MessageCenter.addListener(CommonNoticeRes.s2c_common_ResCloseRoom_msg, CommonNoticeRes.rec_common_ResCloseRoom, CommonNoticeRes);
		App.MessageCenter.addListener(CommonNoticeRes.S2c_commmon_ResMarqueeNotice_msg, CommonNoticeRes.rec_common_ResMarqueeNotice, CommonNoticeRes);
	}


}