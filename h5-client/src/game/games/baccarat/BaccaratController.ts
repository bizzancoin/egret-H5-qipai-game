module baccarat {
	export class BaccaratController extends BaseController {
		private gameInfo: any;

		private resultMsg: any;
		private startMsg: any;

		private loadView: BaccaratLoad;
		private mainView: BaccaratView;

		private bankerChips: number;
		private playerChips: number;

		public constructor() {
			super();
			this.initData();

			this.loadView = new BaccaratLoad(this, LayerManager.UI_Main);
			App.ViewManager.register(ViewConst.BaccaratLoad, this.loadView);
			App.ViewManager.open(ViewConst.BaccaratLoad);

			this.bindFunctions();
		}
		private bindFunctions(): void {
			this.registerFunc(BaccaratConst.LoadCompleted, this.loadCompleted, this);
			this.registerFunc(BaccaratConst.RenderCompleted, this.renderCompleted, this);

			this.registerFunc(BaccaratConst.ExitTable, this.exitTable, this);

			this.registerFunc(BaccaratConst.DoBet, this.resDoBet, this);
			this.registerFunc(BaccaratConst.ClearBet, this.resClearBet, this);

			this.registerFunc(BaccaratConst.GameStateNotice, this.gameState, this);
			this.registerFunc(BaccaratConst.TableBetInfoNotice, this.tableBetInfo, this);
			this.registerFunc(BaccaratConst.PlayerChipNotice, this.changePlayerChip, this);
			this.registerFunc(BaccaratConst.GameStartNotice, this.gameStart, this);
			this.registerFunc(BaccaratConst.GameResultNotice, this.gameResult, this);
			this.registerFunc(BaccaratConst.GameBillNotice, this.gameBill, this);
			this.registerFunc(BaccaratConst.GameStatNotice, this.gameStat, this);
			this.registerFunc(BaccaratConst.GameWayBillNotice, this.gemtWayBill, this);

			this.registerFunc(BaccaratConst.GameShuffleNotice, this.gameShuffle, this);
			this.registerFunc(BaccaratConst.GameInfo, this.resGetGameInfo, this);
		}
		private unbindFunctions(): void {

		}

		private initData(): void {
			BaccaratMgr.setEnterScene(false);
			this.gameInfo = new Object();
			this.gameInfo['time'] = 0;
			this.gameInfo['state'] = 0;
			this.resultMsg = null;
			this.startMsg = null;
			this.bankerChips = 0;
			this.playerChips = 0;
			BaccaratMgr.bjlModel.myBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			BaccaratMgr.bjlModel.lastBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			BaccaratMgr.bjlModel.totalBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		}
		private loadCompleted(): void {
			this.mainView = new BaccaratView(this, LayerManager.UI_Main);
			App.ViewManager.register(ViewConst.Baccarat, this.mainView);
			App.ViewManager.open(ViewConst.Baccarat);
		}
		private renderCompleted(): void {
			baccarat.BaccaratReq.send_ReqGameInfo();
			App.ViewManager.close(ViewConst.BaccaratLoad);
		}
		private initViewData(): void {
			if (BaccaratMgr.isEnterScene()) {
				this.mainView.setPlayerInfo(BaccaratMgr.bjlModel.me);
				this.mainView.showDuihuan();
			}
			if (this.resultMsg) {
				this.gameResult(this.resultMsg);
				this.resultMsg = null;
			}
			if (this.startMsg) {
				this.gameStart(this.startMsg);
				this.startMsg = null;
			}
		}

		private gameState(time: number, state: number): void {
			this.gameInfo['time'] = time;
			this.gameInfo['state'] = state;
			this.renderGameState();
		}
		private renderGameState():void{
			let state = this.gameInfo['state'];
			if (BaccaratMgr.isEnterScene()) {
				this.mainView.setGameState(this.gameInfo['time'], this.gameInfo['state']);
				if(state == 3){
					for(let element of BaccaratMgr.bjlModel.myBet){
						if (element != 0){
							BaccaratMgr.bjlModel.lastBet = BaccaratMgr.bjlModel.myBet;
							break;
						}
					}
					BaccaratMgr.bjlModel.myBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				}
				if (state == 1) {
					this.mainView.setBetBtnState(BaccaratMgr.bjlModel.me.chips, 10000000, BaccaratMgr.bjlModel.lastBet, false);
					this.mainView.setBetAreaState(BaccaratMgr.bjlModel.statInfo ? BaccaratMgr.bjlModel.statInfo.score : 0, false);
				}
				else {
					this.mainView.setBetBtnState(BaccaratMgr.bjlModel.me.chips, 10000000, BaccaratMgr.bjlModel.lastBet, true);
					this.mainView.setBetAreaState(BaccaratMgr.bjlModel.statInfo ? BaccaratMgr.bjlModel.statInfo.score : 0, true);
				}
			}
		}

		private resGetGameInfo(msg: any): void {
			if(msg.cardIndex != this.mainView.cardIndex && this.mainView.cardIndex!=0 ){
				this.mainView.cardIndex = msg.cardIndex;
				this.mainView.clear(false);
				BaccaratMgr.bjlModel.myBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				if (msg.betInfos && msg.betInfos.length == 12) {
					for (let i = 0; i < BaccaratMgr.bjlModel.myBet.length; i++) {
						this.setMyBet(msg.betInfos[i], i)
					}
				}
			}
			BaccaratMgr.setEnterScene(true);
			
			if(BaccaratMgr.bjlModel.statInfo){
				this.mainView.setBillStat(BaccaratMgr.bjlModel.statInfo);
			}
			if(BaccaratMgr.bjlModel.billWayInfo){
				this.mainView.setWayBill(BaccaratMgr.bjlModel.billWayInfo);
			}
			this.mainView.setChipBtnText();

			let state = this.gameInfo['state'];
			if(state <3){
				this.mainView.tableBetInfo(BaccaratMgr.bjlModel.totalBet);
				this.mainView.gameBill(this.bankerChips, this.playerChips);
			}
			this.initViewData();
			this.renderGameState();
		}

		private exitTable(pid: number): void {
			this.unbindFunctions();
			this.mainView.clear(false);
			// BaccaratMgr.finish(true);
			LoginMgr.start();
		}
		private setMyBet(bet: any, area: number): void {
			if (typeof (bet) != "number") {
				bet = bet.toNumber();
			}
			BaccaratMgr.bjlModel.myBet[area] += bet;
			if (BaccaratMgr.isEnterScene()){
				for (let i = 8; i > 0; i--) {
					let chipNum = BaccaratMgr.bjlModel.roomInfo.betOptions[i-1];
					let mul = Math.floor(bet/chipNum);
					if(mul >= 1){
						for(let k = 0;k<mul;k++){
							this.mainView.doBet(chipNum, area, BaccaratMgr.bjlModel.myBet[area]);
						}
						bet = bet - mul * chipNum;
					}
				}
			}
		}
		private resDoBet(bet: any, area: number): void {
			if (typeof (bet) != "number") {
				bet = bet.toNumber();
			}
			BaccaratMgr.bjlModel.myBet[area] += bet;
			BaccaratMgr.bjlModel.totalBet[area] += bet;
			if (BaccaratMgr.isEnterScene()){
				for (let i = 8; i > 0; i--) {
					let chipNum = BaccaratMgr.bjlModel.roomInfo.betOptions[i-1];
					let mul = Math.floor(bet/chipNum);
					if(mul >= 1){
						for(let k = 0;k<mul;k++){
							this.mainView.doBet(chipNum, area, BaccaratMgr.bjlModel.myBet[area]);
						}
						bet = bet - mul * chipNum;
					}
				}
				this.mainView.updateTableBetInfo();
			}
		}
		private resClearBet(): void {
			for (let i = 0; i < BaccaratMgr.bjlModel.myBet.length; i++) {
				BaccaratMgr.bjlModel.totalBet[i] = BaccaratMgr.bjlModel.totalBet[i] - BaccaratMgr.bjlModel.myBet[i];
			}
			BaccaratMgr.bjlModel.myBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			if (BaccaratMgr.isEnterScene())
				this.mainView.clearBet();
		}
		private tableBetInfo(betinfo: any): void {
			if(betinfo.length == 0){
				BaccaratMgr.bjlModel.totalBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			}
			betinfo.forEach(bi => {
				BaccaratMgr.bjlModel.totalBet[bi.area] = bi.chips.toNumber();
			});
			if (BaccaratMgr.isEnterScene())
				this.mainView.tableBetInfo(BaccaratMgr.bjlModel.totalBet);
		}
		private changePlayerChip(pid: any, chips: number): void {
			if (BaccaratMgr.bjlModel.me && pid.toNumber() == BaccaratMgr.bjlModel.me['playerId'].toNumber()) {
				BaccaratMgr.bjlModel.me["chips"] = chips;
				if (BaccaratMgr.isEnterScene()) {
					if (this.gameInfo['state'] != 2 && this.gameInfo['state'] != 3) {
						this.mainView.setPlayerInfo(BaccaratMgr.bjlModel.me);
					}
					if (this.gameInfo['state'] == 1) {
						this.mainView.setBetBtnState(BaccaratMgr.bjlModel.me.chips, 10000000, BaccaratMgr.bjlModel.lastBet, false);
						this.mainView.setBetAreaState(BaccaratMgr.bjlModel.statInfo ? BaccaratMgr.bjlModel.statInfo.score : 0, false);
					}
				}
			}
		}

		private gameStart(msg: any): void {
			if (BaccaratMgr.isEnterScene() && msg) {
				if(this.gameInfo['time']>18){
					this.mainView.gameStartProcess(msg.cardsInfo, msg.cardIndex, msg.yellow == 1);
				}
				else{
					this.mainView.gameStartNow(msg.cardsInfo, msg.cardIndex, msg.yellow == 1);
				}
			} else {
				this.startMsg = msg;
			}
		}
		private gameResult(msg: any): void {
			if (BaccaratMgr.isEnterScene()) {
				this.mainView.gameResult(msg.result);
				BaccaratMgr.bjlModel.totalBet = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			} else {
				this.resultMsg = msg;
			}
		}
		private gameBill(msg: any): void {
			this.bankerChips = msg.bankerChips;
			this.playerChips = msg.playerChips;
			if (BaccaratMgr.isEnterScene())
			this.mainView.gameBill(msg.bankerChips, msg.playerChips);
		}
		private gameStat(msg: any): void {
			BaccaratMgr.bjlModel.statInfo = msg;
			if (BaccaratMgr.isEnterScene()) {
				this.mainView.setBillStat(BaccaratMgr.bjlModel.statInfo);
			}
		}
		private gemtWayBill(msg: any): void {
			BaccaratMgr.bjlModel.billWayInfo = msg;
			//if (BaccaratMgr.isEnterScene())
				//this.mainView.setWayBill(BaccaratMgr.bjlModel.billWayInfo);
		}
		private gameShuffle(msg: any): void {
			if (msg.cardsInfo && BaccaratMgr.isEnterScene())
				this.mainView.showShuffle(msg.cardsInfo)
		}
	}
}