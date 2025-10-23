package westeros;
import java.util.*;

public class Personagem {
    protected String nome;
    protected int x;
    protected int y;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected int alcance;
    protected boolean jaSelecionado;

    public Personagem(){
        jaSelecionado = false;
    }

    @Override
    public boolean equals(Object o){
        if (o.getClass() != Personagem.class) {
            return false;
        }
        return this.nome.equals(((Personagem)o).getNome());
    }

    String getNome(){
        return nome;
    }

    int getX(){
        return x;
    }
    int getY(){
        return y;
    }

    public int getDefesa(){
        return defesa;
    }

    public void atacar(Personagem p){
        p.receberDano(ataque); //coloquei isso um pouco na pressa, pode mudar se tiver outra ideia
    }

    public void receberDano(int dano){
        vida -= (dano - defesa);
    }

    public boolean estaEmAlcance(Personagem p){
        //Pega a maior distância entre os eixos e retorna verdadeiro se a distância
        // é menor que o alcance do Personagem
        int dist = Math.abs(Math.max(p.getX() - this.x, p.getY() - this.y));

        return (dist <= p.alcance);
    }

    public boolean estaMorto(){
        return vida <= 0;
    }



    public void personagemSelecionado(){
        jaSelecionado = true;
    }

    public boolean foiSelecionado(){
        return jaSelecionado;
    }

    protected void imprimeStatus(){
        System.out.println(this.getNome() + "(" + this.getClass().getSimpleName() + ")");
        System.out.println("\tVida: " + this.vida + "\t|| Defesa: " + this.defesa);
        System.out.println("\tAtaque: " + this.ataque + "\t|| Alcance" + this.alcance);
    }
}