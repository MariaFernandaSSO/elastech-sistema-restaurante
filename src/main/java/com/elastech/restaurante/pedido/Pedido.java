package com.elastech.restaurante.pedido;

import com.elastech.restaurante.cardapio.ItemCardapio;

import java.util.ArrayList;

public class Pedido {

    private final int numero;
    private String nomeCliente;
    private final ArrayList<ItemCardapio> itens = new ArrayList<>();

    public Pedido(int numero, String nomeCliente) {
        this.numero = numero;
        setNomeCliente(nomeCliente);
    }

    public int getNumero() {
        return numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente nao pode ser nulo ou vazio.");
        }
        this.nomeCliente = nomeCliente;
    }

    public void adicionarItem(ItemCardapio item) {
        if (item == null) {
            throw new IllegalArgumentException("O item nao pode ser nulo.");
        }
        itens.add(item);
    }

    public void removerItem(int indice) {
        if (indice < 0 || indice >= itens.size()) {
            throw new IllegalArgumentException("Indice de item invalido.");
        }
        itens.remove(indice);
    }

    public ArrayList<ItemCardapio> getItens() {
        return new ArrayList<>(itens);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }
        return total;
    }
}
