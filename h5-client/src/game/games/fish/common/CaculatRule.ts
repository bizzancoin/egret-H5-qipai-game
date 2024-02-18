module fish {

	// 函数计算规则
	export class CaculatRule {
		public constructor() {
		}

		// 抛物线[y = ax2 + bx + c]
		public __parabola(param: any): any {
			return param.a * Math.pow(param.x, 2) + param.b * param.x + param.c;
		}

		// 三次曲线[y = ax3]
		public __acubiccve(param: any): any {
			return param.a * Math.pow(param.x, 3);
		}

		// 指数函数[y = a(x)]
		public __aexponential(param: any): any {
			return Math.pow(param.a, param.x);
		}

		// 对数函数[y = alog(x)]
		public __alogarithm(param: any): any {
			return param.a * Math.log(param.x);
		}

		// 正弦曲线[y = asin(x)]
		public __asin(param: any): any {
			return param.a * Math.sin(param.b * param.x)
		}

		// 余弦曲线[y = acos(x)]
		public __acos(param: any): any {
			return param.a * Math.cos(param.b * param.x)
		}

		// 圆[(x + a)2 + (y + b)2 = r2]
		public __circle(param: any): any {
			if (param.way == 1) {
				return Math.sqrt(Math.pow(param.r, 2) - Math.pow(param.x + param.a, 2)) - param.b
			} else {
				return 0 - Math.sqrt(Math.pow(param.r, 2) - Math.pow(param.x + param.a, 2)) - param.b
			}
		}
		// 直线[y = kx]
		public __kline(param: any): any {
			return param.k * param.x
		}
	}

	export enum CurveRuleType {
		pbl = 1,	//抛物线[y = ax2 + bx + c]
		ccve = 2,	//三次曲线[y = ax3]
		expt = 3,	//指数函数[y = a(x)]
		logh = 4,	//对数函数[y = alog(x)]
		sin = 5,	//正弦曲线[y = asin(x)]
		cos = 6,	//余弦曲线[y = acos(x)]
		cicle = 7,	//圆[(x + a)2 + (y + b)2 = r2]
		line = 8,	//直线[y = kx]	
	}

	export class frule extends BaseClass {
		private _caculatRule:CaculatRule;
		private funLst: any;

		public constructor() {
			super();
			this._caculatRule = new CaculatRule();
			this.funLst = {};
			this.funLst[CurveRuleType.pbl] = this._caculatRule.__parabola;
			this.funLst[CurveRuleType.ccve] = this._caculatRule.__acubiccve;
			this.funLst[CurveRuleType.expt] = this._caculatRule.__aexponential;
			this.funLst[CurveRuleType.logh] = this._caculatRule.__alogarithm;
			this.funLst[CurveRuleType.sin] = this._caculatRule.__asin;
			this.funLst[CurveRuleType.cos] = this._caculatRule.__acos;
			this.funLst[CurveRuleType.cicle] = this._caculatRule.__circle;
			this.funLst[CurveRuleType.line] = this._caculatRule.__kline;
		}

		public getPos(idx: number, param: any) : any {
			let pos = {x: 0, y: 0};
			if(idx) {
				let func = this.funLst[idx];
				if(func) {
					let y = func(param);
					pos.x = param.x;
					pos.y = y;
				}
			}
			return pos;
		}
	}


}
