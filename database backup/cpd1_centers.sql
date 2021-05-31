create table centers
(
    center_id    int(9) auto_increment
        primary key,
    center_type  enum ('PCR_TEST', 'COMIRNATY_VACCINE', 'MODERNA_VACCINE', 'UNKNOWN') not null,
    address_id   int(9)                                                               not null,
    work_week_id int(9)                                                               null
);

INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (3, 'COMIRNATY_VACCINE', 3, 1);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (4, 'MODERNA_VACCINE', 4, 2);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (5, 'MODERNA_VACCINE', 5, 3);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (6, 'COMIRNATY_VACCINE', 6, 4);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (7, 'PCR_TEST', 7, 4);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (8, 'PCR_TEST', 8, 3);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (9, 'COMIRNATY_VACCINE', 9, 3);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (10, 'MODERNA_VACCINE', 10, 2);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (14, 'COMIRNATY_VACCINE', 15, 7);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (16, 'PCR_TEST', 13, 6);
INSERT INTO cpd1.centers (center_id, center_type, address_id, work_week_id) VALUES (17, 'PCR_TEST', 16, 9);