class MarqueeManager extends BaseClass {

	private msgArray: any[];
	private msgQueue: any[];
	private content: egret.Sprite;
	private imgBg: eui.Image;

	private speed: number = 200;
	private isRuning: boolean = false;
	private timeOnEnterFrame: number;
	private text: egret.TextField;
	private lastInterval: number = 0;
	private isInterval: boolean = false;

	private runingMsg: any;
	private lastRunTime: number;

	public constructor() {
		super();
		this.msgArray = [];
		this.msgQueue = [];
		this.init();
	}

	private init(): void {
		this.content = new egret.Sprite();
		this.content.graphics.beginFill(0x000000, 0);
		this.content.graphics.drawRect(0, 0, App.StageUtils.getWidth(), App.StageUtils.getHeight());
		this.content.graphics.endFill();

		this.imgBg = new eui.Image();
		this.imgBg.texture = RES.getRes('common_marquee_bg_png');
		this.imgBg.width = 1097;
		this.imgBg.height = 99;
		this.imgBg.x = (App.StageUtils.getWidth() - this.imgBg.width) / 2;
		this.imgBg.y = 60;

		// 可见区域
		let rectMask = new egret.Rectangle(this.imgBg.x, this.imgBg.y, 1097, 99);
		this.content.mask = rectMask;
		this.content.addChild(this.imgBg);
		this.timeOnEnterFrame = egret.getTimer();
		egret.startTick(this.update, this);
	}


	public start(msg?: any): void {
		if (msg) {
			this.msgQueue.push({interval: 0, runing: false, msg: msg});
			if (msg.times > 1) {
				for (let i: number = 0; i < msg.times; i++) {
					this.msgArray.push(msg);
				}
			} else {
				this.msgArray.push(msg);
			}
		}
	}

	

	private _run2(dt): void {
		this._updateInterval(dt);
		if (this.msgQueue.length <= 0 && this.isRuning == false) {
			if (this.content.parent) {
				this.content.parent.removeChild(this.content)
			}
			return;
		}
		if(!this.text) {
			let runMsg = this._getMsg();
			if(runMsg) {
				this.text = this.createText(runMsg.msg, this.imgBg.x + this.imgBg.width, this.imgBg.y);
				this.content.addChild(this.text);
				this.isRuning = true;
				runMsg.runing = true;
				this.runingMsg = runMsg;
				if (!this.content.parent) {
					App.StageUtils.getStage().addChild(this.content);
				}
			} else {
				let maxInterval = this._getMaxInterval();
				if(maxInterval > 3) {
					if (this.content.parent) {
						this.content.parent.removeChild(this.content)
					}
				}
			}
		} else {
			this.text.x -= dt * this.speed;
			if(this.text.x < this.imgBg.x - this.text.width) {
				if (this.text.parent) {
					this.text.parent.removeChild(this.text);
					this.text = null;
					this.isRuning = false;
					if(this.runingMsg) {
						this.runingMsg.runing = false;
					}
				}
			}
		}
	}

	private _getMsg(): any {
		this._removeMsg(); // 删除无用的信息
		for(let i = 0;i < this.msgQueue.length; i++) {
			let tmpMsg = this.msgQueue[i];
			if(tmpMsg.interval > 0 || tmpMsg.msg.times <= 0) continue;
			tmpMsg.msg.times -= 1;
			tmpMsg.interval = tmpMsg.msg.interval;
			return tmpMsg;
		}
		return null;
	}

	private _removeMsg(): void {
		let length = this.msgQueue.length;
		for(let i = length - 1; i >= 0; i--) {
			let tmp = this.msgQueue[i];
			if(tmp.msg.times <= 0) {
				this.msgQueue.splice(i, 1);
			}
		}
	}

	private _updateInterval(dt):void {
		for(let i = 0;i < this.msgQueue.length; i++) {
			let tmpMsg = this.msgQueue[i];
			if(tmpMsg.runing == false) {
				tmpMsg.interval -= dt;
				if(tmpMsg.interval < 0) {
					tmpMsg.interval = 0;
				}
			}
		}
	}

	private _getMaxInterval(): number {
		let interval = 0;
		for(let i = 0;i < this.msgQueue.length; i++) {
			let tmpMsg = this.msgQueue[i];
			if(tmpMsg.runing == false) {
				if(tmpMsg.interval > interval) {
					interval = tmpMsg.interval;
				}
			}
		}
		return interval;
	}


	private update(timeFrame): boolean {
		let dt = (timeFrame - this.timeOnEnterFrame) / 1000;
		this.timeOnEnterFrame = timeFrame;
		//this._run(dt);
		this._run2(dt);
		return true;
	}

	private _run(dt): void {
		if(this.isInterval && this.msgArray.length > 0) {
			this.lastInterval -= dt;
			if (this.content.parent) {
				this.content.parent.removeChild(this.content)
			}
			if(this.lastInterval <= 0) {
				this.lastInterval = 0;
				this.isInterval = false;
				this.isRuning = false;
			}
			return;
		}
		if (this.msgArray.length <= 0 && this.isRuning == false) {
			if (this.content.parent) {
				this.content.parent.removeChild(this.content)
			}
			return;
		}
		if (!this.content.parent) {
			App.StageUtils.getStage().addChild(this.content);
		}
		if(!this.text && this.msgArray.length > 0) {
			this.isRuning = true;
			let runMsg = this.msgArray.shift();
			this.text = this.createText(runMsg, this.imgBg.x + this.imgBg.width, this.imgBg.y);
			this.lastInterval = runMsg.interval;
			this.content.addChild(this.text);
		} else {
			this.text.x -= dt * this.speed;
			if(this.text.x < this.imgBg.x - this.text.width) {
				if (this.text.parent) {
					this.text.parent.removeChild(this.text);
					this.text = null;
					if(this.lastInterval > 0) {
						this.isInterval = true;
					} else {
						this.isRuning = false;
					}
				}
			}
		}
	}


	private createText(msg: any, startX: number, startY: number): egret.TextField {
		var text: egret.TextField = new egret.TextField();
		text.text = msg.content;
		let color = 0xffffff;
		switch (msg.color) {
			case 'red':
				color = 0xff0000;
				break;
			case 'yellow':
				color = 0xffff00;
				break;
			default:
				break;
		}
		text.textColor = color;
		text.size = 50;
		text.bold = true;
		text.x = startX + text.width;
		text.y = startY + text.height / 2;
		return text;
	}

}