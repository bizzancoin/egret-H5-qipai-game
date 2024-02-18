module fish {
	export class RotateScore extends eui.Group {

		private img: eui.Image;
		private label: eui.BitmapLabel;
		private info: any;

		public constructor(info: any) {
			super();
			this.init(info);
		}

		private init(info: any): void {
			this.info = info;
			this.createItem();
			this.touchEnabled = false;
		}

		private createItem(): void {
			let skillCfg = FishMgr.Config.Skill[this.info.id];
			if (skillCfg) {
				let src = FishMgr.Config.Sprite[skillCfg.bk_src_id];
				if (src.stype == 1) {  // 动画
					let mc = GameApp.getClipByFileName(src.file, src.key);
					mc.anchorOffsetX = mc.width * 0.5;
					mc.anchorOffsetY = mc.width * 0.5;
					this.addChild(mc);
					mc.touchEnabled = false;
					if (skillCfg.floop == -1) {
						mc.gotoAndPlay('act', -1);
					} else {
						mc.gotoAndPlay('act', skillCfg.floop);
					}
				} else if (src.stype == 2) {
					this.img = new eui.Image(src.file);
					this.img.verticalCenter = 0;
					this.img.horizontalCenter = 0;
					this.img.touchEnabled = false;
					this.addChild(this.img);
				}

				this.width = this.img.width;
				this.height = this.img.height;
				this.anchorOffsetX = this.width * 0.5;
				this.anchorOffsetY = this.height * 0.5;

				this.label = new eui.BitmapLabel(this.info.score);
				this.label.font = RES.getRes(skillCfg.score_fnt_src);
				this.label.verticalCenter = 0;
				this.label.horizontalCenter = 0
				this.label.touchEnabled = false;
				this.addChild(this.label);

				this.info.parent.addChild(this);

				let tw = egret.Tween.get(this, {loop: true});
				tw.to({ rotation: 60 }, 600).to({ rotation: 0 }, 600);
				App.TimerManager.doTimer(3600, 1, function () {
				}, this, function () {
					if (this.parent) {
						this.parent.removeChild(this);
					}
				}, this);
			}

			let battery: Battery = this.info.parent;
			if (!battery) {
				return;
			}
			if (battery.getCusSkin() == 1) {
				this.x = 450;
				this.y = 0;
			} else {
				this.x = 150;
				this.y = 0;
			}
		}

	}
}