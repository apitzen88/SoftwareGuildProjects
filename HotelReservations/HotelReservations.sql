drop database if exists HotelReservations;

create database HotelReservations;

use HotelReservations;

create table RoomType
(
	RoomTypeID int not null auto_increment,
    RoomType varchar(45) not null,
    primary key(RoomTypeID)
);

create table Rooms 
(
	RoomID int not null auto_increment,
    RoomNumber int not null,
    RoomFloor int not null,
    RoomTypeID int not null,
    primary key(RoomID),
    foreign key(RoomTypeID)
    references RoomType(RoomTypeID)
);


create table RoomRate
(
	RoomRateID int not null auto_increment,
    RoomRate decimal(10,2) not null,
    RoomTypeID int not null,
    StartDate date not null,
    EndDate date not null,
    primary key(RoomRateID),
    foreign key(RoomTypeID)
    references RoomType(RoomTypeID)
);

create table Amenities
(
	AmenityID int not null auto_increment,
    AmenityDescription varchar(45) not null,
    primary key(AmenityID)
);

create table RoomsAmenities
(
	RoomID int not null,
    AmenityID int not null,
    AmenityCount int not null,
    foreign key(RoomID)
    references Rooms(RoomID),
    foreign key(AmenityID)
    references Amenities(AmenityID)
);

create table Customer
(
	CustomerID int not null auto_increment,
    FirstName varchar(45) not null,
    LastName varchar(45) not null,
    Phone int not null,
    Email varchar(45),
    Address varchar(45),
    primary key(CustomerID)
);

create table Guests
(
	GuestID int not null auto_increment,
	FirstName varchar(45) not null,
    LastName varchar(45),
    Age int not null,
    primary key(GuestID)
);

create table Promotions
(
	PromotionID int not null auto_increment,
    PromotionCode varchar(15) not null,
    PromotionDescription varchar(45) not null,
    DollarDiscount decimal(8,2) not null,
    PercentDiscount decimal(2,2) not null,
    primary key(PromotionID)
);

create table AddOns
(
	AddOnID int not null auto_increment,
    AddOnDescription varchar(45) not null,
    primary key(AddOnID)
);

create table AddOnRate
(
	AddOnRateID int not null auto_increment,
    AddOnID int not null,
    Rate decimal(10,2) not null,
    StartDate date not null,
    EndDate date not null,
    primary key(AddOnRateID),
    foreign key(AddOnID)
    references AddOns(AddOnID)
);

create table Reservation
(
	ReservationID int not null auto_increment,
    CustomerID int not null,
    SubTotal decimal(10,2),
    primary key(ReservationID),
    foreign key (CustomerID)
    references Customer(CustomerID)
);

create table ReservationGuest
(
	ReservationID int not null,
    GuestID int not null,
    foreign key(ReservationID)
    references Reservation(ReservationID),
    foreign key(GuestID)
    references Guests(GuestID)
);

create table ReservationRooms
(
	ReservationRoomsID int not null auto_increment,
    RoomsID int not null,
    ReservationID int not null,
    StartDate date not null,
    EndDate date not null,
    primary key(ReservationRoomsID),
    foreign key(RoomsID)
    references Rooms(RoomID),
    foreign key(ReservationID)
    references Reservation(ReservationID)
);

create table ReservationRoomsAddOns
(
	ReservationRoomsAddOnsID int not null auto_increment,
    AddOnID int not null,
    ReservationRoomsID int not null,
    primary key(ReservationRoomsAddOnsID),
    foreign key(AddOnID)
    references AddOns(AddOnID),
    foreign key (ReservationRoomsID)
    references ReservationRooms(ReservationRoomsID)
);

create table ReservationPromotions
(
    ReservationID int not null,
    PromotionID int not null,
    foreign key (ReservationID)
    references Reservation(ReservationID),
    foreign key (PromotionID)
    references Promotions(PromotionID)
);

create table ReservationDetails
(
	ReservationDetailsID int not null auto_increment,
    ReservationID int not null,
    RoomID int not null,
    AddOn varchar(45),
    AddOnPrice decimal(10,2),
    primary key(ReservationDetailsID),
    foreign key(ReservationID)
    references Reservation(ReservationID)
);

create table Invoice
(
	InvoiceID int not null auto_increment,
    ReservationID int not null,
    CustomerID int not null,
    AddonTotal decimal(12,2),
    Subtotal decimal(12,2),
    DiscountTotal decimal(12,2),
    Tax decimal(12,2),
    GrandTotal decimal(12,2),
    primary key(InvoiceID),
    foreign key(ReservationID)
    references Reservation(ReservationID),
    foreign key (CustomerID)
    references Reservation(ReservationID)
);


#INSERT INTO `RoomType` (RoomTypeID, RoomType) VALUES (1,"King,"),(2,"2 Queens."),(3,"Suite.");
#INSERT INTO Rooms (RoomID, RoomNumber, RoomFloor, RoomTypeID) values(1,101,1,1),(2,202,2,2),(3,303,3,3);
#INSERT INTO 