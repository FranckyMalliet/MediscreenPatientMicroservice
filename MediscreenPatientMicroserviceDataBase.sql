
CREATE TABLE patients (
  patient_id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  birth_date DATE,
  gender VARCHAR(30),
  address VARCHAR (100),
  phone VARCHAR (30),
  danger_level VARCHAR(100),
  
  PRIMARY KEY (patient_id)
);

insert into patients(first_name, last_name, birth_date, gender, address, phone, danger_level) values("Helianor", "Valante", "1966-12-31", "F", "1 Brookside St", "100-222-3333", "TestNone&given");
insert into patients(first_name, last_name, birth_date, gender, address, phone, danger_level) values("Paul", "Atreides", "1945-06-24", "M", "2 High St", "200-333-4444", "TestBorderline");

insert into patients(first_name, last_name, birth_date, gender, address, phone, danger_level) values("Marc", "Dupontavice", "2004-06-18", "M", "3 Club Road", "300-444-5555", "TestInDanger");
insert into patients(first_name, last_name, birth_date, gender, address, phone, danger_level) values("Chani", "Liet", "2002-06-28", "F", "4 Valley Dr", "400-555-6666", "TestEarlyOnset");

insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Lucas", "Ferguson", "1968-06-22", "M", "2 Warren Street", "387-866-1399");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Pippa", "Rees", "1952-09-27", "F", "745 West Valley Farms Drive", "628-423-0993");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Edward", "Arnold", "1952-11-11", "M", "599 East Garden Ave", "123-727-2779");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Anthony", "Sharp", "1946-11-26", "M", "894 Hall Street", "451-761-8383");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Wendy", "Ince", "1958-06-29", "F", "4 Southhampton Road", "802-911-9975");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Tracey", "Ross", "1949-12-07", "F", "40 Sulphur Springs Dr", "131-396-5049");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Claire", "Wilson", "1966-12-31", "F", "12 Cobblestone St", "300-452-1091");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Max", "Buckland", "1945-06-24", "M", "193 Vale St", "833-534-0864");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Natalie", "Clark", "1964-06-18", "F", "12 Beechwood Road", "241-467-9197");
insert into patients(first_name, last_name, birth_date, gender, address, phone) values("Piers", "Bailey", "1959-06-28", "M", "1202 Bumble Dr", "747-815-0557");