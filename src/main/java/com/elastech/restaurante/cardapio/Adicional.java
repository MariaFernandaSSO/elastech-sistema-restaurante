package com.elastech.restaurante.cardapio;

public abstract class Adicional implements ItemCardapio {

    protected final ItemCardapio item;

    protected Adicional(ItemCardapio item) {
        if (item == null) {
            throw new IllegalArgumentException("O item nao pode ser nulo.");
        }
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + getSufixoDescricao();
    }

    @Override
    public double getPreco() {
        return item.getPreco() + getPrecoAdicional();
    }

    protected abstract String getSufixoDescricao();

    protected abstract double getPrecoAdicional();
}
