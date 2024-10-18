package com.upiiz.pedidos.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upiiz.pedidos.models.Pedido;
import com.upiiz.pedidos.services.PedidosService;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidosController {
    // Requiero INYECTAR una dependencia del servicio
    PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    // GET - Todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos() {
        return ResponseEntity.ok(pedidosService.getAllPedidos());
    }

    // GET - Solo un pedido
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidosService.getPedidoById(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - Crear un pedido
    @PostMapping
    public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidosService.createPedido(pedido));
    }

    // PUT - Actualizar un pedido
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido, @PathVariable Long id) {
        pedido.setId(id);
        return ResponseEntity.ok(pedidosService.updatePedido(pedido));
    }

    // DELETE - Eliminar un pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidosService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
