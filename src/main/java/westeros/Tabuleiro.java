package westeros;
import java.util.*;

public class Tabuleiro {
    public static final int tabuleiroOrdem = 10;
    protected List<List<String>> matrizTabuleiro = new ArrayList<>();

    public Tabuleiro(){
        for(int i = 0; i < 10; i++){
            matrizTabuleiro.add(new ArrayList<>());
        }

    }

    public int distanciaEntrePersonagens(Personagem a, Personagem b){
        int v1 = Math.abs(a.getX() - b.getX());
        int v2 = Math.abs(a.getY() - b.getY());

        return Math.max(v1,v2);

    }


    public void imprimirTabuleiro(){
        for(List<String> linha: matrizTabuleiro){
            for(String elemento: linha){
                System.out.println(elemento + "\t");
            }
        }
    }

    private boolean movimentoValido(String entrada, Personagem p) {
        int novoX = p.getX();
        int novoY = p.getY();

        switch (entrada){
            case "W", "w" -> novoY += 1;

            case "S", "s" -> novoY -= 1;

            case "D", "d" -> novoX += 1;

            case "A", "a" -> novoX -= 1;

            default -> {
                return false;
            }
        }

        if (novoX < 0 || novoY <  0 || novoX >= Tabuleiro.tabuleiroOrdem || novoY >= Tabuleiro.tabuleiroOrdem) {
            return false;
        }



        return true;
    }

    protected void movimentarPersonagem(Personagem p){
        Scanner s = new Scanner(System.in);
        System.out.println("Movimentando: " + p.getNome() + "\n Movimente-se com W A S D");
        String entrada = s.nextLine();

        while (!movimentoValido(entrada, p)){
            System.out.println("Movimento invalido");
            System.out.println("Movimentando: " + p.getNome() + "\n Movimente-se com W A S D");
            entrada = s.nextLine();
        }

        switch (entrada) {
            case "W", "w" -> {
                p.y += 1;
            }
            case "S", "s" -> {
                p.y -= 1;
            }
            case "A", "a" -> {
                p.x -= 1;
            }
            case "D", "d" -> {
                p.x += 1;
            }
        }

    }
}