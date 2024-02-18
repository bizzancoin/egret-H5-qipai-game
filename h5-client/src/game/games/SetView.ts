module game {
	export class SetView extends eui.Component {

		private btnClose:eui.Button;
		private tgMusic: eui.ToggleSwitch;
		private tgSound: eui.ToggleSwitch;

		private bgOnMethod: Function;
		private bgOnObj: any;

		public constructor() {
			super();
			this.skinName = 'resource/skins/SetSkin.exml';
			this.addEventListener(eui.UIEvent.COMPLETE,this.onUIComplete,this);
		}

		protected createChildren():void {
			super.createChildren();
        	this.percentHeight = 100;
        	this.percentWidth = 100;
			this.show(false);
		}

		public registerEvent(bgOnMethod: Function, bgOnObj: any): void {
			this.bgOnMethod = bgOnMethod;
			this.bgOnObj = bgOnObj;
		}

    	private onUIComplete():void {
			this.btnClose.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt:egret.TouchEvent)=>{
				App.SoundManager.playEffect("btnclick_mp3");
				this.visible = false;
			}, this);

			this.tgMusic.selected = App.SoundManager.bgOn;
			this.tgSound.selected = App.SoundManager.effectOn;

			this.tgMusic.addEventListener(eui.UIEvent.CHANGE, (evt:eui.UIEvent)=>{
				if(evt.target.selected) {
					App.SoundManager.setBgOn(true);
					if(this.bgOnMethod && this.bgOnObj) {
						this.bgOnMethod.apply(this.bgOnObj);
					}
				} else {
					App.SoundManager.setBgOn(false);
					App.SoundManager.stopBg();
				}
			}, this);

			this.tgSound.addEventListener(eui.UIEvent.CHANGE, (evt:eui.UIEvent)=>{
				if(evt.target.selected) {
					App.SoundManager.setEffectOn(true);
				} else {
					App.SoundManager.setEffectOn(false);
					App.SoundManager.stopEffect();
				}
			}, this);
		}

		public show(show:boolean): void {
			this.visible = show;
		}

	}
}