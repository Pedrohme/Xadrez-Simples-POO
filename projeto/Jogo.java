/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;  
import java.io.IOException;
import java.util.InputMismatchException;

public class Jogo {
    private char xeque, xequeMate;
    private int rodada;
    private Tabuleiro tab;
    private Jogador j1, j2;
    private Peca[][] pecas;
    private Scanner in;

    /**
     * inicializa um jogo novo
     */
    public Jogo(String jog1, String jog2, Scanner in) {
        pecas = new Peca[2][16];
        tab = new Tabuleiro();
        j1 = new Jogador('b', jog1);
        j2 = new Jogador('p', jog2);
        for (int i = 0; i < 16; i++) {
            pecas[0][i] = j1.getPeca(i);
            pecas[1][i] = j2.getPeca(i);
        }
        for (int i = 0; i < 8; i++) {
            char col = Tabuleiro.COLUNAS[i];
            tab.getPosicao(2, col).setPeca(pecas[0][i]);
            tab.getPosicao(1, col).setPeca(pecas[0][i+8]);
            tab.getPosicao(7, col).setPeca(pecas[1][i]);
            tab.getPosicao(8, col).setPeca(pecas[1][i+8]);
        }
        rodada = 0;
        xeque = 'n';
        xequeMate = 'n';
        this.in = in;
    }

    /**
     * Excecao para erro no arquivo de carregamento
     */
    public class InvalidFileException extends Exception {
        private static final long serialVersionUID = 4025322224312526265L;

        public InvalidFileException(String message) {
            super(message);
        }
        public InvalidFileException() {
            super();
        }
    }

    /**
     * inicializa um jogo salvo a partir de um arquivo .txt
     */
    public Jogo(Scanner in, File f) throws InvalidFileException {
        char aux, aux2;
        pecas = new Peca[2][16];
        tab = new Tabuleiro();
        try (Scanner reader = new Scanner(f)){  
            j1 = new Jogador('b', reader.nextLine());
            j2 = new Jogador('p', reader.nextLine());
            this.rodada = reader.nextInt();
            reader.nextLine();
            for (int i = 0; i < 16; i++) {
                pecas[0][i] = j1.getPeca(i);
                pecas[0][i].setViva(false);
                pecas[1][i] = j2.getPeca(i);
                pecas[1][i].setViva(false);
            }
            for (int i = 0; i < 8; i++) {
                String linha = reader.nextLine();
                for (int j = 0; j < 8; j++) {
                    if ( linha.length() != 8) {
                        throw new InvalidFileException("Arquivo inválido");
                    }
                    aux = linha.charAt(j);
                    aux2 = Character.toLowerCase(aux);
                    if (aux == 'n') {
                        continue;
                    }
                    else if (aux2 == 'b' || aux2 == 'c' || aux2 == 'd' || aux2 == 'p' || aux2 == 'r' || aux2 == 't') {
                        if (Character.isUpperCase(aux)) {
                            for (int k = 0; k < 16; k++) {
                                if (!pecas[0][k].isViva() && pecas[0][k].desenho() == aux) {
                                    pecas[0][k].setViva(true);
                                    tab.getPosicao(Tabuleiro.LINHAS[i], Tabuleiro.COLUNAS[j]).setPeca(pecas[0][k]);
                                    break;
                                }
                            }
                        }
                        else if (!Character.isUpperCase(aux)) {
                            for (int k = 0; k < 16; k++) {
                                if (!pecas[1][k].isViva() && pecas[1][k].desenho() == aux) {
                                    pecas[1][k].setViva(true);
                                    tab.getPosicao(Tabuleiro.LINHAS[i], Tabuleiro.COLUNAS[j]).setPeca(pecas[1][k]);
                                    break;
                                }
                            }
                        }
                    }  
                    else {
                        throw new InvalidFileException();
                    }
                }
            }
        }
        catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
        this.in = in;
        xeque = tab.checaXeque();
        xequeMate = 'n';
    }

    /**
     * metodo para receber um input char apenas de letras
     */
    private char inputChar() throws InputMismatchException {
        char input;
        input = in.next().charAt(0);
        if (!Character.toString(input).matches("^[a-zA-Z]*$")) {
            in.nextLine();
            throw new InputMismatchException("Input inválido");
        }
        return input;
    }

    /**
     * metodo para receber apenas valores validos para linhas de um tabuleiro de xadrez
     */
    private int inputLinha() throws InputMismatchException {
        int input;
        try {
            input = in.nextInt();
            if (input >= 1 && input <= 8) {
                return input;
            }
            else {
                throw new InputMismatchException("Input inválido");
            }
        }
        catch (InputMismatchException e) {
            throw new InputMismatchException("Input inválido");   
        }
    }

    /**
     * metodo para receber apenas valores validos para colunas de um tabuleiro de xadrez
     */
    private char inputColuna() throws InputMismatchException {
        char input;
        input = inputChar();
        if (input >= 'a' && input <= 'h') {
            return input;
        }
        else if (input >= 'A' && input <= 'H') {
            input = Character.toLowerCase(input);
            return input;
        }
        else {
            throw new InputMismatchException("Input inválido");
        }
    }

    /**
     * imprime o tabuleiro e as informacoes de jogo
     */
    private void desenharTab() {
        if (xeque == 'b') {
            System.out.println("xeque: REI BRANCO");
        }
        else if (xeque == 'p') {
            System.out.println("xeque: REI PRETO");
        }
        else {
            System.out.println("xeque: não");
        }
        System.out.println("rodada: " + rodada);
        System.out.println("");
        System.out.println("Jogador 2 - Preto: " + j2.getNome());
        System.out.println("");
        tab.printTabuleiro();
        System.out.println("");
        System.out.println("Jogador 1 - Branco: " + j1.getNome());
        System.out.println("");
    }

