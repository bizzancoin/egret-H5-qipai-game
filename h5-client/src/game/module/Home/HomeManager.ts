class HomeManager extends BaseClass {

    // 游戏公共请求模块
    private _moduleReq: any;
    private _moduleReqFuncs: any;
    // 游戏通知监听模块
    private _moduleResFuncs: any;
    private _members: any;
    private _roomSettingData: any;

    public constructor() {
        super();
        this._moduleReq = {};
        this._moduleReqFuncs = {};
        this._moduleResFuncs = {};
    }

    public registerModuleReqFunc(gameid: number, method: Function, methodObj: any) {
        this._moduleReqFuncs[gameid] = [method, methodObj];
    }

    // 注册请求
    private registerModuleReqListener(gameid: number) {
        if (this._moduleReqFuncs[gameid]) {
            let func = this._moduleReqFuncs[gameid][0];
            let obj = this._moduleReqFuncs[gameid][1];
            func.apply(obj);
        }
    }

    // 注册游戏监听模块
    public registerModuleResFunc(gameid, method: Function, methodObj: any): void {
        this._moduleResFuncs[gameid] = [method, methodObj];
    }

    // 注册游戏监听
    private registerGameResListener(gameid) {
        if (this._moduleResFuncs[gameid]) {
            let func = this._moduleResFuncs[gameid][0];
            let obj = this._moduleResFuncs[gameid][1];
            func.apply(obj);
        }
    }

    // 注册公共请求模块
    public registerModuleReq(gameid: number, reqKey: number, method: Function, methodObj: any): void {
        if (!this._moduleReq[gameid]) {
            this._moduleReq[gameid] = {};
        }
        this._moduleReq[gameid][reqKey] = [method, methodObj];
    }

    // 移除公共请求模块
    public removeModuleReq(gameid: number) {
        if (this._moduleReq[gameid]) {
            delete this._moduleReq[gameid];
        }
    }

    // 进入游戏场景(断线重连)
    public loadGameScene(gameid): void {
        this.registerGameResListener(gameid);
        this.registerModuleReqListener(gameid);
        if (this.containsModule(gameid, CommonReq.LoadGameScene)) {
            let func = this._moduleReq[gameid][CommonReq.LoadGameScene][0];
            let obj = this._moduleReq[gameid][CommonReq.LoadGameScene][1];
            func.apply(obj);
        }
    }

    // 设置房间信息
    public setRoomInfo(roomInfo: any): void {
        let gameid = GameApp.PlayerInfo.curGameID;
        if (this.containsModule(gameid, CommonReq.SetRoomInfo)) {
            let func = this._moduleReq[gameid][CommonReq.SetRoomInfo][0];
            let obj = this._moduleReq[gameid][CommonReq.SetRoomInfo][1];
            func.apply(obj, [roomInfo]);
        }
    }

    public reqEnterCurGame(gameid: number): void {
        this.registerGameResListener(gameid);
        this.registerModuleReqListener(gameid);
        if (this.containsModule(gameid, CommonReq.ReqEnterCurGame)) {
            let func = this._moduleReq[gameid][CommonReq.ReqEnterCurGame][0];
            let obj = this._moduleReq[gameid][CommonReq.ReqEnterCurGame][1];
            func.apply(obj);
        }
    }

    // 进入房间
    public reqEnterCurGameRoom(roomid: number): void {
        let gameid = GameApp.PlayerInfo.curGameID;
        if (this.containsModule(gameid, CommonReq.ReqEnterCurGameRoom)) {
            let func = this._moduleReq[gameid][CommonReq.ReqEnterCurGameRoom][0];
            let obj = this._moduleReq[gameid][CommonReq.ReqEnterCurGameRoom][1];
            func.apply(obj, [roomid]);
        }
    }

    // 离开房间
    public reqExitCurGameRoom(): void {
        let gameid = GameApp.PlayerInfo.curGameID;
        if (this.containsModule(gameid, CommonReq.ReqExitCurGameRoom)) {
            let func = this._moduleReq[gameid][CommonReq.ReqExitCurGameRoom][0];
            let obj = this._moduleReq[gameid][CommonReq.ReqExitCurGameRoom][1];
            func.apply(obj);
        }
    }

    // 进入桌子
    public reqQuickEnterCurGame(roomid: number): void {
        let gameid = GameApp.PlayerInfo.curGameID;
        if (this.containsModule(gameid, CommonReq.ReqQuickEnterCurGame)) {
            let func = this._moduleReq[gameid][CommonReq.ReqQuickEnterCurGame][0];
            let obj = this._moduleReq[gameid][CommonReq.ReqQuickEnterCurGame][1];
            func.apply(obj, [roomid]);
        }
    }

    // 离开桌子
    public reqExitCurGameTable(): void {
        let gameid = GameApp.PlayerInfo.curGameID;
        if (this.containsModule(gameid, CommonReq.ReqExitCurGameTable)) {
            let func = this._moduleReq[gameid][CommonReq.ReqExitCurGameTable][0];
            let obj = this._moduleReq[gameid][CommonReq.ReqExitCurGameTable][1];
            func.apply(obj);
        }
    }

    private containsModule(gameid: number, reqKey: number): boolean {
        return gameid && this._moduleReq[gameid] && this._moduleReq[gameid][reqKey];
    }

    // 进入大厅成功
    public resEnterGameHall(gameTitle: string): void {
        App.EasyLoading.hideLoading();
        App.ViewManager.open(ViewConst.Room, gameTitle);
    }

    // 进入房间失败结果消息
    public resFailEnterRoom(): void {
        this.reqEnterCurGame(GameApp.PlayerInfo.curGameID)
    }

    // 房间座位信息变更
    /**
     * SeatInfo = {
			tableId, --桌子id
			order, --座位顺序
			playerId, --玩家id,0代表座位上没有人
		}
     */
    public resSeatInfoUpdateMsg(msg): void {
        let tmpSeat = msg.seat;
        if (!this._members) {
            return;
        }
        let tmpMember = this._members[msg.seat.playerId];
        if (tmpMember) {
            tmpSeat.sex = tmpMember.sex;
            tmpSeat.icon = tmpMember.icon;
            tmpSeat.playerName = tmpMember.playerName;
            tmpSeat.state = tmpMember.state;
            tmpSeat.gold = tmpMember.gold;
            tmpSeat.cedit = tmpMember.cedit;
            tmpSeat.chips = tmpMember.chips;
            tmpSeat.sysHost = tmpMember.sysHost;
            tmpSeat.vip = tmpMember.vip;
        } else {
            tmpSeat.state = 0
        }
        GameApp.PlayerInfo.updateDeskInfo(tmpSeat);
    }

    /**
     * 房间成员变更信息消息
	    msg = {
		    名称:member 类型:MemInfo 备注:房间成员信息
	    }
     */
    public resMemInfoUpdateMsg(msg: any): void {
        GameApp.PlayerInfo.updateDeskInfo(msg.member);
        if (!this._members) {
            this._members = {};
        }
        this._members[msg.member.playerId] = msg.member;
    }

    public resRemoveMemInfoMsg(msg: any): void {
        if (this._members) {
            this._members[msg.playerId] = null;
        }
    }

    // 房间设置更新消息
    public resRoomSettingUpdateMsg(msg: any): void {
        this._roomSettingData = msg.roomSetting;
    }

    // 快速加入失败结果消息
    public resQuickEnterCurGame(): void {
        this.reqExitCurGameRoom();
    }

    // 进入房间结果消息
    public resEnterRoomMsg(msg: any): void {
        this._members = {};
        let orderMembers = [];
        for (let i: number = 0; i < msg.members.length; i++) {
            let mem = msg.members[i];
            this._members[mem.playerId] = mem;
            if (GameApp.PlayerInfo.isMySelf(mem.playerId)) {
                orderMembers.unshift(mem);
            } else {
                orderMembers.push(mem);
            }
        }

        for (let i: number = 0; i < msg.tables.length; i++) {
            let tableinfo = msg.tables[i];
            for (let j: number = 0; j < tableinfo.seats.length; j++) {
                let tmpSeat = tableinfo.seats[j];
                if (!this._members) {
                    return;
                }
                let tmpMember = this._members[tmpSeat.playerId];
                if (tmpMember) {
                    tmpSeat.sex = tmpMember.sex;
                    tmpSeat.icon = tmpMember.icon;
                    tmpSeat.playerName = tmpMember.playerName;
                    tmpSeat.state = tmpMember.state;
                    tmpSeat.gold = tmpMember.gold;
                    tmpSeat.cedit = tmpMember.cedit;
                    tmpSeat.chips = tmpMember.chips;
                    tmpSeat.sysHost = tmpMember.sysHost;
                } else {
                    tmpSeat = 0;
                }
            }
        }

        GameApp.PlayerInfo.myDesksInfo = msg.tables;
        //this.reqQuickEnterCurGame();
    }

    // 获取成员信息
    public getMemberInfos(msg: any): any {
        let members = [];
        for (let i: number = 0; i < msg.players.length; i++) {
            let player = msg.players[i];
            if (GameApp.PlayerInfo.isMySelf(player.playerId)) {
                members.unshift(player);
            } else {
                members.push(player);
            }
        }
        return members;
    }

    public resRoomPlayerNumMsg(msg: any): void {
        GameApp.PlayerInfo.updateRoomInfo(msg.roomId, msg.num);
    }

    // 前台转后台
    public pauseGame(): void {
        if (GameApp.PlayerInfo.isLogin && GameApp.PlayerInfo.curGameID && App.SceneManager.getCurrScene() != SceneConsts.Login) {
            let gameid = GameApp.PlayerInfo.curGameID;
            if (this.containsModule(gameid, CommonReq.PauseGame)) {
                let func = this._moduleReq[gameid][CommonReq.PauseGame][0];
                let obj = this._moduleReq[gameid][CommonReq.PauseGame][1];
                func.apply(obj);
            }
        }
    }

    // 后台转前台
    public resumeGame(): void {
        if (GameApp.PlayerInfo.isLogin && GameApp.PlayerInfo.curGameID && App.SceneManager.getCurrScene() != SceneConsts.Login) {
                GameApp.HomeManager.loadGameScene(GameApp.PlayerInfo.curGameID);
            }
    }

}