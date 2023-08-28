use contiCorrenti;
delete from conti where numeroConto<>'';
delete from clienti where codFiscale<>'';

insert into clienti(codFiscale,nome,cognome) values
('AAAAAA11A01A111A','A','Rossi'),
('AAAAAB11A01A111A','B','Rossi'),
('AAAAAC11A01A111A','C','Verdi');

insert into conti(numeroConto,proprietario,abi,cab,cin,saldo) values
('000000000001','AAAAAA11A01A111A','00000','00000','a',0),
('000000000002','AAAAAB11A01A111A','00000','00000','b',0),
('000000000003','AAAAAC11A01A111A','00000','00000','c',0);


