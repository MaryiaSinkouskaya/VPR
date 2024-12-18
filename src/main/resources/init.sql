create sequence dcn_id_seq
    as integer;

alter sequence dcn_id_seq owner to postgres;

create sequence tpor_id_seq
    as integer;

alter sequence tpor_id_seq owner to postgres;

create sequence work_id_seq
    as integer;

alter sequence work_id_seq owner to postgres;

create table address
(
    id        serial
        constraint address_pkey
            primary key
        constraint address_id_uindex
            unique,
    street    varchar(64) not null,
    building  integer     not null,
    apartment integer     not null,
    town      varchar(32) not null
);

alter table address
    owner to postgres;

create table note
(
    id   integer default nextval('dcn_id_seq'::regclass) not null
        constraint dcn_pkey
            primary key
        constraint tnotific_id_uindex
            unique,
    date date                                            not null,
    note text
);

alter table note
    owner to postgres;

alter sequence dcn_id_seq owned by note.id;

create table organization
(
    id     serial
        constraint organization_pkey
            primary key
        constraint organization_id_uindex
            unique,
    number integer      not null,
    name   varchar(512) not null
);

alter table organization
    owner to postgres;

create table abnormality
(
    id   integer default nextval('tpor_id_seq'::regclass) not null
        constraint tpor_pkey
            primary key
        constraint tpor_id_uindex
            unique,
    name varchar(16)                                      not null
);

alter table abnormality
    owner to postgres;

alter sequence tpor_id_seq owned by abnormality.id;

create table workplace
(
    id       integer default nextval('work_id_seq'::regclass) not null
        constraint work_pkey
            primary key
        constraint work_id_uindex
            unique,
    job_type varchar(32)                                      not null,
    company  varchar(32)                                      not null
);

alter table workplace
    owner to postgres;

alter sequence work_id_seq owned by workplace.id;

create table person_info
(
    id         serial
        constraint person_info_pkey
            primary key
        constraint person_info_id_uindex
            unique,
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

alter table person_info
    owner to postgres;

create table doctor
(
    id             serial
        constraint doctor_pkey
            primary key
        constraint doc_id_uindex
            unique,
    speciality     varchar(32) not null,
    person_info_id integer
        constraint person_info_id
            references person_info
);

alter table doctor
    owner to postgres;

create table mother
(
    id                     serial
        constraint mother_pkey
            primary key
        constraint mother_id_uindex
            unique,
    last_menstruation_date date        not null,
    diagnose_date          date        not null,
    girl_surname           varchar(16) not null,
    person_info_id         integer     not null
        constraint person_info_id
            references person_info
);

alter table mother
    owner to postgres;

create table proband
(
    id                          serial
        constraint proband_pkey
            primary key
        constraint proband_id_uindex
            unique,
    karyotype                   varchar(16)      not null,
    pregnancy_duration_in_weeks integer          not null,
    weight                      double precision not null,
    head                        double precision not null,
    pregnancy_number            integer          not null,
    is_aborted                  boolean,
    organization_id             integer
        constraint organization_id
            references organization,
    tpor_id                     integer
        constraint tpor_id
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

alter table proband
    owner to postgres;

create table prob_d
(
    id         serial
        constraint prob_d_pkey
            primary key
        constraint prob_d_id_uindex
            unique,
    death_date date not null,
    proband_id integer
        constraint proband_id
            references proband
);

alter table prob_d
    owner to postgres;

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

INSERT INTO public.abnormality (id, name) VALUES (1, 'Wilms Tumor	');
INSERT INTO public.abnormality (id, name) VALUES (2, 'Walker-Warburg	');
INSERT INTO public.abnormality (id, name) VALUES (3, 'Rubenstein-Taybi');
INSERT INTO public.abnormality (id, name) VALUES (4, 'Rhabdomyosarcoma');
INSERT INTO public.abnormality (id, name) VALUES (5, 'Miller-Dieker	');
INSERT INTO public.abnormality (id, name) VALUES (6, 'Hodgkins Disease');
INSERT INTO public.abnormality (id, name) VALUES (7, 'Hermaphrodite');
INSERT INTO public.abnormality (id, name) VALUES (8, 'Hepatoblastoma');
INSERT INTO public.abnormality (id, name) VALUES (9, 'Brain or CNS	');
INSERT INTO public.abnormality (id, name) VALUES (10, 'Cholesterol Low	');
INSERT INTO public.abnormality (id, name) VALUES (11, 'Polymicrogyria	');
INSERT INTO public.abnormality (id, name) VALUES (12, 'Polysplenia');
INSERT INTO public.abnormality (id, name) VALUES (13, 'Osteoporosis');
INSERT INTO public.abnormality (id, name) VALUES (14, 'Ectodermal Dyspl');
INSERT INTO public.abnormality (id, name) VALUES (15, 'Myoblastoma');
INSERT INTO public.abnormality (id, name) VALUES (16, 'Malposition of t');
INSERT INTO public.abnormality (id, name) VALUES (17, 'Leukodystrophy');

INSERT INTO public.doctor (id, speciality, person_info_id) VALUES (1, 'genetics', 6);

INSERT INTO public.proband (id, karyotype, pregnancy_duration_in_weeks, weight, head, pregnancy_number, is_aborted, organization_id, tpor_id, mother_id, father_id, doctor_id, note_id, birth_date, gender, ploidity, labor_outcome) VALUES (2, 'undefined', 40, 2.5, 30, 2, false, 1, 1, 1, 5, 1, null, '2021-12-07', 'FEMALE', null, null);
INSERT INTO public.proband (id, karyotype, pregnancy_duration_in_weeks, weight, head, pregnancy_number, is_aborted, organization_id, tpor_id, mother_id, father_id, doctor_id, note_id, birth_date, gender, ploidity, labor_outcome) VALUES (3, 'undefined', 39, 2.2, 32, 1, false, 1, 1, 1, 6, 1, null, '2024-07-10', 'MALE', null, null);

INSERT INTO public.prob_d (id, death_date, proband_id) VALUES (1, '2021-12-16', 2);