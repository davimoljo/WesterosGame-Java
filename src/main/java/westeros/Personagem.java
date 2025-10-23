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
    protected boolean bot;

    public Personagem(boolean bot){
        this.bot = bot;

    }

    @Override
    public boolean equals(Object o){
        if (o.getClass() != Personagem.class) {
            return false;
        }
        return this.nome.equals(((Personagem)o).getNome());
    }

    protected int getAlcance() {
        return alcance;
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

    public boolean estaMorto(){
        return vida <= 0;
    }

    protected void imprimeStatus(){
        System.out.println(this.getNome() + "(" + this.getClass().getSimpleName() + "): " + "Vida: " + this.vida + " || Defesa: " + this.defesa + " || Ataque: " + this.ataque + " || Alcance" + this.alcance);

    }

    private boolean estaEmAlcance(Personagem alvo){
        //Pega a maior distância entre os eixos e retorna verdadeiro se a distância
        // é menor que o alcance do Personagem
        int dist = Math.max(Math.abs(alvo.getX() - this.getX()), Math.abs(alvo.getY() - this.getY()));

        return (dist <= this.getAlcance());
    }

    private List<Personagem> getAlvos(Time adversarios){
        List<Personagem> alvos = new ArrayList<>();
        for (Personagem alvo : adversarios.getPersonagens()) {
            if (estaEmAlcance(alvo))
                alvos.add(alvo);
        }

        return alvos;
    }

    protected boolean listaAlvosEAtaca(Time adversarios){
        List<Personagem> alvos = getAlvos(adversarios);
        Scanner sc = new Scanner(System.in);
        int i = 0;

        if (alvos.isEmpty()){
            System.out.println("Sem alvos disponíveis");

            sc.close();
            return false;
        }

        System.out.println("Selecione o alvo");
        for (Personagem alvo : alvos) {
            i++;
            System.out.println("[" + i + "] - " + alvo.getNome() + "\n");
        }
        int entrada = sc.nextInt();
        while (entrada < 1 || entrada > alvos.size()){
            System.out.println("Entrada invalida: selecione um alvo válido");
            entrada = sc.nextInt();
        }

        Personagem alvo = alvos.get(entrada);
        atacar(alvo);
        if (alvo.estaMorto()){
            adversarios.eliminaJogador(alvo);
        }

        sc.close();
        return true;
    }

    public boolean isBot() {
        return bot;
    }
}