CREATE TABLE IF NOT EXISTS ADMINS (
  adminid INT PRIMARY KEY auto_increment,
  username VARCHAR(20) NOT NULL,
  salt VARCHAR(10000),
  password VARCHAR(10000) ,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

INSERT into ADMINS (username, salt, password, firstname, lastname) values ('admin', '', '', 'admin', 'admin');

CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR(10000),
  password VARCHAR(10000),
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS ITEMS (
    itemid INT PRIMARY KEY auto_increment,
    itemName VARCHAR(100),
    itemDescription varchar (10000),
    price FLOAT,
    imageUrl VARCHAR(100),
    adminid INT,
foreign key (adminid) references ADMINS(adminid)
);

CREATE TABLE IF NOT EXISTS CART (
    cartid INT PRIMARY KEY auto_increment,
    quantity INT,
    itemid INT,
    userid INT,
foreign key (userid) references USERS(userid)
);

ALTER TABLE CART ADD FOREIGN KEY(itemid) references ITEMS(itemid);

