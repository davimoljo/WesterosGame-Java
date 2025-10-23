package westeros;

import java.util.List;
import java.util.Scanner;

public class Jogo {
    //aqui é onde o jogo vai rodar efetivamente
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();

        while(!tabuleiro.restaUmTime()){
            tabuleiro.imprimirTabuleiro();
            Personagem personagemAcao1 = escolherPersonagem(tabuleiro.getTime1());


        }
    }

    public static Personagem escolherPersonagem(Time time){
        List<Personagem> personagens = time.getPersonagens();
        System.out.println("Escolha um personagem para agir: ");
        Personagem p1 = personagens.get(0);
        Personagem p2 = personagens.get(1);
        Personagem p3 = personagens.get(2);

        System.out.println("Selecione: \n [ 1 ] " + p1.getNome() + "\n[ 2 ] " + p2.getNome() + "\n[ 3 ] " + p3.getNome());
        Scanner s = new Scanner(System.in);
        int opc = s.nextInt();
        switch (opc) {
            case 1:
                System.out.println(p1.getNome() + " selecionado!");
                return p1;
            case 2:
                System.out.println(p2.getNome() + " selecionado!");
                return p2;
            case 3:
                System.out.println(p3.getNome() + " selecionado!");
                return p3;
        
            default:
                return p1;
        }
        
    }

    public static escolherEAgir(Personagem p){
            //fazer funcao que escolhe a ação do personagem e a executa
    }
}