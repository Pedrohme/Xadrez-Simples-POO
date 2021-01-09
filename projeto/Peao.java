/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Peao extends Peca {
    public Peao(char cor) {
        super(cor);
        if (cor == 'b') {
            carac = 'P';
        } 
        else {
            carac = 'p';
        }
    }

    @Override
    public boolean checaMovimento(int linOrig, char colOrig, int linDest, char colDest) {
        int colOrigMat = Tabuleiro.colTabToMat(colOrig);
        int linOrigMat = Tabuleiro.linTabToMat(linOrig);
        int colDestMat = Tabuleiro.colTabToMat(colDest);
        int linDestMat = Tabuleiro.linTabToMat(linDest);
        if (colDestMat == colOrigMat) {
            if (cor == 'b') {
                if (linOrigMat == 6) {
                    if (linDestMat == linOrigMat - 1 || linDestMat == linOrigMat - 2) {
                        return true;
                    } 
                    else {
                        return false;
                    }
                } 
                else {
                    if (linDestMat == linOrigMat - 1) {
                        return true;
                    } 
                    else {
                        return false;
                    }
                }
            } 
            else {
                if (linOrigMat == 1) {
                    if (linDestMat == linOrigMat + 1 || linDestMat == linOrigMat + 2) {
                        return true;
                    } 
                    else {
                        return false;
                    }
                } 
                else {
                    if (linDestMat == linOrigMat + 1) {
                        return true;
                    } 
                    else {
                        return false;
                    }
                }
            }
        }
        else if (colDestMat == colOrigMat+1 || colDestMat == colOrigMat-1) {
            if (cor == 'b') {
                if (linDestMat == linOrigMat - 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                if (linDestMat == linOrigMat + 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }
}