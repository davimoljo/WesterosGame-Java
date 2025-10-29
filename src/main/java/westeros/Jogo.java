package westeros;

import java.util.*;
import java.lang.Thread;

// Classe principal que gerencia o estado e o fluxo do jogo.
public class Jogo {
    Time time1;
    Time time2;
    Tabuleiro tabuleiro;
    Replay replay = new Replay();

    // Construtor: Inicializa o tabuleiro e os dois times (jogador vs bot ou jogador).
    public Jogo() {
        limparTela();
        tabuleiro = new Tabuleiro();
        System.out.println("Casas: ");
        System.out.println("Stark:\n\tVida: 60 || Ataque: 20 || Defesa: 10 || Alcance: 1\n\tHabilidade Especial: -20% de Dano (Redução de Dano)" );
        System.out.println("Lannister:\n\tVida: 50 || Ataque: 20 || Defesa: 10 || Alcance: 2\n\tHabilidade Especial: +15% de Dano (Dano Líquido)" );
        System.out.println("Targaryen:\n\tVida: 45 || Ataque: 20 || Defesa: 10 || Alcance: 3\n\tHabilidade Especial: Ignora Defesa Base (Dano Bruto)" );
        time1 = new Time(false); // Cria o time do jogador humano.
        System.out.println("Você deseja jogar contra um computador? [S/N]");
        String ans = Main.s.nextLine();
        if (ans.equals("S") || ans.equals("s"))
            time2 = new Time(true); // Cria o time do bot.
        else
            time2 = new Time(false); // Cria o time do jogador 2 humano.

        tabuleiro.posicionarTimes(time1, time2); // Coloca os personagens no tabuleiro.
    }

    // Método principal que controla o loop do jogo, alternando turnos até um time ser derrotado.
    public void rodarJogo() {
        limparTela();
        System.out.println("\n--- A BATALHA DE WESTEROS COMEÇOU ---");
        int numeroTurno = 1;
        boolean vezDoTime1 = true; // Controla qual time joga.

            try {
                Thread.sleep(1000); 
            } 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        // O loop principal do jogo.
        while (!this.restaUmTime()) {
            String log = null;
            System.out.println("\n===== TURNO " + numeroTurno + " =====");
            tabuleiro.imprimirTabuleiro();
            System.out.println("--- STATUS DOS TIMES ---");

            System.out.println("Time 1:");
            for (Personagem p : time1.getPersonagens()) {
                p.imprimeStatus(); // Chama o método que já existe em Personagem.java
            }

            System.out.println("Time 2:");
            for (Personagem p : time2.getPersonagens()) {
                p.imprimeStatus(); // Chama o método que já existe em Personagem.java
            }

            if (vezDoTime1) {
                // Turno do Time 1 (Humano).
                System.out.println("\n--- VEZ DO TIME 1 ---");
                Personagem p1 = Jogo.escolherPersonagem(time1);
                if (p1 == null) {
                    System.out.println("Time 1 nao tem personagens para agir.");
                } else {
                    log = this.escolherEAgir(p1, time2); // Ação humana.
                    p1.setJaSelecionado(true); // Marca que o personagem já agiu.
                }
            } else {
                // Turno do Time 2 (Bot ou Humano).
                limparTela();
                System.out.println("\n--- VEZ DO TIME 2 ---");
                Personagem p2;
                if (time2.isBot()) {
                    p2 = escolherPersonagemParaBotAgir(time2); // Bot escolhe.
                } else {
                    p2 = Jogo.escolherPersonagem(time2); // Humano 2 escolhe.
                }

                if (p2 == null) {
                    System.out.println("Time 2 nao tem personagens para agir.");
                } else {
                    // Executa a ação (Bot ou Humano).
                    if (time2.isBot()) {
                        log = executarAcaoBot(p2, time1);
                    } else {
                        log = this.escolherEAgir(p2, time1);
                    }
                    p2.setJaSelecionado(true);
                }
            }

            // Alterna a vez.
            vezDoTime1 = !vezDoTime1;
            if (vezDoTime1) {
                numeroTurno++; // Incrementa o número do turno quando volta ao Time 1.
                // Reseta a flag de "já agiu" de todos os personagens.
                time1.resetSelecao();
                time2.resetSelecao();
            }

            replay.registrarQuadro(tabuleiro, time1, time2, log);
        }

        // Fim de jogo.
        System.out.println("\nFIM DE JOGO!");
        tabuleiro.imprimirTabuleiro();
        declararVencedor();
    }

