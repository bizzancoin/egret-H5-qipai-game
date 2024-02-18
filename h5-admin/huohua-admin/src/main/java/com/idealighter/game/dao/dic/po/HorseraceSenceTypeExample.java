package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class HorseraceSenceTypeExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public HorseraceSenceTypeExample() {
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

    public Criteria andBgnameIsNull() {
      addCriterion("bgName is null");
      return (Criteria) this;
    }

    public Criteria andBgnameIsNotNull() {
      addCriterion("bgName is not null");
      return (Criteria) this;
    }

    public Criteria andBgnameEqualTo(String value) {
      addCriterion("bgName =", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameNotEqualTo(String value) {
      addCriterion("bgName <>", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameGreaterThan(String value) {
      addCriterion("bgName >", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameGreaterThanOrEqualTo(String value) {
      addCriterion("bgName >=", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameLessThan(String value) {
      addCriterion("bgName <", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameLessThanOrEqualTo(String value) {
      addCriterion("bgName <=", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameLike(String value) {
      addCriterion("bgName like", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameNotLike(String value) {
      addCriterion("bgName not like", value, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameIn(List<String> values) {
      addCriterion("bgName in", values, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameNotIn(List<String> values) {
      addCriterion("bgName not in", values, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameBetween(String value1, String value2) {
      addCriterion("bgName between", value1, value2, "bgname");
      return (Criteria) this;
    }

    public Criteria andBgnameNotBetween(String value1, String value2) {
      addCriterion("bgName not between", value1, value2, "bgname");
      return (Criteria) this;
    }

    public Criteria andSenceidIsNull() {
      addCriterion("senceId is null");
      return (Criteria) this;
    }

    public Criteria andSenceidIsNotNull() {
      addCriterion("senceId is not null");
      return (Criteria) this;
    }

    public Criteria andSenceidEqualTo(Integer value) {
      addCriterion("senceId =", value, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidNotEqualTo(Integer value) {
      addCriterion("senceId <>", value, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidGreaterThan(Integer value) {
      addCriterion("senceId >", value, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidGreaterThanOrEqualTo(Integer value) {
      addCriterion("senceId >=", value, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidLessThan(Integer value) {
      addCriterion("senceId <", value, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidLessThanOrEqualTo(Integer value) {
      addCriterion("senceId <=", value, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidIn(List<Integer> values) {
      addCriterion("senceId in", values, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidNotIn(List<Integer> values) {
      addCriterion("senceId not in", values, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidBetween(Integer value1, Integer value2) {
      addCriterion("senceId between", value1, value2, "senceid");
      return (Criteria) this;
    }

    public Criteria andSenceidNotBetween(Integer value1, Integer value2) {
      addCriterion("senceId not between", value1, value2, "senceid");
      return (Criteria) this;
    }

    public Criteria andMiddleIsNull() {
      addCriterion("middle is null");
      return (Criteria) this;
    }

    public Criteria andMiddleIsNotNull() {
      addCriterion("middle is not null");
      return (Criteria) this;
    }

    public Criteria andMiddleEqualTo(Integer value) {
      addCriterion("middle =", value, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleNotEqualTo(Integer value) {
      addCriterion("middle <>", value, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleGreaterThan(Integer value) {
      addCriterion("middle >", value, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleGreaterThanOrEqualTo(Integer value) {
      addCriterion("middle >=", value, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleLessThan(Integer value) {
      addCriterion("middle <", value, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleLessThanOrEqualTo(Integer value) {
      addCriterion("middle <=", value, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleIn(List<Integer> values) {
      addCriterion("middle in", values, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleNotIn(List<Integer> values) {
      addCriterion("middle not in", values, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleBetween(Integer value1, Integer value2) {
      addCriterion("middle between", value1, value2, "middle");
      return (Criteria) this;
    }

    public Criteria andMiddleNotBetween(Integer value1, Integer value2) {
      addCriterion("middle not between", value1, value2, "middle");
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
