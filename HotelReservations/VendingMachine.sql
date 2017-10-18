use Vending_Machine;

create table Items 
(
	itemID int not null auto_increment,
    itemName varchar(45) not null,
    itemPrice decimal(5,2) not null,
    itemInventory int not null,
    primary key (itemID)
);

select * from items;

#INSERT VENDING ITEMS HERE

