delete from following_users;
delete from user_posts;
delete from posts_posts;
delete from posts;
delete from users;

insert into Users (username, password, dateOfBirth, email) values ("user1", "1", new DateOfBirth(1,1,2001), new EmailAddress("email1@gmail.com"));
insert into Users (username, password, dateOfBirth, email) values ("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
insert into Users (username, password, dateOfBirth, email) values ("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));
