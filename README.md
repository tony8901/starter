# starter
Starter Hexagonal Architecture Project

El objetivo de este proyecto es mostrar cómo se puede implementar una arquitectura limpia y modular, aprovechando las ventajas de spring boot y spring cloud. El proyecto consta de los siguientes módulos:

- config-server: Es el servidor de configuración centralizado, que almacena y distribuye las propiedades de los demás microservicios.
- eureka-server: Es el servidor de registro y descubrimiento de servicios, que permite a los microservicios comunicarse entre sí de forma dinámica y balanceada.
- gateway-server: Es el punto de entrada a la aplicación, que actúa como un proxy inverso y un enrutador de solicitudes.
- microservice-student: Es un microservicio que expone una API REST para gestionar recursos de tipo Estudiantes. Aplica los principios de la arquitectura hexagonal, separando las capas de dominio, aplicación e infraestructura. Utiliza una base de datos PostgreSQL para persistir los datos.
- microservice-course: Es un microservicio que expone una API REST para gestionar recursos de tipo Curso. Aplica los mismos principios que el microservice-student, aunque en una base de datos diferente que interactúa con Estudiantes a través de Feign.

Para ejecutar el proyecto, se debe clonar el repositorio y ejecutar el comando `mvn spring-boot:run` en cada uno de los módulos, siguiendo el orden: config-server, eureka-server, gateway-server, microservice-student y microservice-course. Luego se puede acceder a las APIs de los microservicios a través del gateway-server, usando las siguientes URLs:

- http://localhost:8082/students/api/students
- http://localhost:8082/courses/api/courses

En la carpeta postman se encuentra la colección de peticiones disponibles de la API-REST

También se puede acceder a la interfaz web de eureka-server, usando la siguiente URL:

- http://localhost:8761
