package westeros;

public class Stark extends Personagem{
    public Stark(String nome){
        this.nome = nome;
        vida = 60;
        ataque = 20;
        defesa = 10;
        alcance = 1;
    }

    @Override //isso serve pra avisar pro compilador que estamos reescrevendo o metodo
    public void receberDano(int dano){
        vida -= (int)((dano - defesa) * 0.8);
    }
}