package westeros;
import java.util.*;

public class Tabuleiro {

    public static final int tabuleiroOrdem = 10;
    protected List<List<Casinha>> matrizTabuleiro = new ArrayList<>();

    public Tabuleiro(){
        for(int i = 0; i < tabuleiroOrdem; i++){
            matrizTabuleiro.add(new ArrayList<>());
            for(int j = 0; j < tabuleiroOrdem; j++){
                matrizTabuleiro.get(i).add(new Casinha());
            }
        }
    }

    public void imprimirTabuleiro(){
        for(List<Casinha> linha : matrizTabuleiro){
            for(Casinha elemento : linha){
                elemento.imprimeCasinha();
            }
        }
    }

    protected boolean movimentoValido(String entrada, Personagem p) {
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

        return !matrizTabuleiro.get(novoX).get(novoY).temPersonagem();
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
        s.close();

        switch (entrada) {
            case "W", "w" -> {
                matrizTabuleiro.get(p.getX()).get(p.getY()).retiraPersonagem();
                p.y += 1;
                matrizTabuleiro.get(p.getX()).get(p.getY()).adicionaPersonagem(p);
            }
            case "S", "s" -> {
                matrizTabuleiro.get(p.getX()).get(p.getY()).retiraPersonagem();
                p.y -= 1;
                matrizTabuleiro.get(p.getX()).get(p.getY()).adicionaPersonagem(p);
            }
            case "A", "a" -> {
                matrizTabuleiro.get(p.getX()).get(p.getY()).retiraPersonagem();
                p.x -= 1;
                matrizTabuleiro.get(p.getX()).get(p.getY()).adicionaPersonagem(p);
            }
            case "D", "d" -> {
                matrizTabuleiro.get(p.getX()).get(p.getY()).retiraPersonagem();
                p.x += 1;
                matrizTabuleiro.get(p.getX()).get(p.getY()).adicionaPersonagem(p);
            }
        }

    }
}