    // Gerencia o turno de um jogador humano: move obrigatoriamente e depois ataca opcionalmente.
    private String escolherEAgir(Personagem p, Time timeInimigo) {
        System.out.println("Agindo com: " + p.getNome());
        p.imprimeStatus();

        // Passo 1: Movimento obrigatório.
        System.out.println("Executando movimento...");
        tabuleiro.movimentarPersonagem(p, Main.s);

        System.out.println("Tabuleiro apos o movimento:");
        tabuleiro.imprimirTabuleiro();

        // Passo 2: Ataque opcional após mover.
        List<Personagem> alvosDisponiveis = p.getAlvos(timeInimigo);

        if (!alvosDisponiveis.isEmpty()) {
            System.out.println("Alvos no alcance apos mover. Deseja atacar? [S/N]");
            String querAtacar = Main.s.nextLine().trim();

            if (querAtacar.equalsIgnoreCase("s")) {
                // Chama o método de ataque do personagem.
                Personagem atacado = p.listaAlvosEAtaca(timeInimigo, Main.s);
                if (atacado == null) {
                    System.out.println(p.getNome() + " decidiu nao atacar ou ocorreu um erro.");
                    return (p.getNome() + " se moveu e não atacou (erro ou negou atacar)");
                }
                return (p.getNome() + " se moveu e atacou " + atacado.getNome());
            } else {
                System.out.println(p.getNome() + " decidiu nao atacar.");
                return (p.getNome() + " se moveu e não atacou (negou atacar)");
            }
        } else {
            System.out.println(p.getNome() + " moveu, mas nao ha alvos no alcance.");
            return (p.getNome() + " se moveu e não atacou (sem alvos no alcance)");
        }
    }

