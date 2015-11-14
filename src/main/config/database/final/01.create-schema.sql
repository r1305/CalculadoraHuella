-- Database
-- drop database if exists calculatuhuella;
-- create database calculatuhuella;
-- use calculatuhuella;
-- set foreign_key_checks=0;
drop table if exists detalles_encuestas_personas;
drop table if exists detalles_encuesta;
drop table if exists detalles_encuesta_electricidad;
drop table if exists detalles_encuesta_transporte;
drop table if exists detalles_encuesta_residuo_solido;
drop table if exists encuestas;
drop table if exists preguntas;
drop table if exists informacion_inei;
drop table if exists residuos_solidos;
drop table if exists categorias_residuos_solidos;
drop table if exists transportes_x_categorias;
drop table if exists transportes;
drop table if exists categorias_transportes;
drop table if exists dispositivos_electricos;
drop table if exists categorias_dispositivos_electricos;
drop table if exists distritos;
drop table if exists provincias;
drop table if exists departamentos;
drop table if exists sectores;
-- set foreign_key_checks=1;

-- Tablas
create table departamentos (
	id integer primary key auto_increment,
	codigo char(2) not null comment 'Codigo Ubigeo Departamento',
	nombre varchar(100) not null
) comment 'Departamento';

create table provincias (
	id integer primary key auto_increment,
    departamento_id integer not null,
    codigo char(4) not null comment 'Codigo Ubigeo Provincia',
	nombre varchar(100) not null
);
alter table provincias add constraint foreign key provincias_departamentos_fk(departamento_id) references departamentos(id);

-- Distrito
create table distritos (
	id integer primary key auto_increment,
    provincia_id integer not null,
    codigo char(6) not null comment 'Codigo Ubigeo Distrito',
	nombre varchar(100) not null,
	latitud varchar(30) not null default '-12.0461628',
	longitud varchar(30) not null default '-77.0329076'
);
alter table distritos add constraint foreign key distritos_provincias_fk(provincia_id) references provincias(id);

-- Sectores: Electricidad, Transportes, Residuos
create table sectores (
	id smallint primary key auto_increment,
	nombre varchar(100) not null comment 'Nombres de Sectore: Electricidad(1), Transporte(2), Residuos(3)'
) comment 'Sectores: Electricidad, Transporte y Residuos solidos';

-- Informacion inei
create table informacion_inei (
	id bigint primary key auto_increment,
    indicador_fuente smallint not null default 1 comment 'Indicador si informacion es Oficial(1) o Pronostico(2)',
	anno integer not null,
	sector_id smallint not null,	
	distrito_id integer not null,
	co2_anno decimal(20,15) not null comment 'Valor de generacion de CO2 per capita anual proporcionado por INEI'
) comment 'Informacion proporcionada por el INEI';
alter table informacion_inei add constraint unique informacion_inei_uk(anno, sector_id, distrito_id);
alter table informacion_inei add constraint foreign key informacion_inei_distritos_fk(distrito_id) references distritos(id);
alter table informacion_inei add constraint foreign key informacion_inei_sectores_fk(sector_id) references sectores(id);


-- Categorias Dispositivos Electricos: Iluminacion, Electrodomésticos
create table categorias_dispositivos_electricos (
	id smallint primary key auto_increment,
	indicador_periodo smallint not null comment 'Periodos de uso de dispositivos electricos(elecrodomesticos/quipos). valores: 1: continuo(diario), 2: esporadico(semanal)',
	nombre varchar(100) not null
	/*sector_id smallint default 1 not null comment 'Referencia al identificador de Sector(Electricidad, Transporte, Residuos Solidos'*/
) comment 'Categoria de equipos electricos como: Iluminacion, Equipos Electrodomesticos, etc';
alter table categorias_dispositivos_electricos add constraint unique categorias_dispositivos_electricos_uk(indicador_periodo, nombre);


