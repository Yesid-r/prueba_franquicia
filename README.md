# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

# Arquitectura Limpia - API de Franquicias

Este proyecto implementa una API para la gestión de datos de franquicias utilizando programación reactiva y arquitectura limpia.


## Descripción General

La API permite realizar operaciones como gestionar franquicias, sucursales y productos.
Una franquicia está compuesta por:
- Un nombre
- Una lista de sucursales

Cada sucursal incluye:
- Un nombre
- Una lista de productos ofrecidos

Cada producto contiene:
- Un nombre
- Una cantidad de stock

---

## Requisitos

- Java (versión 21 o superior)
- Gradle para la gestión de dependencias
- MongoDB (base de datos)
- Postman (para pruebas de la API)

---

## Endpoints

### 1. **Guardar una Franquicia**
**Método:** POST  
**URL:** `http://localhost:8083/api/v1/functional/franquicia`  
**Cuerpo:**
```json
{
    "nombre": "Tesla"
}
```

### 2. **Guardar una Sucursal**
**Método:** POST  
**URL:** `http://localhost:8083/api/v1/functional/sucursal`  
**Cuerpo:**
```json
{
    "nombre": "Toronto",
    "franquiciaId": "67846e7f6470af4b785bc327"
}
```

### 3. **Guardar un Producto en una Sucursal**
**Método:** POST  
**URL:** `http://localhost:8083/api/v1/functional/producto`  
**Cuerpo:**
```json
{
    "nombre": "CyberTruck2",
    "stock": 20,
    "sucursalId": "67846ead6470af4b785bc328"
}
```

### 4. **Eliminar un Producto de una Sucursal**
**Método:** DELETE  
**URL:** `http://localhost:8083/api/v1/functional/producto/{idProducto}`

### 5. **Actualizar Stock de un Producto**
**Método:** PUT  
**URL:** `http://localhost:8083/api/v1/functional/producto/{idProducto}/stock`  
**Cuerpo:**
```json
{
    "cantidad": 25
}
```

### 6. **Actualizar Nombre de un Producto**
**Método:** PUT  
**URL:** `http://localhost:8083/api/v1/functional/producto/{idProducto}/nombre`  
**Cuerpo:**
```json
{
    "nombre": "Cyber Truck"
}
```

### 7. **Actualizar Nombre de una Franquicia**
**Método:** PUT  
**URL:** `http://localhost:8083/api/v1/functional/franquicia/{idFranquicia}/nombre`  
**Cuerpo:**
```json
{
    "nombre": "Tesla by Elon Musk"
}
```

### 8. **Actualizar Nombre de una Sucursal**
**Método:** PUT  
**URL:** `http://localhost:8083/api/v1/functional/sucursal/{idSucursal}/nombre`  
**Cuerpo:**
```json
{
    "nombre": "Texas"
}
```

### 9. **Listar Productos con Mayor Stock por Franquicia**
**Método:** GET  
**URL:** `http://localhost:8083/api/v1/functional/franquicia/{idFranquicia}/mayor-stock`

---

## Despliegue

1. Clonar el repositorio:
   ```bash
   git clone <https://github.com/Yesid-r/prueba_franquicia.git>
   ```
2. Ejecutar en local:
   ```bash
   gradle clean bootRun
   ```

---

## Pruebas

Puedes utilizar la colección de Postman proporcionada para probar la API. Importa el archivo de la colección (`clean-architecture-pruebatecnica.postman_collection.json`) en Postman y ejecuta las solicitudes.

---

## Autor
Generado por Yesid Rincón siguiendo principios de arquitectura limpia.