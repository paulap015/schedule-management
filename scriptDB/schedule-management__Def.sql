/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/2022 8:54:39 p. m.                     */
/*==============================================================*/


drop table if exists AMBIENTE;

drop table if exists AREA;

drop table if exists COMPETENCIA;

drop table if exists CORDINADOR;

drop table if exists DOCENTE;

drop table if exists FRANJAHORARIA;

drop table if exists PERIODOACADEMICO;

drop table if exists PERIODOACADEMICOAMBIENTE;

drop table if exists PROGRAMA;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: AMBIENTE                                              */
/*==============================================================*/
create table AMBIENTE
(
   AMB_CODIGO           varchar(15) not null,
   AMB_NOMBRE           varchar(30) not null,
   AMB_TIPO_AMBIENTE    varchar(15) not null,
   AMB_CAPACIDAD        int not null,
   AMB_UBICACION        varchar(15),
   primary key (AMB_CODIGO)
);

/*==============================================================*/
/* Table: AREA                                                  */
/*==============================================================*/
create table AREA
(
   AREA_ID              int not null,
   AREA_NOMBRE          varchar(30) not null,
   primary key (AREA_ID)
);

/*==============================================================*/
/* Table: COMPETENCIA                                           */
/*==============================================================*/
create table COMPETENCIA
(
   COMP_CODIGO          int not null,
   PROG_CODIGO          int not null,
   COMP_TIPO            varchar(20) not null,
   COMP_NOMBRE          varchar(20) not null,
   primary key (COMP_CODIGO)
);

/*==============================================================*/
/* Table: CORDINADOR                                            */
/*==============================================================*/
create table CORDINADOR
(
   US_ID                int not null,
   US_TIPO_ID           varchar(30) not null,
   US_NOMBRE            varchar(100) not null,
   US_APELLIDO          varchar(30) not null,
   US_ROL               varchar(15) not null,
   COR_FECHA_INICIO     date,
   primary key (US_ID)
);

/*==============================================================*/
/* Table: DOCENTE                                               */
/*==============================================================*/
create table DOCENTE
(
   US_ID                int not null,
   US_TIPO_ID           varchar(30) not null,
   US_NOMBRE            varchar(100) not null,
   US_APELLIDO          varchar(30) not null,
   US_ROL               varchar(15) not null,
   AREA_ID              int not null,
   PROG_CODIGO          int not null,
   DOC_TIPO_DOCENTE     varchar(30) not null,
   DOC_TIPO_CONTRATO    char(30) not null,
   primary key (US_ID)
);

/*==============================================================*/
/* Table: FRANJAHORARIA                                         */
/*==============================================================*/
create table FRANJAHORARIA
(
   HOR_ID               int not null,
   COMP_CODIGO          int not null,
   US_ID                int not null,
   HOR__HORA_INICIO     time,
   HOR_HORA_FIN         time,
   HOR_DIA              varchar(20),
   HOR_DISPONIBLE       bool,
   primary key (HOR_ID)
);

/*==============================================================*/
/* Table: PERIODOACADEMICO                                      */
/*==============================================================*/
create table PERIODOACADEMICO
(
   PA_ID                int not null,
   PA_FECHA_INICIO      date not null,
   PA_FECHA_FIN         date not null,
   PA_NOMBRE            varchar(30) not null,
   primary key (PA_ID)
);

/*==============================================================*/
/* Table: PERIODOACADEMICOAMBIENTE                              */
/*==============================================================*/
create table PERIODOACADEMICOAMBIENTE
(
   AMB_CODIGO           varchar(15) not null,
   PA_ID                int not null,
   HOR_ID               int,
   primary key (AMB_CODIGO, PA_ID)
);

/*==============================================================*/
/* Table: PROGRAMA                                              */
/*==============================================================*/
create table PROGRAMA
(
   PROG_CODIGO          int not null,
   PROG_NOMBRE          varchar(50) not null,
   primary key (PROG_CODIGO)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   US_ID                int not null,
   US_TIPO_ID           varchar(30) not null,
   US_NOMBRE            varchar(100) not null,
   US_APELLIDO          varchar(30) not null,
   US_ROL               varchar(15) not null,
   primary key (US_ID)
);

alter table COMPETENCIA add constraint FK_COMPETENCIAPROGRAMA foreign key (PROG_CODIGO)
      references PROGRAMA (PROG_CODIGO) on delete restrict on update restrict;

alter table CORDINADOR add constraint FK_SON2 foreign key (US_ID)
      references USUARIO (US_ID) on delete restrict on update restrict;

alter table DOCENTE add constraint FK_DOCENTEPROGRAMA foreign key (PROG_CODIGO)
      references PROGRAMA (PROG_CODIGO) on delete restrict on update restrict;

alter table DOCENTE add constraint FK_RELATIONSHIP_1 foreign key (AREA_ID)
      references AREA (AREA_ID) on delete restrict on update restrict;

alter table DOCENTE add constraint FK_SON foreign key (US_ID)
      references USUARIO (US_ID) on delete restrict on update restrict;

alter table FRANJAHORARIA add constraint FK_FRANJAHORARIACOMPETENCIA foreign key (COMP_CODIGO)
      references COMPETENCIA (COMP_CODIGO) on delete restrict on update restrict;

alter table FRANJAHORARIA add constraint FK_TIENE foreign key (US_ID)
      references DOCENTE (US_ID) on delete restrict on update restrict;

alter table PERIODOACADEMICOAMBIENTE add constraint FK_FRANJAAMBIENTE foreign key (HOR_ID)
      references FRANJAHORARIA (HOR_ID) on delete restrict on update restrict;

alter table PERIODOACADEMICOAMBIENTE add constraint FK_PERIODOACADEMICOAMBIENTE foreign key (AMB_CODIGO)
      references AMBIENTE (AMB_CODIGO) on delete restrict on update restrict;

alter table PERIODOACADEMICOAMBIENTE add constraint FK_PERIODOACADEMICOAMBIENTE2 foreign key (PA_ID)
      references PERIODOACADEMICO (PA_ID) on delete restrict on update restrict;

