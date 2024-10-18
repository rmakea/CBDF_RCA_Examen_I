package com.upiiz.pedidos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upiiz.pedidos.models.Pedido;
import com.upiiz.pedidos.repository.PedidosRepository;

@Service
public class PedidosService {
    // Requerimos el REPO (Datos - Listado) - Pedidos
    PedidosRepository pedidosRepository;

    // Constructor - Cuando crea la instancia le pasa el repositorio
    public PedidosService(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    // GET - Todos los pedidos
    public List<Pedido> getAllPedidos() {
        return pedidosRepository.obtenerTodos();
    }

    // GET - Pedido por ID
    public Pedido getPedidoById(Long id) {
        return pedidosRepository.obtenerPorId(id);
    }

    // POST - Crear pedido
    public Pedido createPedido(Pedido pedido) {
        return pedidosRepository.guardar(pedido);
    }

    // PUT - Actualizar pedido
    public Pedido updatePedido(Pedido pedido) {
        return pedidosRepository.actualizar(pedido);
    }

    // DELETE - Eliminar pedido
    public void deletePedido(Long id) {
        pedidosRepository.eliminar(id);
    }
}
