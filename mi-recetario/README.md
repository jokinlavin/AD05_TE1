# Mi Recetario

## 1. Descripción del proyecto

Los requisitos del enunciado es desarrollar una aplicación web de temática propia con Spring Boot, Spring Data JPA, Spring MVC, Oracle, Thymeleaf y Bootstrap.
Debe permitir gestionar dos entidades relacionadas y sus borrados en cascada

He decidido realizar una aplicación de Gestión de recetas: Mi Recetario, que permite gestionar recetas de cocina y sus pasos de preparación.

La aplicación permite realizar operaciones CRUD (crear, consultar, modificar y eliminar) sobre recetas y pasos, manteniendo una relación entre ambas entidades (1:N). El borrado de una receta borra sus pasos, pero no lo contrario.


## 2. Tecnologías utilizadas

- Java 21
- Spring Boot 4.1.2
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- Bootstrap 5
- Oracle Database XE 21c
- Maven
- Eclipse IDE 2025-12


## 3. Funcionalidades

### Menú principal
- Gestión de recetas
- Gestión de pasos

### Gestión de recetas
- Nuevas recetas
- Consulta de recetas
- Modificacíón de una receta
- Eliminación de recetas (y sus pasos)

### Gestión de pasos
- Nuevos pasos de preparación de una receta
- Consulta de los pasos de las recetas
- Modificación de pasos de una receta
- Eliminación de pasos

### Relación entre entidades

La aplicación utiliza una relación uno a muchos (1:N):

- Una receta puede tener varios pasos
- Cada paso pertenece a una única receta
- Al eliminar una receta, se eliminan también sus pasos
- Al eliminar un paso, la receta permanece (aunque sea el último paso)


## 4. Configuración de la base de datos

La aplicación utiliza Oracle Database XE 21c.

Configuración de conexión:

- Host: localhost
- Puerto: 1521
- Servicio: XEPDB1
- Usuario: RECETARIO

Es necesario configurar las credenciales de acceso en `application.properties` antes de ejecutar la aplicación.


## 5. Instalación y ejecución

### Requisitos previos:

- Java JDK 21
- Maven 3.10.1
- Oracle Database XE 21c
- Eclipse IDE u otro entorno compatible con Maven

### Pasos para ejecutar

1. Iniciar Oracle Database y comprobar que el servicio XEPDB1 está disponible.
2. Configurar la conexión a Oracle en `application.properties`.
3. Importar el proyecto Maven en Eclipse.
4. Actualizar las dependencias Maven.
5. Ejecutar la aplicación mediante Spring Boot o con el comando:
6. Abrir el navegador y acceder a:

```text
http://localhost:8080
```


## 6. Validaciones y pruebas

Se han realizado pruebas de:

- Creación y consulta de recetas
- Creación y consulta de pasos
- Asociación de pasos con sus recetas
- Eliminación de una receta y sus pasos asociados
- Eliminación de un paso sin eliminar su receta
