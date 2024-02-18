package com.idealighter.game.dao.data.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public MailExample() {
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

        public Criteria andPlayerIdIsNull() {
            addCriterion("player_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNotNull() {
            addCriterion("player_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdEqualTo(Long value) {
            addCriterion("player_id =", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotEqualTo(Long value) {
            addCriterion("player_id <>", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThan(Long value) {
            addCriterion("player_id >", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("player_id >=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThan(Long value) {
            addCriterion("player_id <", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThanOrEqualTo(Long value) {
            addCriterion("player_id <=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIn(List<Long> values) {
            addCriterion("player_id in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotIn(List<Long> values) {
            addCriterion("player_id not in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdBetween(Long value1, Long value2) {
            addCriterion("player_id between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotBetween(Long value1, Long value2) {
            addCriterion("player_id not between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdIsNull() {
            addCriterion("player_super_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdIsNotNull() {
            addCriterion("player_super_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdEqualTo(Long value) {
            addCriterion("player_super_id =", value, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdNotEqualTo(Long value) {
            addCriterion("player_super_id <>", value, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdGreaterThan(Long value) {
            addCriterion("player_super_id >", value, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("player_super_id >=", value, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdLessThan(Long value) {
            addCriterion("player_super_id <", value, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdLessThanOrEqualTo(Long value) {
            addCriterion("player_super_id <=", value, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdIn(List<Long> values) {
            addCriterion("player_super_id in", values, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdNotIn(List<Long> values) {
            addCriterion("player_super_id not in", values, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdBetween(Long value1, Long value2) {
            addCriterion("player_super_id between", value1, value2, "playerSuperId");
            return (Criteria) this;
        }

        public Criteria andPlayerSuperIdNotBetween(Long value1, Long value2) {
            addCriterion("player_super_id not between", value1, value2, "playerSuperId");
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

        public Criteria andFromPlayerIdIsNull() {
            addCriterion("from_player_id is null");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdIsNotNull() {
            addCriterion("from_player_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdEqualTo(Long value) {
            addCriterion("from_player_id =", value, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdNotEqualTo(Long value) {
            addCriterion("from_player_id <>", value, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdGreaterThan(Long value) {
            addCriterion("from_player_id >", value, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("from_player_id >=", value, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdLessThan(Long value) {
            addCriterion("from_player_id <", value, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdLessThanOrEqualTo(Long value) {
            addCriterion("from_player_id <=", value, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdIn(List<Long> values) {
            addCriterion("from_player_id in", values, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdNotIn(List<Long> values) {
            addCriterion("from_player_id not in", values, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdBetween(Long value1, Long value2) {
            addCriterion("from_player_id between", value1, value2, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerIdNotBetween(Long value1, Long value2) {
            addCriterion("from_player_id not between", value1, value2, "fromPlayerId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdIsNull() {
            addCriterion("from_player_super_id is null");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdIsNotNull() {
            addCriterion("from_player_super_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdEqualTo(Long value) {
            addCriterion("from_player_super_id =", value, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdNotEqualTo(Long value) {
            addCriterion("from_player_super_id <>", value, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdGreaterThan(Long value) {
            addCriterion("from_player_super_id >", value, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("from_player_super_id >=", value, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdLessThan(Long value) {
            addCriterion("from_player_super_id <", value, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdLessThanOrEqualTo(Long value) {
            addCriterion("from_player_super_id <=", value, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdIn(List<Long> values) {
            addCriterion("from_player_super_id in", values, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdNotIn(List<Long> values) {
            addCriterion("from_player_super_id not in", values, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdBetween(Long value1, Long value2) {
            addCriterion("from_player_super_id between", value1, value2, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerSuperIdNotBetween(Long value1, Long value2) {
            addCriterion("from_player_super_id not between", value1, value2, "fromPlayerSuperId");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameIsNull() {
            addCriterion("from_player_name is null");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameIsNotNull() {
            addCriterion("from_player_name is not null");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameEqualTo(String value) {
            addCriterion("from_player_name =", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameNotEqualTo(String value) {
            addCriterion("from_player_name <>", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameGreaterThan(String value) {
            addCriterion("from_player_name >", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameGreaterThanOrEqualTo(String value) {
            addCriterion("from_player_name >=", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameLessThan(String value) {
            addCriterion("from_player_name <", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameLessThanOrEqualTo(String value) {
            addCriterion("from_player_name <=", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameLike(String value) {
            addCriterion("from_player_name like", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameNotLike(String value) {
            addCriterion("from_player_name not like", value, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameIn(List<String> values) {
            addCriterion("from_player_name in", values, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameNotIn(List<String> values) {
            addCriterion("from_player_name not in", values, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameBetween(String value1, String value2) {
            addCriterion("from_player_name between", value1, value2, "fromPlayerName");
            return (Criteria) this;
        }

        public Criteria andFromPlayerNameNotBetween(String value1, String value2) {
            addCriterion("from_player_name not between", value1, value2, "fromPlayerName");
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNull() {
            addCriterion("is_read is null");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNotNull() {
            addCriterion("is_read is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadEqualTo(Boolean value) {
            addCriterion("is_read =", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotEqualTo(Boolean value) {
            addCriterion("is_read <>", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThan(Boolean value) {
            addCriterion("is_read >", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_read >=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThan(Boolean value) {
            addCriterion("is_read <", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThanOrEqualTo(Boolean value) {
            addCriterion("is_read <=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadIn(List<Boolean> values) {
            addCriterion("is_read in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotIn(List<Boolean> values) {
            addCriterion("is_read not in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadBetween(Boolean value1, Boolean value2) {
            addCriterion("is_read between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_read not between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldIsNull() {
            addCriterion("is_rec_gold is null");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldIsNotNull() {
            addCriterion("is_rec_gold is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldEqualTo(Boolean value) {
            addCriterion("is_rec_gold =", value, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldNotEqualTo(Boolean value) {
            addCriterion("is_rec_gold <>", value, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldGreaterThan(Boolean value) {
            addCriterion("is_rec_gold >", value, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_rec_gold >=", value, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldLessThan(Boolean value) {
            addCriterion("is_rec_gold <", value, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldLessThanOrEqualTo(Boolean value) {
            addCriterion("is_rec_gold <=", value, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldIn(List<Boolean> values) {
            addCriterion("is_rec_gold in", values, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldNotIn(List<Boolean> values) {
            addCriterion("is_rec_gold not in", values, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldBetween(Boolean value1, Boolean value2) {
            addCriterion("is_rec_gold between", value1, value2, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andIsRecGoldNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_rec_gold not between", value1, value2, "isRecGold");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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