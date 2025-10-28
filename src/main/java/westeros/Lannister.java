package westeros;

// Define a classe 'Lannister', uma subclasse de 'Personagem' com atributos e habilidades únicas.
public class Lannister extends Personagem {

    // Construtor: Define os atributos específicos (stats) para um personagem Lannister.
    public Lannister(String nome, boolean bot){
        super(bot); // Chama o construtor da classe pai (Personagem).
        this.nome = nome;
        vida = 50;
        ataque = 20;
        defesa = 10;
        alcance = 2;
    }

    public Lannister(Personagem p){
        super(p);
    }

    // Sobrescrita do 'atacar' com a habilidade especial Lannister (15% mais dano).
    @Override
    public void atacar(Personagem p){
        // Chama o 'receberDano' do alvo com o cálculo do dano.
        // (NOTA: A defesa do alvo está sendo subtraída duas vezes: aqui e no 'receberDano')
        p.receberDano((int)(ataque*1.15 - 0.15*p.defesa));
    }
}