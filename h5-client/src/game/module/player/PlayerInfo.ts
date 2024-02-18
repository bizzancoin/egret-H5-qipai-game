class PlayerInfo extends BaseClass {
	public constructor() {
		super();
	}

	id: any = '';                 // 玩家id
	superId = 0;             // 玩家靓号id
	userName = '';           // 用户名
	nickName = '';           // 昵称
	sex = '';                // 性别
	icon = 1;                // 头像id
	level = 0;               // 玩家等级
	vipLevel = 0;            // vip 等级
	vipTime = 0;             // vip 时长（秒）
	gold:any = 0;                // 金币
	safeGold = 0;            // 保险箱金币（银行金币）
	acer = 0;                // 元宝   
	integral = 0;            // 积分 

	fullName = '';           // 玩家真实姓名
	idCardNo = '';           // 身份证
	lottery = 0;             // 奖券
	phone = '';              // 电话
	email = '';              // 邮箱
	age = 0;                 // 年龄
	birthMonth = 1;          // 出生月份
	birthDay = 1;            // 出生日
	province = '';           // 省份
	city = '';               // 城市
	addr = '';               // 地址
	signature = '';          // 签名
	bindingMac = '';         // 绑定机器码
	havePwdProtect = 0;      // 是否有密保(0:没有,非0:有)      
	loginPhoneVerify = 0;    // 登录是否需要手机验证(0:不需要，非0:需要)
	playItemEffect = 0;      // 是否需要播放道具使用特效(0:不需要，非0:需要)
	isTourist = false;       // 是否游客
	isLogin = false;         // 是否登录
	reLoginCode = '';        // 断线重连码
	transferReward = 0;      // 代理转账获取金币.
	type = 0;                // 0:普通用户 1:代理.
	nextLevelExp = 0;        // 下一级升级所需经验
	exp = 0;                 // 当前经验

	myRoomTypeInfo: any[] = [];     // 所有房间信息
	myDesksInfo: any[] = [];         // 当前房间所有桌子信息
	curGameID = null;      // 当前游戏id
	myTableId = null;
	loginType = LoginType.Tourist;                // 登录类型 1 游客 2 账号 3 机器人 4 微信登陆
	gameInfo: any = null;

	public loginSuccess(data: any, gameInfo: any): void {
		this.id = data.id;
		this.superId = data.superId ? data.superId : 0;
		this.userName = data.userName ? data.userName : '';
		this.nickName = data.nickName ? data.nickName : '';
		this.level = data.level ? data.level : 0;
		this.vipLevel = data.vipLevel ? data.vipLevel : 0;
		this.gold = data.gold ? data.gold.toNumber() : 0;
		this.acer = data.ingot ? data.ingot : 0;
		this.safeGold = data.safeGold ? data.safeGold.toNumber() : 0;
		this.integral = data.cedit ? data.cedit : 0;
		this.lottery = data.lottery ? data.lottery : 0;

		this.phone = data.phone ? data.phone : '';
		this.email = data.email ? data.email : '';
		this.fullName = data.fullName ? data.fullName : '';
		this.sex = data.sex ? data.sex : '女';
		this.icon = data.icon ? data.icon : 1;
		this.idCardNo = data.idCardNo ? data.idCardNo : '';
		this.vipTime = data.vipDuration ? data.vipDuration : 0;
		this.age = data.age ? data.age : 18;
		this.birthMonth = data.birthMonth ? data.birthMonth : 1;
		this.birthDay = data.birthDay ? data.birthDay : 1;
		this.province = data.province ? data.province : '';
		this.city = data.city ? data.city : '';
		this.addr = data.addr ? data.addr : '';
		this.signature = data.signature ? data.signature : '';
		this.bindingMac = data.bindingMac ? data.bindingMac : '';
		this.havePwdProtect = data.havePwdProtect ? data.havePwdProtect : 0;
		this.loginPhoneVerify = data.loginPhoneVerify ? data.loginPhoneVerify : 0;
		this.playItemEffect = data.playItemEffect ? data.playItemEffect : 0;
		this.isTourist = data.tourist == 1;
		this.reLoginCode = data.reLoginCode ? data.reLoginCode : '';
		this.transferReward = data.transferReward ? data.transferReward : 0;
		this.type = data.type ? data.type : 0;
		this.nextLevelExp = data.nextLevelExp ? data.nextLevelExp : 0;
		this.exp = data.exp ? data.exp : 0;

		if (this.isTourist) {
			// 保存玩家用户名
			egret.localStorage.setItem('touristKey', this.userName);
		}
		// 保存reloginCode
		egret.localStorage.setItem('reloginCode', this.reLoginCode);
		this.updateLoginState(true);
		this.gameInfo = gameInfo;

	}

	// 更新房间玩家数量
	public updateRoomInfo(id, num): void {
		if (!this.myRoomTypeInfo || this.myRoomTypeInfo.length <= 0)
			return;
		for (let i: number = 0; i < this.myRoomTypeInfo.length; i++) {
			let roomData = this.myRoomTypeInfo[i];
			if (!roomData || roomData.length <= 0)
				continue;
			for (let j: number = 0; j < roomData.length; j++) {
				if (roomData[i].roomId == id) {
					roomData[i].playerNum = num;
					return;
				}
			}
		}
	}

	// 更新桌子信息
	public updateDeskInfo(value: any): void {
		if (!this.myDesksInfo || this.myDesksInfo.length <= 0)
			return;
		for (let i: number = 0; i < this.myDesksInfo.length; i++) {
			let desk = this.myDesksInfo[i].desk;
			if (desk && desk.id == value.tableId) {
				let seats = desk.setas;
				if (!seats || seats.length <= 0) {
					return;
				}
				for (let j: number = 0; j < seats.length; j++) {
					if (seats[i].order == value.order) {
						seats[i] = value;
					}
				}
			}
		}
	}


	public isMySelf(playerId): boolean {
		return this.id.compare(playerId) == 0;
	}

	public updateLoginState(isLogin: boolean) {
		this.isLogin = isLogin;
	}

	public logout(): void {
		this.updateLoginState(false);
		egret.localStorage.removeItem('reloginCode');
	}

	public getHeadTexture(icon?: any): egret.Texture {
		let head = icon;
		if (!head) {
			head = this.icon;
		}
		head = head % 10;
		let resName = 'player_' + head + '_png';
		return RES.getRes(resName);
	}

}