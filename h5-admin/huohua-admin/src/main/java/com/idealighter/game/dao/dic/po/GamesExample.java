package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class GamesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GamesExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andRoomTableIsNull() {
            addCriterion("roomTable is null");
            return (Criteria) this;
        }

        public Criteria andRoomTableIsNotNull() {
            addCriterion("roomTable is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTableEqualTo(String value) {
            addCriterion("roomTable =", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableNotEqualTo(String value) {
            addCriterion("roomTable <>", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableGreaterThan(String value) {
            addCriterion("roomTable >", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableGreaterThanOrEqualTo(String value) {
            addCriterion("roomTable >=", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableLessThan(String value) {
            addCriterion("roomTable <", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableLessThanOrEqualTo(String value) {
            addCriterion("roomTable <=", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableLike(String value) {
            addCriterion("roomTable like", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableNotLike(String value) {
            addCriterion("roomTable not like", value, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableIn(List<String> values) {
            addCriterion("roomTable in", values, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableNotIn(List<String> values) {
            addCriterion("roomTable not in", values, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableBetween(String value1, String value2) {
            addCriterion("roomTable between", value1, value2, "roomTable");
            return (Criteria) this;
        }

        public Criteria andRoomTableNotBetween(String value1, String value2) {
            addCriterion("roomTable not between", value1, value2, "roomTable");
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

        public Criteria andActiveIsNull() {
            addCriterion("active is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("active is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("active =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("active in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("active not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("active between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("active not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andNeedRobotIsNull() {
            addCriterion("needRobot is null");
            return (Criteria) this;
        }

        public Criteria andNeedRobotIsNotNull() {
            addCriterion("needRobot is not null");
            return (Criteria) this;
        }

        public Criteria andNeedRobotEqualTo(Boolean value) {
            addCriterion("needRobot =", value, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotNotEqualTo(Boolean value) {
            addCriterion("needRobot <>", value, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotGreaterThan(Boolean value) {
            addCriterion("needRobot >", value, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotGreaterThanOrEqualTo(Boolean value) {
            addCriterion("needRobot >=", value, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotLessThan(Boolean value) {
            addCriterion("needRobot <", value, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotLessThanOrEqualTo(Boolean value) {
            addCriterion("needRobot <=", value, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotIn(List<Boolean> values) {
            addCriterion("needRobot in", values, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotNotIn(List<Boolean> values) {
            addCriterion("needRobot not in", values, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotBetween(Boolean value1, Boolean value2) {
            addCriterion("needRobot between", value1, value2, "needRobot");
            return (Criteria) this;
        }

        public Criteria andNeedRobotNotBetween(Boolean value1, Boolean value2) {
            addCriterion("needRobot not between", value1, value2, "needRobot");
            return (Criteria) this;
        }

        public Criteria andRoundGameIsNull() {
            addCriterion("roundGame is null");
            return (Criteria) this;
        }

        public Criteria andRoundGameIsNotNull() {
            addCriterion("roundGame is not null");
            return (Criteria) this;
        }

        public Criteria andRoundGameEqualTo(Boolean value) {
            addCriterion("roundGame =", value, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameNotEqualTo(Boolean value) {
            addCriterion("roundGame <>", value, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameGreaterThan(Boolean value) {
            addCriterion("roundGame >", value, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameGreaterThanOrEqualTo(Boolean value) {
            addCriterion("roundGame >=", value, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameLessThan(Boolean value) {
            addCriterion("roundGame <", value, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameLessThanOrEqualTo(Boolean value) {
            addCriterion("roundGame <=", value, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameIn(List<Boolean> values) {
            addCriterion("roundGame in", values, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameNotIn(List<Boolean> values) {
            addCriterion("roundGame not in", values, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameBetween(Boolean value1, Boolean value2) {
            addCriterion("roundGame between", value1, value2, "roundGame");
            return (Criteria) this;
        }

        public Criteria andRoundGameNotBetween(Boolean value1, Boolean value2) {
            addCriterion("roundGame not between", value1, value2, "roundGame");
            return (Criteria) this;
        }

        public Criteria andPersonControlIsNull() {
            addCriterion("personControl is null");
            return (Criteria) this;
        }

        public Criteria andPersonControlIsNotNull() {
            addCriterion("personControl is not null");
            return (Criteria) this;
        }

        public Criteria andPersonControlEqualTo(Boolean value) {
            addCriterion("personControl =", value, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlNotEqualTo(Boolean value) {
            addCriterion("personControl <>", value, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlGreaterThan(Boolean value) {
            addCriterion("personControl >", value, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlGreaterThanOrEqualTo(Boolean value) {
            addCriterion("personControl >=", value, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlLessThan(Boolean value) {
            addCriterion("personControl <", value, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlLessThanOrEqualTo(Boolean value) {
            addCriterion("personControl <=", value, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlIn(List<Boolean> values) {
            addCriterion("personControl in", values, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlNotIn(List<Boolean> values) {
            addCriterion("personControl not in", values, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlBetween(Boolean value1, Boolean value2) {
            addCriterion("personControl between", value1, value2, "personControl");
            return (Criteria) this;
        }

        public Criteria andPersonControlNotBetween(Boolean value1, Boolean value2) {
            addCriterion("personControl not between", value1, value2, "personControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlIsNull() {
            addCriterion("roomControl is null");
            return (Criteria) this;
        }

        public Criteria andRoomControlIsNotNull() {
            addCriterion("roomControl is not null");
            return (Criteria) this;
        }

        public Criteria andRoomControlEqualTo(Boolean value) {
            addCriterion("roomControl =", value, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlNotEqualTo(Boolean value) {
            addCriterion("roomControl <>", value, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlGreaterThan(Boolean value) {
            addCriterion("roomControl >", value, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlGreaterThanOrEqualTo(Boolean value) {
            addCriterion("roomControl >=", value, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlLessThan(Boolean value) {
            addCriterion("roomControl <", value, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlLessThanOrEqualTo(Boolean value) {
            addCriterion("roomControl <=", value, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlIn(List<Boolean> values) {
            addCriterion("roomControl in", values, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlNotIn(List<Boolean> values) {
            addCriterion("roomControl not in", values, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlBetween(Boolean value1, Boolean value2) {
            addCriterion("roomControl between", value1, value2, "roomControl");
            return (Criteria) this;
        }

        public Criteria andRoomControlNotBetween(Boolean value1, Boolean value2) {
            addCriterion("roomControl not between", value1, value2, "roomControl");
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