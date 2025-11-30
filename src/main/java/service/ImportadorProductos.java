package service;

import com.google.gson.Gson;
import dao.ProductoDAO;
import model.DummyResponse;
import model.Producto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ImportadorProductos {

    private static final String URL_API = "https://dummyjson.com/products";

    public static void importarDesdeAPI() {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();

                Gson gson = new Gson();
                DummyResponse dummyResponse = gson.fromJson(json, DummyResponse.class);

                List<Producto> productos = dummyResponse.getProducts()
                        .stream()
                        .map(dp -> new Producto(
                                dp.getTitle(),          // nombre
                                dp.getDescription(),    // descripción
                                dp.getStock(),          // cantidad
                                dp.getPrice()           // precio
                        ))
                        .toList();

                ProductoDAO.insertarProductos(productos);

                System.out.println("✔ Importados " + productos.size() + " productos desde la API.");

            } else {
                System.out.println("❌ Error al llamar a la API. Código HTTP: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error al descargar o procesar el JSON de la API.");
            e.printStackTrace();
        }
    }
}
