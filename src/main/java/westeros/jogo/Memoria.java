package westeros.jogo;

import java.util.*;

import westeros.personagens.Lannister;
import westeros.personagens.Personagem;
import westeros.personagens.Stark;
import westeros.personagens.Targaryen;

public class Memoria {
    private Tabuleiro quadros;
    private List<Personagem> time1;
    private List<Personagem> time2;
    private String log;
    Memoria(Tabuleiro t, Time t1, Time t2, String log) {
        time1 = new ArrayList<>();
        time2 = new ArrayList<>();
        Tabuleiro copia = new Tabuleiro();
        for(int i = 0; i < Tabuleiro.tabuleiroOrdem; i++){
            for(int j = 0; j < Tabuleiro.tabuleiroOrdem; j++){
                Casinha original = t.matrizTabuleiro.get(i).get(j);
                Casinha nova = copia.matrizTabuleiro.get(i).get(j);
                if(original.temPersonagem()){
                    Personagem pOriginal = original.getPersonagem();
                    Personagem pCopia = null;
                    if (pOriginal instanceof Stark)
                        pCopia = new Stark(pOriginal.getNome(), pOriginal.isBot());
                    else if (pOriginal instanceof Lannister)
                        pCopia = new Lannister(pOriginal.getNome(), pOriginal.isBot());
                    else if (pOriginal instanceof Targaryen)
                        pCopia = new Targaryen(pOriginal.getNome(), pOriginal.isBot());

                    if(pCopia != null){
                        pCopia.setPersonagemID(pOriginal.getPersonagemID());
                        pCopia.setX(pOriginal.getX());
                        pCopia.setY(pOriginal.getY());
                        nova.adicionaPersonagem(pCopia);
                    }
                }
            }
        }
        quadros = copia;
        for (Personagem p : t1.getPersonagens()) {
            Personagem tempP;
            if (p instanceof Stark)
                tempP = new Stark(p);
            else if (p instanceof Lannister)
                tempP = new Lannister(p);
            else
                tempP = new Targaryen(p);

            time1.add(tempP);
        }
        for (Personagem p : t2.getPersonagens()) {
            Personagem tempP;
            if (p instanceof Stark)
                tempP = new Stark(p);
            else if (p instanceof Lannister)
                tempP = new Lannister(p);
            else
                tempP = new Targaryen(p);

            time2.add(tempP);
        }
        this.log = log;
    }

    List<Personagem> getTime1(){return  time1;}
    List<Personagem> getTime2(){return  time2;}
    Tabuleiro getTabuleiro(){return  quadros;}
    String getLog(){return  log;}
}
