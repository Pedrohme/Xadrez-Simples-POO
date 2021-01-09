/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

import java.util.Scanner;
import java.io.File;
import projeto.Jogo.InvalidFileException;

public class Gerenciador {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Jogo jogo = null;
        
        System.out.println("Começar um novo jogo: n   -   Carregar um jogo salvo: c");
        char opcao = in.next().charAt(0);
        in.nextLine();

        switch (opcao) {
            case 'n':
                System.out.println("Insira o nome do jogador 1 (Branco): ");
                String jog1 = in.nextLine();
                System.out.println("Insira o nome do jogador 2 (Preto): ");
                String jog2 = in.nextLine();
                System.out.println("");
                
                jogo = new Jogo(jog1, jog2, in);
                jogo.iniciarJogo();
                break;
            case 'c':
                System.out.println("Insira o nome do arquivo: ");
                String nome = in.nextLine();
                File txt = null;
                if (nome.endsWith(".txt")) {
                    txt = new File(nome);
                }
                else {
                    txt = new File(nome+".txt");
                }
                if (txt.exists()) {
                    try {
                        jogo = new Jogo(in, txt);
                        jogo.iniciarJogo();
                    }
                    catch (InvalidFileException e){
                        System.out.println("Arquivo inválido, terminando programa...");
                    }
                }
                else {
                    System.out.println("Arquivo nao existe, terminando programa...");
                }
                break;
            default:
                System.out.println("Opcao invalida, terminando programa...");
                break;
        }
        
        in.close();
    }
}
