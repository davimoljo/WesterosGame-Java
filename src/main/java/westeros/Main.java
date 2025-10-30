package westeros;

import java.util.Scanner;

import westeros.jogo.Jogo;
import westeros.jogo.Menu;
import westeros.personagens.Personagem;

public class Main {

    public static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        boolean executando = true;

        //função main, onde o jogo roda efetivamente

        while (executando) {
            Jogo.limparTela();
            Menu menu = new Menu();
            menu.imprimeMenu();

            // Lê a opção do usuário com validação.
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
                // Opções do menu principal
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
                            Jogo.limparTela();
                            jogo.getReplay().reproduzir();
                            Jogo.limparTela();
                        } else if (resposta == 2) {
                            Personagem.reiniciarContador(); //reiniciar contador de personagens para lógica de impressão
                            jogo = new Jogo();
                            jogo.rodarJogo();
                        } else if (resposta == 3) {
                            emReplayMenu = false;
                        } else if (resposta == 4) {
                            executando = false;
                            emReplayMenu = false;
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    }
                }
                // Instruções
                case 2 -> {
                    menu.imprimeInstrucoes();
                    System.out.println("\nPressione ENTER para voltar ao menu.");
                    s.nextLine();
                }
                // Creditos
                case 3 -> {
                    menu.imprimeCreditos();
                    System.out.println("\nPressione ENTER para voltar ao menu.");
                    s.nextLine();
                }
                // Sair do jogo
                case 4 -> {
                    System.out.println("Saindo do jogo...");
                    executando = false;
                }
            }
        }

        s.close();
    }
}
