create table product (
    PID int,
    PName varchar(20),
    PCat varchar(20),
    Price int
);

create table purchase (
    TxnID int,
    pi_d int,
    Qty int
);

insert into product(PID, PName, PCat, Price) VALUES
('1', 'Book', 'STAT', '1'),
('2', 'Pen', 'STAT', '1'),
('3', 'T-Shirt', 'App', '1'),
('4', 'Shirt', 'App', '1'),
('5', 'Computer', 'Elec', '1'),
('6', 'Laptop', 'Elec', '1'),
('7', 'Phone', 'Elec', '1');

insert into purchase (TxnID, pi_d, Qty) values
('1', '1', '5'),
('2', '1', '3'),
('3', '4', '10'),
('4', '4', '3');