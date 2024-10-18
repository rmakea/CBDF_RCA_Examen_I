package com.upiiz.pedidos.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upiiz.pedidos.models.Pedido;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PedidosRepository {
    private static final String FILE_PATH = "C:/Users/Raul/OneDrive/Escritorio/WebClient/pedidos/src/main/resources/pedidos.json";
    private List<Pedido> pedidos = new ArrayList<>();
    private AtomicLong id = new AtomicLong();

    public PedidosRepository() {
        // Cargar pedidos del archivo JSON cuando se inicialice el repositorio
        this.leerPedidosDeArchivo();
    }

    // Método para guardar un nuevo pedido
    public Pedido guardar(Pedido pedido) {
        pedido.setId(this.id.incrementAndGet());
        this.pedidos.add(pedido);
        this.guardarPedidosEnArchivo();  // Guardar cambios en el archivo JSON
        return pedido;
    }

    // Método para obtener todos los pedidos
    public List<Pedido> obtenerTodos() {
        return this.pedidos;
    }

    // Método para obtener un pedido por su ID
    public Pedido obtenerPorId(Long id) {
        return this.pedidos.stream().filter(pedido -> pedido.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método para eliminar un pedido por su ID
    public void eliminar(Long id) {
        this.pedidos.removeIf(pedido -> pedido.getId().equals(id));
        this.guardarPedidosEnArchivo();  // Guardar cambios en el archivo JSON
    }

    // Método para actualizar un pedido
    public Pedido actualizar(Pedido pedido) {
        this.eliminar(pedido.getId());
        this.pedidos.add(pedido);
        this.guardarPedidosEnArchivo();  // Guardar cambios en el archivo JSON
        return pedido;
    }

    // Método privado para leer los pedidos desde el archivo JSON
    private void leerPedidosDeArchivo() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            File archivo = new File(FILE_PATH);
            if (archivo.exists()) {
                Pedido[] pedidosArray = objectMapper.readValue(archivo, Pedido[].class);
                if (pedidosArray != null) {
                    this.pedidos = new ArrayList<>(Arrays.asList(pedidosArray));

                    // Ajustar el contador de IDs al último ID en la lista
                    if (!this.pedidos.isEmpty()) {
                        Long maxId = this.pedidos.stream().mapToLong(Pedido::getId).max().orElse(0L);
                        this.id.set(maxId);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
    }


    // Método privado para guardar los pedidos en el archivo JSON
    private void guardarPedidosEnArchivo() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Registrar el módulo para manejar Java 8 fechas
            objectMapper.registerModule(new JavaTimeModule());

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), this.pedidos);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo JSON", e);
        }
    }
}
