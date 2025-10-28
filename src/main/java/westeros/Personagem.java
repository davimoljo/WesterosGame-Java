package westeros;
import java.util.*;

public class Personagem {
    protected String nome;
    private int x; // Posição no eixo X
    private int y; // Posição no eixo Y
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected int alcance;
    protected boolean bot; // Flag se é controlado por IA
    private boolean jaSelecionado; // Flag se já agiu neste turno
    private static int contadorPersonagens = 0; // Contador estático para rastrear personagens criados.
    private int personagemID; // ID único do personagem.

    // Construtor base: Define se é bot e reseta o status de seleção.
    public Personagem(boolean bot){
        this.bot = bot;
        this.jaSelecionado = false;
        setPersonagemID(contadorPersonagens++);
    }

    public Personagem(Personagem p){
        this.nome = p.getNome();
        this.vida = p.getVida();
        this.x = p.getX();
        this.y = p.getY();
        this.ataque = p.ataque;
        this.defesa = p.defesa;
        this.alcance = p.alcance;
        this.bot = p.bot;
        this.jaSelecionado = p.jaSelecionado;
        this.personagemID = p.getPersonagemID();
    }

    // Define o ataque básico (padrão) do personagem, chamando o 'receberDano' do alvo.
    public void atacar(Personagem p){
        p.receberDano(ataque);
    }

    // Processa o dano recebido, aplica a defesa e imprime o feedback de combate.
    public void receberDano(int dano) {
        int danoReal = Math.max(0, dano - this.defesa); // Dano mínimo de 0
        this.vida -= danoReal;

        // Imprime o feedback de dano e status atualizado
        System.out.println("\n>>> " + this.getNome() + " sofreu " + danoReal + " de dano!");
        this.imprimeStatus();
    }

    // Gerencia a lógica de ataque: ataca automático (1 alvo) ou pede ao jogador (múltiplos).
    protected Personagem listaAlvosEAtaca(Time adversarios, Scanner s) {
        List<Personagem> alvos = getAlvos(adversarios);

        // Regra 1: Nenhum alvo no alcance.
        if (alvos.isEmpty()) {
            System.out.println("Sem alvos disponíveis no alcance.");
            return null;
        }

        // Regra 2: Apenas um alvo no alcance (ataque automático).
        if (alvos.size() == 1) {
            Personagem alvoUnico = alvos.get(0);
            System.out.println("Apenas um alvo no alcance: " + alvoUnico.getNome() + ". Atacando automaticamente!");
            atacar(alvoUnico);

            if (alvoUnico.estaMorto()) {
                System.out.println(alvoUnico.getNome() + " foi derrotado!");
                adversarios.eliminaJogador(alvoUnico);
            }
            return alvoUnico;
        }

        // Regra 3: Múltiplos alvos (jogador escolhe).
        System.out.println("Selecione o alvo para atacar:");
        int i = 0;
        for (Personagem alvo : alvos) {
            i++;
            System.out.println("[" + i + "] - " + alvo.getNome() + " (Casa: " + alvo.getClass().getSimpleName() + " Vida: " + alvo.getVida() + " || Defesa: " + alvo.getDefesa() + ")");
        }

        // Loop de validação da entrada do usuário
        int entrada = -1;
        while (entrada < 1 || entrada > alvos.size()) {
            System.out.println("Digite um numero entre 1 e " + alvos.size());
            try {
                String linha = s.nextLine();
                entrada = Integer.parseInt(linha);

                if (entrada < 1 || entrada > alvos.size()) {
                    System.out.println("Entrada invalida: selecione um alvo válido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida: digite apenas o número.");
                entrada = -1;
            }
        }

        Personagem alvoEscolhido = alvos.get(entrada - 1);
        atacar(alvoEscolhido);

        if (alvoEscolhido.estaMorto()) {
            System.out.println(alvoEscolhido.getNome() + " foi derrotado!");
            adversarios.eliminaJogador(alvoEscolhido);
        }
        return alvoEscolhido; // Ataque ocorreu.
    }

    // Verifica se a vida do personagem chegou a zero ou menos.
    public boolean estaMorto(){
        return vida <= 0;
    }

    // Verifica se este personagem já agiu no turno atual.
    public boolean isJaSelecionado() {
        return this.jaSelecionado;
    }

    // Setter para 'jaSelecionado'
    public void setJaSelecionado(boolean selecionado) {
        this.jaSelecionado = selecionado;
    }

    // O 'getVida()' é útil também
    public int getVida() {
        return this.vida;
    }

    // Calcula se um 'alvo' está dentro do alcance de ataque (usando Distância de Chebyshev).
    protected boolean estaEmAlcance(Personagem alvo){
        int dist = Math.max(Math.abs(alvo.getX() - this.getX()), Math.abs(alvo.getY() - this.getY()));
        return (dist <= this.getAlcance());
    }

    // Verifica se este personagem é controlado pela IA (Bot).
    public boolean isBot() {
        return bot;
    }

    // Imprime os atributos atuais do personagem (Vida, Ataque, etc.) no console.
    protected void imprimeStatus(){
        System.out.println(this.getNome() + "(" + this.getClass().getSimpleName() + "): " + "Vida: " + this.vida + " || Defesa: " + this.defesa + " || Ataque: " + this.ataque + " || Alcance: " + this.alcance);
    }

    // Retorna uma lista de todos os inimigos vivos que estão no alcance de ataque.
    protected List<Personagem> getAlvos(Time adversarios) {
        List<Personagem> alvos = new ArrayList<>();
        for (Personagem alvo : adversarios.getPersonagens()) {
            if (!alvo.estaMorto() && estaEmAlcance(alvo))
                alvos.add(alvo);
        }
        return alvos;
    }

    // Sobrescrita do 'equals' para comparar personagens (essencial para remover da lista).
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Personagem)) {
            return false;
        }
        Personagem that = (Personagem) o;
        if (this.nome != null) {
            return this.nome.equals(that.nome);
        } else {
            return that.nome == null;
        }
    }

    // Getter para o alcance do personagem.
    protected int getAlcance() {
        return alcance;
    }

    // Getter para o nome do personagem.
    String getNome(){
        return nome;
    }

    // Getter para a coordenada X.
    int getX(){
        return x;
    }

    // Setter para X
    public void setX(int x) {
        this.x = x;
    }

    // Getter para a coordenada Y.
    int getY(){
        return y;
    }

    // Setter para Y
    public void setY(int y) {
        this.y = y;
    }

    // Getter para a defesa do personagem.
    public int getDefesa(){
        return defesa;
    }

    public int getPersonagemID() {
        return personagemID;
    }public void setPersonagemID(int personagemID) {
        this.personagemID = personagemID;
    }


}