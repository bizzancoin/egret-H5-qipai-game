module fish {
	export class FishGold extends egret.MovieClip {

		private info: any;
		private objId: number;
		private dataId: number;
		private isUse: boolean;
		private endPos: any = { x: 0, y: 0 };
		private startPos: any = { x: 0, y: 0 };
		private finishCall: any;

		public constructor(info: any) {
			super();
			this.info = info;
			this.init();
		}

		public resumeOrgin(): void {
			this.visible = false;
			this.startPos = { x: 0, y: 0 };
			this.endPos = { x: 0, y: 0 };
			this.finishCall = null;
			this.x = 0;
			this.y = 0;
		}

		private init(): void {
			this.dataId = this.info.id;
			this.objId = this.info.objId;

			let goldCfg = FishMgr.Config.FishGold[this.info.id];
			let src = FishMgr.Config.Sprite[goldCfg.src_id];

			this.movieClipData = GameApp.getMovieDataByFileName(src.file, src.key);
			if (goldCfg.width && goldCfg.height) {
				this.width = goldCfg.width;
				this.height = goldCfg.height;
			}
			this.anchorOffsetX = this.width / 2;
			this.anchorOffsetY = this.height / 2;
			this.visible = false;
			this.touchEnabled = false;
		}

		public startPlay(startPos: any, endPos: any): void {
			this.setUseState(true);
			this.x = startPos.x;
			this.y = startPos.y;

			this.startPos = startPos;
			this.endPos = endPos;

			this.visible = true;
			this.gotoAndPlay('act', -1);
			let moveTime = this.getEndMoveTime();

			egret.Tween.get(this).to({ x: this.endPos.x, y: this.endPos.y }, moveTime * 1000)
				.call(function () {
					this.gotoAndStop('act');
					if (this.finishCall) {
						this.finishCall[0].apply(this.finishCall[1], [this]);
					}
				}, this);
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

		private getEndMoveTime(): number {
			let dis = App.MathUtils.getDistanceByPoint(this.startPos, this.endPos);

			return dis / FishMgr.Config.System['gold_move_v'];
		}

	}
}