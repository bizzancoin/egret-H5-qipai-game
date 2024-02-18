module baccarat {
	export class BaccaratModel {
		private _roomInfo: any;
		private _isEnterScene: boolean;
		private _gameInfo: any;
		private _statInfo: any;
		private _billWayInfo: any;
		private _mems: any;
		private _me: any;
		private _myBet: Array<number>;
		private _lastBet: Array<number>;
		private _totalBet: Array<number>;

		public set roomInfo(value: any) {
			this._roomInfo = value;
		}
		public get roomInfo(): any {
			return this._roomInfo;
		}

		public set isEnterScene(value: boolean) {
			this._isEnterScene = value;
		}
		public get isEnterScene(): boolean {
			return this._isEnterScene;
		}

		public set gameInfo(value: any) {
			this._gameInfo = value;
		}
		public get gameInfo(): any {
			return this._gameInfo;
		}

		public set statInfo(value: any) {
			this._statInfo = value;
		}
		public get statInfo(): any {
			return this._statInfo;
		}
		public set billWayInfo(value: any) {
			this._billWayInfo = value;
		}
		public get billWayInfo(): any {
			return this._billWayInfo;
		}
		public set members(value: any) {
			this._mems = value;
		}
		public get members(): any {
			return this._mems;
		}

		public set me(value: any) {
			if(typeof(value["chips"]) != "number" ){
				value["chips"] = value["chips"].toNumber();
			}
			this._me = value;
		}
		public get me(): any {
			return this._me;
		}

		public set myBet(value: any) {
			this._myBet = value;
		}
		public get myBet(): any {
			return this._myBet;
		}
		public set lastBet(value: any) {
			this._lastBet = value;
		}
		public get lastBet(): any {
			return this._lastBet;
		}
		public set totalBet(value: any) {
			this._totalBet = value;
		}
		public get totalBet(): any {
			return this._totalBet;
		}

		public setMembers(mems: any): void {
			this._mems = mems;
			this._mems.forEach(m => {
				if (GameApp.PlayerInfo.isMySelf(m.playerId)) {
					this._me = m;
					return;
				}
			});
		}
	}
}