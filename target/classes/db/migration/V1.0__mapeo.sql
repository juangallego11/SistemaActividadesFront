
create table if not exists empleados(
id_empleado serial,
nombre varchar(50),
apellido varchar(50),
telefono varchar(20),
correo varchar(50),
primary key(id_empleado));

create table if not exists actividades(
id_actividad serial,
fecha_registro date,
estado varchar(15),
fk_empleado int,
descripcion varchar(100),
constraint actividad_empleado foreign key(fk_empleado) references empleados(id_empleado),
primary key(id_actividad));
