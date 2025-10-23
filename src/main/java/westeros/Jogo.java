package westeros;

import java.util.ArrayList;
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
    }

    private void movimentarPersonagem(Personagem p){
        List<Personagem> todosP= time1.getPersonagens();
        todosP.addAll(time2.getPersonagens());
        p.movimentar(todosP);
    }

    public static Personagem escolherPersonagem(Time time){ //Dei uma ajeitada pra deixar a selecao mais dinamica em relacao aos personagens que estao disponiveis para selecao
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

        System.out.println(personagensJogaveis.get(opc - 1).getNome() + " selecionado");
        return personagensJogaveis.get(opc - 1);
    }

    public static void escolherEAgir(Personagem p){
            //fazer funcao que escolhe a ação do personagem e a executa
        System.out.println("Agindo: ");
        p.imprimeStatus();
    }
}