module fish {
	/**
	 * 获取配置文件
	 */
	export class FishConfigManager extends BaseClass {

		private curFish: string;
		private config: any;

		public constructor() {
			super();
			// jc || lk
			this.curFish = FishMgr.fishModule;
			this.config = RES.getRes(this.curFish + '_fish_config_json');
		}

		// 获取金蟾捕鱼或李逵劈鱼资源
		private getConfig(key: string): any {
			return this.config[key];
		}

		public get Battery(): any {
			return this.getConfig('Battery');
		}

		public get BatteryPos(): any {
			return this.getConfig('BatteryPos');
		}

		public get Action(): any {
			return this.getConfig('Action');
		}

		public get Background(): any {
			return this.getConfig('Background');
		}

		public get BgMusic(): any {
			return this.getConfig('BgMusic');
		}

		public get BondingBox(): any {
			return this.getConfig('BondingBox');
		}

		public get Bullet(): any {
			return this.getConfig('Bullet');
		}

		public get Curve(): any {
			return this.getConfig('Curve');
		}

		public get Fish(): any {
			return this.getConfig('Fish');
		}

		public get FishGold(): any {
			return this.getConfig('FishGold');
		}

		public get LabelFnt(): any {
			return this.getConfig('LabelFnt');
		}

		public get Net(): any {
			return this.getConfig('Net');
		}

		public get Water(): any {
			return this.getConfig('Water');
		}

		public get Music(): any {
			return this.getConfig('Music');
		}

		public get Skill(): any {
			return this.getConfig('Skill');
		}

		public get Sprite(): any {
			return this.getConfig('Sprite');
		}

		public get System(): any {
			return this.getConfig('System');
		}

		public get Sound(): any {
			return this.getConfig('Sound');
		}
	}
}