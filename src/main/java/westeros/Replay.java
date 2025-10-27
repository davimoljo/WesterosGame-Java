package westeros;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

public class Replay {
    List<Tabuleiro> quadros;
    public Replay(){
        quadros = new ArrayList<>();
    }

    public void registrarQuadro(Tabuleiro t){
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
        quadros.add(copia);

    }

    public void reproduzir(){
        for(Tabuleiro quadro:quadros){
            quadro.imprimirTabuleiro();
            try {
                Thread.sleep(1000); 
            } 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
