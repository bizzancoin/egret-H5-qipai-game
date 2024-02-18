module game {

	export enum ExchangeType {
		CashOut = 1,  // 兑出
		CashIn		  // 兑进
	}

	export class ExchangeView extends eui.Component {

		private groupSelect: eui.Group;
		private groupExchange: eui.Group;
		private btnClose1: eui.Button;
		private btnClose2: eui.Button;
		private btnCashOut: eui.Button;
		private btnCashIn: eui.Button;
		private btnSure: eui.Button;

		private lbTitle: eui.Label;
		private lbExchange: eui.Label;
		private lbTotal: eui.Label;
		private lbRate: eui.Label;

		private exchangeType: ExchangeType;

		private safeGold: number = 0; // 余额
		private chips: number = 0;    // 筹码
		private roomInfo: any;        // 房间信息

		private sliderExchange: eui.HSlider;

		private sureMethod:Function;
		private sureMethodObj:any;

		public constructor() {
			super();
			this.skinName = 'resource/skins/ExchangeSkin.exml';
			this.addEventListener(eui.UIEvent.COMPLETE,this.onUIComplete,this);
    	}

    	protected createChildren():void {
			super.createChildren();
        	this.percentHeight = 100;
        	this.percentWidth = 100;
		}

    	private onUIComplete():void {
			this.btnClose1.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt:egret.TouchEvent)=>{
				App.SoundManager.playEffect("btnclick_mp3");
				this.visible = false;
			}, this);
			this.btnClose2.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt:egret.TouchEvent)=>{
				App.SoundManager.playEffect("btnclick_mp3");
				this.visible = false;
			}, this);
			this.btnCashIn.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt:egret.TouchEvent)=>{
				App.SoundManager.playEffect("btnclick_mp3");
				this.lbTitle.text = '下分';
				this.exchangeType = ExchangeType.CashIn;
				this.initView();
			}, this);
			this.btnCashOut.addEventListener(egret.TouchEvent.TOUCH_TAP, (evt:egret.TouchEvent)=>{
				App.SoundManager.playEffect("btnclick_mp3");
				this.lbTitle.text = '上分';
				this.exchangeType = ExchangeType.CashOut;
				this.initView();
			}, this);
			this.sliderExchange.addEventListener(eui.UIEvent.CHANGE, this.changeHandler, this);
			this.btnSure.addEventListener(egret.TouchEvent.TOUCH_TAP, this.sureHandler, this);
    	}

		private changeHandler(evt:eui.UIEvent): void {
			switch(this.exchangeType) {
				case ExchangeType.CashOut:
					this.lbExchange.text = '选择金币：' + GameApp.chips2MoneyNum(evt.target.value) + ', 兑换 ' + GameApp.chips2MoneyNum(evt.target.value) * this.roomInfo.proportionChips / this.roomInfo.proportionGold + ' 筹码';
					break;
				case ExchangeType.CashIn:
					this.lbExchange.text = '选择筹码：' + evt.target.value + ', 兑换 ' + GameApp.chips2Money(GameApp.money2Chips(evt.target.value * this.roomInfo.proportionGold / this.roomInfo.proportionChips)) + ' 金币';
					break;
			}
		}

		private sureHandler(evt:egret.TouchEvent): void {
			this.sureMethod.apply(this.sureMethodObj, [this.exchangeType, this.sliderExchange.value]);
			this.hide();
		}

		private initView():void {
			this.groupSelect.visible = false;
			this.groupExchange.visible = true;
			this.lbExchange.text = '兑换筹码：0';
			switch(this.exchangeType) {
				case ExchangeType.CashOut:
					this.lbRate.text = '金币:筹码 = ' + this.roomInfo.proportionGold + ':' + this.roomInfo.proportionChips;
					this.lbTotal.text = '剩余金币：' + GameApp.chips2Money(this.safeGold);
					this.sliderExchange.maximum = this.safeGold;
					this.sliderExchange.enabled = this.safeGold > 0;
					this.btnSure.enabled = this.safeGold > 0;
					break;
				case ExchangeType.CashIn:
					this.lbRate.text = '筹码:金币 = ' + this.roomInfo.proportionChips + ':' + this.roomInfo.proportionGold;
					this.lbTotal.text = '剩余筹码：' + this.chips;
					this.sliderExchange.maximum = this.chips;
					this.sliderExchange.enabled = this.chips > 0;
					this.btnSure.enabled = this.chips > 0;
					break;
			}
			this.sliderExchange.value = 0;
		}

		public registerEvent(method, methodObj): void {
			this.sureMethod = method;
			this.sureMethodObj = methodObj;
		}

		// 传入保险箱金币和当前筹码
		public show(saveGold: number, curChips: number, roomInfo: any): void {
			if(this.visible) {
				return;
			}
			this.safeGold = saveGold;
			this.chips = curChips;
			this.roomInfo = roomInfo;
			this.groupSelect.visible = true;
			this.groupExchange.visible = false;
			this.visible = true;
		}

		public hide(): void {
			this.visible = false;
		}

	}
}