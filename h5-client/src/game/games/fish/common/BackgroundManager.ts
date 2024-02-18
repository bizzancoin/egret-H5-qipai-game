module fish {
	export class BackgroundManager extends BaseClass {

		private groupContent: eui.Group;
		private groupWater: eui.Group;
		private config: any;
		private curScene: fish.Background;
		private frontScene: fish.Background;
		private curState: number;
		private switchTime: number;
		private curSceneId: number = -1;
		private frontSceneId: number = -1;
		private useWaterWave: boolean = true;

		private waveMc: egret.MovieClip;

		public constructor() {
			super();
		}

		public init(groupContent: eui.Group, groupWater: eui.Group): void {
			this.groupContent = groupContent;
			this.groupWater = groupWater;
			this.config = FishMgr.Config.Background['common'];
			this.curState = 0; // 背景场景状态  0 无场景，1 场景切换完成，2 正在切换场景
			this.switchTime = this.config.cut_t;
			this.createSeawaveObj();
			this.createWaterObj();
		}

		public destroy(): void {
			this.groupContent = null;
			this.config = null;
			this.curScene = null;
			this.frontScene = null;
			this.curState = -1;
			this.switchTime = -1;
			this.curSceneId = -1;
			this.frontSceneId = -1;
			this.useWaterWave = true;
		}

		public switchBaground(sceneId: number): void {
			FishMgr.BatteryManager.clearAllLockState();
			if (this.curScene) {
				this.frontScene = this.curScene;
				this.curScene = null;
			}
			let bgInfo = FishMgr.Config.Background["data"][sceneId - 1];
			if (bgInfo) {
				this.curScene = new fish.Background();
				this.groupContent.addChild(this.curScene);
				this.frontSceneId = this.curSceneId;
				this.curSceneId = sceneId;
				this.curScene.create(this.curState, this.switchTime, bgInfo);
				this.waveMc.x = 1920 - this.waveMc.width / 4;
				this.groupContent.swapChildren(this.curScene, this.waveMc);
			}
			if (this.curState == 0) {
				this.waveMc.visible = false;
			} else {
				this.waveMc.visible = true;
				this.waveMc.gotoAndPlay('act', -1);
			}
			if (this.curState == 1) {
				FishMgr.MusicManager.play(this.config.mus_id);
				let moveTime = (this.waveMc.width * 0.75 + 1920) / 1920 * this.switchTime;
				egret.Tween.get(this.waveMc).to({ x: - this.waveMc.width }, moveTime * 1000).call(function () {
					this.waveMc.visible = false;
					if (this.frontScene) {
						this.groupContent.removeChild(this.frontScene);
						this.frontScene = null;
					}
					this.curState = 1;
					this.waveMc.gotoAndStop('act');
					FishMgr.FishManager.clearAllFish()
				}, this);
				this.curState = 2;
			} else {
				this.curState = 1;
				FishMgr.FishManager.clearAllFish()
			}
		}

		public getSceneState(): number {
			return this.curState;
		}
		public getCurrentSceneId(): number {
			return this.curSceneId;
		}

		public initState(state: number): void {
			this.curState = state;
		}

		private createSeawaveObj(): void {
			let waveSrc = FishMgr.Config.Sprite[this.config.wave_src_id];
			this.waveMc = GameApp.getClipByFileName(waveSrc.file, waveSrc.key);
			this.groupContent.addChild(this.waveMc);
			this.waveMc.x = 1920 - this.waveMc.width / 4;
			this.waveMc.visible = false;
		}

		private createWaterObj(): void {
			let waterCfg = FishMgr.Config.Water;
			for (let i = 0; i < waterCfg.length; i++) {
				let src = waterCfg[i];
				let mc = GameApp.getClipByFileName(src.pattern, 'water');
				mc.touchEnabled = false;
				mc.anchorOffsetX = mc.width / 2;
				mc.anchorOffsetY = mc.height / 2;
				mc.x = src.pos.x;
				mc.y = src.pos.y;
				this.groupWater.addChild(mc);
				mc.gotoAndPlay('act', -1);
			}
		}

		public playBkMusic(): void {
			FishMgr.MusicManager.play(this.config.mus_id);
		}
	}
}