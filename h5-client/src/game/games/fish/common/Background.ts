module fish {
	export class Background extends eui.Group {

		private musicId: number;

		public constructor() {
			super();
			//this.percentWidth = 100;
			//this.percentHeight = 100;
			this.width = 1920;
			this.height = 1080;
			this.touchEnabled = false;
			this.x = 1920;
		}


		public create(state: number, time: number, info: any) {
			this.musicId = info.bk_mus_id;
			let musicSrc = FishMgr.Config.BgMusic[this.musicId];
			if (musicSrc) {
				App.SoundManager.playBg(musicSrc.file);
			}
			let bkSrc = FishMgr.Config.Sprite[info.bk_pic_id].file;
			if (bkSrc) {
				let texture: egret.Texture = RES.getRes(bkSrc);
				let imgBg = new eui.Image(texture);
				imgBg.percentWidth = 100;
				imgBg.percentHeight = 100;
				imgBg.x = 0;
				imgBg.y = 0;
				this.addChild(imgBg);
			}
			if (state == 0) {
				this.x = 0;
			}
			this.createGrass(info.view_inf);
			if (state == 1) {
				egret.Tween.get(this).to({ x: 0 }, time * 1000);
			}
		}

		// 创建水草
		private createGrass(viewInfo: any): void {
			App.ArrayUtils.forEach(viewInfo, function (k) {
				let src = FishMgr.Config.Sprite[k.src_id];
				if (src.stype == 1) {
					let mc = GameApp.getClipByFileName(src.file, src.key);
					this.addChild(mc);
					mc.anchorOffsetX = mc.width / 2;
					mc.anchorOffsetY = mc.height / 2;
					mc.x = k.pos.x;
					mc.y = 1080 - k.pos.y;
					mc.gotoAndPlay('act', -1);
				} else if (src.stype == 2) { // pic
					let img = new eui.Image(src.file);
					this.addChild(img);
					img.anchorOffsetX = img.width / 2;
					img.anchorOffsetY = img.height / 2;
					img.x = k.pos.x;
					img.y = 1080 - k.pos.y;
				} else if (src.stype == 3) { // pirticle

				}
			}, this);
		}

		public getBkMusicId(): number {
			return this.musicId;
		}
	}
}