import java.io.FileWriter;
import java.io.IOException;

public class Teste {
    public static void main(String[] args) {
        arquivoComandos("comandos.txt");

        Tabuleiro tabuleiro = new Tabuleiro("comandos.txt", 10, 10);
    }

    private static void arquivoComandos(String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write("2, 5, 8, 3, 5, 8, 3, 5, 8, 3, 5, 3, 1, 5, 2, 2, 5, 3, 1, 6, 9");
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }
}