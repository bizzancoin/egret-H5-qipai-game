module baccarat {
	export class BaccaratScene extends BaseScene {
		public constructor() {
			super();
		}

		public onEnter(): void {
			super.onEnter();
			this.addLayer(LayerManager.UI_Main);
		}

		public onExit(): void {
			super.onExit();
			App.SoundManager.stopBg();
			App.SoundManager.stopEffect();
			BaccaratMgr.finish(true);
		}
	}
}