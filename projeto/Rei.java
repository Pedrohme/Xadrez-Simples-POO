/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Rei extends Peca {
    public Rei(char cor) {
        super(cor);
        if (cor == 'b') {
            carac = 'R';
        }
        else {
            carac = 'r';
        }
    }

    @Override
    public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest) {
        int colOrigMat = Tabuleiro.colTabToMat(colOrig);
        int linOrigMat = Tabuleiro.linTabToMat(linOrig);
        int colDestMat = Tabuleiro.colTabToMat(colDest);
        int linDestMat = Tabuleiro.linTabToMat(linDest);
        if (linDestMat == linOrigMat - 1) {
            for (int i = -1; i < 2; i++) {
                if (colDestMat == colOrigMat + i) {
                    return true;
                }
            }
            return false;
        }
        else if (linDestMat == linOrigMat + 1) {
            for (int i = -1; i < 2; i++) {
                if (colDestMat == colOrigMat + i) {
                    return true;
                }
            }
            return false;
        }
        else if (linDestMat == linOrigMat) {
            if (colDestMat == colOrigMat - 1 || colDestMat == colOrigMat + 1) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
