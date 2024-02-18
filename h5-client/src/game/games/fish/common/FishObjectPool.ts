module fish {
	export class FishObjectPool extends BaseClass {

		private objectClsssLst: any = {};
		private default_size = 5;

		public constructor() {
			super();
		}

		public init() {
			Object.keys(ObjectClassType).filter(key => {
				if (isNaN(Number(ObjectClassType[key]))) {
					this.objectClsssLst[key] = {
						objId: 0,
						objLst: {},
						creater: null
					}
				}
			})
		}

		public destroy() {
			this.objectClsssLst = {};
		}

		public registerObjectCreater(cls: any, creater: any) {
			let item = this.objectClsssLst[cls];
			if (item) {
				item.creater = creater;
			}
		}

		public newSomeObject(cls: number, dataId: any, number: number): void {
			let item = this.objectClsssLst[cls];
			if (item && item.creater) {
				for (let i = 0; i < number; i++) {
					item.objId += 1;
					let createInfo = { objId: item.objId, id: dataId };
					let creater = item.creater;
					let newObj = creater.preCreated(createInfo);
					item.objLst[item.objId] = newObj;
				}
			}
		}

		public getObject(cls: number, dataId: any): any {
			let obj = null;
			let item = this.objectClsssLst[cls];
			if (item) {
				for (var p in item.objLst) {
					let v = item.objLst[p];
					if (v && !v.getUseState() && v.getDataId() == dataId) {
						obj = v;
						break;
					}
				}
			}
			if (!obj) {
				this.newSomeObject(cls, dataId, this.default_size);
				item = this.objectClsssLst[cls];
				if (item) {
					obj = item.objLst[item.objId];
				}
			}

			return obj;
		}
	}

	export enum ObjectClassType {
		TypeFish = 1,  //鱼
		TypeBullet ,  //子弹
		TypeNet,         //鱼网
		TypeGold,        //金币
		TypeLabel,       //鱼字体
	}
}