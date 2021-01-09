/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Bispo extends Peca {
    public Bispo(char cor) {
        super(cor);
        if (cor == 'b') {
            carac = 'B';
        }
        else {
            carac = 'b';
        }
    }

    @Override
    public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest) {
        if (Math.abs(linOrig - colOrig) == Math.abs(linDest - colDest) || linOrig + colOrig == linDest + colDest)  {
            return true;
        }
        else {
            return false;
        }
    }
}
