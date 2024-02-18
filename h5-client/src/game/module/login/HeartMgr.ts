// 心跳处理
class HeartMgr extends BaseClass {
	public constructor() {
		super();
	}

	private startHeart: boolean;
	private sendHeart: boolean;
	private timer: egret.Timer;
	private invalidTime = 0;
	private sumTime = 0;
	private timeOut = 50; // 50秒超时
	private pingRate = 10; // 10 pin一次

	public init(): void {
		this.sendHeart = true;
		if (!this.timer) {
			this.timer = new egret.Timer(1000);
		}
		if (!this.startHeart) {
			if (!this.timer.hasEventListener(egret.TimerEvent.TIMER)) {
				this.timer.addEventListener(egret.TimerEvent.TIMER, this.doTimer, this);
			}
			this.timer.start();
			this.startHeart = true;
		}
	}

	public pause(): void {
		this.sendHeart = false;
		this.invalidTime = 0;
		this.sumTime = 0;
		if (this.timer) {
			this.timer.stop();
		}
	}

	public resume(): void {
		this.sendHeart = false;
		this.invalidTime = 0;
		this.sumTime = 0;
		if (this.timer) {
			this.timer.start();
		}
	}

	public uninit(): void {
		this.startHeart = false;
		this.sendHeart = false;
		this.invalidTime = 0;
		this.sumTime = 0;
		this.timer.removeEventListener(egret.TimerEvent.TIMER, this.doTimer, this);
	}

	private doTimer(): void {
		if (this.sendHeart) {
			this.invalidTime++;
			this.sumTime++;
			if (this.sumTime > this.pingRate) {
				this.send_ReqHeartbeat();
				this.sumTime = 0;
			}

			if (this.invalidTime >= this.timeOut) {
				egret.log('心跳超时');
				this.sendHeart = false;
			}
		}
	}

	private send_ReqHeartbeat(): void {
		let body = {
			id: 1
		};
		let self = this;
		App.Socket.send(body, function (msgid, msg) {
			if (msgid == body.id) {
				self.invalidTime = 0;
			} else {
				//
			}
		}, this);
	}
}