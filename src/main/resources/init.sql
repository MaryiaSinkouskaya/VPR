CREATE SEQUENCE address_id_seq START WITH 10 INCREMENT BY 1;
alter sequence address_id_seq owner to postgres;

create table address
(
    id        BIGINT PRIMARY KEY DEFAULT nextval('address_id_seq'),
    street    varchar(64) not null,
    building  integer     not null,
    apartment integer     not null,
    town      varchar(32) not null
);

alter table address owner to postgres;
alter sequence address_id_seq owned by address.id;

create sequence note_id_seq START WITH 10 INCREMENT BY 1;
alter sequence note_id_seq owner to postgres;

create table note
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('note_id_seq'),
    date date                                            not null,
    note text
);

alter table note owner to postgres;
alter sequence note_id_seq owned by note.id;


create sequence org_id_seq START WITH 10 INCREMENT BY 1;
alter sequence org_id_seq owner to postgres;

create table organization
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('org_id_seq'),
    number integer      not null,
    name   varchar(512) not null
);

alter table organization
    owner to postgres;
alter sequence org_id_seq owned by organization.id;


create sequence abnormality_id_seq START WITH 50 INCREMENT BY 1;
alter sequence abnormality_id_seq owner to postgres;

create table abnormality
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('abnormality_id_seq'),
    name varchar(64)                                      not null
);

alter table abnormality owner to postgres;
alter sequence abnormality_id_seq owned by abnormality.id;

create sequence work_id_seq START WITH 10 INCREMENT BY 1;
alter sequence work_id_seq owner to postgres;

create table workplace
(
    id       BIGINT PRIMARY KEY DEFAULT nextval('work_id_seq'),
    job_type varchar(32)                                      not null,
    company  varchar(32)                                      not null
);

alter table workplace owner to postgres;
alter sequence work_id_seq owned by workplace.id;


create sequence person_info_id_seq START WITH 10 INCREMENT BY 1;
alter sequence person_info_id_seq owner to postgres;

create table person_info
(
    id      BIGINT PRIMARY KEY DEFAULT nextval('person_info_id_seq'),
    name       varchar(16) not null,
    surname    varchar(16) not null,
    patronymic varchar(16) not null,
    birth_date date        not null,
    address_id integer     not null
        constraint address_id
            references address,
    work_id    integer     not null
        constraint work_id
            references workplace,
    phone      varchar(16)
);

alter table person_info owner to postgres;
alter sequence person_info_id_seq owned by person_info.id;


create sequence doctor_id_seq START WITH 10 INCREMENT BY 1;
alter sequence doctor_id_seq owner to postgres;

create table doctor
(
    id     BIGINT PRIMARY KEY DEFAULT nextval('doctor_id_seq'),
    speciality     varchar(32) not null,
    person_info_id integer
        constraint person_info_id
            references person_info
);

alter table doctor owner to postgres;
alter sequence doctor_id_seq owned by doctor.id;



create sequence mother_id_seq START WITH 10 INCREMENT BY 1;
alter sequence mother_id_seq owner to postgres;

create table mother
(
    id     BIGINT PRIMARY KEY DEFAULT nextval('mother_id_seq'),
    last_menstruation_date date        not null,
    diagnose_date          date        not null,
    girl_surname           varchar(16) not null,
    person_info_id         integer     not null
        constraint person_info_id
            references person_info
);

alter table mother owner to postgres;
alter sequence mother_id_seq owned by mother.id;


create sequence proband_id_seq START WITH 10 INCREMENT BY 1;
alter sequence proband_id_seq owner to postgres;

create table proband
(
    id    BIGINT PRIMARY KEY DEFAULT nextval('proband_id_seq'),
    karyotype                   varchar(64)      not null,
    pregnancy_duration_in_weeks integer          not null,
    weight                      double precision not null,
    head                        double precision not null,
    pregnancy_number            integer          not null,
    is_aborted                  boolean,
    organization_id             integer
        constraint organization_id
            references organization,
    abnormality_id                     integer
        constraint abnormality_id
            references abnormality,
    mother_id                   integer
        constraint mother_id
            references mother,
    father_id                   integer
        constraint father_id
            references person_info,
    doctor_id                   integer
        constraint doctor_id
            references doctor,
    note_id                     integer
        constraint dcn_id
            references note,
    birth_date                  date,
    gender                      varchar(16),
    ploidity                    varchar(16),
    labor_outcome               varchar(16)
);

alter table proband owner to postgres;
alter sequence proband_id_seq owned by proband.id;


create sequence prob_d_id_seq START WITH 10 INCREMENT BY 1;
alter sequence prob_d_id_seq owner to postgres;

create table prob_d
(
    id     BIGINT PRIMARY KEY DEFAULT nextval('prob_d_id_seq'),
    death_date date not null,
    proband_id integer
        constraint proband_id
            references proband
);

alter table prob_d owner to postgres;
alter sequence prob_d_id_seq owned by prob_d.id;


create sequence user_id_seq START WITH 10 INCREMENT BY 1;
alter sequence user_id_seq owner to postgres;

create table _user
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq'),
    email text,
    password text,
    role text
);

alter table _user owner to postgres;
alter sequence user_id_seq owned by _user.id;


create sequence token_id_seq START WITH 10 INCREMENT BY 1;
alter sequence token_id_seq owner to postgres;

create table token
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('token_id_seq'),
    token text,
    revoked bool,
    expired bool,
    user_id integer
        constraint user_id
            references _user
);

alter table token owner to postgres;
alter sequence token_id_seq owned by token.id;


