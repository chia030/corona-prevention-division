create table users
(
    username varchar(25)                 not null
        primary key,
    password varchar(64)                 not null,
    level    enum ('ADMIN', 'SECRETARY') not null
);

INSERT INTO cpd1.users (username, password, level) VALUES ('aking', 'e5608a3b6f889fee1488462778e3999c72199070f9e2c06256200b97b88c01f4', 'SECRETARY');
INSERT INTO cpd1.users (username, password, level) VALUES ('kfenders', '1da0c92d4d5490bc198a4178ae7f807f63d971f090c264d0f7452ef60d263ab6', 'SECRETARY');
INSERT INTO cpd1.users (username, password, level) VALUES ('mandersen', 'f6c65e6319a94509ee3afc934089216fe131f7b0e015034fb7e00223f1b1b79f', 'SECRETARY');
INSERT INTO cpd1.users (username, password, level) VALUES ('mbrown', 'fc98eda165a751a658762c5c63eb52225196516e22341ac26104d57868b70500', 'ADMIN');
INSERT INTO cpd1.users (username, password, level) VALUES ('pbaker', 'cca4314209b156875ff72d4a1145d891ad02fc3a0733fa2657fa9a6715bd3874', 'SECRETARY');