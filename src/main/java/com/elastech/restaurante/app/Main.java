package com.elastech.restaurante.app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RestauranteFacade facade = new RestauranteFacade();
        try (Scanner scanner = new Scanner(System.in)) {
            MenuConsole menu = new MenuConsole(facade, scanner);
            int opcao;
            do {
                menu.exibirMenu();
                opcao = menu.lerOpcao();
                switch (opcao) {
                    case 1 -> menu.registrarPedido();
                    case 2 -> menu.listarPedidos();
                    case 3 -> menu.modificarPedido();
                    case 4 -> menu.fecharRestaurante();
                    case 0 -> {
                    }
                    default -> menu.opcaoInvalida();
                }
            } while (opcao != 0);
        }
    }
}
