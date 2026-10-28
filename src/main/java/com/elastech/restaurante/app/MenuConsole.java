package com.elastech.restaurante.app;

import com.elastech.restaurante.cardapio.ItemCardapio;
import com.elastech.restaurante.cardapio.Lanche;
import com.elastech.restaurante.pedido.Pedido;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
        System.out.println("3 - Modificar pedido");
        System.out.println("4 - Fechar restaurante");
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
            ItemCardapio item = montarItem();
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
        List<ItemCardapio> itens = pedido.getItens();
        if (itens.isEmpty()) {
            System.out.println("  (sem itens)");
        }
        for (int i = 0; i < itens.size(); i++) {
            ItemCardapio item = itens.get(i);
            System.out.printf("  %d. %s - R$ %.2f%n", i + 1, item.getDescricao(), item.getPreco());
        }
        System.out.printf("  Total: R$ %.2f%n", pedido.calcularTotal());
    }

    public void modificarPedido() {
        int numero = lerInteiro("Numero do pedido: ");
        Pedido pedido = facade.buscarPedido(numero);
        if (pedido == null) {
            System.out.println("Pedido nao encontrado.");
            return;
        }

        int opcao;
        do {
            exibirPedido(pedido);
            System.out.println("1 - Acrescentar item");
            System.out.println("2 - Remover item");
            System.out.println("3 - Cancelar pedido");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1 -> {
                    ItemCardapio item = montarItem();
                    facade.adicionarItem(pedido, item);
                    System.out.println("Item adicionado.");
                }
                case 2 -> removerItem(pedido);
                case 3 -> {
                    if (facade.cancelarPedido(numero)) {
                        System.out.println("Pedido cancelado.");
                    } else {
                        System.out.println("Pedido nao encontrado.");
                    }
                    opcao = 0;
                }
                case 0 -> {
                }
                default -> opcaoInvalida();
            }
        } while (opcao != 0);
    }

    private void removerItem(Pedido pedido) {
        List<ItemCardapio> itens = pedido.getItens();
        if (itens.isEmpty()) {
            System.out.println("O pedido nao possui itens.");
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, itens.get(i).getDescricao());
        }
        int indice = lerInteiro("Numero do item: ");
        if (indice < 1 || indice > itens.size()) {
            System.out.println("Item invalido.");
            return;
        }
        facade.removerItem(pedido, indice - 1);
        System.out.println("Item removido.");
    }

    private ItemCardapio montarItem() {
        List<Lanche> cardapio = facade.listarCardapio();
        System.out.println();
        System.out.println("=== CARDAPIO ===");
        for (int i = 0; i < cardapio.size(); i++) {
            Lanche lanche = cardapio.get(i);
            System.out.printf("%d - %s - R$ %.2f%n", i + 1, lanche.getDescricao(), lanche.getPreco());
        }

        int opcao;
        while (true) {
            opcao = lerInteiro("Escolha o lanche: ");
            if (opcao >= 1 && opcao <= cardapio.size()) {
                break;
            }
            System.out.println("Lanche invalido. Tente novamente.");
        }

        Lanche lanche = facade.criarLanche(opcao);
        if (!lerSimNao("Manter queijo? (s/n): ")) {
            lanche.setQueijo(false);
        }
        if (!lerSimNao("Manter alface? (s/n): ")) {
            lanche.setAlface(false);
        }
        if (!lerSimNao("Manter tomate? (s/n): ")) {
            lanche.setTomate(false);
        }
        if (!lerSimNao("Manter cebola? (s/n): ")) {
            lanche.setCebola(false);
        }

        ItemCardapio item = lanche;
        if (lerSimNao("Adicionar cheddar (+ R$ 4,00)? (s/n): ")) {
            item = facade.adicionarCheddar(item);
        }
        if (lerSimNao("Adicionar bacon extra (+ R$ 6,00)? (s/n): ")) {
            item = facade.adicionarBaconExtra(item);
        }
        return item;
    }

    public void fecharRestaurante() {
        double total = facade.calcularTotalDoDia();
        Set<String> combinacoes = facade.getCombinacoesDiferentes();
        exibirFechamentoDoDia(total, combinacoes);
    }

    public void exibirFechamentoDoDia(double total, Set<String> combinacoes) {
        System.out.println();
        System.out.println("=== FECHAMENTO DO DIA ===");
        System.out.printf("Total vendido: R$ %.2f%n", total);
        System.out.printf("Combinacoes diferentes: %d%n", combinacoes.size());
        for (String combinacao : combinacoes) {
            System.out.println("  " + combinacao);
        }
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
