package westeros;

// Define a classe 'Targaryen', uma subclasse de 'Personagem' com atributos e habilidades únicas.
public class Targaryen extends Personagem{

    // Construtor: Define os atributos específicos (stats) para um personagem Targaryen.
    Targaryen(String nome, boolean bot){
        super(bot); // Chama o construtor da classe pai (Personagem).
        this.nome = nome;
        vida = 45;
        ataque = 20;
        defesa = 10;
        alcance = 3;
    }

    public Targaryen(Personagem p){
        super(p);
    }

    // Sobrescrita do 'atacar' com a habilidade especial Targaryen (dano ignora defesa).
    @Override
    public void atacar(Personagem p){
        // Chama o 'receberDano' do alvo com o cálculo do dano.
        // (NOTA: A defesa está sendo somada aqui e subtraída no 'receberDano', cancelando o bônus).
        p.receberDano(this.ataque + p.defesa);
    }
}