package com.elastech.restaurante.cardapio;

public class ComBaconExtra extends Adicional {

    public ComBaconExtra(ItemCardapio item) {
        super(item);
    }

    @Override
    protected String getSufixoDescricao() {
        return " + bacon extra";
    }

    @Override
    protected double getPrecoAdicional() {
        return 6.00;
    }
}
