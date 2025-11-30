package service;

import Conexion.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultasProductos {

    public static void mostrarTodosProductos() {
        System.out.println("\n===== TODOS LOS PRODUCTOS =====");

        String sql = "SELECT id, nombre, descripcion, cantidad, precio FROM productos";

        Connection conn = ConexionBD.getInstance().getConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                System.out.printf("ID: %d | Nombre: %s | Cantidad: %d | Precio: %.2f €%n",
                        id, nombre, cantidad, precio);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al mostrar todos los productos.");
            e.printStackTrace();
        }
    }

    public static void mostrarProductosPrecioInferior600() {
        System.out.println("\n===== PRODUCTOS CON PRECIO < 600€ =====");

        String sql = "SELECT id, nombre, descripcion, cantidad, precio FROM productos WHERE precio < 600";

        Connection conn = ConexionBD.getInstance().getConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                System.out.printf("ID: %d | Nombre: %s | Cantidad: %d | Precio: %.2f €%n",
                        id, nombre, cantidad, precio);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al mostrar productos con precio < 600€.");
            e.printStackTrace();
        }
    }

    public static void insertarProductosFavMayor1000() {
        System.out.println("\n===== INSERTANDO PRODUCTOS FAVORITOS (precio > 1000€) =====");

        // Para evitar duplicados cada vez que ejecutas el programa:
        String limpiarFav = "TRUNCATE TABLE productos_fav";
        String insertarFav = "INSERT INTO productos_fav (id_producto) " +
                "SELECT id FROM productos WHERE precio > 1000";

        Connection conn = ConexionBD.getInstance().getConnection();

        try (Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(limpiarFav);
            int filas = stmt.executeUpdate(insertarFav);

            System.out.println("✔ Insertados " + filas + " productos en 'productos_fav'.");

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar productos favoritos.");
            e.printStackTrace();
        }
    }

    public static void mostrarProductosFavoritos() {
        System.out.println("\n===== PRODUCTOS FAVORITOS (precio > 1000€) =====");

        String sql = """
                SELECT p.id, p.nombre, p.descripcion, p.cantidad, p.precio
                FROM productos_fav pf
                JOIN productos p ON pf.id_producto = p.id
                """;

        Connection conn = ConexionBD.getInstance().getConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                System.out.printf("ID: %d | Nombre: %s | Cantidad: %d | Precio: %.2f €%n",
                        id, nombre, cantidad, precio);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al mostrar productos favoritos.");
            e.printStackTrace();
        }
    }
}
