module fish {
	export class SkillManager extends BaseClass {

		private skillFuncLst: any[];
		private onShakeFunc: Function;
		public constructor() {
			super();
		}

		public init(onShakeFunc: Function): void {
			this.skillFuncLst = [];
			this.onShakeFunc = onShakeFunc;
			this.skillFuncLst.push({ func: this.shakeScreen, skillIdLst: [1] });
			this.skillFuncLst.push({ func: this.rotateScore, skillIdLst: [2] });
			this.skillFuncLst.push({ func: this.lineFish, skillIdLst: [3] });
			this.skillFuncLst.push({ func: null, skillIdLst: [4] });
			this.skillFuncLst.push({ func: this.bigFishBom, skillIdLst: [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26] });
		}

		public destroy(): void {
			this.skillFuncLst = [];
			this.onShakeFunc = null;
		}

		public play(obj: any, id: number): boolean {
			for (let i = 0; i < this.skillFuncLst.length; i++) {
				let data = this.skillFuncLst[i];
				for (let j = 0; j < data.skillIdLst.length; j++) {
					if (id == data.skillIdLst[j]) {
						if (data.func) {
							data.func(obj, id);
							return true;
						}
					}
				}
			}
			return false;
		}

		private shakeScreen(obj: any, skillId: number): void {
			let cfg = FishMgr.Config.Skill[skillId];
			if (cfg) {
				//let time = cfg.t * cfg.loop;
				// 全屏振动
				if(FishMgr.SkillManager.onShakeFunc) {
					FishMgr.SkillManager.onShakeFunc();
				}
			}
		}

		private rotateScore(obj: any, skillId: number): void {
			let seatId = obj.getShotSeatId();
			let battery = FishMgr.BatteryManager.getBatteryBySeatId(seatId);
			if (seatId) {
				let info = {
					id: skillId,
					parent: battery,
					pos: FishMgr.Config.System['rotate_score_pos'],
					score: obj.getFishScore()
				}
				new RotateScore(info);
			}
		}

		private lineFish(obj: any, skillId: number): void {
			let pos = obj.getCurrentPos();
			let lineObjLst = obj.getLineRelObjLst();
			let src = FishMgr.Config.Skill[skillId];
			for (let i = 0; i < lineObjLst.length; i++) {
				let endPos = lineObjLst[i].getCurrentPos();
				let info = { pos1: pos, pos2: endPos, parent: obj.parent, src_id: src.line_src_id };
				let line = new SkillItemLine(info);
				lineObjLst[i].setCurrentLineObj(line);
			}
		}

		private bigFishBom(obj: any, skillId: number): void {
			let pos = obj.getCurrentPos();
			let cfg = FishMgr.Config.Skill[skillId];
			for (let i = 0; i < cfg.src_id_lst.length; i++) {
				
			}
		}

	}
}