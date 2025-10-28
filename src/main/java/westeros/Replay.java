package westeros;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

public class Replay {
    List<Memoria> sets;
    public Replay(){
        sets =  new ArrayList<>();
    }

    public void registrarQuadro(Tabuleiro t, Time time1, Time  time2, String log){
        Memoria set = new Memoria(t, time1, time2, log);
        sets.add(set);
    }

    public void reproduzir(){
        int turno = 1;
        int rodada = 1;
        for(Memoria set : sets){
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Turno: " + turno + " || Rodada: " +  rodada);
            set.getTabuleiro().imprimirTabuleiro();
            System.out.println(set.getLog());
            System.out.println("Status time 1:");
            for (Personagem p : set.getTime1()){
                System.out.println(p.getNome() + " (" + p.getClass().getSimpleName() + "): Vida: " + p.getVida() + " || Defesa: " + p.getDefesa());
            }
            System.out.println("Status time 2:");
            for  (Personagem p : set.getTime2()){
                System.out.println(p.getNome() + " (" + p.getClass().getSimpleName() + "): Vida: " + p.getVida() + " || Defesa: " + p.getDefesa());
            }
            System.out.println("---------------------------------------------------------------------------");
            if (rodada == 2)
                turno++;

            rodada++;

            if (rodada > 2)
                rodada = 1;

            try {
                Thread.sleep(1000); 
            } 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
