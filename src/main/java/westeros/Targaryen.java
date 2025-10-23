package westeros;

public class Targaryen extends Personagem{
    Targaryen(String nome, boolean bot){
        super(bot);
        this.nome = nome;
        vida = 45;
        ataque = 20;
        defesa = 10;
        alcance = 3;
    }

    @Override
    public void atacar(Personagem p){
        p.receberDano(this.ataque + p.defesa);
    }
}
