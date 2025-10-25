package westeros;

import java.util.Scanner;

public class Main {
    private static final Scanner s = new Scanner(System.in); 
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.imprimeMenu();
        int opcao = s.nextInt();
        while (opcao < 1 || opcao > 4){
            System.out.println("Opção inválida. Tente novamente.");
            menu.imprimeMenu();
            opcao = s.nextInt();
        }

        if(opcao == 1){
            Jogo jogo = new Jogo();
            jogo.rodarJogo();;
        } else if(opcao == 2){
            menu.imprimeInstrucoes();
        } else if(opcao == 3){
            menu.imprimeCreditos();
        } else {
            System.out.println("Saindo do jogo...");
        }
        
    }
}
