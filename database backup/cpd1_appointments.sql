create table appointments
(
    appointment_id int(9) auto_increment
        primary key,
    result         enum ('BOOKED', 'MISSED', 'POSITIVE', 'NEGATIVE', 'INCONCLUSIVE', 'PARTIAL_VACCINE', 'VACCINATED') not null,
    date           date                                                                                               not null,
    time           time                                                                                               not null,
    cpr            char(10)                                                                                           not null,
    center_id      int(9)                                                                                             not null,
    patient_email  varchar(128)                                                                                       not null,
    constraint appointments_patients_cpr_fk
        foreign key (cpr) references patients (cpr)
);

create index appointments_centers_center_id_fk
    on appointments (center_id);

INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (2, 'MISSED', '2021-05-11', '10:30:00', '1401790462', 7, 'SelamPPetersen@rhyta.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (3, 'VACCINATED', '2021-05-01', '12:00:00', '1504624559', 4, 'VilladsLLauridsen@teleworm.us');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (4, 'PARTIAL_VACCINE', '2021-04-20', '17:00:00', '2012953081', 9, 'AlfredRKlausen@armyspy.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (5, 'NEGATIVE', '2021-05-11', '11:00:00', '2112424484', 8, 'AnnemetteKEriksen@dayrep.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (6, 'VACCINATED', '2021-05-16', '23:30:00', '1405017777', 3, 'just1531@stud.kea.dk');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (7, 'NEGATIVE', '2021-05-21', '12:30:00', '1405017777', 6, 'just1531@stud.kea.dk');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (8, 'MISSED', '2021-05-27', '16:15:00', '1405017777', 8, 'just1531@stud.kea.dk');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (9, 'MISSED', '2021-05-20', '17:30:00', '1405017777', 7, 'just1531@stud.kea.dk');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (10, 'MISSED', '2021-05-29', '19:00:00', '1010105555', 4, 'zdanaviciusjustas1@gmail.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (11, 'BOOKED', '2021-06-02', '19:00:00', '1405017777', 4, 'zdanaviciusjustas1@gmail.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (12, 'MISSED', '2021-05-21', '14:30:00', '1313131111', 7, 'zdanaviciusjustas1@gmail.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (13, 'BOOKED', '2021-06-27', '16:00:00', '1405016666', 8, 'zdanaviciusjustas1@gmail.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (14, 'MISSED', '2021-05-29', '15:40:00', '1405017777', 16, 'zdanaviciusjustas1@gmail.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (15, 'BOOKED', '2021-06-26', '19:20:00', '3101641111', 16, 'chiaravisca@outlook.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (16, 'MISSED', '2021-05-29', '11:40:00', '1405017777', 17, 'zdanaviciusjustas1@gmail.com');
INSERT INTO cpd1.appointments (appointment_id, result, date, time, cpr, center_id, patient_email) VALUES (17, 'MISSED', '2021-05-30', '16:45:00', '6575765765', 8, 'chiaravisca@outlook.com');