# 🐦 social

- [🏗 Arquitectura y componentes de la aplicación](#-arquitectura-y-componentes-de-la-aplicación)
  - [📦 Estructura / Taxonomía de paquetes](#-estructura--taxonomía-de-paquetes)
- [🛠 Hecho con](#-hecho-con)
- [🚀 Levantar el proyecto](#-levantar-el-proyecto)
  - [📋 Pre-requisitos](#-pre-requisitos)
  - [✅ Pruebas en entorno local](#-pruebas-en-entorno-local)
    - [📋 Casos de uso](#-casos-de-uso)
      - [Tweets](#tweets)
      - [Follow](#follow)
      - [Timeline](#timeline)

## 🏗️ Arquitectura y componentes de la aplicación

Se opta por un enfoque orientado a la arquitectura hexagonal ya que simplifica el desarrollo al separar la lógica
principal de la aplicación de los detalles técnicos.
Esto hace que el código sea más fácil de mantener y probar, lo que ayuda a desarrollar de forma más rápida y segura.

En resumen, este enfoque permite construir una aplicación más sólida y flexible.

### 📦 Estructura / Taxonomía de paquetes

- `application`: Puente entre las capas de dominio e infraestructura.
  - `port`: Definición de los puertos de salida de la aplicación que serán implementados en la capa de
    infraestructura.
  - `usecase`: Orquesta las operaciones de la aplicación y aplicar reglas de negocio (casos de uso).
- `domain`: Definición de entidades que representan los objetos de dominio. Es independiente de cualquier tecnología o
  infraestructura específica.
- `infrastructure`: Implementaciones concretas de las interfaces definidas en la capa de puertos. Incluye adaptadores
  para DB, servicios externos y cualquier otra tecnología utilizada en la aplicación.
  - `configuration`: Configuración de elementos provistos para la aplicación.
  - `[nombre-tecología]`: Teconología específica que implementa el puerto de entrada/salida (Ej: _jdbc_, _redis_,
    _controller_, etc.).
    - `model`: Objetos de valor utilizados en la capa.

## 🛠 Hecho con

- Java (JDK 17)
- Gradle
- SpringBoot (v3.2.5)
- Spring Data JDBC
- PostgreSQL

## 🚀 Levantar el proyecto

### 📋 Pre-requisitos

- Java (17.*)
- Docker Compose

### ✅ Pruebas en entorno local

Para correr las pruebas unitarias del proyecto, ejecutar en una terminal ubicados en la raíz el proyecto el siguiente comando:

```bash
./gradlew test
```

Para hacer `build`, ubicados en la raíz del proyecto ejecutar el siguiente comando:

```bash
./gradlew clean build
```

Una vez finalizada la instancia de `build`, ejecutar el siguiente comando:

```bash
docker-compose up --build -d
```

Siguiendo estos pasos, se podrán probar los endpoints de la aplicación a través de `http://localhost:8080/`.

El ambiente de pruebas cuenta con tres usuarios:

|Id|Username|
|-|-|
|1|walter.white|
|2|jesse.pinkman|
|3|saul.goodman|

#### 📋 Casos de uso

##### Tweets

- `HTTP POST /api/v1/users/{userId}/tweets`

Cuerpo de petición

```json
{
  "content": "..."
}
```

Ejemplo con cURL

```bash
curl --location 'http://localhost:8080/api/v1/users/1/tweets' \
--header 'Content-Type: application/json' \
--data '{
    "content": "I am the danger"
}'
```

Respuesta esperada en caso de éxito

`201 Created`

```json
{
  "id": 1,
  "content": "I am the danger",
  "created_at": "2024-04-28T10:00:00.000",
  "user": {
    "id": 1,
    "username": "walter.white"
  }
}
```

##### Follow

- `HTTP POST /api/v1/users/{userId}/follows`

Cuerpo de petición

```json
{
  "followed_user_id": 1
}
```

Ejemplo con cURL

```bash
curl --location 'http://localhost:8080/api/v1/users/3/follows' \
--header 'Content-Type: application/json' \
--data '{
    "followed_user_id": 1
}'
```

Respuesta esperada en caso de éxito

`204 No Content`

##### Timeline

- `HTTP GET /api/v1/users/{userId}/timeline`

```bash
curl --location 'http://localhost:8080/api/v1/users/2/timeline'
```

Respuesta esperada en caso de éxito

`200 OK`

```json
[
  {
    "id": 1,
    "content": "I am the danger",
    "created_at": "2024-04-28T10:00:00.000",
    "user": {
      "id": 1,
      "username": "walter.white"
    }
  }
]
```
