package com.elastech.restaurante.cardapio;

public final class LancheFactory {

    private LancheFactory() {
    }

    public static Lanche xBurger() {
        return new Lanche("X-Burger", 16.00);
    }

    public static Lanche xSalada() {
        return new Lanche("X-Salada", 18.00);
    }

    public static Lanche xBacon() {
        return new Lanche("X-Bacon", 22.00);
    }
}
