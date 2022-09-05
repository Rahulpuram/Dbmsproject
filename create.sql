create table Developer (
    Developerid smallint AUTO_INCREMENT,
    Name varchar(40),
    Country varchar(25),
    constraint pk_developer PRIMARY KEY (Developerid)
);
create table Game (
    Gameid smallint AUTO_INCREMENT,
    Name varchar(30),
    Genre varchar(25),
    Releasedate smallint,
    developerid smallint,
    constraint pk_game PRIMARY KEY (Gameid) 
);
create table Customer (
    Customerid smallint AUTO_INCREMENT,
    Name varchar(30),
    Country varchar(25),
    gameid smallint,
    constraint pk_customer PRIMARY KEY (Customerid)
);
create table Distributor(
    Disributorid smallint AUTO_INCREMENT,
    Name varchar(30),
    Password varchar(30),
    constraint pk_distributor PRIMARY KEY (Disributorid)
);
