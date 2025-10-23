package westeros;

public class Main {

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        Jogo partida = new Jogo();
        System.out.println("\n--- A BATALHA DE WESTEROS COMEÇOU ---");
        while (!partida.restaUmTime()) {
            tabuleiro.imprimirTabuleiro();
            System.out.println("\n--- TURNO DO TIME 1 ---");
            Time time1 = partida.getTime1();
            Personagem p1 = Jogo.escolherPersonagem(time1);

            if (p1 != null) {
                partida.escolherEAgir(p1);
            }
            if (partida.restaUmTime()) break;
            tabuleiro.imprimirTabuleiro();
            System.out.println("\n--- TURNO DO TIME 2 ---");
            Time time2 = partida.getTime2();
            Personagem p2 = Jogo.escolherPersonagem(time2);

            if (p2 != null) {
                partida.escolherEAgir(p2);
            }
        }
        System.out.println("\nFIM DE JOGO!");
    }
}