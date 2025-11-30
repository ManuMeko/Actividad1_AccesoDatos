import Conexion.ConexionBD;
import database.EsquemaBD;
import service.ImportadorProductos;
import service.ConsultasProductos;
import service.EmpleadosPedidosService;

public class Main {
    public static void main(String[] args) {

        // 1. Conectar
        ConexionBD.getInstance();

        // 2. Crear tablas si no existen
        EsquemaBD.crearTablas();

        // 3. Importar productos desde la API
        ImportadorProductos.importarDesdeAPI();

        // 4. Consultas de productos
        ConsultasProductos.mostrarTodosProductos();
        ConsultasProductos.mostrarProductosPrecioInferior600();
        ConsultasProductos.insertarProductosFavMayor1000();
        ConsultasProductos.mostrarProductosFavoritos();

        // 5. Empleados y pedidos con Statement
        EmpleadosPedidosService.insertarEmpleadosYPedidos();
        EmpleadosPedidosService.mostrarPedidosConDetalle();
    }
}
