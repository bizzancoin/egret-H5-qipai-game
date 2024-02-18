module fish {
	export class FishReq {

		// 开炮
		public static send_ReqFire(angle: number): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqFire(angle);
					break;
				case 'lk':
					break;
			}
		}

		// 切换炮台
		public static send_ReqSwitchBattery(type: number): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqSwitchBattery(type);
					break;
				case 'lk':
					break;
			}
		}

		// 打中鱼
		public static send_ReqHitFish(playerId: number, bulletId: number, fishId: number): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqHitFish(playerId, bulletId, fishId);
					break;
				case 'lk':
					break;
			}
		}

		// 退出桌子
		public static send_ReqExitTable(): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqExitTable();
					break;
				case 'lk':
					break;
			}
		}

		// 请求场景数据
		public static send_ReqScene(): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqScene();
					break;
				case 'lk':
					break;
			}
		}

		// 锁定鱼
		public static send_ReqLockFish(fishId: number, angle: number): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqLockFish(fishId, angle);
					break;
				case 'lk':
					break;
			}
		}

		// 取消锁定鱼
		public static send_ReqCancelLockFish(): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqCancelLockFish();
					break;
				case 'lk':
					break;
			}
		}

		// 兑换筹码
		public static send_ReqExchangeChip(type: game.ExchangeType, value:number): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					switch(type) {
						case game.ExchangeType.CashIn:
							jcby.FishReq.send_ReqExchangeGolds(value);
							break;
						case game.ExchangeType.CashOut:
							jcby.FishReq.send_ReqExchangeChips(value);
							break;
					}
					break;
				case 'lk':
					break;
			}
		}

		// 断线重连
		public static send_ReqBackTable(): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.send_ReqBackTable();
					break;
				case 'lk':
					break;
			}
		}


		// 注册公共模块请求
		public static registerListener(gameId: number): void {
			switch (gameId) {
				case 9:
					GameApp.HomeManager.registerModuleReqFunc(gameId, jcby.FishReq.registerCommonReq, jcby.FishReq);
					GameApp.HomeManager.registerModuleResFunc(gameId, jcby.FishRes.registerResListener, jcby.FishRes);
					break;
				case 14:
					break;
			}
		}

		// 移除公共模块请求
		public static removeListener(): void {
			switch (FishMgr.fishModule) {
				case 'jc':
					jcby.FishReq.removeCommonReq();
					jcby.FishRes.removeResListener();
					break;
				case 'lk':
					break;
			}
		}
	}
}