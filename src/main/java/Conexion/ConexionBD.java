package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instance;
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/almacen";
    private final String USER = "root";       // <-- cámbialo si tu usuario NO es root
    private final String PASSWORD = "";       // <-- pon aquí tu contraseña si tienes

    // Constructor privado → patrón Singleton
    private ConexionBD() {
        conectar();
    }

    private void conectar() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✔ Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    // Método estático para obtener la instancia única
    public static ConexionBD getInstance() {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    // Devuelve la conexión, y si está cerrada la vuelve a abrir
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                conectar();
            }
        } catch (SQLException e) {
            System.out.println("❌ Error comprobando el estado de la conexión.");
            e.printStackTrace();
        }
        return connection;
    }
}
