module fish {
	export class FishMgr {

		// 当前游戏（金蟾捕鱼jc/李逵劈鱼 lk）
		public static fishModule: string;
		public static seatId: number = 4;

		public static fishModel: FishMainModel = new FishMainModel();
		public static fishStart: FishStart;


		// 资源管理
		public static get Config(): fish.FishConfigManager {
			return fish.FishConfigManager.getInstance();
		}

		// 背景管理
		public static get BackgroundManager(): BackgroundManager {
			return BackgroundManager.getInstance();
		}

		// 动作管理
		public static get ActionEffect(): ActionEffect {
			return ActionEffect.getInstance();
		}

		// 飘字管理
		public static get FishLabelManager(): FishLabelManager {
			return FishLabelManager.getInstance();
		}

		// 渔网管理
		public static get FishNetManager(): FishNetManager {
			return FishNetManager.getInstance();
		}

		// 音效管理
		public static get MusicManager(): MusicManager {
			return MusicManager.getInstance();
		}

		// 飞行金币管理
		public static get FishGoldManager(): FishGoldManager {
			return FishGoldManager.getInstance();
		}

		// 鱼管理
		public static get FishManager(): FishManager {
			return FishManager.getInstance();
		}

		// 炮台管理
		public static get BatteryManager(): BatteryManager {
			return BatteryManager.getInstance();
		}

		// 对象池管理
		public static get FishObjectPool(): FishObjectPool {
			return FishObjectPool.getInstance();
		}

		// 子弹管理
		public static get BulletManager(): BulletManager {
			return BulletManager.getInstance();
		}

		// 碰撞管理
		public static get CollisionManager(): CollisionManager {
			return CollisionManager.getInstance();
		}

		// 技能管理
		public static get SkillManager(): SkillManager {
			return SkillManager.getInstance();
		}

		public static randomSelectId(lst: any): number {
			let val = Math.random();
			let sum = 0;
			for (let i = 0; i < lst.length; i++) {
				sum += lst[i].gi;
				if (sum > val) {
					return lst[i].id;
				}
			}
			return -1;
		}

		public static registerListener() {
			FishReq.registerListener(9);
		}


		public static setRoomInfo(roomInfo: any): void {
			if (!FishMgr.fishModel) {
				FishMgr.fishModel = new FishMainModel();
			}
			FishMgr.fishModel.roomInfo = roomInfo;
		}

		public static isEnterScene(): boolean {
			return FishMgr.fishModel && FishMgr.fishModel.isEnterScene;
		}

		public static start(key: string) {
			FishMgr.fishModule = key;
			if (!FishMgr.fishModel) {
				FishMgr.fishModel = new FishMainModel();
			}
			if (!FishMgr.fishStart) {
				FishMgr.fishStart = new FishStart();
			}
			FishMgr.fishStart.start()
		}

		public static finish() {
			if (FishMgr.fishStart) {
				FishMgr.fishStart.finish();
			}
			FishReq.removeListener();
			FishMgr.fishStart = null;
			FishMgr.fishModel = null;
		}
	}
}