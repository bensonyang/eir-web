/* 数据库 */
CREATE DATABASE EIR;

/* 用户登录信息 */
DROP TABLE `EIR`.`Account`;
CREATE TABLE IF NOT EXISTS `EIR`.`Account` (
	`UserId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`UserEmail` varchar(100) COMMENT '用户Email地址',
	`UserPassword` varchar(100) COMMENT '用户登录密码',
	`Status` tinyint(3) COMMENT '0-正常 1-非正常',
	PRIMARY KEY (`UserId`),
	UNIQUE `UK_Account_UserEmail`(`UserEmail`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 用户表 */
DROP TABLE `EIR`.`UserInfo`;
CREATE TABLE IF NOT EXISTS `EIR`.`UserInfo` (
	`UserId` int(11) NOT NULL COMMENT 'UserID',
	`MobileNum` varchar(20) COMMENT '用户手机号码',
	`NickName` varchar(100) COMMENT '用户昵称',
	`RealName` varchar(100) COMMENT '用户真实姓名',
	`Gender` tinyint(3) COMMENT '用户性别(1-男, 2-女)',
	`JobTitle` varchar(100) COMMENT '公司职位',
	`Company` varchar(100) COMMENT '公司名称',
	`Description` varchar(100) DEFAULT NULL COMMENT '描述(扩展字段)',
	`ProfileStatus` tinyint(3) DEFAULT 0 COMMENT '资料完善程度(0-未完善,1-完善)',
	`AddTime` datetime NOT NULL COMMENT '创建时间',
	`LastTime` datetime NOT NULL COMMENT '最新更新时间',
	PRIMARY KEY (`UserId`),
	INDEX `IX_UserInfo_Company`(`Company`),
	INDEX `IX_UserInfo_JobTitle`(`JobTitle`),
	INDEX `IX_UserInfo_AddTime`(`AddTime`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 用户计数器表 */
DROP TABLE `EIR`.`UserFeedCounter`;
CREATE TABLE IF NOT EXISTS `EIR`.`UserFeedCounter` (
	`CounterId` int(11) NOT NULL AUTO_INCREMENT COMMENT '计数器ID',
	`UserId` int(11) NOT NULL COMMENT '用户ID',
	`Sum` int(11) NOT NULL COMMENT '计数器总值',
	`CounterType` tinyint(3) NOT NULL COMMENT '计数器类型 0-动态 1-评论',
	PRIMARY KEY (`CounterId`),
	UNIQUE `UK_UserFeedCounter_UserId_CounterType`(`UserId`, `CounterType`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 第三方平台用户表 */
DROP TABLE `EIR`.`WeixinUserInfo`;
CREATE TABLE IF NOT EXISTS `EIR`.`WeixinUserInfo` (
	`Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`UserId` int(11) NOT NULL COMMENT '用户ID',
	`OpenId` varchar(100) NOT NULL COMMENT '微信用户openID',
	`AvatarUrl` varchar(100) COMMENT '微信头像',
	`WeixinUserName` varchar(100) COMMENT '微信用户昵称',
	`Gender` tinyint(3) COMMENT '用户性别(1-男, 2-女)',
	`Province` varchar(10) COMMENT '省份',
	`City` varchar(10) COMMENT '城市',
	`Country` varchar(10) COMMENT '国家',
	`Privilege` varchar(200) COMMENT '用户权限',
	`UnionId` varchar(100) COMMENT '用户统一标识',
	PRIMARY KEY (`Id`),
	UNIQUE `UK_WeixinUserInfo_UserId_OpenId`(`UserId`,`OpenId`),
	INDEX `IX_WeixinUserInfo_UnionId`(`UnionId`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 用户动态表 */
DROP TABLE `EIR`.`FeedInfo`;
CREATE TABLE IF NOT EXISTS `EIR`.`FeedInfo` (
	`FeedId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`MsgId` int(11) NOT NULL COMMENT '消息ID',
	`MsgType` tinyint(3) NOT NULL COMMENT '动态类型（0-短文,1-推荐网址）',
	`UserId` int(11) NOT NULL COMMENT '作者ID',
	`Status` tinyint(3) DEFAULT 0 COMMENT '状态(0-正常,1-删除)',
	`LikeCount` int(11) DEFAULT 0 COMMENT '点赞数',
	`CommentCount` int(11) DEFAULT 0 COMMENT '评论数',
	`TagName` varchar(50) DEFAULT NULL COMMENT '分类标签',
	`AddTime` datetime NOT NULL COMMENT '创建时间',
	`LastTime` datetime NOT NULL COMMENT '最新更新时间',
	PRIMARY KEY (`FeedId`),
	UNIQUE `UK_FeedInfo_MsgId_MsgType`(`MsgId`, `MsgType`),
	INDEX `IX_FeedInfo_UserId`(`UserId`),
	INDEX `IX_FeedInfo_Status`(`Status`),
	INDEX `IX_FeedInfo_TagName`(`TagName`),
	INDEX `IX_FeedInfo_Status_AddTime`(`Status`, `AddTime`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 微博140字动态内容表 */
DROP TABLE `EIR`.`WeiboMsg`;
CREATE TABLE IF NOT EXISTS `EIR`.`WeiboMsg` (
	`MsgId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`Content` varchar(280) NOT NULL COMMENT '140字短文',
	PRIMARY KEY (`MsgId`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 网页地址推荐内容表 */
DROP TABLE `EIR`.`WebPageMsg`;
CREATE TABLE IF NOT EXISTS `EIR`.`WebPageMsg` (
	`MsgId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`Title` varchar(60) NOT NULL COMMENT '30字的标题',
	`Description` varchar(280) NOT NULL COMMENT '140字的标题',
	`WebPageUrl` varchar(300) NOT NULL COMMENT '网页地址http',
	PRIMARY KEY (`MsgId`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 动态评论表 */
DROP TABLE `EIR`.`FeedCommentMsg`;
CREATE TABLE IF NOT EXISTS `EIR`.`FeedCommentMsg` (
	`CommentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`FeedId` int(11) NOT NULL COMMENT '动态ID',
	`FeedUserId` int(11) NOT NULL COMMENT '动态UserID',
	`Content` varchar(280) NOT NULL COMMENT '140字评论',
	`UserId` int(11) COMMENT '作者ID',
	`ToUserId` int(11) COMMENT '被@的作者',
	`Status` tinyint(3) DEFAULT 0 COMMENT '状态(0-正常,1-删除)',
	`AddTime` datetime NOT NULL COMMENT '创建时间',
	`LastTime` datetime NOT NULL COMMENT '最新更新时间',
	PRIMARY KEY (`CommentId`),
	INDEX `IX_FeedCommentMsg_FeedId_Status_AddTime`(`FeedId`, `Status`, `AddTime`),
	INDEX `IX_FeedCommentMsg_FeedId_UserId_Status`(`FeedId`, `UserId`, `Status`),
	INDEX `IX_FeedCommentMsg_UserId_ToUserId_Status`(`UserId`, `ToUserId`, `Status`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 动态点赞表 */
DROP TABLE `EIR`.`FeedLikeMsg`;
CREATE TABLE IF NOT EXISTS `EIR`.`FeedLikeMsg` (
	`LikeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`FeedId` int(11) NOT NULL COMMENT '动态ID',
	`FeedUserId` int(11) NOT NULL COMMENT '动态UserID',
	`UserId` int(11) COMMENT '作者ID',
	`AddTime` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`LikeId`),
	UNIQUE `UK_FeedLikeMsg_FeedId_UserId`(`FeedId`, `UserId`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;

/* 标签表 */
DROP TABLE `EIR`.`TagInfo`;
CREATE TABLE IF NOT EXISTS `EIR`.`TagInfo` (
	`TagId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	`TagName` varchar(50) NOT NULL COMMENT '标签名称',
	`Status` tinyint(3) DEFAULT 0 COMMENT '标签状态(0-正常显示,1-不显示)',
	`Order` int(11) NOT NULL COMMENT '标签排序,数值越小，优先级越高',
	PRIMARY KEY (`TagId`),
	UNIQUE `UK_TagInfo_TagName`(`TagName`),
	INDEX `IX_TagInfo_Order`(`Order`, `Status`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8;




