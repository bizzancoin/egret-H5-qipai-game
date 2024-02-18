package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class HorseraceSence1Example {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public HorseraceSence1Example() {
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

    public Criteria andItemidIsNull() {
      addCriterion("itemId is null");
      return (Criteria) this;
    }

    public Criteria andItemidIsNotNull() {
      addCriterion("itemId is not null");
      return (Criteria) this;
    }

    public Criteria andItemidEqualTo(Integer value) {
      addCriterion("itemId =", value, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidNotEqualTo(Integer value) {
      addCriterion("itemId <>", value, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidGreaterThan(Integer value) {
      addCriterion("itemId >", value, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidGreaterThanOrEqualTo(Integer value) {
      addCriterion("itemId >=", value, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidLessThan(Integer value) {
      addCriterion("itemId <", value, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidLessThanOrEqualTo(Integer value) {
      addCriterion("itemId <=", value, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidIn(List<Integer> values) {
      addCriterion("itemId in", values, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidNotIn(List<Integer> values) {
      addCriterion("itemId not in", values, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidBetween(Integer value1, Integer value2) {
      addCriterion("itemId between", value1, value2, "itemid");
      return (Criteria) this;
    }

    public Criteria andItemidNotBetween(Integer value1, Integer value2) {
      addCriterion("itemId not between", value1, value2, "itemid");
      return (Criteria) this;
    }

    public Criteria andPxIsNull() {
      addCriterion("pX is null");
      return (Criteria) this;
    }

    public Criteria andPxIsNotNull() {
      addCriterion("pX is not null");
      return (Criteria) this;
    }

    public Criteria andPxEqualTo(Integer value) {
      addCriterion("pX =", value, "px");
      return (Criteria) this;
    }

    public Criteria andPxNotEqualTo(Integer value) {
      addCriterion("pX <>", value, "px");
      return (Criteria) this;
    }

    public Criteria andPxGreaterThan(Integer value) {
      addCriterion("pX >", value, "px");
      return (Criteria) this;
    }

    public Criteria andPxGreaterThanOrEqualTo(Integer value) {
      addCriterion("pX >=", value, "px");
      return (Criteria) this;
    }

    public Criteria andPxLessThan(Integer value) {
      addCriterion("pX <", value, "px");
      return (Criteria) this;
    }

    public Criteria andPxLessThanOrEqualTo(Integer value) {
      addCriterion("pX <=", value, "px");
      return (Criteria) this;
    }

    public Criteria andPxIn(List<Integer> values) {
      addCriterion("pX in", values, "px");
      return (Criteria) this;
    }

    public Criteria andPxNotIn(List<Integer> values) {
      addCriterion("pX not in", values, "px");
      return (Criteria) this;
    }

    public Criteria andPxBetween(Integer value1, Integer value2) {
      addCriterion("pX between", value1, value2, "px");
      return (Criteria) this;
    }

    public Criteria andPxNotBetween(Integer value1, Integer value2) {
      addCriterion("pX not between", value1, value2, "px");
      return (Criteria) this;
    }

    public Criteria andPyIsNull() {
      addCriterion("pY is null");
      return (Criteria) this;
    }

    public Criteria andPyIsNotNull() {
      addCriterion("pY is not null");
      return (Criteria) this;
    }

    public Criteria andPyEqualTo(Integer value) {
      addCriterion("pY =", value, "py");
      return (Criteria) this;
    }

    public Criteria andPyNotEqualTo(Integer value) {
      addCriterion("pY <>", value, "py");
      return (Criteria) this;
    }

    public Criteria andPyGreaterThan(Integer value) {
      addCriterion("pY >", value, "py");
      return (Criteria) this;
    }

    public Criteria andPyGreaterThanOrEqualTo(Integer value) {
      addCriterion("pY >=", value, "py");
      return (Criteria) this;
    }

    public Criteria andPyLessThan(Integer value) {
      addCriterion("pY <", value, "py");
      return (Criteria) this;
    }

    public Criteria andPyLessThanOrEqualTo(Integer value) {
      addCriterion("pY <=", value, "py");
      return (Criteria) this;
    }

    public Criteria andPyIn(List<Integer> values) {
      addCriterion("pY in", values, "py");
      return (Criteria) this;
    }

    public Criteria andPyNotIn(List<Integer> values) {
      addCriterion("pY not in", values, "py");
      return (Criteria) this;
    }

    public Criteria andPyBetween(Integer value1, Integer value2) {
      addCriterion("pY between", value1, value2, "py");
      return (Criteria) this;
    }

    public Criteria andPyNotBetween(Integer value1, Integer value2) {
      addCriterion("pY not between", value1, value2, "py");
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