    // Define o Bot: move-se e ataca o inimigo mais próximo no alcance.
    private String executarAcaoBot(Personagem botPersonagem, Time timeInimigo) {
        System.out.println("Bot " + botPersonagem.getNome() + " esta agindo...");
        botPersonagem.imprimeStatus();

        // --- PASSO 1: IDENTIFICAR O ALVO PRIORITÁRIO (PARA ONDE MOVER) ---
        Personagem alvoPrioritario = null;
        int menorDistanciaGeral = Integer.MAX_VALUE;

        // Procura na lista de inimigos o que está mais próximo (menor distância).
        for (Personagem inimigo : timeInimigo.getPersonagens()) {
            if (!inimigo.estaMorto()) {
                int dist = calcularDistanciaEntre(botPersonagem, inimigo);
                if (dist < menorDistanciaGeral) {
                    menorDistanciaGeral = dist;
                    alvoPrioritario = inimigo;
                }
            }
        }

        // Assume-se que 'alvoPrioritario' nunca será nulo, pois o jogo já checa isso.
        System.out.println("Bot " + botPersonagem.getNome() + " está perseguindo " + alvoPrioritario.getNome() + ".");

        // --- PASSO 2: CÁLCULO DO MOVIMENTO OBRIGATÓRIO ---
        int botX = botPersonagem.getX();
        int botY = botPersonagem.getY();
        int alvoX = alvoPrioritario.getX();
        int alvoY = alvoPrioritario.getY();

        int diffX = alvoX - botX;
        int diffY = alvoY - botY;

        String direcaoIdeal; // O melhor eixo para mover (horizontal ou vertical)
        String direcaoSecundaria; // O segundo melhor eixo
        String movimentoEscolhido = null; // W, A, S, D

        // Prioriza o eixo com a maior distância (para se mover na "diagonal" de Chebyshev).
        if (Math.abs(diffX) > Math.abs(diffY)) {
            direcaoIdeal = (diffX > 0) ? "D" : "A"; // Direita ou Esquerda
            direcaoSecundaria = (diffY > 0) ? "S" : "W"; // Baixo ou Cima
        } else {
            direcaoIdeal = (diffY > 0) ? "S" : "W"; // Baixo ou Cima
            direcaoSecundaria = (diffX > 0) ? "D" : "A"; // Direita ou Esquerda
        }

        // Tenta usar o movimento ideal (mais rápido para o alvo).
        if (tabuleiro.movimentoValido(direcaoIdeal, botPersonagem)) {
            movimentoEscolhido = direcaoIdeal;
        }
        // Se não puder, tenta o movimento secundário (a outra direção para o alvo).
        else if (tabuleiro.movimentoValido(direcaoSecundaria, botPersonagem)) {
            movimentoEscolhido = direcaoSecundaria;
        }
        // Se estiver bloqueado, tenta um movimento aleatório (pois TEM que mover).
        else {
            System.out.println(botPersonagem.getNome() + " está bloqueado. Tentando movimento aleatório...");
            List<String> movimentos = new ArrayList<>(List.of("W", "A", "S", "D"));
            Collections.shuffle(movimentos); // Embaralha as direções
            for (String m : movimentos) {
                if (tabuleiro.movimentoValido(m, botPersonagem)) {
                    movimentoEscolhido = m;
                    break; // Para no primeiro movimento aleatório válido que encontrar.
                }
            }
        }

        // --- PASSO 3: EXECUTAR O MOVIMENTO OBRIGATÓRIO ---
        if (movimentoEscolhido != null) {
            System.out.println("Bot " + botPersonagem.getNome() + " moveu para " + movimentoEscolhido);

            int xAntigo = botPersonagem.getX();
            int yAntigo = botPersonagem.getY();

            // Atualiza as coordenadas (X, Y) do objeto Personagem (ENCAPSULADO).
            switch (movimentoEscolhido) {
                case "W" -> botPersonagem.setY(botPersonagem.getY() - 1);
                case "S" -> botPersonagem.setY(botPersonagem.getY() + 1);
                case "A" -> botPersonagem.setX(botPersonagem.getX() - 1);
                case "D" -> botPersonagem.setX(botPersonagem.getX() + 1);
            }

            // Atualiza a matriz do tabuleiro (ENCAPSULADO).
            tabuleiro.atualizarPosicaoPersonagem(botPersonagem, xAntigo, yAntigo);

        } else {
            // Isso só acontece se o bot estiver 100% cercado e não puder mover.
            System.out.println("Bot " + botPersonagem.getNome() + " está cercado e não pode se mover!");
        }

        // --- PASSO 4: EXECUTAR O ATAQUE (Pós-Movimento) ---

        // Reavalia os alvos disponíveis da NOVA posição.
        List<Personagem> alvosEmAlcance = botPersonagem.getAlvos(timeInimigo);

        // Se houver alvos no alcance após o movimento.
        if (!alvosEmAlcance.isEmpty()) {

            // Escolhe o alvo mais próximo DENTRE OS QUE ESTÃO NO ALCANCE.
            Personagem alvoParaAtacar = null;
            int menorDistanciaAtaque = Integer.MAX_VALUE;

            for (Personagem inimigoEmAlcance : alvosEmAlcance) {
                int distancia = calcularDistanciaEntre(botPersonagem, inimigoEmAlcance);
                if (distancia < menorDistanciaAtaque) {
                    menorDistanciaAtaque = distancia;
                    alvoParaAtacar = inimigoEmAlcance;
                }
            }

            // Ataca o alvo escolhido (o mais próximo no alcance).
            if(alvoParaAtacar != null) {
                System.out.println("ALVO NO ALCANCE! " + botPersonagem.getNome() + " ataca " + alvoParaAtacar.getNome() + "!");
                botPersonagem.atacar(alvoParaAtacar);

                // Verifica se o alvo morreu após o ataque.
                if (alvoParaAtacar.estaMorto()) {
                    System.out.println(alvoParaAtacar.getNome() + " foi derrotado pelo Bot!");
                    timeInimigo.eliminaJogador(alvoParaAtacar); // Remove da lista do time.
                    tabuleiro.removerPersonagemDoTabuleiro(alvoParaAtacar); // Remove do tabuleiro.
                }
            }
            return (botPersonagem.getNome() + " (Bot) se moveu e atacou " + alvoParaAtacar.getNome());
        } else {
            System.out.println("Bot " + botPersonagem.getNome() + " terminou o turno sem alvos no alcance.");
            return (botPersonagem.getNome() + " (Bot) se moveu e não atacou (sem alvos no alcance)");
        }
    }

