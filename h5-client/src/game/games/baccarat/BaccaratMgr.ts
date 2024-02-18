module baccarat {
	export class BaccaratMgr {

		private static _resultMsg: any;
		private static _startMsg: any;

		public static bjlModel: BaccaratModel = new BaccaratModel();
		public static bjlStart: BaccaratStart;

		public constructor() {
		}
		public static setRoomInfo(roomInfo: any): void {
			if (!BaccaratMgr.bjlModel) {
				BaccaratMgr.bjlModel = new BaccaratModel();
			}
			BaccaratMgr.bjlModel.roomInfo = roomInfo;
			if(BaccaratMgr.bjlModel.roomInfo.betOptions){
				for (let i = 0; i < BaccaratMgr.bjlModel.roomInfo.betOptions.length; i++) {
					if(typeof(BaccaratMgr.bjlModel.roomInfo.betOptions[i]) != "number"){
						BaccaratMgr.bjlModel.roomInfo.betOptions[i] = BaccaratMgr.bjlModel.roomInfo.betOptions[i].toNumber();
					}
				}
			}
		}

		public static isEnterScene(): boolean {
			return BaccaratMgr.bjlModel && BaccaratMgr.bjlModel.isEnterScene;
		}

		public static setEnterScene(val:boolean): void {
			if(BaccaratMgr.bjlModel){
				BaccaratMgr.bjlModel.isEnterScene = val;
			}
		}
		public static registerListener() {
			GameApp.HomeManager.registerModuleReqFunc(18, BaccaratReq.registerComReq, BaccaratMgr);
			GameApp.HomeManager.registerModuleResFunc(18, BaccaratRes.registerListener, BaccaratMgr);
		}

		public static start() {
			if (!BaccaratMgr.bjlModel) {
				BaccaratMgr.bjlModel = new BaccaratModel();
			}
			if (!BaccaratMgr.bjlStart) {
				BaccaratMgr.bjlStart = new BaccaratStart();
			}
			BaccaratMgr.bjlStart.start()
		}

		public static finish(removeListener: boolean) {
			if (removeListener) {
				BaccaratRes.unregisterListener();
			}
			if (BaccaratMgr.bjlStart) {
				BaccaratMgr.bjlStart.finish();
			}
			BaccaratMgr.bjlStart = null;
			BaccaratMgr.bjlModel = null;
		}
	}
}