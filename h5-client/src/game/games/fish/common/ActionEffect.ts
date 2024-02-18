module fish {
	export class ActionEffect extends BaseClass {
		public constructor() {
			super();
		}

		public play(obj: any, endCall: Function, effectId: number, parentObj?: any): void {
			if (obj && effectId && effectId != -1) {
				let effCfg = FishMgr.Config.Action[effectId];
				let effLst = this.analyze(effCfg);
				this.setInitParam(effCfg.init_param, obj, parentObj);
				let effLen = effLst.length;
				for (let i = 0; i < effLen; i++) {
					let len = effLst[i].length;
					let lst = effLst[i];
					let tw = egret.Tween.get(obj);
					let props = [];
					for (let j = 0; j < len; j++) {
						let p = new ActionCreater().create(lst[j].key, lst[j]);
						if (p) {
							props.push(p);
						}
					}
					if (props.length > 0) {
						props.forEach(e => {
							if(e.prop) {
								tw.to(e.prop, e.t * 1000);
							} else {
								tw.wait(e.t * 1000);
							}
						});
					}
					if (i == effLen - 1) {
						tw.call(function () {
							if (endCall) {
								endCall();
							}
						});
					}
				}
			} else {
				if (endCall) {
					endCall();
				}
			}
		}

		private analyze(data): any {
			let actLst = [];
			if (data) {
				let effCfgLst = data.eff_lst;
				let curNewIdx = 0;
				actLst.push([]);
				let effLen = effCfgLst.length;
				for (let i = 0; i < effLen; i++) {
					actLst[curNewIdx].push(effCfgLst[i]);
					if (effCfgLst[i].nxt_id == -1 && i < effLen) {
						actLst.push([]);
						curNewIdx = actLst.length;
					}
				}
			}
			return actLst;
		}

		private isCanReserver(actKey: string): boolean {
			if (actKey == "move_by"
				|| actKey == "scale_by"
				|| actKey == "rotate_by"
				|| actKey == "skew_by"
				|| actKey == "jump_by"
				|| actKey == "tint_by"
			) {
				return true
			}
			return false
		}

		private setInitParam(param: any, obj: any, parentObj: any): void {
			if (param && obj) {
				if (param.angle) {
					obj.rotation = param.angle;
				}
				if (param.sax) {
					obj.scaleX = param.sax;
				}
				if (param.say) {
					obj.scaleY = param.say;
				}
				if (param.skx) {
					obj.skewX = param.skx;
				}
				if (param.sky) {
					obj.skewY = param.sky;
				}
			}
		}
	}
}