CREATE DATABASE calendariolaboral;

\c calendariolaboral

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    usuario VARCHAR(100) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    clave VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT true,
    foto BYTEA NULL,
    roles VARCHAR(100) NULL
);

CREATE TABLE dia_calendario (
    id SERIAL PRIMARY KEY,
    fecha VARCHAR(20),
    tipo VARCHAR(20),
    anio INTEGER,
    descripcion VARCHAR(20)
);