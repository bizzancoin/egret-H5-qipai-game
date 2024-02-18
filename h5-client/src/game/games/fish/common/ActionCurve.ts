module fish {
	export class ActionCurve {
		readonly piAngle = 180;
		readonly dt = 0.001;
		private startPos = { x: 0, y: 0 };
		private ratio: number = 1920 / 100;
		// 运动方向， 右为正方向（1），右为负方向（-1）
		private curDefWay: number = 1;
		// 在当前曲线上运动的时间
		private curActionTime: number = 0;
		private breserve: boolean = false;
		// 正为顺时针方向 0 ~ 360 负为逆时针方向 -360 ~ 0
		private rotateAngle: number = 0;
		private curveLst: any[] = [];

		// 构造函数
		public constructor(info: any) {
			this.curveLst = [];
			if (info.spos) {
				this.startPos = info.pos;
			}
			this.ratio = 1920 / 100;
			if (info.ratio) {
				this.ratio = info.ratio;
			}
			if (!this.ratio) {
				this.ratio = 1;
			}
			this.curDefWay = 1;
			this.curActionTime = 0;
			this.breserve = false;
			this.rotateAngle = 0;
		}

		/** 
		 * 添加一条曲线
			cveinfo = {
				t,
				x1,
				x2,
				ftype,
				fparam = {a,b,c,way,r,k},
			}
		*/
		public addCurve(cveinfo: any): boolean {
			if (cveinfo) {
				let fitem: any = {};
				fitem.time = cveinfo.t;
				fitem.s = cveinfo.x2 - cveinfo.x1
				fitem.v = fitem.s / fitem.time;
				fitem.ftype = cveinfo.ftype;
				fitem.fparam = GameApp.clone(cveinfo.fparam);
				fitem.x1 = cveinfo.x1;
				fitem.x2 = cveinfo.x2;
				if (this.breserve) {
					fitem.s = cveinfo.x1 - cveinfo.x2;
					fitem.v = fitem.s / fitem.time;
					fitem.x1 = cveinfo.x2;
					fitem.x2 = cveinfo.x1;
				}

				let lstLen = this.curveLst.length;
				if (lstLen <= 0) {
					fitem.t1 = 0;
					fitem.t2 = fitem.t1 + fitem.time;
					this.curveLst.push(fitem);
					return true;
				} else {
					let lastItem = this.curveLst[lstLen - 1];
					if (lastItem.x2 == fitem.x1) {
						fitem.t1 = lastItem.t2;
						fitem.t2 = fitem.t1 + fitem.time;
						this.curveLst.push(fitem);
						return true;
					}
				}
			}
			return false;
		}

		public getAllTime(): number {
			let time = 0;
			App.ArrayUtils.forEach(this.curveLst, function (item) {
				time += item.time;
			}, this)
			return time;
		}

		public setStartPos(pos: any): void {
			this.startPos.x = pos.x;
			this.startPos.y = pos.y;
		}

		public setRatio(ratio: number): void {
			this.ratio = ratio;
			if (!this.ratio || this.ratio <= 0) {
				this.ratio = 1;
			}
		}

		// 通过时间判断当前计算规则函数
		private getFunctionRuleByTime(t: number): any {
			let index = 0;
			let len = this.curveLst.length;
			if (len <= 0) return null;
			let lastItem = this.curveLst[len - 1];
			if (t > lastItem.t2) {
				return lastItem;
			}
			for (let i: number = 0; i < len; i++) {
				let item = this.curveLst[i];
				if (t >= item.t1 && t < item.t2) {
					index = i;
					break;
				}
			}
			return this.curveLst[index];
		}

		// 通过时间计算当前绝对坐标
		private getPositionByTime(t: number): any {
			let index = 0;
			let pos = { x: 0, y: 0 };
			let len = this.curveLst.length;
			if (len <= 0) return pos;

			let lastItem = this.curveLst[len - 1];
			if (t > lastItem.t2) {
				let param = {
					"a": lastItem.fparam.a,
					"b": lastItem.fparam.b,
					"c": lastItem.fparam.c,
					"way": lastItem.fparam.way,
					"r": lastItem.fparam.r,
					"k": lastItem.fparam.k,
					"x": lastItem.v * lastItem.t2
				};

				let tmpPos = (<frule>frule.getInstance()).getPos(lastItem.ftype, param);
				pos.x = pos.x + tmpPos.x;
				pos.y = pos.y + tmpPos.y;
				if (lastItem.x1 > lastItem.x2) {
					this.curDefWay = -1;
				} else {
					this.curDefWay = 1;
				}
			} else {
				for (let i = 0; i < this.curveLst.length; i++) {
					let item = this.curveLst[i];
					if (t >= item.t1 && t < item.t2) {
						let param = {
							"a": item.fparam.a,
							"b": item.fparam.b,
							"c": item.fparam.c,
							"way": item.fparam.way,
							"r": item.fparam.r,
							"k": item.fparam.k,
							"x": item.v * (t - item.t1)
						};

						let tmpPos = (<frule>frule.getInstance()).getPos(item.ftype, param);
						pos.x = pos.x + tmpPos.x;
						pos.y = pos.y + tmpPos.y;
						if (item.x1 > item.x2) {
							this.curDefWay = -1;
						} else {
							this.curDefWay = 1;
						}
						break;
					} else {
						let param = {
							"a": item.fparam.a,
							"b": item.fparam.b,
							"c": item.fparam.c,
							"way": item.fparam.way,
							"r": item.fparam.r,
							"k": item.fparam.k,
							"x": item.v * item.time
						};

						let tmpPos = (<frule>frule.getInstance()).getPos(item.ftype, param);
						pos.x = pos.x + tmpPos.x;
						pos.y = pos.y + tmpPos.y;
					}
				}
			}

			if (this.rotateAngle && this.rotateAngle != 0) {
				let newX = pos.x * Math.cos(this.rotateAngle / this.piAngle * Math.PI) + pos.y * Math.sin(this.rotateAngle / this.piAngle * Math.PI);
				let newY = pos.y * Math.cos(this.rotateAngle / this.piAngle * Math.PI) - pos.x * Math.sin(this.rotateAngle / this.piAngle * Math.PI);
				pos.x = newX;
				pos.y = newY;
			}

			return { x: this.startPos.x + pos.x, y: this.startPos.y + pos.y };
		}

		// 通过时间计算当前坐标和斜率k
		private getPosAndSlopeByTime(t: number): any {
			this.curActionTime = t;
			let pos0 = this.getPositionByTime(t);
			let pos1 = this.getPositionByTime(t + this.dt);
			if (this.rotateAngle && this.rotateAngle != 0) {
				if (pos0.x > pos1.x) {
					this.curDefWay = -1;
				} else {
					this.curDefWay = 1;
				}
			}

			let dx = pos1.x - pos0.x;
			let dy = pos1.y - pos0.y;

			if (Math.abs(dx) <= 0.000001) {
				if (this.rotateAngle && this.rotateAngle != 0) {
					if (pos0.y <= pos1.y) {
						this.curDefWay = -1
					} else {
						this.curDefWay = 1
					}
				}

				return { pos: pos0, k: null };
			}

			return { pos: pos0, k: dy / dx };
		}

		// 通过时间计算当前坐标和偏转角度
		public getPosAndAngleByTime(t: number): any {
			this.curActionTime = t;
			let ret = this.getPosAndSlopeByTime(t);
			let angle = 0;
			if (ret.k != null) {
				if (this.curDefWay == 1) {
					let tmpAngle = Math.atan(ret.k) * this.piAngle / Math.PI;
					angle = 2 * this.piAngle - Math.atan(ret.k) * this.piAngle / Math.PI;
				} else {
					let tmpAngle = Math.atan(ret.k) * this.piAngle / Math.PI;
					angle = this.piAngle - Math.atan(ret.k) * this.piAngle / Math.PI;
				}
			} else {
				if (this.curDefWay == -1) {
					angle = 270;
				} else {
					angle = 90;
				}
			}
			return { pos: ret.pos, angle: angle };
		}

		public isCurveEnd(): boolean {
			let isEnd = true;
			let lastItem = this.curveLst[this.curveLst.length - 1];
			if (lastItem) {
				if (lastItem.t2 > this.curActionTime) {
					isEnd = false;
				}
			}
			return isEnd;
		}

		public resetInfo(): void {
			this.curActionTime = 0;
		}

		public setActionReserve(bres: boolean): void {
			if (bres != null) {
				this.breserve = bres;
				if (this.breserve) {
					App.ArrayUtils.forEach(this.curveLst, function (item) {
						let x1 = item.x1;
						item.x1 = item.x2;
						item.x2 = item.x1
						item.s = item.x2 - item.x1;
						item.v = item.s / item.time;
					}, this)
				}
			}
		}

		public setActionRotation(angle: number): void {
			if (angle && (angle >= 0 && angle <= 360)) {
				this.rotateAngle = angle;
			}
		}

		public clearAllCurve(): void {
			this.curveLst = [];
		}

		public setCurveRatio(ratio: number): void {
			if (ratio) {
				this.ratio = ratio;
			}
		}
	}
}