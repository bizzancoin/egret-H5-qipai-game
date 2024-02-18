package com.idealighter.game.service.roomctl;

import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.service.roomctl.bo.StrategyBo;
import com.idealighter.utils.json.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class RoomCtlService implements IRoomCtlService {

  @Override
  public String findCtlInfo(Integer gameId, Integer roomId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_DETAIL, paramBuild);
  }

  @Override
  public String addDefaultPrizePool(Integer gameId, Integer roomId, Long prizePoolGold) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePools", prizePoolGold);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_ADD_DEFAULT_PRIZE_POOL, paramBuild);
  }

  @Override
  public String addDefaultPrizePoolGold(Integer gameId, Integer roomId, Long gold) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("gold", gold);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_ADD_DEFAULT_PRIZE_POOL_GOLD, paramBuild);
  }

  @Override
  public String modifyDefaultPrizePoolGold(Integer gameId, Integer roomId, Long gold) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("gold", gold);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_MODIFY_DEFAULT_PRIZE_POOL_GOLD, paramBuild);
  }

  @Override
  public String addDefaultPrizePoolStrategy(Integer gameId, Integer roomId, StrategyBo strategy) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("strategy", JsonUtil.toJson(strategy));

    return GameServerRequest.post(GameServerUrl.ROOMCTL_ADD_DEFAULT_PRIZE_POOL_STRATEGY,
        paramBuild);
  }

  @Override
  public String modifyDefaultPrizePoolStrategy(Integer gameId, Integer roomId,
      StrategyBo strategy) {
    IdeaAssert.isTrue(strategy != null && strategy.getId() != null);
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("strategyId", strategy.getId());
    paramBuild.add("strategy", JsonUtil.toJson(strategy));

    return GameServerRequest.post(GameServerUrl.ROOMCTL_MODIFY_DEFAULT_PRIZE_POOL_STRATEGY,
        paramBuild);
  }

  @Override
  public String delDefaultPrizePoolStrategy(Integer gameId, Integer roomId, Long strategyId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("strategyId", strategyId);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_DEL_DEFAULT_PRIZE_POOL_STRATEGY,
        paramBuild);
  }

  @Override
  public String delDefaultPrizePool(Integer gameId, Integer roomId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_DEL_DEFAULT_PRIZE_POOL, paramBuild);
  }

  @Override
  public String addGoldPrizePool(Integer gameId, Integer roomId, Long prizePoolGold, Long lower,
      Long upper) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePools", prizePoolGold);
    paramBuild.add("lower", lower);
    paramBuild.add("upper", upper);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_ADD_GOLD_PRIZE_POOL, paramBuild);
  }

  @Override
  public String delGoldPrizePool(Integer gameId, Integer roomId, Long prizePoolId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_DEL_GOLD_PRIZE_POOL, paramBuild);
  }

  @Override
  public String addGoldPrizePoolGold(Integer gameId, Integer roomId, Long prizePoolId, Long gold) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);
    paramBuild.add("gold", gold);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_ADD_GOLD_PRIZE_POOL_GOLD, paramBuild);
  }

  @Override
  public String modifyGoldPrizePoolGold(Integer gameId, Integer roomId, Long prizePoolId,
      Long gold) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);
    paramBuild.add("gold", gold);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_MODIFY_GOLD_PRIZE_POOL_GOLD, paramBuild);
  }

  @Override
  public String modifyGoldPrizePoolRange(Integer gameId, Integer roomId, Long prizePoolId,
      Long lower, Long upper) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);
    paramBuild.add("lower", lower);
    paramBuild.add("upper", upper);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_MODIFY_GOLD_PRIZE_POOL_RANGE, paramBuild);
  }

  @Override
  public String addGoldPrizePoolStrategy(Integer gameId, Integer roomId, Long prizePoolId,
      StrategyBo strategy) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);
    paramBuild.add("strategy", JsonUtil.toJson(strategy));

    return GameServerRequest.post(GameServerUrl.ROOMCTL_ADD_GOLD_PRIZE_POOL_STRATEGY, paramBuild);
  }

  @Override
  public String modifyGoldPrizePoolStrategy(Integer gameId, Integer roomId, Long prizePoolId,
      StrategyBo strategy) {
    IdeaAssert.isTrue(strategy != null && strategy.getId() != null);
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);
    paramBuild.add("strategyId", strategy.getId());
    paramBuild.add("strategy", JsonUtil.toJson(strategy));

    return GameServerRequest.post(GameServerUrl.ROOMCTL_MODIFY_GOLD_PRIZE_POOL_STRATEGY,
        paramBuild);
  }

  @Override
  public String delGoldPrizePoolStrategy(Integer gameId, Integer roomId, Long prizePoolId,
      Long strategyId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);
    paramBuild.add("strategyId", strategyId);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_DEL_GOLD_PRIZE_POOL_STRATEGY, paramBuild);
  }

  @Override
  public String findDefaultPrizePool(Integer gameId, Integer roomId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_DEFAULT_DETAIL, paramBuild);
  }

  @Override
  public String findGoldPrizePool(Integer gameId, Integer roomId, Long prizePoolId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("prizePoolId", prizePoolId);

    return GameServerRequest.post(GameServerUrl.ROOMCTL_GOLD_DETAIL, paramBuild);
  }
}
