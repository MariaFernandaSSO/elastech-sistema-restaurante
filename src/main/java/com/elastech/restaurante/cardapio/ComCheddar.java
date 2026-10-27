package com.elastech.restaurante.cardapio;

public class ComCheddar extends Adicional {

    public ComCheddar(ItemCardapio item) {
        super(item);
    }

    @Override
    protected String getSufixoDescricao() {
        return " + cheddar";
    }

    @Override
    protected double getPrecoAdicional() {
        return 4.00;
    }
}
