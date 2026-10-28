package com.elastech.restaurante.pedido;

import com.elastech.restaurante.cardapio.ItemCardapio;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorPedidos {

    private final HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente) {
        Pedido novo = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novo.getNumero(), novo);
        contadorPedidos++;
        return novo;
    }

    public Pedido buscarPedido(int numero) {
        return pedidos.get(numero);
    }

    public Collection<Pedido> getPedidos() {
        return pedidos.values();
    }

    public boolean removerPedido(int numero) {
        return pedidos.remove(numero) != null;
    }

    public double calcularTotalDoDia() {
        double total = 0;
        for (Pedido pedido : pedidos.values()) {
            total += pedido.calcularTotal();
        }
        return total;
    }

    public Set<String> getCombinacoesDiferentes() {
        HashSet<String> combinacoes = new HashSet<>();
        for (Pedido pedido : pedidos.values()) {
            for (ItemCardapio item : pedido.getItens()) {
                combinacoes.add(item.getDescricao());
            }
        }
        return combinacoes;
    }
}
