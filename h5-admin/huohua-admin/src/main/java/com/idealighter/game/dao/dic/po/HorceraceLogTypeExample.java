package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class HorceraceLogTypeExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public HorceraceLogTypeExample() {
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

    public Criteria andPlanidIsNull() {
      addCriterion("planId is null");
      return (Criteria) this;
    }

    public Criteria andPlanidIsNotNull() {
      addCriterion("planId is not null");
      return (Criteria) this;
    }

    public Criteria andPlanidEqualTo(Integer value) {
      addCriterion("planId =", value, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidNotEqualTo(Integer value) {
      addCriterion("planId <>", value, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidGreaterThan(Integer value) {
      addCriterion("planId >", value, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidGreaterThanOrEqualTo(Integer value) {
      addCriterion("planId >=", value, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidLessThan(Integer value) {
      addCriterion("planId <", value, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidLessThanOrEqualTo(Integer value) {
      addCriterion("planId <=", value, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidIn(List<Integer> values) {
      addCriterion("planId in", values, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidNotIn(List<Integer> values) {
      addCriterion("planId not in", values, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidBetween(Integer value1, Integer value2) {
      addCriterion("planId between", value1, value2, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanidNotBetween(Integer value1, Integer value2) {
      addCriterion("planId not between", value1, value2, "planid");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityIsNull() {
      addCriterion("planProbability is null");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityIsNotNull() {
      addCriterion("planProbability is not null");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityEqualTo(Integer value) {
      addCriterion("planProbability =", value, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityNotEqualTo(Integer value) {
      addCriterion("planProbability <>", value, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityGreaterThan(Integer value) {
      addCriterion("planProbability >", value, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityGreaterThanOrEqualTo(Integer value) {
      addCriterion("planProbability >=", value, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityLessThan(Integer value) {
      addCriterion("planProbability <", value, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityLessThanOrEqualTo(Integer value) {
      addCriterion("planProbability <=", value, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityIn(List<Integer> values) {
      addCriterion("planProbability in", values, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityNotIn(List<Integer> values) {
      addCriterion("planProbability not in", values, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityBetween(Integer value1, Integer value2) {
      addCriterion("planProbability between", value1, value2, "planprobability");
      return (Criteria) this;
    }

    public Criteria andPlanprobabilityNotBetween(Integer value1, Integer value2) {
      addCriterion("planProbability not between", value1, value2, "planprobability");
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