    /**
     * imprime menu de opcoes da rodada
     */
    private void printTurno() {
        if (rodada%2 == 0) {
            System.out.println("Turno de " + j1.getNome());
        }
        else {
            System.out.println("Turno de " + j2.getNome());
        }
        System.out.println("Jogar: j   -   Desistir: d   -   Salvar jogo: s");
    }

    /**
     * realiza uma jogada
     * @param atual jogador da rodada atual
     */
    private void jogar(Jogador atual) {
        int linOrig, linDest;
        char colOrig, colDest;
        Posicao posicao = null;
        Peca aux;
        
        System.out.println("escolha uma peça para mover [linha coluna]");
        while (true) {
            try {
                linOrig = inputLinha();
                colOrig = inputColuna();
                break;
            }
            catch (InputMismatchException e) {
                in.nextLine();
                System.out.println("Input inválido!");
                continue;
            }
        }
        
        posicao = tab.getPosicao(linOrig, colOrig);
        
        if (posicao.getPeca() != null && posicao.getPeca().getCor() == atual.getCor()) {
            System.out.println("Voce selecionou: " + posicao.desenho());
            System.out.println("Selecione o destino de " + posicao.desenho() + "  -  para cancelar, digite a posicao de origem");

            while (true) {
                try {
                    linDest = inputLinha();
                    colDest = inputColuna();
                    break;
                }
                catch (InputMismatchException e) {
                    in.nextLine();
                    System.out.println("Input inválido!");
                    continue;
                }
            }
            if (tab.checaMovimento(linOrig, colOrig, linDest, colDest)) {
                aux = tab.moverPeca(linOrig, colOrig, linDest, colDest);
                if (tab.checaXeque() == atual.getCor()) {
                    System.out.println("Movimento inválido, seu rei está em Xeque!");
                    tab.moverPeca(linDest, colDest, linOrig, colOrig);
                    if (aux != null) {
                        tab.getPosicao(linDest, colDest).setPeca(aux);
                    }
                }
                else {
                    rodada++;
                }
            }
            xeque = tab.checaXeque();
        }
    }
    
    /**
     * salva o estado atual do jogo em um arquivo .txt para carregamento posterior
     */
    private boolean salvarJogo() {
        String nome = "";
        System.out.println("Digite o nome do arquivo para salvar, apenas letras e números, até 20 caracteres");
        while (true) {
            nome = in.nextLine();
            if(nome.matches("[A-Za-z0-9.]{1,20}")) {
                break;
            }
            else {
                System.out.println("Nome invalido");
            }
        }
        File salvo = null;
        if (nome.endsWith(".txt")) {
            salvo = new File(nome);
        }
        else {
            salvo = new File(nome+".txt");
        }
        try {
            if (salvo.createNewFile()) {
                System.out.println("Arquivo criado, salvando jogo...");
                try (FileWriter writer = new FileWriter(salvo)){
                    writer.write(j1.getNome()+"\n");
                    writer.write(j2.getNome()+"\n");
                    writer.write(rodada+"\n");
                    for (int i = 8; i > 0; i--) {
                        for (char j = 'a'; j <= 'h'; j++) {
                            Peca aux = tab.getPosicao(i, j).getPeca();
                            if (aux != null) {
                                writer.write(aux.desenho());
                            }
                            else {
                                writer.write("n");
                            }
                        }
                        writer.write("\n");
                    }
                    System.out.println("Jogo salvo!");
                    return true;
                }
                catch (IOException e) {
                    System.out.println("Ocorreu um erro");
                    return false;
                }
            }
            else {
                System.out.println("Já existe um arquivo com esse nome");
                return false;
            }
        }
        catch (IOException e) {
            System.out.println("Ocorreu um erro");
            return false;
        }
    }

    /**
     * inicia o loop principal do jogo
     */
    public void iniciarJogo() {
        Jogador atual = null;
        char menu = 'n';
        System.out.println("");
        System.out.println("Jogo Iniciado!");
        System.out.println("");
        do {
            if (rodada % 2 == 0) {
                atual = j1;
            }
            else {
                atual = j2;
            }
            
            if (xeque != 'n') {
                xequeMate = tab.checaXequeMate(xeque);
                if (xequeMate != 'n') {
                    System.out.println("Xeque-Mate!");
                    break;
                }
            }
            
            this.desenharTab();
            
            this.printTurno();

            while (true) {
                try {
                    menu = inputChar();
                    in.nextLine();
                    break;
                }
                catch (InputMismatchException e) {
                    System.out.println("Input inválido");
                }
            }
            
            switch (menu) {
                case 'j':
                    this.jogar(atual);
                    break;
                case 'd':
                    xequeMate = atual.getCor();
                    break;
                case 's':
                    if (this.salvarJogo()) {
                        return;
                    }
                    else {
                        break;
                    }
                default:
                    System.out.println("Opcao inválida");
                    break;
            }
            menu = 'n';
            System.out.println("");
            System.out.println("");
        } while (xequeMate == 'n');
        
        if (xequeMate == 'b') {
            System.out.println(j2.getNome() + " venceu!");
        }
        else if (xequeMate == 'p') {
            System.out.println(j1.getNome() + " venceu!");
        }
    }
}