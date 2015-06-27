package com.morningsidevc.po.gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedLikeMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public FeedLikeMsgExample() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
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

        public Criteria andLikeidIsNull() {
            addCriterion("LikeId is null");
            return (Criteria) this;
        }

        public Criteria andLikeidIsNotNull() {
            addCriterion("LikeId is not null");
            return (Criteria) this;
        }

        public Criteria andLikeidEqualTo(Integer value) {
            addCriterion("LikeId =", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidNotEqualTo(Integer value) {
            addCriterion("LikeId <>", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidGreaterThan(Integer value) {
            addCriterion("LikeId >", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("LikeId >=", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidLessThan(Integer value) {
            addCriterion("LikeId <", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidLessThanOrEqualTo(Integer value) {
            addCriterion("LikeId <=", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidIn(List<Integer> values) {
            addCriterion("LikeId in", values, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidNotIn(List<Integer> values) {
            addCriterion("LikeId not in", values, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidBetween(Integer value1, Integer value2) {
            addCriterion("LikeId between", value1, value2, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidNotBetween(Integer value1, Integer value2) {
            addCriterion("LikeId not between", value1, value2, "likeid");
            return (Criteria) this;
        }

        public Criteria andFeedidIsNull() {
            addCriterion("FeedId is null");
            return (Criteria) this;
        }

        public Criteria andFeedidIsNotNull() {
            addCriterion("FeedId is not null");
            return (Criteria) this;
        }

        public Criteria andFeedidEqualTo(Integer value) {
            addCriterion("FeedId =", value, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidNotEqualTo(Integer value) {
            addCriterion("FeedId <>", value, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidGreaterThan(Integer value) {
            addCriterion("FeedId >", value, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidGreaterThanOrEqualTo(Integer value) {
            addCriterion("FeedId >=", value, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidLessThan(Integer value) {
            addCriterion("FeedId <", value, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidLessThanOrEqualTo(Integer value) {
            addCriterion("FeedId <=", value, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidIn(List<Integer> values) {
            addCriterion("FeedId in", values, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidNotIn(List<Integer> values) {
            addCriterion("FeedId not in", values, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidBetween(Integer value1, Integer value2) {
            addCriterion("FeedId between", value1, value2, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeedidNotBetween(Integer value1, Integer value2) {
            addCriterion("FeedId not between", value1, value2, "feedid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridIsNull() {
            addCriterion("FeedUserId is null");
            return (Criteria) this;
        }

        public Criteria andFeeduseridIsNotNull() {
            addCriterion("FeedUserId is not null");
            return (Criteria) this;
        }

        public Criteria andFeeduseridEqualTo(Integer value) {
            addCriterion("FeedUserId =", value, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridNotEqualTo(Integer value) {
            addCriterion("FeedUserId <>", value, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridGreaterThan(Integer value) {
            addCriterion("FeedUserId >", value, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("FeedUserId >=", value, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridLessThan(Integer value) {
            addCriterion("FeedUserId <", value, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridLessThanOrEqualTo(Integer value) {
            addCriterion("FeedUserId <=", value, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridIn(List<Integer> values) {
            addCriterion("FeedUserId in", values, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridNotIn(List<Integer> values) {
            addCriterion("FeedUserId not in", values, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridBetween(Integer value1, Integer value2) {
            addCriterion("FeedUserId between", value1, value2, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andFeeduseridNotBetween(Integer value1, Integer value2) {
            addCriterion("FeedUserId not between", value1, value2, "feeduserid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("UserId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("UserId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("UserId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("UserId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("UserId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("UserId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("UserId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("UserId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("UserId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("AddTime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("AddTime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("AddTime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("AddTime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("AddTime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AddTime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("AddTime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("AddTime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("AddTime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("AddTime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("AddTime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("AddTime not between", value1, value2, "addtime");
            return (Criteria) this;
        }
    }

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