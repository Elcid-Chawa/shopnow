CREATE TABLE IF NOT EXISTS ADMINS (
  adminid INT PRIMARY KEY auto_increment,
  username VARCHAR(20) NOT NULL,
  salt VARCHAR(10000),
  password VARCHAR(10000) ,
  firstname VARCHAR(20),
  lastname VARCHAR(20),
    USER_ROLE VARCHAR(20) NOT NULL
);

INSERT into ADMINS (username, salt, password, firstname, lastname, USER_ROLE) values ('admin', '', '', 'admin', 'admin', 'ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR(10000),
  password VARCHAR(10000),
  firstname VARCHAR(20),
  lastname VARCHAR(20),
  USER_ROLE VARCHAR(20) NOT NULL
);
INSERT into USERS (username, salt, password, firstname, lastname, USER_ROLE) values ('user', 'MACEF58xLN99OMJ9VdloPQ==', 'dbh/MBoTlNdHkIosG31Ung==', 'user', 'user', 'ROLE_USER');
CREATE TABLE IF NOT EXISTS ITEMS (
    itemid INT PRIMARY KEY auto_increment,
    itemName VARCHAR(100),
    itemDescription varchar (10000),
    price FLOAT,
    imageUrl VARCHAR(100),
    adminid INT,
foreign key (adminid) references ADMINS(adminid)
);

INSERT into ITEMS (itemName, itemDescription, price, imageUrl, adminid) values ('Product 1', 'Description of product', 3.0, 'https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Vertical/12a.jpg', 1);

CREATE TABLE IF NOT EXISTS CART (
    cartid INT PRIMARY KEY auto_increment,
    quantity INT,
    itemid INT,
    userid INT,
foreign key (userid) references USERS(userid)
);

ALTER TABLE CART ADD FOREIGN KEY(itemid) references ITEMS(itemid);
