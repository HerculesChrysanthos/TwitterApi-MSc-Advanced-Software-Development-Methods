delete from following_users;
delete from user_posts;
delete from posts_posts;
delete from posts;
delete from users;

insert into Users (id, username, password, the_day, the_month, the_year, email) values (1000, 'user1', 'password1', 1, 1, 1980,'user1@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1001, 'user2', 'password2', 1, 1, 1980, 'user2@email.com');
insert into Users (id, username, password, the_day, the_month, the_year, email) values (1002, 'user3', 'password3', 1, 1, 1980, 'user3@email.com');

--insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, postId) values (1, 'TWEET', '2023-03-10T13:03:10.7278844', 50, 'This is tweet #1', 1, null);
--insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, postId) values (2, 'TWEET', '2023-03-10T14:03:10.7278844', 50, 'This is tweet #2', 2, null);
--insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, postId) values (3, 'TWEET', '2023-03-10T14:03:10.7278844', 50, 'This is tweet #3', 2, null);

-- insert into Posts (id, postType, createdAt, maxNumOfChars, tweetContent, userId, postId) values (3, 'REPLY', '2023-03-10T14:03:10.7278844', 50, 'This is reply to tweet #1', 2, 1);
-- 2023-03-10T13:03:10.7278844