# Proyecto integrador final de Backend I - CTD

## Resúmen
El sistema permite la gestión de una clínica odontológica. Se puede gestionar los pacientes, los odontólogos, las consultas.
Cada uno de ellos posee sus endpoints para poder realizar las operaciones necesarias. Se cuenta con dos usuarios un usuario de nivel ADMIN y uno de nivel USER. 
- El ADMIN puede crear, modificar y eliminar los odontólogos, los pacientes y las consultas. 
- El USER solo puede relaizar las consultas de los turnos de los pacientes.

## Cuentas y claves
- Usuario ADMIN: admin@admin.com
- Clave: admin
- --
- Usuario USER: user@mail.com
- Clave: user

## Capas del proyecto
### Controller:
La capa ```controller``` se encarga de transefrir las peticiones entrantes que realice el cliente, al ```service```. Con los distintos @RequestMapping mapeamos el Controller a los distintos endpoints de la API, y con ResponseEntity devolvemos respuestas HTTP.

###Service:
La capa ```service``` se encarga de realizar las operaciones necesarias para la gestión de la clínica. Se conecta con la capa ```repository``` para realizar las operaciones de persistencia. Además se validan los datos que nos envia el ```controller```.

###Repository:
La capa ```repository``` utiliza un ORM, en este caso Hibernate de Springboot, entonces se conecta con la base de datos ```H2``` para realizar las operaciones de persistencia.

###Base de datos:
La base de datos utilizada es la de H2. La dirección donde está configurado para la creación de la BD es: ```jdbc:h2:~/dentalClinic```
- --
## Endpoints
A continuación se muestran los endpoints de toda la aplicación, aún asi en el proyecto se encuentra un archivo endpoints.json que contiene una colección con todos los CRUD necesarios, que pueden ser facilmente importados a POSTMAN para realizar de manera rápida las pruebas.

*Listar* los odontólogos, pacientes y turnos:*
- Listar odontólogos:
  - [GET] /dentists
- Listar pacientes:
  - [GET]/patients
- Listar turnos:
  - [GET]/appointments

*Crear un odontólogo, un paciente o un turno:*
- Crear un odontólogo:
  - [POST] /dentists
- Crear un paciente:
  - [POST] /patients
- Crear un turno:
  - [POST] /appointments

*Buscar un odontólogo, un paciente o un turno:*
  - Buscar un odontólogo:
      - [GET] /dentists/{id}
  - Buscar un paciente:
      - [GET] /patients/{id}
  - Buscar un turno:
      - [GET] /appointments/{id}

*Modificar un odontólogo, un paciente o un turno:*
  - Modificar un odontólogo:
      - [PUT] /dentists/:id
  - Modificar un paciente:
      - [PUT] /patients/:id
  - Modificar un turno:
      - [PUT] /appointments/:id

*Eliminar un odontólogo, un paciente o un turno:*
  - Eliminar un odontólogo:
      - [DELETE] /dentists/:id
  - Eliminar un paciente:
      - [DELETE] /patients/:id
  - Eliminar un turno:
      - [DELETE] /appointments/:id
- --
### Cuestiones a tener en cuenta antes de realizar las pruebas
- La inserción de nuevos pacientes u odonólogos **no deben contener campos vacíos**. o se obtiene un *error 400*.
- La insercion de turnos debe tener una fecha, el ID del odontólogo y el ID del paciente. Ambos ids deben ser válidos y existir en la base de datos o se obtiene un *error 404*.
- --
### Modelos de los endpoints
1. Odontólogo:
    - name: *string*
    - lastName: *string*
    - professionalRegistrationNumber: *string*
2. Pacientes:
    - name: *string*
    - lastName: *string*
    - dni: *number*
    - dischargDate: *string* (formato: yyyy-mm-dd)
    - address: *Object*
        - street: *string*
        - number: *number*
        - city: *string*
        - county: *string*
3. Turnos:
    - date: *string* (formato: yyyy-mm-dd)
    - dentistId: *number*
    - patientId: *number*
- --
    
