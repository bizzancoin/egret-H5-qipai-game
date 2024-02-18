module brnn {
	export class MainModel {
		public roomInfo: any; // 房间信息 {}
		public isEnterScene: boolean = false;
		public applicants: any; // 申请人列表 [1,2,3,4,5]
		public histories: any;  // 历史输赢
		public gameStage: any; // 当前游戏阶段 {state, time}  1:休息,2:下注, 3:发牌
		public banker: any;    // 当前庄家信息 {playerId, gold ....}
		public billInfo: any; // 结算信息

		public lastBetChips: any = {}; // {area:{chips}}  上一把的下注情况

		public curBetChipsMap: any = {}; // 当前玩家下注

		public chips: any; // 我的筹码
		public seatOrder: number; // 我的座位
		public selectedChipIdx: number; // 当前选中筹码

		public totalBetChips: any = {}; // 所有玩家的下注信息
		public totalBetChipsMap: any = {}; // 所有下注的筹码信息

		private members: any;

		public constructor() {
			this.lastBetChips = {};
			this.curBetChipsMap = {};
			this.chips = 0;
			this.seatOrder = 0;
			this.selectedChipIdx = null;
			this.totalBetChipsMap = {};
		}

		// 添加牌桌玩家
		public addMember(mem: any): void {
			if (!this.members) {
				this.members = {};
			}
			if(mem && mem.playerId) {
				if (GameApp.PlayerInfo.isMySelf(mem.playerId)) {
					this.seatOrder = mem.order;
					this.chips = mem.chips.toNumber();
				}
				this.members[mem.playerId.toNumber()] = mem;
			}
		}

		// 移除牌桌玩家
		public removeMember(playerId): void {
			if (this.members) {
				delete this.members[playerId];
			}
		}

		public getMember(playerId): any {
			if (this.members) {
				return this.members[playerId];
			}
			return null;
		}

		public updateMemberChips(playerId, chips): void {
			if (this.members) {
				let member = this.members[playerId];
				if (member) {
					member.chips = chips;
				}
			}
		}

		public applyList(): any {
			let lst = [];
			if (this.applicants && this.applicants.length && this.members) {
				this.applicants.forEach(e => {
					let mem = this.getMember(e.toNumber());
					if (mem) {
						if(GameApp.PlayerInfo.isMySelf(e)) {
							lst.push({ nickname: GameApp.PlayerInfo.nickName, gold: this.chips });
						} else {
							lst.push({ nickname: mem.playerName, gold: mem.gold.toNumber() });
						}
					}
				});
			}
			return lst;
		}

		public isApply(): boolean {
			if (this.applicants) {
				for (let i = 0; i < this.applicants.length; i++) {
					if (this.applicants[i].equals(GameApp.PlayerInfo.id)) {
						return true;
					}
				}
			}
			return this.isBanker();
		}

		public isBanker(): boolean {
			return this.banker && GameApp.PlayerInfo.isMySelf(this.banker.playerId);
		}

		public get state() {
			return this.gameStage ? this.gameStage.state : 0;
		}

		public get countDown() {
			return this.gameStage ? this.gameStage.time : 0;
		}

		public descTime() {
			if (this.gameStage && this.gameStage.time > 0) {
				this.gameStage.time -= 1;
			}
		}

		public getBankerIcon(): any {
			if(!this.banker) {
				return null;
			}
			let member = this.getMember(this.banker.playerId.toNumber());
			if(member) {
				return member.icon;
			}
			return null;
		}

		public isBet(): boolean {
			if (this.curBetChipsMap && Object.keys(this.curBetChipsMap).length > 0) {
				let requireChips = 0;
				for (let p in this.curBetChipsMap) {
					if (this.curBetChipsMap[p]) {
						requireChips += this.curBetChipsMap[p];
					}
				}
				return requireChips > 0;
			}

			return false;
		}

		public getCurBetRequireChips(): number {
			if (this.curBetChipsMap && Object.keys(this.curBetChipsMap).length > 0) {
				let requireChips = 0;
				for (let p in this.curBetChipsMap) {
					if (this.curBetChipsMap[p]) {
						requireChips += this.curBetChipsMap[p];
					}
				}
				return requireChips;
			}

			return 0;
		}

		public isChipsEnoughForApplyBanker() {
			return this.chips >= this.roomInfo.beBankerChips.toNumber();
		}

		public isCanContinue(): boolean {
			if (this.lastBetChips && Object.keys(this.lastBetChips).length > 0) {
				let requireChips = 0;
				for (let p in this.lastBetChips) {
					if (this.lastBetChips[p]) {
						requireChips += this.lastBetChips[p];
					}
				}
				return this.chips > requireChips;
			}

			return false;
		}

		public getContinueRequireChips(): number {
			if (this.lastBetChips && Object.keys(this.lastBetChips).length > 0) {
				let requireChips = 0;
				for (let p in this.lastBetChips) {
					if (this.lastBetChips[p]) {
						requireChips += this.lastBetChips[p];
					}
				}
				return requireChips;
			}

			return 0;
		}
	}
}