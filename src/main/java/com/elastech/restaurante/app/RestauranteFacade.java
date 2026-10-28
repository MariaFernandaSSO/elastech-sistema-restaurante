package com.elastech.restaurante.app;

import com.elastech.restaurante.cardapio.ComBaconExtra;
import com.elastech.restaurante.cardapio.ComCheddar;
import com.elastech.restaurante.cardapio.ItemCardapio;
import com.elastech.restaurante.cardapio.Lanche;
import com.elastech.restaurante.cardapio.LancheFactory;
import com.elastech.restaurante.pedido.GerenciadorPedidos;
import com.elastech.restaurante.pedido.Pedido;

import java.util.Collection;
import java.util.List;

public class RestauranteFacade {

    private final GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public List<Lanche> listarCardapio() {
        return List.of(LancheFactory.xBurger(), LancheFactory.xSalada(), LancheFactory.xBacon());
    }

    public Lanche criarLanche(int opcao) {
        return switch (opcao) {
            case 1 -> LancheFactory.xBurger();
            case 2 -> LancheFactory.xSalada();
            case 3 -> LancheFactory.xBacon();
            default -> throw new IllegalArgumentException("Opcao de lanche invalida.");
        };
    }

    public ItemCardapio adicionarCheddar(ItemCardapio item) {
        return new ComCheddar(item);
    }

    public ItemCardapio adicionarBaconExtra(ItemCardapio item) {
        return new ComBaconExtra(item);
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

    public void adicionarItem(Pedido pedido, ItemCardapio item) {
        pedido.adicionarItem(item);
    }

    public void removerItem(Pedido pedido, int indice) {
        pedido.removerItem(indice);
    }

    public boolean cancelarPedido(int numero) {
        return gerenciador.removerPedido(numero);
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }
}
