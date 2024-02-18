module brnn {

	export class MainView extends BaseEuiView {

		private btnExit: eui.Button;
		private btnHelp: eui.Button;
		private btnHistory: eui.Button;
		private btnSet: eui.Button;
		private btnExchange: eui.Button;
		private btnClear: eui.Button;
		private btnContinue: eui.Button;
		private btnSZLst: eui.Button; // 上庄列表
		private groupHelp: eui.Group;
		private groupDealer: eui.Group;
		private lbNick: eui.Label;
		private lbGold: eui.Label;
		private imgHead: eui.Image;
		private lbDealerGold: eui.Label;
		private lbDealerNick: eui.Label;
		private imgDealer: eui.Image;
		// 下注按钮
		private betBtn: BetBtn;
		// 发牌起点
		private cardStartRect: eui.Group;
		// 五副牌
		private groupCards: eui.Group;
		private cardItemList: any; // 牌

		// 筹码区域 (四个区域)
		private groupChips: eui.Group;
		private betAreas: any;

		private clearingView: ClearingView;
		private timer: egret.Timer;

		private bmpClock: eui.BitmapLabel;
		private imgState: eui.Image;
		//private imgTimer: eui.Image;
		private groupCountdown: eui.Group;

		private recordView: RecordView;
		private waitView: WaitView;
		private exchangeView: game.ExchangeView;
		private setView: game.SetView;

		private sendCards = [];



		private chipsRes = ['1', '10', '100', '1000', '10000', '100000', '1000000', '10000000'];
		/// 四个区域下注的每种类型筹码个数
		private areaNumLst = {
			'1': [0, 0, 0, 0, 0, 0, 0, 0],
			'2': [0, 0, 0, 0, 0, 0, 0, 0],
			'3': [0, 0, 0, 0, 0, 0, 0, 0],
			'4': [0, 0, 0, 0, 0, 0, 0, 0],
		}

		private cardItemListPos = {
			'1': { x: 127.5, y: 70 },
			'2': { x: 408.5, y: 70 },
			'3': { x: 688.5, y: 70 },
			'4': { x: 967.5, y: 70 },
			'5': { x: 545.5, y: -510 }
		}

		private binTable = [
			[0, 0, 0, 0], [0, 0, 0, 1], [0, 0, 1, 0], [0, 0, 1, 1],
			[0, 1, 0, 0], [0, 1, 0, 1], [0, 1, 1, 0], [0, 1, 1, 1],
			[1, 0, 0, 0], [1, 0, 0, 1], [1, 0, 1, 0], [1, 0, 1, 1],
			[1, 1, 0, 0], [1, 1, 0, 1], [1, 1, 1, 0], [1, 1, 1, 1],
		]

		private playerChipsPos = [
			{ x: 30, y: 647 },
			{ x: 1858, y: 868 },
			{ x: 1890, y: 440 }
		];

		private chipsLst: any;


		public constructor($controller, $parent) {
			super($controller, $parent);
			this.skinName = 'resource/skins/brnn/BrnnMainSkin.exml';
			this.chipsLst = {};
		}

		public initUI(): void {
			super.initUI();
			this.registerEvent();
			this.createBetAreas();
			this.createCardItems();
			this.imgState.visible = false;
			this.bmpClock.text = '';
			this.bmpClock.visible = false;
			BrnnMgr.ObjectPool.init();
		}

		public initData(): void {
			super.initData();
			BrnnMgr.ObjectPool.registerObjectCreater(ObjectClassType.Chips, BrnnMgr.ChipsManager);
			this.chipsRes.forEach(e => {
				BrnnMgr.ObjectPool.newSomeObject(ObjectClassType.Chips, e, 20);
			});
			BrnnMgr.ObjectPool.registerObjectCreater(ObjectClassType.BigCards, BrnnMgr.BigCard);
			for (let p in BrnnConfig.CardData) {
				BrnnMgr.ObjectPool.newSomeObject(ObjectClassType.BigCards, p, 2);
			}
			BrnnMgr.ObjectPool.registerObjectCreater(ObjectClassType.SmallCards, BrnnMgr.SmallCard);
			for (let p in BrnnConfig.CardData) {
				BrnnMgr.ObjectPool.newSomeObject(ObjectClassType.SmallCards, p, 2);
			}
		}

		public open(...param: any[]) {
			super.open(param);
			BrnnReq.send_ReqBackTable();
		}

		private registerEvent(): void {
			this.recordView.registerTouchEvent();
			this.recordView.hide();
			this.clearingView.hide();
			this.exchangeView.hide();
			this.setView.show(false);
			this.setView.registerEvent(()=>{
				App.SoundManager.playBg(BrnnConfig.MusicConfig.MusicTable['1'].resPath);
			}, this);
			this.betBtn.visible = false;
			this.waitView.visible = false;
			this.groupCountdown.visible = false;
			this.waitView.init((isApply: boolean) => {
				if (isApply) {
					if (BrnnMgr.mainMoel.applicants && BrnnMgr.mainMoel.applicants.length >= 4) {
						GameApp.PromotManager.floatingTip('对不起，当前庄家列队已满，请您稍后再试。');
					} else {
						if(!BrnnMgr.mainMoel.isChipsEnoughForApplyBanker()) {
							GameApp.PromotManager.floatingTip('上庄筹码为：'+BrnnMgr.mainMoel.roomInfo.beBankerChips+'，您的筹码不足');
						} else {
							BrnnReq.send_ReqApplyZuoZ();
						}
					}
				} else {
					if (BrnnMgr.mainMoel.isBanker()) {
						if (BrnnMgr.mainMoel.state == 1 ) {
							BrnnReq.send_ReqApplyCancelZuoZ();
						} else {
							GameApp.PromotManager.oneButtonTip('请在休息阶段下庄');
						}
					} else {
						BrnnReq.send_ReqCancelZuoZ();
					}
				}
			}, this);
			this.exchangeView.registerEvent((exchangeType: game.ExchangeType, chips: number)=>{
				if(chips <= 0) {
					GameApp.PromotManager.floatingTip('兑换筹码必须大于0');
					return;
				}
				switch(exchangeType) {
					case game.ExchangeType.CashIn:   // 下分
						if (BrnnMgr.mainMoel.state != 2) {
							BrnnReq.send_ReqExchangeGolds(chips);
						} else {
							GameApp.PromotManager.oneButtonTip("游戏阶段不能下分");
						}
						break;
					case game.ExchangeType.CashOut:  // 上分
						BrnnReq.send_ReqExchangeChips(chips);
						break;
				}
			}, this);
			this.btnExit.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnHelp.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnHistory.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnSet.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnExchange.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnClear.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnContinue.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.btnSZLst.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.groupHelp.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			this.betBtn.registerListener();
			this.betBtn.setOnBetHandler(this.onBetHandler, this);
			// 1 秒运行一次
			this.timer = new egret.Timer(1000);
			this.timer.addEventListener(egret.TimerEvent.TIMER, this.updateCountdownAndState, this);
			this.timer.start();
		}

		// 重置游戏
		public resetGame(): void {
			this.calculateContinue();
			this.betBtn.visible = false;
			this.btnClear.enabled = false;
			this.btnExchange.enabled = false;
			if (BrnnMgr.mainMoel.state == 1) { // 休息阶段
				this.groupCountdown.visible = true;
				this.btnExchange.enabled = true;
				BrnnMgr.mainMoel.lastBetChips = GameApp.clone(BrnnMgr.mainMoel.curBetChipsMap);
				this.resetUI();
			} else if (BrnnMgr.mainMoel.state == 2) { // 下注阶段
				this.clearingView.hide();
				this.btnExchange.enabled = true;
				BrnnMgr.mainMoel.selectedChipIdx = null;
				// 开始下注音效
				if (BrnnMgr.mainMoel.countDown > 5) {
					BrnnMgr.playSound(8);
				}
				if (BrnnMgr.mainMoel.isBanker()) {
					this.betBtn.visible = false
					this.groupCountdown.visible = false;
				} else {
					this.betBtn.visible = true;
					this.btnClear.enabled = BrnnMgr.mainMoel.isBet();
					this.groupCountdown.visible = true;
				}
			} else if (BrnnMgr.mainMoel.state == 3) { // 开奖阶段
				this.groupCountdown.visible = true;
				BrnnMgr.mainMoel.lastBetChips = {};
			}
			if (BrnnMgr.mainMoel.state) {
				this.imgState.texture = RES.getRes('brnn_atlas1_json.bairenniuniu_state_' + BrnnMgr.mainMoel.state);
				this.imgState.visible = true;
			}
			this.bmpClock.text = BrnnMgr.mainMoel.countDown;
			this.bmpClock.visible = true;
		}

		public resetUI(): void {
			this.removeSendCard();
			// 清除牌
			for (var p in this.cardItemList) {
				this.cardItemList[p].reset();
			}
			this.areaNumLst = {
				'1': [0, 0, 0, 0, 0, 0, 0, 0],
				'2': [0, 0, 0, 0, 0, 0, 0, 0],
				'3': [0, 0, 0, 0, 0, 0, 0, 0],
				'4': [0, 0, 0, 0, 0, 0, 0, 0],
			}
			// 清除筹码信息
			for (var p in this.chipsLst) {
				let imgs = this.chipsLst[p];
				if (imgs && imgs.length > 0) {
					imgs.forEach((e: Chip) => {
						e.resumeOrgin();
					});
				}
			}
			this.chipsLst = {};
			for (var p in this.betAreas) {
				let betArea: BetArea = this.betAreas[p];
				betArea.reset();
			}
			// 移除结算面板
			this.clearingView.hide();
			BrnnMgr.mainMoel.totalBetChipsMap = {};
			BrnnMgr.mainMoel.curBetChipsMap = {};
			// 清除筹码信息
			this.updateTotalBetChips();
			this.updatePlayerBetChips();
		}

		// 创建五副牌
		private createCardItems(): void {
			this.cardItemList = {};
			for (let i = 1; i <= 5; i++) {
				let cardItem = new CardItem(i);
				cardItem.anchorOffsetX = cardItem.width / 2;
				cardItem.anchorOffsetY = cardItem.height / 2;
				let pos = this.cardItemListPos[i];
				cardItem.x = pos.x;
				cardItem.y = pos.y;
				this.groupCards.addChild(cardItem);
				this.cardItemList[i] = cardItem;
				cardItem.reset();
			}
		}

		private createBetAreas(): void {
			this.betAreas = {};
			for (let i = 1; i <= 4; i++) {
				let betArea = new BetArea();
				betArea.anchorOffsetX = betArea.width / 2;
				betArea.anchorOffsetY = betArea.height / 2;
				let pos = this.cardItemListPos[i];
				betArea.x = pos.x;
				betArea.y = betArea.height / 2;
				this.groupChips.addChild(betArea);
				this.betAreas[i] = betArea;
				betArea.registerEvent(i, (area) => {
					// 下注
					if (BrnnMgr.mainMoel.state == 2 && BrnnMgr.mainMoel.selectedChipIdx) {
						let index = BrnnMgr.mainMoel.selectedChipIdx - 1;
						BrnnReq.send_ReqBet(area, BrnnMgr.mainMoel.roomInfo.betOptions[index]);
					}
				}, this)
			}
		}

		private onClickHandler(evt: egret.TouchEvent): void {
			switch (evt.target) {
				case this.btnExit:
					if (BrnnMgr.mainMoel.isBet() ) {
						if(BrnnMgr.mainMoel.state == 1) {
							BrnnReq.send_ReqExitTable();
						} else {
							GameApp.PromotManager.floatingTip("游戏进行中，请在休息阶段退出");
						}
					} else {
						BrnnReq.send_ReqExitTable();
					}
					break;
				case this.btnHelp:
					this.groupHelp.visible = true;
					break;
				case this.btnHistory:
					this.recordView.show(BrnnMgr.mainMoel.histories);
					break;
				case this.btnSet:
					this.setView.show(true);
					break;
				case this.btnExchange:
					this.exchangeView.show(GameApp.PlayerInfo.safeGold, BrnnMgr.mainMoel.chips, BrnnMgr.mainMoel.roomInfo);
					break;
				case this.btnClear:
					BrnnReq.send_ReqClearBet();
					break;
				case this.btnContinue:
					// 续押
					this.continueBet()
					break;
				case this.btnSZLst:
					this.waitView.show(BrnnMgr.mainMoel.applyList(), BrnnMgr.mainMoel.isApply());
					break;
				case this.groupHelp:
					this.groupHelp.visible = false;
					break;
			}
		}

		private onBetHandler(idx: number): void {
			BrnnMgr.mainMoel.selectedChipIdx = idx;
		}

		// 发牌阶段
		public sendCardsStage(tipsCards: any): void {
			// this.groupCountdown.visible = false;
			// 设置牌的信息
			for (let i = 0; i < tipsCards.length; i++) {
				this.cardItemList[i + 1].setCardInfo(tipsCards[i]);
			}
			let self = this;
			// 发牌
			let sendCardCallBack = () => {
				for (let i = 1; i <= 5; i++) {
					let sendCard = () => {
						self.cardItemList[i].addCard();
						BrnnMgr.playSound(5);
					}
					let endPos = self.cardItemList[i].parent.localToGlobal(self.cardItemList[i].x, self.cardItemList[i].y);
					egret.Tween.get(self).wait(100 * (i - 1)).call(() => {
						self.showSendHandCardAction(endPos, sendCard);
					});
				}
			}

			// 翻牌动画
			let flopCardCallBack = () => {
				for (let i = 1; i <= 5; i++) {
					egret.Tween.get(self).wait(2400 * (i - 1)).call(() => {
						self.cardItemList[i].playFlopAnimation();
					});
				}
			}
			egret.Tween.get(self).wait(0).call(()=>{
				for (let i = 1; i <= 5; i++) {
					egret.Tween.get(self).wait(500 * (i - 1)).call(() => {
						sendCardCallBack();
					});
				}
			}).wait(3000).call(()=>{
				flopCardCallBack();
			}).wait(12000).call(()=>{
				self.playBlinkAnimation();
			}).wait(1000).call(()=>{
				self.openClearingView();
			}).wait(1000).call(()=>{
				self.playBankerAnimation();
			});
		}

		// 发牌
		private showSendHandCardAction(endPos: any, callback: Function): void {
			let imgCard: eui.Image = new eui.Image();
			this.sendCards.push(imgCard);
			imgCard.texture = RES.getRes(BrnnConfig.CardData[54].card_big);
			imgCard.anchorOffsetX = imgCard.width / 2;
			imgCard.anchorOffsetY = imgCard.height / 2;
			let startPos = this.cardStartRect.parent.localToGlobal(this.cardStartRect.x, this.cardStartRect.y);
			imgCard.x = startPos.x;
			imgCard.y = startPos.y;
			this.addChild(imgCard);
			egret.Tween.get(imgCard).to({ x: endPos.x, y: endPos.y }, 200).call(function () {
				if (imgCard.parent) {
					imgCard.parent.removeChild(imgCard);
				}
				callback();
			}, this);
		}

		private playBlinkAnimation(): void {
			if (BrnnMgr.mainMoel.histories && BrnnMgr.mainMoel.histories.length > 0) {
				let record = BrnnMgr.mainMoel.histories[0];
				let data = this.binTable[record];
				for (let i = 0; i < data.length; i++) {
					this.betAreas[i + 1].setWin(data[i] > 0);
				}
			}
		}

		// 更新筹码
		public updateChips(updateInfo: boolean): void {
			if (updateInfo || BrnnMgr.mainMoel.state != 3) {
				this.updatePlayerInfo();
			} 
			this.updateBtnChipsState();
		}

		private updatePlayerInfo(): void {
			this.lbGold.text = BrnnMgr.mainMoel.chips;
			this.lbNick.text = GameApp.PlayerInfo.nickName;
			this.imgHead.texture = GameApp.PlayerInfo.getHeadTexture();
		}

		// 根据当前筹码更新筹码按钮状态
		private updateBtnChipsState(): void {
			this.betBtn.updateBtnStatus(BrnnMgr.mainMoel.chips);
			this.calculateContinue();
		}

		// 判断当前筹码是否可以续押（徐亚按钮按钮）
		private calculateContinue(): void {
			// 不是庄家，且在押注阶段
			if (!BrnnMgr.mainMoel.isBanker() && BrnnMgr.mainMoel.state == 2) {
				if (BrnnMgr.mainMoel.isCanContinue()) {
					this.btnContinue.enabled = true;
				} else {
					this.btnContinue.enabled = false;
				}
			} else {
				this.btnContinue.enabled = false;
			}
		}

		// 添加筹码（下注信息）
		public addBetChips(area: number, chips: number, isMyBet: boolean): void {
			BrnnMgr.playSound(4);
			let betOptions = BrnnMgr.mainMoel.roomInfo.betOptions
			let length = betOptions.length;
			for(let i = length - 1; i >= 0; i--) {
				let num = chips / betOptions[i];
				num = Math.floor(num);
				if(num > 0) {
					chips = chips % betOptions[i];
					for(let k=0; k<num; k++) {
					let betArea: BetArea = this.betAreas[area];
					let pos = betArea.parent.localToGlobal(betArea.x, betArea.y);
					this.createChips(area, i, isMyBet, pos);
				 }
				}
			}

			this.btnClear.enabled = BrnnMgr.mainMoel.isBet();
		}

		private createChips(area, i, isMyBet, pos): void {
			if (this.areaNumLst[area][i] < 15 || isMyBet) {
							// 筹码结束位置
				let endPos = {
					x: Math.random() * 140 + pos.x - 70,
					y: Math.random() * 140 + pos.y - 100
				}
				let chipStartPos: any;
				if (isMyBet) {
					chipStartPos = this.betBtn.parent.localToGlobal(this.betBtn.x, this.betBtn.y);
					chipStartPos = this.groupChips.globalToLocal(chipStartPos.x, chipStartPos.y);
				} else {
					let idx = Math.floor(Math.random() * 3);
					chipStartPos = this.playerChipsPos[idx];
				}
				let imgChip = BrnnMgr.ChipsManager.get(this.chipsRes[i], chipStartPos);
				imgChip.tag = isMyBet ? 'mybet' : 'totalBet';
				this.groupChips.addChild(imgChip);
				if (!this.chipsLst[area]) {
					this.chipsLst[area] = [];
				}
				this.chipsLst[area].push(imgChip);
				endPos = this.groupChips.globalToLocal(endPos.x, endPos.y);
				egret.Tween.get(imgChip).to({ x: endPos.x, y: endPos.y }, 300);
				this.areaNumLst[area][i] += 1;
			}
		}

		public minusBetChips(area: number, chips: number): void {
			let numArr: string[] = BrnnMgr.gsplit(chips + '');
			if (numArr) {
				numArr.reverse();
				for (let i = 0; i < numArr.length; i++) {
					let v = parseInt(numArr[i]);
					let chipName = this.chipsRes[i];
					let imgChips: Chip[] = this.chipsLst[area];
					if (imgChips && imgChips.length > 0) {
						for (let k = 0; k < v; k++) {
							for (let j = 0; j < imgChips.length; j++) {
								if (imgChips[j].getDataId() == chipName) {
									imgChips[j].resumeOrgin();
									break;
								}
							}
						}
					}

				}
			}
		}

		public updateTotalBetChips(): void {
			for (let area = 1; area <= 4; area++) {
				if (BrnnMgr.mainMoel.totalBetChipsMap[area] > 0) {
					this.betAreas[area].setTotalBetChips(BrnnMgr.mainMoel.totalBetChipsMap[area]);
				} else {
					this.betAreas[area].setTotalBetChips(0);
				}
			}
		}

		public updatePlayerBetChips(): void {
			for (let area = 1; area <= 4; area++) {
				if (BrnnMgr.mainMoel.curBetChipsMap[area] > 0) {
					this.betAreas[area].setMyBetChips(BrnnMgr.mainMoel.curBetChipsMap[area]);
				} else {
					this.betAreas[area].setMyBetChips(0);
				}
			}
		}

		public clearBetChips(): void {
			for (let i = 1; i <= 4; i++) {
				if (BrnnMgr.mainMoel.curBetChipsMap[i]) {
					BrnnMgr.mainMoel.totalBetChipsMap[i] -= BrnnMgr.mainMoel.curBetChipsMap[i];
					BrnnMgr.mainMoel.curBetChipsMap[i] = 0;
				}
			}
			BrnnMgr.mainMoel.curBetChipsMap = {};

			for (let area = 1; area <= 4; area++) {
				if (this.chipsLst[area]) {
					let imgChips: Chip[] = this.chipsLst[area];
					for (let i = 0; i < imgChips.length; i++) {
						if (imgChips[i].tag == 'mybet') {
							imgChips[i].resumeOrgin();
						}
					}
				}
			}
			this.btnClear.enabled = BrnnMgr.mainMoel.isBet();
			this.updateTotalBetChips();
			this.updatePlayerBetChips();
			this.updatePlayerInfo();
		}

		// 更新庄家信息
		public updateBankerInfo(): void {
			if(this.waitView.visible) {
				this.waitView.show(BrnnMgr.mainMoel.applyList(), BrnnMgr.mainMoel.isApply());
			}
			if (BrnnMgr.mainMoel.banker) {
				this.lbDealerNick.text = BrnnMgr.mainMoel.banker.name;
				if (BrnnMgr.mainMoel.banker.playerId > 0) {
					//this.imgHead.texture = PlayerRes
					if(GameApp.PlayerInfo.isMySelf(BrnnMgr.mainMoel.banker.playerId)) {
						this.imgDealer.texture = GameApp.PlayerInfo.getHeadTexture();
						this.imgDealer.scaleX = 0.5;
						this.imgDealer.scaleY = 0.5;
					} else {
						let icon = BrnnMgr.mainMoel.getBankerIcon();
						let texture: egret.Texture = null;
						if(icon) {
							texture = GameApp.PlayerInfo.getHeadTexture(icon);
						} 
						if(texture) {
							this.imgDealer.texture = texture;
							this.imgDealer.scaleX = 0.5;
							this.imgDealer.scaleY = 0.5;
						} else {
							this.imgDealer.texture = RES.getRes('brnn_atlas1_json.tx');
							this.imgDealer.scaleX = 1;
							this.imgDealer.scaleY = 1;
						}
					}
					this.lbDealerGold.text = BrnnMgr.mainMoel.banker.chips;
				} else {
					this.imgDealer.texture = RES.getRes('brnn_atlas1_json.banker');
					this.lbDealerGold.text = BrnnMgr.mainMoel.banker.score;
					this.imgDealer.scaleX = 1;
				    this.imgDealer.scaleY = 1;
				}
			}
		}

		public updateWaitView(): void {
			if (this.waitView.visible) {
				this.waitView.show(BrnnMgr.mainMoel.applyList(), BrnnMgr.mainMoel.isApply());
			}
		}

		public updateRecordView(): void {
			if (this.recordView.visible) {
				this.recordView.show(BrnnMgr.mainMoel.histories);
			}
		}

		// 结算
		private openClearingView(): void {
			if (BrnnMgr.mainMoel.billInfo) {
				this.clearingView.show(BrnnMgr.mainMoel.billInfo.bankerChips, BrnnMgr.mainMoel.billInfo.playerChips);
				egret.Tween.get(this).wait(1000).call(() => {
					this.clearChips();
				}, this).wait(1000).call(() => {
					this.updateChips(true);
					this.updateBankerInfo();
				}, this);
			}
		}

		// 庄家通吃或者通赔动画
		private playBankerAnimation(): void {
			if (BrnnMgr.mainMoel.histories && BrnnMgr.mainMoel.histories.length > 0) {
				let curRecord = BrnnMgr.mainMoel.histories[0];
				let key = '';
				if (curRecord == 15) { // 通吃
					BrnnMgr.playSound(2);
					key = '庄通赔';
				} else if (curRecord == 0) { // 通赔
					key = '庄通吃';
					BrnnMgr.playSound(3);
				}
				let effData = BrnnConfig.EffectConfig[key];
				if (effData) {
					let mc = GameApp.getClipByFileName(effData.file, effData.key);
					GameApp.playMoveClip(this, mc, 'act', { x: 1480, y: 270 }, 1, function () {
						if (mc.parent) {
							mc.parent.removeChild(mc);
						}
					}, this);
				}
			}
		}

		// 清除筹码
		private clearChips(): void {
			if (!this.chipsLst) {
				return;
			}
			let self = this;
			if (BrnnMgr.mainMoel.histories && BrnnMgr.mainMoel.histories.length > 0) {
				let curRecord = BrnnMgr.mainMoel.histories[0];
				let accountData = this.binTable[curRecord];
				egret.Tween.get(this).wait(1).call(() => {
					self.flyToMe(accountData);
				}).wait(300).call(() => {
					self.flyToBanker();
				}).wait(300).call(() => {
					self.chipsLst = {};
				});
			}
		}

		// 清除全部筹码
		private flyToMe(accountData): void {
			if (accountData) {
				let endPos = this.betBtn.parent.localToGlobal(this.betBtn.x, this.betBtn.y);
				endPos = this.groupChips.globalToLocal(endPos.x, endPos.y);
				for (let i = 0; i < accountData.length; i++) {
					let v = accountData[i];
					if (v == 1) {
						let lst = this.chipsLst[i + 1];
						if (lst && lst.length > 0) {
							lst.forEach((e: Chip) => {
								egret.Tween.get(e).to({ x: endPos.x, y: endPos.y }, 300).call(() => {
									e.resumeOrgin();
								});
							});
						}
					}
				}
			}
		}

		private flyToBanker(): void {
			let endPos = this.groupDealer.parent.localToGlobal(this.groupDealer.x, this.groupDealer.y);
			endPos = this.groupChips.globalToLocal(endPos.x, endPos.y);
			for (var p in this.chipsLst) {
				let lst = this.chipsLst[p];
				if (lst && lst.length > 0) {
					lst.forEach((e: Chip) => {
						egret.Tween.get(e).to({ x: endPos.x, y: endPos.y }, 300).call(function () {
							e.resumeOrgin();
						});
					});
				}
			}
			this.areaNumLst = {
				'1': [0, 0, 0, 0, 0, 0, 0, 0],
				'2': [0, 0, 0, 0, 0, 0, 0, 0],
				'3': [0, 0, 0, 0, 0, 0, 0, 0],
				'4': [0, 0, 0, 0, 0, 0, 0, 0],
			}
		}

		// 续押
		private continueBet(): void {
			if (BrnnMgr.mainMoel.isCanContinue()) {
				let continueChips = BrnnMgr.mainMoel.getContinueRequireChips();
				let curBetChips = BrnnMgr.mainMoel.getCurBetRequireChips();
				if (continueChips + curBetChips <= BrnnMgr.mainMoel.chips) {
					for (let i = 1; i <= 4; i++) {
						let e = BrnnMgr.mainMoel.lastBetChips[i];
						if (e) {
							BrnnReq.send_ReqBet(i, e);
						}
					}
				} else {
					GameApp.PromotManager.floatingTip('对不起，您的筹码不足');
				}

			}
		}

		private updateCountdownAndState(): void {
			if (!BrnnMgr.isEnterScene()) {
				return;
			}
			this.updateChips(false);
			if (BrnnMgr.mainMoel.state == 2 && BrnnMgr.mainMoel.countDown > 0 && BrnnMgr.mainMoel.countDown <= 5) {
				BrnnMgr.playSound(7);
			}
			if (BrnnMgr.mainMoel.state) {
				this.imgState.texture = RES.getRes('brnn_atlas1_json.bairenniuniu_state_' + BrnnMgr.mainMoel.state);
				this.imgState.visible = true;
			}
			this.bmpClock.text = BrnnMgr.mainMoel.countDown;
			this.bmpClock.visible = true;
			if (BrnnMgr.mainMoel.countDown && BrnnMgr.mainMoel.countDown > 0) {
				BrnnMgr.mainMoel.descTime();
			}
		}

		public backTable(): void {
			App.SoundManager.playBg(BrnnConfig.MusicConfig.MusicTable['1'].resPath);
			this.betBtn.initBetOptions(BrnnMgr.mainMoel.roomInfo.betOptions);
			this.resetGame();
			if (BrnnMgr.mainMoel.state) {
				this.imgState.texture = RES.getRes('brnn_atlas1_json.bairenniuniu_state_' + BrnnMgr.mainMoel.state);
				this.imgState.visible = true;
			}
			if(BrnnMgr.mainMoel.chips <= 0) {
				this.exchangeView.show(GameApp.PlayerInfo.safeGold, BrnnMgr.mainMoel.chips, BrnnMgr.mainMoel.roomInfo);
			}
			this.updateBankerInfo();
			this.updatePlayerInfo();
		}

		// 直接显示出结果牌行
		public showCards(tipsCards: any, t: number): void {
			let self = this;
			for (let i = 0; i < tipsCards.length; i++) {
				this.cardItemList[i + 1].setCardInfo(tipsCards[i]);
			}
			// 发牌（省略动画）
			for (let i = 1; i <= 5; i++) {
				for (let j = 1; j <= 5; j++) {
					this.cardItemList[i].addCard();
				}
			}
			// 直接显示结果
			if (t < 14 ) {
				for (let i = 1; i <= 5; i++) {
					this.cardItemList[i].showCards();
					this.cardItemList[i].showCardType(false);
				}
				egret.Tween.get(self).wait(500).call(()=>{
					self.playBlinkAnimation();
				});
				egret.Tween.get(self).wait(1000).call(()=>{
					self.openClearingView();
				});
			} else { // 时间够长+翻牌动画
				let flopCardCallBack = () => {
					for (let i = 1; i <= 5; i++) {
						egret.Tween.get(self).wait(2000 * (i - 1)).call(() => {
							self.cardItemList[i].playFlopAnimation();
						});
					}
				}
				egret.Tween.get(self).call(()=>{
					flopCardCallBack();
				}).wait(13000).call(()=>{
					self.playBlinkAnimation();
				}).wait(1000).call(()=>{
					self.openClearingView();
				}).wait(1000).call(()=>{
					self.playBankerAnimation();
				});
			} 
		}

		private removeSendCard(): void {
			this.sendCards.forEach(e => {
				if(e.parent) {
					e.parent.removeChild(e);
				}
			});
			this.sendCards = [];
		}

		public pauseGame(stopMusic: boolean): void {
			egret.Tween.removeAllTweens();
			this.resetUI();
			if(stopMusic) {
				App.SoundManager.stopBg();
				App.SoundManager.stopEffect();
			}
		}


		public finish(): void {
			if (this.timer) {
				this.timer.stop();
				if (this.timer.hasEventListener(egret.TimerEvent.TIMER)) {
					this.timer.removeEventListener(egret.TimerEvent.TIMER, this.updateCountdownAndState, this);
				}
				this.timer = null;
			}
			this.resetUI();
			BrnnMgr.ObjectPool.destroy();
			egret.Tween.removeAllTweens();
			this.removeSendCard();
		}
	}
}