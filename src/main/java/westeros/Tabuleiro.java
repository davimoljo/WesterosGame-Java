import java.util.*;

public class Tabuleiro {
    List<List<String>> matrizTabuleiro = new ArrayList<>();
    Time time1;
    Time time2;

    public Tabuleiro(){
        for(int i = 0; i < 10; i++){
            matrizTabuleiro.add(new ArrayList<>());
        }
        time1 = new Time(false);
        System.out.println("Você deseja jogar contra um computador? [S/N]");
        Scanner s = new Scanner(System.in);
        String ans = s.nextLine();
        if(ans == "S")
            time2 = new Time(true);
        else
            time2 = new Time(false);
        s.close();
    }

    public int distanciaEntrePersonagens(Personagem a, Personagem b){
        int v1 = Math.abs(a.getX() - b.getX());
        int v2 = Math.abs(a.getY() - b.getY());

        return Math.max(v1,v2);

    }
}