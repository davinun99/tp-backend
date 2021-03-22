create table cliente (
	id_cliente Serial Primary Key, 
	nombre VARCHAR (100) NOT NULL ,
	apellido VARCHAR (100) NOT NULL,
	numero_documento Integer UNIQUE NOT NULL, 
	tipo_documento VARCHAR (100) NOT NULL,
	nacionalidad VARCHAR (100) NOT NULL,
	email VARCHAR (100) NOT NULL,
	telefono Integer ,
	fecha_nacimiento TIMESTAMP NOT NULL
);
create table concepto_puntos(
	id_concepto Serial Primary Key,
	descripcion Varchar (100) NOT NULL,
	puntos_requeridos Integer NOT NULL
);
create table asignacion_puntos(
	id_asignacion Serial Primary Key,
	limite_inferior Integer NOT NULL,
	limite_superior Integer NOT NULL,
	monto Integer NOT NULL
);
create table vencimiento_puntos(
	id_vencimiento Serial Primary Key,
	fecha_inicio TIMESTAMP NOT NULL,
	fecha_fin TIMESTAMP NOT NULL,
	duracion Integer
);
create table bolsa_puntos(
	id_bolsa Serial Primary Key,
	id_cliente Integer NOT NULL,
	fecha_asignacion TIMESTAMP NOT NULL,
	fecha_caducidad TIMESTAMP NOT NULL,
	puntajes_asignado Integer NOT NULL,
	puntaje_utilizado Integer NOT NULL,
	saldo_puntos Integer NOT NULL,
	monto Integer NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

create table uso_puntos_cabecera(
	id_cabecera Serial Primary Key,
	id_cliente Integer NOT NULL,
	id_concepto Integer NOT NULL,
	puntaje_utilizado Integer NOT NULL,
	fecha TIMESTAMP NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
	FOREIGN KEY(id_concepto) REFERENCES concepto_puntos (id_concepto)
);

create table uso_puntos_detalle(
	id_detalle Serial Primary Key,
	id_cabecera Integer NOT NULL,
	id_bolsa Integer NOT NULL,
	puntaje_utilizado Integer NOT NULL,
	FOREIGN KEY (id_cabecera) REFERENCES uso_puntos_cabecera(id_cabecera),
	FOREIGN Key( id_bolsa) REFERENCES bolsa_puntos (id_bolsa)
);
