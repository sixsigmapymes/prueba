# examen20220511
Examen sondeo 20220511
##############################################################
### ANGULAR
Version : 8.x
##  IDE
Visual Studio 
Version : 2022
#############################################################
      Front   =================>   Front End
#############################################################
# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2
# Datasource
spring.datasource.url=jdbc:h2:file:./target/BaseDatos/prueba;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
server.port=8082  
*************************************************************
    Carga de Objects Sets =====>  BOs
-------------------------------------------------------------

// Carga 15 Business Objects BOs en la Base de datos H2

http://localhost:8082/api/libro/init
------------------------------------------------------------
@Autor: Rozenberg Sergio 20220512
