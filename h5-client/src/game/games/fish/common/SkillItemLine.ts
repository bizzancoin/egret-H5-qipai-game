module fish {
	/**
	 * 激光连线
	 */
	export class SkillItemLine extends egret.MovieClip {
		private info: any;
		public constructor(info: any) {
			super();
			this.info = info;
			this.runLine();
		}

		private runLine(): void {
			let src = FishMgr.Config.Sprite[this.info.src_id];
			if (src) {
				this.movieClipData = GameApp.getMovieDataByFileName(src.file, src.key);
			}
			this.info.parent.addChild(this);
			this.setPosAndRotate();
		}

		private setPosAndRotate(): void {
			this.x = this.info.pos1.x;
			this.y = this.info.pos1.y;
			this.anchorOffsetY = this.height / 2;
			// 计算距离
			let posDis = App.MathUtils.getDistanceByPoint(this.info.pos1, this.info.pos2); 
			let sx = posDis / this.width;
			this.scaleX = sx;

			let angle = this.getAngleByPos(this.info.pos1, this.info.pos2);
			this.rotation = angle;
			
			this.gotoAndPlay('act', -1);

			egret.Tween.get(this).wait(1000).call(function(){
				if(this.parent) {
					this.parent.removeChild(this);
				}
			}, this);
			
		}

		private getAngleByPos(pos1: any, pos2: any): number {
			let angle = 0;
			let side = this.getSideByPos(pos1, pos2);
			if (pos1.x == pos2.x) {
				if (side == 1) {
					angle = 90
				} else if (side == 4) {
					angle = 270;
				} else if (side == 2) {
					angle = 90;
				} else if (side == 3) {
					angle = 270
				}
			} else {
				let k = (pos1.y - pos2.y) / (pos1.x - pos2.x);
				angle = Math.atan(k) * 180 / Math.PI;
			}

			if (side == 1 && angle <= 0) {
				angle = -angle;
			} else if (side == 2 && angle < 0) {
				angle = 180 + angle;
			} else if (side == 3 && angle > 0) {
				angle = 180 + angle;
			} else if (side == 3 && angle <= 0) {
				angle = 180 - angle;
			} else if (side == 4 && angle <= 0) {
				angle = 2 * 180 + angle;
			}
			return angle;
		}

		private getSideByPos(pos1: any, pos2: any): number {
			let side = 0;
			let dp = { x: pos2.x - pos1.x, y: pos2.y - pos1.y };
			if (dp.x >= 0 && dp.y >= 0) {
				side = 1;
			} else if (dp.x >= 0 && dp.y <= 0) {
				side = 4;
			} else if (dp.x <= 0 && dp.y >= 0) {
				side = 2;
			} else if (dp.x <= 0 && dp.y <= 0) {
				side = 3;
			}
			return side;
		}
	}
}