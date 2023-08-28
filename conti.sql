drop database if exists contiCorrenti;
create database contiCorrenti;
use contiCorrenti;
create table clienti(
codFiscale varchar(16) primary key,
nome varchar(20) not null,
cognome varchar(50) not null); 
create table conti(
numeroConto varchar(12) primary key,
proprietario varchar(16) unique not null,
abi varchar(5) not null,
cab varchar(5) not null,
cin char not null,
saldo double not null,
foreign key(proprietario) references clienti(codFiscale)
);