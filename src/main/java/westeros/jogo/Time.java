package westeros.jogo;

import java.util.*;

import westeros.Main;
import westeros.personagens.Lannister;
import westeros.personagens.Personagem;
import westeros.personagens.Stark;
import westeros.personagens.Targaryen;

// Gerencia um time de personagens (seja Humano ou Bot) e seus estados.
public class Time {
    private List<Personagem> personagens = new ArrayList<>(); // Lista dos personagens do time.
    private int personagensVivos; // Contador de personagens vivos.
    private final boolean bot; // Flag para identificar se o time é controlado por IA.

    // Construtor: Define se o time é Bot e chama o método de seleção apropriado.
    public Time(boolean bot){
        this.bot = bot;
        if(!bot){
            selPersonagem(); // Chama a seleção manual para jogadores humanos.
        } else{
            aleatorizarSelecao(); // Chama a seleção automática para a IA (Bot).
        }
        personagensVivos = personagens.size(); // Define a contagem inicial de vivos.
    }

    // Guia o jogador humano na escolha (Casa) e nomeação dos 3 personagens.
    private void selPersonagem(){
        for(int i = 0; i < 3; i++){
            System.out.println("Insira a casa de seu personagem: ");
            System.out.println("[ 1 ] Stark \n[ 2 ] Lannister \n[ 3 ] Targaryen");

            int n = -1; // Loop de validação para a escolha da Casa (1, 2 ou 3).
            while (n < 1 || n > 3){
                System.out.println("Selecione uma casa válida (1, 2 ou 3):");
                try {
                    String entrada = Main.s.nextLine(); // Lê a linha inteira (seguro).
                    n = Integer.parseInt(entrada);
                    if (n < 1 || n > 3) {
                        System.out.println("Opção inválida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, digite apenas um número.");
                    n = -1; // Força o loop a repetir.
                }
            }

            System.out.println("Insira o nome do seu personagem: ");
            String nome = Main.s.nextLine(); // Lê o nome do personagem.
            Personagem p = null;

            // Instancia o personagem da Casa (Stark, Lannister, Targaryen) escolhida.
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

    // Cria 3 personagens aleatórios para o time do Bot com nomes pré-definidos.
    private void aleatorizarSelecao(){
        List<String> nomesBot = List.of("Xanric", "Ysolda", "Zephyr");

        for(int i = 0; i < 3; i++){
            int numCasa = i; // Escolhe uma Casa aleatória (0, 1, ou 2).
            String nomeEscolhido = nomesBot.get(i);

            // Instancia o personagem da Casa aleatória.
            switch(numCasa){
                case 0:
                    personagens.add(new Stark(nomeEscolhido, bot));
                    break;
                case 1:
                    personagens.add(new Lannister(nomeEscolhido, bot));
                    break;
                case 2:
                    personagens.add(new Targaryen(nomeEscolhido, bot));
                    break;
            }
        }
    }

    // Remove um personagem da lista (geralmente ao morrer) e atualiza a contagem de vivos.
    public void eliminaJogador(Personagem p){
        // O .remove(p) usa o método .equals() do Personagem para encontrar e remover.
        boolean removido = personagens.remove(p);

        // Se a remoção foi bem-sucedida, decrementa a contagem de vivos.
        if (removido) {
            personagensVivos--;
        }
    }

    // Reseta o status 'jaSelecionado' de todos os personagens vivos (usado no início de um novo turno).
    protected void resetSelecao() {
        for (Personagem p : personagens) {
            if (!p.estaMorto()) {
                p.setJaSelecionado(false); // Permite que o personagem jogue de novo.
            }
        }
    }

    // Verifica se o time foi derrotado (sem personagens vivos).
    public boolean timeDerrotado(){
        return personagensVivos <= 0;
    }

    // Getter para a lista completa de personagens do time.
    public List<Personagem> getPersonagens() {
        return personagens;
    }

    // Getter para verificar se este time é controlado por IA (Bot).
    public boolean isBot() {
        return bot;
    }

}