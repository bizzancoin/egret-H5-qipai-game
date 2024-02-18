module brnn {
	// 下注按钮
	export class BetBtn extends eui.Component {

		private btnChip1: eui.Button;
		private btnChip2: eui.Button;
		private btnChip3: eui.Button;
		private btnChip4: eui.Button;
		private btnChip5: eui.Button;
		private btnChip6: eui.Button;
		private btnChip7: eui.Button;
		private btnChip8: eui.Button;

		private btnChips: eui.Button[];

		private onBetHandler: Function;
		private onBetObj: any;
		private betOptions: any;

		public constructor() {
			super();
			this.skinName = 'resource/skins/brnn/BrnnBetBtnSkin.exml';
			this.btnChips = [];
		}

		public registerListener(): void {
			this.btnChips.push(this.btnChip1, this.btnChip2, this.btnChip3, this.btnChip4, this.btnChip5, this.btnChip6, this.btnChip7, this.btnChip8);
			this.btnChips.forEach(btn => {
				btn.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onClickHandler, this);
			});
			for (let i = 0; i < this.btnChips.length; i++) {
				this.btnChips[i].enabled = false;
			}
		}

		public initBetOptions(betOptions: any): void {
			this.betOptions = betOptions;
			if (betOptions && betOptions.length > 0) {
				for (let i = 0; i < this.btnChips.length; i++) {
					let label: eui.Label = <eui.Label>this.btnChips[i].getChildAt(1);
					label.text = betOptions[i];
				}
			}
		}

		public setOnBetHandler(method: Function, methodObj: any): void {
			this.onBetHandler = method;
			this.onBetObj = methodObj;
		}

		public updateBtnStatus(totalChips): void {
			for (let i = 0; i < this.btnChips.length; i++) {
				let requireChips = this.betOptions[i];
				if (totalChips >= requireChips) {
					this.btnChips[i].enabled = true;
				} else {
					this.btnChips[i].enabled = false;
				}
			}
		}

		private onClickHandler(evt: egret.TouchEvent): void {
			let idx = this.btnChips.indexOf(evt.target);
			if (this.onBetHandler && this.onBetObj) {
				this.onBetHandler.apply(this.onBetObj, [idx + 1]);
			}
		}
	}
}