    // Permite que um jogador humano escolha um personagem jogável (vivo e que não agiu).
    protected static Personagem escolherPersonagem(Time time) {
        List<Personagem> personagensJogaveis = new ArrayList<>();
        System.out.println("Escolha um personagem para agir: ");

        // Filtra personagens que podem agir.
        for (Personagem p : time.getPersonagens()) {
            if (!p.estaMorto() && !p.isJaSelecionado()) {
                personagensJogaveis.add(p);
            }
        }

        // Retorna nulo se ninguém puder agir.
        if (personagensJogaveis.isEmpty()) {
            System.out.println("Nenhum personagem disponivel para agir.");
            return null;
        }

        // Imprime a lista de personagens jogáveis.
        int i = 0;
        for (Personagem p : personagensJogaveis) {
            i++;
            System.out.println("[ " + i + " ] " + p.getNome() + "(" + p.getClass().getSimpleName() + ")");
        }

        // Loop para garantir uma entrada numérica válida.
        int opc = -1;
        do {
            try {
                String entrada = Main.s.nextLine();
                opc = Integer.parseInt(entrada);

                if (opc < 1 || opc > i) {
                    System.out.println("Seleção inválida! Digite um numero entre 1 e " + i);
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida, digite apenas o numero.");
                opc = -1;
            }

        } while (opc < 1 || opc > i);

        // Retorna o personagem escolhido pelo índice.
        System.out.println(personagensJogaveis.get(opc - 1).getNome() + " selecionado");
        return personagensJogaveis.get(opc - 1);
    }

    // Lógica do Bot para selecionar qual personagem agir (pega o primeiro vivo disponível).
    public Personagem escolherPersonagemParaBotAgir(Time timeDoBot) {
        for (Personagem p : timeDoBot.getPersonagens()) {
            if (!p.estaMorto() && !p.isJaSelecionado()) {
                return p;
            }
        }
        return null; // Retorna nulo se ninguém puder agir.
    }

    // Imprime a mensagem final do vencedor com base em qual time foi derrotado.
    private void declararVencedor() {
        if (time1.timeDerrotado()) {
            System.out.println("O TIME 2 VENCEU A BATALHA!");
        } else if (time2.timeDerrotado()) {
            System.out.println("O TIME 1 VENCEU A BATALHA!");
        } else {
            System.out.println("A batalha terminou inesperadamente.");
        }
    }

    // Calcula a "Distância de Chebyshev" (útil para tabuleiros em grade).
    private int calcularDistanciaEntre(Personagem p1, Personagem p2) {
        int diffX = Math.abs(p1.getX() - p2.getX());
        int diffY = Math.abs(p1.getY() - p2.getY());
        return Math.max(diffX, diffY); // Retorna a maior das distâncias (X ou Y).
    }

    // Verifica a condição de fim de jogo (se algum time perdeu todos os personagens).
    protected boolean restaUmTime() {
        return time1.timeDerrotado() || time2.timeDerrotado();
    }

    // Getter simples para o Time 1.
    protected Time getTime1() {
        return time1;
    }

    // Getter simples para o Time 2.
    protected Time getTime2() {
        return time2;
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Replay getReplay() {
        return this.replay;
    }


}