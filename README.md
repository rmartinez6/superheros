# W2M Challenge
## Prueba técnica Spring Boot
### API que permite hacer mantenimiento de súper héroes.

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

### Docker

Generar imagen docker

docker build -t superheros:1.0.0 .  

Levantar imagen generada

docker run -it -p8080:8080 superheros:1.0.0
