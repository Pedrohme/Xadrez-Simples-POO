/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Posicao {
    private char cor, coluna;
    private int linha;
    private char casa;
    private Peca peca;

    public Posicao(int linha, char coluna, char cor) throws IllegalArgumentException {
        if (cor != 'p' && cor != 'b') {
            throw new IllegalArgumentException("Cor inválida");
        }
        if (linha < 1 || linha > 8) {
            throw new IllegalArgumentException("Linha inválida");
        }
        if (coluna < 'a' || coluna > 'h') {
            throw new IllegalArgumentException("Coluna inválida");
        }
        this.linha = linha;
        this.coluna = coluna;
        this.cor = cor;
        if (this.cor == 'p') {
            this.casa = '■';
        }
        else if (this.cor == 'b'){
            this.casa = '□';
        }
        this.peca = null;
    }

    public int getLinha() {
        return linha;
    }

    public char getColuna() {
        return coluna;
    }

    public char desenho() {
        if (peca != null) {
            return peca.desenho();
        }
        else {
            return casa;
        }
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
    public void removePeca() {
        this.peca = null;
    }
    public Peca getPeca() {
        return this.peca;
    }
}
