import java.util.*;

public class Time {
    List<Personagem> personagens = new ArrayList<>();
    private boolean controladoPorJogador;
    public Time(boolean ia){
        controladoPorJogador = ia;
        if(!controladoPorJogador){
            selPersonagem();
        } else{
            aleatorizarSelecao();
        }
    }
    private void selPersonagem(){
        Scanner s = new Scanner(System.in);

        for(int i = 0; i < 3; i++){
            System.out.println("Insira a casa de seu personagem: ");
            System.out.println("[ 1 ] Stark \n[ 2 ] Lannister \n[ 3 ] Targaryen");

            int n = s.nextInt();
            System.out.println("Insira o nome do seu personagem: ");
            String nome = s.nextLine();
            Personagem p = null;

            switch (n){
                case 1:
                    p = new Stark(nome);
                    break;
                case 2:
                    p = new Lannister(nome);
                    break;
                case 3:
                    p = new Targaryen(nome);
                    break;
            }
            personagens.add(p);

        }

        s.close();
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
                    personagens.add(new Stark(aleatorizarNome()));
                    break;
                case 1:
                    personagens.add(new Lannister(aleatorizarNome()));
                    break;
                case 2:
                    personagens.add(new Targaryen(aleatorizarNome()));
                    break;
            }
        }
    }
}