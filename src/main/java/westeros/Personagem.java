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

    public Personagem(){}

    @Override
    public boolean equals(Object o){
        if (o.getClass() != Personagem.class) {
            return false;
        }
        return this.nome.equals(((Personagem)o).getNome());
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

    public boolean estaEmAlcance(Personagem p){
        //Pega a maior distância entre os eixos e retorna verdadeiro se a distância
        // é menor que o alcance do Personagem
        int dist = Math.abs(Math.max(p.getX() - this.x, p.getY() - this.y));

        return (dist <= p.alcance);
    }

    public boolean estaMorto(){
        return vida <= 0;
    }

    protected boolean movimentoValido(String entrada, List<Personagem> todosPersonagens) {
        int novoX = this.x;
        int novoY = this.y;

        switch (entrada){
            case "W", "w" ->{
                novoY += 1;
            }
            case "S", "s" ->{
                novoY -= 1;
            }
            case "D", "d" ->{
                novoX += 1;
            }
            case "A", "a" ->{
                novoX -= 1;
            }
            default -> {
                return false;
            }
        }

        if (novoX < 0 || novoY <  0 || novoX >= Tabuleiro.tabuleiroOrdem || novoY >= Tabuleiro.tabuleiroOrdem) {
            return false;
        }

        for (Personagem p : todosPersonagens) {
            if (p.getX() == novoX && p.getY() == novoY) {
                return false;
            }
        }

        return true;
    }

    protected void movimentar(List<Personagem> todosPersonagens){
        Scanner s = new Scanner(System.in);
        System.out.println("Movimentando: " + this.getNome() + "\n Movimente-se com W A S D");
        String entrada = s.nextLine();

        while (!movimentoValido(entrada, todosPersonagens)){
            System.out.println("Movimento invalido");
            System.out.println("Movimentando: " + this.getNome() + "\n Movimente-se com W A S D");
            entrada = s.nextLine();
        }

        switch (entrada) {
            case "W", "w" -> {
                this.y += 1;
            }
            case "S", "s" -> {
                this.y -= 1;
            }
            case "A", "a" -> {
                this.x -= 1;
            }
            case "D", "d" -> {
                this.x += 1;
            }
        }


    }

}