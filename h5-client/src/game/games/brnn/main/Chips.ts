module brnn {

	export class ChipsManager extends BaseClass {
		public constructor() {
			super();
		}

		public preCreated(info: any): Chip {
			return new Chip(info);
		}

		public get(dataId: any, pos: any): Chip {
			let chip: Chip = BrnnMgr.ObjectPool.getObject(ObjectClassType.Chips, dataId);
			chip.x = pos.x;
			chip.y = pos.y;
			chip.visible = true;
			chip.setUseState(true);
			return chip;
		}
	}


	export class Chip extends eui.Image {

		private objId: number;
		private dataId: string;
		private _tag: string;
		private isUse: boolean;

		public constructor(info: any) {
			super();
			this.objId = info.objId;
			this.dataId = info.id;
			this.init();
		}

		private init(): void {
			let res = 'brnn_atlas1_json.' + this.dataId;
			this.texture = RES.getRes(res);
			this.scaleX = 0.8;
			this.scaleY = 0.8;
			this.anchorOffsetX = this.width / 2;
			this.anchorOffsetY = this.height / 2;
			this.touchEnabled = false;
			this.visible = false;
			this.setUseState(false);
		}

		public resumeOrgin(): void {
			this.visible = false;
			this.setUseState(false);
			if (this.parent) {
				this.parent.removeChild(this);
			}
			this.x = 0;
			this.y = 0;
		}

		public set tag(value: string) {
			this._tag = value;
		}

		public get tag(): string {
			return this._tag;
		}

		public getDataId(): string {
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
	}

	export class BigCardsManager extends BaseClass {
		public constructor() {
			super();
		}

		public preCreated(info: any): Card {
			return new Card(info, true);
		}

		public get(dataId: any, pos: any): Card {
			let card: Card = BrnnMgr.ObjectPool.getObject(ObjectClassType.BigCards, dataId);
			card.x = pos.x;
			card.y = pos.y;
			card.visible = true;
			card.setUseState(true);
			return card;
		}
	}

	export class SmallCardsManager extends BaseClass {
		public constructor() {
			super();
		}

		public preCreated(info: any): Card {
			return new Card(info, false);
		}

		public get(dataId: any, pos: any): Card {
			let card: Card = BrnnMgr.ObjectPool.getObject(ObjectClassType.SmallCards, dataId);
			card.x = pos.x;
			card.y = pos.y;
			card.visible = true;
			card.setUseState(true);
			return card;
		}
	}

	export class Card extends eui.Image {
		private objId: number;
		private dataId: string;
		private isUse: boolean;

		public constructor(info: any, isBig: boolean) {
			super();
			this.objId = info.objId;
			this.dataId = info.id;
			this.init(isBig);
		}

		private init(isBig: boolean): void {
			let cfg = BrnnConfig.CardData[this.dataId];
			let res = isBig ? cfg.card_big : cfg.card_sm;
			this.texture = RES.getRes(res);
			this.anchorOffsetX = this.width / 2;
			this.anchorOffsetY = this.height / 2;
			this.touchEnabled = false;
			this.visible = false;
			this.setUseState(false);
		}

		public resumeOrgin(): void {
			this.visible = false;
			this.setUseState(false);
			if (this.parent) {
				this.parent.removeChild(this);
			}
			this.x = 0;
			this.y = 0;
		}

		public getDataId(): string {
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
	}
}