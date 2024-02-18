module fish {
	/**
	 * 动作创建器
	 */
	export class ActionCreater {
		private actionArray: any = {};
		public constructor() {
			this.actionArray = {};
			this.actionArray['move_to'] = this.__move_to;
			this.actionArray['move_by'] = this.__move_by;
			this.actionArray['scale_to'] = this.__scale_to;
			this.actionArray['scale_by'] = this.__scale_by;
			this.actionArray['rotate_to'] = this.__rotate_to;
			this.actionArray['rotate_by'] = this.__rotate_by;
			this.actionArray['skew_to'] = this.__skew_to;
			this.actionArray['skew_by'] = this.__skew_by;
			this.actionArray['jump_to'] = this.__jump_to;
			this.actionArray['jump_by'] = this.__jump_by;
			this.actionArray['blink'] = this.__move_to;
			this.actionArray['fade_in'] = this.__move_to;
			this.actionArray['fade_out'] = this.__move_to;
			this.actionArray['tint_to'] = this.__tint_by;
			this.actionArray['tint_by'] = this.__tint_to;
			this.actionArray['delay'] = this.__delay;
			this.actionArray['shake'] = this.__shake;
			this.actionArray['ffshake'] = this.__fallofshake;
			this.actionArray['circle'] = this.__circle;
		}

		public create(key, cfg): any {
			if (this.actionArray[key]) {
				return this.actionArray[key](cfg);
			}
			return null;
		}

		private __move_to(cfg: any): any {
			return { prop: { x: cfg.param.pos.x, y: cfg.param.pos.y }, t: cfg.param.t }
		}

		private __move_by(cfg: any): any {
			if (cfg.loop == -1) {
				return { prop: { x: cfg.param.pos.x * 1000000, y: cfg.param.pos.y * 1000000 }, t: cfg.param.t * 1000000 };
			} else {
				return { prop: { x: cfg.param.pos.x * cfg.loop, y: cfg.param.pos.y * cfg.loop }, t: cfg.param.t * cfg.loop };
			}
		}

		private __scale_to(cfg: any): any {
			return { prop: { scaleX: cfg.sx, scaleY: cfg.sy }, t: cfg.param.t };
		}

		private __scale_by(cfg: any): any {
			if (cfg.loop == -1) {
				return { prop: { scaleX: cfg.param.sx * 1000000, scaleY: cfg.param.sy * 1000000 }, t: cfg.param.t * 1000000 };
			} else {
				return { prop: { scaleX: cfg.param.sx * cfg.loop, scaleY: cfg.param.sy * cfg.loop }, t: cfg.param.t * cfg.loop };
			}
		}

		private __rotate_to(cfg: any): any {
			return { prop: { rotation: cfg.angle }, t: cfg.param.t };
		}

		private __rotate_by(cfg: any): any {
			if (cfg.loop == -1) {
				return { prop: { rotation: cfg.param.angle * 1000000 }, t: cfg.param.t * 1000000 };
			} else {
				return { prop: { rotation: cfg.param.angle * cfg.loop }, t: cfg.param.t * cfg.loop };
			}
		}

		private __skew_to(cfg: any): any {
			return { prop: { skewX: cfg.sx, skewY: cfg.sy }, t: cfg.param.t };
		}

		private __skew_by(cfg: any): any {
			if (cfg.loop == -1) {
				return { prop: { skewX: cfg.param.sx * 1000000, skewY: cfg.param.sy * 1000000 }, t: cfg.param.t * 1000000 };
			} else {
				return { prop: { skewX: cfg.param.sx * cfg.loop, skewY: cfg.param.sy * cfg.loop }, t: cfg.param.t * cfg.loop };
			}
		}

		private __jump_to(cfg: any): any {
			return null;
		}

		private __jump_by(cfg: any): any {
			return null;
		}

		private __blink(cfg: any): any {
			return null;
		}

		private __fade_in(cfg: any): any {
			return { prop: { alpha: 1 }, t: cfg.param.t };
		}

		private __fade_out(cfg: any): any {
			return { prop: { alpha: 0 }, t: cfg.param.t };
		}

		private __tint_to(cfg: any): any {
			return null;
		}

		private __tint_by(cfg: any): any {
			return null;
		}

		private __delay(cfg: any): any {
			return {t: cfg.param.t };
		}

		private __shake(cfg: any) {
			return null;
		}

		private __fallofshake(cfg: any) {
			return null;
		}

		private __circle(cfg: any) {
			return null;
		}
	}
}