-- Electrodomesticos: Foco ahorrador, Cocina Eléctrica, Licuadora etc
create table dispositivos_electricos (
	id integer primary key auto_increment,
    categoria_id smallint not null,
	nombre varchar(100) not null
) comment 'Dispositivos electricos como Foco, Televsior, etc';
-- TODO: Revisar para el caso de Iluminacion: T5, T8 (interiores)
-- alter table dispositivos_electricos add constraint unique dispositivos_electricos_uk(categoria_id, nombre);
alter table dispositivos_electricos add constraint foreign key dispositivos_electricos_categorias_fk(categoria_id) references categorias_dispositivos_electricos(id);


-- TODO: Categoria Transportes Publico, Privado, Propio
create table categorias_transportes (
	id smallint primary key auto_increment,
	nombre varchar(100) not null
	/*sector_id smallint not null default 2 comment 'Referencia al identificador de Sector(Electricidad, Transporte, Residuos Solidos)'*/
) comment 'Categorias de Transportes: Publico, Propio';
alter table categorias_transportes add constraint unique categorias_transportes_uk(nombre);


-- Tipos de Transportes: Colectivo, Combi, Coaster, Bus, Sedan
create table transportes (
	id integer primary key auto_increment,
	nombre varchar(100) not null comment 'valores: Colectivo, Combi, Coaster, Bus, Sedan'
) comment 'Transporte: Auto, Combi, Bus, Camioneta';
alter table transportes add constraint unique transportes_uk(nombre);

-- Un transporte puede estar en mas de una categoria
create table transportes_x_categorias (
	id integer primary key auto_increment comment 'Identificador de un transporte en una cateoria (Combi: puede ser publico o privado)',
	categoria_id smallint not null,
    transporte_id integer not null
) comment 'Relacion de Trnsportes y Categoria de transporte: ejemplo: Auto: Puede ser propio o publico';
alter table transportes_x_categorias add constraint unique transportes_x_categorias_uk(categoria_id, transporte_id);
alter table transportes_x_categorias add constraint foreign key transportes_x_categorias_categorias_fk(categoria_id) references categorias_transportes(id);
alter table transportes_x_categorias add constraint foreign key transportes_x_categorias_transportes_fk(transporte_id) references transportes(id);

-- Categorias Residuos Solidos
create table categorias_residuos_solidos (
	id smallint primary key auto_increment,
	indicador_periodo smallint not null comment 'Periodos de generacion de residuos solidos. valores: 1:diario, 2:semanal, 3:mensual',
	nombre varchar(100) not null
	/*sector_id smallint not null default 2 comment 'Referencia al identificador de Sector(Electricidad, Transporte, Residuos Solidos)'*/
) comment 'Categorias de Transportes: Publico, Propio';
alter table categorias_residuos_solidos add constraint unique categorias_residuos_solidos_uk(indicador_periodo, nombre);

-- Guarda informacion de Residuos Solidos
create table residuos_solidos (
	id integer primary key auto_increment,
	categoria_id smallint not null,
	nombre varchar(100) not null comment 'Valores: Papel, Pilas'
) comment 'Residuo solido: Valores';
-- alter table residuos_solidos add constraint unique residuos_solidos_uk(nombre);
alter table residuos_solidos add constraint foreign key residuos_solidos_categorias_fk(categoria_id) references categorias_residuos_solidos(id);

-- Pregunta de Encuesta: Puede no pertenecer a un sector(ejemplo: Numero de Personas en Hogar)
create table preguntas (
	id integer primary key primary key auto_increment,
    sector_id smallint null,
    seccion_id smallint not null default 1 comment 'En caso de Transporte existen 2 secciones',
    orden integer not null comment 'Orden que se muestra en encuesta',
	pregunta varchar (500) not null
) comment 'Pregunta usado en encuesta, Se refiere a un sector. Puede pertenecer a una seccion(Ejemplo: Transporte tiene 2 secciones), ademas de un orden';
alter table preguntas add constraint unique preguntas_uk(sector_id, seccion_id, pregunta);
alter table preguntas add constraint foreign key preguntas_sectores_fk(sector_id) references sectores(id);

