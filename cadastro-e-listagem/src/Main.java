import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File arquivoEntrada = new File("Movie.txt");
        Scanner leitorConsole = new Scanner(System.in);

        Lista L = new Lista();

        if (arquivoEntrada.exists()) {
            Scanner leitor = new Scanner(arquivoEntrada);
            while (leitor.hasNextLine()) {
                String texto = leitor.nextLine();
                String str[] = texto.split(", ");
                int x = Integer.parseInt(str[0]);
                L.cadastrarFilme(new Filme(str[1], x));
            }
            leitor.close();
            System.out.println("Filmes do arquivo carregados!");
        } else {
            System.out.println("Arquivo não encontrado. Iniciando lista...");
        }

        System.out.println("\nINSERIR FILMES");
        System.out.println("Digite os filmes (ou 'sair' para terminar):");

        while (true) {
            System.out.print("\nTítulo do filme: ");
            String titulo = leitorConsole.nextLine();

            if (titulo.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Ano do filme: ");
            int ano = leitorConsole.nextInt();
            leitorConsole.nextLine();

            L.cadastrarFilme(new Filme(titulo, ano));
        }

        FileWriter escritor = new FileWriter("Movie.txt");
        escritor.write("LISTA DE FILMES\n\n");

        if (L.estaVazia()) {
            escritor.write("Lista vazia!\n");
        } else {
            for (Filme filme : L.getFilmes()) {
                escritor.write("• " + filme.titulo + " (" + filme.ano + ")\n");
            }
        }

        escritor.close();

        leitorConsole.close();
    }
}