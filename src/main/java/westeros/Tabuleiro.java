package westeros;
import java.util.*;

public class Tabuleiro {
    public static final int tabuleiroOrdem = 10;
    protected List<List<String>> matrizTabuleiro = new ArrayList<>();
    protected Time time1;
    protected Time time2;

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

    public boolean restaUmTime(){
        return time1.timeDerrotado() || time2.timeDerrotado();
    }

    public void movimentarPersonagem(Personagem p){
        List<Personagem> todosP = time1.getPersonagens();
        todosP.addAll(time2.getPersonagens());
        p.movimentar(todosP);
    }

    public void imprimirTabuleiro(){
        for(List<String> linha: matrizTabuleiro){
            for(String elemento: linha){
                System.out.println(elemento + "\t");
            }
        }
    }

    protected Time getTime1(){
        return time1;
    }

    protected Time getTime2(){
        return time2;
    }

    protected List<Personagem> getTodosPersonagens(){
        List<Personagem> todosP = time1.getPersonagens();
        todosP.addAll(time2.getPersonagens());
        return todosP;
    }
}