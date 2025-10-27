package westeros;

// Representa um único "quadrado" (tile) no tabuleiro do jogo.
public class Casinha {
    private final String quadrado;
    private Personagem personagem;

    // Construtor: Inicializa a casinha como vazia.
    Casinha(){
        quadrado = "|   |";
        personagem = null;
    }

    // Define qual personagem 'p' está ocupando esta casinha.
    public void adicionaPersonagem(Personagem p){
        personagem = p;
    }


    // Verifica se a casinha está ocupada por um personagem vivo (e limpa mortos).
    public boolean temPersonagem(){
        if (personagem != null) {
            if(personagem.estaMorto()){
                retiraPersonagem();
                return false;
            }
            return true;
        }
        return false;
    }

    public Personagem getPersonagem() {
        if (temPersonagem()) {
            return personagem;
        }
        return null; // Retorna null se não houver personagem
    }

    // Remove o personagem atual desta casinha (seta para null).
    public void retiraPersonagem(){
        personagem = null;
    }

    // Imprime a casinha no console (vazia ou com a inicial do personagem).
    public void imprimeCasinha(){
        int id = -1;
        if(getPersonagem() != null)
            id = personagem.getPersonagemID();
        
        if (temPersonagem() && id < 3 && id >=0) {
            char inicial = personagem.getNome().charAt(0);
            System.out.print("\u001B[34m| " + inicial + " |\u001B[0m");
        }else if(temPersonagem() && id < 6 && id >=3){
            char inicial = personagem.getNome().charAt(0);
            System.out.print("\u001B[31m| " + inicial + " |\u001B[0m");

        } else {
            System.out.print(quadrado);
        }
    }
}