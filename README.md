# ⚔️ WesterosGame-Java

Este é um projeto de Programação Orientada a Objetos desenvolvido em Java. É um jogo de estratégia em turnos para console, onde o objetivo é derrotar o time adversário.

Você controla um time de 3 personagens, escolhendo entre as casas Stark, Lannister ou Targaryen, cada uma com habilidades únicas.

## ✨ Funcionalidades

* **Jogo em Turnos:** Lute em um tabuleiro 10x10. O movimento é obrigatório em cada turno.
* **3 Casas Nobres:**
    * **Stark:** Foco defensivo (recebe 20% menos dano).
    * **Lannister:** Foco ofensivo (causa 15% a mais de dano).
    * **Targaryen:** Foco em dano "puro" (ignora a defesa base do oponente).
* **Modos de Jogo:** Jogue contra outro jogador (PvP local) ou contra o computador (PvE).
* **IA Tática:** O Bot (IA) não age aleatoriamente. Ele identifica e "caça" ativamente o inimigo mais próximo no mapa.
* **Sistema de Replay:** Ao final de cada partida, você pode assistir ao replay completo de todas as jogadas.
* **Interface de Console:** O jogo usa cores para diferenciar os times e limpa a tela a cada turno para melhor visualização.

## 🚀 Como Executar

A forma mais simples é executar o projeto através de uma IDE Java (como IntelliJ, Eclipse ou VS Code).

1.  Clone este repositório:
    ```bash
    git clone [https://github.com/davimoljo/WesterosGame-Java.git]
    ```
2.  Abra a pasta do projeto na sua IDE.
3.  Localize e execute o arquivo `src/westeros/Main.java`.

## 📁 Estrutura do Projeto

O código está organizado em 3 pacotes principais para separação de responsabilidades:

* `westeros`: Contém o `Main.java` (ponto de entrada da aplicação).
* `westeros.jogo`: Contém as classes que controlam a lógica do jogo (Jogo, Tabuleiro, Time, Menu, Replay).
* `westeros.personagens`: Contém a classe base `Personagem` e suas subclasses (Stark, Lannister, Targaryen).

# ⚔️ WesterosGame-Java (English)

This is an Object-Oriented Programming (OOP) project developed in Java. It is a turn-based strategy game for the console, where the objective is to defeat the opposing team.

You control a team of 3 characters, choosing from House Stark, Lannister, or Targaryen, each with unique abilities.

## ✨ Features

* **Turn-Based Gameplay:** Fight on a 10x10 grid. Movement is mandatory on every turn.
* **3 Noble Houses:**
    * **Stark:** Defensive focus (takes 20% less damage).
    * **Lannister:** Offensive focus (deals 15% more damage).
    * **Targaryen:** "Pure damage" focus (attack ignores the opponent's base defense).
* **Game Modes:** Play against another player (local PvP) or against the computer (PvE).
* **Tactical AI:** The Bot (AI) does not act randomly. It actively identifies and "hunts" the nearest enemy on the map.
* **Replay System:** At the end of each match, you can watch a full replay of all moves.
* **Console Interface:** The game uses colors to differentiate teams and clears the screen every turn for a better user experience.

## 🚀 How to Run

The easiest way to run this project is through a Java IDE (like IntelliJ, Eclipse, or VS Code).

1.  Clone this repository:
    ```bash
    git clone [https://github.com/davimoljo/WesterosGame-Java.git]
    ```
2.  Open the project folder in your IDE.
3.  Locate and run the `src/westeros/Main.java` file.

## 📁 Project Structure

The code is organized into 3 main packages for separation of concerns:

* `westeros`: Contains `Main.java` (the application's entry point).
* `westeros.jogo`: Contains the game logic and controller classes (Game, Board, Team, Menu, Replay).
* `westeros.personagens`: Contains the base `Personagem` (Character) class and its subclasses (Stark, Lannister, Targaryen).
