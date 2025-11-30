package database;

import Conexion.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EsquemaBD {

    public static void crearTablas() {
        String tablaProductos = """
                CREATE TABLE IF NOT EXISTS productos (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL,
                    descripcion TEXT,
                    cantidad INT NOT NULL,
                    precio DOUBLE NOT NULL
                ) ENGINE=InnoDB;
                """;

        String tablaProductosFav = """
                CREATE TABLE IF NOT EXISTS productos_fav (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    id_producto INT NOT NULL,
                    CONSTRAINT fk_producto_fav
                        FOREIGN KEY (id_producto) REFERENCES productos(id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                ) ENGINE=InnoDB;
                """;

        String tablaEmpleados = """
                CREATE TABLE IF NOT EXISTS empleados (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL,
                    puesto VARCHAR(100),
                    salario DOUBLE
                ) ENGINE=InnoDB;
                """;

        String tablaPedidos = """
                CREATE TABLE IF NOT EXISTS pedidos (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    id_empleado INT NOT NULL,
                    id_producto INT NOT NULL,
                    cantidad INT NOT NULL,
                    fecha DATE NOT NULL,
                    CONSTRAINT fk_pedido_empleado
                        FOREIGN KEY (id_empleado) REFERENCES empleados(id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CONSTRAINT fk_pedido_producto
                        FOREIGN KEY (id_producto) REFERENCES productos(id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                ) ENGINE=InnoDB;
                """;

        Connection conn = ConexionBD.getInstance().getConnection();

        try (Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(tablaProductos);
            stmt.executeUpdate(tablaProductosFav);
            stmt.executeUpdate(tablaEmpleados);
            stmt.executeUpdate(tablaPedidos);

            System.out.println("✔ Tablas creadas/actualizadas correctamente.");

        } catch (SQLException e) {
            System.out.println("❌ Error al crear las tablas.");
            e.printStackTrace();
        }
    }
}
