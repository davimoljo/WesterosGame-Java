package westeros;

// Define a classe 'Stark', uma subclasse de 'Personagem' com atributos e habilidades únicas.
public class Stark extends Personagem{

    // Construtor: Define os atributos específicos (stats) para um personagem Stark.
    public Stark(String nome, boolean bot){
        super(bot); // Chama o construtor da classe pai (Personagem).
        this.nome = nome;
        vida = 60;
        ataque = 20;
        defesa = 10;
        alcance = 1;
    }

    // Sobrescrita do 'receberDano' com a habilidade especial Stark (toma 20% menos dano).
    @Override
    public void receberDano(int dano){
        // Aplica o dano (BUG: 'dano - defesa' pode curar; BUG 2: imprime 'dano' bruto)
        vida -= (int)((dano - defesa) * 0.8);

        // Imprime o feedback de dano (NOTA: está imprimindo o dano bruto, não o dano real sofrido)
        System.out.println("\n>>> " + this.getNome() + " sofreu " + dano + " de dano! (Habilidade Stark ativada!)");
        this.imprimeStatus(); // Mostra o status atualizado.
    }
}