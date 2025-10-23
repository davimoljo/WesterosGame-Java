package westeros;

public class Casinha {
    private final String quadrado;
    private Personagem personagem;

    Casinha(){
        quadrado = "|   |";
        personagem = null;
    }

    public void adicionaPersonagem(Personagem p){
        personagem = p;
    }


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

    public void retiraPersonagem(){
        personagem = null;
    }

    public void imprimeCasinha(){
        if (temPersonagem()) {
            char inicial = personagem.getNome().charAt(0);
            System.out.print("| " + inicial + " |");
        } else {
            System.out.print("|   |");
        }
    }
}
