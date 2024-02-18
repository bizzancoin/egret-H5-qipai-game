package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class WknhCurveExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public WknhCurveExample() {
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

    public Criteria andAngleIsNull() {
      addCriterion("angle is null");
      return (Criteria) this;
    }

    public Criteria andAngleIsNotNull() {
      addCriterion("angle is not null");
      return (Criteria) this;
    }

    public Criteria andAngleEqualTo(Integer value) {
      addCriterion("angle =", value, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleNotEqualTo(Integer value) {
      addCriterion("angle <>", value, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleGreaterThan(Integer value) {
      addCriterion("angle >", value, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleGreaterThanOrEqualTo(Integer value) {
      addCriterion("angle >=", value, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleLessThan(Integer value) {
      addCriterion("angle <", value, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleLessThanOrEqualTo(Integer value) {
      addCriterion("angle <=", value, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleIn(List<Integer> values) {
      addCriterion("angle in", values, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleNotIn(List<Integer> values) {
      addCriterion("angle not in", values, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleBetween(Integer value1, Integer value2) {
      addCriterion("angle between", value1, value2, "angle");
      return (Criteria) this;
    }

    public Criteria andAngleNotBetween(Integer value1, Integer value2) {
      addCriterion("angle not between", value1, value2, "angle");
      return (Criteria) this;
    }

    public Criteria andRatioIsNull() {
      addCriterion("ratio is null");
      return (Criteria) this;
    }

    public Criteria andRatioIsNotNull() {
      addCriterion("ratio is not null");
      return (Criteria) this;
    }

    public Criteria andRatioEqualTo(Integer value) {
      addCriterion("ratio =", value, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioNotEqualTo(Integer value) {
      addCriterion("ratio <>", value, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioGreaterThan(Integer value) {
      addCriterion("ratio >", value, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioGreaterThanOrEqualTo(Integer value) {
      addCriterion("ratio >=", value, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioLessThan(Integer value) {
      addCriterion("ratio <", value, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioLessThanOrEqualTo(Integer value) {
      addCriterion("ratio <=", value, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioIn(List<Integer> values) {
      addCriterion("ratio in", values, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioNotIn(List<Integer> values) {
      addCriterion("ratio not in", values, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioBetween(Integer value1, Integer value2) {
      addCriterion("ratio between", value1, value2, "ratio");
      return (Criteria) this;
    }

    public Criteria andRatioNotBetween(Integer value1, Integer value2) {
      addCriterion("ratio not between", value1, value2, "ratio");
      return (Criteria) this;
    }

    public Criteria andDataIsNull() {
      addCriterion("data is null");
      return (Criteria) this;
    }

    public Criteria andDataIsNotNull() {
      addCriterion("data is not null");
      return (Criteria) this;
    }

    public Criteria andDataEqualTo(String value) {
      addCriterion("data =", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataNotEqualTo(String value) {
      addCriterion("data <>", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataGreaterThan(String value) {
      addCriterion("data >", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataGreaterThanOrEqualTo(String value) {
      addCriterion("data >=", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataLessThan(String value) {
      addCriterion("data <", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataLessThanOrEqualTo(String value) {
      addCriterion("data <=", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataLike(String value) {
      addCriterion("data like", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataNotLike(String value) {
      addCriterion("data not like", value, "data");
      return (Criteria) this;
    }

    public Criteria andDataIn(List<String> values) {
      addCriterion("data in", values, "data");
      return (Criteria) this;
    }

    public Criteria andDataNotIn(List<String> values) {
      addCriterion("data not in", values, "data");
      return (Criteria) this;
    }

    public Criteria andDataBetween(String value1, String value2) {
      addCriterion("data between", value1, value2, "data");
      return (Criteria) this;
    }

    public Criteria andDataNotBetween(String value1, String value2) {
      addCriterion("data not between", value1, value2, "data");
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
