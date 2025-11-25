
# ⚔️ Battle for Westeros (WesterosGame-Java)

> **Final Project for Object-Oriented Programming** | Computer Science - UFJF

This project consists of a turn-based strategy game (TBS) running on the console, set in the Game of Thrones universe. The objective was to apply the four pillars of Object-Oriented Programming to create a robust, extensible, and interactive system.

---

## 🎯 About the Project

The game simulates tactical combat on a 10x10 grid between two noble houses. The player can face another player (local PvP) or challenge an Artificial Intelligence (PvE). The technical differentiator of the project is the implementation of a **Tactical AI** (non-random) and a complete **Replay** system for the match.

---

## 🛠️ OOP Principles Applied

The core of this project is object-oriented modeling. Below, we detail how each pillar was utilized in the architecture:

### 1. Inheritance (`extends`)
We used inheritance to avoid code repetition and create a logical hierarchy.
* **Base Class:** `Personagem` (Character) (in the `westeros.personagens` package) defines common attributes (health, attack, defense, position) and standard behaviors.
* **Subclasses:** `Stark`, `Lannister`, and `Targaryen` inherit from `Personagem`. They reuse all movement and state logic, focusing only on defining their specific attributes.

### 2. Polymorphism (`@Override`)
This was essential for the **Special Abilities**. Although the game treats everyone as a `Personagem`, each house reacts differently at runtime:
* **`Stark`:** Overrides the `receberDano()` (takeDamage) method to apply its damage reduction (tank).
* **`Lannister`:** Overrides the `atacar()` (attack) method to calculate amplified damage.
* **`Targaryen`:** Overrides the `atacar()` (attack) method to implement logic that ignores the opponent's defense.

### 3. Encapsulation (`private` & `protected`)
To ensure data integrity and reduce coupling:
* Sensitive attributes like `health`, `x`, `y`, and `Time` (Team) lists are private.
* Access is strictly done through **Getters and Setters** or business methods (e.g., `receberDano` instead of `setVida`).
* This allows, for example, the `Time` class to manage a character's death (`eliminaJogador`) without exposing the internal list to improper external manipulation.

### 4. Abstraction
The classes were designed to represent real-world entities within the game:
* `Tabuleiro` (Board): Abstracts the complexity of the matrix and movement validation.
* `Time` (Team): Abstracts the management of unit groups.
* `Jogo` (Game): Acts as the *Controller*, abstracting the flow of turns and victory rules.

---

## 🚀 Architecture and Algorithm Highlights

Beyond basic object CRUD, we implemented advanced logic:

### 🤖 Artificial Intelligence (Hunting Heuristic)
The Bot does not roll dice randomly. We implemented a tactical search algorithm:
1.  **Detection:** The Bot scans the board and calculates the distance to all living enemies.
2.  **Chebyshev Metric:** We use `Math.max(|x1-x2|, |y1-y2|)` to calculate the real distance in turns (considering grid movement).
3.  **Pursuit:** The Bot identifies the nearest target and calculates the ideal movement vector to reduce the X or Y distance.
4.  **Anti-Blocking:** If the ideal path is blocked, it has a *fallback* routine to attempt alternative routes.

### 📼 Replay System (Snapshot Pattern)
We implemented a variation of the **Memento** design pattern.
* At every turn, the `Replay` class captures a "Snapshot" (`Memoria`) of the current game state.
* We perform a **Deep Copy** of the `Personagem` objects and the `Tabuleiro` to ensure history is not altered by future game actions.
* This allows rewatching the entire match step-by-step at the end of the game.

### 📦 Modularization (Packages)
The code was organized into packages to separate concerns (close to an MVC architecture):
* `westeros`: Entry point (`Main`).
* `westeros.jogo`: Control logic and environment (`Jogo`, `Tabuleiro`, `Time`).
* `westeros.personagens`: Data models (`Personagem` and subclasses).

---

## 🎮 How to Run

1.  Ensure you have **Java JDK 17+** installed.
2.  Clone the repository.
3.  Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, VS Code).
4.  Run the `westeros.Main` class.

# ⚔️ Batalha de Westeros (WesterosGame-Java)

> **Projeto Final de Programação Orientada a Objetos** | Ciência da Computação - UFJF

Este projeto consiste em um jogo de estratégia em turnos (TBS - *Turn-Based Strategy*) rodando em console, ambientado no universo de Game of Thrones. O objetivo foi aplicar os quatro pilares da Orientação a Objetos para criar um sistema robusto, extensível e interativo.

---

