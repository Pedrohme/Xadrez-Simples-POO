/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Tabuleiro {
    public static final int[] LINHAS = { 8, 7, 6, 5, 4, 3, 2, 1 };
    public static final char[] COLUNAS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private Posicao[][] posicoes;

    //conversoes de coluna e linha do padrão do tabuleiro de xadrez para padrão de matriz
    static public int colTabToMat(char coluna) {
        return (-1 * ('a' - coluna));
    }
    static public int linTabToMat(int linha) {
        return (-1 * (linha - 8));
    }

    public Tabuleiro() {
        this.posicoes = new Posicao[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        posicoes[i][j] = new Posicao(LINHAS[i], COLUNAS[j], 'b');
                    }
                    else {
                        posicoes[i][j] = new Posicao(LINHAS[i], COLUNAS[j], 'p');
                    }
                }
                else {
                    if (j % 2 == 0) {
                        posicoes[i][j] = new Posicao(LINHAS[i], COLUNAS[j], 'p');
                    }
                    else {
                        posicoes[i][j] = new Posicao(LINHAS[i], COLUNAS[j], 'b');
                    }
                }
            }
        }
    }

    /**
     * @param linha  linha num tabuleiro de xadrez - vai de 1 a 8
     * @param coluna coluna num tabuleiro de xadrez - vai de 'a' a 'h'
     * @return elemento Posicao na posicao descrita pela linhaXcoluna
     */
    public Posicao getPosicao(int linha, char coluna) {
        int colMat = Tabuleiro.colTabToMat(coluna);
        int linMat = Tabuleiro.linTabToMat(linha);

        if (checaLimite(linha, coluna)) {
            return posicoes[linMat][colMat];
        }
        else {
            return null;
        }
    }

    /**
     * checa os limite do tabuleiro
     * @return true se o destino estiver dentro dos limites do tabuleiro, false se nao
     */
    private boolean checaLimite(int linDest, char colDest) {
        int colMat = Tabuleiro.colTabToMat(colDest);
        int linMat = Tabuleiro.linTabToMat(linDest);
        if (linMat < 0 || linMat > 7 || colMat < 0 || colMat > 7) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * checa se a origem é igual ao destino do movimento
     * @return true se o destino for diferente da origem, false se nao
     */
    private boolean checaIgual(int linOrig, char colOrig, int linDest, char colDest) {
        if (colDest == colOrig && linDest == linOrig) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * checa se o destino do movimento é válido
     * OBS: Esse método irá assinalar a peça de destino como morta (viva = false) caso a checagem validar que será comida
     * @param origem a peça de origem
     * @return true se o destino do movimento for vazio ou uma peça inimiga, false se nao
     */
    private boolean checaDestino(int linDest, char colDest, Peca origem) {
        Peca destino = this.getPosicao(linDest, colDest).getPeca();
        if (destino == null) {
            return true;
        }
        else if (destino.getCor() != origem.getCor()) {
            destino.setViva(false);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * checa se ocorrerá uma colisao nas direcoes ortogonais entre a origem e o destino
     * @return true se o movimento for válido e não ocorrer colisao, false se nao
     */
    private boolean checaColisaoOrtogonal(int linOrig, char colOrig, int linDest, char colDest) {
        if (linOrig == linDest) {
            int colOrigMat = Tabuleiro.colTabToMat(colOrig);
            int colDestMat = Tabuleiro.colTabToMat(colDest);
            if (colDest > colOrig) {
                for (int i = colOrigMat+1; i < colDestMat; i++) {
                    if (this.getPosicao(linOrig, COLUNAS[i]).getPeca() != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = colOrigMat-1; i > colDestMat; i--) {
                    if (this.getPosicao(linOrig, COLUNAS[i]).getPeca() != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        else if (colOrig == colDest) {
            if (linDest > linOrig) {
                for (int i = linOrig+1; i < linDest; i++) {
                    if (this.getPosicao(i, colOrig).getPeca() != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = linOrig-1; i > linDest; i--) {
                    if (this.getPosicao(i, colOrig).getPeca() != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * checa se ocorrerá uma colisao nas direcoes diagonais entre a origem e o destino
     * @return true se o movimento for válido e não ocorrer colisao, false se nao
     */
    private boolean checaColisaoDiagonal(int linOrig, char colOrig, int linDest, char colDest) {
        if (linDest > linOrig && colDest > colOrig) {
            int colOrigMat = 0;
            for (int i = 1; i < colDest-colOrig; i++) {
                colOrigMat = Tabuleiro.colTabToMat(colOrig);
                if (this.getPosicao(linOrig+i, COLUNAS[colOrigMat+i]).getPeca() != null) {
                    return false;
                }
            }
            return true;
        }
        else if (linDest < linOrig && colDest > colOrig) {
            int colOrigMat = 0;
            for (int i = 1; i < colDest-colOrig; i++) {
                colOrigMat = Tabuleiro.colTabToMat(colOrig);
                if (this.getPosicao(linOrig-i, COLUNAS[colOrigMat+i]).getPeca() != null) {
                    return false;
                }
            }
            return true;
        }
        else if (linDest > linOrig && colDest < colOrig) {
            int colOrigMat = 0;
            for (int i = 1; i < colOrig-colDest; i++) {
                colOrigMat = Tabuleiro.colTabToMat(colOrig);
                if (this.getPosicao(linOrig+i, COLUNAS[colOrigMat-i]).getPeca() != null) {
                    return false;
                }
            }
            return true;
        }
        else if (linDest < linOrig && colDest < colOrig) {
            int colOrigMat = 0;
            for (int i = 1; i < colOrig-colDest; i++) {
                colOrigMat = Tabuleiro.colTabToMat(colOrig);
                if (this.getPosicao(linOrig-i, COLUNAS[colOrigMat-i]).getPeca() != null) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * checa se o movimento é válido
     * @return true se o movimento for válido, false se não
     */
    public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest) {
        Peca origem = this.getPosicao(linOrig, colOrig).getPeca();
        if (origem == null) {
            return false;
        }
        if ( this.checaLimite(linDest, colDest) && this.checaIgual(linOrig, colOrig, linDest, colDest) && origem.checaMovimento(linOrig, colOrig, linDest, colDest) ) {
            char desenho = Character.toLowerCase(origem.desenho());

            switch (desenho) {
                case 'b':
                    return ( this.checaColisaoDiagonal(linOrig, colOrig, linDest, colDest) && this.checaDestino(linDest, colDest, origem) );
                case 'c':
                    return this.checaDestino(linDest, colDest, origem);
                case 'd':
                    if (linDest == linOrig || colDest == colOrig) {
                        return ( this.checaColisaoOrtogonal(linOrig, colOrig, linDest, colDest) && this.checaDestino(linDest, colDest, origem) );
                    } 
                    else {
                        return ( this.checaColisaoDiagonal(linOrig, colOrig, linDest, colDest) && this.checaDestino(linDest, colDest, origem) );
                    }
                case 'p':
                    if (colOrig == colDest) {
                        if (this.checaColisaoOrtogonal(linOrig, colOrig, linDest, colDest)) {
                            Peca destino = this.getPosicao(linDest, colDest).getPeca();
                            if (destino == null) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                        else {
                            return false;
                        }
                    }
                    else if (this.getPosicao(linDest, colDest).getPeca() != null) {
                        return this.checaDestino(linDest, colDest, origem);
                    }
                    else {
                        return false;
                    }
                case 'r':
                    return (this.checaDestino(linDest, colDest, origem));
                case 't':
                    return ( this.checaColisaoOrtogonal(linOrig, colOrig, linDest, colDest) && this.checaDestino(linDest, colDest, origem) );
                default:
                    return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * move a peca da posicao inicial para a final
     * @return a Peca removida caso o destino estivesse ocupado e seja comido (retorna null caso o destino estivesse vazio)
     */
    public Peca moverPeca(int linOrig, char colOrig, int linDest, char colDest) {
        Posicao posOrig = this.getPosicao(linOrig, colOrig);
        Posicao posDest = this.getPosicao(linDest, colDest);
        Peca aux = posDest.getPeca();
        posDest.setPeca(posOrig.getPeca());
        posOrig.removePeca();
        return aux;
    }

    /**
     * checa se algum rei do tabuleiro está em xeque
     * @return o char referente a cor do rei que está em xeque: 'b' para branco e 'p' para preto. Caso nenhum esteja, retorna 'n'
     */
    public char checaXeque() {
        Posicao reiB = null;
        Posicao reiP = null;
        Posicao aux = null;
        for (int i = 8; i > 0; i--) {
            for (char j = 'a'; j < 'i'; j++) {
                aux = this.getPosicao(i, j);
                if (aux.getPeca() != null) {
                    if (aux.desenho() == 'r') {
                        reiP = aux;
                    }
                    else if (aux.desenho() == 'R') {
                        reiB = aux;
                    }
                }
            }
        }
        for (int i = 8; i > 0; i--) {
            for (char j = 'a'; j < 'i'; j++) {
                aux = this.getPosicao(i, j);
                if (aux.getPeca() != null) {
                    if (aux.getPeca().getCor() == 'p') {
                        if (checaMovimento(aux.getLinha(), aux.getColuna(), reiB.getLinha(), reiB.getColuna())) {
                            reiB.getPeca().setViva(true);
                            return 'b';
                        }
                    }
                    else {
                        if (checaMovimento(aux.getLinha(), aux.getColuna(), reiP.getLinha(), reiP.getColuna())) {
                            reiP.getPeca().setViva(true);
                            return 'p';
                        }    
                    }
                }
            }
        }
        return 'n';
    }

    /**
     * checa se o rei que está em xeque está em xeque-mate
     * @param cor char cor do rei que está em xeque atualmente
     * @return char cor referente a cor do rei que está em xeque-mate: 'b' se branco, 'p' se preto. Caso não esteja, retorna 'n'
     */
    public char checaXequeMate(char cor) {
        Posicao aux = null;
        Peca aux2 = null;
        for (int i = 8; i > 0; i--) {
            for (char j = 'a'; j < 'i'; j++) {
                aux = this.getPosicao(i, j);
                if (aux.getPeca() != null && aux.getPeca().getCor() == cor) {
                    for (int k = 8; k > 0; k--) {
                        for (char l = 'a'; l < 'i'; l++) {
                            if (this.getPosicao(k, l).getPeca() != null) {
                                aux2 = this.getPosicao(k, l).getPeca();
                            }
                            if (checaMovimento(aux.getLinha(), aux.getColuna(), k, l)) {
                                moverPeca(aux.getLinha(), aux.getColuna(), k, l);
                                if (this.checaXeque() == 'n') {
                                    moverPeca(k, l, aux.getLinha(), aux.getColuna());
                                    if (aux2 != null) {
                                        aux2.setViva(true);
                                        this.getPosicao(k, l).setPeca(aux2);
                                    }
                                    return 'n';
                                }
                                else {
                                    moverPeca(k, l, aux.getLinha(), aux.getColuna());
                                    if (aux2 != null) {
                                        aux2.setViva(true);
                                        this.getPosicao(k, l).setPeca(aux2);
                                    }
                                    aux2 = null;
                                }
                            }
                            else {
                                aux2 = null;
                            }
                        }
                    }
                }
            }
        }
        return cor;
    }

    /**
     * desenha o tabuleiro no terminal
     */
    public void printTabuleiro() {
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print(COLUNAS[i] + " ");
        }
        for (int i = 0; i < 8; i++) {
            System.out.println("");
            System.out.print(LINHAS[i] + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(this.getPosicao(LINHAS[i], COLUNAS[j]).desenho() + " ");
            }
        }
        System.out.println("");
    }
}
