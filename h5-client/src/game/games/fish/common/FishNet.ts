module fish {
	export class FishNet extends egret.MovieClip {

		private objId: number;
		private dataId: number;
		private isUse: boolean;
		private finishCall: any;

		public constructor(info: any) {
			super();
			this.init(info);
		}

		private init(info: any): void {
			this.dataId = info.id;
			this.objId = info.objId;
			this.setUseState(false);
			let netCfg = FishMgr.Config.Net[info.id];
			let netSrc = FishMgr.Config.Sprite[netCfg.src_id];
			this.movieClipData = GameApp.getMovieDataByFileName(netSrc.file, netSrc.key);
			if(netSrc.width && netSrc.height) {
				this.width = netSrc.width;
				this.height = netSrc.height;
			}
			this.anchorOffsetX = this.width / 2;
			this.anchorOffsetY = this.height / 2;
			this.touchEnabled = false;
		}

		public resumeOrgin(): void {
			this.visible = false;
			this.finishCall = null;
			this.x = 0;
			this.y = 0;
		}

		public startPlay(pos: any): void {
			this.setUseState(true);
			this.visible = true;
			if (this.parent) {
				pos = this.parent.globalToLocal(pos.x, pos.y);
			}
			this.x = pos.x;
			this.y = pos.y;
			this.addEventListener(egret.Event.COMPLETE, (e: egret.Event) => {
				if (this.finishCall) {
					this.finishCall[0].apply(this.finishCall[1], [this]);
				}
			}, this);
			this.gotoAndPlay('act');
		}

		public setFinishCall(finishCall: Function, finishObj: any): void {
			if (finishCall && finishObj) {
				this.finishCall = [finishCall, finishObj];
			}
		}

		public getDataId(): number {
			return this.dataId;
		}

		public getObjId(): number {
			return this.objId;
		}

		public setUseState(state: boolean): void {
			this.isUse = state;
		}

		public getUseState(): boolean {
			return this.isUse;
		}
	}
}