module brnn {
	export class BrnnMgr {

		private static gameStart: BrnnStart;
		public static mainMoel: MainModel;

		public static gsplit(str: string): string[] {
			if (!str || str.length <= 0) {
				return null;
			}
			let lst = [];
			for (let i = 0; i < str.length; i++) {
				let code = str.charCodeAt(i);
				if ((code >= 48 && code <= 57) || (code >= 65 && code <= 90) || (code >= 97 && code <= 122)) {
					lst.push(str.charAt(i));
				}
			}
			return lst;
		}

		public static playSound(id: any, sex?: any): void {
			let sounds;
			if (sex == 0) {
				sounds = BrnnConfig.MusicConfig.MaleTable;
			} else if (sex == 1) {
				sounds = BrnnConfig.MusicConfig.FemaleTable;
			} else {
				sounds = BrnnConfig.MusicConfig.OtherTable;
			}
			if (sounds && sounds[id]) {
				App.SoundManager.playEffect(sounds[id].resPath);
			}

		}

		public static get ObjectPool(): ObjectPool {
			return ObjectPool.getInstance();
		}

		public static get ChipsManager(): ChipsManager {
			return ChipsManager.getInstance();
		}

		public static get BigCard(): BigCardsManager {
			return BigCardsManager.getInstance();
		}

		public static get SmallCard(): SmallCardsManager {
			return SmallCardsManager.getInstance();
		}

		public static setRoomInfo(roomInfo: any): void {
			if (!BrnnMgr.mainMoel) {
				BrnnMgr.mainMoel = new MainModel();
			}
			BrnnMgr.mainMoel.roomInfo = roomInfo;
			if(BrnnMgr.mainMoel.roomInfo.betOptions){
				for (let i = 0; i < BrnnMgr.mainMoel.roomInfo.betOptions.length; i++) {
					BrnnMgr.mainMoel.roomInfo.betOptions[i] = BrnnMgr.mainMoel.roomInfo.betOptions[i].toNumber();
				}
			}
		}

		public static isEnterScene(): boolean {
			return BrnnMgr.mainMoel && BrnnMgr.mainMoel.isEnterScene;
		}

		public static setEnterScene(isEnterScene: boolean): void {
			if(BrnnMgr.mainMoel) {
				BrnnMgr.mainMoel.isEnterScene = isEnterScene;
			}
		}

		public static registerListener(): void {
			GameApp.HomeManager.registerModuleReqFunc(7, BrnnReq.registerCommonReq, BrnnReq);
			GameApp.HomeManager.registerModuleResFunc(7, BrnnRes.registerResListener, BrnnRes);
		}

		public static start() {
			if (!BrnnMgr.gameStart) {
				BrnnMgr.gameStart = new BrnnStart();
			}
			if (!BrnnMgr.mainMoel) {
				BrnnMgr.mainMoel = new MainModel();
			}
			BrnnMgr.gameStart.start();
		}

		public static finish() {
			if (BrnnMgr.gameStart) {
				BrnnMgr.gameStart.finish();
			}
			// 移除监听
			BrnnReq.removeCommonReq();
			BrnnRes.removeResListener();
			BrnnMgr.gameStart = null;
			BrnnMgr.mainMoel = null;
		}
	}
}