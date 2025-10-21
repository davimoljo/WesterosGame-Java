import java.util.*;

public class Personagem {
    private String nome;
    private int x;
    private int y;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected int alcance;

    public Personagem(String n){
        nome = n;

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

    public void receberDano(int d){
        vida -= (d - defesa);
    }


}