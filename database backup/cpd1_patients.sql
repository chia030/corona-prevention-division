create table patients
(
    cpr         char(10)     not null
        primary key,
    email       varchar(128) not null,
    last_name   varchar(30)  not null,
    first_name  varchar(30)  not null,
    approved    tinyint(1)   not null,
    approval_id varchar(64)  null
);

INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('0907840223', 'KasperSSchmidt@jourrapide.com', 'Schmidt', 'Kasper S.', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('1010105555', 'zdanaviciusjustas1@gmail.com', 'Reindeer', 'Jessica', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('1313131111', 'zdanaviciusjustas1@gmail.com', 'Z', 'J', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('1401790462', 'SelamPPetersen@rhyta.com', 'Petersen', 'Selam P.', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('1405016666', 'zdanaviciusjustas1@gmail.com', 'Zdanavicius', 'Justas', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('1405017777', 'zdanaviciusjustas1@gmail.com', 'Z', 'J', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('1504624559', 'VilladsLLauridsen@teleworm.us', 'Lauridsen', 'Villads L.', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('2012953081', 'AlfredRKlausen@armyspy.com', 'Klausen', 'Alfred R.', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('2112424484', 'AnnemetteKEriksen@dayrep.com', 'Eriksen', 'Annemette K.', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('3101641111', 'chiaravisca@outlook.com', 'Gerladina', 'Jasmin', 1, null);
INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES ('6575765765', 'chiaravisca@outlook.com', 'Visca', 'Chiara', 1, null);