package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class TexapokerRobotRulesExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public TexapokerRobotRulesExample() {
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

    public Criteria andCardsnumIsNull() {
      addCriterion("cardsNum is null");
      return (Criteria) this;
    }

    public Criteria andCardsnumIsNotNull() {
      addCriterion("cardsNum is not null");
      return (Criteria) this;
    }

    public Criteria andCardsnumEqualTo(Integer value) {
      addCriterion("cardsNum =", value, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumNotEqualTo(Integer value) {
      addCriterion("cardsNum <>", value, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumGreaterThan(Integer value) {
      addCriterion("cardsNum >", value, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumGreaterThanOrEqualTo(Integer value) {
      addCriterion("cardsNum >=", value, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumLessThan(Integer value) {
      addCriterion("cardsNum <", value, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumLessThanOrEqualTo(Integer value) {
      addCriterion("cardsNum <=", value, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumIn(List<Integer> values) {
      addCriterion("cardsNum in", values, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumNotIn(List<Integer> values) {
      addCriterion("cardsNum not in", values, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumBetween(Integer value1, Integer value2) {
      addCriterion("cardsNum between", value1, value2, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andCardsnumNotBetween(Integer value1, Integer value2) {
      addCriterion("cardsNum not between", value1, value2, "cardsnum");
      return (Criteria) this;
    }

    public Criteria andPairedIsNull() {
      addCriterion("paired is null");
      return (Criteria) this;
    }

    public Criteria andPairedIsNotNull() {
      addCriterion("paired is not null");
      return (Criteria) this;
    }

    public Criteria andPairedEqualTo(Integer value) {
      addCriterion("paired =", value, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedNotEqualTo(Integer value) {
      addCriterion("paired <>", value, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedGreaterThan(Integer value) {
      addCriterion("paired >", value, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedGreaterThanOrEqualTo(Integer value) {
      addCriterion("paired >=", value, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedLessThan(Integer value) {
      addCriterion("paired <", value, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedLessThanOrEqualTo(Integer value) {
      addCriterion("paired <=", value, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedIn(List<Integer> values) {
      addCriterion("paired in", values, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedNotIn(List<Integer> values) {
      addCriterion("paired not in", values, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedBetween(Integer value1, Integer value2) {
      addCriterion("paired between", value1, value2, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedNotBetween(Integer value1, Integer value2) {
      addCriterion("paired not between", value1, value2, "paired");
      return (Criteria) this;
    }

    public Criteria andPairedmaxIsNull() {
      addCriterion("pairedMax is null");
      return (Criteria) this;
    }

    public Criteria andPairedmaxIsNotNull() {
      addCriterion("pairedMax is not null");
      return (Criteria) this;
    }

    public Criteria andPairedmaxEqualTo(Integer value) {
      addCriterion("pairedMax =", value, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxNotEqualTo(Integer value) {
      addCriterion("pairedMax <>", value, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxGreaterThan(Integer value) {
      addCriterion("pairedMax >", value, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxGreaterThanOrEqualTo(Integer value) {
      addCriterion("pairedMax >=", value, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxLessThan(Integer value) {
      addCriterion("pairedMax <", value, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxLessThanOrEqualTo(Integer value) {
      addCriterion("pairedMax <=", value, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxIn(List<Integer> values) {
      addCriterion("pairedMax in", values, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxNotIn(List<Integer> values) {
      addCriterion("pairedMax not in", values, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxBetween(Integer value1, Integer value2) {
      addCriterion("pairedMax between", value1, value2, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedmaxNotBetween(Integer value1, Integer value2) {
      addCriterion("pairedMax not between", value1, value2, "pairedmax");
      return (Criteria) this;
    }

    public Criteria andPairedminIsNull() {
      addCriterion("pairedMin is null");
      return (Criteria) this;
    }

    public Criteria andPairedminIsNotNull() {
      addCriterion("pairedMin is not null");
      return (Criteria) this;
    }

    public Criteria andPairedminEqualTo(Integer value) {
      addCriterion("pairedMin =", value, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminNotEqualTo(Integer value) {
      addCriterion("pairedMin <>", value, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminGreaterThan(Integer value) {
      addCriterion("pairedMin >", value, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminGreaterThanOrEqualTo(Integer value) {
      addCriterion("pairedMin >=", value, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminLessThan(Integer value) {
      addCriterion("pairedMin <", value, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminLessThanOrEqualTo(Integer value) {
      addCriterion("pairedMin <=", value, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminIn(List<Integer> values) {
      addCriterion("pairedMin in", values, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminNotIn(List<Integer> values) {
      addCriterion("pairedMin not in", values, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminBetween(Integer value1, Integer value2) {
      addCriterion("pairedMin between", value1, value2, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedminNotBetween(Integer value1, Integer value2) {
      addCriterion("pairedMin not between", value1, value2, "pairedmin");
      return (Criteria) this;
    }

    public Criteria andPairedmyIsNull() {
      addCriterion("pairedMy is null");
      return (Criteria) this;
    }

    public Criteria andPairedmyIsNotNull() {
      addCriterion("pairedMy is not null");
      return (Criteria) this;
    }

    public Criteria andPairedmyEqualTo(Integer value) {
      addCriterion("pairedMy =", value, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyNotEqualTo(Integer value) {
      addCriterion("pairedMy <>", value, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyGreaterThan(Integer value) {
      addCriterion("pairedMy >", value, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyGreaterThanOrEqualTo(Integer value) {
      addCriterion("pairedMy >=", value, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyLessThan(Integer value) {
      addCriterion("pairedMy <", value, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyLessThanOrEqualTo(Integer value) {
      addCriterion("pairedMy <=", value, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyIn(List<Integer> values) {
      addCriterion("pairedMy in", values, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyNotIn(List<Integer> values) {
      addCriterion("pairedMy not in", values, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyBetween(Integer value1, Integer value2) {
      addCriterion("pairedMy between", value1, value2, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andPairedmyNotBetween(Integer value1, Integer value2) {
      addCriterion("pairedMy not between", value1, value2, "pairedmy");
      return (Criteria) this;
    }

    public Criteria andTwotwoIsNull() {
      addCriterion("twoTwo is null");
      return (Criteria) this;
    }

    public Criteria andTwotwoIsNotNull() {
      addCriterion("twoTwo is not null");
      return (Criteria) this;
    }

    public Criteria andTwotwoEqualTo(Integer value) {
      addCriterion("twoTwo =", value, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoNotEqualTo(Integer value) {
      addCriterion("twoTwo <>", value, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoGreaterThan(Integer value) {
      addCriterion("twoTwo >", value, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoGreaterThanOrEqualTo(Integer value) {
      addCriterion("twoTwo >=", value, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoLessThan(Integer value) {
      addCriterion("twoTwo <", value, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoLessThanOrEqualTo(Integer value) {
      addCriterion("twoTwo <=", value, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoIn(List<Integer> values) {
      addCriterion("twoTwo in", values, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoNotIn(List<Integer> values) {
      addCriterion("twoTwo not in", values, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoBetween(Integer value1, Integer value2) {
      addCriterion("twoTwo between", value1, value2, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwoNotBetween(Integer value1, Integer value2) {
      addCriterion("twoTwo not between", value1, value2, "twotwo");
      return (Criteria) this;
    }

    public Criteria andTwotwomyIsNull() {
      addCriterion("twoTwoMy is null");
      return (Criteria) this;
    }

    public Criteria andTwotwomyIsNotNull() {
      addCriterion("twoTwoMy is not null");
      return (Criteria) this;
    }

    public Criteria andTwotwomyEqualTo(Integer value) {
      addCriterion("twoTwoMy =", value, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyNotEqualTo(Integer value) {
      addCriterion("twoTwoMy <>", value, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyGreaterThan(Integer value) {
      addCriterion("twoTwoMy >", value, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyGreaterThanOrEqualTo(Integer value) {
      addCriterion("twoTwoMy >=", value, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyLessThan(Integer value) {
      addCriterion("twoTwoMy <", value, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyLessThanOrEqualTo(Integer value) {
      addCriterion("twoTwoMy <=", value, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyIn(List<Integer> values) {
      addCriterion("twoTwoMy in", values, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyNotIn(List<Integer> values) {
      addCriterion("twoTwoMy not in", values, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyBetween(Integer value1, Integer value2) {
      addCriterion("twoTwoMy between", value1, value2, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andTwotwomyNotBetween(Integer value1, Integer value2) {
      addCriterion("twoTwoMy not between", value1, value2, "twotwomy");
      return (Criteria) this;
    }

    public Criteria andThreeIsNull() {
      addCriterion("three is null");
      return (Criteria) this;
    }

    public Criteria andThreeIsNotNull() {
      addCriterion("three is not null");
      return (Criteria) this;
    }

    public Criteria andThreeEqualTo(Integer value) {
      addCriterion("three =", value, "three");
      return (Criteria) this;
    }

    public Criteria andThreeNotEqualTo(Integer value) {
      addCriterion("three <>", value, "three");
      return (Criteria) this;
    }

    public Criteria andThreeGreaterThan(Integer value) {
      addCriterion("three >", value, "three");
      return (Criteria) this;
    }

    public Criteria andThreeGreaterThanOrEqualTo(Integer value) {
      addCriterion("three >=", value, "three");
      return (Criteria) this;
    }

    public Criteria andThreeLessThan(Integer value) {
      addCriterion("three <", value, "three");
      return (Criteria) this;
    }

    public Criteria andThreeLessThanOrEqualTo(Integer value) {
      addCriterion("three <=", value, "three");
      return (Criteria) this;
    }

    public Criteria andThreeIn(List<Integer> values) {
      addCriterion("three in", values, "three");
      return (Criteria) this;
    }

    public Criteria andThreeNotIn(List<Integer> values) {
      addCriterion("three not in", values, "three");
      return (Criteria) this;
    }

    public Criteria andThreeBetween(Integer value1, Integer value2) {
      addCriterion("three between", value1, value2, "three");
      return (Criteria) this;
    }

    public Criteria andThreeNotBetween(Integer value1, Integer value2) {
      addCriterion("three not between", value1, value2, "three");
      return (Criteria) this;
    }

    public Criteria andThreemaxIsNull() {
      addCriterion("threeMax is null");
      return (Criteria) this;
    }

    public Criteria andThreemaxIsNotNull() {
      addCriterion("threeMax is not null");
      return (Criteria) this;
    }

    public Criteria andThreemaxEqualTo(Integer value) {
      addCriterion("threeMax =", value, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxNotEqualTo(Integer value) {
      addCriterion("threeMax <>", value, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxGreaterThan(Integer value) {
      addCriterion("threeMax >", value, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxGreaterThanOrEqualTo(Integer value) {
      addCriterion("threeMax >=", value, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxLessThan(Integer value) {
      addCriterion("threeMax <", value, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxLessThanOrEqualTo(Integer value) {
      addCriterion("threeMax <=", value, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxIn(List<Integer> values) {
      addCriterion("threeMax in", values, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxNotIn(List<Integer> values) {
      addCriterion("threeMax not in", values, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxBetween(Integer value1, Integer value2) {
      addCriterion("threeMax between", value1, value2, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemaxNotBetween(Integer value1, Integer value2) {
      addCriterion("threeMax not between", value1, value2, "threemax");
      return (Criteria) this;
    }

    public Criteria andThreemyIsNull() {
      addCriterion("threeMy is null");
      return (Criteria) this;
    }

    public Criteria andThreemyIsNotNull() {
      addCriterion("threeMy is not null");
      return (Criteria) this;
    }

    public Criteria andThreemyEqualTo(Integer value) {
      addCriterion("threeMy =", value, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyNotEqualTo(Integer value) {
      addCriterion("threeMy <>", value, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyGreaterThan(Integer value) {
      addCriterion("threeMy >", value, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyGreaterThanOrEqualTo(Integer value) {
      addCriterion("threeMy >=", value, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyLessThan(Integer value) {
      addCriterion("threeMy <", value, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyLessThanOrEqualTo(Integer value) {
      addCriterion("threeMy <=", value, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyIn(List<Integer> values) {
      addCriterion("threeMy in", values, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyNotIn(List<Integer> values) {
      addCriterion("threeMy not in", values, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyBetween(Integer value1, Integer value2) {
      addCriterion("threeMy between", value1, value2, "threemy");
      return (Criteria) this;
    }

    public Criteria andThreemyNotBetween(Integer value1, Integer value2) {
      addCriterion("threeMy not between", value1, value2, "threemy");
      return (Criteria) this;
    }

    public Criteria andFourIsNull() {
      addCriterion("four is null");
      return (Criteria) this;
    }

    public Criteria andFourIsNotNull() {
      addCriterion("four is not null");
      return (Criteria) this;
    }

    public Criteria andFourEqualTo(Integer value) {
      addCriterion("four =", value, "four");
      return (Criteria) this;
    }

    public Criteria andFourNotEqualTo(Integer value) {
      addCriterion("four <>", value, "four");
      return (Criteria) this;
    }

    public Criteria andFourGreaterThan(Integer value) {
      addCriterion("four >", value, "four");
      return (Criteria) this;
    }

    public Criteria andFourGreaterThanOrEqualTo(Integer value) {
      addCriterion("four >=", value, "four");
      return (Criteria) this;
    }

    public Criteria andFourLessThan(Integer value) {
      addCriterion("four <", value, "four");
      return (Criteria) this;
    }

    public Criteria andFourLessThanOrEqualTo(Integer value) {
      addCriterion("four <=", value, "four");
      return (Criteria) this;
    }

    public Criteria andFourIn(List<Integer> values) {
      addCriterion("four in", values, "four");
      return (Criteria) this;
    }

    public Criteria andFourNotIn(List<Integer> values) {
      addCriterion("four not in", values, "four");
      return (Criteria) this;
    }

    public Criteria andFourBetween(Integer value1, Integer value2) {
      addCriterion("four between", value1, value2, "four");
      return (Criteria) this;
    }

    public Criteria andFourNotBetween(Integer value1, Integer value2) {
      addCriterion("four not between", value1, value2, "four");
      return (Criteria) this;
    }

    public Criteria andFourmyIsNull() {
      addCriterion("fourMy is null");
      return (Criteria) this;
    }

    public Criteria andFourmyIsNotNull() {
      addCriterion("fourMy is not null");
      return (Criteria) this;
    }

    public Criteria andFourmyEqualTo(Integer value) {
      addCriterion("fourMy =", value, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyNotEqualTo(Integer value) {
      addCriterion("fourMy <>", value, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyGreaterThan(Integer value) {
      addCriterion("fourMy >", value, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyGreaterThanOrEqualTo(Integer value) {
      addCriterion("fourMy >=", value, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyLessThan(Integer value) {
      addCriterion("fourMy <", value, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyLessThanOrEqualTo(Integer value) {
      addCriterion("fourMy <=", value, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyIn(List<Integer> values) {
      addCriterion("fourMy in", values, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyNotIn(List<Integer> values) {
      addCriterion("fourMy not in", values, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyBetween(Integer value1, Integer value2) {
      addCriterion("fourMy between", value1, value2, "fourmy");
      return (Criteria) this;
    }

    public Criteria andFourmyNotBetween(Integer value1, Integer value2) {
      addCriterion("fourMy not between", value1, value2, "fourmy");
      return (Criteria) this;
    }

    public Criteria andLongthreeIsNull() {
      addCriterion("longThree is null");
      return (Criteria) this;
    }

    public Criteria andLongthreeIsNotNull() {
      addCriterion("longThree is not null");
      return (Criteria) this;
    }

    public Criteria andLongthreeEqualTo(Integer value) {
      addCriterion("longThree =", value, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeNotEqualTo(Integer value) {
      addCriterion("longThree <>", value, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeGreaterThan(Integer value) {
      addCriterion("longThree >", value, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeGreaterThanOrEqualTo(Integer value) {
      addCriterion("longThree >=", value, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeLessThan(Integer value) {
      addCriterion("longThree <", value, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeLessThanOrEqualTo(Integer value) {
      addCriterion("longThree <=", value, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeIn(List<Integer> values) {
      addCriterion("longThree in", values, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeNotIn(List<Integer> values) {
      addCriterion("longThree not in", values, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeBetween(Integer value1, Integer value2) {
      addCriterion("longThree between", value1, value2, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreeNotBetween(Integer value1, Integer value2) {
      addCriterion("longThree not between", value1, value2, "longthree");
      return (Criteria) this;
    }

    public Criteria andLongthreemyIsNull() {
      addCriterion("longThreeMy is null");
      return (Criteria) this;
    }

    public Criteria andLongthreemyIsNotNull() {
      addCriterion("longThreeMy is not null");
      return (Criteria) this;
    }

    public Criteria andLongthreemyEqualTo(Integer value) {
      addCriterion("longThreeMy =", value, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyNotEqualTo(Integer value) {
      addCriterion("longThreeMy <>", value, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyGreaterThan(Integer value) {
      addCriterion("longThreeMy >", value, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyGreaterThanOrEqualTo(Integer value) {
      addCriterion("longThreeMy >=", value, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyLessThan(Integer value) {
      addCriterion("longThreeMy <", value, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyLessThanOrEqualTo(Integer value) {
      addCriterion("longThreeMy <=", value, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyIn(List<Integer> values) {
      addCriterion("longThreeMy in", values, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyNotIn(List<Integer> values) {
      addCriterion("longThreeMy not in", values, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyBetween(Integer value1, Integer value2) {
      addCriterion("longThreeMy between", value1, value2, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongthreemyNotBetween(Integer value1, Integer value2) {
      addCriterion("longThreeMy not between", value1, value2, "longthreemy");
      return (Criteria) this;
    }

    public Criteria andLongfourIsNull() {
      addCriterion("longFour is null");
      return (Criteria) this;
    }

    public Criteria andLongfourIsNotNull() {
      addCriterion("longFour is not null");
      return (Criteria) this;
    }

    public Criteria andLongfourEqualTo(Integer value) {
      addCriterion("longFour =", value, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourNotEqualTo(Integer value) {
      addCriterion("longFour <>", value, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourGreaterThan(Integer value) {
      addCriterion("longFour >", value, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourGreaterThanOrEqualTo(Integer value) {
      addCriterion("longFour >=", value, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourLessThan(Integer value) {
      addCriterion("longFour <", value, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourLessThanOrEqualTo(Integer value) {
      addCriterion("longFour <=", value, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourIn(List<Integer> values) {
      addCriterion("longFour in", values, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourNotIn(List<Integer> values) {
      addCriterion("longFour not in", values, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourBetween(Integer value1, Integer value2) {
      addCriterion("longFour between", value1, value2, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourNotBetween(Integer value1, Integer value2) {
      addCriterion("longFour not between", value1, value2, "longfour");
      return (Criteria) this;
    }

    public Criteria andLongfourmyIsNull() {
      addCriterion("longFourMy is null");
      return (Criteria) this;
    }

    public Criteria andLongfourmyIsNotNull() {
      addCriterion("longFourMy is not null");
      return (Criteria) this;
    }

    public Criteria andLongfourmyEqualTo(Integer value) {
      addCriterion("longFourMy =", value, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyNotEqualTo(Integer value) {
      addCriterion("longFourMy <>", value, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyGreaterThan(Integer value) {
      addCriterion("longFourMy >", value, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyGreaterThanOrEqualTo(Integer value) {
      addCriterion("longFourMy >=", value, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyLessThan(Integer value) {
      addCriterion("longFourMy <", value, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyLessThanOrEqualTo(Integer value) {
      addCriterion("longFourMy <=", value, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyIn(List<Integer> values) {
      addCriterion("longFourMy in", values, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyNotIn(List<Integer> values) {
      addCriterion("longFourMy not in", values, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyBetween(Integer value1, Integer value2) {
      addCriterion("longFourMy between", value1, value2, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfourmyNotBetween(Integer value1, Integer value2) {
      addCriterion("longFourMy not between", value1, value2, "longfourmy");
      return (Criteria) this;
    }

    public Criteria andLongfiveIsNull() {
      addCriterion("longFive is null");
      return (Criteria) this;
    }

    public Criteria andLongfiveIsNotNull() {
      addCriterion("longFive is not null");
      return (Criteria) this;
    }

    public Criteria andLongfiveEqualTo(Integer value) {
      addCriterion("longFive =", value, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveNotEqualTo(Integer value) {
      addCriterion("longFive <>", value, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveGreaterThan(Integer value) {
      addCriterion("longFive >", value, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveGreaterThanOrEqualTo(Integer value) {
      addCriterion("longFive >=", value, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveLessThan(Integer value) {
      addCriterion("longFive <", value, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveLessThanOrEqualTo(Integer value) {
      addCriterion("longFive <=", value, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveIn(List<Integer> values) {
      addCriterion("longFive in", values, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveNotIn(List<Integer> values) {
      addCriterion("longFive not in", values, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveBetween(Integer value1, Integer value2) {
      addCriterion("longFive between", value1, value2, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfiveNotBetween(Integer value1, Integer value2) {
      addCriterion("longFive not between", value1, value2, "longfive");
      return (Criteria) this;
    }

    public Criteria andLongfivemyIsNull() {
      addCriterion("longFiveMy is null");
      return (Criteria) this;
    }

    public Criteria andLongfivemyIsNotNull() {
      addCriterion("longFiveMy is not null");
      return (Criteria) this;
    }

    public Criteria andLongfivemyEqualTo(Integer value) {
      addCriterion("longFiveMy =", value, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyNotEqualTo(Integer value) {
      addCriterion("longFiveMy <>", value, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyGreaterThan(Integer value) {
      addCriterion("longFiveMy >", value, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyGreaterThanOrEqualTo(Integer value) {
      addCriterion("longFiveMy >=", value, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyLessThan(Integer value) {
      addCriterion("longFiveMy <", value, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyLessThanOrEqualTo(Integer value) {
      addCriterion("longFiveMy <=", value, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyIn(List<Integer> values) {
      addCriterion("longFiveMy in", values, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyNotIn(List<Integer> values) {
      addCriterion("longFiveMy not in", values, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyBetween(Integer value1, Integer value2) {
      addCriterion("longFiveMy between", value1, value2, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andLongfivemyNotBetween(Integer value1, Integer value2) {
      addCriterion("longFiveMy not between", value1, value2, "longfivemy");
      return (Criteria) this;
    }

    public Criteria andSamefourIsNull() {
      addCriterion("sameFour is null");
      return (Criteria) this;
    }

    public Criteria andSamefourIsNotNull() {
      addCriterion("sameFour is not null");
      return (Criteria) this;
    }

    public Criteria andSamefourEqualTo(Integer value) {
      addCriterion("sameFour =", value, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourNotEqualTo(Integer value) {
      addCriterion("sameFour <>", value, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourGreaterThan(Integer value) {
      addCriterion("sameFour >", value, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourGreaterThanOrEqualTo(Integer value) {
      addCriterion("sameFour >=", value, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourLessThan(Integer value) {
      addCriterion("sameFour <", value, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourLessThanOrEqualTo(Integer value) {
      addCriterion("sameFour <=", value, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourIn(List<Integer> values) {
      addCriterion("sameFour in", values, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourNotIn(List<Integer> values) {
      addCriterion("sameFour not in", values, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourBetween(Integer value1, Integer value2) {
      addCriterion("sameFour between", value1, value2, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourNotBetween(Integer value1, Integer value2) {
      addCriterion("sameFour not between", value1, value2, "samefour");
      return (Criteria) this;
    }

    public Criteria andSamefourmyIsNull() {
      addCriterion("sameFourMy is null");
      return (Criteria) this;
    }

    public Criteria andSamefourmyIsNotNull() {
      addCriterion("sameFourMy is not null");
      return (Criteria) this;
    }

    public Criteria andSamefourmyEqualTo(Integer value) {
      addCriterion("sameFourMy =", value, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyNotEqualTo(Integer value) {
      addCriterion("sameFourMy <>", value, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyGreaterThan(Integer value) {
      addCriterion("sameFourMy >", value, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyGreaterThanOrEqualTo(Integer value) {
      addCriterion("sameFourMy >=", value, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyLessThan(Integer value) {
      addCriterion("sameFourMy <", value, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyLessThanOrEqualTo(Integer value) {
      addCriterion("sameFourMy <=", value, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyIn(List<Integer> values) {
      addCriterion("sameFourMy in", values, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyNotIn(List<Integer> values) {
      addCriterion("sameFourMy not in", values, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyBetween(Integer value1, Integer value2) {
      addCriterion("sameFourMy between", value1, value2, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefourmyNotBetween(Integer value1, Integer value2) {
      addCriterion("sameFourMy not between", value1, value2, "samefourmy");
      return (Criteria) this;
    }

    public Criteria andSamefiveIsNull() {
      addCriterion("sameFive is null");
      return (Criteria) this;
    }

    public Criteria andSamefiveIsNotNull() {
      addCriterion("sameFive is not null");
      return (Criteria) this;
    }

    public Criteria andSamefiveEqualTo(Integer value) {
      addCriterion("sameFive =", value, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveNotEqualTo(Integer value) {
      addCriterion("sameFive <>", value, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveGreaterThan(Integer value) {
      addCriterion("sameFive >", value, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveGreaterThanOrEqualTo(Integer value) {
      addCriterion("sameFive >=", value, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveLessThan(Integer value) {
      addCriterion("sameFive <", value, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveLessThanOrEqualTo(Integer value) {
      addCriterion("sameFive <=", value, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveIn(List<Integer> values) {
      addCriterion("sameFive in", values, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveNotIn(List<Integer> values) {
      addCriterion("sameFive not in", values, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveBetween(Integer value1, Integer value2) {
      addCriterion("sameFive between", value1, value2, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefiveNotBetween(Integer value1, Integer value2) {
      addCriterion("sameFive not between", value1, value2, "samefive");
      return (Criteria) this;
    }

    public Criteria andSamefivemyIsNull() {
      addCriterion("sameFiveMy is null");
      return (Criteria) this;
    }

    public Criteria andSamefivemyIsNotNull() {
      addCriterion("sameFiveMy is not null");
      return (Criteria) this;
    }

    public Criteria andSamefivemyEqualTo(Integer value) {
      addCriterion("sameFiveMy =", value, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyNotEqualTo(Integer value) {
      addCriterion("sameFiveMy <>", value, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyGreaterThan(Integer value) {
      addCriterion("sameFiveMy >", value, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyGreaterThanOrEqualTo(Integer value) {
      addCriterion("sameFiveMy >=", value, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyLessThan(Integer value) {
      addCriterion("sameFiveMy <", value, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyLessThanOrEqualTo(Integer value) {
      addCriterion("sameFiveMy <=", value, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyIn(List<Integer> values) {
      addCriterion("sameFiveMy in", values, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyNotIn(List<Integer> values) {
      addCriterion("sameFiveMy not in", values, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyBetween(Integer value1, Integer value2) {
      addCriterion("sameFiveMy between", value1, value2, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamefivemyNotBetween(Integer value1, Integer value2) {
      addCriterion("sameFiveMy not between", value1, value2, "samefivemy");
      return (Criteria) this;
    }

    public Criteria andSamelongIsNull() {
      addCriterion("sameLong is null");
      return (Criteria) this;
    }

    public Criteria andSamelongIsNotNull() {
      addCriterion("sameLong is not null");
      return (Criteria) this;
    }

    public Criteria andSamelongEqualTo(Integer value) {
      addCriterion("sameLong =", value, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongNotEqualTo(Integer value) {
      addCriterion("sameLong <>", value, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongGreaterThan(Integer value) {
      addCriterion("sameLong >", value, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongGreaterThanOrEqualTo(Integer value) {
      addCriterion("sameLong >=", value, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongLessThan(Integer value) {
      addCriterion("sameLong <", value, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongLessThanOrEqualTo(Integer value) {
      addCriterion("sameLong <=", value, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongIn(List<Integer> values) {
      addCriterion("sameLong in", values, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongNotIn(List<Integer> values) {
      addCriterion("sameLong not in", values, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongBetween(Integer value1, Integer value2) {
      addCriterion("sameLong between", value1, value2, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongNotBetween(Integer value1, Integer value2) {
      addCriterion("sameLong not between", value1, value2, "samelong");
      return (Criteria) this;
    }

    public Criteria andSamelongmyIsNull() {
      addCriterion("sameLongMy is null");
      return (Criteria) this;
    }

    public Criteria andSamelongmyIsNotNull() {
      addCriterion("sameLongMy is not null");
      return (Criteria) this;
    }

    public Criteria andSamelongmyEqualTo(Integer value) {
      addCriterion("sameLongMy =", value, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyNotEqualTo(Integer value) {
      addCriterion("sameLongMy <>", value, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyGreaterThan(Integer value) {
      addCriterion("sameLongMy >", value, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyGreaterThanOrEqualTo(Integer value) {
      addCriterion("sameLongMy >=", value, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyLessThan(Integer value) {
      addCriterion("sameLongMy <", value, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyLessThanOrEqualTo(Integer value) {
      addCriterion("sameLongMy <=", value, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyIn(List<Integer> values) {
      addCriterion("sameLongMy in", values, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyNotIn(List<Integer> values) {
      addCriterion("sameLongMy not in", values, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyBetween(Integer value1, Integer value2) {
      addCriterion("sameLongMy between", value1, value2, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andSamelongmyNotBetween(Integer value1, Integer value2) {
      addCriterion("sameLongMy not between", value1, value2, "samelongmy");
      return (Criteria) this;
    }

    public Criteria andShowhandmustIsNull() {
      addCriterion("showhandMust is null");
      return (Criteria) this;
    }

    public Criteria andShowhandmustIsNotNull() {
      addCriterion("showhandMust is not null");
      return (Criteria) this;
    }

    public Criteria andShowhandmustEqualTo(Integer value) {
      addCriterion("showhandMust =", value, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustNotEqualTo(Integer value) {
      addCriterion("showhandMust <>", value, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustGreaterThan(Integer value) {
      addCriterion("showhandMust >", value, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustGreaterThanOrEqualTo(Integer value) {
      addCriterion("showhandMust >=", value, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustLessThan(Integer value) {
      addCriterion("showhandMust <", value, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustLessThanOrEqualTo(Integer value) {
      addCriterion("showhandMust <=", value, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustIn(List<Integer> values) {
      addCriterion("showhandMust in", values, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustNotIn(List<Integer> values) {
      addCriterion("showhandMust not in", values, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustBetween(Integer value1, Integer value2) {
      addCriterion("showhandMust between", value1, value2, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andShowhandmustNotBetween(Integer value1, Integer value2) {
      addCriterion("showhandMust not between", value1, value2, "showhandmust");
      return (Criteria) this;
    }

    public Criteria andWinIsNull() {
      addCriterion("win is null");
      return (Criteria) this;
    }

    public Criteria andWinIsNotNull() {
      addCriterion("win is not null");
      return (Criteria) this;
    }

    public Criteria andWinEqualTo(Integer value) {
      addCriterion("win =", value, "win");
      return (Criteria) this;
    }

    public Criteria andWinNotEqualTo(Integer value) {
      addCriterion("win <>", value, "win");
      return (Criteria) this;
    }

    public Criteria andWinGreaterThan(Integer value) {
      addCriterion("win >", value, "win");
      return (Criteria) this;
    }

    public Criteria andWinGreaterThanOrEqualTo(Integer value) {
      addCriterion("win >=", value, "win");
      return (Criteria) this;
    }

    public Criteria andWinLessThan(Integer value) {
      addCriterion("win <", value, "win");
      return (Criteria) this;
    }

    public Criteria andWinLessThanOrEqualTo(Integer value) {
      addCriterion("win <=", value, "win");
      return (Criteria) this;
    }

    public Criteria andWinIn(List<Integer> values) {
      addCriterion("win in", values, "win");
      return (Criteria) this;
    }

    public Criteria andWinNotIn(List<Integer> values) {
      addCriterion("win not in", values, "win");
      return (Criteria) this;
    }

    public Criteria andWinBetween(Integer value1, Integer value2) {
      addCriterion("win between", value1, value2, "win");
      return (Criteria) this;
    }

    public Criteria andWinNotBetween(Integer value1, Integer value2) {
      addCriterion("win not between", value1, value2, "win");
      return (Criteria) this;
    }

    public Criteria andFollowIsNull() {
      addCriterion("follow is null");
      return (Criteria) this;
    }

    public Criteria andFollowIsNotNull() {
      addCriterion("follow is not null");
      return (Criteria) this;
    }

    public Criteria andFollowEqualTo(Integer value) {
      addCriterion("follow =", value, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowNotEqualTo(Integer value) {
      addCriterion("follow <>", value, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowGreaterThan(Integer value) {
      addCriterion("follow >", value, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowGreaterThanOrEqualTo(Integer value) {
      addCriterion("follow >=", value, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowLessThan(Integer value) {
      addCriterion("follow <", value, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowLessThanOrEqualTo(Integer value) {
      addCriterion("follow <=", value, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowIn(List<Integer> values) {
      addCriterion("follow in", values, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowNotIn(List<Integer> values) {
      addCriterion("follow not in", values, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowBetween(Integer value1, Integer value2) {
      addCriterion("follow between", value1, value2, "follow");
      return (Criteria) this;
    }

    public Criteria andFollowNotBetween(Integer value1, Integer value2) {
      addCriterion("follow not between", value1, value2, "follow");
      return (Criteria) this;
    }

    public Criteria andFillingIsNull() {
      addCriterion("filling is null");
      return (Criteria) this;
    }

    public Criteria andFillingIsNotNull() {
      addCriterion("filling is not null");
      return (Criteria) this;
    }

    public Criteria andFillingEqualTo(Integer value) {
      addCriterion("filling =", value, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingNotEqualTo(Integer value) {
      addCriterion("filling <>", value, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingGreaterThan(Integer value) {
      addCriterion("filling >", value, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingGreaterThanOrEqualTo(Integer value) {
      addCriterion("filling >=", value, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingLessThan(Integer value) {
      addCriterion("filling <", value, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingLessThanOrEqualTo(Integer value) {
      addCriterion("filling <=", value, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingIn(List<Integer> values) {
      addCriterion("filling in", values, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingNotIn(List<Integer> values) {
      addCriterion("filling not in", values, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingBetween(Integer value1, Integer value2) {
      addCriterion("filling between", value1, value2, "filling");
      return (Criteria) this;
    }

    public Criteria andFillingNotBetween(Integer value1, Integer value2) {
      addCriterion("filling not between", value1, value2, "filling");
      return (Criteria) this;
    }

    public Criteria andShowhandIsNull() {
      addCriterion("showhand is null");
      return (Criteria) this;
    }

    public Criteria andShowhandIsNotNull() {
      addCriterion("showhand is not null");
      return (Criteria) this;
    }

    public Criteria andShowhandEqualTo(Integer value) {
      addCriterion("showhand =", value, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandNotEqualTo(Integer value) {
      addCriterion("showhand <>", value, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandGreaterThan(Integer value) {
      addCriterion("showhand >", value, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandGreaterThanOrEqualTo(Integer value) {
      addCriterion("showhand >=", value, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandLessThan(Integer value) {
      addCriterion("showhand <", value, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandLessThanOrEqualTo(Integer value) {
      addCriterion("showhand <=", value, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandIn(List<Integer> values) {
      addCriterion("showhand in", values, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandNotIn(List<Integer> values) {
      addCriterion("showhand not in", values, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandBetween(Integer value1, Integer value2) {
      addCriterion("showhand between", value1, value2, "showhand");
      return (Criteria) this;
    }

    public Criteria andShowhandNotBetween(Integer value1, Integer value2) {
      addCriterion("showhand not between", value1, value2, "showhand");
      return (Criteria) this;
    }

    public Criteria andGiveupIsNull() {
      addCriterion("giveUp is null");
      return (Criteria) this;
    }

    public Criteria andGiveupIsNotNull() {
      addCriterion("giveUp is not null");
      return (Criteria) this;
    }

    public Criteria andGiveupEqualTo(Integer value) {
      addCriterion("giveUp =", value, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupNotEqualTo(Integer value) {
      addCriterion("giveUp <>", value, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupGreaterThan(Integer value) {
      addCriterion("giveUp >", value, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupGreaterThanOrEqualTo(Integer value) {
      addCriterion("giveUp >=", value, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupLessThan(Integer value) {
      addCriterion("giveUp <", value, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupLessThanOrEqualTo(Integer value) {
      addCriterion("giveUp <=", value, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupIn(List<Integer> values) {
      addCriterion("giveUp in", values, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupNotIn(List<Integer> values) {
      addCriterion("giveUp not in", values, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupBetween(Integer value1, Integer value2) {
      addCriterion("giveUp between", value1, value2, "giveup");
      return (Criteria) this;
    }

    public Criteria andGiveupNotBetween(Integer value1, Integer value2) {
      addCriterion("giveUp not between", value1, value2, "giveup");
      return (Criteria) this;
    }

    public Criteria andTimeminIsNull() {
      addCriterion("timeMin is null");
      return (Criteria) this;
    }

    public Criteria andTimeminIsNotNull() {
      addCriterion("timeMin is not null");
      return (Criteria) this;
    }

    public Criteria andTimeminEqualTo(Integer value) {
      addCriterion("timeMin =", value, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminNotEqualTo(Integer value) {
      addCriterion("timeMin <>", value, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminGreaterThan(Integer value) {
      addCriterion("timeMin >", value, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminGreaterThanOrEqualTo(Integer value) {
      addCriterion("timeMin >=", value, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminLessThan(Integer value) {
      addCriterion("timeMin <", value, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminLessThanOrEqualTo(Integer value) {
      addCriterion("timeMin <=", value, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminIn(List<Integer> values) {
      addCriterion("timeMin in", values, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminNotIn(List<Integer> values) {
      addCriterion("timeMin not in", values, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminBetween(Integer value1, Integer value2) {
      addCriterion("timeMin between", value1, value2, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimeminNotBetween(Integer value1, Integer value2) {
      addCriterion("timeMin not between", value1, value2, "timemin");
      return (Criteria) this;
    }

    public Criteria andTimemaxIsNull() {
      addCriterion("timeMax is null");
      return (Criteria) this;
    }

    public Criteria andTimemaxIsNotNull() {
      addCriterion("timeMax is not null");
      return (Criteria) this;
    }

    public Criteria andTimemaxEqualTo(Integer value) {
      addCriterion("timeMax =", value, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxNotEqualTo(Integer value) {
      addCriterion("timeMax <>", value, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxGreaterThan(Integer value) {
      addCriterion("timeMax >", value, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxGreaterThanOrEqualTo(Integer value) {
      addCriterion("timeMax >=", value, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxLessThan(Integer value) {
      addCriterion("timeMax <", value, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxLessThanOrEqualTo(Integer value) {
      addCriterion("timeMax <=", value, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxIn(List<Integer> values) {
      addCriterion("timeMax in", values, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxNotIn(List<Integer> values) {
      addCriterion("timeMax not in", values, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxBetween(Integer value1, Integer value2) {
      addCriterion("timeMax between", value1, value2, "timemax");
      return (Criteria) this;
    }

    public Criteria andTimemaxNotBetween(Integer value1, Integer value2) {
      addCriterion("timeMax not between", value1, value2, "timemax");
      return (Criteria) this;
    }

    public Criteria andRemarkIsNull() {
      addCriterion("remark is null");
      return (Criteria) this;
    }

    public Criteria andRemarkIsNotNull() {
      addCriterion("remark is not null");
      return (Criteria) this;
    }

    public Criteria andRemarkEqualTo(String value) {
      addCriterion("remark =", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotEqualTo(String value) {
      addCriterion("remark <>", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkGreaterThan(String value) {
      addCriterion("remark >", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkGreaterThanOrEqualTo(String value) {
      addCriterion("remark >=", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLessThan(String value) {
      addCriterion("remark <", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLessThanOrEqualTo(String value) {
      addCriterion("remark <=", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLike(String value) {
      addCriterion("remark like", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotLike(String value) {
      addCriterion("remark not like", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkIn(List<String> values) {
      addCriterion("remark in", values, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotIn(List<String> values) {
      addCriterion("remark not in", values, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkBetween(String value1, String value2) {
      addCriterion("remark between", value1, value2, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotBetween(String value1, String value2) {
      addCriterion("remark not between", value1, value2, "remark");
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
