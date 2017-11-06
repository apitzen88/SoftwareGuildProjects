DROP DATABASE IF EXISTS SuperHeroSightingsTest;

CREATE DATABASE SuperHeroSightingsTest;

USE SuperHeroSightingsTest;



CREATE TABLE Hero
(
	HeroID int not null auto_increment,
    HeroName varchar(25) not null,
    RealName varchar(45),
    Description varchar(45) not null,
    primary key(HeroID)
);

CREATE TABLE SuperPower
(
	SuperPowerID int not null auto_increment,
    Description varchar(45) not null,
    primary key(SuperPowerID)
);

CREATE TABLE HeroSuperPower
(
	HeroID int not null,
    SuperPowerID int not null,
    foreign key(HeroID)
    references Hero(HeroID),
    foreign key(SuperPowerID)
    references SuperPower(SuperPowerID)
);

CREATE TABLE Location
(
	LocationID int not null auto_increment,
    LocationName varchar(45) not null,
    Description varchar(45) not null,
    Address varchar(45),
    City varchar(45) not null,
    Latitude decimal(9,6) not null,
    Longitude decimal(9,6) not null,
    primary key(LocationID)
);

CREATE TABLE Organization
(
	OrganizationID int not null auto_increment,
    OrganizationName varchar(45) not null,
    Description varchar(45) not null,
    Contact varchar(45),
    BaseLocation varchar(45) not null,
    primary key(OrganizationID)
);

CREATE TABLE HeroOrganization
(
	HeroID int not null,
    OrganizationID int not null,
    foreign key(HeroID)
    references Hero(HeroID),
    foreign key Organization(OrganizationID)
    references Organization(OrganizationID)
);

CREATE TABLE Sightings
(
	SightingID int not null auto_increment,
    LocationID int not null,
    SightingDate date not null,
    primary key(SightingID),
    foreign key(LocationID)
    references Location(LocationID)
);

CREATE TABLE HeroSightings
(
	HeroID int not null,
    SightingID int not null,
    foreign key(HeroID)
    references Hero(HeroID),
    foreign key(SightingID)
    references Sightings(SightingID)
);


insert into Hero (HeroID, HeroName, RealName, Description)
values
(1, 'Flash', 'Barry Allen','Really fast red guy'),
(2,'Superman','Clark Kent','Pretty super, bad disguise');

insert into SuperPower (SuperPowerID, Description)
values
(1,'Speed'),(2,'Strength'),(3,'flight');

insert into HeroSuperPower(HeroID, SuperPowerID)
values
(1,1),(2,1),(2,2),(2,3);

insert into Location (LocationID, LocationName, Description, Address, City, Latitude, Longitude)
values
(1,'Upper East Side','Near the coffee shop','123 Street','Central City',12.0001,33.0001),
(2,'Downtown','At the liqour store','369 Getlo Ave.','Metropolis',44.0001,44.0001);

insert into Organization (OrganizationID, OrganizationName, Description, Contact, BaseLocation)
values
(1,'Justice League','A league for justice','Knock twice and ring the doorbell','in space'),
(2,'Bro Force','A force of bros','just scream really loud','underground');

insert into HeroOrganization(HeroID, OrganizationID)
values(1,1),(2,2);

insert into Sightings(SightingID, LocationID, SightingDate)
values(1, 1, '2017-10-19'),(2 ,2 ,'2017-10-20');

insert into HeroSightings(HeroID, SightingID)
values (1,1),(2,2),(1,2);