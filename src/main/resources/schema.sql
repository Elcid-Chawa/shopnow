CREATE TABLE IF NOT EXISTS ADMINS (
  adminid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR(10000),
  password VARCHAR(10000),
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR(10000),
  password VARCHAR(10000),
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS CART (
    cartid INT PRIMARY KEY auto_increment,
    filename VARCHAR(10000),
    contenttype VARCHAR(10000),
    filesize VARCHAR (10000),
    userid INT,
foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS ITEMS (
    itemid INT PRIMARY KEY auto_increment,
    itemname VARCHAR(100),
    price FLOAT,
    itemimage LONGBLOB,
    adminid INT,
foreign key (adminid) references ADMINS(adminid)
);