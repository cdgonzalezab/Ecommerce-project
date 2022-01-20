# [Guardería - API-Gateway](https://mintic-p5-g4-dw-apigateway.herokuapp.com/)

**Guardería - API-Gateway** Gateway para los microservicios de [**backend**](https://mintic2022-p5-g4-dw-be-auth.herokuapp.com/api/schema/swagger-ui/#/) y [**frontend**](https://mintic-p5-g4-dw-be-projects.herokuapp.com/swagger-ui/#/product-controller). En este microservicio, se programa parte de la lógica del programa y generan validaciones para el consumo de las API referenciadas anteriormente.

**Url con Graphql query console [https://mintic-p5-g4-dw-apigateway.herokuapp.com/](https://mintic-p5-g4-dw-apigateway.herokuapp.com/)**

#### Resumen
| Servicio | Mutations | Mutations | Mutations | Queries | Queries | Queries |
| --------- | --------- | --------- | --------- | --------- | --------- | --------- |
| Token | logIn | refreshToken | verifyToken | | | | |
| User DB | SignUpUser | updateUser | deleteUser | products | userList | userDetailById |
| Products DB | createProduct | updateProduct | deleteProduct | productById | products |
| UserProducts DB | createItem | updateItem | deleteItem <br> deleteItemsByUserId | getItem | getItems |


## User Mutations & Queries
A contiuación se describen los servicios disponibles para el crud de User. Existe el rol de administrador que puede modificar usuarios tanto administradores como
no adimistradores. Este tipo de usuario, también puede obtener información específica de cualquier usuario y un listado de todos los usuarios existentes.

### SignUpUser
Ejemplo entrada función
```bash
mutation SignUpUser($userInput: SignUpInput!) {
  signUpUser(userInput: $userInput) {
    refresh
  }
}
```
Ejemplo variable
```bash
{
  "userInput": {
    "username": "String",
    "phone": "Int"
  }
}
```
Ejemplo de respuesta OK
```bash
 "data": {
    "signUpUser": {
      "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYzODM4OTE0OCwianRpIjoiNTFmZjM3NjFmZWRmNGY0MDk5MDEyZmRhZWMzOTcyMjkiLCJ1c2VyX2lkIjoxN30.Rs6PzsPCruPlqqF3Idq-1dS8wwDm9VlBbIJFpl0eCic",
      "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM4MzAzNjQ4LCJqdGkiOiI3ODI1ODQ1OTg1ZmE0NWQ5OTljNzUzNDNiYWJlN2FlMyIsInVzZXJfaWQiOjE3fQ.-fkijfOFpyisqX_TJckPxYr1dXiEpTIq-bJctG6dDT8"
    }
  }
```

### userDetailById 
Ejemplo de función:
```bash
query UserDetailById($userId: Int) {
  userDetailById(userId: $userId) {
    id
  }
}
```
Ejemplo de variable
```bash
{
  "userId": 11
}
```
Ejemplo de respuesta OK
```bash
{
  "data": {
    "userDetailById": {
      "id": 11,
      "username": "postman3"
    }
  }
}
```

### userList:
Ejemplo de función:
```bash
query UserList {
  userList {
    id
    username
    name
  }
}
```
Ejemplo de respuesta OK
```bash
  "data": {
    "userList": [
      {
        "id": 8,
        "username": "postman1",
        "name": "postman"
      },
      {
        "id": 9,
        "username": "graph1",
        "name": "graph1"
      },
```

### updateUser:

Ejemplo de función:
```bash
mutation UpdateUser($putUser: SignUpInput!, $userId: Int) {
  updateUser(putUser: $putUser, userId: $userId) {
    id
    name
    username
  }
}
```
Ejemplo de variable
```bash
  "putUser": {
    "username": "graphqlmodi",
    "password": "contraseña",
    "name": "graphqmodi"
  }
```
Ejemplo de respuesta OK
```bash
"data": {
    "updateUser": {
      "id": 16,
      "username": "graphqlmodi",
      "name": "graphqmodi"
    }
  }
```

### deleteUser
Ejemplo de función:
```bash
mutation Mutation {
  deleteUser
}
```
Ejemplo de respuesta OK
```bash
 "data": {
    "deleteUser": ""
  }
```


## Product Mutations & Queries
A contiuación se describen los servicios disponibles para el crud de Product. Para cualquier mutation hecha a esta DB, se necesita ser usuario ADMIN, en caso contrario, no será posible modificar los productos.

### createProduct
Ejemplo entrada función
```bash
mutation CreateProduct($inputProduct: inProduct!) {
  createProduct(inputProduct: $inputProduct) {
    id
    name
    price
  }
}
```
Ejemplo variable
```bash
  "inputProduct": {
    "id": 7,
    "name": "graphql1",
    "price": 100000
  }
```
Ejemplo de respuesta OK
```bash
  "data": {
    "createProduct": {
      "id": 7,
      "name": "graphql1",
      "price": 100000,
    }
  }
```

### productById 
Ejemplo de función:
```bash
query ProductById($productByIdId: Int!) {
  productById(id: $productByIdId) {
    id
    name
    price
  }
}
```
Ejemplo de variable
```bash
{
  "productByIdId": 1
}
```
Ejemplo de respuesta OK
```bash
  "data": {
    "productById": {
      "id": 1,
      "name": "vacunación",
      "price": 100000
    }
  }
```

### userList:
Ejemplo de función:
```bash
query ProductById {
  products {
    id
    name
    price
  }
}
```
Ejemplo de respuesta OK
```bash
"data": {
    "products": [
      {
        "id": 1,
        "name": "vacunación",
        "price": 100000,
      },
      {
        "id": 2,
        "name": "esterilización",
        "price": 100000,
      },
```

### updateUser:

Ejemplo de función:
```bash
mutation UpdateProduct($updateProductId: Int!, $product: updateProduct!) {
  updateProduct(id: $updateProductId, product: $product) {
    id
    name
    price
  }
}
```
Ejemplo de variable
```bash
  "updateProductId": 7,
  "product": {
    "name": "modi11",
    "price": 100000,
  }
```
Ejemplo de respuesta OK
```bash
   "data": {
    "updateProduct": {
      "id": 7,
      "name": "graphql1modi",
      "price": 100000
    }
  }
```

### deleteProduct
Ejemplo de función:
```bash
mutation UpdateProduct($deleteProductId: Int!) {
  deleteProduct(id: $deleteProductId)
}
```
Ejemplo de respuesta OK
```bash
"data": {
    "deleteProduct": "producto eliminado"
  }
```


## UserProduct Mutations & Queries
A contiuación se describen los servicios disponibles para el crud de Product. Este crud va haica los productos comprados por los usuarios. Tiene restricciones para no tener más de dos product ID por user Id.

### createItem
Ejemplo entrada función
```bash
mutation CreateUserProduct($inputItem: inUserProduct!) {
  createUserProduct(inputItem: $inputItem) {
    id
    userId
    productId
    quantity
    dateModified
  }
}
```
Ejemplo variable
```bash
{
  "inputItem": {
    "productId": 0,
    "quantity": 0
  }
}
```
Ejemplo de respuesta OK
```bash
{
  "data": {
    "createUserProduct": {
      "id": 91,
      "userId": 7,
      "productId": 100,
      "quantity": 1,
      "dateModified": "2021-12-15T01:51:57.536+00:00"
    }
  }
}
```

### Update Item
Ejemplo de función:
```bash
mutation UpdateUserProduct($inUpdateItem: inUserProduct!) {
  updateUserProduct(inUpdateItem: $inUpdateItem) {
    id
    userId
    productId
    quantity
    dateModified
  }
}
```
Ejemplo de variable
```bash
{
  "inUpdateItem": {
    "productId": 100,
    "quantity": 2
  }
}
```
Ejemplo de respuesta OK
```bash
{
  "data": {
    "updateUserProduct": {
      "id": 91,
      "userId": 7,
      "productId": 100,
      "quantity": 2,
      "dateModified": "2021-12-15T01:51:57.536+00:00"
    }
  }
}
```

### Delete Item:
Ejemplo de función:
```bash
mutation DeleteUserProduct($productId: Int!) {
  deleteUserProduct(productId: $productId)
}
```
Ejemplo de variable
```bash
{
  "productId": 100
}
```
Ejemplo de respuesta OK
```bash
{
  "data": {
    "deleteUserProduct": "Product id deleted: 100"
  }
}
```

### GeItem:

Ejemplo de función:
```bash
query GetUserProduct($userId: Int!, $productId: Int!) {
  getUserProduct(userId: $userId, productId: $productId) {
    id
    userId
    productId
    quantity
    dateModified
  }
}
```
Ejemplo de variable
```bash
{ 
  "userId": 0,
  "productId": 0
}
```
Ejemplo de respuesta OK
```bash
{
  "data": {
    "getUserProduct": {
      "id": 60,
      "userId": 7,
      "productId": 25,
      "quantity": 1,
      "dateModified": "2021-12-12T16:35:12.653+00:00"
    }
  }
}
```

### GetItems by userId
Ejemplo de función:
```bash
query GetUserProductsByUserId($getUserProductsByUserIdId: Int!) {
  getUserProductsByUserId(id: $getUserProductsByUserIdId) {
    id
    userId
    productId
    quantity
    dateModified
  }
}
```
Ejemplo de variable
```bash
{
  "getUserProductsByUserIdId": 7
}
```
Ejemplo de respuesta OK
```bash
{
  "data": {
    "getUserProductsByUserId": [
      {
        "id": 60,
        "userId": 7,
        "productId": 25,
        "quantity": 1,
        "dateModified": "2021-12-12T16:35:12.653+00:00"
      }
    ]
  }
}
```


## Tokens
A contiuación se describen los servicios para el manejo de los tokens.

### logIn
Ejemplo entrada función
```bash
mutation UpdateProduct($credentials: CredentialsInput!) {
  logIn(credentials: $credentials) {
    refresh
    access
  }
}
```
Ejemplo variable
```bash
  "credentials": {
    "username": "String",
    "password": "String"
  }
```
Ejemplo de respuesta OK
```bash
"data": {
    "logIn": {
      "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYzODQwNzE1NywianRpIjoiNmExNTNkOWEzZGRjNDU2OGExNzAwZmE2ZGJhYjAyZTgiLCJ1c2VyX2lkIjoxMX0._RY95tR_BiAEcdIdfoYRlnRAvPz-YmdT1-qAE86wCYA",
      "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM4MzIxNjU3LCJqdGkiOiIyMTMzMzAxZTUzZTY0MzhiYTdkODEwNzhiMDdlZTQyZSIsInVzZXJfaWQiOjExfQ.3gFLUm9q-0twF-_QesrEg1eiXK354_lqrb65_ZpvgwU"
    }
  }
```

### refreshToken
Ejemplo de función:
```bash
mutation UpdateProduct($refresh: String!) {
  refreshToken(refresh: $refresh) {
    access
  }
}
```
Ejemplo de variable
```bash
{
   "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYzODQwNzM1MiwianRpIjoiYzRiMjM0ZmZjNWZhNDBjZmFkOGQwZjYwYzRlNjAwMGYiLCJ1c2VyX2lkIjo3fQ.iNJbPEEsf8IbFf5m0OPYqAm9hrT9qe4ZfJ5NFbgTq4E"
}
```
Ejemplo de respuesta OK
```bash
 "data": {
    "refreshToken": {
      "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM4MzIxODY4LCJqdGkiOiJiNTY4MWU4YTAyMzc0ZGQ0YjI0ODNlYzI5NGExNDcwZiIsInVzZXJfaWQiOjd9.gWmG6zd0dhX4osx-ciQbVVsAcZeLeo_V5rzS3RDrZVg"
    }
  }
```


## Créditos por base del readme
[AdminLTE](https://github.com/ColorlibHQ/AdminLTE/blob/master/README.md)
