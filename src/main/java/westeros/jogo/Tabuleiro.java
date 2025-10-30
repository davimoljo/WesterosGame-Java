package westeros.jogo;
import java.util.*;
import westeros.Main;

import westeros.personagens.Personagem;

// Gerencia a grade (matriz) do jogo, controlando posições e movimentos.
public class Tabuleiro {
    public static final int tabuleiroOrdem = 10; // Define o tamanho do tabuleiro (10x10).
    protected List<List<Casinha>> matrizTabuleiro = new ArrayList<>(); // Matriz de Casinhas (Lista de Listas).

    // Construtor: Cria a matriz 10x10 e inicializa cada 'Casinha' nela.
    public Tabuleiro(){
        for(int i = 0; i < tabuleiroOrdem; i++){
            matrizTabuleiro.add(new ArrayList<>());
            for(int j = 0; j < tabuleiroOrdem; j++){
                matrizTabuleiro.get(i).add(new Casinha());
            }
        }
    }

    public void atualizarPosicaoPersonagem(Personagem p, int xAntigo, int yAntigo) {
        // Remove da casa antiga
        matrizTabuleiro.get(yAntigo).get(xAntigo).retiraPersonagem();
        // Adiciona na casa nova
        matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
    }

    // Controla o movimento de um personagem (seja Humano via W-A-S-D ou Bot aleatório).
    protected void movimentarPersonagem(Personagem p) {
        if (!p.isBot()) {
            // Lógica de movimento para jogador Humano.
            System.out.println("Movimentando: " + p.getNome() + "\n Movimente-se com W A S D");
            String entrada = Main.s.nextLine();

            while (!movimentoValido(entrada, p)) {
                System.out.println("Movimento invalido");
                System.out.println("Movimentando: " + p.getNome() + "\n Movimente-se com W A S D");
                entrada = Main.s.nextLine();
            }

            // Atualiza a matriz: retira da casa antiga, atualiza P, adiciona na casa nova.
            switch (entrada) {
                case "W", "w" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setY(p.getY() - 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
                case "S", "s" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setY(p.getY() + 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
                case "A", "a" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setX(p.getX() - 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
                case "D", "d" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setX(p.getX() + 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
            }

        } else {
            // Lógica de movimento para o Bot (aleatório).
            Random random = new Random();
            String[] movimentos = {"W", "A", "S", "D"};
            String movimentoEscolhido;
            int tentativas = 0;
            final int MAX_TENTATIVAS = 20; // Limite para evitar loop infinito.

            // Tenta um movimento aleatório até achar um válido.
            do {
                movimentoEscolhido = movimentos[random.nextInt(movimentos.length)];
                tentativas++;
                if (tentativas > MAX_TENTATIVAS) {
                    System.out.println("Bot " + p.getNome() + " nao conseguiu se mover.");
                    return; // Desiste de mover.
                }
            } while (!movimentoValido(movimentoEscolhido, p));

            // Atualiza a matriz com o movimento aleatório escolhido.
            switch (movimentoEscolhido) {
                case "W" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setY(p.getY() - 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
                case "S" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setY(p.getY() + 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
                case "A" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setX(p.getX() - 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
                case "D" -> {
                    matrizTabuleiro.get(p.getY()).get(p.getX()).retiraPersonagem();
                    p.setX(p.getX() + 1);
                    matrizTabuleiro.get(p.getY()).get(p.getX()).adicionaPersonagem(p);
                }
            }
            System.out.println("Bot " + p.getNome() + " moveu para (" + p.getX() + ", " + p.getY() + ")");
        }
    }

    // Posiciona os times no início do jogo, cada um em um lado do tabuleiro.
    public void posicionarTimes(Time time1, Time time2) {
        Random random = new Random();
        int x, y;

        // Posiciona o Time 1 na metade esquerda (colunas 0-4).
        for (Personagem p : time1.getPersonagens()) {
            do {
                x = random.nextInt(tabuleiroOrdem / 2);
                y = random.nextInt(tabuleiroOrdem);
            } while (matrizTabuleiro.get(y).get(x).temPersonagem());

            matrizTabuleiro.get(y).get(x).adicionaPersonagem(p);
            p.setX(x);
            p.setY(y);
        }

        // Posiciona o Time 2 na metade direita (colunas 5-9).
        for (Personagem p : time2.getPersonagens()) {
            do {
                x = random.nextInt(tabuleiroOrdem / 2) + (tabuleiroOrdem / 2);
                y = random.nextInt(tabuleiroOrdem);
            } while (matrizTabuleiro.get(y).get(x).temPersonagem());

            matrizTabuleiro.get(y).get(x).adicionaPersonagem(p);
            p.setX(x);
            p.setY(y);
        }
    }

    // Remove um personagem morto do tabuleiro (limpa a 'Casinha' onde ele estava).
    public void removerPersonagemDoTabuleiro(Personagem p) {
        if (p != null) {
            int x = p.getX();
            int y = p.getY();

            // Checagem de segurança para evitar 'IndexOutOfBoundsException'.
            if (x >= 0 && x < tabuleiroOrdem && y >= 0 && y < tabuleiroOrdem) {
                matrizTabuleiro.get(y).get(x).retiraPersonagem();
                System.out.println(p.getNome() + " foi removido visualmente do tabuleiro.");
            } else {
                System.err.println("Alerta: Tentativa de remover " + p.getNome() + " de coordenadas invalidas (" + x + "," + y + ")");
            }
        }
    }

    // Imprime o estado atual do tabuleiro no console.
    public void imprimirTabuleiro(){
        for(List<Casinha> linha : matrizTabuleiro){
            for(Casinha elemento : linha){
                elemento.imprimeCasinha();
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
    }

    // Verifica se um movimento (W,A,S,D) é válido (dentro do mapa e para uma casa vazia).
    protected boolean movimentoValido(String entrada, Personagem p) {
        int novoX = p.getX();
        int novoY = p.getY();

        // Calcula a coordenada de destino com base na entrada.
        switch (entrada){
            case "W", "w" -> novoY -= 1;
            case "S", "s" -> novoY += 1;
            case "D", "d" -> novoX += 1;
            case "A", "a" -> novoX -= 1;
            default -> {
                return false; // Retorna falso se a entrada não for W, A, S ou D.
            }
        }

        // Checa se o movimento está fora dos limites do tabuleiro.
        if (novoX < 0 || novoY <  0 || novoX >= Tabuleiro.tabuleiroOrdem || novoY >= Tabuleiro.tabuleiroOrdem) {
            return false;
        }

        // Checa se a casa de destino já está ocupada.
        return !matrizTabuleiro.get(novoY).get(novoX).temPersonagem();
    }
}