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

    public static Lanche xEspecial() {
        return new Lanche("X-Especial", 25.00);
    }

    public static Lanche xFrango() {
        return new Lanche("X-Frango", 20.00);
    }

    public static Lanche xEgg() {
        return new Lanche("X-Egg", 19.00);
    }

    public static Lanche pastel() {
        return new Lanche("Pastel", 10.00);
    }
}
