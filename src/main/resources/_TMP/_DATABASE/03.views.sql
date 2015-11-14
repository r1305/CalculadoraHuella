-- CO2 Generado por Distrito per capita anual
-- Uso: Mapa de Produccion de CO2 Distrito
create or replace view distritos_co2 as
select d.id,d.nombre, d.latitud, d.longitud,  avg(co2_anno_electricidad + co2_anno_transportes + co2_anno_residuos_solidos) co2_anno
from distritos d, encuestas e, detalles_encuesta de
where d.id=e.distrito_id and e.id=de.id group by d.id;

-- CO2 Generado por Distrito por Dispositivo Electrico (detalle) anual
-- Uso: Detalle de Produccion de CO2 por Electromestico
create or replace view dispositivos_electricos_co2 as
select e.distrito_id, de.id as dispositivo_electrico_id, nombre, avg(dee.co2_dia)*365/1000 co2_anno
from dispositivos_electricos de,  encuestas e, detalles_encuesta_electricidad dee
where de.id=dee.dispositivo_electrico_id and e.id=encuesta_id and e.distrito_id=1280
group by e.distrito_id, de.id
order by e.distrito_id, de.id;

-- Lista de Transportes ordenados por Categoria
-- Uso: Mostrar Lista de Transportes ordenados por categoria
create or replace view transportes_categorias as
select tc.id, tc.categoria_id, t.nombre
from transportes t, transportes_x_categorias tc
where t.id=tc.transporte_id order by tc.categoria_id, t.nombre;