-- Encuestas
create table encuestas (
	id integer primary key auto_increment,
	anno integer not null comment 'Anno en que se realizo la encuesta',
	distrito_id integer not null comment 'Distrito donde reside el encuestado',
	email varchar(100) comment 'Email de persona encuestada',
	indicador_fuente smallint not null default 1 comment 'Indifcador si encuesta fisica, online: Online(1), Fisica(2)'
) comment 'Una encuesta por persona anual';
alter table encuestas add constraint unique encuestas_uk(anno, email);
alter table encuestas add constraint foreign key encuestas_distritos_fk(distrito_id) references distritos(id);

create table detalles_encuesta (
	id integer primary key,
	-- encuesta_id integer not null,
	cantidad_residentes smallint not null comment 'Cantidad de miembros de familia',
	area_terreno smallint not null comment 'Area de construccion de vivienda',
	indicador_jardin char(1) not null default 'N' comment 'Indicdor si tiene jardin. Valores S:Si, N:No',
	area_jardin decimal(5,2) null comment 'Area de jardin, en caso posea',
	co2_anno_electricidad decimal(20,15) comment 'Electricidad: Cantidad en toneladas de generacion de CO2 per capita anual',
	co2_anno_transportes decimal(20,15) comment 'Transporte: Cantidad en toneladas de generacion de CO2 per capita anual',
	co2_anno_residuos_solidos decimal(20,15) comment 'Residuos Solidos: Cantidad en toneladas de generacion de CO2 per capita anual'
) comment 'Detalle de encuesta (Cabecera), informacion de encuesta que no pertenece a ninguna seccion: Numero de residentes';
-- alter table detalles_encuesta add constraint unique detalles_encuesta_uk(encuesta_id);
alter table detalles_encuesta add constraint foreign key detalles_encuesta_encuestas_fk(id) references encuestas(id);

create table detalles_encuestas_personas (
  id bigint not null auto_increment,
  encuesta_id int null,
  sexo char(1) null comment 'Indicador sexo: M, F',
  indicador_edad smallint null comment 'Rango de edad 1: Menos de 2a, 2:2-14a, 3: 15-50a, 4:51-70a, 5:Mas de 70a',
  cantidad_personas smallint not null comment 'Cantidad de residentes en rango de dead',
  primary key (id),
  constraint detalles_encuestas_personas_encuestas_fk foreign key (encuesta_id) references detalles_encuesta(id)
) comment 'Informacion de cantidad de residentes por sexo/rango edad';
alter table detalles_encuestas_personas add constraint unique detalles_encuestas_personas_uk(encuesta_id, sexo, indicador_edad);


create table detalles_encuesta_electricidad (
	id bigint primary key auto_increment,
	encuesta_id integer not null,
    sector_id smallint null default 1,
    pregunta_id integer null,
    dispositivo_electrico_id integer not null,
    cantidad_dispositivos integer not null comment 'Electricidad: Cantidad de dispositivos',
    tiempo_uso_dia integer not null comment 'Electricidad: Tiempo de uso por dia',
    co2_dia decimal(20,15) comment 'Electricidad: Cantidad en kgs de generacion de CO2 per capita diario'
) comment 'Detalle de encuesta en sector Electricidad (1)';
alter table detalles_encuesta_electricidad add constraint unique detalles_encuesta_electricidad_uk(encuesta_id, sector_id, dispositivo_electrico_id);
alter table detalles_encuesta_electricidad add constraint foreign key detalles_encuestas_electricidad_encuestas_fk(encuesta_id) references encuestas(id);
alter table detalles_encuesta_electricidad add constraint foreign key detalles_encuestas_electricidad_sectores_fk(sector_id) references sectores(id);
alter table detalles_encuesta_electricidad add constraint foreign key detalles_encuestas_electricidad_preguntas_fk(pregunta_id) references preguntas(id);
alter table detalles_encuesta_electricidad add constraint foreign key detalles_encuestas_electricidad_dispositivos_electricos_fk(dispositivo_electrico_id) references dispositivos_electricos(id);


