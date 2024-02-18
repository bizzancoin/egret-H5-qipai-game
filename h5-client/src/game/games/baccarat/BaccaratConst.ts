class BaccaratConst {
    public static LoadCompleted:number = 590001;//资源加载完成
    public static RenderCompleted:number = 590002;//界面初始化完成
    
    public static FastEnterTable:number = 595904;//快速进入桌子
    public static ExitTable:number = 595931;   //推出桌子

    public static DoBet:number = 595907; //下注
    public static ClearBet:number = 595908;//清除下注

    public static TableBetInfoNotice:number = 595928;//桌子筹码变化
    public static PlayerChipNotice:number = 595921;//玩家筹码变更
    public static GameStateNotice:number = 595929;//游戏状态和倒计时
    public static GameOddsNotice:number = 595923;//游戏赔率
    public static GameStartNotice:number = 595925;//游戏开始发牌
    public static GameResultNotice:number = 595930;//游戏开结果
    public static GameBillNotice:number = 595926;//游戏结算
    public static GameStatNotice:number = 595927;//游戏统计
    
    public static GameWayBillNotice:number = 595922;//游戏路单通知

    public static GameShuffleNotice:number = 595924;//洗牌通知
    
    public static GameInfo:number = 595999;//获取桌子信息
}