create table work_weeks
(
    work_week_id int(9) auto_increment
        primary key,
    monday       int null,
    tuesday      int null,
    wednesday    int null,
    thursday     int null,
    friday       int null,
    saturday     int null,
    sunday       int null
);

INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (2, 1, 1, 1, 1, 1, 2, 2);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (3, 3, 3, 3, 3, 3, 4, 4);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (4, 1, 1, 1, 1, 1, null, null);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (5, 5, 5, 5, 5, 6, 7, 7);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (6, 8, 8, 8, 8, 9, 9, 9);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (7, 5, 5, 5, 5, 6, 7, 6);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (8, 3, 3, 3, 3, 3, 4, 6);
INSERT INTO cpd1.work_weeks (work_week_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (9, 10, 10, 10, 10, 10, 11, 12);