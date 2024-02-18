package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class LkpyFishExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public LkpyFishExample() {
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

    public Criteria andBbxIdIsNull() {
      addCriterion("bbx_id is null");
      return (Criteria) this;
    }

    public Criteria andBbxIdIsNotNull() {
      addCriterion("bbx_id is not null");
      return (Criteria) this;
    }

    public Criteria andBbxIdEqualTo(Integer value) {
      addCriterion("bbx_id =", value, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdNotEqualTo(Integer value) {
      addCriterion("bbx_id <>", value, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdGreaterThan(Integer value) {
      addCriterion("bbx_id >", value, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("bbx_id >=", value, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdLessThan(Integer value) {
      addCriterion("bbx_id <", value, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdLessThanOrEqualTo(Integer value) {
      addCriterion("bbx_id <=", value, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdIn(List<Integer> values) {
      addCriterion("bbx_id in", values, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdNotIn(List<Integer> values) {
      addCriterion("bbx_id not in", values, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdBetween(Integer value1, Integer value2) {
      addCriterion("bbx_id between", value1, value2, "bbxId");
      return (Criteria) this;
    }

    public Criteria andBbxIdNotBetween(Integer value1, Integer value2) {
      addCriterion("bbx_id not between", value1, value2, "bbxId");
      return (Criteria) this;
    }

    public Criteria andLockLeveIsNull() {
      addCriterion("lock_leve is null");
      return (Criteria) this;
    }

    public Criteria andLockLeveIsNotNull() {
      addCriterion("lock_leve is not null");
      return (Criteria) this;
    }

    public Criteria andLockLeveEqualTo(Integer value) {
      addCriterion("lock_leve =", value, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveNotEqualTo(Integer value) {
      addCriterion("lock_leve <>", value, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveGreaterThan(Integer value) {
      addCriterion("lock_leve >", value, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveGreaterThanOrEqualTo(Integer value) {
      addCriterion("lock_leve >=", value, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveLessThan(Integer value) {
      addCriterion("lock_leve <", value, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveLessThanOrEqualTo(Integer value) {
      addCriterion("lock_leve <=", value, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveIn(List<Integer> values) {
      addCriterion("lock_leve in", values, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveNotIn(List<Integer> values) {
      addCriterion("lock_leve not in", values, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveBetween(Integer value1, Integer value2) {
      addCriterion("lock_leve between", value1, value2, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andLockLeveNotBetween(Integer value1, Integer value2) {
      addCriterion("lock_leve not between", value1, value2, "lockLeve");
      return (Criteria) this;
    }

    public Criteria andTypeIsNull() {
      addCriterion("type is null");
      return (Criteria) this;
    }

    public Criteria andTypeIsNotNull() {
      addCriterion("type is not null");
      return (Criteria) this;
    }

    public Criteria andTypeEqualTo(Integer value) {
      addCriterion("type =", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotEqualTo(Integer value) {
      addCriterion("type <>", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThan(Integer value) {
      addCriterion("type >", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
      addCriterion("type >=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThan(Integer value) {
      addCriterion("type <", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThanOrEqualTo(Integer value) {
      addCriterion("type <=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeIn(List<Integer> values) {
      addCriterion("type in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotIn(List<Integer> values) {
      addCriterion("type not in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeBetween(Integer value1, Integer value2) {
      addCriterion("type between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotBetween(Integer value1, Integer value2) {
      addCriterion("type not between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andFishsIsNull() {
      addCriterion("fishs is null");
      return (Criteria) this;
    }

    public Criteria andFishsIsNotNull() {
      addCriterion("fishs is not null");
      return (Criteria) this;
    }

    public Criteria andFishsEqualTo(String value) {
      addCriterion("fishs =", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsNotEqualTo(String value) {
      addCriterion("fishs <>", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsGreaterThan(String value) {
      addCriterion("fishs >", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsGreaterThanOrEqualTo(String value) {
      addCriterion("fishs >=", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsLessThan(String value) {
      addCriterion("fishs <", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsLessThanOrEqualTo(String value) {
      addCriterion("fishs <=", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsLike(String value) {
      addCriterion("fishs like", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsNotLike(String value) {
      addCriterion("fishs not like", value, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsIn(List<String> values) {
      addCriterion("fishs in", values, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsNotIn(List<String> values) {
      addCriterion("fishs not in", values, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsBetween(String value1, String value2) {
      addCriterion("fishs between", value1, value2, "fishs");
      return (Criteria) this;
    }

    public Criteria andFishsNotBetween(String value1, String value2) {
      addCriterion("fishs not between", value1, value2, "fishs");
      return (Criteria) this;
    }

    public Criteria andMinRateIsNull() {
      addCriterion("min_rate is null");
      return (Criteria) this;
    }

    public Criteria andMinRateIsNotNull() {
      addCriterion("min_rate is not null");
      return (Criteria) this;
    }

    public Criteria andMinRateEqualTo(Integer value) {
      addCriterion("min_rate =", value, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateNotEqualTo(Integer value) {
      addCriterion("min_rate <>", value, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateGreaterThan(Integer value) {
      addCriterion("min_rate >", value, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateGreaterThanOrEqualTo(Integer value) {
      addCriterion("min_rate >=", value, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateLessThan(Integer value) {
      addCriterion("min_rate <", value, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateLessThanOrEqualTo(Integer value) {
      addCriterion("min_rate <=", value, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateIn(List<Integer> values) {
      addCriterion("min_rate in", values, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateNotIn(List<Integer> values) {
      addCriterion("min_rate not in", values, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateBetween(Integer value1, Integer value2) {
      addCriterion("min_rate between", value1, value2, "minRate");
      return (Criteria) this;
    }

    public Criteria andMinRateNotBetween(Integer value1, Integer value2) {
      addCriterion("min_rate not between", value1, value2, "minRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateIsNull() {
      addCriterion("max_rate is null");
      return (Criteria) this;
    }

    public Criteria andMaxRateIsNotNull() {
      addCriterion("max_rate is not null");
      return (Criteria) this;
    }

    public Criteria andMaxRateEqualTo(Integer value) {
      addCriterion("max_rate =", value, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateNotEqualTo(Integer value) {
      addCriterion("max_rate <>", value, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateGreaterThan(Integer value) {
      addCriterion("max_rate >", value, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateGreaterThanOrEqualTo(Integer value) {
      addCriterion("max_rate >=", value, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateLessThan(Integer value) {
      addCriterion("max_rate <", value, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateLessThanOrEqualTo(Integer value) {
      addCriterion("max_rate <=", value, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateIn(List<Integer> values) {
      addCriterion("max_rate in", values, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateNotIn(List<Integer> values) {
      addCriterion("max_rate not in", values, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateBetween(Integer value1, Integer value2) {
      addCriterion("max_rate between", value1, value2, "maxRate");
      return (Criteria) this;
    }

    public Criteria andMaxRateNotBetween(Integer value1, Integer value2) {
      addCriterion("max_rate not between", value1, value2, "maxRate");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyIsNull() {
      addCriterion("death_strategy is null");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyIsNotNull() {
      addCriterion("death_strategy is not null");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyEqualTo(Integer value) {
      addCriterion("death_strategy =", value, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyNotEqualTo(Integer value) {
      addCriterion("death_strategy <>", value, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyGreaterThan(Integer value) {
      addCriterion("death_strategy >", value, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyGreaterThanOrEqualTo(Integer value) {
      addCriterion("death_strategy >=", value, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyLessThan(Integer value) {
      addCriterion("death_strategy <", value, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyLessThanOrEqualTo(Integer value) {
      addCriterion("death_strategy <=", value, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyIn(List<Integer> values) {
      addCriterion("death_strategy in", values, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyNotIn(List<Integer> values) {
      addCriterion("death_strategy not in", values, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyBetween(Integer value1, Integer value2) {
      addCriterion("death_strategy between", value1, value2, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andDeathStrategyNotBetween(Integer value1, Integer value2) {
      addCriterion("death_strategy not between", value1, value2, "deathStrategy");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeIsNull() {
      addCriterion("notice_type is null");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeIsNotNull() {
      addCriterion("notice_type is not null");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeEqualTo(Integer value) {
      addCriterion("notice_type =", value, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeNotEqualTo(Integer value) {
      addCriterion("notice_type <>", value, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeGreaterThan(Integer value) {
      addCriterion("notice_type >", value, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeGreaterThanOrEqualTo(Integer value) {
      addCriterion("notice_type >=", value, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeLessThan(Integer value) {
      addCriterion("notice_type <", value, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeLessThanOrEqualTo(Integer value) {
      addCriterion("notice_type <=", value, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeIn(List<Integer> values) {
      addCriterion("notice_type in", values, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeNotIn(List<Integer> values) {
      addCriterion("notice_type not in", values, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeBetween(Integer value1, Integer value2) {
      addCriterion("notice_type between", value1, value2, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeTypeNotBetween(Integer value1, Integer value2) {
      addCriterion("notice_type not between", value1, value2, "noticeType");
      return (Criteria) this;
    }

    public Criteria andNoticeContentIsNull() {
      addCriterion("notice_content is null");
      return (Criteria) this;
    }

    public Criteria andNoticeContentIsNotNull() {
      addCriterion("notice_content is not null");
      return (Criteria) this;
    }

    public Criteria andNoticeContentEqualTo(String value) {
      addCriterion("notice_content =", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentNotEqualTo(String value) {
      addCriterion("notice_content <>", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentGreaterThan(String value) {
      addCriterion("notice_content >", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentGreaterThanOrEqualTo(String value) {
      addCriterion("notice_content >=", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentLessThan(String value) {
      addCriterion("notice_content <", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentLessThanOrEqualTo(String value) {
      addCriterion("notice_content <=", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentLike(String value) {
      addCriterion("notice_content like", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentNotLike(String value) {
      addCriterion("notice_content not like", value, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentIn(List<String> values) {
      addCriterion("notice_content in", values, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentNotIn(List<String> values) {
      addCriterion("notice_content not in", values, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentBetween(String value1, String value2) {
      addCriterion("notice_content between", value1, value2, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andNoticeContentNotBetween(String value1, String value2) {
      addCriterion("notice_content not between", value1, value2, "noticeContent");
      return (Criteria) this;
    }

    public Criteria andDescIsNull() {
      addCriterion("desc is null");
      return (Criteria) this;
    }

    public Criteria andDescIsNotNull() {
      addCriterion("desc is not null");
      return (Criteria) this;
    }

    public Criteria andDescEqualTo(String value) {
      addCriterion("desc =", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotEqualTo(String value) {
      addCriterion("desc <>", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescGreaterThan(String value) {
      addCriterion("desc >", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescGreaterThanOrEqualTo(String value) {
      addCriterion("desc >=", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescLessThan(String value) {
      addCriterion("desc <", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescLessThanOrEqualTo(String value) {
      addCriterion("desc <=", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescLike(String value) {
      addCriterion("desc like", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotLike(String value) {
      addCriterion("desc not like", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescIn(List<String> values) {
      addCriterion("desc in", values, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotIn(List<String> values) {
      addCriterion("desc not in", values, "desc");
      return (Criteria) this;
    }

    public Criteria andDescBetween(String value1, String value2) {
      addCriterion("desc between", value1, value2, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotBetween(String value1, String value2) {
      addCriterion("desc not between", value1, value2, "desc");
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
