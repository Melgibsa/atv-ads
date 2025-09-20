import java.util.ArrayList;
import java.util.List;

public class Lista {
    private List<Filme> filmes;

    public Lista() {
        this.filmes = new ArrayList<>();
    }

    public void cadastrarFilme(Filme filme) {
        filmes.add(filme);
    }

    public boolean estaVazia() {
        return filmes.isEmpty();
    }

    public void listarFilmes() {
        for (Filme filme : filmes) {
            System.out.println("- " + filme.titulo + " (" + filme.ano + ")");
        }
    }
    public List<Filme> getFilmes() {
        return filmes;
    }
}