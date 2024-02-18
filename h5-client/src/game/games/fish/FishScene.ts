module fish {
	export class FishScene extends BaseScene {
		public constructor() {
			super();
		}

		public onEnter(): void {
			super.onEnter();
			this.addLayer(LayerManager.UI_Main);
		}

		public onExit(): void {
			super.onExit();
			FishMgr.finish();
			App.SoundManager.stopBg();
			App.SoundManager.stopEffect();
		}
	}
}