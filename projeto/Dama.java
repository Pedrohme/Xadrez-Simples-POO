/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Dama extends Peca {
    public Dama(char cor) {
        super(cor);
        if (cor == 'b') {
            carac = 'D';
        } 
        else {
            carac = 'd';
        }
    }

    @Override
    public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest) {
        if (linDest == linOrig || colDest == colOrig) {
            return true;
        } 
        else if (Math.abs(linOrig - colOrig) == Math.abs(linDest - colDest) || linOrig + colOrig == linDest + colDest)  {
            return true;
        }
        else {
            return false;
        }
    }
}
