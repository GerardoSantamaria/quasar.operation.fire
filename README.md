# API Operación Quasar
Esta API tiene la finalidad de decodificar los mensajes y posiciones en un mapa de dos dimensiones.

## Especificaciones técnicas
- Java 8
- Spring boot 2
- JUnit
- PostgreSQL
- Maven
- Swagger

## Arquitectura
Paquetes:
1. Resource: se encuentra el rest controller
2. Service: las interfaces de servicio
3. Service_Impl: implementaciones de servicios
4. Repository: capa de persistencia
5. Entity: entidades del sistema
6. Exception: errores específicos de sistemas
7. DTO: dtos que se utiliza para enviar información
8. Configuration: configuración del Swagger

Se basó en las especificaciones de REST y el modelo de tres capas para el desarrollo de la API.

## Ambiente Productivo
Se utilizó Heroku para el despliegue de la misma. URL donde está alojado la API <https://quasar-operation-fire.herokuapp.com/> .

Endpoint expuestos:

1.POST <https://quasar-operation-fire.herokuapp.com/quasar-operation/v1/topsecret> 

2.POST <https://quasar-operation-fire.herokuapp.com/quasar-operation/v1/topsecret_split/{satellite_name}> 

3.GET <https://quasar-operation-fire.herokuapp.com/quasar-operation/v1/topsecret_split> 

## Documentación de Enpoints
Con la utilización de Swagger se documentó cada endpoint <https://quasar-operation-fire.herokuapp.com/swagger-ui.html#/>
