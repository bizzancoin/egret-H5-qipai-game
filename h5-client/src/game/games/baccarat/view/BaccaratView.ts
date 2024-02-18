module baccarat {
	export class BaccaratView extends BaseEuiView {
		//下注筹码按钮
		private grpBetBtns: eui.Group;
		private btnChipArray: Array<eui.Button>;
		private btnChip1: eui.Button;
		private btnChip2: eui.Button;
		private btnChip3: eui.Button;
		private btnChip5: eui.Button;
		private btnChip4: eui.Button;
		private btnChip6: eui.Button;
		private btnChip7: eui.Button;
		private btnChip8: eui.Button;
		private btnClear: eui.Button;
		private btnContinue: eui.Button;
		//下注区域信息
		private grpBetInfo: eui.Group;
		private grpChipPanel: eui.Group;
		private grpWinLight: eui.Group;
		private betInfoArray: any;
		private tableBets: any;
		private tableChipArray: any;
		
		//我的下注区域信息
		private grpMyBet: eui.Group;
		private myBetArray: any;
		private chipArray: Array<baccarat.ChipItem>;
		//下注区域按钮
		private grpBetArea: eui.Group;
		private btnBetArea0: eui.Button;
		private btnBetArea1: eui.Button;
		private btnBetArea2: eui.Button;
		private btnBetArea4: eui.Button;
		private btnBetArea9: eui.Button;
		private btnAreaArray: any;

		//倒计时和状态
		private lbClock: eui.BitmapLabel;
		private imgStatus: eui.Image;
		private lbRoundCnt: eui.Label;
		private gameTime: number;
		private gameState: number;
		private timer: egret.Timer;
		private timerArray: Array<egret.Timer>;

		//牌局信息
		private grpPaim: eui.Group;
		private imgPaim: eui.Image;

		private grpBoxAnim: eui.Group;
		private imgPokerMachine: eui.Image;

		private grpTop: eui.Group;
		private grpXPool: eui.Group;
		private grpZPool: eui.Group;
		private grpWPool: eui.Group;

		private imgXPB: eui.Image;
		private imgZPB: eui.Image;

		private pokerArray: Array<baccarat.PokerItem>;
		private pokerWaitArray: Array<baccarat.PokerItem>;
		//结果
		private pnlResult: eui.Panel;
		private resultPanel: baccarat.BaccaratResult;
		//切牌动画
		private grpShuffle: eui.Group;
		private imgShufflePair: eui.Image;
		private imgShufflePaim: eui.Image;
		private imgShuffleYellow: eui.Image;
		//兑换筹码
		private btnDuihuan: eui.Button;
		//个人信息
		private grpPlayerInfo: eui.Group;
		private imgPlayerAvatar: eui.Image;
		private lbNick: eui.Label;
		private lbGold: eui.Label;
		private lbChip: eui.Label;
		private btnPlayer: eui.Button;
		private isOpenPlayer: boolean;
		//路单
		private wayBillView: baccarat.BaccaratWayBillView;
		//提示
		private grpHint: eui.Group;
		private imgHint: eui.Image;
		//设置界面
		private grpSetPanel: eui.Group;
		private btnSetDrag: eui.Button;
		private imgSetDrag: eui.Image;
		private btnSet: eui.Button;
		private btnExit: eui.Button;

		private grpSet: eui.Group;
		private musicGroup: eui.RadioButtonGroup;
		private voiceGroup: eui.RadioButtonGroup;
		private btnSetClose: eui.Button;
		private rabMusicOn: eui.RadioButton;
		private rabMusicOff: eui.RadioButton;
		private rabVoiceOn: eui.RadioButton;
		private rabVoiceOff: eui.RadioButton;
		private exchangeView: game.ExchangeView;
		//数据
		private myInfo: any;
		private cardInfo: any;
		public cardIndex:number;
		private curBet: number;
		private lastBets: any;
		private score: number;
		private firstResult: number;
		private bankerChips: number;
		private playerChips: number;
		public constructor($controller, $parent) {
			super($controller, $parent);
			this.skinName = "resource/skins/game/baccarat/BaccaratSkin.exml";
		}

		public initUI(): void {
			super.initUI();
			this.findUIChipBtns();
			this.findUIBetAreaBtns();
			this.findUIBetInfo();
			this.findUIMyBetInfo();
			this.findUISet();
			this.imgXPB.visible = false;
			this.imgZPB.visible = false;
			this.resultPanel = new baccarat.BaccaratResult(this.pnlResult);
			this.wayBillView = new baccarat.BaccaratWayBillView();
			this.wayBillView.horizontalCenter=0;
			this.wayBillView.y = -950;

			this.grpPaim.visible = true;
			this.imgPaim.y = 95;
			App.SoundManager.playBg("bjl_bj0_mp3");
			this.addChild(this.wayBillView);
			this.btnDuihuan.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onDuihuanClick, this);
			this.exchangeView.hide();
			this.exchangeView.registerEvent((exchangeType: game.ExchangeType, chips: number)=>{
					if(chips <= 0) {
						GameApp.PromotManager.floatingTip('兑换筹码必须大于0');
						return;
					}
					switch(exchangeType) {
						case game.ExchangeType.CashIn:   // 下分
							BaccaratReq.send_ReqExchangeGolds(chips);
							break;
						case game.ExchangeType.CashOut:  // 上分
							BaccaratReq.send_ReqExchangeChips(chips);
							break;
					}
				}, this);
			this.gameTime = 0;
			this.gameState = 0;
			this.timer = new egret.Timer(1000);
			this.timer.addEventListener(egret.TimerEvent.TIMER, this.timerListener, this);
			this.timer.start();
		}

		// 初始化数据
		public initData(): void {
			super.initData();
			this.curBet = 0;
			this.score = 0;
			this.bankerChips = 0;
			this.playerChips = 0;
			this.firstResult = 0;
			this.tableBets = [0,0,0,0,0,0,0,0,0,0,0,0];
			this.timerArray = new Array<egret.Timer>();
			this.isOpenPlayer = false;
			this.tableChipArray = new Array<baccarat.ChipItem>();
			this.chipArray = new Array<baccarat.ChipItem>();
			this.pokerArray = new Array<baccarat.PokerItem>();
			this.pokerWaitArray = new Array<baccarat.PokerItem>();
			this.setGameState(0, 0);
			App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.RenderCompleted);
		}
		public open(...param: any[]): void {
			super.open(param);
		}
		//设置个人信息
		public setPlayerInfo(info: any): void {
			this.myInfo = info;
			this.lbNick.text = info.playerName.toString();
			this.lbChip.text = info.chips.toString();
			this.lbGold.text = GameApp.chips2Money(GameApp.PlayerInfo.safeGold);
			this.imgPlayerAvatar.texture = GameApp.PlayerInfo.getHeadTexture();
		}
		//设置游戏状态
		public setGameState(time: number, state: number): void {
			this.gameTime = time;
			this.gameState = state;
			switch (state) {
				case 1:
					this.btnDuihuan.enabled = true;
					App.SoundManager.playEffect("bjl_beginXiazhu_mp3")
					this.imgStatus.source = RES.getRes("bjl_wd_json.bjl_wd_qxz");
					break;
				case 2:
					this.btnDuihuan.enabled = false;
					this.imgStatus.source = RES.getRes("bjl_wd_json.bjl_wd_kpz");
					break;
				case 3:
					this.clear(false);
					this.imgStatus.source = RES.getRes("bjl_wd_json.bjl_wd_xxyx");
					App.SoundManager.playEffect("bjl_READY_mp3")
					this.lbRoundCnt.text = (this.score + 1).toString();
					break;
				case 4:
					this.btnDuihuan.enabled = false;
					this.imgStatus.source = RES.getRes("bjl_wd_json.bjl_wd_xpz");
					break;
				default:
					this.imgStatus.source = RES.getRes("bjl_wd_json.bjl_wd_xxyx");
			}
		}
		private timerListener():void{
			if(this.gameTime<=0){
				return;
			}
			this.gameTime--;
			this.lbClock.text = "" + this.gameTime;
			if (this.gameState == 1) {
				if (this.gameTime < 5)
					App.SoundManager.playEffect("bjl_time_mp3")
				if (this.gameTime == 0)
					App.SoundManager.playEffect("bjl_stopXiazhu_mp3")
			}
			if (this.gameState == 2 && this.gameTime == 1) {
				this.clear(true);
			}
		}
		//本人下注
		public doBet(bet: number, area: number, total: number): void {
			App.SoundManager.playEffect("bjl_PAWN_BANKER_mp3")
			this.btnClear.enabled = true;

			let chip: baccarat.ChipItem = new baccarat.ChipItem();
			chip.setChip(bet);
			chip.scaleX = 0.4;
			chip.scaleY = 0.4;
			this.chipArray.push(chip);
			let x = this.betInfoArray[area].x + Math.floor(Math.random() * (this.betInfoArray[area].width - 100 + 1) + 30);
			let y = this.betInfoArray[area].y + Math.floor(Math.random() * (this.betInfoArray[area].height - 60 + 1) + 10);
			this.grpChipPanel.addChildAt(chip, 0);
			this.myBetArray[area].setMyBet(total);
			chip.sendChip(x, y);
		}
		//清除本人下注
		public clearBet(): void {
			this.chipArray.forEach(ele => {
				ele.clear();
			});
			for(let k in this.myBetArray){
				this.myBetArray[k].clear();
			}
			this.curBet = 0;
			this.btnClear.enabled = false;
			this.chipArray = new Array<baccarat.ChipItem>();
		}
		//桌面下注信息
		public tableBetInfo(betInfo: any): void {
			for (let i = 0; i < betInfo.length; i++) {
				if(this.betInfoArray[i]){
					let chip = betInfo[i];
					if(BaccaratMgr.bjlModel.myBet[i]){
						chip -= BaccaratMgr.bjlModel.myBet[i];
					}
					chip -= this.tableBets[i];
					for (let j = 8; j > 0; j--) {
						let cfg = BaccaratMgr.bjlModel.roomInfo.betOptions[j-1];
						let num = Math.floor(chip/cfg);
						if(num>0){
							chip = chip - num*cfg;
							App.SoundManager.playEffect("bjl_PAWN_BANKER_mp3");
							for(let k = 0;k<num;k++){
								let chip: baccarat.ChipItem = new baccarat.ChipItem();
								chip.setChip(cfg);
								chip.scaleX = 0.4;
								chip.scaleY = 0.4;
								this.tableChipArray.push(chip);
								let x = this.betInfoArray[i].x + Math.floor(Math.random() * (this.betInfoArray[i].width - 100 + 1) + 30);
								let y = this.betInfoArray[i].y + Math.floor(Math.random() * (this.betInfoArray[i].height - 60 + 1) + 10);
								this.grpChipPanel.addChild(chip);
								chip.sendChip(x, y);
							}
						}
					}
					this.betInfoArray[i].setBet(betInfo[i]);
					this.tableBets[i] = betInfo[i];
				}
			}
		}
		public updateTableBetInfo():void{
			for (let i = 0; i < BaccaratMgr.bjlModel.totalBet.length; i++) {
				if(this.betInfoArray[i]){
					this.betInfoArray[i].setBet(BaccaratMgr.bjlModel.totalBet[i]);
				}
			}

		}
		//中间区域
		public gameResult(result: Array<number>): void {
			result = result.sort();
			this.firstResult = result[0];
			this.grpWinLight.visible = false;
			result.forEach(idx => {
				if(this.betInfoArray[idx])
				this.betInfoArray[idx].setLight();
			});
		}
		//显示中奖区域结果
		public showGameResult(): void {
			this.setWayBill(BaccaratMgr.bjlModel.billWayInfo);
			this.grpWinLight.visible = true;
			let tw = egret.Tween.get(this.grpWinLight, { loop: true });
			tw.to({ alpha: 0 }, 100).wait(200).to({ alpha: 1 }, 100).wait(300);

			this.imgXPB.visible = false;
			this.imgZPB.visible = false;
			let cur = 0;
			let timer = new egret.Timer(1000, 3);
			timer.addEventListener(egret.TimerEvent.TIMER, function () {
				if (cur == 0) {
					if (this.cardInfo[0]) {
						App.SoundManager.playEffect("bjl_xian" + this.cardInfo[0].point + "dian_mp3")
					}
				}
				if (cur == 1) {
					if (this.cardInfo[1]) {
						App.SoundManager.playEffect("bjl_zhuang" + this.cardInfo[1].point + "dian_mp3")
					}
				}
				if (cur == 2) {
					if (this.myInfo) {
						let sex = this.myInfo.icon % 2;
						let rand = new Date().getTime() % 2;
						if (this.playerChips > 0) {
							let name = sex == 1 ? "bjl_M_WIN_" + rand + "_mp3" : "bjl_F_WIN_" + rand + "_mp3";
							App.SoundManager.playEffect(name);
						}
						else {
							let name = sex == 1 ? "bjl_M_LOSE_" + rand + "_mp3" : "bjl_F_LOSE_" + rand + "_mp3";
							App.SoundManager.playEffect(name);
						}
					}
				}
				cur++;
			}, this);
			timer.addEventListener(egret.TimerEvent.TIMER_COMPLETE, function () {
				this.resultPanel.showResult(this.firstResult, this.bankerChips, this.playerChips);
				this.setPlayerInfo(BaccaratMgr.bjlModel.me);
			}, this);
			timer.start();
			this.timerArray.push(timer);
		}
		//结算信息
		public gameBill(bankerChips: number, playerChips: number): void {
			this.bankerChips = bankerChips;
			this.playerChips = playerChips;
		}
		//游戏开始发牌
		public gameStartProcess(cardsInfo: any, cardIndex: number, yellow: boolean): void {
			if(this.cardIndex == cardIndex){
				return;
			}
			this.cardIndex = cardIndex;
			this.pokerArray.forEach(pk => {
				pk.clear();
			});
			this.pokerArray = new Array<baccarat.PokerItem>();
			//判断回收池高度
			this.grpPaim.visible = true;
			let h = (cardIndex / 416) * 90;
			this.imgPaim.y = 95 - h;

			this.pokerBoxAnim(yellow);
			cardsInfo.reverse();
			this.cardInfo = cardsInfo;

			let cnt = cardsInfo[0].cards.length + cardsInfo[1].cards.length;
			let cur = 0;
			let timer = new egret.Timer(700, 7);
			timer.addEventListener(egret.TimerEvent.TIMER, function () {
				if (cur < 4) {
					//4张直接发牌
					let suitIdx = Math.floor(cur % 2);
					let cardIdx = Math.floor(cur / 2);
					let x = 0;
					if (suitIdx == 0) {
						x = this.grpXPool.x + cardIdx * 160;
					}
					else {
						x = this.grpZPool.x + cardIdx * 160;
					}
					let pk: baccarat.PokerItem = null;
					if (this.pokerWaitArray.length > 0) {
						pk = this.pokerWaitArray.shift();
					}
					else {
						pk = new baccarat.PokerItem();
						pk.scaleX = 0.9;
						pk.scaleY = 0.9;
						pk.x = this.grpBoxAnim.x;
						pk.y = 30;
					}
					this.pokerArray.push(pk);
					pk.setCard(cardsInfo[suitIdx].cards[cardIdx]);
					this.grpTop.addChild(pk);
					pk.sendCard(x, 130, true);
				}
				else if (cur < 6) {
					//2张发到等候区域
					let pk: baccarat.PokerItem = new baccarat.PokerItem();
					pk.x = this.grpBoxAnim.x;
					pk.y = 30;
					pk.scaleX = 0.9;
					pk.scaleY = 0.9;
					this.grpTop.addChild(pk);
					let x = this.grpWPool.x + (cur - 4) * 160 - 50;
					pk.sendCard(x, this.grpWPool.y + 30, false);
					this.pokerWaitArray.push(pk);
				}
				else {
					if (cnt > 4) {
						this.dealPoker(cardsInfo);
					}
					else {
						this.showGameResult();
					}
				}
				cur++;
			}, this);
			timer.addEventListener(egret.TimerEvent.TIMER_COMPLETE, function () {
				if (cnt == 4) {
				}
				else {
					//this.showGameResult();
				}
			}, this);
			timer.start();
			this.timerArray.push(timer);
		}
		//直接发牌
		public gameStartNow(cardsInfo: any, cardIndex: number, yellow: boolean): void {
			if(this.cardIndex == cardIndex && this.pokerArray.length>0){
				return;
			}
			this.cardIndex = cardIndex;
			this.pokerArray.forEach(pk => {
				pk.clear();
			});
			this.pokerArray = new Array<baccarat.PokerItem>();
			//判断回收池高度
			this.grpPaim.visible = true;
			let h = (cardIndex / 416) * 90;
			this.imgPaim.y = 95 - h;
			cardsInfo.reverse();
			this.cardInfo = cardsInfo;

			let cnt = cardsInfo[0].cards.length + cardsInfo[1].cards.length;
			for(let cur = 0;cur<7;cur++){
				if (cur < 4) {
					//4张直接发牌
					let suitIdx = Math.floor(cur % 2);
					let cardIdx = Math.floor(cur / 2);
					let x = 0;
					if (suitIdx == 0) {
						x = this.grpXPool.x + cardIdx * 160;
					}
					else {
						x = this.grpZPool.x + cardIdx * 160;
					}
					let pk: baccarat.PokerItem = null;
					if (this.pokerWaitArray.length > 0) {
						pk = this.pokerWaitArray.shift();
					}
					else {
						pk = new baccarat.PokerItem();
						pk.scaleX = 0.9;
						pk.scaleY = 0.9;
						pk.x = this.grpBoxAnim.x;
						pk.y = 30;
					}
					this.pokerArray.push(pk);
					pk.setCard(cardsInfo[suitIdx].cards[cardIdx]);
					pk.showCard();
					this.grpTop.addChild(pk);
					pk.x = x;
					pk.y = 130;
				}
				else if (cur < 6) {
					//2张发到等候区域
					let pk: baccarat.PokerItem = new baccarat.PokerItem();
					pk.x = this.grpBoxAnim.x;
					pk.y = 30;
					pk.scaleX = 0.9;
					pk.scaleY = 0.9;
					this.grpTop.addChild(pk);
					let x = this.grpWPool.x + (cur - 4) * 160 - 50;
					pk.x = x;
					pk.y = this.grpWPool.y + 30;
					this.pokerWaitArray.push(pk);
				}
				else {
					if (cnt > 4) {
						if (cardsInfo[0].cards.length > 2) {
							let pk = this.pokerWaitArray.shift();
							this.pokerArray.push(pk);
							pk.setCard(cardsInfo[0].cards[2]);
							pk.showCard();
							this.grpTop.addChild(pk);
							let x = this.grpXPool.x + 220;
							pk.ratateCard(x, 190);
						}
						if (cardsInfo[1].cards.length > 2) {
							let pk = this.pokerWaitArray.shift();
							this.pokerArray.push(pk);
							pk.setCard(cardsInfo[1].cards[2]);
							pk.showCard();
							this.grpTop.addChild(pk);
							let x = this.grpZPool.x + 220;
							pk.ratateCard(x, 190);
						}
					}
					// this.showGameResult();
				}
			}
		}
		//路单通知
		public setWayBill(billInfo: any): void {
			this.wayBillView.setData(billInfo);
		}
		//结算统计
		public setBillStat(stat: any): void {
			this.score = stat.score;
			this.lbRoundCnt.text = (this.score + 1).toString();
			this.wayBillView.setStat(stat);
		}

		//博派
		private dealPoker(cardsInfo: any) {
			let cnt = 0;
			if (cardsInfo[0].cards.length > 2 && cardsInfo[1].cards.length > 2) {
				this.xianAinm(cardsInfo[0].cards[2], () => {
					this.zhuangAinm(cardsInfo[1].cards[2], () => {
						this.showGameResult();
					})
				});
			}
			else if (cardsInfo[0].cards.length > 2 && cardsInfo[1].cards.length == 2) {
				this.xianAinm(cardsInfo[0].cards[2], () => {
					this.showGameResult();
				});
			}
			else if (cardsInfo[0].cards.length == 2 && cardsInfo[1].cards.length > 2) {
				this.zhuangAinm(cardsInfo[1].cards[2], () => {
					this.showGameResult();
				});
			}
		}
		//发牌动画
		private pokerBoxAnim(yellow: boolean): void {
			if (yellow) {
				var data = RES.getRes("l2h_json");
				var txtr = RES.getRes("l2h_png");
				var mcFactory: egret.MovieClipDataFactory = new egret.MovieClipDataFactory(data, txtr);
				var mc1: egret.MovieClip = new egret.MovieClip(mcFactory.generateMovieClipData("h-l"));
				this.grpBoxAnim.addChild(mc1);
				mc1.gotoAndPlay("l2h", 6);
				mc1.addEventListener(egret.Event.COMPLETE, (e: egret.Event) => {
					mc1.parent.removeChild(mc1);
				}, this);
			}
			else {
				var data = RES.getRes("l2l_json");
				var txtr = RES.getRes("l2l_png");
				var mcFactory: egret.MovieClipDataFactory = new egret.MovieClipDataFactory(data, txtr);
				var mc1: egret.MovieClip = new egret.MovieClip(mcFactory.generateMovieClipData("h-l"));
				this.grpBoxAnim.addChild(mc1);
				mc1.gotoAndPlay("l2l", 6);
				mc1.addEventListener(egret.Event.COMPLETE, (e: egret.Event) => {
					mc1.parent.removeChild(mc1);
				}, this);
			}
		}
		//xianbopai
		private xianAinm(card: number, callback: Function): void {
			App.SoundManager.playEffect("bjl_bopai_mp3")
			this.imgXPB.visible = true;
			let tw = egret.Tween.get(this.imgXPB, { loop: true });
			tw.to({ alpha: 0 }, 100).wait(200).to({ alpha: 1 }, 100).wait(200);
			let timer = new egret.Timer(900, 2);
			let i = 0;
			timer.addEventListener(egret.TimerEvent.TIMER, function () {
				if (i == 0) {
					let pk = this.pokerWaitArray.shift();
					this.pokerArray.push(pk);
					pk.setCard(card);
					this.grpTop.addChild(pk);
					let x = this.grpXPool.x + 220;
					pk.ratateCard(x, 190);
				}
				i++;
			}, this);
			timer.addEventListener(egret.TimerEvent.TIMER_COMPLETE, function () {
				this.imgXPB.visible = false;
				if (callback) {
					callback();
				}
			}, this);
			timer.start();
			this.timerArray.push(timer);
		}
		//zhuangbopai
		private zhuangAinm(card: number, callback: Function): void {
			App.SoundManager.playEffect("bjl_bopai_mp3")
			this.imgZPB.visible = true;
			let tw = egret.Tween.get(this.imgZPB, { loop: true });
			tw.to({ alpha: 0 }, 100).wait(200).to({ alpha: 1 }, 100).wait(200);
			let i = 0;
			let timer = new egret.Timer(900, 2);
			timer.addEventListener(egret.TimerEvent.TIMER, function () {
				if (i == 0) {
					let pk = this.pokerWaitArray.shift();
					this.pokerArray.push(pk);
					pk.setCard(card);
					this.grpTop.addChild(pk);
					let x = this.grpZPool.x + 220;
					pk.ratateCard(x, 190);
				}
				i++;
			}, this);
			timer.addEventListener(egret.TimerEvent.TIMER_COMPLETE, function () {
				this.imgZPB.visible = false;
				if (callback) {
					callback();
				}
			}, this);
			timer.start();
			this.timerArray.push(timer);
		}

		//下注按钮初始化
		private findUIChipBtns(): void {
			this.btnContinue.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onContineClick, this);
			this.btnClear.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClearBetClick, this);
			this.btnContinue.enabled = false;
			this.btnClear.enabled = false;
			this.btnChipArray = [this.btnChip1, this.btnChip2, this.btnChip3, this.btnChip4, this.btnChip5, this.btnChip6, this.btnChip7, this.btnChip8];
			this.btnChipArray.forEach(btn => {
				btn.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onBetClick, this);
				btn.enabled = false;
			});

		}
		//下注信息初始化
		private findUIBetInfo(): void {
			this.betInfoArray = {};
			let tmp: Object = new Object();

			this.grpBetInfo.$children.forEach(element => {
				tmp[element.name] = element;
			});
			this.grpWinLight.$children.forEach(element => {
				tmp[element.name] = element;
			});
			for (let i = 0; i < 12; i++) {
				let img = <eui.Image>tmp["imgBetArea" + i];
				let lb = <eui.BitmapLabel>tmp["lbBetArea" + i];
				if(img!=null && lb!=null){
					let info = new baccarat.BaccaratBetAreaItem(img, lb);
					this.betInfoArray[i] = info;
				}
			}
		}
		//下注区域按钮初始化
		private findUIBetAreaBtns(): void {
			this.btnAreaArray = {};
			this.btnAreaArray[0] = this.btnBetArea0;
			this.btnAreaArray[1] = this.btnBetArea1;
			this.btnAreaArray[2] = this.btnBetArea2;
			this.btnAreaArray[4] = this.btnBetArea4;
			this.btnAreaArray[9] = this.btnBetArea9;
			this.btnAreaArray
			for(let k in this.btnAreaArray){
				this.btnAreaArray[k].addEventListener(egret.TouchEvent.TOUCH_TAP, (evt: egret.TouchEvent) => {
					if (this.curBet > 0 && this.curBet <= BaccaratMgr.bjlModel.me.chips) {
						baccarat.BaccaratReq.send_ReqBet(this.curBet, Number(k));
					}
				}, this);
				this.btnAreaArray[k].enabled = false;
			}
		}
		//我的下注区域初始化
		private findUIMyBetInfo(): void {
			this.myBetArray = {};

			let tmp: Object = new Object();
			this.grpMyBet.$children.forEach(element => {
				tmp[element.name] = element;
			});

			for (let i = 0; i < 12; i++) {
				let grp = <eui.Group>tmp["grpMyBet" + i];
				if(grp!=null){
					let myBet = new baccarat.BaccaratMyBetItem(grp);
					this.myBetArray[i] = myBet;
				}
			}
		}
		//设置页面/玩家界面/提示界面初始化
		private findUISet(): void {
			this.btnSetDrag.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onSetDragClick, this);
			this.btnSet.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onSetClick, this);
			this.btnExit.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onExitClick, this);
			this.btnSetClose.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onSetClick, this);

			this.grpHint.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onHintPanelClick, this);

			this.btnPlayer.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onPlayerClick, this);

			this.musicGroup = new eui.RadioButtonGroup();
			this.musicGroup.addEventListener(eui.UIEvent.CHANGE, this.musicRadioChange, this);
			this.rabMusicOn.group = this.musicGroup;
			this.rabMusicOff.group = this.musicGroup;
			App.SoundManager.bgOn ? this.rabMusicOn.selected = true : this.rabMusicOff.selected = true;

			this.voiceGroup = new eui.RadioButtonGroup();
			this.voiceGroup.addEventListener(eui.UIEvent.CHANGE, this.voiceRadioChange, this);
			this.rabVoiceOn.group = this.voiceGroup;
			this.rabVoiceOff.group = this.voiceGroup;
			App.SoundManager.effectOn ? this.rabVoiceOn.selected = true : this.rabVoiceOff.selected = true;
		}
		//清除牌局信息
		public clear(voice: boolean): void {
			this.imgXPB.visible = false;
			this.imgZPB.visible = false;
			this.btnDuihuan.enabled = true;
			if (voice && this.curBet)
				App.SoundManager.playEffect("bjl_PAWN_BANKER_mp3");
			for(let k in this.betInfoArray){
				this.betInfoArray[k].clear();
			}
			for(let k in this.myBetArray){
				this.myBetArray[k].clear();
			}
			this.chipArray.forEach(ele => {
				ele.recyle();
			});
			this.tableChipArray.forEach(ele => {
				ele.recyle();
			});
			this.pokerArray.forEach(pk => {
				pk.recyle();
			});
			while (this.timerArray.length > 0) {
				let tt = this.timerArray.pop();
				tt.stop();
			}
			this.tableBets = [0,0,0,0,0,0,0,0,0,0,0,0];
			this.tableChipArray = new Array<baccarat.ChipItem>();
			this.chipArray = new Array<baccarat.ChipItem>();
			this.pokerArray = new Array<baccarat.PokerItem>();
			this.resultPanel.clear();
			this.grpWinLight.visible = false;
		}
		//设置下注按钮状态
		public setBetBtnState(myChip: number, maxBet: number, lastBets: any, close: boolean): void {
			if (close) {
				this.btnChipArray.forEach(btn => {
					btn.enabled = false;
				});
				this.btnContinue.enabled = false;
				this.btnClear.enabled = false;
				return;
			}
			for (let i = 0; i < 8; i++) {
				let bet = BaccaratMgr.bjlModel.roomInfo.betOptions[i];
				if (myChip < bet || bet > maxBet) {
					this.btnChipArray[i].enabled = false;
				}
				else {
					this.btnChipArray[i].enabled = true;
				}
			}
			if(this.curBet > myChip){
				this.curBet = 0;
			}
			this.lastBets = lastBets;
			let totalBet = 0;
			lastBets.forEach(element => {
				totalBet = totalBet + element;
			});
			if (totalBet <= myChip && totalBet > 0) {
				this.btnContinue.enabled = true;
			}
		}
		//设置下注区域状态
		public setBetAreaState(score: number, close: boolean): void {
			for(let k in this.btnAreaArray){
				this.btnAreaArray[k].enabled = !close;
			}
		}
		public setChipBtnText():void{
			for(let i = 0;i<this.btnChipArray.length;i++){
				let btn = this.btnChipArray[i];
				btn.label = this.setChipText(BaccaratMgr.bjlModel.roomInfo.betOptions[i]);
			}
		}
		private setChipText(chip:number):string{
			let str = "";
			if(chip>=100000000){
				str = Math.floor(chip/100000000)+"亿";
			}
			else if(chip>=10000){
				str = Math.floor(chip/10000)+"万";
			}
			else{
				str = chip.toString();
			}
			return str;
		}
		//弹出玩家信息
		private onPlayerClick(evt: egret.Event): void {
			let x = this.isOpenPlayer ? -285 : 0;
			this.isOpenPlayer = !this.isOpenPlayer;
			egret.Tween.get(this.grpPlayerInfo).to({ left: x }, 500);
		}
		//下注按钮点击事件
		private onBetClick(evt: egret.Event): void {
			let idx = this.btnChipArray.indexOf(evt.currentTarget);
			// let bet = Math.pow(10, idx)
			let bet = BaccaratMgr.bjlModel.roomInfo.betOptions[idx];
			this.curBet = bet;
		}
		//继续押注点击事件
		private onContineClick(): void {
			for (let i = 0; i < this.lastBets.length; i++) {
				if (this.lastBets[i] > 0) {
					this.curBet = this.lastBets[i];
					baccarat.BaccaratReq.send_ReqBet(this.lastBets[i], i);
				}
			}
		}
		//清除押注信息点击事件
		private onClearBetClick(): void {
			baccarat.BaccaratReq.send_ReqClearBet();
		}
		//退出点击事件
		private onExitClick(): void {
			GameApp.HomeManager.reqExitCurGameTable();
		}
		//显示洗牌和切牌
		public showShuffle(array: Array<number>): void {
			let ori_paim_pos = { x: 387, y: 298 }
			let ori_pair_pos = { x: 387, y: 410 }
			let ori_pair_h = 416;
			let ori_yellow_pos = { x: 1320, y: 560 }

			this.grpPaim.visible = false;
			this.grpShuffle.visible = true;
			this.imgShuffleYellow.visible = true;

			egret.Tween.get(this.imgShuffleYellow).to({ x: 387, y: 500 }, 600).call(() => {
				this.imgShuffleYellow.visible = false;
				egret.Tween.get(this.imgShufflePair).to({ height: 0 }, 1500).call(() => {
					this.imgShufflePair.visible = false;
					this.imgShufflePaim.visible = false;
					egret.Tween.get(this.imgShufflePaim).to({ x: 387, y: 660 }, 200).call(() => {
						this.imgShufflePair.visible = true;
						this.imgShufflePaim.visible = true;
						this.imgShufflePair.y = 810;
						egret.Tween.get(this.imgShufflePaim).to(ori_paim_pos, 1500);
						egret.Tween.get(this.imgShufflePair).to({ x: 387, y: 410, height: 416 }, 1500).call(() => {
							this.imgShufflePair.visible = false;
							this.imgShufflePaim.visible = false;
							this.showShuffleCards(array);
						});
					})
				})
			})
		}
		//显示切牌的牌
		private showShuffleCards(cards: Array<number>): void {

			let array = new Array<baccarat.PokerItem>();
			let cur = 0;
			let timer = new egret.Timer(800, cards.length + 4);
			timer.addEventListener(egret.TimerEvent.TIMER, function () {
				if (cur < cards.length) {
					let pk = new baccarat.PokerItem();
					pk.scaleX = 0.9;
					pk.scaleY = 0.9;
					pk.x = this.grpBoxAnim.x;
					pk.y = 30;
					pk.setCard(cards[cur]);

					this.grpShuffle.addChild(pk);
					let x = 0;
					let y = 0;
					if (cur == 0) {
						x = 895;
						y = 300;
					}
					else {
						let row = Math.floor((cur - 1) / 6) + 1;
						let col = (cur - 1) % 6;
						x = 520 + col * 150;
						y = 300 + row * 200;
					}
					array.push(pk);
					pk.sendCard(x, y, true);
				}
				if (cur == cards.length + 2) {
					array.forEach(pk => {
						pk.recyle();
					});
				}

				cur++;
			}, this);
			timer.addEventListener(egret.TimerEvent.TIMER_COMPLETE, function () {
				this.grpShuffle.visible = false;
				this.imgShuffleYellow.visible = false;
				this.imgShufflePair.visible = true;
				this.imgShufflePaim.visible = true;
				this.grpPaim.visible = true;
				this.imgPaim.y = 95;
			}, this);
			timer.start();
			this.timerArray.push(timer);
		}
		//显示宝龙提醒
		private onXBLHintClick(): void {
			this.imgHint.source = RES.getRes("bjl_img_json.bjl_bi");
			this.grpHint.visible = true;
		}
		private onZBLHintClick(): void {
			this.imgHint.source = RES.getRes("bjl_img_json.bjl_bi1");
			this.grpHint.visible = true;
		}
		private onHintPanelClick(): void {
			this.grpHint.visible = false;
		}
		//设置操作
		private onSetDragClick():void{
			let x = this.grpSetPanel.right == 0 ? -320 : 0;
			egret.Tween.get(this.grpSetPanel).to({ right: x }, 500).call(()=>{
				let str = this.grpSetPanel.right == 0 ? "bjl_img_json.bjl_s_in" : "bjl_img_json.bjl_s_out";
				this.imgSetDrag.source = RES.getRes(str)
			});
		}
		private onSetClick(): void {
			this.grpSet.visible = !this.grpSet.visible;
		}
		private musicRadioChange(evt: eui.UIEvent): void {
			let rg: eui.RadioButtonGroup = evt.target;
			App.SoundManager.setBgOn(rg.selectedValue.toString() == "1")
		}
		private voiceRadioChange(evt: eui.UIEvent): void {
			let rg: eui.RadioButtonGroup = evt.target;
			App.SoundManager.setEffectOn(rg.selectedValue.toString() == "1")
		}
		private onDuihuanClick():void{
			this.exchangeView.show(GameApp.PlayerInfo.safeGold,this.myInfo.chips,BaccaratMgr.bjlModel.roomInfo);
		}
		public showDuihuan():void{
			if(this.myInfo.chips <= 0) {
				this.exchangeView.show(GameApp.PlayerInfo.safeGold,this.myInfo.chips,BaccaratMgr.bjlModel.roomInfo);
			}
		}
		public close():void{
			if (this.timer) {
				this.timer.stop();
				this.timer.removeEventListener(egret.TimerEvent.TIMER, this.timerListener, this);
				this.timer = null;
			}
			super.close();
		}
	}
}