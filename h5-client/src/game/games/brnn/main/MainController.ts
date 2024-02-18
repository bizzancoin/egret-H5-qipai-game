module brnn {
	export class MainController extends BaseController {
		private mainView: MainView;
		public constructor() {
			super();
			this.mainView = new MainView(this, LayerManager.UI_Main);
			App.ViewManager.register(ViewConst.BrnnMain, this.mainView);

			this.registerResListener();
		}

		private registerResListener(): void {

			this.registerFunc(BrnnResConst.ResExitRoom, (msg) => {
				this.mainView.finish();
				LoginMgr.start();
			}, this);

			// {playerId, chips}
			this.registerFunc(BrnnResConst.ResChangeChips, (msg) => {
				if (BrnnMgr.mainMoel.state != 3) {
					this.mainView.updateChips(false);
					this.mainView.updateWaitView();
				}
			}, this);

			// {mem}
			this.registerFunc(BrnnResConst.ResOtherEnterTable, (mem) => {
				// doNothing
			}, this);

			// {id, playerId, order}
			this.registerFunc(BrnnResConst.ResExitTable, (msg) => {
				if (msg.order == BrnnMgr.mainMoel.seatOrder) {
					
				}
			}, this);

			this.registerFunc(BrnnResConst.ResApplyZuoZLst, () => {
				this.mainView.updateWaitView();
			}, this);

			this.registerFunc(BrnnResConst.ResHistory, () => {
				this.mainView.updateRecordView();
			}, this);

			// [{id:0-4 0 庄家牌 cardsType, cards:[1,2,3,4,5]}]
			this.registerFunc(BrnnResConst.ResSendCard, (cardsInfo) => {
				// 发牌音效
				BrnnMgr.playSound(1);
				cardsInfo.sort((a, b) => {
					return a.id - b.id;
				})
				this.mainView.sendCardsStage(cardsInfo);
			}, this);

			//{area, chip}
			this.registerFunc(BrnnResConst.ResBet, (betInfo) => {
				if (betInfo) {
					if (BrnnMgr.mainMoel.totalBetChipsMap[betInfo.area]) {
						BrnnMgr.mainMoel.totalBetChipsMap[betInfo.area] += betInfo.chips.toNumber();
					} else {
						BrnnMgr.mainMoel.totalBetChipsMap[betInfo.area] = betInfo.chips.toNumber();
					}
					if (BrnnMgr.mainMoel.curBetChipsMap[betInfo.area]) {
						BrnnMgr.mainMoel.curBetChipsMap[betInfo.area] += betInfo.chips.toNumber();
					} else {
						BrnnMgr.mainMoel.curBetChipsMap[betInfo.area] = betInfo.chips.toNumber();
					}
					this.mainView.addBetChips(betInfo.area, betInfo.chips.toNumber(), true);
					this.mainView.updateChips(false);
					this.mainView.updateTotalBetChips();
					this.mainView.updatePlayerBetChips();
				}
			}, this);

			//[betInfo:{area, chips}]
			this.registerFunc(BrnnResConst.ResTableBet, (betInfo) => {
				if (betInfo && betInfo.length > 0) {
					betInfo.forEach(e => {
						if (BrnnMgr.mainMoel.totalBetChipsMap[e.area]) {
							let addBetChips = e.chips.toNumber() - BrnnMgr.mainMoel.totalBetChipsMap[e.area];
							BrnnMgr.mainMoel.totalBetChipsMap[e.area] = e.chips.toNumber();
							if (addBetChips > 0) {
								this.mainView.addBetChips(e.area, addBetChips, false);
							} else if (addBetChips < 0) {
								//this.mainView.minusBetChips(e.area, Math.abs(addBetChips));
							}
						} else {
							BrnnMgr.mainMoel.totalBetChipsMap[e.area] = e.chips.toNumber();
							if (BrnnMgr.mainMoel.curBetChipsMap[e.area]) {
								let addBetChips = e.chips.toNumber() - BrnnMgr.mainMoel.curBetChipsMap[e.area];
								if (addBetChips > 0) {
									this.mainView.addBetChips(e.area, addBetChips, false);
								}
							} else {
								this.mainView.addBetChips(e.area, e.chips.toNumber(), false);
							}
						}
					});
				} else {
					BrnnMgr.mainMoel.totalBetChipsMap = {};
					BrnnMgr.mainMoel.curBetChipsMap = {};
				}
				this.mainView.updateTotalBetChips();
				this.mainView.updatePlayerBetChips();
			}, this);

			//{playerId, name, sex, chips, num, score}
			this.registerFunc(BrnnResConst.ResNewDealer, (banker) => {
				if (BrnnMgr.mainMoel.state != 3) {
					this.mainView.updateBankerInfo();
				}
			}, this);

			// {bankerChips, playerChips}
			this.registerFunc(BrnnResConst.ResBill, (billInfo) => {
				BrnnMgr.mainMoel.billInfo = billInfo;
			}, this);

			this.registerFunc(BrnnResConst.ResClearBet, (msg) => {
				this.mainView.clearBetChips();
			}, this);

			// 游戏阶段 {state, time} 1:休息,2:下注, 3:发牌
			this.registerFunc(BrnnResConst.ResGameStage, (stage) => {
				this.mainView.resetGame();
			}, this);

			this.registerFunc(BrnnResConst.ResBackTable, (msg) => {
				BrnnMgr.setEnterScene(true);
				this.mainView.pauseGame(false);
				if (msg.room) {
					BrnnMgr.setRoomInfo(msg.room); // 房间信息
					msg.mems.forEach(mem => {	              // 成员信息
						BrnnMgr.mainMoel.addMember(mem);
					});
					if (msg.gameInfo) {
						// 游戏阶段信息
						BrnnMgr.mainMoel.gameStage = {
							state: msg.gameInfo.stage,
							time: msg.gameInfo.stageTime.toNumber()
						}
						BrnnMgr.mainMoel.histories = msg.gameInfo.historyList;  // 历史记录
						BrnnMgr.mainMoel.applicants = msg.gameInfo.bankerApplicants; // 申请列表
						BrnnMgr.mainMoel.banker = msg.gameInfo.bankerInfo; // 申请列表
						if (BrnnMgr.mainMoel.state != 1) {
							this.mainView.resetUI();
							// 恢复筹码信息
							if (msg.gameInfo.myBetInfos && msg.gameInfo.myBetInfos.length > 0) {
								this.mainView.clearBetChips(); // 清除筹码信息
								BrnnMgr.mainMoel.curBetChipsMap = {};
								BrnnMgr.mainMoel.totalBetChipsMap = {};
								msg.gameInfo.myBetInfos.forEach(e => {
									if (BrnnMgr.mainMoel.totalBetChipsMap[e.area]) {
										BrnnMgr.mainMoel.totalBetChipsMap[e.area] += e.chips.toNumber();
									} else {
										BrnnMgr.mainMoel.totalBetChipsMap[e.area] = e.chips.toNumber();
									}
									if (BrnnMgr.mainMoel.curBetChipsMap[e.area]) {
										BrnnMgr.mainMoel.curBetChipsMap[e.area] += e.chips.toNumber();
									} else {
										BrnnMgr.mainMoel.curBetChipsMap[e.area] = e.chips.toNumber();
									}
									this.mainView.addBetChips(e.area, e.chips.toNumber(), true);
								});
								this.mainView.updateTotalBetChips();
								this.mainView.updatePlayerBetChips();
							}
							if(BrnnMgr.mainMoel.isBet() || BrnnMgr.mainMoel.state == 2) {
							// 恢复筹码信息
								if (msg.gameInfo.tableBetInfos && msg.gameInfo.tableBetInfos.length > 0) {
									msg.gameInfo.tableBetInfos.forEach(e => {
										if (BrnnMgr.mainMoel.totalBetChipsMap[e.area]) {
											let addBetChips = e.chips.toNumber() - BrnnMgr.mainMoel.totalBetChipsMap[e.area];
											BrnnMgr.mainMoel.totalBetChipsMap[e.area] = e.chips.toNumber();
											if (addBetChips > 0) {
												this.mainView.addBetChips(e.area, e.chips.toNumber(), false);
											}
										} else {
											BrnnMgr.mainMoel.totalBetChipsMap[e.area] = e.chips.toNumber();
											this.mainView.addBetChips(e.area, e.chips.toNumber(), false);
										}
									});
									this.mainView.updateTotalBetChips();
									this.mainView.updatePlayerBetChips();
								}
							}
						}
						this.mainView.backTable();
						// 开奖阶段
						if (BrnnMgr.mainMoel.state == 3) {
							if (msg.gameInfo.cardsInfo) {
								msg.gameInfo.cardsInfo.sort((a, b) => {
									return a.id - b.id;
								});
								// 直接开
								if (msg.gameInfo.balanceInfo) {
									if (BrnnMgr.mainMoel.isBet()) { // 我有下注，显示牌局信息，否则不处理
										BrnnMgr.mainMoel.billInfo = msg.gameInfo.balanceInfo;
										this.mainView.showCards(msg.gameInfo.cardsInfo, BrnnMgr.mainMoel.countDown);
									}
								}
							}
						}
						this.mainView.updateWaitView();
						this.mainView.updateRecordView();
					} else {
						this.mainView.backTable();
					}
				} else {
					LoginMgr.start();
				}
			}, this);

		}
	}
}