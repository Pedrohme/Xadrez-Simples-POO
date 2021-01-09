/** 
 * @author Pedro Henrique Mendes
 * RA: 771056
 */ 

package projeto;

public class Jogador {
    private String nome;
    private Peca[] pecas;
    private char cor;

    /**
     * @throws IllegalArgumentException caso o char cor passado for diferente de 'b' (branco) e 'p' (preto)
     */
    public Jogador(char cor, String nome) throws IllegalArgumentException {
        this.nome = nome;
        this.cor = cor;
        pecas = new Peca[16];
        if (cor == 'b' || cor == 'p') {
            for (int i = 0; i < 8; i++) {
                pecas[i] = new Peao(cor);
            }
            pecas[8] = new Torre(cor);
            pecas[9] = new Cavalo(cor);
            pecas[10] = new Bispo(cor);
            pecas[11] = new Dama(cor);
            pecas[12] = new Rei(cor);
            pecas[13] = new Bispo(cor);
            pecas[14] = new Cavalo(cor);
            pecas[15] = new Torre(cor);
        }
        else {
            throw new IllegalArgumentException("Cor inválida");
        }
    }
    
    public Peca getPeca(int pos) {
        return pecas[pos];
    }
    public String getNome() {
        return nome;
    }
    public char getCor() {
        return cor;
    }
}
