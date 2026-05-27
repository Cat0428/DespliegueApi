# API Festivos Colombia 🇨🇴

API REST desarrollada en Spring Boot que gestiona festivos y calendario laboral de Colombia.

## 🛠️ Tecnologías
- Java 17
- Spring Boot
- PostgreSQL
- JWT (Seguridad)
- Swagger UI

## 📁 Estructura del Proyecto

```
api-festivos/
├── dominio/
│   └── src/main/java/
│       ├── entidades/
│       │   ├── DiaCalendario.java
│       │   ├── Festivo.java
│       │   ├── Tipo.java
│       │   └── Usuario.java
│       └── dtos/
│           ├── FestivoDto.java
│           └── UsuarioLoginDto.java
├── core/
│   └── src/main/java/interfaces/
│       ├── IFestivoServicio.java
│       ├── IFestivoExternoServicio.java
│       ├── ICalendarioServicio.java
│       └── IUsuarioServicio.java
├── infraestructura/
│   └── src/main/java/
│       ├── repositorios/
│       │   ├── IFestivoRepositorio.java
│       │   ├── IDiaCalendarioRepositorio.java
│       │   └── IUsuarioRepositorio.java
│       └── integracion/
│           └── FestivoClienteServicio.java
├── aplicacion/
│   └── src/main/java/
│       ├── servicios/
│       │   ├── FestivoServicio.java
│       │   ├── CalendarioServicio.java
│       │   └── UsuarioServicio.java
│       └── seguridad/
│           ├── FiltroSeguridad.java
│           ├── SeguridadServicio.java
│           ├── UsuarioDetalles.java
│           └── UsuarioDetalleServicio.java
└── presentacion/
    └── src/main/java/
        ├── controladores/
        │   ├── ApiFestivosApplication.java
        │   ├── FestivoControlador.java
        │   └── UsuarioControlador.java
        └── config/
            ├── ConfiguracionSeguridad.java
            └── SwaggerConfig.java
```

## ⚙️ Configuración

### Base de datos
Crear una base de datos en PostgreSQL llamada `calendariolaboral` y configurar las credenciales en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/calendariolaboral
spring.datasource.username=postgres
spring.datasource.password=1234
```

### API Node.js externa
Configurar la URL de la API de Node.js en `application.properties`:

```properties
festivos.externo.url=http://localhost:3000/api/festivos/obtener
```

## 🚀 Endpoints

### Autenticación
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios/login/{usuario}/{clave}` | Genera token JWT |

### Festivos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/festivos/verificar/{ano}/{mes}/{dia}` | Verifica si una fecha es festiva |
| GET | `/festivos/externos/{anio}` | Lista festivos del año desde Node.js |
| GET | `/festivos/generar/{anio}` | Genera y guarda el calendario del año |
| GET | `/festivos/calendario/{anio}` | Retorna el calendario completo del año |

### Usuarios
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios/` | Lista todos los usuarios |
| POST | `/api/usuarios/` | Crea un usuario |
| PUT | `/api/usuarios/` | Actualiza un usuario |
| DELETE | `/api/usuarios/{id}` | Elimina un usuario |

## 📖 Documentación
Swagger UI disponible en:

http://localhost:8080/swagger-ui/index.html

## 🔐 Autenticación
Todos los endpoints requieren token JWT. Para obtenerlo:
1. Llamar a `/api/usuarios/login/{cristian}/{123}`
2. Copiar el token de la respuesta
3. En Swagger hacer clic en **Authorize** 🔒 y pegar el token

## 🐳 Levantar los servicios con Docker

### Requisitos
- Docker Desktop instalado y corriendo

### Pasos

## 📦 Repositorios del proyecto

| Repositorio | Descripción | Enlace |
|-------------|-------------|--------|
| api-festivos | API Spring Boot + PostgreSQL | https://github.com/Cat0428/api-festivos |
| Api-Fechas | API Node.js + MongoDB | https://github.com/Cat0428/Api-Fechas |

cd api-festivos
```

**2. Crear la red de Docker:**
```bash
docker network create redcalendario
```

**3. Levantar todos los servicios:**
```bash
docker-compose up
```

Esto levanta automáticamente los 4 contenedores:
- `dockerbdfestivos` — MongoDB (puerto 27018)
- `dockerapifestivos` — API Node.js (puerto 3000)
- `dockerbdcalendario` — PostgreSQL (puerto 5433)
- `dockerapicalendario` — API Spring Boot (puerto 8080)

**4. Probar la API:**

GET http://localhost:8080/api/usuarios/login/cristian/123

**5. Para detener los servicios:**
```bash
docker-compose down