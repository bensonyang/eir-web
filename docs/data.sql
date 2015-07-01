TRUNCATE TABLE `EIR`.Account;
TRUNCATE TABLE `EIR`.UserInfo;
TRUNCATE TABLE `EIR`.UserFeedCounter;
TRUNCATE TABLE `EIR`.FeedInfo;
TRUNCATE TABLE `EIR`.WeiboMsg;
TRUNCATE TABLE `EIR`.WebPageMsg;
TRUNCATE TABLE `EIR`.FeedCommentMsg;
TRUNCATE TABLE `EIR`.FeedLikeMsg;
TRUNCATE TABLE `EIR`.TagInfo;

INSERT INTO
`EIR`.Account(`UserId`, `UserEmail`, `UserPassword`, `Status`)
VALUES(123, 'cheng.yang@dianping.com', '123456', 0);

INSERT INTO
`EIR`.UserInfo(`UserId`, `MobileNum`, `NickName`, `RealName`, `Gender`, `JobTitle`, `Company`, `Description`, `ProfileStatus`, `AddTime`, `LastTime`)
VALUES(123, '139****5144', 'Cheng', 'Cheng', 1, 'CEO', 'huhaha.com', '', 1, NOW(), NOW());

INSERT INTO
`EIR`.UserFeedCounter(`CounterId`, `UserId`, `Sum`, `CounterType`)
VALUES(1, 123, 1, 0);

INSERT INTO
`EIR`.UserFeedCounter(`CounterId`, `UserId`, `Sum`, `CounterType`)
VALUES(2, 123, 1, 1);

INSERT INTO 
`EIR`.FeedInfo(`FeedId`, `MsgId`, `MsgType`, `UserId`, `Status`, `LikeCount`, `CommentCount`, `TagName`, `AddTime`, `LastTime`) 
VALUES(1, 10, 0, 123, 0, 2, 4, "互联网金融", NOW(), NOW());

INSERT INTO 
`EIR`.FeedInfo(`FeedId`, `MsgId`, `MsgType`, `UserId`, `Status`, `LikeCount`, `CommentCount`, `TagName`, `AddTime`, `LastTime`) 
VALUES(2, 11, 1, 123, 0, 2, 4, "互联网金融", NOW(), NOW());

INSERT INTO
`EIR`.WeiboMsg(`MsgId`, `Content`)
VALUES(10, '互联网大战一触即发，谁将一统江湖，拭目以待');

INSERT INTO
`EIR`.WebPageMsg(`MsgId`, `Title`, `Description`, `WebPageUrl`)
VALUES(11, 'O2O大战', '互联网大战一触即发，谁将一统江湖，拭目以待', 'http://www.baidu.com');

INSERT INTO
`EIR`.FeedCommentMsg(`CommentId`, `FeedId`, `FeedUserId`, `Content`, `UserId`, `ToUserId`, `Status`, `AddTime`, `LastTime`)
VALUES(1, 1, 123, '赞成', 123, 123, 0, NOW(), NOW());

INSERT INTO
`EIR`.FeedLikeMsg(`LikeId`, `FeedId`, `FeedUserId`, `UserId`, `AddTime`)
VALUES(1, 1, 123, 123, NOW());

INSERT INTO
`EIR`.TagInfo(`TagId`, `TagName`, `Status`, `Order`)
VALUES(1, '互联网金融', 0, 1);


INSERT INTO
`EIR`.TagInfo(`TagId`, `TagName`, `Status`, `Order`)
VALUES(2, 'O2O', 0, 2);

INSERT INTO
`EIR`.TagInfo(`TagId`, `TagName`, `Status`, `Order`)
VALUES(3, '智能硬件', 0, 3);