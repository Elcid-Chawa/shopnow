CREATE TABLE IF NOT EXISTS ADMINS (
  adminid INT PRIMARY KEY auto_increment NOT NULL,
  username VARCHAR(20) NOT NULL,
  salt VARCHAR(10000),
  password VARCHAR(10000) ,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment not null,
  username VARCHAR(20),
  salt VARCHAR(10000),
  password VARCHAR(10000),
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS CART (
    cartid INT PRIMARY KEY auto_increment not null,
    quantity INT,
    itemid INT,
    userid INT,
foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS ITEMS (
    itemid INT PRIMARY KEY auto_increment,
    itemName VARCHAR(100),
    itemDescription varchar (10000),
    price FLOAT,
    itemImage BLOB,
    adminid INT,
foreign key (adminid) references ADMINS(adminid)
);