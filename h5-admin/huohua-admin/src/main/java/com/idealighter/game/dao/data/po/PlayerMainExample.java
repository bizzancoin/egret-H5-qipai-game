package com.idealighter.game.dao.data.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public PlayerMainExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSuperIdIsNull() {
            addCriterion("super_id is null");
            return (Criteria) this;
        }

        public Criteria andSuperIdIsNotNull() {
            addCriterion("super_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuperIdEqualTo(Long value) {
            addCriterion("super_id =", value, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdNotEqualTo(Long value) {
            addCriterion("super_id <>", value, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdGreaterThan(Long value) {
            addCriterion("super_id >", value, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("super_id >=", value, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdLessThan(Long value) {
            addCriterion("super_id <", value, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdLessThanOrEqualTo(Long value) {
            addCriterion("super_id <=", value, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdIn(List<Long> values) {
            addCriterion("super_id in", values, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdNotIn(List<Long> values) {
            addCriterion("super_id not in", values, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdBetween(Long value1, Long value2) {
            addCriterion("super_id between", value1, value2, "superId");
            return (Criteria) this;
        }

        public Criteria andSuperIdNotBetween(Long value1, Long value2) {
            addCriterion("super_id not between", value1, value2, "superId");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andUnionIdIsNull() {
            addCriterion("union_id is null");
            return (Criteria) this;
        }

        public Criteria andUnionIdIsNotNull() {
            addCriterion("union_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnionIdEqualTo(String value) {
            addCriterion("union_id =", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotEqualTo(String value) {
            addCriterion("union_id <>", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdGreaterThan(String value) {
            addCriterion("union_id >", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdGreaterThanOrEqualTo(String value) {
            addCriterion("union_id >=", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLessThan(String value) {
            addCriterion("union_id <", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLessThanOrEqualTo(String value) {
            addCriterion("union_id <=", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLike(String value) {
            addCriterion("union_id like", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotLike(String value) {
            addCriterion("union_id not like", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdIn(List<String> values) {
            addCriterion("union_id in", values, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotIn(List<String> values) {
            addCriterion("union_id not in", values, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdBetween(String value1, String value2) {
            addCriterion("union_id between", value1, value2, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotBetween(String value1, String value2) {
            addCriterion("union_id not between", value1, value2, "unionId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPlayerNameIsNull() {
            addCriterion("player_name is null");
            return (Criteria) this;
        }

        public Criteria andPlayerNameIsNotNull() {
            addCriterion("player_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerNameEqualTo(String value) {
            addCriterion("player_name =", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameNotEqualTo(String value) {
            addCriterion("player_name <>", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameGreaterThan(String value) {
            addCriterion("player_name >", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameGreaterThanOrEqualTo(String value) {
            addCriterion("player_name >=", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameLessThan(String value) {
            addCriterion("player_name <", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameLessThanOrEqualTo(String value) {
            addCriterion("player_name <=", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameLike(String value) {
            addCriterion("player_name like", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameNotLike(String value) {
            addCriterion("player_name not like", value, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameIn(List<String> values) {
            addCriterion("player_name in", values, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameNotIn(List<String> values) {
            addCriterion("player_name not in", values, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameBetween(String value1, String value2) {
            addCriterion("player_name between", value1, value2, "playerName");
            return (Criteria) this;
        }

        public Criteria andPlayerNameNotBetween(String value1, String value2) {
            addCriterion("player_name not between", value1, value2, "playerName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeIsNull() {
            addCriterion("re_login_code is null");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeIsNotNull() {
            addCriterion("re_login_code is not null");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeEqualTo(String value) {
            addCriterion("re_login_code =", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeNotEqualTo(String value) {
            addCriterion("re_login_code <>", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeGreaterThan(String value) {
            addCriterion("re_login_code >", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("re_login_code >=", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeLessThan(String value) {
            addCriterion("re_login_code <", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeLessThanOrEqualTo(String value) {
            addCriterion("re_login_code <=", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeLike(String value) {
            addCriterion("re_login_code like", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeNotLike(String value) {
            addCriterion("re_login_code not like", value, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeIn(List<String> values) {
            addCriterion("re_login_code in", values, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeNotIn(List<String> values) {
            addCriterion("re_login_code not in", values, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeBetween(String value1, String value2) {
            addCriterion("re_login_code between", value1, value2, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andReLoginCodeNotBetween(String value1, String value2) {
            addCriterion("re_login_code not between", value1, value2, "reLoginCode");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIsNull() {
            addCriterion("recommend_id is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIsNotNull() {
            addCriterion("recommend_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendIdEqualTo(Long value) {
            addCriterion("recommend_id =", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotEqualTo(Long value) {
            addCriterion("recommend_id <>", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdGreaterThan(Long value) {
            addCriterion("recommend_id >", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recommend_id >=", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdLessThan(Long value) {
            addCriterion("recommend_id <", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdLessThanOrEqualTo(Long value) {
            addCriterion("recommend_id <=", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIn(List<Long> values) {
            addCriterion("recommend_id in", values, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotIn(List<Long> values) {
            addCriterion("recommend_id not in", values, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdBetween(Long value1, Long value2) {
            addCriterion("recommend_id between", value1, value2, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotBetween(Long value1, Long value2) {
            addCriterion("recommend_id not between", value1, value2, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIsNull() {
            addCriterion("register_type is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIsNotNull() {
            addCriterion("register_type is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeEqualTo(Byte value) {
            addCriterion("register_type =", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotEqualTo(Byte value) {
            addCriterion("register_type <>", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeGreaterThan(Byte value) {
            addCriterion("register_type >", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("register_type >=", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeLessThan(Byte value) {
            addCriterion("register_type <", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeLessThanOrEqualTo(Byte value) {
            addCriterion("register_type <=", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIn(List<Byte> values) {
            addCriterion("register_type in", values, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotIn(List<Byte> values) {
            addCriterion("register_type not in", values, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeBetween(Byte value1, Byte value2) {
            addCriterion("register_type between", value1, value2, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("register_type not between", value1, value2, "registerType");
            return (Criteria) this;
        }

        public Criteria andTouristIsNull() {
            addCriterion("tourist is null");
            return (Criteria) this;
        }

        public Criteria andTouristIsNotNull() {
            addCriterion("tourist is not null");
            return (Criteria) this;
        }

        public Criteria andTouristEqualTo(Boolean value) {
            addCriterion("tourist =", value, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristNotEqualTo(Boolean value) {
            addCriterion("tourist <>", value, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristGreaterThan(Boolean value) {
            addCriterion("tourist >", value, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristGreaterThanOrEqualTo(Boolean value) {
            addCriterion("tourist >=", value, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristLessThan(Boolean value) {
            addCriterion("tourist <", value, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristLessThanOrEqualTo(Boolean value) {
            addCriterion("tourist <=", value, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristIn(List<Boolean> values) {
            addCriterion("tourist in", values, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristNotIn(List<Boolean> values) {
            addCriterion("tourist not in", values, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristBetween(Boolean value1, Boolean value2) {
            addCriterion("tourist between", value1, value2, "tourist");
            return (Criteria) this;
        }

        public Criteria andTouristNotBetween(Boolean value1, Boolean value2) {
            addCriterion("tourist not between", value1, value2, "tourist");
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

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andOnlineIsNull() {
            addCriterion("online is null");
            return (Criteria) this;
        }

        public Criteria andOnlineIsNotNull() {
            addCriterion("online is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineEqualTo(Boolean value) {
            addCriterion("online =", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineNotEqualTo(Boolean value) {
            addCriterion("online <>", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineGreaterThan(Boolean value) {
            addCriterion("online >", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("online >=", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineLessThan(Boolean value) {
            addCriterion("online <", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineLessThanOrEqualTo(Boolean value) {
            addCriterion("online <=", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineIn(List<Boolean> values) {
            addCriterion("online in", values, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineNotIn(List<Boolean> values) {
            addCriterion("online not in", values, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineBetween(Boolean value1, Boolean value2) {
            addCriterion("online between", value1, value2, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("online not between", value1, value2, "online");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(Integer value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(Integer value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(Integer value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(Integer value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(Integer value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(Integer value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<Integer> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<Integer> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(Integer value1, Integer value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(Integer value1, Integer value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andExpIsNull() {
            addCriterion("exp is null");
            return (Criteria) this;
        }

        public Criteria andExpIsNotNull() {
            addCriterion("exp is not null");
            return (Criteria) this;
        }

        public Criteria andExpEqualTo(Long value) {
            addCriterion("exp =", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotEqualTo(Long value) {
            addCriterion("exp <>", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpGreaterThan(Long value) {
            addCriterion("exp >", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpGreaterThanOrEqualTo(Long value) {
            addCriterion("exp >=", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpLessThan(Long value) {
            addCriterion("exp <", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpLessThanOrEqualTo(Long value) {
            addCriterion("exp <=", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpIn(List<Long> values) {
            addCriterion("exp in", values, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotIn(List<Long> values) {
            addCriterion("exp not in", values, "exp");
            return (Criteria) this;
        }

        public Criteria andExpBetween(Long value1, Long value2) {
            addCriterion("exp between", value1, value2, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotBetween(Long value1, Long value2) {
            addCriterion("exp not between", value1, value2, "exp");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andIngotIsNull() {
            addCriterion("ingot is null");
            return (Criteria) this;
        }

        public Criteria andIngotIsNotNull() {
            addCriterion("ingot is not null");
            return (Criteria) this;
        }

        public Criteria andIngotEqualTo(Long value) {
            addCriterion("ingot =", value, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotNotEqualTo(Long value) {
            addCriterion("ingot <>", value, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotGreaterThan(Long value) {
            addCriterion("ingot >", value, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotGreaterThanOrEqualTo(Long value) {
            addCriterion("ingot >=", value, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotLessThan(Long value) {
            addCriterion("ingot <", value, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotLessThanOrEqualTo(Long value) {
            addCriterion("ingot <=", value, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotIn(List<Long> values) {
            addCriterion("ingot in", values, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotNotIn(List<Long> values) {
            addCriterion("ingot not in", values, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotBetween(Long value1, Long value2) {
            addCriterion("ingot between", value1, value2, "ingot");
            return (Criteria) this;
        }

        public Criteria andIngotNotBetween(Long value1, Long value2) {
            addCriterion("ingot not between", value1, value2, "ingot");
            return (Criteria) this;
        }

        public Criteria andGoldIsNull() {
            addCriterion("gold is null");
            return (Criteria) this;
        }

        public Criteria andGoldIsNotNull() {
            addCriterion("gold is not null");
            return (Criteria) this;
        }

        public Criteria andGoldEqualTo(Long value) {
            addCriterion("gold =", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotEqualTo(Long value) {
            addCriterion("gold <>", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldGreaterThan(Long value) {
            addCriterion("gold >", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldGreaterThanOrEqualTo(Long value) {
            addCriterion("gold >=", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldLessThan(Long value) {
            addCriterion("gold <", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldLessThanOrEqualTo(Long value) {
            addCriterion("gold <=", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldIn(List<Long> values) {
            addCriterion("gold in", values, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotIn(List<Long> values) {
            addCriterion("gold not in", values, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldBetween(Long value1, Long value2) {
            addCriterion("gold between", value1, value2, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotBetween(Long value1, Long value2) {
            addCriterion("gold not between", value1, value2, "gold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldIsNull() {
            addCriterion("safe_gold is null");
            return (Criteria) this;
        }

        public Criteria andSafeGoldIsNotNull() {
            addCriterion("safe_gold is not null");
            return (Criteria) this;
        }

        public Criteria andSafeGoldEqualTo(Long value) {
            addCriterion("safe_gold =", value, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldNotEqualTo(Long value) {
            addCriterion("safe_gold <>", value, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldGreaterThan(Long value) {
            addCriterion("safe_gold >", value, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldGreaterThanOrEqualTo(Long value) {
            addCriterion("safe_gold >=", value, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldLessThan(Long value) {
            addCriterion("safe_gold <", value, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldLessThanOrEqualTo(Long value) {
            addCriterion("safe_gold <=", value, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldIn(List<Long> values) {
            addCriterion("safe_gold in", values, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldNotIn(List<Long> values) {
            addCriterion("safe_gold not in", values, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldBetween(Long value1, Long value2) {
            addCriterion("safe_gold between", value1, value2, "safeGold");
            return (Criteria) this;
        }

        public Criteria andSafeGoldNotBetween(Long value1, Long value2) {
            addCriterion("safe_gold not between", value1, value2, "safeGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldIsNull() {
            addCriterion("win_gold is null");
            return (Criteria) this;
        }

        public Criteria andWinGoldIsNotNull() {
            addCriterion("win_gold is not null");
            return (Criteria) this;
        }

        public Criteria andWinGoldEqualTo(Long value) {
            addCriterion("win_gold =", value, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldNotEqualTo(Long value) {
            addCriterion("win_gold <>", value, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldGreaterThan(Long value) {
            addCriterion("win_gold >", value, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldGreaterThanOrEqualTo(Long value) {
            addCriterion("win_gold >=", value, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldLessThan(Long value) {
            addCriterion("win_gold <", value, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldLessThanOrEqualTo(Long value) {
            addCriterion("win_gold <=", value, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldIn(List<Long> values) {
            addCriterion("win_gold in", values, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldNotIn(List<Long> values) {
            addCriterion("win_gold not in", values, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldBetween(Long value1, Long value2) {
            addCriterion("win_gold between", value1, value2, "winGold");
            return (Criteria) this;
        }

        public Criteria andWinGoldNotBetween(Long value1, Long value2) {
            addCriterion("win_gold not between", value1, value2, "winGold");
            return (Criteria) this;
        }

        public Criteria andCeditIsNull() {
            addCriterion("cedit is null");
            return (Criteria) this;
        }

        public Criteria andCeditIsNotNull() {
            addCriterion("cedit is not null");
            return (Criteria) this;
        }

        public Criteria andCeditEqualTo(Long value) {
            addCriterion("cedit =", value, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditNotEqualTo(Long value) {
            addCriterion("cedit <>", value, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditGreaterThan(Long value) {
            addCriterion("cedit >", value, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditGreaterThanOrEqualTo(Long value) {
            addCriterion("cedit >=", value, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditLessThan(Long value) {
            addCriterion("cedit <", value, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditLessThanOrEqualTo(Long value) {
            addCriterion("cedit <=", value, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditIn(List<Long> values) {
            addCriterion("cedit in", values, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditNotIn(List<Long> values) {
            addCriterion("cedit not in", values, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditBetween(Long value1, Long value2) {
            addCriterion("cedit between", value1, value2, "cedit");
            return (Criteria) this;
        }

        public Criteria andCeditNotBetween(Long value1, Long value2) {
            addCriterion("cedit not between", value1, value2, "cedit");
            return (Criteria) this;
        }

        public Criteria andLotteryIsNull() {
            addCriterion("lottery is null");
            return (Criteria) this;
        }

        public Criteria andLotteryIsNotNull() {
            addCriterion("lottery is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryEqualTo(Long value) {
            addCriterion("lottery =", value, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryNotEqualTo(Long value) {
            addCriterion("lottery <>", value, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryGreaterThan(Long value) {
            addCriterion("lottery >", value, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryGreaterThanOrEqualTo(Long value) {
            addCriterion("lottery >=", value, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryLessThan(Long value) {
            addCriterion("lottery <", value, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryLessThanOrEqualTo(Long value) {
            addCriterion("lottery <=", value, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryIn(List<Long> values) {
            addCriterion("lottery in", values, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryNotIn(List<Long> values) {
            addCriterion("lottery not in", values, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryBetween(Long value1, Long value2) {
            addCriterion("lottery between", value1, value2, "lottery");
            return (Criteria) this;
        }

        public Criteria andLotteryNotBetween(Long value1, Long value2) {
            addCriterion("lottery not between", value1, value2, "lottery");
            return (Criteria) this;
        }

        public Criteria andTransferRewardIsNull() {
            addCriterion("transfer_reward is null");
            return (Criteria) this;
        }

        public Criteria andTransferRewardIsNotNull() {
            addCriterion("transfer_reward is not null");
            return (Criteria) this;
        }

        public Criteria andTransferRewardEqualTo(Long value) {
            addCriterion("transfer_reward =", value, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardNotEqualTo(Long value) {
            addCriterion("transfer_reward <>", value, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardGreaterThan(Long value) {
            addCriterion("transfer_reward >", value, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardGreaterThanOrEqualTo(Long value) {
            addCriterion("transfer_reward >=", value, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardLessThan(Long value) {
            addCriterion("transfer_reward <", value, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardLessThanOrEqualTo(Long value) {
            addCriterion("transfer_reward <=", value, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardIn(List<Long> values) {
            addCriterion("transfer_reward in", values, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardNotIn(List<Long> values) {
            addCriterion("transfer_reward not in", values, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardBetween(Long value1, Long value2) {
            addCriterion("transfer_reward between", value1, value2, "transferReward");
            return (Criteria) this;
        }

        public Criteria andTransferRewardNotBetween(Long value1, Long value2) {
            addCriterion("transfer_reward not between", value1, value2, "transferReward");
            return (Criteria) this;
        }

        public Criteria andBankPwdIsNull() {
            addCriterion("bank_pwd is null");
            return (Criteria) this;
        }

        public Criteria andBankPwdIsNotNull() {
            addCriterion("bank_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andBankPwdEqualTo(String value) {
            addCriterion("bank_pwd =", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdNotEqualTo(String value) {
            addCriterion("bank_pwd <>", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdGreaterThan(String value) {
            addCriterion("bank_pwd >", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdGreaterThanOrEqualTo(String value) {
            addCriterion("bank_pwd >=", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdLessThan(String value) {
            addCriterion("bank_pwd <", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdLessThanOrEqualTo(String value) {
            addCriterion("bank_pwd <=", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdLike(String value) {
            addCriterion("bank_pwd like", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdNotLike(String value) {
            addCriterion("bank_pwd not like", value, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdIn(List<String> values) {
            addCriterion("bank_pwd in", values, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdNotIn(List<String> values) {
            addCriterion("bank_pwd not in", values, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdBetween(String value1, String value2) {
            addCriterion("bank_pwd between", value1, value2, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andBankPwdNotBetween(String value1, String value2) {
            addCriterion("bank_pwd not between", value1, value2, "bankPwd");
            return (Criteria) this;
        }

        public Criteria andVipLevelIsNull() {
            addCriterion("vip_level is null");
            return (Criteria) this;
        }

        public Criteria andVipLevelIsNotNull() {
            addCriterion("vip_level is not null");
            return (Criteria) this;
        }

        public Criteria andVipLevelEqualTo(Integer value) {
            addCriterion("vip_level =", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelNotEqualTo(Integer value) {
            addCriterion("vip_level <>", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelGreaterThan(Integer value) {
            addCriterion("vip_level >", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("vip_level >=", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelLessThan(Integer value) {
            addCriterion("vip_level <", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelLessThanOrEqualTo(Integer value) {
            addCriterion("vip_level <=", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelIn(List<Integer> values) {
            addCriterion("vip_level in", values, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelNotIn(List<Integer> values) {
            addCriterion("vip_level not in", values, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelBetween(Integer value1, Integer value2) {
            addCriterion("vip_level between", value1, value2, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("vip_level not between", value1, value2, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeIsNull() {
            addCriterion("vip_end_time is null");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeIsNotNull() {
            addCriterion("vip_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeEqualTo(Date value) {
            addCriterion("vip_end_time =", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeNotEqualTo(Date value) {
            addCriterion("vip_end_time <>", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeGreaterThan(Date value) {
            addCriterion("vip_end_time >", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("vip_end_time >=", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeLessThan(Date value) {
            addCriterion("vip_end_time <", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("vip_end_time <=", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeIn(List<Date> values) {
            addCriterion("vip_end_time in", values, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeNotIn(List<Date> values) {
            addCriterion("vip_end_time not in", values, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeBetween(Date value1, Date value2) {
            addCriterion("vip_end_time between", value1, value2, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("vip_end_time not between", value1, value2, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andLockedIsNull() {
            addCriterion("locked is null");
            return (Criteria) this;
        }

        public Criteria andLockedIsNotNull() {
            addCriterion("locked is not null");
            return (Criteria) this;
        }

        public Criteria andLockedEqualTo(Boolean value) {
            addCriterion("locked =", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotEqualTo(Boolean value) {
            addCriterion("locked <>", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThan(Boolean value) {
            addCriterion("locked >", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("locked >=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThan(Boolean value) {
            addCriterion("locked <", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThanOrEqualTo(Boolean value) {
            addCriterion("locked <=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedIn(List<Boolean> values) {
            addCriterion("locked in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotIn(List<Boolean> values) {
            addCriterion("locked not in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedBetween(Boolean value1, Boolean value2) {
            addCriterion("locked between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("locked not between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("channel_id like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("channel_id not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
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