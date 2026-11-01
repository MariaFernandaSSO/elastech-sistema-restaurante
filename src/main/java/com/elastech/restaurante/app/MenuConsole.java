package com.elastech.restaurante.app;

import com.elastech.restaurante.cardapio.Lanche;
import com.elastech.restaurante.pedido.Pedido;

import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    private final RestauranteFacade facade;
    private final Scanner scanner;

    public MenuConsole(RestauranteFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        System.out.println();
        System.out.println("=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        return lerInteiro("Opcao: ");
    }

    public void opcaoInvalida() {
        System.out.println("Opcao invalida. Tente novamente.");
    }

    public void registrarPedido() {
        Pedido pedido = null;
        while (pedido == null) {
            String nome = lerTexto("Nome do cliente: ");
            try {
                pedido = facade.criarPedido(nome);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        boolean adicionarMais;
        do {
            Lanche item = montarItem();
            facade.adicionarItem(pedido, item);
            System.out.println("Item adicionado.");
            adicionarMais = lerSimNao("Deseja adicionar outro item? (s/n): ");
        } while (adicionarMais);

        System.out.printf("Pedido %d registrado para %s. Total: R$ %.2f%n",
                pedido.getNumero(), pedido.getNomeCliente(), pedido.calcularTotal());
    }

    public void listarPedidos() {
        List<Pedido> pedidos = List.copyOf(facade.listarPedidos());
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        for (Pedido pedido : pedidos) {
            exibirPedido(pedido);
        }
    }

    private void exibirPedido(Pedido pedido) {
        System.out.println();
        System.out.printf("Pedido %d - Cliente: %s%n", pedido.getNumero(), pedido.getNomeCliente());
        List<Lanche> itens = pedido.getItens();
        if (itens.isEmpty()) {
            System.out.println("  (sem itens)");
        }
        for (int i = 0; i < itens.size(); i++) {
            Lanche item = itens.get(i);
            System.out.printf("  %d. %s - R$ %.2f%n", i + 1, item.getNome(), item.getPreco());
        }
        System.out.printf("  Total: R$ %.2f%n", pedido.calcularTotal());
    }

    private Lanche montarItem() {
        List<Lanche> cardapio = facade.listarCardapio();
        System.out.println();
        System.out.println("=== CARDAPIO ===");
        for (int i = 0; i < cardapio.size(); i++) {
            Lanche lanche = cardapio.get(i);
            System.out.printf("%d - %s - R$ %.2f%n", i + 1, lanche.getNome(), lanche.getPreco());
        }

        int opcao;
        while (true) {
            opcao = lerInteiro("Escolha o lanche: ");
            if (opcao >= 1 && opcao <= cardapio.size()) {
                break;
            }
            System.out.println("Lanche invalido. Tente novamente.");
        }

        return facade.criarLanche(opcao);
    }

    public void fecharRestaurante() {
        double total = facade.calcularTotalDoDia();
        exibirFechamentoDoDia(total);
    }

    public void exibirFechamentoDoDia(double total) {
        System.out.println();
        System.out.println("=== FECHAMENTO DO DIA ===");
        System.out.printf("Total vendido: R$ %.2f%n", total);
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                return true;
            }
            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                return false;
            }
            System.out.println("Responda com s ou n.");
        }
    }
}
