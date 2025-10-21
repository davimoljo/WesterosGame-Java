public class Targaryen extends Personagem {
    public Targaryen(String nome){
        super(nome);
        vida = 45;
        ataque = 20;
        defesa = 10;
        alcance = 3;
    }

    @Override //isso serve pra avisar pro compilador que estamos reescrevendo o metodo
    public void atacar(Personagem p){
        p.receberDano(ataque + p.getDefesa()); // ele ignora a defesa base
    }
}