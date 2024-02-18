package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class RobotConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public RobotConfigExample() {
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

        public Criteria andGameIsNull() {
            addCriterion("game is null");
            return (Criteria) this;
        }

        public Criteria andGameIsNotNull() {
            addCriterion("game is not null");
            return (Criteria) this;
        }

        public Criteria andGameEqualTo(Integer value) {
            addCriterion("game =", value, "game");
            return (Criteria) this;
        }

        public Criteria andGameNotEqualTo(Integer value) {
            addCriterion("game <>", value, "game");
            return (Criteria) this;
        }

        public Criteria andGameGreaterThan(Integer value) {
            addCriterion("game >", value, "game");
            return (Criteria) this;
        }

        public Criteria andGameGreaterThanOrEqualTo(Integer value) {
            addCriterion("game >=", value, "game");
            return (Criteria) this;
        }

        public Criteria andGameLessThan(Integer value) {
            addCriterion("game <", value, "game");
            return (Criteria) this;
        }

        public Criteria andGameLessThanOrEqualTo(Integer value) {
            addCriterion("game <=", value, "game");
            return (Criteria) this;
        }

        public Criteria andGameIn(List<Integer> values) {
            addCriterion("game in", values, "game");
            return (Criteria) this;
        }

        public Criteria andGameNotIn(List<Integer> values) {
            addCriterion("game not in", values, "game");
            return (Criteria) this;
        }

        public Criteria andGameBetween(Integer value1, Integer value2) {
            addCriterion("game between", value1, value2, "game");
            return (Criteria) this;
        }

        public Criteria andGameNotBetween(Integer value1, Integer value2) {
            addCriterion("game not between", value1, value2, "game");
            return (Criteria) this;
        }

        public Criteria andRoomIsNull() {
            addCriterion("room is null");
            return (Criteria) this;
        }

        public Criteria andRoomIsNotNull() {
            addCriterion("room is not null");
            return (Criteria) this;
        }

        public Criteria andRoomEqualTo(Integer value) {
            addCriterion("room =", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotEqualTo(Integer value) {
            addCriterion("room <>", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThan(Integer value) {
            addCriterion("room >", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThanOrEqualTo(Integer value) {
            addCriterion("room >=", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomLessThan(Integer value) {
            addCriterion("room <", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomLessThanOrEqualTo(Integer value) {
            addCriterion("room <=", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomIn(List<Integer> values) {
            addCriterion("room in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotIn(List<Integer> values) {
            addCriterion("room not in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomBetween(Integer value1, Integer value2) {
            addCriterion("room between", value1, value2, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotBetween(Integer value1, Integer value2) {
            addCriterion("room not between", value1, value2, "room");
            return (Criteria) this;
        }

        public Criteria andOccupytableIsNull() {
            addCriterion("occupyTable is null");
            return (Criteria) this;
        }

        public Criteria andOccupytableIsNotNull() {
            addCriterion("occupyTable is not null");
            return (Criteria) this;
        }

        public Criteria andOccupytableEqualTo(Byte value) {
            addCriterion("occupyTable =", value, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableNotEqualTo(Byte value) {
            addCriterion("occupyTable <>", value, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableGreaterThan(Byte value) {
            addCriterion("occupyTable >", value, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableGreaterThanOrEqualTo(Byte value) {
            addCriterion("occupyTable >=", value, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableLessThan(Byte value) {
            addCriterion("occupyTable <", value, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableLessThanOrEqualTo(Byte value) {
            addCriterion("occupyTable <=", value, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableIn(List<Byte> values) {
            addCriterion("occupyTable in", values, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableNotIn(List<Byte> values) {
            addCriterion("occupyTable not in", values, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableBetween(Byte value1, Byte value2) {
            addCriterion("occupyTable between", value1, value2, "occupytable");
            return (Criteria) this;
        }

        public Criteria andOccupytableNotBetween(Byte value1, Byte value2) {
            addCriterion("occupyTable not between", value1, value2, "occupytable");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerIsNull() {
            addCriterion("gameWithPlayer is null");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerIsNotNull() {
            addCriterion("gameWithPlayer is not null");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerEqualTo(Byte value) {
            addCriterion("gameWithPlayer =", value, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerNotEqualTo(Byte value) {
            addCriterion("gameWithPlayer <>", value, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerGreaterThan(Byte value) {
            addCriterion("gameWithPlayer >", value, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerGreaterThanOrEqualTo(Byte value) {
            addCriterion("gameWithPlayer >=", value, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerLessThan(Byte value) {
            addCriterion("gameWithPlayer <", value, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerLessThanOrEqualTo(Byte value) {
            addCriterion("gameWithPlayer <=", value, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerIn(List<Byte> values) {
            addCriterion("gameWithPlayer in", values, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerNotIn(List<Byte> values) {
            addCriterion("gameWithPlayer not in", values, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerBetween(Byte value1, Byte value2) {
            addCriterion("gameWithPlayer between", value1, value2, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGamewithplayerNotBetween(Byte value1, Byte value2) {
            addCriterion("gameWithPlayer not between", value1, value2, "gamewithplayer");
            return (Criteria) this;
        }

        public Criteria andGoldlowerIsNull() {
            addCriterion("goldLower is null");
            return (Criteria) this;
        }

        public Criteria andGoldlowerIsNotNull() {
            addCriterion("goldLower is not null");
            return (Criteria) this;
        }

        public Criteria andGoldlowerEqualTo(Long value) {
            addCriterion("goldLower =", value, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerNotEqualTo(Long value) {
            addCriterion("goldLower <>", value, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerGreaterThan(Long value) {
            addCriterion("goldLower >", value, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerGreaterThanOrEqualTo(Long value) {
            addCriterion("goldLower >=", value, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerLessThan(Long value) {
            addCriterion("goldLower <", value, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerLessThanOrEqualTo(Long value) {
            addCriterion("goldLower <=", value, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerIn(List<Long> values) {
            addCriterion("goldLower in", values, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerNotIn(List<Long> values) {
            addCriterion("goldLower not in", values, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerBetween(Long value1, Long value2) {
            addCriterion("goldLower between", value1, value2, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldlowerNotBetween(Long value1, Long value2) {
            addCriterion("goldLower not between", value1, value2, "goldlower");
            return (Criteria) this;
        }

        public Criteria andGoldupperIsNull() {
            addCriterion("goldUpper is null");
            return (Criteria) this;
        }

        public Criteria andGoldupperIsNotNull() {
            addCriterion("goldUpper is not null");
            return (Criteria) this;
        }

        public Criteria andGoldupperEqualTo(Long value) {
            addCriterion("goldUpper =", value, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperNotEqualTo(Long value) {
            addCriterion("goldUpper <>", value, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperGreaterThan(Long value) {
            addCriterion("goldUpper >", value, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperGreaterThanOrEqualTo(Long value) {
            addCriterion("goldUpper >=", value, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperLessThan(Long value) {
            addCriterion("goldUpper <", value, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperLessThanOrEqualTo(Long value) {
            addCriterion("goldUpper <=", value, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperIn(List<Long> values) {
            addCriterion("goldUpper in", values, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperNotIn(List<Long> values) {
            addCriterion("goldUpper not in", values, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperBetween(Long value1, Long value2) {
            addCriterion("goldUpper between", value1, value2, "goldupper");
            return (Criteria) this;
        }

        public Criteria andGoldupperNotBetween(Long value1, Long value2) {
            addCriterion("goldUpper not between", value1, value2, "goldupper");
            return (Criteria) this;
        }

        public Criteria andMaxgoldIsNull() {
            addCriterion("maxGold is null");
            return (Criteria) this;
        }

        public Criteria andMaxgoldIsNotNull() {
            addCriterion("maxGold is not null");
            return (Criteria) this;
        }

        public Criteria andMaxgoldEqualTo(Long value) {
            addCriterion("maxGold =", value, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldNotEqualTo(Long value) {
            addCriterion("maxGold <>", value, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldGreaterThan(Long value) {
            addCriterion("maxGold >", value, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldGreaterThanOrEqualTo(Long value) {
            addCriterion("maxGold >=", value, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldLessThan(Long value) {
            addCriterion("maxGold <", value, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldLessThanOrEqualTo(Long value) {
            addCriterion("maxGold <=", value, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldIn(List<Long> values) {
            addCriterion("maxGold in", values, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldNotIn(List<Long> values) {
            addCriterion("maxGold not in", values, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldBetween(Long value1, Long value2) {
            addCriterion("maxGold between", value1, value2, "maxgold");
            return (Criteria) this;
        }

        public Criteria andMaxgoldNotBetween(Long value1, Long value2) {
            addCriterion("maxGold not between", value1, value2, "maxgold");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerIsNull() {
            addCriterion("tableGameLower is null");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerIsNotNull() {
            addCriterion("tableGameLower is not null");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerEqualTo(Integer value) {
            addCriterion("tableGameLower =", value, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerNotEqualTo(Integer value) {
            addCriterion("tableGameLower <>", value, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerGreaterThan(Integer value) {
            addCriterion("tableGameLower >", value, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("tableGameLower >=", value, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerLessThan(Integer value) {
            addCriterion("tableGameLower <", value, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerLessThanOrEqualTo(Integer value) {
            addCriterion("tableGameLower <=", value, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerIn(List<Integer> values) {
            addCriterion("tableGameLower in", values, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerNotIn(List<Integer> values) {
            addCriterion("tableGameLower not in", values, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerBetween(Integer value1, Integer value2) {
            addCriterion("tableGameLower between", value1, value2, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegamelowerNotBetween(Integer value1, Integer value2) {
            addCriterion("tableGameLower not between", value1, value2, "tablegamelower");
            return (Criteria) this;
        }

        public Criteria andTablegameupperIsNull() {
            addCriterion("tableGameUpper is null");
            return (Criteria) this;
        }

        public Criteria andTablegameupperIsNotNull() {
            addCriterion("tableGameUpper is not null");
            return (Criteria) this;
        }

        public Criteria andTablegameupperEqualTo(Integer value) {
            addCriterion("tableGameUpper =", value, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperNotEqualTo(Integer value) {
            addCriterion("tableGameUpper <>", value, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperGreaterThan(Integer value) {
            addCriterion("tableGameUpper >", value, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperGreaterThanOrEqualTo(Integer value) {
            addCriterion("tableGameUpper >=", value, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperLessThan(Integer value) {
            addCriterion("tableGameUpper <", value, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperLessThanOrEqualTo(Integer value) {
            addCriterion("tableGameUpper <=", value, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperIn(List<Integer> values) {
            addCriterion("tableGameUpper in", values, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperNotIn(List<Integer> values) {
            addCriterion("tableGameUpper not in", values, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperBetween(Integer value1, Integer value2) {
            addCriterion("tableGameUpper between", value1, value2, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTablegameupperNotBetween(Integer value1, Integer value2) {
            addCriterion("tableGameUpper not between", value1, value2, "tablegameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerIsNull() {
            addCriterion("totalGameLower is null");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerIsNotNull() {
            addCriterion("totalGameLower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerEqualTo(Integer value) {
            addCriterion("totalGameLower =", value, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerNotEqualTo(Integer value) {
            addCriterion("totalGameLower <>", value, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerGreaterThan(Integer value) {
            addCriterion("totalGameLower >", value, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalGameLower >=", value, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerLessThan(Integer value) {
            addCriterion("totalGameLower <", value, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerLessThanOrEqualTo(Integer value) {
            addCriterion("totalGameLower <=", value, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerIn(List<Integer> values) {
            addCriterion("totalGameLower in", values, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerNotIn(List<Integer> values) {
            addCriterion("totalGameLower not in", values, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerBetween(Integer value1, Integer value2) {
            addCriterion("totalGameLower between", value1, value2, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgamelowerNotBetween(Integer value1, Integer value2) {
            addCriterion("totalGameLower not between", value1, value2, "totalgamelower");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperIsNull() {
            addCriterion("totalGameUpper is null");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperIsNotNull() {
            addCriterion("totalGameUpper is not null");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperEqualTo(Integer value) {
            addCriterion("totalGameUpper =", value, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperNotEqualTo(Integer value) {
            addCriterion("totalGameUpper <>", value, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperGreaterThan(Integer value) {
            addCriterion("totalGameUpper >", value, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalGameUpper >=", value, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperLessThan(Integer value) {
            addCriterion("totalGameUpper <", value, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperLessThanOrEqualTo(Integer value) {
            addCriterion("totalGameUpper <=", value, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperIn(List<Integer> values) {
            addCriterion("totalGameUpper in", values, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperNotIn(List<Integer> values) {
            addCriterion("totalGameUpper not in", values, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperBetween(Integer value1, Integer value2) {
            addCriterion("totalGameUpper between", value1, value2, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andTotalgameupperNotBetween(Integer value1, Integer value2) {
            addCriterion("totalGameUpper not between", value1, value2, "totalgameupper");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsIsNull() {
            addCriterion("beBankerChips is null");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsIsNotNull() {
            addCriterion("beBankerChips is not null");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsEqualTo(Long value) {
            addCriterion("beBankerChips =", value, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsNotEqualTo(Long value) {
            addCriterion("beBankerChips <>", value, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsGreaterThan(Long value) {
            addCriterion("beBankerChips >", value, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsGreaterThanOrEqualTo(Long value) {
            addCriterion("beBankerChips >=", value, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsLessThan(Long value) {
            addCriterion("beBankerChips <", value, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsLessThanOrEqualTo(Long value) {
            addCriterion("beBankerChips <=", value, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsIn(List<Long> values) {
            addCriterion("beBankerChips in", values, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsNotIn(List<Long> values) {
            addCriterion("beBankerChips not in", values, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsBetween(Long value1, Long value2) {
            addCriterion("beBankerChips between", value1, value2, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBebankerchipsNotBetween(Long value1, Long value2) {
            addCriterion("beBankerChips not between", value1, value2, "bebankerchips");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsIsNull() {
            addCriterion("bankerApplyNums is null");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsIsNotNull() {
            addCriterion("bankerApplyNums is not null");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsEqualTo(Byte value) {
            addCriterion("bankerApplyNums =", value, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsNotEqualTo(Byte value) {
            addCriterion("bankerApplyNums <>", value, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsGreaterThan(Byte value) {
            addCriterion("bankerApplyNums >", value, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsGreaterThanOrEqualTo(Byte value) {
            addCriterion("bankerApplyNums >=", value, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsLessThan(Byte value) {
            addCriterion("bankerApplyNums <", value, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsLessThanOrEqualTo(Byte value) {
            addCriterion("bankerApplyNums <=", value, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsIn(List<Byte> values) {
            addCriterion("bankerApplyNums in", values, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsNotIn(List<Byte> values) {
            addCriterion("bankerApplyNums not in", values, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsBetween(Byte value1, Byte value2) {
            addCriterion("bankerApplyNums between", value1, value2, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andBankerapplynumsNotBetween(Byte value1, Byte value2) {
            addCriterion("bankerApplyNums not between", value1, value2, "bankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsIsNull() {
            addCriterion("robotBankerApplyNums is null");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsIsNotNull() {
            addCriterion("robotBankerApplyNums is not null");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsEqualTo(Byte value) {
            addCriterion("robotBankerApplyNums =", value, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsNotEqualTo(Byte value) {
            addCriterion("robotBankerApplyNums <>", value, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsGreaterThan(Byte value) {
            addCriterion("robotBankerApplyNums >", value, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsGreaterThanOrEqualTo(Byte value) {
            addCriterion("robotBankerApplyNums >=", value, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsLessThan(Byte value) {
            addCriterion("robotBankerApplyNums <", value, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsLessThanOrEqualTo(Byte value) {
            addCriterion("robotBankerApplyNums <=", value, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsIn(List<Byte> values) {
            addCriterion("robotBankerApplyNums in", values, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsNotIn(List<Byte> values) {
            addCriterion("robotBankerApplyNums not in", values, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsBetween(Byte value1, Byte value2) {
            addCriterion("robotBankerApplyNums between", value1, value2, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andRobotbankerapplynumsNotBetween(Byte value1, Byte value2) {
            addCriterion("robotBankerApplyNums not between", value1, value2, "robotbankerapplynums");
            return (Criteria) this;
        }

        public Criteria andTime1playersIsNull() {
            addCriterion("time1Players is null");
            return (Criteria) this;
        }

        public Criteria andTime1playersIsNotNull() {
            addCriterion("time1Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime1playersEqualTo(Integer value) {
            addCriterion("time1Players =", value, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersNotEqualTo(Integer value) {
            addCriterion("time1Players <>", value, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersGreaterThan(Integer value) {
            addCriterion("time1Players >", value, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time1Players >=", value, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersLessThan(Integer value) {
            addCriterion("time1Players <", value, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersLessThanOrEqualTo(Integer value) {
            addCriterion("time1Players <=", value, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersIn(List<Integer> values) {
            addCriterion("time1Players in", values, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersNotIn(List<Integer> values) {
            addCriterion("time1Players not in", values, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersBetween(Integer value1, Integer value2) {
            addCriterion("time1Players between", value1, value2, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime1playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time1Players not between", value1, value2, "time1players");
            return (Criteria) this;
        }

        public Criteria andTime2playersIsNull() {
            addCriterion("time2Players is null");
            return (Criteria) this;
        }

        public Criteria andTime2playersIsNotNull() {
            addCriterion("time2Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime2playersEqualTo(Integer value) {
            addCriterion("time2Players =", value, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersNotEqualTo(Integer value) {
            addCriterion("time2Players <>", value, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersGreaterThan(Integer value) {
            addCriterion("time2Players >", value, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time2Players >=", value, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersLessThan(Integer value) {
            addCriterion("time2Players <", value, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersLessThanOrEqualTo(Integer value) {
            addCriterion("time2Players <=", value, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersIn(List<Integer> values) {
            addCriterion("time2Players in", values, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersNotIn(List<Integer> values) {
            addCriterion("time2Players not in", values, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersBetween(Integer value1, Integer value2) {
            addCriterion("time2Players between", value1, value2, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime2playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time2Players not between", value1, value2, "time2players");
            return (Criteria) this;
        }

        public Criteria andTime3playersIsNull() {
            addCriterion("time3Players is null");
            return (Criteria) this;
        }

        public Criteria andTime3playersIsNotNull() {
            addCriterion("time3Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime3playersEqualTo(Integer value) {
            addCriterion("time3Players =", value, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersNotEqualTo(Integer value) {
            addCriterion("time3Players <>", value, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersGreaterThan(Integer value) {
            addCriterion("time3Players >", value, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time3Players >=", value, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersLessThan(Integer value) {
            addCriterion("time3Players <", value, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersLessThanOrEqualTo(Integer value) {
            addCriterion("time3Players <=", value, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersIn(List<Integer> values) {
            addCriterion("time3Players in", values, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersNotIn(List<Integer> values) {
            addCriterion("time3Players not in", values, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersBetween(Integer value1, Integer value2) {
            addCriterion("time3Players between", value1, value2, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime3playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time3Players not between", value1, value2, "time3players");
            return (Criteria) this;
        }

        public Criteria andTime4playersIsNull() {
            addCriterion("time4Players is null");
            return (Criteria) this;
        }

        public Criteria andTime4playersIsNotNull() {
            addCriterion("time4Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime4playersEqualTo(Integer value) {
            addCriterion("time4Players =", value, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersNotEqualTo(Integer value) {
            addCriterion("time4Players <>", value, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersGreaterThan(Integer value) {
            addCriterion("time4Players >", value, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time4Players >=", value, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersLessThan(Integer value) {
            addCriterion("time4Players <", value, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersLessThanOrEqualTo(Integer value) {
            addCriterion("time4Players <=", value, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersIn(List<Integer> values) {
            addCriterion("time4Players in", values, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersNotIn(List<Integer> values) {
            addCriterion("time4Players not in", values, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersBetween(Integer value1, Integer value2) {
            addCriterion("time4Players between", value1, value2, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime4playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time4Players not between", value1, value2, "time4players");
            return (Criteria) this;
        }

        public Criteria andTime5playersIsNull() {
            addCriterion("time5Players is null");
            return (Criteria) this;
        }

        public Criteria andTime5playersIsNotNull() {
            addCriterion("time5Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime5playersEqualTo(Integer value) {
            addCriterion("time5Players =", value, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersNotEqualTo(Integer value) {
            addCriterion("time5Players <>", value, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersGreaterThan(Integer value) {
            addCriterion("time5Players >", value, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time5Players >=", value, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersLessThan(Integer value) {
            addCriterion("time5Players <", value, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersLessThanOrEqualTo(Integer value) {
            addCriterion("time5Players <=", value, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersIn(List<Integer> values) {
            addCriterion("time5Players in", values, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersNotIn(List<Integer> values) {
            addCriterion("time5Players not in", values, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersBetween(Integer value1, Integer value2) {
            addCriterion("time5Players between", value1, value2, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime5playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time5Players not between", value1, value2, "time5players");
            return (Criteria) this;
        }

        public Criteria andTime6playersIsNull() {
            addCriterion("time6Players is null");
            return (Criteria) this;
        }

        public Criteria andTime6playersIsNotNull() {
            addCriterion("time6Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime6playersEqualTo(Integer value) {
            addCriterion("time6Players =", value, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersNotEqualTo(Integer value) {
            addCriterion("time6Players <>", value, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersGreaterThan(Integer value) {
            addCriterion("time6Players >", value, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time6Players >=", value, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersLessThan(Integer value) {
            addCriterion("time6Players <", value, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersLessThanOrEqualTo(Integer value) {
            addCriterion("time6Players <=", value, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersIn(List<Integer> values) {
            addCriterion("time6Players in", values, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersNotIn(List<Integer> values) {
            addCriterion("time6Players not in", values, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersBetween(Integer value1, Integer value2) {
            addCriterion("time6Players between", value1, value2, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime6playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time6Players not between", value1, value2, "time6players");
            return (Criteria) this;
        }

        public Criteria andTime7playersIsNull() {
            addCriterion("time7Players is null");
            return (Criteria) this;
        }

        public Criteria andTime7playersIsNotNull() {
            addCriterion("time7Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime7playersEqualTo(Integer value) {
            addCriterion("time7Players =", value, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersNotEqualTo(Integer value) {
            addCriterion("time7Players <>", value, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersGreaterThan(Integer value) {
            addCriterion("time7Players >", value, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time7Players >=", value, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersLessThan(Integer value) {
            addCriterion("time7Players <", value, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersLessThanOrEqualTo(Integer value) {
            addCriterion("time7Players <=", value, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersIn(List<Integer> values) {
            addCriterion("time7Players in", values, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersNotIn(List<Integer> values) {
            addCriterion("time7Players not in", values, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersBetween(Integer value1, Integer value2) {
            addCriterion("time7Players between", value1, value2, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime7playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time7Players not between", value1, value2, "time7players");
            return (Criteria) this;
        }

        public Criteria andTime8playersIsNull() {
            addCriterion("time8Players is null");
            return (Criteria) this;
        }

        public Criteria andTime8playersIsNotNull() {
            addCriterion("time8Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime8playersEqualTo(Integer value) {
            addCriterion("time8Players =", value, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersNotEqualTo(Integer value) {
            addCriterion("time8Players <>", value, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersGreaterThan(Integer value) {
            addCriterion("time8Players >", value, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time8Players >=", value, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersLessThan(Integer value) {
            addCriterion("time8Players <", value, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersLessThanOrEqualTo(Integer value) {
            addCriterion("time8Players <=", value, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersIn(List<Integer> values) {
            addCriterion("time8Players in", values, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersNotIn(List<Integer> values) {
            addCriterion("time8Players not in", values, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersBetween(Integer value1, Integer value2) {
            addCriterion("time8Players between", value1, value2, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime8playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time8Players not between", value1, value2, "time8players");
            return (Criteria) this;
        }

        public Criteria andTime9playersIsNull() {
            addCriterion("time9Players is null");
            return (Criteria) this;
        }

        public Criteria andTime9playersIsNotNull() {
            addCriterion("time9Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime9playersEqualTo(Integer value) {
            addCriterion("time9Players =", value, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersNotEqualTo(Integer value) {
            addCriterion("time9Players <>", value, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersGreaterThan(Integer value) {
            addCriterion("time9Players >", value, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time9Players >=", value, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersLessThan(Integer value) {
            addCriterion("time9Players <", value, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersLessThanOrEqualTo(Integer value) {
            addCriterion("time9Players <=", value, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersIn(List<Integer> values) {
            addCriterion("time9Players in", values, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersNotIn(List<Integer> values) {
            addCriterion("time9Players not in", values, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersBetween(Integer value1, Integer value2) {
            addCriterion("time9Players between", value1, value2, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime9playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time9Players not between", value1, value2, "time9players");
            return (Criteria) this;
        }

        public Criteria andTime10playersIsNull() {
            addCriterion("time10Players is null");
            return (Criteria) this;
        }

        public Criteria andTime10playersIsNotNull() {
            addCriterion("time10Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime10playersEqualTo(Integer value) {
            addCriterion("time10Players =", value, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersNotEqualTo(Integer value) {
            addCriterion("time10Players <>", value, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersGreaterThan(Integer value) {
            addCriterion("time10Players >", value, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time10Players >=", value, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersLessThan(Integer value) {
            addCriterion("time10Players <", value, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersLessThanOrEqualTo(Integer value) {
            addCriterion("time10Players <=", value, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersIn(List<Integer> values) {
            addCriterion("time10Players in", values, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersNotIn(List<Integer> values) {
            addCriterion("time10Players not in", values, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersBetween(Integer value1, Integer value2) {
            addCriterion("time10Players between", value1, value2, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime10playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time10Players not between", value1, value2, "time10players");
            return (Criteria) this;
        }

        public Criteria andTime11playersIsNull() {
            addCriterion("time11Players is null");
            return (Criteria) this;
        }

        public Criteria andTime11playersIsNotNull() {
            addCriterion("time11Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime11playersEqualTo(Integer value) {
            addCriterion("time11Players =", value, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersNotEqualTo(Integer value) {
            addCriterion("time11Players <>", value, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersGreaterThan(Integer value) {
            addCriterion("time11Players >", value, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time11Players >=", value, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersLessThan(Integer value) {
            addCriterion("time11Players <", value, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersLessThanOrEqualTo(Integer value) {
            addCriterion("time11Players <=", value, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersIn(List<Integer> values) {
            addCriterion("time11Players in", values, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersNotIn(List<Integer> values) {
            addCriterion("time11Players not in", values, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersBetween(Integer value1, Integer value2) {
            addCriterion("time11Players between", value1, value2, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime11playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time11Players not between", value1, value2, "time11players");
            return (Criteria) this;
        }

        public Criteria andTime12playersIsNull() {
            addCriterion("time12Players is null");
            return (Criteria) this;
        }

        public Criteria andTime12playersIsNotNull() {
            addCriterion("time12Players is not null");
            return (Criteria) this;
        }

        public Criteria andTime12playersEqualTo(Integer value) {
            addCriterion("time12Players =", value, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersNotEqualTo(Integer value) {
            addCriterion("time12Players <>", value, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersGreaterThan(Integer value) {
            addCriterion("time12Players >", value, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersGreaterThanOrEqualTo(Integer value) {
            addCriterion("time12Players >=", value, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersLessThan(Integer value) {
            addCriterion("time12Players <", value, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersLessThanOrEqualTo(Integer value) {
            addCriterion("time12Players <=", value, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersIn(List<Integer> values) {
            addCriterion("time12Players in", values, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersNotIn(List<Integer> values) {
            addCriterion("time12Players not in", values, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersBetween(Integer value1, Integer value2) {
            addCriterion("time12Players between", value1, value2, "time12players");
            return (Criteria) this;
        }

        public Criteria andTime12playersNotBetween(Integer value1, Integer value2) {
            addCriterion("time12Players not between", value1, value2, "time12players");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Byte value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Byte value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Byte value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Byte value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Byte value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Byte> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Byte> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Byte value1, Byte value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Byte value1, Byte value2) {
            addCriterion("state not between", value1, value2, "state");
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