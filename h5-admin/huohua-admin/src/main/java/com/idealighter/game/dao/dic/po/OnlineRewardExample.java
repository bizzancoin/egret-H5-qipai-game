package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class OnlineRewardExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public OnlineRewardExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getOffset() {
    return offset;
  }

  protected abstract static class GeneratedCriteria {
    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIdIsNull() {
      addCriterion("id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andGameIsNull() {
      addCriterion("game is null");
      return (Criteria) this;
    }

    public Criteria andGameIsNotNull() {
      addCriterion("game is not null");
      return (Criteria) this;
    }

    public Criteria andGameEqualTo(Integer value) {
      addCriterion("game =", value, "game");
      return (Criteria) this;
    }

    public Criteria andGameNotEqualTo(Integer value) {
      addCriterion("game <>", value, "game");
      return (Criteria) this;
    }

    public Criteria andGameGreaterThan(Integer value) {
      addCriterion("game >", value, "game");
      return (Criteria) this;
    }

    public Criteria andGameGreaterThanOrEqualTo(Integer value) {
      addCriterion("game >=", value, "game");
      return (Criteria) this;
    }

    public Criteria andGameLessThan(Integer value) {
      addCriterion("game <", value, "game");
      return (Criteria) this;
    }

    public Criteria andGameLessThanOrEqualTo(Integer value) {
      addCriterion("game <=", value, "game");
      return (Criteria) this;
    }

    public Criteria andGameIn(List<Integer> values) {
      addCriterion("game in", values, "game");
      return (Criteria) this;
    }

    public Criteria andGameNotIn(List<Integer> values) {
      addCriterion("game not in", values, "game");
      return (Criteria) this;
    }

    public Criteria andGameBetween(Integer value1, Integer value2) {
      addCriterion("game between", value1, value2, "game");
      return (Criteria) this;
    }

    public Criteria andGameNotBetween(Integer value1, Integer value2) {
      addCriterion("game not between", value1, value2, "game");
      return (Criteria) this;
    }

    public Criteria andRoomIsNull() {
      addCriterion("room is null");
      return (Criteria) this;
    }

    public Criteria andRoomIsNotNull() {
      addCriterion("room is not null");
      return (Criteria) this;
    }

    public Criteria andRoomEqualTo(Integer value) {
      addCriterion("room =", value, "room");
      return (Criteria) this;
    }

    public Criteria andRoomNotEqualTo(Integer value) {
      addCriterion("room <>", value, "room");
      return (Criteria) this;
    }

    public Criteria andRoomGreaterThan(Integer value) {
      addCriterion("room >", value, "room");
      return (Criteria) this;
    }

    public Criteria andRoomGreaterThanOrEqualTo(Integer value) {
      addCriterion("room >=", value, "room");
      return (Criteria) this;
    }

    public Criteria andRoomLessThan(Integer value) {
      addCriterion("room <", value, "room");
      return (Criteria) this;
    }

    public Criteria andRoomLessThanOrEqualTo(Integer value) {
      addCriterion("room <=", value, "room");
      return (Criteria) this;
    }

    public Criteria andRoomIn(List<Integer> values) {
      addCriterion("room in", values, "room");
      return (Criteria) this;
    }

    public Criteria andRoomNotIn(List<Integer> values) {
      addCriterion("room not in", values, "room");
      return (Criteria) this;
    }

    public Criteria andRoomBetween(Integer value1, Integer value2) {
      addCriterion("room between", value1, value2, "room");
      return (Criteria) this;
    }

    public Criteria andRoomNotBetween(Integer value1, Integer value2) {
      addCriterion("room not between", value1, value2, "room");
      return (Criteria) this;
    }

    public Criteria andVipIsNull() {
      addCriterion("vip is null");
      return (Criteria) this;
    }

    public Criteria andVipIsNotNull() {
      addCriterion("vip is not null");
      return (Criteria) this;
    }

    public Criteria andVipEqualTo(Boolean value) {
      addCriterion("vip =", value, "vip");
      return (Criteria) this;
    }

    public Criteria andVipNotEqualTo(Boolean value) {
      addCriterion("vip <>", value, "vip");
      return (Criteria) this;
    }

    public Criteria andVipGreaterThan(Boolean value) {
      addCriterion("vip >", value, "vip");
      return (Criteria) this;
    }

    public Criteria andVipGreaterThanOrEqualTo(Boolean value) {
      addCriterion("vip >=", value, "vip");
      return (Criteria) this;
    }

    public Criteria andVipLessThan(Boolean value) {
      addCriterion("vip <", value, "vip");
      return (Criteria) this;
    }

    public Criteria andVipLessThanOrEqualTo(Boolean value) {
      addCriterion("vip <=", value, "vip");
      return (Criteria) this;
    }

    public Criteria andVipIn(List<Boolean> values) {
      addCriterion("vip in", values, "vip");
      return (Criteria) this;
    }

    public Criteria andVipNotIn(List<Boolean> values) {
      addCriterion("vip not in", values, "vip");
      return (Criteria) this;
    }

    public Criteria andVipBetween(Boolean value1, Boolean value2) {
      addCriterion("vip between", value1, value2, "vip");
      return (Criteria) this;
    }

    public Criteria andVipNotBetween(Boolean value1, Boolean value2) {
      addCriterion("vip not between", value1, value2, "vip");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperIsNull() {
      addCriterion("tenLevelUpper is null");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperIsNotNull() {
      addCriterion("tenLevelUpper is not null");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperEqualTo(Boolean value) {
      addCriterion("tenLevelUpper =", value, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperNotEqualTo(Boolean value) {
      addCriterion("tenLevelUpper <>", value, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperGreaterThan(Boolean value) {
      addCriterion("tenLevelUpper >", value, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperGreaterThanOrEqualTo(Boolean value) {
      addCriterion("tenLevelUpper >=", value, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperLessThan(Boolean value) {
      addCriterion("tenLevelUpper <", value, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperLessThanOrEqualTo(Boolean value) {
      addCriterion("tenLevelUpper <=", value, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperIn(List<Boolean> values) {
      addCriterion("tenLevelUpper in", values, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperNotIn(List<Boolean> values) {
      addCriterion("tenLevelUpper not in", values, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperBetween(Boolean value1, Boolean value2) {
      addCriterion("tenLevelUpper between", value1, value2, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevelupperNotBetween(Boolean value1, Boolean value2) {
      addCriterion("tenLevelUpper not between", value1, value2, "tenlevelupper");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerIsNull() {
      addCriterion("tenLevelLower is null");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerIsNotNull() {
      addCriterion("tenLevelLower is not null");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerEqualTo(Boolean value) {
      addCriterion("tenLevelLower =", value, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerNotEqualTo(Boolean value) {
      addCriterion("tenLevelLower <>", value, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerGreaterThan(Boolean value) {
      addCriterion("tenLevelLower >", value, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerGreaterThanOrEqualTo(Boolean value) {
      addCriterion("tenLevelLower >=", value, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerLessThan(Boolean value) {
      addCriterion("tenLevelLower <", value, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerLessThanOrEqualTo(Boolean value) {
      addCriterion("tenLevelLower <=", value, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerIn(List<Boolean> values) {
      addCriterion("tenLevelLower in", values, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerNotIn(List<Boolean> values) {
      addCriterion("tenLevelLower not in", values, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerBetween(Boolean value1, Boolean value2) {
      addCriterion("tenLevelLower between", value1, value2, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTenlevellowerNotBetween(Boolean value1, Boolean value2) {
      addCriterion("tenLevelLower not between", value1, value2, "tenlevellower");
      return (Criteria) this;
    }

    public Criteria andTablegoldIsNull() {
      addCriterion("tableGold is null");
      return (Criteria) this;
    }

    public Criteria andTablegoldIsNotNull() {
      addCriterion("tableGold is not null");
      return (Criteria) this;
    }

    public Criteria andTablegoldEqualTo(Integer value) {
      addCriterion("tableGold =", value, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldNotEqualTo(Integer value) {
      addCriterion("tableGold <>", value, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldGreaterThan(Integer value) {
      addCriterion("tableGold >", value, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldGreaterThanOrEqualTo(Integer value) {
      addCriterion("tableGold >=", value, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldLessThan(Integer value) {
      addCriterion("tableGold <", value, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldLessThanOrEqualTo(Integer value) {
      addCriterion("tableGold <=", value, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldIn(List<Integer> values) {
      addCriterion("tableGold in", values, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldNotIn(List<Integer> values) {
      addCriterion("tableGold not in", values, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldBetween(Integer value1, Integer value2) {
      addCriterion("tableGold between", value1, value2, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTablegoldNotBetween(Integer value1, Integer value2) {
      addCriterion("tableGold not between", value1, value2, "tablegold");
      return (Criteria) this;
    }

    public Criteria andTabledurationIsNull() {
      addCriterion("tableDuration is null");
      return (Criteria) this;
    }

    public Criteria andTabledurationIsNotNull() {
      addCriterion("tableDuration is not null");
      return (Criteria) this;
    }

    public Criteria andTabledurationEqualTo(Integer value) {
      addCriterion("tableDuration =", value, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationNotEqualTo(Integer value) {
      addCriterion("tableDuration <>", value, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationGreaterThan(Integer value) {
      addCriterion("tableDuration >", value, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationGreaterThanOrEqualTo(Integer value) {
      addCriterion("tableDuration >=", value, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationLessThan(Integer value) {
      addCriterion("tableDuration <", value, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationLessThanOrEqualTo(Integer value) {
      addCriterion("tableDuration <=", value, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationIn(List<Integer> values) {
      addCriterion("tableDuration in", values, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationNotIn(List<Integer> values) {
      addCriterion("tableDuration not in", values, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationBetween(Integer value1, Integer value2) {
      addCriterion("tableDuration between", value1, value2, "tableduration");
      return (Criteria) this;
    }

    public Criteria andTabledurationNotBetween(Integer value1, Integer value2) {
      addCriterion("tableDuration not between", value1, value2, "tableduration");
      return (Criteria) this;
    }

    public Criteria andRoomgoldIsNull() {
      addCriterion("roomGold is null");
      return (Criteria) this;
    }

    public Criteria andRoomgoldIsNotNull() {
      addCriterion("roomGold is not null");
      return (Criteria) this;
    }

    public Criteria andRoomgoldEqualTo(Integer value) {
      addCriterion("roomGold =", value, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldNotEqualTo(Integer value) {
      addCriterion("roomGold <>", value, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldGreaterThan(Integer value) {
      addCriterion("roomGold >", value, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldGreaterThanOrEqualTo(Integer value) {
      addCriterion("roomGold >=", value, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldLessThan(Integer value) {
      addCriterion("roomGold <", value, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldLessThanOrEqualTo(Integer value) {
      addCriterion("roomGold <=", value, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldIn(List<Integer> values) {
      addCriterion("roomGold in", values, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldNotIn(List<Integer> values) {
      addCriterion("roomGold not in", values, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldBetween(Integer value1, Integer value2) {
      addCriterion("roomGold between", value1, value2, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomgoldNotBetween(Integer value1, Integer value2) {
      addCriterion("roomGold not between", value1, value2, "roomgold");
      return (Criteria) this;
    }

    public Criteria andRoomdurationIsNull() {
      addCriterion("roomDuration is null");
      return (Criteria) this;
    }

    public Criteria andRoomdurationIsNotNull() {
      addCriterion("roomDuration is not null");
      return (Criteria) this;
    }

    public Criteria andRoomdurationEqualTo(Integer value) {
      addCriterion("roomDuration =", value, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationNotEqualTo(Integer value) {
      addCriterion("roomDuration <>", value, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationGreaterThan(Integer value) {
      addCriterion("roomDuration >", value, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationGreaterThanOrEqualTo(Integer value) {
      addCriterion("roomDuration >=", value, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationLessThan(Integer value) {
      addCriterion("roomDuration <", value, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationLessThanOrEqualTo(Integer value) {
      addCriterion("roomDuration <=", value, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationIn(List<Integer> values) {
      addCriterion("roomDuration in", values, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationNotIn(List<Integer> values) {
      addCriterion("roomDuration not in", values, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationBetween(Integer value1, Integer value2) {
      addCriterion("roomDuration between", value1, value2, "roomduration");
      return (Criteria) this;
    }

    public Criteria andRoomdurationNotBetween(Integer value1, Integer value2) {
      addCriterion("roomDuration not between", value1, value2, "roomduration");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveIsNull() {
      addCriterion("tableEffective is null");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveIsNotNull() {
      addCriterion("tableEffective is not null");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveEqualTo(Boolean value) {
      addCriterion("tableEffective =", value, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveNotEqualTo(Boolean value) {
      addCriterion("tableEffective <>", value, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveGreaterThan(Boolean value) {
      addCriterion("tableEffective >", value, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveGreaterThanOrEqualTo(Boolean value) {
      addCriterion("tableEffective >=", value, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveLessThan(Boolean value) {
      addCriterion("tableEffective <", value, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveLessThanOrEqualTo(Boolean value) {
      addCriterion("tableEffective <=", value, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveIn(List<Boolean> values) {
      addCriterion("tableEffective in", values, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveNotIn(List<Boolean> values) {
      addCriterion("tableEffective not in", values, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveBetween(Boolean value1, Boolean value2) {
      addCriterion("tableEffective between", value1, value2, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andTableeffectiveNotBetween(Boolean value1, Boolean value2) {
      addCriterion("tableEffective not between", value1, value2, "tableeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveIsNull() {
      addCriterion("roomEffective is null");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveIsNotNull() {
      addCriterion("roomEffective is not null");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveEqualTo(Boolean value) {
      addCriterion("roomEffective =", value, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveNotEqualTo(Boolean value) {
      addCriterion("roomEffective <>", value, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveGreaterThan(Boolean value) {
      addCriterion("roomEffective >", value, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveGreaterThanOrEqualTo(Boolean value) {
      addCriterion("roomEffective >=", value, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveLessThan(Boolean value) {
      addCriterion("roomEffective <", value, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveLessThanOrEqualTo(Boolean value) {
      addCriterion("roomEffective <=", value, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveIn(List<Boolean> values) {
      addCriterion("roomEffective in", values, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveNotIn(List<Boolean> values) {
      addCriterion("roomEffective not in", values, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveBetween(Boolean value1, Boolean value2) {
      addCriterion("roomEffective between", value1, value2, "roomeffective");
      return (Criteria) this;
    }

    public Criteria andRoomeffectiveNotBetween(Boolean value1, Boolean value2) {
      addCriterion("roomEffective not between", value1, value2, "roomeffective");
      return (Criteria) this;
    }
  }

  /**
   */
  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  public static class Criterion {
    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}
