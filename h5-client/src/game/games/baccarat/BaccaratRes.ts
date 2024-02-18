module baccarat {
    export class BaccaratRes {
        private static s2c_bjl_ResPlayerEnter_msg = '5920';//玩家进入5920
        private static s2c_bjl_ResPlayerExit_msg = '5931';//

        private static s2c_bjl_ResTableBetInfo_msg = '5928';//桌面筹码信息5928
        private static s2c_bjl_ResPlayerChipChange_msg = '5921';//玩家筹码变化5921

        private static s2c_bjl_ResGameState_msg = '5929';//游戏阶段和倒计时5929
        private static s2c_bjl_ResSendCards_msg = '5925';//发牌5925
        private static s2c_bjl_ResGameResult_msg = '5930';//开出结果5930
        private static s2c_bjl_ResBill_msg = '5926';//计算信息5926
        private static s2c_bjl_ResBillStat_msg = '5927';//结算统计5927

        private static s2c_bjl_ResWayBill_msg = '5922';//路单记录5922
        private static s2c_bjl_ResShuffle_msg = '5924';//洗牌5924

        private static rec_bjl_ResPlayerEnter(msg): void {
        }
        private static rec_bjl_ResPlayerExit(msg): void {
        }

        private static rec_bjl_ResPlayerChipChange(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.PlayerChipNotice, msg.playerId, msg.chips);
        }
        private static rec_bjl_ResTableBetInfo(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.TableBetInfoNotice, msg.betInfo);
        }

        private static rec_bjl_ResGameState(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameStateNotice, msg.time, msg.state);
        }
        private static rec_bjl_ResSendCards(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameStartNotice, msg);
        }
        private static rec_bjl_ResGameResult(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameResultNotice, msg);
        }
        private static rec_bjl_ResGameBill(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameBillNotice, msg);
        }
        private static rec_bjl_ResGameBillStat(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameStatNotice, msg);
        }

        private static rec_bjl_ResGameWayBill(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameWayBillNotice, msg);
        }
        private static rec_bjl_ResShuffle(msg): void {
            App.ControllerManager.applyFunc(ControllerConst.Baccarat, BaccaratConst.GameShuffleNotice, msg);
        }

        public static registerListener() {
            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResPlayerEnter_msg, BaccaratRes.rec_bjl_ResPlayerEnter, BaccaratRes);

            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResTableBetInfo_msg, BaccaratRes.rec_bjl_ResTableBetInfo, BaccaratRes);
            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResPlayerChipChange_msg, BaccaratRes.rec_bjl_ResPlayerChipChange, BaccaratRes);

            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResGameState_msg, BaccaratRes.rec_bjl_ResGameState, BaccaratRes);
            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResSendCards_msg, BaccaratRes.rec_bjl_ResSendCards, BaccaratRes);
            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResGameResult_msg, BaccaratRes.rec_bjl_ResGameResult, BaccaratRes);
            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResBill_msg, BaccaratRes.rec_bjl_ResGameBill, BaccaratRes);
            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResBillStat_msg, BaccaratRes.rec_bjl_ResGameBillStat, BaccaratRes);

            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResWayBill_msg, BaccaratRes.rec_bjl_ResGameWayBill, BaccaratRes);

            App.MessageCenter.addListener(BaccaratRes.s2c_bjl_ResShuffle_msg, BaccaratRes.rec_bjl_ResShuffle, BaccaratRes);
        }
        public static unregisterListener() {
			App.MessageCenter.removeAll(BaccaratRes)
        }
    }
}