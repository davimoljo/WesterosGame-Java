package westeros.jogo;

public class Menu {

    //funções print que imprimem os letreiros do menu, instruções e créditos
    public Menu(){}
    public void imprimeMenu(){
        imprimeTitulo();
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Jogar");
        System.out.println("2 - Instruções");
        System.out.println("3 - Créditos");
        System.out.println("4 - Sair");
    }
    private void imprimeTitulo(){
                String ascii = """
        ##      ## ########   :####:   ########  ########  ######:    .####.    :####:
        ##.    .## ########  :######   ########  ########  #######    ######   :######  
        ##:    :## ##        ##:  :#      ##     ##        ##   :##  :##  ##:  ##:  :#  
         #: ## :#  ##        ##           ##     ##        ##    ##  ##:  :##  ##       
        :# .## ##: ##        ###:         ##     ##        ##   :##  ##    ##  ###:     
        :##.##.##: #######   :#####:      ##     #######   #######:  ##    ##  :#####:  
        .##:##:##. #######    .#####:     ##     #######   ######    ##    ##   .#####: 
         ##    ##. ##            :###     ##     ##        ##   ##.  ##    ##      :### 
         ###::###  ##              ##     ##     ##        ##   ##   ##:  :##        ## 
         ###..###  ##        #:.  :##     ##     ##        ##   :##  :##  ##:  #:.  :## 
         ###  ###  ########  #######:     ##     ########  ##    ##:  ######   #######: 
          ##  ###  ########  .#####:      ##     ########  ##    ###  .####.   .#####:  
        """;
        System.out.println(ascii);
    }
    public void imprimeInstrucoes(){
        System.out.println("Instruções:");
        System.out.println("1. Escolha uma casa (Stark, Lannister ou Targaryen).");
        System.out.println("2. Cada casa tem características únicas.");
        System.out.println("3. O jogo é turn-based, onde você ataca o inimigo.");
        System.out.println("4. O objetivo é eliminar todos os personagens do time adversário.");
    }

    public void imprimeCreditos(){
        String creditos = """
                João Pedro Lemos Guadalupe,
                Carlos Roberto da Silva,
                Davi Moljo Domingues
                """;
        System.out.println("Créditos:");
        System.out.println("Desenvolvido por: ");
        System.out.println(creditos);
    }
}
