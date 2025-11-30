package service;

import Conexion.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpleadosPedidosService {

    public static void insertarEmpleadosYPedidos() {
        System.out.println("\n===== INSERTANDO EMPLEADOS Y PEDIDOS (Statement) =====");

        Connection conn = ConexionBD.getInstance().getConnection();

        try (Statement stmt = conn.createStatement()) {

            // 1) Limpiamos primero para que al ejecutar varias veces no se duplique todo
            String borrarPedidos = "DELETE FROM pedidos";
            String borrarEmpleados = "DELETE FROM empleados";
            String resetAutoIncrementEmpleados = "ALTER TABLE empleados AUTO_INCREMENT = 1";

            stmt.executeUpdate(borrarPedidos);
            stmt.executeUpdate(borrarEmpleados);
            stmt.executeUpdate(resetAutoIncrementEmpleados);

            // 2) Insertamos varios empleados (ids serán 1, 2 y 3)
            String insertarEmpleados = """
                    INSERT INTO empleados (nombre, puesto, salario) VALUES
                    ('Ana López', 'Vendedora', 1800),
                    ('Carlos García', 'Responsable de almacén', 2000),
                    ('María Pérez', 'Atención al cliente', 1700)
                    """;

            int filasEmpleados = stmt.executeUpdate(insertarEmpleados);
            System.out.println("✔ Insertados " + filasEmpleados + " empleados.");

            // 3) Insertamos pedidos referenciando a empleados 1, 2 y 3
            String insertarPedidos = """
                    INSERT INTO pedidos (id_empleado, id_producto, cantidad, fecha) VALUES
                    (1, 11, 2, '2025-01-10'),
                    (1, 12, 1, '2025-01-11'),
                    (2, 20, 10, '2025-01-11'),
                    (3, 28, 5, '2025-01-12')
                    """;

            int filasPedidos = stmt.executeUpdate(insertarPedidos);
            System.out.println("✔ Insertados " + filasPedidos + " pedidos.");

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar empleados y pedidos.");
            e.printStackTrace();
        }
    }

    public static void mostrarPedidosConDetalle() {
        System.out.println("\n===== LISTADO DE PEDIDOS (empleado + producto) =====");

        String sql = """
                SELECT pe.id,
                       e.nombre AS empleado,
                       p.nombre AS producto,
                       pe.cantidad,
                       pe.fecha
                FROM pedidos pe
                JOIN empleados e ON pe.id_empleado = e.id
                JOIN productos p ON pe.id_producto = p.id
                """;

        Connection conn = ConexionBD.getInstance().getConnection();

        try (Statement stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idPedido = rs.getInt("id");
                String empleado = rs.getString("empleado");
                String producto = rs.getString("producto");
                int cantidad = rs.getInt("cantidad");
                java.sql.Date fecha = rs.getDate("fecha");

                System.out.printf("Pedido %d | Empleado: %s | Producto: %s | Cantidad: %d | Fecha: %s%n",
                        idPedido, empleado, producto, cantidad, fecha.toString());
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al mostrar los pedidos.");
            e.printStackTrace();
        }
    }
}
