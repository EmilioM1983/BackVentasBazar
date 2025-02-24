# BackVentasBazar

## Descripción
BackVentasBazar es un backend desarrollado con Spring Boot para gestionar ventas en un sistema de bazar. Permite registrar clientes, productos y ventas, asegurando un flujo de compra eficiente y organizado.

## Características
- Registro y gestión de clientes.
- Registro y gestión de productos.
- Registro de ventas con detalles de productos y clientes.
- API REST para integración con un frontend o sistema externo.

## Tecnologías Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA** (para la gestión de base de datos)
- **MySQL** (como base de datos relacional)
- **Lombok** (para reducir el código boilerplate)
- **Swagger** (para documentación de la API)
- **Maven** (para la gestión de dependencias)

## Instalación y Configuración
1. Clona el repositorio:
   ```sh
   git clone https://github.com/EmilioM1983/BackVentasBazar.git
   ```
2. Accede al directorio del proyecto:
   ```sh
   cd BackVentasBazar
   ```
3. Configura la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ventas_bazar
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Ejecuta el proyecto con Maven:
   ```sh
   mvn spring-boot:run
   ```

## Endpoints Principales
### Clientes
- **GET** `/clientes` - Listar todos los clientes.
- **POST** `/clientes` - Registrar un nuevo cliente.

### Productos
- **GET** `/productos` - Listar todos los productos.
- **POST** `/productos` - Registrar un nuevo producto.

### Ventas
- **GET** `/ventas` - Listar todas las ventas.
- **POST** `/ventas` - Registrar una nueva venta.

## Contribución
Si deseas contribuir, por favor crea un *fork* del repositorio y envía un *pull request* con tus mejoras.

## Autor
**Emilio M.**
- GitHub: [EmilioM1983](https://github.com/EmilioM1983)