## 🎯 Sobre o Projeto

O jogo simula um combate tático em um tabuleiro 10x10 entre duas casas nobres. O jogador pode enfrentar outro jogador (PvP local) ou desafiar uma Inteligência Artificial (PvE). O diferencial técnico do projeto é a implementação de um sistema de **IA Tática** (não aleatória) e um sistema de **Replay** completo da partida.

---

## 🛠️ Princípios de Orientação a Objetos Aplicados

O coração deste projeto é a modelagem orientada a objetos. Abaixo, detalhamos como cada pilar foi utilizado na arquitetura:

### 1. Herança (`extends`)
Utilizamos herança para evitar repetição de código e criar uma hierarquia lógica.
* **Classe Base:** `Personagem` (no pacote `westeros.personagens`) define os atributos comuns (vida, ataque, defesa, posição) e comportamentos padrão.
* **Subclasses:** `Stark`, `Lannister` e `Targaryen` herdam de `Personagem`. Elas reaproveitam toda a lógica de movimento e estado, focando apenas em definir seus atributos específicos.

### 2. Polimorfismo (`@Override`)
Este foi essencial para as **Habilidades Especiais**. Embora o jogo trate todos como `Personagem`, cada casa reage de forma diferente em tempo de execução:
* **`Stark`:** Sobrescreve o método `receberDano()` para aplicar sua redução de dano (tank).
* **`Lannister`:** Sobrescreve o método `atacar()` para calcular um dano amplificado.
* **`Targaryen`:** Sobrescreve o método `atacar()` para implementar uma lógica de dano que ignora a defesa do oponente.

### 3. Encapsulamento (`private` & `protected`)
Para garantir a integridade dos dados e reduzir o acoplamento:
* Atributos sensíveis como `vida`, `x`, `y` e as listas de `Time` são privados.
* O acesso é feito estritamente através de **Getters e Setters** ou métodos de negócio (ex: `receberDano` em vez de `setVida`).
* Isso permite, por exemplo, que a classe `Time` gerencie a morte de um personagem (`eliminaJogador`) sem expor a lista interna para manipulação externa indevida.

### 4. Abstração
As classes foram desenhadas para representar entidades do mundo real do jogo:
* `Tabuleiro`: Abstrai a complexidade da matriz e validação de movimento.
* `Time`: Abstrai o gerenciamento de grupos de unidades.
* `Jogo`: Atua como o *Controller*, abstraindo o fluxo de turnos e regras de vitória.

---

## 🚀 Destaques da Arquitetura e Algoritmos

Além do CRUD básico de objetos, implementamos lógicas avançadas:

### 🤖 Inteligência Artificial (Heurística de Caça)
O Bot não joga dados aleatoriamente. Implementamos um algoritmo de busca tática:
1.  **Detecção:** O Bot varre o tabuleiro e calcula a distância para todos os inimigos vivos.
2.  **Métrica de Chebyshev:** Utilizamos `Math.max(|x1-x2|, |y1-y2|)` para calcular a distância real em turnos (considerando movimentos em grade).
3.  **Perseguição:** O Bot identifica o alvo mais próximo e calcula o vetor de movimento ideal para reduzir a distância X ou Y.
4.  **Anti-Bloqueio:** Se o caminho ideal estiver bloqueado, ele possui uma rotina de *fallback* para tentar rotas alternativas.

### 📼 Sistema de Replay (Snapshot Pattern)
Implementamos uma variação do padrão de projeto **Memento**.
* A cada turno, a classe `Replay` captura um "Snapshot" (`Memoria`) do estado atual do jogo.
* Realizamos uma **Cópia Profunda (Deep Copy)** dos objetos `Personagem` e do `Tabuleiro` para garantir que o histórico não seja alterado pelas ações futuras do jogo.
* Isso permite reassistir a partida inteira passo-a-passo ao final do jogo.

### 📦 Modularização (Pacotes)
O código foi organizado em pacotes para separar responsabilidades (quase um MVC):
* `westeros`: Ponto de entrada (`Main`).
* `westeros.jogo`: Lógica de controle e ambiente (`Jogo`, `Tabuleiro`, `Time`).
* `westeros.personagens`: Modelos de dados (`Personagem` e subclasses).

---

## 🎮 Como Executar

1.  Certifique-se de ter o **Java JDK 17+** instalado.
2.  Clone o repositório.
3.  Abra o projeto na sua IDE de preferência (IntelliJ IDEA, Eclipse, VS Code).
4.  Execute a classe `westeros.Main`.

---
