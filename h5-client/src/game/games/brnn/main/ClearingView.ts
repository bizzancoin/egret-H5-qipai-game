module brnn {
	export class ClearingView extends eui.Component {

		private bmpDealerResult: eui.BitmapLabel;
		private bmpMeResult: eui.BitmapLabel;
		private groupContent: eui.Group;
		private mcList: egret.MovieClip[];
		private imgCow: eui.Image;
		private imgShadow: eui.Image;
		private cryingMc: egret.MovieClip;

		private starsPos = [
			{ x: 1032, y: 236 },
			{ x: 1311, y: 456 },
			{ x: 687, y: 272 },
			{ x: 584, y: 510 },
		];

		public constructor() {
			super();
			this.skinName = 'resource/skins/brnn/BrnnClearingSkin.exml';
		}

		public show(dealer: number, mine: number): void {
			this.bmpDealerResult.text = dealer < 0 ? dealer.toString() : '+' + dealer;
			this.bmpMeResult.text = mine < 0 ? mine.toString() : '+' + mine;
			this.playEffect(mine);
		}

		private playEffect(winchips: number): void {
			if (winchips >= 0) {
				this.imgCow.visible = true;
				this.imgShadow.visible = true;
				if(winchips > 0) {
					BrnnMgr.playSound(App.MathUtils.range(23, 24), App.MathUtils.range(0, 1));
				}
			} else {
				BrnnMgr.playSound(App.MathUtils.range(8, 9), App.MathUtils.range(0, 1));
				App.MathUtils
				this.imgCow.visible = false;
				this.imgShadow.visible = false;
				let effData = BrnnConfig.EffectConfig['crying'];
				this.cryingMc = GameApp.getClipByFileName(effData.file, effData.key);
				this.cryingMc.anchorOffsetX = this.cryingMc.width / 2;
				this.cryingMc.anchorOffsetY = this.cryingMc.height / 2;
				this.cryingMc.skewY = 180;
				this.cryingMc.gotoAndPlay('act', -1);
				this.cryingMc.x = 600;
				this.cryingMc.y = 690;
				this.addChild(this.cryingMc);
			}


			this.visible = true;
			let posIdxs = [0, 1, 2, 3];
			this.mcList = [];
			for (let i = 0; i < 4; i++) {
				egret.Tween.get(this).wait(900 * i).call(() => {
					let idx = Math.floor(Math.random() * posIdxs.length);
					if (idx >= posIdxs.length) {
						idx = posIdxs.length - 1;
					}
					let posIdx = posIdxs[idx];
					posIdxs.splice(idx, 1);
					let pos = this.starsPos[posIdx];
					let effData = BrnnConfig.EffectConfig['星星'];
					let mc = GameApp.getClipByFileName(effData.file, effData.key);
					mc.anchorOffsetX = mc.width / 2;
					mc.anchorOffsetY = mc.height / 2;
					GameApp.playMoveClip(this.groupContent, mc, 'act', pos, -1);
					this.mcList.push(mc);
				}, this);
			}
		}

		public hide(): void {
			this.visible = false;
			this.bmpDealerResult.text = '0';
			this.bmpMeResult.text = '0';
			if (this.mcList && this.mcList.length > 0) {
				this.mcList.forEach(e => {
					if (e.parent) {
						e.parent.removeChild(e);
					}
				});
				this.mcList = [];
			}
			if (this.cryingMc && this.cryingMc.parent) {
				this.cryingMc.parent.removeChild(this.cryingMc);
				this.cryingMc = null;
			}
			this.visible = false;
		}
	}
}