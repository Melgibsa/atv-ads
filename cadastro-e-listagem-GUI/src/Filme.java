public class Filme {
    String titulo;
    int ano;
    String direcao;

    public Filme(String titulo, int ano, String direcao) {
        this.titulo = titulo;
        this.ano = ano;
        this.direcao = direcao;
    }

    public String getTitulo() {
        return titulo;
    }
    public int getAno() {
        return ano;
    }
    public String getDirecao() {
        return direcao;
    }

    public String toFileFormat() {
        return titulo + ", " + ano + ", " + direcao;
    }
    public static Filme fromFileFormat(String linha) {
        String[] dados = linha.split(", ");
        if (dados.length == 3) {
            return new Filme(dados[0],
                    Integer.parseInt(dados[1]),
                    dados[2]);
        }
        return null;
    }
}