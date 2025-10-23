package westeros;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    Time time1;
    Time time2;

    public Jogo(){
        time1 = new Time(false);
        System.out.println("Você deseja jogar contra um computador? [S/N]");
        Scanner s = new Scanner(System.in);
        String ans = s.nextLine();
        if(ans.equals("S") || ans.equals("s"))
            time2 = new Time(true);
        else
            time2 = new Time(false);
        s.close();
    }

    protected static Personagem escolherPersonagem(Time time){ //Dei uma ajeitada pra deixar a selecao mais dinamica em relacao aos personagens que estao disponiveis para selecao
        List<Personagem> personagensJogaveis = new ArrayList<>();
        System.out.println("Escolha um personagem para agir: ");

        for (Personagem p : time.getPersonagens()){
            if(!p.foiSelecionado()){
                personagensJogaveis.add(p);
            }
        }

        int i = 0;
        for (Personagem p : personagensJogaveis) {
            i++;
            System.out.println("[ " + i + " ] " + p.getNome() + "(" + p.getClass().getSimpleName() + ")");
        }

        Scanner s = new Scanner(System.in);
        int opc = s.nextInt();
        while (opc < 1 || opc > i) {
            System.out.println("Seleção inválida!");
            opc = s.nextInt();
        }
        s.close();
        System.out.println(personagensJogaveis.get(opc - 1).getNome() + " selecionado");
        return personagensJogaveis.get(opc - 1);
    }

    public static void escolherEAgir(Personagem p){
            //fazer funcao que escolhe a ação do personagem e a executa
        System.out.println("Agindo: ");
        p.imprimeStatus();

    }

    protected boolean restaUmTime(){
        return time1.timeDerrotado() || time2.timeDerrotado();
    }

    protected Time getTime1(){
        return time1;
    }

    protected Time getTime2(){
        return time2;
    }
}