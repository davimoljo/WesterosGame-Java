public class Stark extends Personagem{
    public Stark(String nome){
        super(nome);
        vida = 60;
        ataque = 20;
        defesa = 10;

    }

    @Override //isso serve pra avisar pro compilador que estamos reescrevendo o metodo
    public void receberDano(int d){
        vida -= (int)((d - defesa) * 0.8);
    }
}