INSERT INTO public.address (id, street, building, apartment, town) VALUES (2, 'Tsentralnaya', 11, 48, 'Brest');
INSERT INTO public.address (id, street, building, apartment, town) VALUES (1, 'Inrernatsionalnaya', 33, 10, 'Brest');
INSERT INTO public.address (id, street, building, apartment, town) VALUES (4, 'Volhohratskaya', 1, 12, 'Minsk');
INSERT INTO public.address (id, street, building, apartment, town) VALUES (5, 'Kalinina', 2, 9, 'Mohilev');
INSERT INTO public.address (id, street, building, apartment, town) VALUES (6, 'Nekrasova', 34, 3, 'Mohilev');
INSERT INTO public.address (id, street, building, apartment, town) VALUES (7, 'Surhanova', 87, 55, 'Vitsebsk');
INSERT INTO public.address (id, street, building, apartment, town) VALUES (8, 'Raduzhnaya', 32, 108, 'Smolevichi');
INSERT INTO public.address (id, street, building, apartment, town) VALUES (9, 'Kalininovskogo', 12, 43, 'Minsk');

INSERT INTO public.workplace (id, job_type, company) VALUES (1, 'info am', 'First');
INSERT INTO public.workplace (id, job_type, company) VALUES (2, 'info dnms', 'Second');
INSERT INTO public.workplace (id, job_type, company) VALUES (3, 'info jdm', 'Third');

INSERT INTO public.person_info (id, name, surname, patronymic, birth_date, address_id, work_id, phone) VALUES (4, 'AAA', 'AAAA', 'AAAA', '1990-12-23', 1, 1, '1111111');
INSERT INTO public.person_info (id, name, surname, patronymic, birth_date, address_id, work_id, phone) VALUES (5, 'BBBB', 'BBBB', 'BBB', '1992-12-23', 2, 2, '2222222');
INSERT INTO public.person_info (id, name, surname, patronymic, birth_date, address_id, work_id, phone) VALUES (6, 'CCCC', 'CCCC', 'CCCC', '1982-12-23', 2, 3, '3333333');

INSERT INTO public.organization (id, number, name) VALUES (1, 1, 'first ');

INSERT INTO public.mother (id, last_menstruation_date, diagnose_date, girl_surname, person_info_id) VALUES (1, '2020-12-11', '2020-12-12', 'qqq', 4);

INSERT INTO public.abnormality (id, name) VALUES (1, 'Wilms Tumor');
INSERT INTO public.abnormality (id, name) VALUES (2, 'Walker-Warburg');
INSERT INTO public.abnormality (id, name) VALUES (3, 'Rubenstein-Taybi');
INSERT INTO public.abnormality (id, name) VALUES (4, 'Rhabdomyosarcoma');
INSERT INTO public.abnormality (id, name) VALUES (5, 'Miller-Dieker');
INSERT INTO public.abnormality (id, name) VALUES (6, 'Hodgkins Disease');
INSERT INTO public.abnormality (id, name) VALUES (7, 'Hermaphrodite');
INSERT INTO public.abnormality (id, name) VALUES (8, 'Hepatoblastoma');
INSERT INTO public.abnormality (id, name) VALUES (9, 'Brain or CNS');
INSERT INTO public.abnormality (id, name) VALUES (10, 'Cholesterol Low	');
INSERT INTO public.abnormality (id, name) VALUES (11, 'Polymicrogyria	');
INSERT INTO public.abnormality (id, name) VALUES (12, 'Polysplenia');
INSERT INTO public.abnormality (id, name) VALUES (13, 'Osteoporosis');
INSERT INTO public.abnormality (id, name) VALUES (14, 'Ectodermal Dyspl');
INSERT INTO public.abnormality (id, name) VALUES (15, 'Myoblastoma');
INSERT INTO public.abnormality (id, name) VALUES (16, 'Leukodystrophy');

INSERT INTO public.doctor (id, speciality, person_info_id) VALUES (1, 'genetics', 6);

insert into public.note (id, date, note) values (1, '2021-12-16', 'some note info');
insert into public.note (id, date, note) values (2, '2022-12-16', 'some note info');

INSERT INTO public.proband (id, karyotype, pregnancy_duration_in_weeks, weight, head, pregnancy_number, is_aborted, organization_id, abnormality_id, mother_id, father_id, doctor_id, note_id, birth_date, gender, ploidity, labor_outcome) VALUES (2, 'undefined', 40, 2.5, 30, 2, false, 1, 1, 1, 5, 1, 1, '2021-12-07', 'FEMALE', null, null);
INSERT INTO public.proband (id, karyotype, pregnancy_duration_in_weeks, weight, head, pregnancy_number, is_aborted, organization_id, abnormality_id, mother_id, father_id, doctor_id, note_id, birth_date, gender, ploidity, labor_outcome) VALUES (3, 'undefined', 39, 2.2, 32, 1, false, 1, 1, 1, 6, 1, 2, '2024-07-10', 'MALE', null, null);

INSERT INTO public.prob_d (id, death_date, proband_id) VALUES (1, '2021-12-16', 2);

INSERT INTO public._user (id, email, password, role) VALUES (1, 'bjksdkjvsd@mail.com', 'sdbj983247hc783b', 'ADMIN');
INSERT INTO public._user (id, email, password, role) VALUES (2, 'bnkjljek83sd@mail.com', ',m65jkl46jk', 'DOCTOR');

INSERT INTO public.token (id, token, revoked, expired, user_id) VALUES (1, 'ksdjnc8db87dc79320d', false, false, 1);
