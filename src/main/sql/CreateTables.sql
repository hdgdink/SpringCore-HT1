Create table Users(
ID Int Primary key,
FirstName Varchar(255),
 LastName Varchar(255),
 Email Varchar(255),
 Pass Varchar(255),
 Balance double(255),
 Role Varchar(25)
);

Create table Tickets(
ID Int Primary key Auto_increment,
UserId Int,
 EventId Int,
AuditoriumId int,
 Date DateTime,
 Seat int(255)
);

Create table Auditoriums(
ID int Primary Key,
Name Varchar(255),
NumberOfSeats int,
RowSize int
);

Create table Events(
ID int Primary Key,
Name Varchar(255),
BasePrice Double,
Rating Varchar(15),
Date DateTime,
AuditoriumId int
);

ALTER TABLE Events
  ADD FOREIGN KEY (AuditoriumId) REFERENCES Auditoriums (ID);
ALTER TABLE Tickets
  ADD FOREIGN KEY (UserId) REFERENCES Users (ID);
ALTER TABLE Tickets
  ADD FOREIGN KEY (EventId) REFERENCES Events (ID);
ALTER TABLE Tickets
  ADD FOREIGN KEY (AuditoriumId) REFERENCES Auditoriums (ID);

Create table CounterAspect(
ID int Primary Key AUTO_INCREMENT,
ReqByName int,
ReqByPrice int,
BuyReq int
);

Create table DiscountAspect(
ID int Primary Key AUTO_INCREMENT,
userId int
);