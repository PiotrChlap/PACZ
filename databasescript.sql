Create Table truck(
	id_t INTEGER  CONSTRAINT truck_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	numOfDoors INTEGER,
	maximumLoad INTEGER,
    rented BOOLEAN
);

INSERT INTO truck values(1,'Mercedes','Transit',2.5,120,TRUE, 10,2005,3,3000);
INSERT INTO truck values(2,'MAN','Tgl',5.5,180,FALSE, 20,2007,5,5500);

Create Table special(
	id_t INTEGER  CONSTRAINT special_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	numOfDoors INTEGER,
	type varchar(30),
    rented BOOLEAN
);

INSERT INTO special values(3,'Jelcz','Jelcz Turbo',2.5,240,FALSE, 10,2005,3,'Pompa Do cementu');


Create Table sportPassCar(
	id_t INTEGER  CONSTRAINT sportPassCar_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	numOfDoors INTEGER,
	numOfSeats INTEGER,
	chipTuning BOOLEAN,
	spoiler BOOLEAN,
	maxSpeed INTEGER,
    rented BOOLEAN
);

INSERT INTO sportPassCar values(4,'Ferrari','F430',4.5,490,TRUE, 35,2007,3,2,TRUE,TRUE,290);
INSERT INTO sportPassCar values(5,'BMW','M8',4.4,625,TRUE, 60,2020,2,4,TRUE,FALSE,350);

Create Table premiumPassCar(
	id_t INTEGER  CONSTRAINT premiumPassCar_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	numOfDoors INTEGER,
	numOfSeats INTEGER,
	autopilot BOOLEAN,
	glassRoof BOOLEAN,
	bar BOOLEAN,
    rented BOOLEAN

);

INSERT INTO premiumPassCar values(6,'Rolls-Royce','GHOST',6.6,570,TRUE, 120,2020,5,5,TRUE,TRUE,TRUE);


Create Table familyPassCar(
	id_t INTEGER  CONSTRAINT familyPassCar_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	numOfDoors INTEGER,
	numOfSeats INTEGER,
	typeFamilyCar varchar(30),
	seatChild INTEGER,
	spareWheel BOOLEAN,
    rented BOOLEAN

);

INSERT INTO familyPassCar values(7,'Opel','Vivaro',2.0,90,FALSE, 30,2010,5,9,'miniVan',2,TRUE);



Create Table chopper(
	id_t INTEGER  CONSTRAINT chopper_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	driveType varchar(30),
	length float,
    rented BOOLEAN
);

INSERT INTO chopper values(8,'Junak','M11',0.2,10,FALSE, 25,2020,'Pas_napędowy',2.3);

Create Table cross_M(
	id_t INTEGER  CONSTRAINT cross_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	driveType varchar(30),
	torque INTEGER,
    rented BOOLEAN
);

INSERT INTO cross_M values(9,'Xmotos','XB-27',0.1,11,TRUE, 15,2020,'Łańcuch',40);

Create Table sportMotorcycle(
	id_t INTEGER  CONSTRAINT sportMotorcycle_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	driveType varchar(30),
	maxSpeed INTEGER,
    rented BOOLEAN
);

INSERT INTO sportMotorcycle values(10,'Yamaha','R6',0.6,118,FALSE, 30,2019,'Łańcuch',350);

Create Table touristMotorcycle(
	id_t INTEGER  CONSTRAINT touristMotorcycle_id Primary key,
	marka varchar(20),
	model varchar(20),
	capacity float,
	power INTEGER,
	automaticGearbox BOOLEAN NOT NULL,
	basicPrice INTEGER,
	yearOfProduction INTEGER,
	driveType varchar(30),
	bootCapacity INTEGER,
    rented BOOLEAN
);

INSERT INTO touristMotorcycle values(11,'Honda','GL',1.8,120,FALSE, 25,2012,'Wał_kardana',120);


CREATE TABLE client(
id_c INTEGER CONSTRAINT client_id Primary key,
address varchar(50),
password varchar(20),
login varchar(20),
taxNumber varchar(20),
nameCompany varchar(20),
representativeName varchar(40),
representativeTel varchar (14),
firstName varchar(15),
surname varchar(20),
personalIdNum varchar(12),
type INTEGER,
loyaltypoints INTEGER);

INSERT INTO client values(1,'Ul.Próchnika 22 m. 3 Łódź 90-425','jeden','jeden','435345674210','Polonica','Adam Kornacki','523333444', 1);

Create table order_o(
id_o INTEGER CONSTRAINT order_id PRIMARY KEY,
submitDate varchar(20),
id_c INTEGER )

CREATE table rent(
id_r INTEGER CONSTRAINT rent_id PRIMARY KEY,
startDate varchar(20),
endDate varchar(20),
id_c INTEGER,
closed BOOLEAN DEFAULT FALSE,
id_v INTEGER);