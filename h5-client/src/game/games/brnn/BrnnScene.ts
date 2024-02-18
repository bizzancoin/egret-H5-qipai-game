module brnn {
	export class BrnnScene extends BaseScene {
		public constructor() {
			super();
		}

		public onEnter(): void {
			super.onEnter();
			this.addLayer(LayerManager.UI_Main);
		}

		public onExit(): void {
			super.onExit();
			BrnnMgr.finish();
			App.SoundManager.stopBg();
			App.SoundManager.stopEffect();
		}
	}
}