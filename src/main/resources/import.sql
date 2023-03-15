delete from following_users;
delete from likes;
delete from posts;
delete from users;

insert into Users (id, username, password, the_day, the_month, the_year, email) values (1000, 'user1', 'password1', 1, 1, 1980,'user1@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1001, 'user2', 'password2', 1, 1, 1980, 'user2@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1002, 'user3', 'password3', 1, 1, 1980, 'user3@email.com');

insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (1000, 'TWEET', '2023-03-10T13:03:10.7278844', 50, 'This is tweet #1000', 1000, null, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (2000, 'REPLY', '2023-03-10T13:03:10.7278844', 50, 'This is reply #2000 to tweet #1000.', 1000, 1000, null);
insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, parentPostId, OriginalPostId) values (3000, 'RETWEET', '2023-03-10T13:03:10.7278844', null, null, 1000, null, 1000);

insert into Likes (postId, userId) values (1000, 1001); -- user with id 1001 likes post 1000
insert into Likes (postId, userId) values (2000, 1001);
insert into Likes (postId, userId) values (3000, 1001);
