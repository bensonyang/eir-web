package com.morningsidevc.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public FeedInfoExample() {
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

        public Criteria andMsgidIsNull() {
            addCriterion("MsgId is null");
            return (Criteria) this;
        }

        public Criteria andMsgidIsNotNull() {
            addCriterion("MsgId is not null");
            return (Criteria) this;
        }

        public Criteria andMsgidEqualTo(Integer value) {
            addCriterion("MsgId =", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotEqualTo(Integer value) {
            addCriterion("MsgId <>", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidGreaterThan(Integer value) {
            addCriterion("MsgId >", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidGreaterThanOrEqualTo(Integer value) {
            addCriterion("MsgId >=", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidLessThan(Integer value) {
            addCriterion("MsgId <", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidLessThanOrEqualTo(Integer value) {
            addCriterion("MsgId <=", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidIn(List<Integer> values) {
            addCriterion("MsgId in", values, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotIn(List<Integer> values) {
            addCriterion("MsgId not in", values, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidBetween(Integer value1, Integer value2) {
            addCriterion("MsgId between", value1, value2, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotBetween(Integer value1, Integer value2) {
            addCriterion("MsgId not between", value1, value2, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgtypeIsNull() {
            addCriterion("MsgType is null");
            return (Criteria) this;
        }

        public Criteria andMsgtypeIsNotNull() {
            addCriterion("MsgType is not null");
            return (Criteria) this;
        }

        public Criteria andMsgtypeEqualTo(Byte value) {
            addCriterion("MsgType =", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeNotEqualTo(Byte value) {
            addCriterion("MsgType <>", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeGreaterThan(Byte value) {
            addCriterion("MsgType >", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("MsgType >=", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeLessThan(Byte value) {
            addCriterion("MsgType <", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeLessThanOrEqualTo(Byte value) {
            addCriterion("MsgType <=", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeIn(List<Byte> values) {
            addCriterion("MsgType in", values, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeNotIn(List<Byte> values) {
            addCriterion("MsgType not in", values, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeBetween(Byte value1, Byte value2) {
            addCriterion("MsgType between", value1, value2, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeNotBetween(Byte value1, Byte value2) {
            addCriterion("MsgType not between", value1, value2, "msgtype");
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

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andLikecountIsNull() {
            addCriterion("LikeCount is null");
            return (Criteria) this;
        }

        public Criteria andLikecountIsNotNull() {
            addCriterion("LikeCount is not null");
            return (Criteria) this;
        }

        public Criteria andLikecountEqualTo(Integer value) {
            addCriterion("LikeCount =", value, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountNotEqualTo(Integer value) {
            addCriterion("LikeCount <>", value, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountGreaterThan(Integer value) {
            addCriterion("LikeCount >", value, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountGreaterThanOrEqualTo(Integer value) {
            addCriterion("LikeCount >=", value, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountLessThan(Integer value) {
            addCriterion("LikeCount <", value, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountLessThanOrEqualTo(Integer value) {
            addCriterion("LikeCount <=", value, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountIn(List<Integer> values) {
            addCriterion("LikeCount in", values, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountNotIn(List<Integer> values) {
            addCriterion("LikeCount not in", values, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountBetween(Integer value1, Integer value2) {
            addCriterion("LikeCount between", value1, value2, "likecount");
            return (Criteria) this;
        }

        public Criteria andLikecountNotBetween(Integer value1, Integer value2) {
            addCriterion("LikeCount not between", value1, value2, "likecount");
            return (Criteria) this;
        }

        public Criteria andCommentcountIsNull() {
            addCriterion("CommentCount is null");
            return (Criteria) this;
        }

        public Criteria andCommentcountIsNotNull() {
            addCriterion("CommentCount is not null");
            return (Criteria) this;
        }

        public Criteria andCommentcountEqualTo(Integer value) {
            addCriterion("CommentCount =", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotEqualTo(Integer value) {
            addCriterion("CommentCount <>", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThan(Integer value) {
            addCriterion("CommentCount >", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("CommentCount >=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThan(Integer value) {
            addCriterion("CommentCount <", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThanOrEqualTo(Integer value) {
            addCriterion("CommentCount <=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountIn(List<Integer> values) {
            addCriterion("CommentCount in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotIn(List<Integer> values) {
            addCriterion("CommentCount not in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountBetween(Integer value1, Integer value2) {
            addCriterion("CommentCount between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotBetween(Integer value1, Integer value2) {
            addCriterion("CommentCount not between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("Tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("Tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("Tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("Tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("Tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("Tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("Tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("Tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("Tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("Tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("Tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("Tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("Tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("Tag not between", value1, value2, "tag");
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

        public Criteria andLasttimeIsNull() {
            addCriterion("LastTime is null");
            return (Criteria) this;
        }

        public Criteria andLasttimeIsNotNull() {
            addCriterion("LastTime is not null");
            return (Criteria) this;
        }

        public Criteria andLasttimeEqualTo(Date value) {
            addCriterion("LastTime =", value, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeNotEqualTo(Date value) {
            addCriterion("LastTime <>", value, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeGreaterThan(Date value) {
            addCriterion("LastTime >", value, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LastTime >=", value, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeLessThan(Date value) {
            addCriterion("LastTime <", value, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeLessThanOrEqualTo(Date value) {
            addCriterion("LastTime <=", value, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeIn(List<Date> values) {
            addCriterion("LastTime in", values, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeNotIn(List<Date> values) {
            addCriterion("LastTime not in", values, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeBetween(Date value1, Date value2) {
            addCriterion("LastTime between", value1, value2, "lasttime");
            return (Criteria) this;
        }

        public Criteria andLasttimeNotBetween(Date value1, Date value2) {
            addCriterion("LastTime not between", value1, value2, "lasttime");
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