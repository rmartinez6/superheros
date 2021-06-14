# W2M Challenge
## Prueba técnica Spring Boot
### API que permite hacer mantenimiento de súper héroes.

### Build and Run
Ubicarse en la raiz del proyecto y correr los siguientes comandos:

- mvn clean package

- java -jar target/superhero-0.0.1-SNAPSHOT.jar

- La aplicación se encuentra corriendo en http://localhost:8080

#### Documentacion: 
Swagger --> http://localhost:8080/swagger-ui/index.html

#### Seguridad:
username: admin

password: 1234 

### Pruebas por Postman

Para probar los endpoint por postman agregar en Authorization
un BasicAuth con las credenciales:

username: admin

password: 1234 

Los endpoints son los siguientes:

- Obtener todos los superheroes: (GET) http://localhost:8080/superheros
- Filtrar superheroes por nombre: (GET) http://localhost:8080/superheros?name=man
- Obtener un superheroe por su id: (GET) http://localhost:8080/superheros/1
- Modificar informacion de un superheroe: (PUT) http://localhost:8080/superheros/1
con body: {
              "id": 1,
              "name": "Superman",
              "fullName": "Clark Joseph Kent",
              "placeOfBirth": "Cordoba"
          }
- Eliminar un superheros: (DELETE) http://localhost:8080/superheros/5
### Docker
Ubicarse en la raiz del proyecto y correr los siguientes comandos:

- Generar el jar:  mvn clean package
- Generar imagen docker: docker build -t superheros:1.0.0 .  
- Levantar imagen generada: docker run -it -p8080:8080 superheros:1.0.0
