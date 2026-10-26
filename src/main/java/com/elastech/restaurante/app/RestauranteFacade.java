package com.elastech.restaurante.app;

import com.elastech.restaurante.cardapio.Lanche;
import com.elastech.restaurante.pedido.GerenciadorPedidos;
import com.elastech.restaurante.pedido.Pedido;

import java.util.Collection;
import java.util.List;

public class RestauranteFacade {

    private final GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public List<Lanche> listarCardapio() {
        return List.of(
                new Lanche("X-Burger", 16.00),
                new Lanche("X-Salada", 18.00),
                new Lanche("X-Bacon", 22.00)
        );
    }

    public Lanche criarLanche(int opcao) {
        return switch (opcao) {
            case 1 -> new Lanche("X-Burger", 16.00);
            case 2 -> new Lanche("X-Salada", 18.00);
            case 3 -> new Lanche("X-Bacon", 22.00);
            default -> throw new IllegalArgumentException("Opcao de lanche invalida.");
        };
    }

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public Pedido buscarPedido(int numero) {
        return gerenciador.buscarPedido(numero);
    }

    public Collection<Pedido> listarPedidos() {
        return gerenciador.getPedidos();
    }

    public void adicionarItem(Pedido pedido, Lanche item) {
        pedido.adicionarItem(item);
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }
}
