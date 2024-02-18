module fish {
	export class Fish extends eui.Group {
		private objId: number;
		private dataId: number;
		private startPos: any = { x: 0, y: 0 };
		private fishCfg: any;
		private bbxCfg: any;
		private curveCfg: any;
		private fishCurve: ActionCurve;
		private runTime = 0;                // 运动的时间
		private curPos = { x: 0, y: 0 };  // 当前运动到的点（屏幕坐标）
		private curAngle = 0;               // 当前偏转角度

		private preObjId: number;

		private goldEndPos = { x: 0, y: 0 }; //金币飞往的最后位置
		private userInfo: any = {       // 用户数据
			playerId: -1,
			seatId: 1
		};
		private fishScore = 0;     // 鱼分数
		private curLineObjLst: any[] = [];//当前关联鱼对象列表
		private curLineObj = null; //当前连线的对象
		private fishLevel = 0;   // 鱼等级
		private sceneId = -1;
		private aliveLst: any[];
		private deathLst: any[];

		private isUse: boolean;

		public constructor(info: any) {
			super();
			this.initData(info);
			this.initUI();
			// this.createBoundbox();
			this.touchEnabled = false;
		}

		public initData(info: any): void {
			this.preObjId = info.objId;
			this.dataId = info.id;
			this.fishCfg = FishMgr.Config.Fish[info.id];
			this.bbxCfg = FishMgr.Config.BondingBox[this.fishCfg.bbx_id];

			if (this.fishCfg.cve_id != -1) {
				this.curveCfg = FishMgr.Config.Curve[this.fishCfg.cve_id];
			}

			let cveInfo: any = {};
			if (this.curveCfg) {
				cveInfo = { spos: this.startPos, ratio: this.curveCfg.ratio };
			}
			this.fishCurve = new ActionCurve(cveInfo);
			if (this.curveCfg) {
				this.curveCfg.data.forEach(e => {
					this.fishCurve.addCurve(e);
				});
				this.setCurveRotation(this.curveCfg.angle);
			}

			this.runTime = 0;
			this.curPos = { x: 0, y: 0 };
			this.curAngle = 0;
			this.goldEndPos = { x: 0, y: 0 };;
			this.userInfo = {
				playerId: -1,
				seatId: 1
			};
			this.fishScore = 0;;
			this.curLineObjLst = [];
			this.curLineObj = null;
			this.fishLevel = 0;
			this.sceneId = -1;

			this.aliveLst = [];
			this.deathLst = [];
		}

		private initUI(): void {
			this.createFishItem(this.fishCfg.alive, this.aliveLst);
			this.createFishItem(this.fishCfg.death, this.deathLst);
		}

		public resumeOrgin(): void {
			this.setUseState(false);
			this.userInfo = {
				playerId: -1,
				seatId: 1
			};
			this.runTime = 0;
			this.goldEndPos = { x: 0, y: 0 };
			this.fishCurve.resetInfo();
			if (this.curLineObj) {
				if (this.curLineObj.parent) {
					this.curLineObj.parent.removeChild(this.curLineObj);
				}
			}
			this.curLineObjLst = [];
			if (this.parent) {
				this.parent.removeChild(this);
			}
		}

		private createBoundbox(): void {
			for (let j = 0; j < this.bbxCfg.data.length; j++) {
				let n = this.bbxCfg.data[j];
				var shp: egret.Shape = new egret.Shape();
				shp.graphics.beginFill(0xff0000);
				shp.graphics.drawCircle(n.a, n.b, n.r);
				shp.graphics.endFill();
				shp.width = n.r;
				shp.height = n.r;
				this.addChild(shp);
			}
		}

		private runStartEff(itemLst: any[]): void {
			itemLst.forEach(e => {
				if (e.stype == 1) {
					if (e.spr) {
						e.spr.gotoAndPlay('act', e.loop);
					}
				} else if (e.stype == 2) {

				} else if (e.stype == 3) {

				}
				let self = this;
				if (e.spr) {
					FishMgr.ActionEffect.play(e.spr, function () {
						self.startEffEndCall();
					}, e.act_id)
				}
				FishMgr.MusicManager.play(FishMgr.randomSelectId(this.fishCfg.alive.mus_lst));
				for (let i = 0; i < this.fishCfg.alive.skl_id_lst.length; i++) {
					FishMgr.SkillManager.play(this, this.fishCfg.alive.skl_id_lst[i]);
				}
			});
		}

		private startEffEndCall(): void {

		}

		private runDeadEff(itemLst: any[]): void {
			itemLst.forEach(e => {
				if (e.stype == 1) {
					if (e.spr && e.spr instanceof egret.MovieClip) {
						e.spr.gotoAndPlay('act', e.loop);
					}
				} else if (e.stype == 2) {

				} else if (e.stype == 3) {

				}
				let self = this;
				if (e.spr) {
					FishMgr.ActionEffect.play(e.spr, function () {
						self.deadEffEndCall();
					}, e.act_id)
				}
			});
			FishMgr.FishLabelManager.play(this.getCurrentPos(), this.fishScore);
			FishMgr.MusicManager.play(FishMgr.randomSelectId(this.fishCfg.death.mus_lst));
			for (let i = 0; i < this.fishCfg.death.skl_id_lst.length; i++) {
				FishMgr.SkillManager.play(this, this.fishCfg.death.skl_id_lst[i]);
			}
		}

		private deadEffEndCall(): void {
			if (this.parent) {
				let info = {
					startPos: this.getCurrentPos(),
					angle: this.curAngle,
					number: this.fishScore,
					endPos: GameApp.clone(this.goldEndPos)
				}
				FishMgr.FishGoldManager.play(info);
				this.resumeOrgin();
			}
		}

		private createFishItem(data: any, itemLst: any[]) {
			let len = data.view_inf.length;
			for (let i = 0; i < len; i++) {
				let src = FishMgr.Config.Sprite[data.view_inf[i].src_id];
				let item: any = {};
				item.loop = data.view_inf[i].loop;
				item.act_id = data.view_inf[i].act_id;
				item.src_id = data.view_inf[i].src_id;
				item.stype = src.stype;
				item.sspos = data.view_inf[i].pos;
				item.ani_key = src.key;

				if (src.stype == 1) {
					let mc = GameApp.getClipByFileName(src.file, src.key);
					if (src.width && src.height) {
						mc.width = src.width;
						mc.height = src.height;
					}
					mc.anchorOffsetX = mc.width / 2;
					mc.anchorOffsetY = mc.height / 2;
					mc.touchEnabled = false;
					item.spr = mc;
					this.addChild(item.spr);
					item.spr.x = data.view_inf[i].pos.x + this.width / 2;
					item.spr.y = data.view_inf[i].pos.y + this.height / 2;
					item.spr.visible = true;
				} else if (src.stype == 2) {
					let img = new eui.Image();
					img.texture = RES.getRes(src.file);
					img.anchorOffsetX = img.width / 2;
					img.anchorOffsetY = img.height / 2;
					item.spr = img;
					img.touchEnabled = false;
					this.addChild(item.spr);
					item.spr.x = data.view_inf[i].pos.x + this.width / 2;
					item.spr.y = data.view_inf[i].pos.y + this.height / 2;
					item.spr.visible = true;
				} else if (src.styoe == 3) { // 粒子

				}
				itemLst.push(item);
			}
		}


		public setFishCurveId(id: number): void {
			if (id && id != -1) {
				let cfg = FishMgr.Config.Curve[id];
				if (cfg) {
					this.curveCfg = cfg;
					this.fishCurve.clearAllCurve();
					this.fishCurve.setCurveRatio(this.curveCfg.ratio);
					this.curveCfg.data.forEach(e => {
						this.fishCurve.addCurve(e);
					});
					this.setCurveRotation(this.curveCfg.angle);
				}
			}
		}

		public setStartPos(pos: any): void {
			this.startPos = pos;
			this.fishCurve.setStartPos(pos);
			this.setPos(pos);
		}

		private setPos(pos: any): void {
			if (pos && pos.x && pos.y) {
				if (this.parent) {
					pos = this.parent.globalToLocal(pos.x, 1080 - pos.y);
				}
				switch (FishMgr.seatId) {
					case 1:
						pos.y = 1080 - pos.y;
						break;
					case 2:
						pos.y = 1080 - pos.y;
						pos.x = 1920 - pos.x;
						break;
					case 3:
						pos.x = 1920 - pos.x;
						//pos.y = 1080 - pos.y;
						break;
					case 4:
						//pos.y = 1080 - pos.y;
						break;
				}
				this.x = pos.x;
				this.y = pos.y;
			}

		}

		private setFishPosAndAngle(pos: any, angle: number) {
			this.setPos(pos);
			if (angle) {
				switch (FishMgr.seatId) {
					case 1:
						angle = - angle;
						break;
					case 2:
						angle = 180 + angle;
						break;
					case 3:
						angle = 180 - angle;
						break;
					case 4:
						break;
				}
				this.curAngle = angle;
				this.rotation = angle;
			}
		}

		public isOutofTime(): boolean {
			return this.fishCurve.isCurveEnd();
		}

		public setRunTime(t: number): void {
			if (t) {
				this.runTime = t;
			}
		}

		private showAliveSpr(show: boolean): void {
			this.aliveLst.forEach(e => {
				e.spr.visible = show;
			});
		}

		private showDeadSpr(show: boolean): void {
			this.deathLst.forEach(e => {
				e.spr.visible = show;
			});
		}

		public fishDead(): void {
			this.showAliveSpr(false);
			this.showDeadSpr(true);
			this.runDeadEff(this.deathLst);
		}

		public fishOut(): void {
			this.showAliveSpr(false);
			this.showDeadSpr(false);
			/*this.runTime = 0;
			this.goldEndPos = { x: 0, y: 0 };
			this.fishCurve.resetInfo();*/
			this.resumeOrgin();
		}

		public fishStart(): void {
			this.showAliveSpr(true);
			this.showDeadSpr(false);
			this.runStartEff(this.aliveLst);
			let data = this.fishCurve.getPosAndAngleByTime(0.001);
			if (data.pos && data.pos.x && data.pos.y) {
				this.curPos = data.pos;
			}
			if (data.angle) {
				this.curAngle = data.angle;
			}
			this.setFishPosAndAngle(data.pos, data.angle);
		}



		public setCurveRotation(angle: number): void {
			this.fishCurve.setActionRotation(angle);
		}

		public setReserve(bres: boolean): void {
			this.fishCurve.setActionReserve(bres);
		}

		public setUserInfo(userInfo: any): void {
			if (userInfo) {
				this.userInfo = userInfo;
			}
		}

		public setFishSceneId(sceneId: number): void {
			this.sceneId = sceneId;
		}

		public isShotFish(bbx: any): boolean {
			if (this.curPos) {
				let pos = this.parent ? this.parent.localToGlobal(this.x, this.y) : { x: this.x, y: this.y };
				for (let i = 0; i < bbx.length; i++) {
					let v = bbx[i];
					for (let j = 0; j < this.bbxCfg.data.length; j++) {
						let n = this.bbxCfg.data[j];
						let angle = -this.curAngle;
						let newA = n.a * Math.cos(angle / 180 * Math.PI) + n.b * Math.sin(angle / 180 * Math.PI);
						let newB = n.b * Math.cos(angle / 180 * Math.PI) - n.a * Math.sin(angle / 180 * Math.PI);
						let tmpPos = { x: newA + pos.x, y: newB + pos.y };
						let dis = App.MathUtils.getDistance(tmpPos.x, tmpPos.y, v.a, v.b);
						if (v.r + n.r > dis) {
							return true;
						}
					}
				}
			}
			return false;
		}

		public getCurrentBBX(): any {
			let bbx = GameApp.clone(this.bbxCfg.data);
			let pos = this.getCurrentPos();
			for (let i = 0; i < this.bbxCfg.data.length; i++) {
				let v = this.bbxCfg.data[i];
				let aggle = -this.curAngle;
				bbx[i].a = v.a * Math.cos(aggle / 180 * Math.PI) + v.b * Math.sin(aggle / 180 * Math.PI) + pos.x;
				bbx[i].b = v.b * Math.cos(aggle / 180 * Math.PI) - v.a * Math.sin(aggle / 180 * Math.PI) + pos.y;
			}
			return bbx;
		}


		public getObjId(): number {
			return this.objId;
		}

		public setObjId(objId) {
			this.objId = objId;
		}

		public getDataId(): number {
			return this.dataId;
		}

		public getCurrentPos(): any {
			if (this.parent) {
				return this.parent.localToGlobal(this.x, this.y);
			}
			return { x: this.x, y: this.y };
		}

		public setShotSeatId(seatId: number) {
			this.userInfo.seatId = seatId;
		}

		public getShotSeatId(): number {
			return this.userInfo.seatId;
		}

		public getLockLevel(): number {
			return this.fishCfg.lock_level;
		}

		public addLineRelObj(obj: any): void {
			if (obj) {
				this.curLineObjLst.push(obj);
			}
		}

		public getLineRelObjLst(): any {
			return this.curLineObjLst;
		}

		public setFishLevel(level: number): void {
			if (level) {
				this.fishLevel = level;
			}
		}

		public getFishLevel(): number {
			return this.fishLevel;
		}

		public setFishScore(score: number): void {
			this.fishScore = score;
		}

		public getFishScore(): number {
			return this.fishScore;
		}

		public setGoldMoveEndPos(pos: any): void {
			this.goldEndPos = { x: pos.x, y: pos.y };
		}


		public getFishSceneId(): number {
			return this.sceneId
		}

		public setCurrentLineObj(obj: any): void {
			this.curLineObj = obj;
		}

		public getCurrentLineObj(): any {
			return this.curLineObj;
		}

		public update(dt) {
			if (FishMgr.BackgroundManager.getSceneState() == 2 && this.sceneId != FishMgr.BackgroundManager.getCurrentSceneId()
				&& this.sceneId != -1) {
				dt *= FishMgr.Config.System['cut_scene_fish_vx'];
			}
			this.runTime += dt;
			let data = this.fishCurve.getPosAndAngleByTime(this.runTime);
			if (data.pos && data.pos.x && data.pos.y) {
				this.curPos = data.pos;
			}
			if (data.angle) {
				this.curAngle = data.angle;
			}
			this.setFishPosAndAngle(data.pos, data.angle);
		}

		public setUseState(state: boolean): void {
			this.isUse = state;
		}

		public getUseState(): boolean {
			return this.isUse;
		}


	}
}