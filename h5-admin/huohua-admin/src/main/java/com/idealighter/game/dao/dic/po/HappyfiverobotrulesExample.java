package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class HappyfiverobotrulesExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public HappyfiverobotrulesExample() {
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
      addCriterion("ID is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("ID is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("ID =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("ID <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("ID >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("ID >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("ID <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("ID <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("ID in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("ID not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("ID between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("ID not between", value1, value2, "id");
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

    public Criteria andTimesIsNull() {
      addCriterion("times is null");
      return (Criteria) this;
    }

    public Criteria andTimesIsNotNull() {
      addCriterion("times is not null");
      return (Criteria) this;
    }

    public Criteria andTimesEqualTo(Integer value) {
      addCriterion("times =", value, "times");
      return (Criteria) this;
    }

    public Criteria andTimesNotEqualTo(Integer value) {
      addCriterion("times <>", value, "times");
      return (Criteria) this;
    }

    public Criteria andTimesGreaterThan(Integer value) {
      addCriterion("times >", value, "times");
      return (Criteria) this;
    }

    public Criteria andTimesGreaterThanOrEqualTo(Integer value) {
      addCriterion("times >=", value, "times");
      return (Criteria) this;
    }

    public Criteria andTimesLessThan(Integer value) {
      addCriterion("times <", value, "times");
      return (Criteria) this;
    }

    public Criteria andTimesLessThanOrEqualTo(Integer value) {
      addCriterion("times <=", value, "times");
      return (Criteria) this;
    }

    public Criteria andTimesIn(List<Integer> values) {
      addCriterion("times in", values, "times");
      return (Criteria) this;
    }

    public Criteria andTimesNotIn(List<Integer> values) {
      addCriterion("times not in", values, "times");
      return (Criteria) this;
    }

    public Criteria andTimesBetween(Integer value1, Integer value2) {
      addCriterion("times between", value1, value2, "times");
      return (Criteria) this;
    }

    public Criteria andTimesNotBetween(Integer value1, Integer value2) {
      addCriterion("times not between", value1, value2, "times");
      return (Criteria) this;
    }

    public Criteria andFirstIsNull() {
      addCriterion("First is null");
      return (Criteria) this;
    }

    public Criteria andFirstIsNotNull() {
      addCriterion("First is not null");
      return (Criteria) this;
    }

    public Criteria andFirstEqualTo(Integer value) {
      addCriterion("First =", value, "first");
      return (Criteria) this;
    }

    public Criteria andFirstNotEqualTo(Integer value) {
      addCriterion("First <>", value, "first");
      return (Criteria) this;
    }

    public Criteria andFirstGreaterThan(Integer value) {
      addCriterion("First >", value, "first");
      return (Criteria) this;
    }

    public Criteria andFirstGreaterThanOrEqualTo(Integer value) {
      addCriterion("First >=", value, "first");
      return (Criteria) this;
    }

    public Criteria andFirstLessThan(Integer value) {
      addCriterion("First <", value, "first");
      return (Criteria) this;
    }

    public Criteria andFirstLessThanOrEqualTo(Integer value) {
      addCriterion("First <=", value, "first");
      return (Criteria) this;
    }

    public Criteria andFirstIn(List<Integer> values) {
      addCriterion("First in", values, "first");
      return (Criteria) this;
    }

    public Criteria andFirstNotIn(List<Integer> values) {
      addCriterion("First not in", values, "first");
      return (Criteria) this;
    }

    public Criteria andFirstBetween(Integer value1, Integer value2) {
      addCriterion("First between", value1, value2, "first");
      return (Criteria) this;
    }

    public Criteria andFirstNotBetween(Integer value1, Integer value2) {
      addCriterion("First not between", value1, value2, "first");
      return (Criteria) this;
    }

    public Criteria andBiggestIsNull() {
      addCriterion("biggest is null");
      return (Criteria) this;
    }

    public Criteria andBiggestIsNotNull() {
      addCriterion("biggest is not null");
      return (Criteria) this;
    }

    public Criteria andBiggestEqualTo(Integer value) {
      addCriterion("biggest =", value, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestNotEqualTo(Integer value) {
      addCriterion("biggest <>", value, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestGreaterThan(Integer value) {
      addCriterion("biggest >", value, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestGreaterThanOrEqualTo(Integer value) {
      addCriterion("biggest >=", value, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestLessThan(Integer value) {
      addCriterion("biggest <", value, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestLessThanOrEqualTo(Integer value) {
      addCriterion("biggest <=", value, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestIn(List<Integer> values) {
      addCriterion("biggest in", values, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestNotIn(List<Integer> values) {
      addCriterion("biggest not in", values, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestBetween(Integer value1, Integer value2) {
      addCriterion("biggest between", value1, value2, "biggest");
      return (Criteria) this;
    }

    public Criteria andBiggestNotBetween(Integer value1, Integer value2) {
      addCriterion("biggest not between", value1, value2, "biggest");
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

    public Criteria andPairedothersIsNull() {
      addCriterion("pairedOthers is null");
      return (Criteria) this;
    }

    public Criteria andPairedothersIsNotNull() {
      addCriterion("pairedOthers is not null");
      return (Criteria) this;
    }

    public Criteria andPairedothersEqualTo(Integer value) {
      addCriterion("pairedOthers =", value, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersNotEqualTo(Integer value) {
      addCriterion("pairedOthers <>", value, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersGreaterThan(Integer value) {
      addCriterion("pairedOthers >", value, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersGreaterThanOrEqualTo(Integer value) {
      addCriterion("pairedOthers >=", value, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersLessThan(Integer value) {
      addCriterion("pairedOthers <", value, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersLessThanOrEqualTo(Integer value) {
      addCriterion("pairedOthers <=", value, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersIn(List<Integer> values) {
      addCriterion("pairedOthers in", values, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersNotIn(List<Integer> values) {
      addCriterion("pairedOthers not in", values, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersBetween(Integer value1, Integer value2) {
      addCriterion("pairedOthers between", value1, value2, "pairedothers");
      return (Criteria) this;
    }

    public Criteria andPairedothersNotBetween(Integer value1, Integer value2) {
      addCriterion("pairedOthers not between", value1, value2, "pairedothers");
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

    public Criteria andTwotwoothersIsNull() {
      addCriterion("twoTwoOthers is null");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersIsNotNull() {
      addCriterion("twoTwoOthers is not null");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersEqualTo(Integer value) {
      addCriterion("twoTwoOthers =", value, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersNotEqualTo(Integer value) {
      addCriterion("twoTwoOthers <>", value, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersGreaterThan(Integer value) {
      addCriterion("twoTwoOthers >", value, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersGreaterThanOrEqualTo(Integer value) {
      addCriterion("twoTwoOthers >=", value, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersLessThan(Integer value) {
      addCriterion("twoTwoOthers <", value, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersLessThanOrEqualTo(Integer value) {
      addCriterion("twoTwoOthers <=", value, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersIn(List<Integer> values) {
      addCriterion("twoTwoOthers in", values, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersNotIn(List<Integer> values) {
      addCriterion("twoTwoOthers not in", values, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersBetween(Integer value1, Integer value2) {
      addCriterion("twoTwoOthers between", value1, value2, "twotwoothers");
      return (Criteria) this;
    }

    public Criteria andTwotwoothersNotBetween(Integer value1, Integer value2) {
      addCriterion("twoTwoOthers not between", value1, value2, "twotwoothers");
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

    public Criteria andThreeothersIsNull() {
      addCriterion("threeOthers is null");
      return (Criteria) this;
    }

    public Criteria andThreeothersIsNotNull() {
      addCriterion("threeOthers is not null");
      return (Criteria) this;
    }

    public Criteria andThreeothersEqualTo(Integer value) {
      addCriterion("threeOthers =", value, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersNotEqualTo(Integer value) {
      addCriterion("threeOthers <>", value, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersGreaterThan(Integer value) {
      addCriterion("threeOthers >", value, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersGreaterThanOrEqualTo(Integer value) {
      addCriterion("threeOthers >=", value, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersLessThan(Integer value) {
      addCriterion("threeOthers <", value, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersLessThanOrEqualTo(Integer value) {
      addCriterion("threeOthers <=", value, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersIn(List<Integer> values) {
      addCriterion("threeOthers in", values, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersNotIn(List<Integer> values) {
      addCriterion("threeOthers not in", values, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersBetween(Integer value1, Integer value2) {
      addCriterion("threeOthers between", value1, value2, "threeothers");
      return (Criteria) this;
    }

    public Criteria andThreeothersNotBetween(Integer value1, Integer value2) {
      addCriterion("threeOthers not between", value1, value2, "threeothers");
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

    public Criteria andLongfourothersIsNull() {
      addCriterion("longFourOthers is null");
      return (Criteria) this;
    }

    public Criteria andLongfourothersIsNotNull() {
      addCriterion("longFourOthers is not null");
      return (Criteria) this;
    }

    public Criteria andLongfourothersEqualTo(Integer value) {
      addCriterion("longFourOthers =", value, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersNotEqualTo(Integer value) {
      addCriterion("longFourOthers <>", value, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersGreaterThan(Integer value) {
      addCriterion("longFourOthers >", value, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersGreaterThanOrEqualTo(Integer value) {
      addCriterion("longFourOthers >=", value, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersLessThan(Integer value) {
      addCriterion("longFourOthers <", value, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersLessThanOrEqualTo(Integer value) {
      addCriterion("longFourOthers <=", value, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersIn(List<Integer> values) {
      addCriterion("longFourOthers in", values, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersNotIn(List<Integer> values) {
      addCriterion("longFourOthers not in", values, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersBetween(Integer value1, Integer value2) {
      addCriterion("longFourOthers between", value1, value2, "longfourothers");
      return (Criteria) this;
    }

    public Criteria andLongfourothersNotBetween(Integer value1, Integer value2) {
      addCriterion("longFourOthers not between", value1, value2, "longfourothers");
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

    public Criteria andSamefourothersIsNull() {
      addCriterion("sameFourOthers is null");
      return (Criteria) this;
    }

    public Criteria andSamefourothersIsNotNull() {
      addCriterion("sameFourOthers is not null");
      return (Criteria) this;
    }

    public Criteria andSamefourothersEqualTo(Integer value) {
      addCriterion("sameFourOthers =", value, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersNotEqualTo(Integer value) {
      addCriterion("sameFourOthers <>", value, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersGreaterThan(Integer value) {
      addCriterion("sameFourOthers >", value, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersGreaterThanOrEqualTo(Integer value) {
      addCriterion("sameFourOthers >=", value, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersLessThan(Integer value) {
      addCriterion("sameFourOthers <", value, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersLessThanOrEqualTo(Integer value) {
      addCriterion("sameFourOthers <=", value, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersIn(List<Integer> values) {
      addCriterion("sameFourOthers in", values, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersNotIn(List<Integer> values) {
      addCriterion("sameFourOthers not in", values, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersBetween(Integer value1, Integer value2) {
      addCriterion("sameFourOthers between", value1, value2, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andSamefourothersNotBetween(Integer value1, Integer value2) {
      addCriterion("sameFourOthers not between", value1, value2, "samefourothers");
      return (Criteria) this;
    }

    public Criteria andWin1IsNull() {
      addCriterion("win1 is null");
      return (Criteria) this;
    }

    public Criteria andWin1IsNotNull() {
      addCriterion("win1 is not null");
      return (Criteria) this;
    }

    public Criteria andWin1EqualTo(Integer value) {
      addCriterion("win1 =", value, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1NotEqualTo(Integer value) {
      addCriterion("win1 <>", value, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1GreaterThan(Integer value) {
      addCriterion("win1 >", value, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1GreaterThanOrEqualTo(Integer value) {
      addCriterion("win1 >=", value, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1LessThan(Integer value) {
      addCriterion("win1 <", value, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1LessThanOrEqualTo(Integer value) {
      addCriterion("win1 <=", value, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1In(List<Integer> values) {
      addCriterion("win1 in", values, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1NotIn(List<Integer> values) {
      addCriterion("win1 not in", values, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1Between(Integer value1, Integer value2) {
      addCriterion("win1 between", value1, value2, "win1");
      return (Criteria) this;
    }

    public Criteria andWin1NotBetween(Integer value1, Integer value2) {
      addCriterion("win1 not between", value1, value2, "win1");
      return (Criteria) this;
    }

    public Criteria andShowhandothersIsNull() {
      addCriterion("showHandOthers is null");
      return (Criteria) this;
    }

    public Criteria andShowhandothersIsNotNull() {
      addCriterion("showHandOthers is not null");
      return (Criteria) this;
    }

    public Criteria andShowhandothersEqualTo(Integer value) {
      addCriterion("showHandOthers =", value, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersNotEqualTo(Integer value) {
      addCriterion("showHandOthers <>", value, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersGreaterThan(Integer value) {
      addCriterion("showHandOthers >", value, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersGreaterThanOrEqualTo(Integer value) {
      addCriterion("showHandOthers >=", value, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersLessThan(Integer value) {
      addCriterion("showHandOthers <", value, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersLessThanOrEqualTo(Integer value) {
      addCriterion("showHandOthers <=", value, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersIn(List<Integer> values) {
      addCriterion("showHandOthers in", values, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersNotIn(List<Integer> values) {
      addCriterion("showHandOthers not in", values, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersBetween(Integer value1, Integer value2) {
      addCriterion("showHandOthers between", value1, value2, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andShowhandothersNotBetween(Integer value1, Integer value2) {
      addCriterion("showHandOthers not between", value1, value2, "showhandothers");
      return (Criteria) this;
    }

    public Criteria andWin2IsNull() {
      addCriterion("win2 is null");
      return (Criteria) this;
    }

    public Criteria andWin2IsNotNull() {
      addCriterion("win2 is not null");
      return (Criteria) this;
    }

    public Criteria andWin2EqualTo(Integer value) {
      addCriterion("win2 =", value, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2NotEqualTo(Integer value) {
      addCriterion("win2 <>", value, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2GreaterThan(Integer value) {
      addCriterion("win2 >", value, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2GreaterThanOrEqualTo(Integer value) {
      addCriterion("win2 >=", value, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2LessThan(Integer value) {
      addCriterion("win2 <", value, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2LessThanOrEqualTo(Integer value) {
      addCriterion("win2 <=", value, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2In(List<Integer> values) {
      addCriterion("win2 in", values, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2NotIn(List<Integer> values) {
      addCriterion("win2 not in", values, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2Between(Integer value1, Integer value2) {
      addCriterion("win2 between", value1, value2, "win2");
      return (Criteria) this;
    }

    public Criteria andWin2NotBetween(Integer value1, Integer value2) {
      addCriterion("win2 not between", value1, value2, "win2");
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

    public Criteria andFilling1IsNull() {
      addCriterion("filling1 is null");
      return (Criteria) this;
    }

    public Criteria andFilling1IsNotNull() {
      addCriterion("filling1 is not null");
      return (Criteria) this;
    }

    public Criteria andFilling1EqualTo(Integer value) {
      addCriterion("filling1 =", value, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1NotEqualTo(Integer value) {
      addCriterion("filling1 <>", value, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1GreaterThan(Integer value) {
      addCriterion("filling1 >", value, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1GreaterThanOrEqualTo(Integer value) {
      addCriterion("filling1 >=", value, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1LessThan(Integer value) {
      addCriterion("filling1 <", value, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1LessThanOrEqualTo(Integer value) {
      addCriterion("filling1 <=", value, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1In(List<Integer> values) {
      addCriterion("filling1 in", values, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1NotIn(List<Integer> values) {
      addCriterion("filling1 not in", values, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1Between(Integer value1, Integer value2) {
      addCriterion("filling1 between", value1, value2, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling1NotBetween(Integer value1, Integer value2) {
      addCriterion("filling1 not between", value1, value2, "filling1");
      return (Criteria) this;
    }

    public Criteria andFilling2IsNull() {
      addCriterion("filling2 is null");
      return (Criteria) this;
    }

    public Criteria andFilling2IsNotNull() {
      addCriterion("filling2 is not null");
      return (Criteria) this;
    }

    public Criteria andFilling2EqualTo(Integer value) {
      addCriterion("filling2 =", value, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2NotEqualTo(Integer value) {
      addCriterion("filling2 <>", value, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2GreaterThan(Integer value) {
      addCriterion("filling2 >", value, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2GreaterThanOrEqualTo(Integer value) {
      addCriterion("filling2 >=", value, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2LessThan(Integer value) {
      addCriterion("filling2 <", value, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2LessThanOrEqualTo(Integer value) {
      addCriterion("filling2 <=", value, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2In(List<Integer> values) {
      addCriterion("filling2 in", values, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2NotIn(List<Integer> values) {
      addCriterion("filling2 not in", values, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2Between(Integer value1, Integer value2) {
      addCriterion("filling2 between", value1, value2, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling2NotBetween(Integer value1, Integer value2) {
      addCriterion("filling2 not between", value1, value2, "filling2");
      return (Criteria) this;
    }

    public Criteria andFilling3IsNull() {
      addCriterion("filling3 is null");
      return (Criteria) this;
    }

    public Criteria andFilling3IsNotNull() {
      addCriterion("filling3 is not null");
      return (Criteria) this;
    }

    public Criteria andFilling3EqualTo(Integer value) {
      addCriterion("filling3 =", value, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3NotEqualTo(Integer value) {
      addCriterion("filling3 <>", value, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3GreaterThan(Integer value) {
      addCriterion("filling3 >", value, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3GreaterThanOrEqualTo(Integer value) {
      addCriterion("filling3 >=", value, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3LessThan(Integer value) {
      addCriterion("filling3 <", value, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3LessThanOrEqualTo(Integer value) {
      addCriterion("filling3 <=", value, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3In(List<Integer> values) {
      addCriterion("filling3 in", values, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3NotIn(List<Integer> values) {
      addCriterion("filling3 not in", values, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3Between(Integer value1, Integer value2) {
      addCriterion("filling3 between", value1, value2, "filling3");
      return (Criteria) this;
    }

    public Criteria andFilling3NotBetween(Integer value1, Integer value2) {
      addCriterion("filling3 not between", value1, value2, "filling3");
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
