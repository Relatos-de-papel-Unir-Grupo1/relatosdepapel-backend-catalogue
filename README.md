# Relatos de Papel - Microservicio de Catálogo

Microservicio encargado de gestionar el catálogo de libros para la aplicación **Relatos de Papel**. Proporciona una API REST para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros, gestionando su información detallada como título, autor, ISBN, stock y precio.

## ✨ Características Principales

- **Gestión Completa de Libros**: API para administrar todos los aspectos de los libros del catálogo.
- **Integración con Service Discovery**: Registrado como un cliente de Eureka para ser descubierto por otros microservicios dentro del ecosistema (como el API Gateway y el servicio de órdenes).
- **Carga de Datos de Prueba (Seeding)**: Mecanismo configurable para poblar la base de datos con libros de prueba generados automáticamente, ideal para entornos de desarrollo y pruebas.
- **Persistencia de Datos**: Utiliza Spring Data JPA y MySQL para la persistencia de la información.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3
- **Persistencia**: Spring Data JPA, Hibernate, MySQL
- **Gestión de Dependencias**: Maven
- **Service Discovery**: Spring Cloud Netflix Eureka Client
- **Generación de Datos Mock**: Java Faker
- **Utilidades**: Lombok

## 🚀 Cómo Empezar

Sigue estos pasos para configurar y ejecutar el microservicio en tu entorno local.

### Prerrequisitos

- JDK 17 o superior.
- Maven 3.8 o superior.
- Una instancia de MySQL en ejecución.
- Un servidor Eureka para el registro de servicios.

### Configuración

1.  **Clona el repositorio**:
    ```bash
    git clone <url-del-repositorio>
    cd relatosdepapel-backend-catalogue
    ```

2.  **Configura la base de datos**:
    -   Crea una base de datos en MySQL llamada `catalogue_db`.
    -   El esquema de la tabla `book` se creará automáticamente al iniciar la aplicación gracias a JPA.

3.  **Configura las propiedades de la aplicación**:
    Ajusta el archivo `src/main/resources/application.properties` con la configuración de tu base de datos y Eureka.

    ```properties
    # Puerto del servidor
    server.port=8080

    # Configuración de la Base de Datos
    spring.datasource.url=jdbc:mysql://localhost:3306/catalogue_db
    spring.datasource.username=root
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update

    # Configuración de Eureka
    eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka

    # Configuración de Carga de Datos de Prueba
    app.seed.enabled=true
    app.seed.books-count=50
    ```

### Ejecución

Puedes ejecutar la aplicación utilizando el siguiente comando de Maven:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

## 🌱 Carga de Datos de Prueba (Seeding)

Este microservicio puede generar datos de prueba automáticamente al arrancar.

- Para **habilitar** esta función, establece `app.seed.enabled=true`.
- Para **deshabilitarla** (recomendado en producción), establece `app.seed.enabled=false`.
- Puedes controlar la cantidad de libros a generar con la propiedad `app.seed.books-count`.

El sistema es idempotente: si la base de datos ya contiene libros, no se agregarán más datos de prueba para evitar duplicados.

## 📖 Documentación de la API

La API proporciona los siguientes endpoints para gestionar los libros:

#### `GET /api/v1/books`
- **Descripción**: Obtiene una lista de todos los libros disponibles en el catálogo.
- **Respuesta Exitosa (200 OK)**:
  ```json
  [
    {
      "id": 1,
      "title": "El nombre del viento",
      "author": "Patrick Rothfuss",
      "publicationDate": "2007-03-27",
      "isbn": "978-8401336323",
      "category": "Fantasía",
      "unitPrice": 23.70,
      "stock": 50
    }
  ]
  ```

#### `GET /api/v1/books/{id}`
- **Descripción**: Obtiene un libro específico por su ID.
- **Respuesta Exitosa (200 OK)**:
  ```json
  {
    "id": 1,
    "title": "El nombre del viento",
    "author": "Patrick Rothfuss",
    "stock": 50,
    "unitPrice": 23.70
  }
  ```

#### `PATCH /api/v1/books/{id}`
- **Descripción**: Actualiza parcialmente un libro. Actualmente se utiliza para actualizar el stock.
- **Cuerpo de la Petición**:
  ```json
  {
    "stock": 49
  }
  ```
- **Respuesta Exitosa**: `200 OK` (sin cuerpo).

## 👥 Colaboradores

- **Sebastian Felipe Alvarado Prieto**
- **Ardys Díaz Hurtado**
- **Luis Ferdinand Lugoz Rivas**