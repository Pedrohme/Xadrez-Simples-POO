/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Cavalo extends Peca {
    public Cavalo(char cor) {
        super(cor);
        if (cor == 'b') {
            carac = 'C';
        } 
        else {
            carac = 'c';
        }
    }

    @Override
    public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest) {
        int colOrigMat = Tabuleiro.colTabToMat(colOrig);
        int linOrigMat = Tabuleiro.linTabToMat(linOrig);
        int colDestMat = Tabuleiro.colTabToMat(colDest);
        int linDestMat = Tabuleiro.linTabToMat(linDest);
        if (linDestMat == linOrigMat - 2) {
            if (colDestMat == colOrigMat - 1 || colDestMat == colOrigMat + 1) {
                return true;
            } 
            else {
                return false;
            }
        } 
        else if (linDestMat == linOrigMat - 1) {
            if (colDestMat == colOrigMat - 2 || colDestMat == colOrigMat + 2) {
                return true;
            } 
            else {
                return false;
            }
        } 
        else if (linDestMat == linOrigMat + 1) {
            if (colDestMat == colOrigMat - 2 || colDestMat == colOrigMat + 2) {
                return true;
            } 
            else {
                return false;
            }
        } 
        else if (linDestMat == linOrigMat + 2) {
            if (colDestMat == colOrigMat - 1 || colDestMat == colOrigMat + 1) {
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
}
