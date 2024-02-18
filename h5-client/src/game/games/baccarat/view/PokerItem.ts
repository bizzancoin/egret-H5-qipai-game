module baccarat {
	export class PokerItem extends eui.Component {
		private imgPokerFront: eui.Image;
		private imgPokerBack: eui.Image;

		public showed: boolean;

		public constructor() {
			super();
			this.skinName = "resource/skins/game/baccarat/BJLPokerSkin.exml"
		}

		protected createChildren(): void {
			super.createChildren();
			this.imgPokerFront.visible = false;
		}
		public setCard(card: number): void {
			let configData = RES.getRes("poker_config_json");
			if (configData) {
				let cardCfg = configData["baccarat_card_data"][card];
				this.imgPokerFront.source = RES.getRes(cardCfg["card_big"]);
			}
			else {
				this.imgPokerFront.source = RES.getRes("poker_json.FKK");
			}
		}
		public sendCard(x: number, y: number, show: boolean): void {
			App.SoundManager.playEffect("bjl_outcard_mp3")
			let tw = egret.Tween.get(this);
			tw.to({ x: x, y: y }, 200);
			tw.call(() => {
				if (show) {
					this.showCard();
				}
			});
		}
		public ratateCard(x: number, y: number): void {
			let tw = egret.Tween.get(this);
			tw.to({ x: x, y: y, rotation: 90 }, 200);
			tw.call(() => {
				this.showCard();
			});
		}
		public showCard(): void {
			App.SoundManager.playEffect("bjl_OpenCard_mp3")
			let tw = egret.Tween.get(this.imgPokerBack);
			tw.to({ skewY: 90 }, 200, egret.Ease.sineOut);
			tw.call(() => {
				this.imgPokerFront.visible = true;
				this.imgPokerFront.skewY = 270;
				egret.Tween.get(this.imgPokerFront).to({ skewY: 360 }, 200, egret.Ease.sineIn);
			});
		}
		public clear(): void {
			this.parent.removeChild(this);
		}
		public recyle(): void {
			let tw = egret.Tween.get(this);
			this.imgPokerFront.visible = false;
			this.imgPokerBack.skewY = 0;
			tw.to({ x: 160, y: 300, rotation: 0 }, 300).wait(200).to({ scaleX: 0.8, scaleY: 0.8 }, 100).call(() => {
				this.parent.removeChild(this);
			});
		}
	}
}