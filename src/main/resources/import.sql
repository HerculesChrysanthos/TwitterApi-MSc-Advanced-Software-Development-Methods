delete from following_users;
delete from likes;
delete from posts;
delete from users;

insert into Users (id, username, password, the_day, the_month, the_year, email) values (1000, 'user1', 'password1', 1, 1, 1980, 'user1@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1001, 'user2', 'password2', 1, 1, 1981, 'user2@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1002, 'user3', 'password3', 1, 1, 1982, 'user3@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1004, 'user4', 'password4', 1, 1, 1983, 'user4@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1005, 'user5', 'password5', 1, 1, 1984, 'user5@email.com');

insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1000, 'TWEET', '2023-03-10T13:03:10.7278844', 50, 'This is tweet #1000', 1000, null, null); -- user 1 Tweets
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1001, 'TWEET', '2023-03-14T13:03:10.7278844', 50, 'This is tweet #1001', 1000, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1002, 'TWEET', '2023-03-15T13:03:10.7278844', 50, 'This is tweet #1002', 1001, null, null); -- user 2 Tweets
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1003, 'TWEET', '2023-03-10T18:03:10.7278844', 50, 'This is tweet #1003', 1002, null, null); -- user 3 Tweets
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1004, 'TWEET', '2023-03-10T19:03:10.7278844', 50, 'This is tweet #1004', 1002, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1005, 'TWEET', '2023-03-11T14:03:10.7278844', 50, 'This is tweet #1005', 1002, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1006, 'TWEET', '2023-03-12T15:03:10.7278844', 50, 'This is tweet #1006', 1002, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1007, 'TWEET', '2023-03-13T16:03:10.7278844', 50, 'This is tweet #1007', 1002, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1008, 'TWEET', '2023-03-20T13:03:10.7278844', 50, 'This is tweet #1008', 1004, null, null); -- user 4 Tweets
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1009, 'TWEET', '2023-03-20T14:03:10.7278844', 50, 'This is tweet #1009', 1004, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1010, 'TWEET', '2023-03-20T15:03:10.7278844', 50, 'This is tweet #1010', 1004, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1011, 'TWEET', '2023-03-20T16:03:10.7278844', 50, 'This is tweet #1011', 1004, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1012, 'TWEET', '2023-03-20T17:03:10.7278844', 50, 'This is tweet #1012', 1004, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1013, 'TWEET', '2023-03-21T18:03:10.7278844', 50, 'This is tweet #1013', 1005, null, null); -- user 5 Tweets
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1014, 'TWEET', '2023-03-21T19:03:10.7278844', 50, 'This is tweet #1014', 1005, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1015, 'TWEET', '2023-03-22T14:03:10.7278844', 50, 'This is tweet #1015', 1005, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1016, 'TWEET', '2023-03-22T15:03:10.7278844', 50, 'This is tweet #1016', 1005, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1017, 'TWEET', '2023-03-22T16:03:10.7278844', 50, 'This is tweet #1017', 1005, null, null);


insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (2000, 'REPLY', '2023-03-10T13:03:10.7278844', 50, 'This is reply #2000 to tweet #1000.', 1000, 1000, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (3000, 'RETWEET', '2023-03-10T13:03:10.7278844', null, null, 1000, null, 1000);

insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (5000, 'TWEET', '2023-03-19T13:03:10.7278844', 50, 'This is tweet #5000', 1001, null, null);

insert into Likes (postId, userId) values (1000, 1001); -- user 2 likes post 1000
insert into Likes (postId, userId) values (2000, 1001);
insert into Likes (postId, userId) values (3000, 1001);

insert into Following_Users(following, userId) values (1000, 1002); -- user 1 follows user 3
insert into Following_Users(following, userId) values (1000, 1004); -- user 1 follows user 4
insert into Following_Users(following, userId) values (1000, 1005); -- user 1 follows user 5
insert into Following_Users(following, userId) values (1002, 1000);
insert into Following_Users(following, userId) values (1002, 1001);