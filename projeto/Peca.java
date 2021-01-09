/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public abstract class Peca {
    protected char carac, cor;
    protected boolean viva;

    public Peca(char cor) throws IllegalArgumentException {
        if (cor != 'b' && cor != 'p') {
            throw new IllegalArgumentException("char cor inválido");
        }
        this.cor = cor;
        this.carac = 'n';
        this.viva = true;
    }

    /**
     * @return true se o movimento da peca for valido, false se nao
     */
    abstract public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest);
    
    public char desenho() {
        return carac;
    }
    public char getCor() {
        return cor;
    }
    public boolean isViva() {
        return viva;
    }
    public void setViva(boolean viva) {
        this.viva = viva;
    }
}
