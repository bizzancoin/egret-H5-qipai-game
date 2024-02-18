module baccarat {
	export class BaccaratWayBillView extends eui.Component {
		private btnShowLoad: eui.Button;
		private tabLoad: eui.TabBar;
		private loaded: boolean;

		//百家乐面板
		private grpBJLLoad: eui.Group;
		private btnBLoadBJL: eui.Button;
		private btnBLoadLH: eui.Button;

		private vsBJLMainLoad: eui.ViewStack;
		private grpBJLZXMainLoad: eui.Group;
		private grpBJLDXMainLoad: eui.Group;
		private mainItemBJLArray: Array<baccarat.BaccaratMainWayItem>;
		private bigItemBJLArray: Array<baccarat.BaccaratBigWayItem>;
		private bigEyeItemBJLArray: Array<baccarat.BaccaratBigEyeWayItem>;
		private smallItemBJLArray: Array<baccarat.BaccaratSmallWayItem>;
		private smallStrongItemBJLArray: Array<baccarat.BaccaratSmallStrongItem>;

		private grpBJLBigWay: eui.Group; //大路单
		private grpBJLBigEyeWay: eui.Group; //大眼单
		private grpBJLSmallWay: eui.Group; //小路单和小强单

		private lbBJLGameNum: eui.Label;
		private lbBJLHeNum: eui.Label;
		private lbBJLZhuangNum: eui.Label;
		private lbBJLZPairNum: eui.Label;
		private lbBJLXianNum: eui.Label;
		private lbBJLXPairNum: eui.Label;

		private btnBLoadZWL: eui.Button;
		private btnBLoadXWL: eui.Button;
		private imgSmallIconZhuang: eui.Image;
		private imgXQIconZhuang: eui.Image;
		private imgSmallIconXian: eui.Image;
		private imgXQIconXian: eui.Image;
		//龙虎
		private grpLHLoad: eui.Group;
		private btnLLoadBJL: eui.Button;
		private btnLLoadLH: eui.Button;

		private grpLHMainLoad: eui.Group;
		private mainItemLHArray: Array<baccarat.BaccaratMainWayItem>;
		private bigItemLHArray: Array<baccarat.BaccaratBigWayItem>;
		private bigEyeItemLHArray: Array<baccarat.BaccaratBigEyeWayItem>;
		private smallItemLHArray: Array<baccarat.BaccaratSmallWayItem>;
		private smallStrongItemLHArray: Array<baccarat.BaccaratSmallStrongItem>;

		private grpLHBigWay: eui.Group; //大路单
		private grpLHBigEyeWay: eui.Group; //大眼单
		private grpLHSmallWay: eui.Group; //小路单和小强单

		private lbLHGameNum: eui.Label;
		private lbLHHeNum: eui.Label;
		private lbLHLongNum: eui.Label;
		private lbLHHuNum: eui.Label;

		private btnLoadLWL: eui.Button;
		private btnLoadHWL: eui.Button;
		private imgSmallIconLong: eui.Image;
		private imgXQIconLong: eui.Image;
		private imgSmallIconHu: eui.Image;
		private imgXQIconHu: eui.Image;

		private statInfo: any;
		private billInfo: any;
		private bigItemData: any;

		private bigLHItemData: any;

		private m_max: number;
		private m_max_list: Array<number>;

		public constructor() {
			super();
			this.loaded = false;
			this.addEventListener(eui.UIEvent.COMPLETE, this.onUIComplete, this);
			this.skinName = "resource/skins/game/baccarat/BJLWayBillSkin.exml"
		}

		private onUIComplete(): void {
			this.loaded = true;
			this.touchEnabled = true;
			this.m_max_list = new Array<number>();
			this.btnShowLoad.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onShowClick, this);
			this.tabLoad.addEventListener(egret.Event.CHANGE, this.loadChange, this);

			this.btnBLoadBJL.addEventListener(egret.TouchEvent.TOUCH_TAP, this.bjlClick, this);
			this.btnBLoadLH.addEventListener(egret.TouchEvent.TOUCH_TAP, this.lhClick, this);
			this.btnLLoadBJL.addEventListener(egret.TouchEvent.TOUCH_TAP, this.bjlClick, this);
			this.btnLLoadLH.addEventListener(egret.TouchEvent.TOUCH_TAP, this.lhClick, this);
			this.btnBLoadLH.visible = false;
			this.btnLLoadBJL.visible = false;
			this.btnLLoadLH.visible = false;

			this.btnBLoadXWL.addEventListener(egret.TouchEvent.TOUCH_TAP, this.xianAskWayClick, this);
			this.btnBLoadZWL.addEventListener(egret.TouchEvent.TOUCH_TAP, this.zhuangAskWayClick, this);
			this.btnLoadLWL.addEventListener(egret.TouchEvent.TOUCH_TAP, this.longAskWayClick, this);
			this.btnLoadHWL.addEventListener(egret.TouchEvent.TOUCH_TAP, this.huAskWayClick, this);

			this.mainItemBJLArray = new Array<baccarat.BaccaratMainWayItem>();
			this.bigItemBJLArray = new Array<baccarat.BaccaratBigWayItem>();
			this.bigEyeItemBJLArray = new Array<baccarat.BaccaratBigEyeWayItem>();
			this.smallItemBJLArray = new Array<baccarat.BaccaratSmallWayItem>();
			this.smallStrongItemBJLArray = new Array<baccarat.BaccaratSmallStrongItem>();

			this.mainItemLHArray = new Array<baccarat.BaccaratMainWayItem>();
			this.bigItemLHArray = new Array<baccarat.BaccaratBigWayItem>();
			this.bigEyeItemLHArray = new Array<baccarat.BaccaratBigEyeWayItem>();
			this.smallItemLHArray = new Array<baccarat.BaccaratSmallWayItem>();
			this.smallStrongItemLHArray = new Array<baccarat.BaccaratSmallStrongItem>();
		}
		private onShowClick(evt: egret.Event): void {
			// if (this.y != 0) {
			// 	let data = this.exchangeBaccaratBigItemData(this.billInfo.hisWaybill)
			// 	this.showExChangeImg(data)
			// 	this.bigItemData = this.getBigItemData(data, false);
			// 	this.renderView();
			// }
			this.grpBJLLoad.visible = true;
			this.grpLHLoad.visible = false;
			let y = this.y == 0 ? -950 : 0;
			egret.Tween.get(this).to({ y: y }, 500);
		}
		private loadChange(evt: egret.Event): void {
			this.vsBJLMainLoad.selectedIndex = this.tabLoad.selectedIndex;
		}
		public setData(data: any): void {
			this.billInfo = data;
			if (this.loaded) {
				this.renderView();
			}
		}
		public setStat(data: any): void {
			this.statInfo = data;
			if (this.loaded) {
				this.renderView();
			}
		}
		public renderView(): void {
			if (this.billInfo) {
				this.vsBJLMainLoad.selectedIndex = this.tabLoad.selectedIndex;
				this.renderBView();
				this.renderLView();
			}
			if (this.statInfo) {
				this.lbBJLGameNum.text = this.statInfo.score.toString();
				this.lbBJLHeNum.text = this.statInfo.tie.toString();
				this.lbBJLZhuangNum.text = this.statInfo.bankerWin.toString();
				this.lbBJLZPairNum.text = this.statInfo.bankerPaire.toString();
				this.lbBJLXianNum.text = this.statInfo.playerWin.toString();
				this.lbBJLXPairNum.text = this.statInfo.playerPaire.toString();

				this.lbLHGameNum.text = this.statInfo.score.toString();
				this.lbLHHeNum.text = this.statInfo.dragonTigerTie.toString();
				this.lbLHLongNum.text = this.statInfo.dragon.toString();
				this.lbLHHuNum.text = this.statInfo.tiger.toString();
			}
		}
		private renderBView(): void {
			let data = this.exchangeBaccaratBigItemData(this.billInfo.hisWaybill)
			this.bigItemData = this.getBigItemData(data, false);
			this.showExChangeImg(1, data)
			this.showBJLMainItem();
			this.showBigWayItem(1, this.bigItemData);
			let bigEyeData = this.getItemDataByK(1, this.bigItemData);
			let smallData = this.getItemDataByK(2, this.bigItemData);
			let smallSData = this.getItemDataByK(3, this.bigItemData);

			this.showBigEyeItem(1, bigEyeData);
			this.showSmallItem(1, smallData);
			this.showSmallStrong(1, smallSData);
		}
		private renderLView(): void {
			this.bigLHItemData = this.getBigItemData(this.billInfo.hisDragon, false);
			this.showExChangeImg(2, this.billInfo.hisDragon)
			this.showLHMainItem();
			this.showBigWayItem(2, this.bigLHItemData);
			let bigEyeData = this.getItemDataByK(1, this.bigLHItemData);
			let smallData = this.getItemDataByK(2, this.bigLHItemData);
			let smallSData = this.getItemDataByK(3, this.bigLHItemData);

			this.showBigEyeItem(2, bigEyeData);
			this.showSmallItem(2, smallData);
			this.showSmallStrong(2, smallSData);
		}

		private bjlClick(): void {
			this.grpBJLLoad.visible = true;
			this.grpLHLoad.visible = false;
		}
		private lhClick(): void {
			this.grpBJLLoad.visible = false;
			this.grpLHLoad.visible = true;
		}

		private zhuangAskWayClick(): void {
			let data = this.exchangeBaccaratBigItemData(this.billInfo.hisWaybill)
			data.push(1);
			this.bigItemData = this.getBigItemData(data, true);
			this.showBigWayItem(1, this.bigItemData);
			let bigEyeData = this.getItemDataByK(1, this.bigItemData);
			let smallData = this.getItemDataByK(2, this.bigItemData);
			let smallSData = this.getItemDataByK(3, this.bigItemData);

			this.showBigEyeItem(1, bigEyeData);
			this.showSmallItem(1, smallData);
			this.showSmallStrong(1, smallSData);
		}
		private xianAskWayClick(): void {
			let data = this.exchangeBaccaratBigItemData(this.billInfo.hisWaybill)
			data.push(3);
			this.bigItemData = this.getBigItemData(data, true);
			this.showBigWayItem(1, this.bigItemData);
			let bigEyeData = this.getItemDataByK(1, this.bigItemData);
			let smallData = this.getItemDataByK(2, this.bigItemData);
			let smallSData = this.getItemDataByK(3, this.bigItemData);

			this.showBigEyeItem(1, bigEyeData);
			this.showSmallItem(1, smallData);
			this.showSmallStrong(1, smallSData);
		}

		private longAskWayClick(): void {
			let data = this.billInfo.hisDragon.slice(0);
			data.push(1);
			this.bigLHItemData = this.getBigItemData(data, true);
			this.showBigWayItem(2, this.bigLHItemData);
			let bigEyeData = this.getItemDataByK(1, this.bigLHItemData);
			let smallData = this.getItemDataByK(2, this.bigLHItemData);
			let smallSData = this.getItemDataByK(3, this.bigLHItemData);

			this.showBigEyeItem(2, bigEyeData);
			this.showSmallItem(2, smallData);
			this.showSmallStrong(2, smallSData);
		}
		private huAskWayClick(): void {
			let data = this.billInfo.hisDragon.slice(0);
			data.push(3);
			this.bigLHItemData = this.getBigItemData(data, true);
			this.showBigWayItem(2, this.bigLHItemData);
			let bigEyeData = this.getItemDataByK(1, this.bigLHItemData);
			let smallData = this.getItemDataByK(2, this.bigLHItemData);
			let smallSData = this.getItemDataByK(3, this.bigLHItemData);

			this.showBigEyeItem(2, bigEyeData);
			this.showSmallItem(2, smallData);
			this.showSmallStrong(2, smallSData);
		}
		private showBJLMainItem(): void {
			this.mainItemBJLArray.forEach(mi => {
				mi.parent.removeChild(mi);
			});
			this.mainItemBJLArray = new Array<baccarat.BaccaratMainWayItem>();
			this.billInfo.hisWaybill.forEach(ele => {
				let mi: baccarat.BaccaratMainWayItem = new baccarat.BaccaratMainWayItem();
				mi.setType(1, ele);
				this.grpBJLZXMainLoad.addChild(mi);
				this.mainItemBJLArray.push(mi);
			});
			this.billInfo.hisBigSmall.forEach(ele => {
				let mi: baccarat.BaccaratMainWayItem = new baccarat.BaccaratMainWayItem();
				mi.setType(2, ele);
				this.grpBJLDXMainLoad.addChild(mi);
				this.mainItemBJLArray.push(mi);
			});
		}
		private showLHMainItem(): void {
			this.mainItemLHArray.forEach(mi => {
				mi.parent.removeChild(mi);
			});
			this.mainItemLHArray = new Array<baccarat.BaccaratMainWayItem>();

			this.billInfo.hisDragon.forEach(ele => {
				let mi: baccarat.BaccaratMainWayItem = new baccarat.BaccaratMainWayItem();
				mi.setType(3, ele);
				this.grpLHMainLoad.addChild(mi);
				this.mainItemLHArray.push(mi);
			});
		}
		private showBigWayItem(type: number, dataList: any): void {
			if (type == 1) {
				this.bigItemBJLArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.bigItemBJLArray = new Array<baccarat.BaccaratBigWayItem>();
			}
			else {
				this.bigItemLHArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.bigItemLHArray = new Array<baccarat.BaccaratBigWayItem>();
			}
			if (dataList) {
				dataList.forEach(v => {
					if (v.n <= 8) {
						if (this.m_max > 24) {
							let dif_m = this.m_max - 24
							v.m = v.m - dif_m
						}
						if (v.m - 1 >= 0) {
							let mi = new baccarat.BaccaratBigWayItem();
							mi.setType(type, v.result, v.drawNum)
							mi.x = (v.m - 1) * 31 + v.m * 1.3;
							mi.y = (v.n - 1) * 31 + v.n + 1;
							if (type == 1) {
								this.grpBJLBigWay.addChild(mi);
								this.bigItemBJLArray.push(mi)
							}
							else {
								this.grpLHBigWay.addChild(mi);
								this.bigItemLHArray.push(mi)
							}
							if (v.bEffect) {
								mi.anim();
							}
						}
					}
				});
			}
		}
		private showBigEyeItem(type: number, dataList: any): void {
			if (type == 1) {
				this.bigEyeItemBJLArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.bigEyeItemBJLArray = new Array<baccarat.BaccaratBigEyeWayItem>();
			}
			else {
				this.bigEyeItemLHArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.bigEyeItemLHArray = new Array<baccarat.BaccaratBigEyeWayItem>();
			}

			if (dataList) {
				dataList.forEach(v => {
					if (v.n <= 8) {
						if (this.m_max_list[1] > 24) {
							let dif_m = this.m_max_list[1] - 24
							v.m = v.m - dif_m
						}
						if (v.m - 1 >= 0) {
							let mi = new baccarat.BaccaratBigEyeWayItem();
							mi.setType(type, v.result)
							mi.x = (v.m - 1) * 31 + v.m * 1.3;
							mi.y = (v.n - 1) * 31 + v.n + 1;
							if (type == 1) {
								this.grpBJLBigEyeWay.addChild(mi);
								this.bigEyeItemBJLArray.push(mi)
							} else {
								this.grpLHBigEyeWay.addChild(mi);
								this.bigEyeItemLHArray.push(mi)
							}
							if (v.bEffect) {
								mi.anim();
							}
						}
					}
				});
			}
		}
		private showSmallItem(type: number, dataList: any): void {
			if (type == 1) {
				this.smallItemBJLArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.smallItemBJLArray = new Array<baccarat.BaccaratSmallWayItem>();
			}
			else {
				this.smallItemLHArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.smallItemLHArray = new Array<baccarat.BaccaratSmallWayItem>();
			}

			if (dataList) {
				dataList.forEach(v => {
					if (v.n <= 8) {
						if (this.m_max_list[2] > 12) {
							let dif_m = this.m_max_list[1] - 12
							v.m = v.m - dif_m
						}
						if (v.m - 1 >= 0) {
							let mi = new baccarat.BaccaratSmallWayItem();
							mi.setType(type, v.result)
							mi.x = (v.m - 1) * 31 + v.m * 1.1;
							mi.y = (v.n - 1) * 31 + v.n;
							if (type == 1) {
								this.grpBJLSmallWay.addChild(mi);
								this.smallItemBJLArray.push(mi)
							}
							else {
								this.grpLHSmallWay.addChild(mi);
								this.smallItemLHArray.push(mi)
							}
							if (v.bEffect) {
								mi.anim();
							}
						}
					}
				});
			}
		}
		private showSmallStrong(type: number, dataList: any): void {
			if (type == 1) {
				this.smallStrongItemBJLArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.smallStrongItemBJLArray = new Array<baccarat.BaccaratSmallStrongItem>();
			}
			else {
				this.smallStrongItemLHArray.forEach(mi => {
					mi.parent.removeChild(mi);
				});
				this.smallStrongItemLHArray = new Array<baccarat.BaccaratSmallStrongItem>();
			}

			if (dataList) {
				dataList.forEach(v => {
					if (v.n <= 8) {
						if (this.m_max_list[3] > 12) {
							let dif_m = this.m_max_list[1] - 12
							v.m = v.m - dif_m
						}
						if (v.m - 1 >= 0) {
							let mi = new baccarat.BaccaratSmallStrongItem();
							mi.setType(type, v.result)
							mi.x = 389 + (v.m - 1) * 31 + v.m * 1.1;
							mi.y = (v.n - 1) * 31 + v.n;
							if (type == 1) {
								this.grpBJLSmallWay.addChild(mi);
								this.smallStrongItemBJLArray.push(mi)
							}
							else {
								this.grpLHSmallWay.addChild(mi);
								this.smallStrongItemLHArray.push(mi)
							}
							if (v.bEffect) {
								mi.anim();
							}
						}
					}
				});
			}
		}

		private exchangeBaccaratBigItemData(dataList: any): any {
			let data = new Array<number>();
			dataList.forEach(v => {
				data.push(Math.floor(v / 4) + 1)
			});
			return data;
		}
		private getBigItemData(dataList: any, bEffect: boolean): any {
			let bigItemData = [];
			let m = 1;
			let n = 1;
			this.m_max = 1;
			if (dataList) {
				for (let k in dataList) {
					let v = dataList[k];
					if (v != 2) {
						if (bigItemData[bigItemData.length - 1]) {
							if (v == bigItemData[bigItemData.length - 1].result) {
								n++;
							} else {
								m++;
								n = 1;
								this.m_max = m;
							}
						}
						let index = bigItemData.length;
						if (bEffect && parseInt(k) == dataList.length - 1) {
							bigItemData[index] = { result: v, drawNum: 0, m: m, n: n, bEffect: true }
						}
						else {
							bigItemData[index] = { result: v, drawNum: 0, m: m, n: n }
						}
					}
					else {
						if (bigItemData[bigItemData.length - 1]) {
							let tmp = bigItemData[bigItemData.length - 1]
							tmp.drawNum++;
						}
					}
				}
			}
			return bigItemData;
		}
		private getItemDataByK(key: number, dataList: any): any {
			let m = 1; let n = 1;
			this.m_max_list[key] = 1;
			let data = [];

			if (dataList) {
				for (let k in dataList) {
					let v = dataList[k];
					let result = this.getResult(dataList, v, key);
					if (result) {
						if (data.length > 0 && data[data.length - 1]) {
							if (data[data.length - 1].result == result) {
								n++;
							}
							else {
								n = 1;
								m++;
								this.m_max_list[key] = m;
							}
						}
						let index = data.length;
						if (v.bEffect && parseInt(k) == dataList.length - 1) {
							data[index] = { result: result, m: m, n: n, bEffect: true }
						}
						else {
							data[index] = { result: result, m: m, n: n }
						}
					}
				}
			}

			return data;
		}
		private getResult(dataList: any, data: any, key: number): any {
			let result = null;
			if (data.m && data.n) {
				if (data.n > 1) {
					if (data.m > key) {
						result = ((this.getPByK(data.m - key, dataList) + 1) == data.n) ? 2 : 1;
					}
				}
				else {
					if (data.m > key) {
						result = ((this.getPByK(data.m - key, dataList) + 1) == data.n) ? 1 : 2;
					}
				}
			}
			return result;
		}
		private getPByK(m: number, data: any): any {
			let p = 0;
			for (let k in data) {
				let v = data[k];
				if (v.m == m) {
					p++;
				}
			}
			return p;
		}

		private showExChangeImg(type: number, dataList: any): void {
			if (dataList) {
				let _bankerDataList = dataList.slice(0);
				let _playerDataList = dataList.slice(0);
				_bankerDataList.push(1);
				_playerDataList.push(3);
				let bankerBigItemData = this.getBigItemData(_bankerDataList, false);
				let playerBigItemData = this.getBigItemData(_playerDataList, false);
				let bankerData = bankerBigItemData[bankerBigItemData.length - 1];
				let playerData = playerBigItemData[playerBigItemData.length - 1];
				if (bankerData) {
					if (type == 1) {
						this.imgSmallIconZhuang.source = this.getResult(bankerBigItemData, bankerData, 2) == 2 ? RES.getRes("ludan_json.bjl_t_lqiu") : RES.getRes("ludan_json.bjl_t_fqiu");
						this.imgXQIconZhuang.source = this.getResult(bankerBigItemData, bankerData, 3) == 2 ? RES.getRes("ludan_json.bjl_t_lg") : RES.getRes("ludan_json.bjl_t_fg");
					} else {
						this.imgSmallIconLong.source = this.getResult(bankerBigItemData, bankerData, 2) == 2 ? RES.getRes("ludan_json.bjl_t_jqiu") : RES.getRes("ludan_json.bjl_t_fqiu");
						this.imgXQIconLong.source = this.getResult(bankerBigItemData, bankerData, 3) == 2 ? RES.getRes("ludan_json.bjl_t_jg") : RES.getRes("ludan_json.bjl_t_fg");
					}
				}
				if (playerData) {
					if (type == 1) {
						this.imgSmallIconXian.source = this.getResult(bankerBigItemData, bankerData, 2) == 1 ? RES.getRes("ludan_json.bjl_t_fqiu") : RES.getRes("ludan_json.bjl_t_lqiu");
						this.imgXQIconXian.source = this.getResult(bankerBigItemData, bankerData, 3) == 1 ? RES.getRes("ludan_json.bjl_t_fg") : RES.getRes("ludan_json.bjl_t_lg");
					} else {
						this.imgSmallIconHu.source = this.getResult(bankerBigItemData, bankerData, 2) == 1 ? RES.getRes("ludan_json.bjl_t_fqiu") : RES.getRes("ludan_json.bjl_t_jqiu");
						this.imgXQIconHu.source = this.getResult(bankerBigItemData, bankerData, 3) == 1 ? RES.getRes("ludan_json.bjl_t_fg") : RES.getRes("ludan_json.bjl_t_jg");
					}

				}
			}
		}
	}
}