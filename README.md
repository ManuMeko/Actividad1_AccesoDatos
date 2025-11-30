📘 Actividad 1 – Acceso a Datos

Proyecto desarrollado en Java + Maven que realiza operaciones de inserción, consulta y filtrado de datos en una base de datos MySQL utilizando JDBC.

🔧 Funcionalidades implementadas
✔ Conexión a MySQL usando JDBC
✔ Creación automática de las tablas necesarias
✔ Importación de 30 productos desde la API fake dummyjson.com
✔ Inserción de productos en la tabla productos
✔ Filtrado e inserción automática de productos con precio > 1000€ en productos_fav
✔ Listado de productos generales y filtrados
✔ Inserción de empleados y pedidos mediante Statement
✔ Relación pedidos ↔ empleado ↔ producto
✔ Muestra completa de los pedidos realizados

📂 Estructura del proyecto
Conexion/ConexionBD.java → conexión MySQL
database/EsquemaBD.java → creación de tablas
dao/ProductoDAO.java → inserción de productos
service/ImportadorProductos.java → descarga e importación de productos desde API
service/ConsultasProductos.java → consultas y filtros
service/EmpleadosPedidosService.java → inserción y listado de empleados/pedidos
model/… → clases de modelo
products.json → copia local del JSON de productos
pom.xml → dependencias Maven
▶ Ejecución
Ejecutar desde Main.java.
La aplicación:
Crea tablas
Importa productos
Inserta productos
Filtra productos favoritos
Inserta empleados y pedidos
Muestra listados completos

🎥 Demostración en vídeo
Se adjunta el archivo DemostraciónVideo.mp4 tal como pide la actividad.

📦 Entrega
Proyecto comprimido en .zip + este repositorio GitHub.
