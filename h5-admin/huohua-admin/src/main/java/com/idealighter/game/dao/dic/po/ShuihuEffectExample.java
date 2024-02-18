package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class ShuihuEffectExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public ShuihuEffectExample() {
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

    public Criteria andKeyIsNull() {
      addCriterion("key is null");
      return (Criteria) this;
    }

    public Criteria andKeyIsNotNull() {
      addCriterion("key is not null");
      return (Criteria) this;
    }

    public Criteria andKeyEqualTo(String value) {
      addCriterion("key =", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyNotEqualTo(String value) {
      addCriterion("key <>", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyGreaterThan(String value) {
      addCriterion("key >", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyGreaterThanOrEqualTo(String value) {
      addCriterion("key >=", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyLessThan(String value) {
      addCriterion("key <", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyLessThanOrEqualTo(String value) {
      addCriterion("key <=", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyLike(String value) {
      addCriterion("key like", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyNotLike(String value) {
      addCriterion("key not like", value, "key");
      return (Criteria) this;
    }

    public Criteria andKeyIn(List<String> values) {
      addCriterion("key in", values, "key");
      return (Criteria) this;
    }

    public Criteria andKeyNotIn(List<String> values) {
      addCriterion("key not in", values, "key");
      return (Criteria) this;
    }

    public Criteria andKeyBetween(String value1, String value2) {
      addCriterion("key between", value1, value2, "key");
      return (Criteria) this;
    }

    public Criteria andKeyNotBetween(String value1, String value2) {
      addCriterion("key not between", value1, value2, "key");
      return (Criteria) this;
    }

    public Criteria andRemarksIsNull() {
      addCriterion("remarks is null");
      return (Criteria) this;
    }

    public Criteria andRemarksIsNotNull() {
      addCriterion("remarks is not null");
      return (Criteria) this;
    }

    public Criteria andRemarksEqualTo(String value) {
      addCriterion("remarks =", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotEqualTo(String value) {
      addCriterion("remarks <>", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksGreaterThan(String value) {
      addCriterion("remarks >", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksGreaterThanOrEqualTo(String value) {
      addCriterion("remarks >=", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksLessThan(String value) {
      addCriterion("remarks <", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksLessThanOrEqualTo(String value) {
      addCriterion("remarks <=", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksLike(String value) {
      addCriterion("remarks like", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotLike(String value) {
      addCriterion("remarks not like", value, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksIn(List<String> values) {
      addCriterion("remarks in", values, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotIn(List<String> values) {
      addCriterion("remarks not in", values, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksBetween(String value1, String value2) {
      addCriterion("remarks between", value1, value2, "remarks");
      return (Criteria) this;
    }

    public Criteria andRemarksNotBetween(String value1, String value2) {
      addCriterion("remarks not between", value1, value2, "remarks");
      return (Criteria) this;
    }

    public Criteria andPicnameIsNull() {
      addCriterion("picName is null");
      return (Criteria) this;
    }

    public Criteria andPicnameIsNotNull() {
      addCriterion("picName is not null");
      return (Criteria) this;
    }

    public Criteria andPicnameEqualTo(String value) {
      addCriterion("picName =", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameNotEqualTo(String value) {
      addCriterion("picName <>", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameGreaterThan(String value) {
      addCriterion("picName >", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameGreaterThanOrEqualTo(String value) {
      addCriterion("picName >=", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameLessThan(String value) {
      addCriterion("picName <", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameLessThanOrEqualTo(String value) {
      addCriterion("picName <=", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameLike(String value) {
      addCriterion("picName like", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameNotLike(String value) {
      addCriterion("picName not like", value, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameIn(List<String> values) {
      addCriterion("picName in", values, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameNotIn(List<String> values) {
      addCriterion("picName not in", values, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameBetween(String value1, String value2) {
      addCriterion("picName between", value1, value2, "picname");
      return (Criteria) this;
    }

    public Criteria andPicnameNotBetween(String value1, String value2) {
      addCriterion("picName not between", value1, value2, "picname");
      return (Criteria) this;
    }

    public Criteria andFrametimeIsNull() {
      addCriterion("frameTime is null");
      return (Criteria) this;
    }

    public Criteria andFrametimeIsNotNull() {
      addCriterion("frameTime is not null");
      return (Criteria) this;
    }

    public Criteria andFrametimeEqualTo(Integer value) {
      addCriterion("frameTime =", value, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeNotEqualTo(Integer value) {
      addCriterion("frameTime <>", value, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeGreaterThan(Integer value) {
      addCriterion("frameTime >", value, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeGreaterThanOrEqualTo(Integer value) {
      addCriterion("frameTime >=", value, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeLessThan(Integer value) {
      addCriterion("frameTime <", value, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeLessThanOrEqualTo(Integer value) {
      addCriterion("frameTime <=", value, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeIn(List<Integer> values) {
      addCriterion("frameTime in", values, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeNotIn(List<Integer> values) {
      addCriterion("frameTime not in", values, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeBetween(Integer value1, Integer value2) {
      addCriterion("frameTime between", value1, value2, "frametime");
      return (Criteria) this;
    }

    public Criteria andFrametimeNotBetween(Integer value1, Integer value2) {
      addCriterion("frameTime not between", value1, value2, "frametime");
      return (Criteria) this;
    }

    public Criteria andBeginframeIsNull() {
      addCriterion("beginFrame is null");
      return (Criteria) this;
    }

    public Criteria andBeginframeIsNotNull() {
      addCriterion("beginFrame is not null");
      return (Criteria) this;
    }

    public Criteria andBeginframeEqualTo(Integer value) {
      addCriterion("beginFrame =", value, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeNotEqualTo(Integer value) {
      addCriterion("beginFrame <>", value, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeGreaterThan(Integer value) {
      addCriterion("beginFrame >", value, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeGreaterThanOrEqualTo(Integer value) {
      addCriterion("beginFrame >=", value, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeLessThan(Integer value) {
      addCriterion("beginFrame <", value, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeLessThanOrEqualTo(Integer value) {
      addCriterion("beginFrame <=", value, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeIn(List<Integer> values) {
      addCriterion("beginFrame in", values, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeNotIn(List<Integer> values) {
      addCriterion("beginFrame not in", values, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeBetween(Integer value1, Integer value2) {
      addCriterion("beginFrame between", value1, value2, "beginframe");
      return (Criteria) this;
    }

    public Criteria andBeginframeNotBetween(Integer value1, Integer value2) {
      addCriterion("beginFrame not between", value1, value2, "beginframe");
      return (Criteria) this;
    }

    public Criteria andLengthIsNull() {
      addCriterion("length is null");
      return (Criteria) this;
    }

    public Criteria andLengthIsNotNull() {
      addCriterion("length is not null");
      return (Criteria) this;
    }

    public Criteria andLengthEqualTo(Integer value) {
      addCriterion("length =", value, "length");
      return (Criteria) this;
    }

    public Criteria andLengthNotEqualTo(Integer value) {
      addCriterion("length <>", value, "length");
      return (Criteria) this;
    }

    public Criteria andLengthGreaterThan(Integer value) {
      addCriterion("length >", value, "length");
      return (Criteria) this;
    }

    public Criteria andLengthGreaterThanOrEqualTo(Integer value) {
      addCriterion("length >=", value, "length");
      return (Criteria) this;
    }

    public Criteria andLengthLessThan(Integer value) {
      addCriterion("length <", value, "length");
      return (Criteria) this;
    }

    public Criteria andLengthLessThanOrEqualTo(Integer value) {
      addCriterion("length <=", value, "length");
      return (Criteria) this;
    }

    public Criteria andLengthIn(List<Integer> values) {
      addCriterion("length in", values, "length");
      return (Criteria) this;
    }

    public Criteria andLengthNotIn(List<Integer> values) {
      addCriterion("length not in", values, "length");
      return (Criteria) this;
    }

    public Criteria andLengthBetween(Integer value1, Integer value2) {
      addCriterion("length between", value1, value2, "length");
      return (Criteria) this;
    }

    public Criteria andLengthNotBetween(Integer value1, Integer value2) {
      addCriterion("length not between", value1, value2, "length");
      return (Criteria) this;
    }

    public Criteria andIsloopIsNull() {
      addCriterion("isLoop is null");
      return (Criteria) this;
    }

    public Criteria andIsloopIsNotNull() {
      addCriterion("isLoop is not null");
      return (Criteria) this;
    }

    public Criteria andIsloopEqualTo(Integer value) {
      addCriterion("isLoop =", value, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopNotEqualTo(Integer value) {
      addCriterion("isLoop <>", value, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopGreaterThan(Integer value) {
      addCriterion("isLoop >", value, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopGreaterThanOrEqualTo(Integer value) {
      addCriterion("isLoop >=", value, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopLessThan(Integer value) {
      addCriterion("isLoop <", value, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopLessThanOrEqualTo(Integer value) {
      addCriterion("isLoop <=", value, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopIn(List<Integer> values) {
      addCriterion("isLoop in", values, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopNotIn(List<Integer> values) {
      addCriterion("isLoop not in", values, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopBetween(Integer value1, Integer value2) {
      addCriterion("isLoop between", value1, value2, "isloop");
      return (Criteria) this;
    }

    public Criteria andIsloopNotBetween(Integer value1, Integer value2) {
      addCriterion("isLoop not between", value1, value2, "isloop");
      return (Criteria) this;
    }

    public Criteria andLifetimeIsNull() {
      addCriterion("lifeTime is null");
      return (Criteria) this;
    }

    public Criteria andLifetimeIsNotNull() {
      addCriterion("lifeTime is not null");
      return (Criteria) this;
    }

    public Criteria andLifetimeEqualTo(Integer value) {
      addCriterion("lifeTime =", value, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeNotEqualTo(Integer value) {
      addCriterion("lifeTime <>", value, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeGreaterThan(Integer value) {
      addCriterion("lifeTime >", value, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeGreaterThanOrEqualTo(Integer value) {
      addCriterion("lifeTime >=", value, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeLessThan(Integer value) {
      addCriterion("lifeTime <", value, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeLessThanOrEqualTo(Integer value) {
      addCriterion("lifeTime <=", value, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeIn(List<Integer> values) {
      addCriterion("lifeTime in", values, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeNotIn(List<Integer> values) {
      addCriterion("lifeTime not in", values, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeBetween(Integer value1, Integer value2) {
      addCriterion("lifeTime between", value1, value2, "lifetime");
      return (Criteria) this;
    }

    public Criteria andLifetimeNotBetween(Integer value1, Integer value2) {
      addCriterion("lifeTime not between", value1, value2, "lifetime");
      return (Criteria) this;
    }

    public Criteria andIsmoveIsNull() {
      addCriterion("isMove is null");
      return (Criteria) this;
    }

    public Criteria andIsmoveIsNotNull() {
      addCriterion("isMove is not null");
      return (Criteria) this;
    }

    public Criteria andIsmoveEqualTo(Integer value) {
      addCriterion("isMove =", value, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveNotEqualTo(Integer value) {
      addCriterion("isMove <>", value, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveGreaterThan(Integer value) {
      addCriterion("isMove >", value, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveGreaterThanOrEqualTo(Integer value) {
      addCriterion("isMove >=", value, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveLessThan(Integer value) {
      addCriterion("isMove <", value, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveLessThanOrEqualTo(Integer value) {
      addCriterion("isMove <=", value, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveIn(List<Integer> values) {
      addCriterion("isMove in", values, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveNotIn(List<Integer> values) {
      addCriterion("isMove not in", values, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveBetween(Integer value1, Integer value2) {
      addCriterion("isMove between", value1, value2, "ismove");
      return (Criteria) this;
    }

    public Criteria andIsmoveNotBetween(Integer value1, Integer value2) {
      addCriterion("isMove not between", value1, value2, "ismove");
      return (Criteria) this;
    }

    public Criteria andMovetimeIsNull() {
      addCriterion("moveTime is null");
      return (Criteria) this;
    }

    public Criteria andMovetimeIsNotNull() {
      addCriterion("moveTime is not null");
      return (Criteria) this;
    }

    public Criteria andMovetimeEqualTo(Integer value) {
      addCriterion("moveTime =", value, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeNotEqualTo(Integer value) {
      addCriterion("moveTime <>", value, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeGreaterThan(Integer value) {
      addCriterion("moveTime >", value, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeGreaterThanOrEqualTo(Integer value) {
      addCriterion("moveTime >=", value, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeLessThan(Integer value) {
      addCriterion("moveTime <", value, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeLessThanOrEqualTo(Integer value) {
      addCriterion("moveTime <=", value, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeIn(List<Integer> values) {
      addCriterion("moveTime in", values, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeNotIn(List<Integer> values) {
      addCriterion("moveTime not in", values, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeBetween(Integer value1, Integer value2) {
      addCriterion("moveTime between", value1, value2, "movetime");
      return (Criteria) this;
    }

    public Criteria andMovetimeNotBetween(Integer value1, Integer value2) {
      addCriterion("moveTime not between", value1, value2, "movetime");
      return (Criteria) this;
    }

    public Criteria andBeginposIsNull() {
      addCriterion("beginPos is null");
      return (Criteria) this;
    }

    public Criteria andBeginposIsNotNull() {
      addCriterion("beginPos is not null");
      return (Criteria) this;
    }

    public Criteria andBeginposEqualTo(String value) {
      addCriterion("beginPos =", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposNotEqualTo(String value) {
      addCriterion("beginPos <>", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposGreaterThan(String value) {
      addCriterion("beginPos >", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposGreaterThanOrEqualTo(String value) {
      addCriterion("beginPos >=", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposLessThan(String value) {
      addCriterion("beginPos <", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposLessThanOrEqualTo(String value) {
      addCriterion("beginPos <=", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposLike(String value) {
      addCriterion("beginPos like", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposNotLike(String value) {
      addCriterion("beginPos not like", value, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposIn(List<String> values) {
      addCriterion("beginPos in", values, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposNotIn(List<String> values) {
      addCriterion("beginPos not in", values, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposBetween(String value1, String value2) {
      addCriterion("beginPos between", value1, value2, "beginpos");
      return (Criteria) this;
    }

    public Criteria andBeginposNotBetween(String value1, String value2) {
      addCriterion("beginPos not between", value1, value2, "beginpos");
      return (Criteria) this;
    }

    public Criteria andEndposIsNull() {
      addCriterion("endPos is null");
      return (Criteria) this;
    }

    public Criteria andEndposIsNotNull() {
      addCriterion("endPos is not null");
      return (Criteria) this;
    }

    public Criteria andEndposEqualTo(String value) {
      addCriterion("endPos =", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposNotEqualTo(String value) {
      addCriterion("endPos <>", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposGreaterThan(String value) {
      addCriterion("endPos >", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposGreaterThanOrEqualTo(String value) {
      addCriterion("endPos >=", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposLessThan(String value) {
      addCriterion("endPos <", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposLessThanOrEqualTo(String value) {
      addCriterion("endPos <=", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposLike(String value) {
      addCriterion("endPos like", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposNotLike(String value) {
      addCriterion("endPos not like", value, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposIn(List<String> values) {
      addCriterion("endPos in", values, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposNotIn(List<String> values) {
      addCriterion("endPos not in", values, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposBetween(String value1, String value2) {
      addCriterion("endPos between", value1, value2, "endpos");
      return (Criteria) this;
    }

    public Criteria andEndposNotBetween(String value1, String value2) {
      addCriterion("endPos not between", value1, value2, "endpos");
      return (Criteria) this;
    }

    public Criteria andMusicidIsNull() {
      addCriterion("musicId is null");
      return (Criteria) this;
    }

    public Criteria andMusicidIsNotNull() {
      addCriterion("musicId is not null");
      return (Criteria) this;
    }

    public Criteria andMusicidEqualTo(Integer value) {
      addCriterion("musicId =", value, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidNotEqualTo(Integer value) {
      addCriterion("musicId <>", value, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidGreaterThan(Integer value) {
      addCriterion("musicId >", value, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidGreaterThanOrEqualTo(Integer value) {
      addCriterion("musicId >=", value, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidLessThan(Integer value) {
      addCriterion("musicId <", value, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidLessThanOrEqualTo(Integer value) {
      addCriterion("musicId <=", value, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidIn(List<Integer> values) {
      addCriterion("musicId in", values, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidNotIn(List<Integer> values) {
      addCriterion("musicId not in", values, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidBetween(Integer value1, Integer value2) {
      addCriterion("musicId between", value1, value2, "musicid");
      return (Criteria) this;
    }

    public Criteria andMusicidNotBetween(Integer value1, Integer value2) {
      addCriterion("musicId not between", value1, value2, "musicid");
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
