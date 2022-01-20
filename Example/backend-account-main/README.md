# [Guardería - BE-Products-CRUD](https://mintic-p5-g4-dw-be-projects.herokuapp.com/swagger-ui/)

**BE-Products-CRUD** Microservicio para realizar las operaciones de create, modify, get, getAll a la base de datos no relacional ProductsDB. Añadida también base de datos UserPoducts donde se registra el id del usuario y el id de un producto, sobre esta nueva base de datos, también se añadio CRUD teniendo metodos para eliminar pos usuario, por producto, por usuario y producto juntos.

**Vista de documentación con swagger en [https://mintic-p5-g4-dw-be-projects.herokuapp.com/swagger-ui/](https://mintic-p5-g4-dw-be-projects.herokuapp.com/swagger-ui/)**

#### Resumen
| Servicio | funcionalidad| funcionalidad | funcionalidad | funcionalidad |
| ----------- | ----------- | ----------- | ----------- | ----------- |
| CRUD Products| Create| Get <br/> getAll <br/> getByIsService| Update| Delete|
| CRUD userProducts| Create| GetbyUserId <br/> getbyUser&Product| Update| DeletebyUserId <br/> DeleteByProductId <br/> Delete byUser&Product|


## CRUD Products
A contiuación se describen los servicios disponibles para el crud de User

### Create:
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/product
```

Ejemplo entrada body JSON:
```bash
{
  "name": "string",
  "price": 0.
  "service": true
  "imgSrc": "string"
}
```
Ejemplo respuesta OK JSON:
```bash
{
  "id": 0,
  "name": "string",
  "price": 0,
  "service": true,
  "imgSrc": "string"
}
```

### Get
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/product/1
```
Ejemplo respuesta OK JSON:
```bash
{
  "id": 0,
  "name": "string",
  "price": 0.
  "isService": true
}
```

### GetByIsService
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/products/false
```
Ejemplo respuesta OK JSON:
```bash
[
  {
    "id": 0,
    "service": true,
    "name": "string",
    "price": 0,
    "imgSrc": "string"
  }
]
```

### GetAll:
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/products/
```

Ejemplo respuesta JSON:
```bash
[
  {
    "id": 0,
    "isService": true,
    "name": "string",
    "price": 0,
    "imgSrc": "string"
  }
]
```

### Update:
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/product/3
```
Ejemplo entrada body JSON:
```bash
{
  "isService": true,
  "name": "string",
  "price": 0,
  "imgSrc": "string"
}
```
Ejemplo respuesta OK JSON:
```bash
{
  "id": 0,
  "isService": true,
  "name": "string",
  "price": 0,
  "imgSrc": "string"
}
```

### Delete
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/user/6/delete/
```
Respuesta ok:
```bash
string
```

## CRUD UserProducts
A contiuación se describen los servicios disponibles para el crud de User

### Create:
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/item
```

Ejemplo entrada body JSON:
```bash
{
  "dateModified": "2021-12-02T17:49:48.012Z",
  "productId": 0,
  "quantity": 0,
  "userId": 0
}
```
Ejemplo respuesta OK JSON:
```bash
{
  "dateModified": "2021-12-02T17:49:48.021Z",
  "id": 0,
  "productId": 0,
  "quantity": 0,
  "userId": 0
}
```

### GetbyUserId
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/items/1
```
Ejemplo respuesta OK JSON:
```bash
[
  {
    "dateModified": "2021-12-02T17:51:02.876Z",
    "id": 0,
    "productId": 0,
    "quantity": 0,
    "userId": 0
  }
]
```

### GetbyUser&Product:
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/item/1/1
```

Ejemplo respuesta JSON:
```bash
{
  "dateModified": "2021-12-02T17:51:53.588Z",
  "id": 0,
  "productId": 0,
  "quantity": 0,
  "userId": 0
}
```

### Update:
Link del servicio
```bash
https://mintic-p5-g4-dw-be-projects.herokuapp.com/item
```
Ejemplo entrada body JSON:
```bash
{
  "dateModified": "2021-12-02T17:52:39.586Z",
  "productId": 0,
  "quantity": 0,
  "userId": 0
}
```
Ejemplo respuesta OK JSON:
```bash
{
  "dateModified": "2021-12-02T17:52:39.598Z",
  "id": 0,
  "productId": 0,
  "quantity": 0,
  "userId": 0
}
```

### Delete by UserId
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/item/userId/1
```
Respuesta ok:
```bash
string
```

### Delete by ProductId
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/item/productId/1
```
Respuesta ok:
```bash
string
```

### Delete by UserId % ProductId
Link del servicio
```bash
https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/item/1/1
```
Respuesta ok:
```bash
string
```


## Créditos por base del readme
[AdminLTE](https://github.com/ColorlibHQ/AdminLTE/blob/master/README.md)
