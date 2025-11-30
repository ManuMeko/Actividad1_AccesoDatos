package dao;

import Conexion.ConexionBD;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductoDAO {

    public static void insertarProductos(List<Producto> productos) {

        String sql = "INSERT INTO productos (nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Producto p : productos) {
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getDescripcion());
                ps.setInt(3, p.getCantidad());
                ps.setDouble(4, p.getPrecio());
                ps.addBatch();
            }

            int[] resultados = ps.executeBatch();
            System.out.println("✔ Insertados " + resultados.length + " productos en la tabla 'productos'.");

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar productos.");
            e.printStackTrace();
        }
    }
}

