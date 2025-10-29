package westeros;

import java.util.Scanner;

public class Main {

    public static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        boolean executando = true;



        while (executando) {
            Jogo.limparTela();
            Menu menu = new Menu();
            menu.imprimeMenu();

            int opcao = -1;
            try {
                String entrada = s.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            while (opcao < 1 || opcao > 4) {
                System.out.println("Opção inválida. Tente novamente.");
                menu.imprimeMenu();
                try {
                    String entrada = s.nextLine();
                    opcao = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, digite apenas o número.");
                    opcao = -1;
                }
            }

            switch (opcao) {
                case 1 -> {
                    Jogo jogo = new Jogo();
                    jogo.rodarJogo();

                    boolean emReplayMenu = true;

                    while (emReplayMenu) {
                        System.out.println("\nEscolha uma opção:");
                        System.out.println("[ 1 ] Ver replay da partida");
                        System.out.println("[ 2 ] Jogar novamente");
                        System.out.println("[ 3 ] Voltar ao menu principal");
                        System.out.println("[ 4 ] Sair");

                        String entrada = s.nextLine();
                        int resposta = -1;
                        try {
                            resposta = Integer.parseInt(entrada);
                        } catch (NumberFormatException e) {
                            System.out.println("Digite um número válido.");
                            continue;
                        }

                        if (resposta == 1) {
                            jogo.getReplay().reproduzir();
                        } else if (resposta == 2) {
                            jogo = new Jogo(); // novos times
                            jogo.rodarJogo();
                        } else if (resposta == 3) {
                            emReplayMenu = false; // volta ao menu principal (sem recursão)
                        } else if (resposta == 4) {
                            executando = false;
                            emReplayMenu = false;
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    }
                }

                case 2 -> {
                    menu.imprimeInstrucoes();
                    System.out.println("\nPressione ENTER para voltar ao menu.");
                    s.nextLine();
                }

                case 3 -> {
                    menu.imprimeCreditos();
                    System.out.println("\nPressione ENTER para voltar ao menu.");
                    s.nextLine();
                }

                case 4 -> {
                    System.out.println("Saindo do jogo...");
                    executando = false;
                }
            }
        }

        s.close();
    }
}
