package westeros;

public class Lannister extends Personagem {
    public Lannister(String nome, boolean bot){
        super(bot);
        this.nome = nome;
        vida = 50;
        ataque = 20;
        defesa = 10;
        alcance = 2;
    }

    @Override //isso serve pra avisar pro compilador que estamos reescrevendo o metodo
    public void atacar(Personagem p){
        p.receberDano((int)(ataque*1.15 - 0.15*p.defesa));
    }
}