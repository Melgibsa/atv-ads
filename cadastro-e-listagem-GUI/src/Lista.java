import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lista {
    private List<Filme> filmes;
    private static final String arquivo = "Movie.txt";

    public Lista() {
        this.filmes = new ArrayList<>();
        carregarArquivo();
    }

    public void cadastrarFilme(Filme filme) {
        filmes.add(filme);
        salvarArquivo();
    }

    public boolean estaVazia(){
        return filmes.isEmpty();
    }

    public List<Filme> getFilmes() {
        return filmes;
    }
    public void removerFilme(Filme filme) {
        filmes.remove(filme);
        salvarArquivo();
    }

    //métodos de persistência para ler e salvar dados do arquivo txt
    public void carregarArquivo() {
        File archive = new File(arquivo);
        if(!archive.exists()) return;

        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;

            br.readLine();
            br.readLine();

            while((linha = br.readLine()) != null){
                if(!linha.trim().isEmpty()){
                    Filme filme = Filme.fromFileFormat(linha.trim());
                    if(filme != null) {
                        filmes.add(filme);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }

    public void salvarArquivo() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))){

            bw.write("LISTA DE FILMES");
            bw.newLine();
            bw.newLine();

            for(Filme filme : filmes) {
                bw.write(filme.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}