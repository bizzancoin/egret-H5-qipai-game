module brnn {
	// 牌想
	export class CardItem extends eui.Component {

		private groupCard: eui.Group;
		private groupType: eui.Group;
		private groupEffect: eui.Group;
		private imgType: eui.Image;
		private imgTypeBg: eui.Image;

		private imgCards: Card[];
		private cardList: any;
		private cardType: number;
		private cardNum: number;
		private cardsPosX: number;
		private imgFlopCard: Card;
		private effectMc: egret.MovieClip;
		private flopEffectMc: egret.MovieClip;

		private cardScale = 1;

		private idx: number;

		private cardTypeRes = [
			"brnn_atlas1_json.wn",
			"brnn_atlas1_json.n1",
			"brnn_atlas1_json.n2",
			"brnn_atlas1_json.n3",
			"brnn_atlas1_json.n4",
			"brnn_atlas1_json.n5",
			"brnn_atlas1_json.n6",
			"brnn_atlas1_json.n7",
			"brnn_atlas1_json.n8",
			"brnn_atlas1_json.n9",
		]

		public constructor(idx: number) {
			super();
			this.skinName = 'resource/skins/brnn/BrnnCardItemSkin.exml';
			this.imgCards = [];
			this.cardNum = 0;
			this.idx = idx;
		}

		// 设置牌的信息
		public setCardInfo(cardInfo: any): void {
			this.cardList = cardInfo.cards;
			this.cardType = cardInfo.cardsType;
		}

		public reset(): void {
			this.groupType.visible = false;
			this.cardList = null;
			this.cardType = null;
			this.cardNum = 0;
			if (this.imgCards) {
				this.imgCards.forEach(e => {
					e.resumeOrgin();
				});
			}
			this.imgCards = [];
			if (this.effectMc && this.effectMc.parent) {
				this.effectMc.parent.removeChild(this.effectMc);
				this.effectMc = null;
			}
			if (this.flopEffectMc && this.flopEffectMc.parent) {
				this.flopEffectMc.parent.removeChild(this.flopEffectMc);
				this.flopEffectMc = null;
			}
			if(this.imgFlopCard && this.imgFlopCard.parent) {
				this.imgFlopCard.resumeOrgin();
			}
		}

		// 添加牌
		public addCard(): void {
			if (this.cardList && this.cardNum < 5) {
				let imgCard: Card;
				this.cardNum++;
				if (this.cardNum == 5) {
					imgCard = BrnnMgr.BigCard.get(55, { x: 0, y: 0 });
				} else {
					let cardId = this.cardList[this.cardNum - 1];
					imgCard = BrnnMgr.BigCard.get(cardId, { x: 0, y: 0 });
				}
				this.groupCard.addChild(imgCard);
				this.imgCards.push(imgCard);
				let num = this.imgCards.length;
				let beginX = -(num - 1) * imgCard.width * this.cardScale / 8 + this.groupCard.width / 2;
				for (let i = 0; i < num; i++) {
					if (i == 4) {
						let px = beginX + 2 * imgCard.width * this.cardScale / 4;
						this.imgCards[i].x = px - 10;
						this.imgCards[i].y = this.groupCard.height / 2 + 30;
					} else {
						let px = beginX + i * imgCard.width * this.cardScale / 4;
						this.imgCards[i].x = px;
						this.imgCards[i].y = this.groupCard.height / 2;
					}
					this.cardsPosX = beginX + imgCard.width;
				}
			}
		}

		private showHiddenCard(): void {
			BrnnMgr.playSound(6);
			let cardId = this.cardList[4];
			this.imgCards[4] = BrnnMgr.BigCard.get(cardId, { x: 0, y: 0 });
			this.imgCards[4].visible = true;
			this.imgCards[4].x = this.cardsPosX + 22;
			this.imgCards[4].y = this.groupCard.height / 2 - 20;
		}

		public addFlopCard(): void {
			let cardId = this.cardList[4];
			let pos = {
				x: this.groupEffect.width / 2 + 40,
				y: this.groupEffect.height / 2 + 87
			}
			this.imgFlopCard = BrnnMgr.SmallCard.get(cardId, pos);
			this.groupEffect.addChild(this.imgFlopCard);
			egret.Tween.get(this).wait(8 / 9 * 1000).call(() => {
				this.imgFlopCard.resumeOrgin();
			}, this);
			egret.Tween.get(this).wait(7 / 9 * 1000).call(() => {
				this.removeFlopCard();
			}, this);
		}

		private removeFlopCard(): void {
			this.flopEffectMc.gotoAndPlay(8);
			egret.Tween.get(this)
				.wait(2 / 9 * 1000).call(() => {
					if (this.flopEffectMc && this.flopEffectMc.parent) {
						this.flopEffectMc.parent.removeChild(this.flopEffectMc);
						this.flopEffectMc = null;
						this.showHiddenCard();
					}
				}, this).wait(300).call(() => {
					this.showCards();
					this.showCardType();
				}, this);
		}


		// 翻拍动画
		public playFlopAnimation(): void {
			if (!this.imgCards || this.imgCards.length <= 0 || this.imgCards.length < 5) {
				return;
			}
			this.imgCards[4].visible = false;
			let effData = BrnnConfig.EffectConfig['翻牌1'];
			this.flopEffectMc = GameApp.getClipByFileName(effData.file, effData.key);
			this.flopEffectMc.anchorOffsetX = this.flopEffectMc.width / 2;
			this.flopEffectMc.anchorOffsetY = this.flopEffectMc.height / 2;
			this.groupEffect.addChild(this.flopEffectMc);
			this.flopEffectMc.gotoAndPlay(1);
			this.flopEffectMc.x = this.groupEffect.width / 2 - 10;
			this.flopEffectMc.y = this.groupEffect.height / 2 + 47;
			egret.Tween.get(this).wait(7 / 9 * 1000).call(() => {
				this.flopEffectMc.gotoAndStop(7);
				this.addFlopCard();
			}, this);
		}

		public showCardType(playSoune: boolean = true): void {
			let sex = 0;
			if (this.idx < 5) {
				sex = 0;
			} else {
				sex = 1;
			}
			if (playSoune) {
				switch (this.cardType) {
					case CardsKinds.SI_ZHA:
						BrnnMgr.playSound(6, sex);
						break;
					case CardsKinds.MEI_NIU:
						BrnnMgr.playSound(10, sex);
						break;
					case CardsKinds.NIU_1:
						BrnnMgr.playSound(11, sex);
						break;
					case CardsKinds.NIU_2:
						BrnnMgr.playSound(12, sex);
						break;
					case CardsKinds.NIU_3:
						BrnnMgr.playSound(13, sex);
						break;
					case CardsKinds.NIU_4:
						BrnnMgr.playSound(14, sex);
						break;
					case CardsKinds.NIU_5:
						BrnnMgr.playSound(15, sex);
						break;
					case CardsKinds.NIU_6:
						BrnnMgr.playSound(16, sex);
						break;
					case CardsKinds.NIU_7:
						BrnnMgr.playSound(17, sex);
						break;
					case CardsKinds.NIU_8:
						BrnnMgr.playSound(18, sex);
						break;
					case CardsKinds.NIU_9:
						BrnnMgr.playSound(19, sex);
						break;
					case CardsKinds.NIU_NIU:
						BrnnMgr.playSound(20, sex);
						break;
					case CardsKinds.WU_XIAO:
						BrnnMgr.playSound(21, sex);
						break;
					case CardsKinds.WU_HUA:
						BrnnMgr.playSound(25, sex);
						break;
					case CardsKinds.SI_HUA:
						BrnnMgr.playSound(26, sex);
						break;
				}
			}
			if (this.cardType >= CardsKinds.NIU_NIU) {
				let effData = BrnnConfig.EffectConfig[this.cardType];
				this.effectMc = GameApp.getClipByFileName(effData.file, effData.key);
				this.groupEffect.addChild(this.effectMc);
				this.effectMc.gotoAndPlay('act', -1);
				this.effectMc.anchorOffsetX = this.effectMc.width / 2;
				this.effectMc.anchorOffsetY = this.effectMc.height / 2;
				this.effectMc.x = this.groupEffect.width / 2 - 20;
				this.effectMc.y = this.groupEffect.height / 2 + 80;
			} else {
				this.imgType.texture = RES.getRes(this.cardTypeRes[this.cardType]);
				this.groupType.visible = true;
			}
		}

		public showCards(): void {
			this.imgCards.forEach(e => {
				if (e.parent) {
					e.parent.removeChild(e);
				}
			});
			this.imgCards = [];
			if (this.cardType > CardsKinds.MEI_NIU) {
				for (let i = 3; i < 5; i++) {
					let imgCard = BrnnMgr.BigCard.get(this.cardList[i], { x: 0, y: 0 });
					let beginX = -imgCard.width / 4 + this.groupCard.width / 2;
					let px = beginX + (i - 3) * imgCard.width / 2;
					imgCard.x = px;
					imgCard.y = -imgCard.height / 3 + this.groupCard.height / 2 + 10;
					this.imgCards.push(imgCard);
					this.groupCard.addChild(imgCard);
				}
				for (let i = 0; i < 3; i++) {
					let imgCard = BrnnMgr.BigCard.get(this.cardList[i], { x: 0, y: 0 });
					let beginX = -imgCard.width / 2 + this.groupCard.width / 2;
					let px = beginX + i * imgCard.width / 2;
					imgCard.x = px;
					imgCard.y = this.groupCard.height / 2 + 10;
					this.imgCards.push(imgCard);
					this.groupCard.addChild(imgCard);
				}
			} else {
				for (let i = 0; i < 5; i++) {
					let imgCard = BrnnMgr.BigCard.get(this.cardList[i], { x: 0, y: 0 });
					let beginX = -4 * imgCard.width / 8 + this.groupCard.width / 2;;
					let px = beginX + i * imgCard.width / 4;
					imgCard.x = px;
					imgCard.y = this.groupCard.height / 2 + 10;
					this.imgCards.push(imgCard);
					this.groupCard.addChild(imgCard);
				}
			}
		}


	}
}