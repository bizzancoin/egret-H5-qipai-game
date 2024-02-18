module fish {
	export class FishMainModel {

		// 房间信息
		private _roomInfo: any;
		// 当前是否是断线重连
		private _backRoom: boolean = false;
		private _userInfoLst: any;
		private _instatdSendPlayerLst: any;
		private _isEnterScene: boolean;
		public gameInfo: any;

		public set roomInfo(value: any) {
			this._roomInfo = value;
		}

		public get roomInfo(): any {
			return this._roomInfo;
		}

		public set backRoom(value: boolean) {
			this._backRoom = value;
		}

		public get backRoom(): boolean {
			return this._backRoom;
		}

		public set isEnterScene(value: boolean) {
			this._isEnterScene = value;
		}

		public get isEnterScene(): boolean {
			return this._isEnterScene;
		}

		public set instatdSendPlayerLst(value: any) {
			this._instatdSendPlayerLst = value;
		}

		public get instatdSendPlayerLst(): any {
			return this._instatdSendPlayerLst;
		}

		public get userInfoLst(): any {
			return this._userInfoLst;
		}

		// {playerId, seatId, isLocal, chips}
		public addUserInfo(seatId: any, userInfo: any): void {
			if (!this._userInfoLst) {
				this._userInfoLst = {};
			}
			this._userInfoLst[seatId] = userInfo;
		}

		public resetUserInfo(seatId: number) {
			if (this._userInfoLst[seatId].seatId != -1) {
				this._userInfoLst[seatId].seatId = -1;
				this._userInfoLst[seatId].playerId = -1;
				this._userInfoLst[seatId].isLocal = false;
				this._userInfoLst[seatId].chips = 0;
			}
		}

		public getUserInfo(seatId: number): any {
			if (!this._userInfoLst) {
				return null;
			}
			return this._userInfoLst[seatId];
		}

		public removeAllUserInfo(): void {
			this._userInfoLst = null;
		}
	}
}