# [Guardería - BE-auth-&-Users-CRUD](https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/api/schema/swagger-ui/)

**BE-auth-&-Users-CRUD** Microservicio para generar y verificar tokens de acceso y refresh. Para usuarios, se cuenta con get de un solo usuario y de todos en la base de datos, delete, update y create.
Para los token, se tiene la generación con la crecaión de un usuario, refresh, que da nuevo token de acceso, login que genera tokens de acceso y refresh. Como último, se tiene verfyToken.

**Vista de documentación con swagger en [https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/api/schema/swagger-ui/](https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/api/schema/swagger-ui/)**

#### Resumen
| Servicio | funcionalidad| funcionalidad | funcionalidad | funcionalidad |
| --------- | --------- | --------- | --------- | --------- |
| Autentificación | login | refresh | verifyToken | user |
| CRUD User| Create| getById </br>getAll| Update| Delete|


## CRUD User
A contiuación se describen los servicios disponibles para el crud de User

### Create:
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/user/
```

Ejemplo entrada body JSON:
```bash
{
    "username": "USUARIO",
    "password": "contraseña",
    "name": "mi Nombre",
    "email": "miCorreo@misionTIC.com",
    "phone": "3125559977",
    "admin": false
}
```
Ejemplo respuesta JSON:
```bash
{
    "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYzNzI3NTk1MSwianRpIjoiMTI0ZGViMjA1YjVmNDM4OGFlMTJlNDdkNDIzNGRlNGQiLCJ1c2VyX2lkIjo2fQ.ru2FMLrU8s0A8tyMFGfEk0ugBIJpGH1UM_nuAQOArBw",
    "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM3MTkwNDUxLCJqdGkiOiJjOWRlMGFhNThhN2Y0NDI3YWUwYzc0MDc0MDg0ZGI5YiIsInVzZXJfaWQiOjZ9.DRLBa_soZ0v7yznrQoxpOQMaOTLsRNyofl7PFTfo0Ik"
}
```

### Get
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/user/1
```
Ejemplo respuesta JSON:
```bash
{
    "id": 1,
    "username": "fjgomezpe",
    "name": "francisco",
    "email": "fjgomezpe@misionTIC.com",
    "phone": 3203448555,
    "admin": false
}
```

### GetAll
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/user/getAll/
```
Ejemplo respuesta JSON:
```bash
[
    {
    "id": 0,
    "username": "string",
    "name": "string",
    "email": "user@example.com",
    "phone": 9223372036854776000,
    "admin": true
    },
    {
        "id": 1,
        "username": "fjgomezpe",
        "name": "francisco",
        "email": "fjgomezpe@misionTIC.com",
        "phone": 3203448555,
        "admin": true
    }
]
```

### Update:
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/user/5/update/
```
Ejemplo entrada body JSON:
```bash
{
    "username": "modificado",
    "password": "contraseña",
    "name": "modificado2",
    "email": "modificado@misionTIC.com",
    "phone": "3127775555",
    "admin": false
}
```
Ejemplo respuesta JSON:
```bash
{
    "id": 5,
    "username": "modificado",
    "name": "modificado2",
    "email": "modificado@misionTIC.com",
    "phone": 3127775555,
    "admin": false
}
```

### Delete
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/user/6/delete/
```




## Autentificación
A contiuación se describen los servicios disponibles para el crud de User

### user:
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/user/
```
Ejemplo entrada body JSON:
```bash
{
    "username": "USUARIO",
    "password": "contraseña",
    "name": "mi Nombre",
    "email": "miCorreo@misionTIC.com",
    "phone": "3125559977",
    "admin": false
}
```
Ejemplo respuesta JSON:
```bash
{
    "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYzNzI3NTk1MSwianRpIjoiMTI0ZGViMjA1YjVmNDM4OGFlMTJlNDdkNDIzNGRlNGQiLCJ1c2VyX2lkIjo2fQ.ru2FMLrU8s0A8tyMFGfEk0ugBIJpGH1UM_nuAQOArBw",
    "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM3MTkwNDUxLCJqdGkiOiJjOWRlMGFhNThhN2Y0NDI3YWUwYzc0MDc0MDg0ZGI5YiIsInVzZXJfaWQiOjZ9.DRLBa_soZ0v7yznrQoxpOQMaOTLsRNyofl7PFTfo0Ik"
}
```

### Login
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/login/
```
Ejemplo entrada body JSON:
```bash
{
    "username": "fjgomezpe",
    "password": "contraseña"
}
```
Ejemplo respuesta JSON:
```bash
{
    "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYzNzI3NTU1MCwianRpIjoiZWI1MDc1ZjhjZmZlNDBiZTk2MDkwMTRkNWJiMjhhYWMiLCJ1c2VyX2lkIjoxfQ.FkyjFicCZaYbFn9yeJY5czlYbDyKdwizuUHPTd8Yxuo",
    "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM3MTkwMDUwLCJqdGkiOiJhYzNlOWJhMzQyZDg0YzMxYmUzMDIyZWRlYzY5MTZhMCIsInVzZXJfaWQiOjF9.7U-gPAF78TNPLNgAUXp87HZnh6v5-G7KEmw1vxctpxM"
}
```

### verifyToken
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/verifyToken/
```
Ejemplo entrada body JSON:
```bash
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM3MTkwMDUwLCJqdGkiOiJhYzNlOWJhMzQyZDg0YzMxYmUzMDIyZWRlYzY5MTZhMCIsInVzZXJfaWQiOjF9.7U-gPAF78TNPLNgAUXp87HZnh6v5-G7KEmw1vxctpxM"
}
```
Ejemplo respuesta JSON:
```bash
{
    "UserId": 1
}
```

### Refresh
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/refresh/
```
Ejemplo entrada body JSON:
```bash
{
    "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYzNzI3NTU1MCwianRpIjoiZWI1MDc1ZjhjZmZlNDBiZTk2MDkwMTRkNWJiMjhhYWMiLCJ1c2VyX2lkIjoxfQ.FkyjFicCZaYbFn9yeJY5czlYbDyKdwizuUHPTd8Yxuo",
    "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM3MTkwMDUwLCJqdGkiOiJhYzNlOWJhMzQyZDg0YzMxYmUzMDIyZWRlYzY5MTZhMCIsInVzZXJfaWQiOjF9.7U-gPAF78TNPLNgAUXp87HZnh6v5-G7KEmw1vxctpxM"
}
```
Ejemplo respuesta JSON:
```bash
{
    "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM3MTkwMjM5LCJqdGkiOiJiYTczOTlhY2Q4N2Q0YzFiYWIzZjdhMWIxYWVkNmY5ZCIsInVzZXJfaWQiOjF9.z_yZfDfux_JdGMphghtra_s8ZRnNHEN-8wzw7Ri_QjU"
}
```


## Créditos por base del readme
[AdminLTE](https://github.com/ColorlibHQ/AdminLTE/blob/master/README.md)
