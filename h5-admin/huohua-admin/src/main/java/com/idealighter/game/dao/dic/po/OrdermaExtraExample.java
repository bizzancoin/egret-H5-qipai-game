package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class OrdermaExtraExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public OrdermaExtraExample() {
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

    public Criteria andMinIsNull() {
      addCriterion("min is null");
      return (Criteria) this;
    }

    public Criteria andMinIsNotNull() {
      addCriterion("min is not null");
      return (Criteria) this;
    }

    public Criteria andMinEqualTo(Integer value) {
      addCriterion("min =", value, "min");
      return (Criteria) this;
    }

    public Criteria andMinNotEqualTo(Integer value) {
      addCriterion("min <>", value, "min");
      return (Criteria) this;
    }

    public Criteria andMinGreaterThan(Integer value) {
      addCriterion("min >", value, "min");
      return (Criteria) this;
    }

    public Criteria andMinGreaterThanOrEqualTo(Integer value) {
      addCriterion("min >=", value, "min");
      return (Criteria) this;
    }

    public Criteria andMinLessThan(Integer value) {
      addCriterion("min <", value, "min");
      return (Criteria) this;
    }

    public Criteria andMinLessThanOrEqualTo(Integer value) {
      addCriterion("min <=", value, "min");
      return (Criteria) this;
    }

    public Criteria andMinIn(List<Integer> values) {
      addCriterion("min in", values, "min");
      return (Criteria) this;
    }

    public Criteria andMinNotIn(List<Integer> values) {
      addCriterion("min not in", values, "min");
      return (Criteria) this;
    }

    public Criteria andMinBetween(Integer value1, Integer value2) {
      addCriterion("min between", value1, value2, "min");
      return (Criteria) this;
    }

    public Criteria andMinNotBetween(Integer value1, Integer value2) {
      addCriterion("min not between", value1, value2, "min");
      return (Criteria) this;
    }

    public Criteria andMaxIsNull() {
      addCriterion("max is null");
      return (Criteria) this;
    }

    public Criteria andMaxIsNotNull() {
      addCriterion("max is not null");
      return (Criteria) this;
    }

    public Criteria andMaxEqualTo(Integer value) {
      addCriterion("max =", value, "max");
      return (Criteria) this;
    }

    public Criteria andMaxNotEqualTo(Integer value) {
      addCriterion("max <>", value, "max");
      return (Criteria) this;
    }

    public Criteria andMaxGreaterThan(Integer value) {
      addCriterion("max >", value, "max");
      return (Criteria) this;
    }

    public Criteria andMaxGreaterThanOrEqualTo(Integer value) {
      addCriterion("max >=", value, "max");
      return (Criteria) this;
    }

    public Criteria andMaxLessThan(Integer value) {
      addCriterion("max <", value, "max");
      return (Criteria) this;
    }

    public Criteria andMaxLessThanOrEqualTo(Integer value) {
      addCriterion("max <=", value, "max");
      return (Criteria) this;
    }

    public Criteria andMaxIn(List<Integer> values) {
      addCriterion("max in", values, "max");
      return (Criteria) this;
    }

    public Criteria andMaxNotIn(List<Integer> values) {
      addCriterion("max not in", values, "max");
      return (Criteria) this;
    }

    public Criteria andMaxBetween(Integer value1, Integer value2) {
      addCriterion("max between", value1, value2, "max");
      return (Criteria) this;
    }

    public Criteria andMaxNotBetween(Integer value1, Integer value2) {
      addCriterion("max not between", value1, value2, "max");
      return (Criteria) this;
    }

    public Criteria andMultipleIsNull() {
      addCriterion("multiple is null");
      return (Criteria) this;
    }

    public Criteria andMultipleIsNotNull() {
      addCriterion("multiple is not null");
      return (Criteria) this;
    }

    public Criteria andMultipleEqualTo(Integer value) {
      addCriterion("multiple =", value, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleNotEqualTo(Integer value) {
      addCriterion("multiple <>", value, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleGreaterThan(Integer value) {
      addCriterion("multiple >", value, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleGreaterThanOrEqualTo(Integer value) {
      addCriterion("multiple >=", value, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleLessThan(Integer value) {
      addCriterion("multiple <", value, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleLessThanOrEqualTo(Integer value) {
      addCriterion("multiple <=", value, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleIn(List<Integer> values) {
      addCriterion("multiple in", values, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleNotIn(List<Integer> values) {
      addCriterion("multiple not in", values, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleBetween(Integer value1, Integer value2) {
      addCriterion("multiple between", value1, value2, "multiple");
      return (Criteria) this;
    }

    public Criteria andMultipleNotBetween(Integer value1, Integer value2) {
      addCriterion("multiple not between", value1, value2, "multiple");
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
