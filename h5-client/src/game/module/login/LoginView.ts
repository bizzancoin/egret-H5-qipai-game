class LoginView extends BaseEuiView {

	private countdown: number = 60;

	private btnCode: eui.Button;
	private btnAccount: eui.Button;
	private btnTourist: eui.Button;
	private inputAccount: eui.TextInput;
	private inputCode: eui.TextInput;

	public constructor($controller: BaseController, $parent: eui.Group) {
		super($controller, $parent);
		this.skinName = "resource/skins/LoginSkin.exml";
	}

	public initUI(): void {
		super.initUI();
		// 只能输入数字
		this.inputAccount.restrict = "0-9";
		this.inputAccount.maxChars = 11;
		this.inputCode.restrict = "0-9";
		this.inputCode.maxChars = 6;
		// 注册事件
		this.btnCode.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onCodeHandler, this);
		this.btnAccount.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onAccountLoginHandler, this);
		this.btnTourist.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onTouristLoginHandler, this);
	}

	private onCodeHandler(evt: egret.TouchEvent): void {
		let phone = App.StringUtils.trimSpace(this.inputAccount.text);
		if (this.checkPhone(phone)) {
			this.btnCode.label = "60s, 再获取";
			this.btnCode.enabled = false;
			let t = 60;
			App.TimerManager.doTimer(1000, this.countdown, function () {
				t--;
				this.btnCode.label = t + "s, 再获取";
			}, this, function () {
				this.btnCode.label = "获取验证码";
				this.btnCode.enabled = true;
			}, this);

		}
	}

	private onAccountLoginHandler(evt: egret.TouchEvent): void {
		let phone = App.StringUtils.trimSpace(this.inputAccount.text);
		let code = App.StringUtils.trimSpace(this.inputCode.text);
		if (this.checkPhone(phone) && this.checkCode(code)) {
			LoginReq.send_loginBySmsCode(phone, code, function (success, msg) {
				if (success) {
					// 登录成功
					GameApp.PlayerInfo.loginType = LoginType.Phone;
					egret.localStorage.setItem('reloginType', LoginType.Phone + '');
				} else {
					GameApp.PromotManager.floatingTip(msg.errorCode);
				}
			}, this);
		}
	}

	private onTouristLoginHandler(evt: egret.TouchEvent): void {
		LoginReq.send_loginByTourist(function (success, msg) {
			if (success) {
				// 登录成功
				GameApp.PlayerInfo.loginType = LoginType.Tourist;
				egret.localStorage.setItem('reloginType', LoginType.Tourist + '');
			} else {
				GameApp.PromotManager.floatingTip(msg.errorCode);
			}
		}, this);
	}

	private checkPhone(phone: string): boolean {
		if (App.StringUtils.isEmpty(phone)) {
			GameApp.PromotManager.floatingTip('号码不能为空');
			return false;
		}
		if (!App.StringUtils.isPhone(phone)) {
			GameApp.PromotManager.floatingTip('号码不合法');
			return false;
		}
		return true;
	}

	private checkCode(code: string) {
		if (App.StringUtils.isEmpty(code)) {
			GameApp.PromotManager.floatingTip("验证码为空");
			return false;
		}
		if (code.length != 6) {
			GameApp.PromotManager.floatingTip("验证码不合法 < 6 位");
			return false;
		}
		return true;
	}

}