package com.upiiz.pedidos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pedido {
    private Long id; // ID entero autoincremental
    private LocalDateTime fechaPedido; // Fecha de pedido
    private int idCliente; // ID del cliente
    private double total; // Total en decimal

    // Constructor


    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime fechaPedido, int idCliente, double total) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.idCliente = idCliente;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
