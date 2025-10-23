package westeros;

import java.util.*;

public class Time {
    protected List<Personagem> personagens = new ArrayList<>();
    protected int personagensVivos;
    private boolean bot;

    public Time(boolean bot){
        if(!bot){
            this.bot = false;
            selPersonagem();
        } else{
            this.bot = true;
            aleatorizarSelecao();
        }
        personagensVivos = personagens.size();
    }

    private void selPersonagem(){
        Scanner s = new Scanner(System.in);

        for(int i = 0; i < 3; i++){
            System.out.println("Insira a casa de seu personagem: ");
            System.out.println("[ 1 ] Stark \n[ 2 ] Lannister \n[ 3 ] Targaryen");

            int n = s.nextInt();
            while (n < 1 || n > 3){
                System.out.println("Selecione uma casa válida!");
                n = s.nextInt();
            }
            s.nextLine();
            System.out.println("Insira o nome do seu personagem: ");
            String nome = s.nextLine();
            Personagem p = null;

            switch (n){
                case 1:
                    p = new Stark(nome, bot);
                    break;
                case 2:
                    p = new Lannister(nome, bot);
                    break;
                case 3:
                    p = new Targaryen(nome, bot);
                    break;
            }
            personagens.add(p);

        }


    }

    private String aleatorizarNome(){
        List<String> nomesPadrao = new ArrayList<>(List.of(
                "Aldren", "Brynna", "Caelum", "Draven", "Elyra",
                "Faelan", "Garruk", "Halric", "Isolde", "Jareth",
                "Kaelin", "Lyra", "Morthos", "Nerwen", "Orin",
                "Peregrin", "Quintus", "Rhiannon", "Sylas", "Tamsin",
                "Ulric", "Vaelis", "Wynne", "Xanric", "Ysolda", "Zephyr"
        ));
        Collections.shuffle(nomesPadrao);
        return nomesPadrao.get(0);
    }

    private void aleatorizarSelecao(){
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            int num = random.nextInt(3);
            switch(num){
                case 0:
                    personagens.add(new Stark(aleatorizarNome(), bot));
                    break;
                case 1:
                    personagens.add(new Lannister(aleatorizarNome(), bot));
                    break;
                case 2:
                    personagens.add(new Targaryen(aleatorizarNome(), bot));
                    break;
            }
        }
    }

    public boolean timeDerrotado(){
        return personagensVivos <= 0;
    }

    protected void eliminaJogador(Personagem p){

        for(Personagem p2 : personagens){
            if(p2.equals(p)){
                personagens.remove(p2);
                personagensVivos--;
                return;
            }
        }
    }

    protected List<Personagem> getPersonagens() {
        return personagens;
    }

    public boolean isBot() {
        return bot;
    }

}