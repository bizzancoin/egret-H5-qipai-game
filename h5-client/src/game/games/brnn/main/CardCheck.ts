module brnn {
	export class CardCheck {
		public checkMaxNiuNiuPower(list: any): number {
			if (this.checkIsWuXiao(list)) {
				return CardsKinds.WU_XIAO;
			} else if (this.checkIsWuHua(list)) {
				return CardsKinds.WU_HUA;
			} else if (this.checkIsSiZha(list)) {
				return CardsKinds.SI_HUA;
			} else if (this.checkIsSiHua(list)) {
				return CardsKinds.SI_ZHA;
			} else {
				return this.getNormalNiuNiuMaxPower(list);
			}
		}

		// 五小
		public checkIsWuXiao(list: any): boolean {
			let totalPower = 0;
			for (let i = 0; i < list.lenth; i++) {
				if (BrnnConfig.CardData[i].power > 6) {
					return false;
				}

				totalPower += BrnnConfig.CardData[i].power;
			}
			if (totalPower <= 10) {
				return true;
			}
			return false;
		}

		// 五花
		public checkIsWuHua(list: any): boolean {
			for (let i = 0; i < list.lenth; i++) {
				if (BrnnConfig.CardData[i].power < 11 || BrnnConfig.CardData[i].power > 13) {
					return false;
				}

				return true;
			}
		}

		// 四花
		public checkIsSiHua(list: any): boolean {
			let num = 0;
			let haveTen = false;
			for (let i = 0; i < list.lenth; i++) {
				if (BrnnConfig.CardData[i].power >= 11 || BrnnConfig.CardData[i].power <= 13) {
					return false;
				}

				if (BrnnConfig.CardData[i].power == 10) {
					haveTen = true;
				}
			}
			return num == 4 && haveTen;
		}

		public checkIsSiZha(list: any): boolean {
			let tmp: any = {};
			for (let i = 0; i < list.lenth; i++) {
				if (tmp[BrnnConfig.CardData[i].power]) {
					tmp[BrnnConfig.CardData[i].power]++;
				} else {
					tmp[BrnnConfig.CardData[i].power] = 1;
				}
			}
			if (Object.keys(tmp).length == 2 && (tmp[Object.keys(tmp)[0]] == 1 || tmp[Object.keys(tmp)[0]] == 4)) {
				return true
			} else {
				return false;
			}
		}

		public getNormalNiuNiuMaxPower(list: any): number {
			let maxPower = 0; // 牌行点数
			let totalPower = 0;
			let kingNum = 0;
			let tmpList = [];
			for (let i = 0; i < list.lenth; i++) {
				totalPower += BrnnConfig.CardData[i].mathpower;
				if (BrnnConfig.CardData[i].type == CardType.DA_WANG || BrnnConfig.CardData[i].type == CardType.XIAO_WANG) {
					kingNum++;
				} else {
					tmpList.push(BrnnConfig.CardData[i]);
				}
			}
			if (kingNum == 0) { // 没有王
				for (let i = 0; i < 5; i++) {
					for (let j = i; j < 5; j++) {
						for (let k = j; k < 5; k++) {
							let power = tmpList[i].mathpower + tmpList[j].mathpower + tmpList[k].mathpower;
							if (power % 10 == 0) {
								power = (totalPower - power) % 10 // 剩余点数
								if (power > maxPower) {
									maxPower = power;
								}
							}
						}
					}
				}
			} else if (kingNum == 1) { // 有一张王， 必是有牛  从剩余四张中选出两张加起来最大
				for (let i = 0; i < 4; i++) {
					for (let j = i; j < 4; j++) {
						let power = tmpList[i].mathpower + tmpList[j].mathpower;
						if (power % 10 > maxPower) {
							maxPower = power;
						}
					}
				}
			} else if (kingNum == 2) { // 有两张王，必是牛牛
				maxPower = CardsKinds.NIU_NIU;
			}

			return maxPower;
		}
	}

	export enum CardsKinds {
		MEI_NIU = 0, //没牛
		NIU_1 = 1, //牛1
		NIU_2 = 2, //牛2
		NIU_3 = 3, //牛3
		NIU_4 = 4, //牛4
		NIU_5 = 5, //牛5
		NIU_6 = 6, //牛6
		NIU_7 = 7, //牛7
		NIU_8 = 8, //牛8
		NIU_9 = 9, //牛9
		NIU_NIU = 10, //牛牛
		SI_HUA = 11, //四花
		SI_ZHA = 12, //四炸
		WU_HUA = 13, //五花
		WU_XIAO = 14, //五小
	}
}