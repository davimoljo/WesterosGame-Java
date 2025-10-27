package westeros;

import java.util.Scanner;

public class Main {

    // Scanner global usado por todo o aplicativo para evitar conflitos de entrada.
    public static final Scanner s = new Scanner(System.in);
    public static void main(String[] args) {


        Menu menu = new Menu();
        menu.imprimeMenu();

        int opcao = -1;

        // Bloco para ler a opção do menu com segurança (evitando o bug do nextInt/nextLine).
        try {
            String entrada = s.nextLine();
            opcao = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            // Se a entrada não for um número, 'opcao' continua -1 e entra no loop.
        }

        // Loop de validação: Garante que a opção esteja dentro do intervalo válido (1-4).
        while (opcao < 1 || opcao > 4) {
            System.out.println("Opção inválida. Tente novamente.");
            menu.imprimeMenu();

            try {
                String entrada = s.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, digite apenas o número.");
                opcao = -1; // Força o loop a repetir.
            }
        }

        // Estrutura condicional para executar a ação escolhida no menu.
        if (opcao == 1) {
            Jogo jogo = new Jogo();
            jogo.rodarJogo();; // Inicia o loop principal do jogo.
        } else if (opcao == 2) {
            menu.imprimeInstrucoes();
        } else if (opcao == 3) {
            menu.imprimeCreditos();
        } else {
            System.out.println("Saindo do jogo...");
        }

        s.close(); // Fecha o scanner
    }
}