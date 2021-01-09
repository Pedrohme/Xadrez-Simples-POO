/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Torre extends Peca {
    public Torre(char cor) {
        super(cor);
        if (cor == 'b') {
            carac = 'T';
        }
        else {
            carac = 't';
        }
    }

    @Override
    public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest) {
        if (linDest == linOrig || colDest == colOrig) {
            return true;
        }
        else {
            return false;
        }
    }
}
