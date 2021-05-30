create table work_days
(
    work_day_id  int(9) auto_increment
        primary key,
    opening_time time   not null,
    closing_time time   not null,
    interv       int(4) not null,
    capacity     int(4) not null
);

INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (1, '09:00:00', '20:00:00', 15, 15);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (2, '09:00:00', '20:00:00', 30, 50);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (3, '10:00:00', '17:00:00', 15, 20);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (4, '10:00:00', '17:00:00', 15, 15);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (5, '06:00:00', '22:00:00', 30, 10);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (6, '08:00:00', '22:00:00', 30, 5);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (7, '10:00:00', '21:00:00', 30, 5);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (8, '06:00:00', '18:00:00', 20, 10);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (9, '08:00:00', '21:00:00', 20, 10);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (10, '08:00:00', '20:00:00', 25, 5);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (11, '10:00:00', '20:00:00', 25, 3);
INSERT INTO cpd1.work_days (work_day_id, opening_time, closing_time, interv, capacity) VALUES (12, '12:00:00', '20:00:00', 25, 3);