create table detalles_encuesta_transporte (
	id bigint primary key auto_increment,
	encuesta_id integer not null,
    sector_id smallint null default 2,
    pregunta_id integer null,
    transporte_x_categoria_id integer not null comment 'Identificador del transporte en una categoria',
    cantidad_viajes_dia integer not null comment 'Electricidad: Cantidad de dispositivos',
    tiempo_uso_dia integer not null comment 'Tiempo de uso del Transporte por dia',
    co2_dia decimal(20,15) comment 'Transporte: Cantidad en kgs de generacion de CO2 per capita diario'
) comment 'Detalle de encuesta en sector Transpores (2)';
alter table detalles_encuesta_transporte add constraint unique detalles_encuesta_transporte_uk(encuesta_id, sector_id, transporte_x_categoria_id);
alter table detalles_encuesta_transporte add constraint foreign key detalles_encuesta_transporte_encuestas_fk(encuesta_id) references encuestas(id);
alter table detalles_encuesta_transporte add constraint foreign key detalles_encuesta_transporte_sectores_fk(sector_id) references sectores(id);
alter table detalles_encuesta_transporte add constraint foreign key detalles_encuesta_transporte_preguntas_fk(pregunta_id) references preguntas(id);
alter table detalles_encuesta_transporte add constraint foreign key detalles_encuesta_transporte_transportes_x_categorias_fk(pregunta_id) references transportes_x_categorias(id);


create table detalles_encuesta_residuo_solido (
	id bigint primary key auto_increment,
	encuesta_id integer not null,
    sector_id smallint null default 3,
    pregunta_id integer null,
    residuo_solido_id integer not null comment 'Identificador del Residuo',
    cantidad_residuo_generado integer not null comment 'Cantidad en Kgs generada de Residuo solido diario',
    co2_dia decimal(20,15) comment 'Residuos Solidos: Cantidad en kgs de generacion de CO2 per capita diario'
) comment 'Detalle de encuesta en sector Residuos (3)';
alter table detalles_encuesta_residuo_solido add constraint unique detalles_encuesta_residuo_solido_uk(encuesta_id, sector_id, residuo_solido_id);
alter table detalles_encuesta_residuo_solido add constraint foreign key detalles_encuesta_residuo_solido_encuestas_fk(encuesta_id) references encuestas(id);
alter table detalles_encuesta_residuo_solido add constraint foreign key detalles_encuesta_residuo_solido_sectores_fk(sector_id) references sectores(id);
alter table detalles_encuesta_residuo_solido add constraint foreign key detalles_encuesta_residuo_solido_preguntas_fk(pregunta_id) references preguntas(id);
alter table detalles_encuesta_residuo_solido add constraint foreign key detalles_encuesta_residuo_solido_residuo_solido_id_fk(residuo_solido_id) references residuos_solidos(id);


/* Cambiar engine*/
/*
alter table departamentos engine=innodb;
alter table provincias engine=innodb;
alter table distritos engine=innodb;
alter table sectores engine=innodb;
alter table informacion_inei engine=innodb;
alter table categorias_dispositivos_electricos engine=innodb;
alter table dispositivos_electricos engine=innodb;
alter table categorias_transportes engine=innodb;
alter table transportes engine=innodb;
alter table transportes_x_categorias engine=innodb;
alter table residuos_solidos engine=innodb;
alter table preguntas engine=innodb;
alter table encuestas engine=innodb;
alter table detalles_encuesta engine=innodb;
alter table detalles_encuesta_electricidad engine=innodb;
alter table detalles_encuesta_transporte engine=innodb;
alter table detalles_encuesta_residuo_solido engine=innodb;
*/