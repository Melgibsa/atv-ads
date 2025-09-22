import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;

public class Turtle {
    private int[] posicao;
    private boolean caneta;
    private int[] comandos;
    private int orientacao;

    public Turtle(String arquivo, int x0, int y0){
        this.caneta = false;
        this.orientacao = 0;
        this.posicao = new int[]{x0, y0};
        this.comandos = leitura(arquivo);
    }
    private int[] leitura(String arquivo){
        List<Integer> lista = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();
            if (linha != null) {
                String[] partes = linha.split(",");
                for (String parte : partes) {
                    try {
                        lista.add(Integer.parseInt(parte.trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Comando inválido");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            return new int[0];
        }
        return lista.stream().mapToInt(i -> i).toArray();
    }
    public void ativarCaneta(){
        caneta = true;
    }
    public void desativarCaneta(){
        caneta = false;
    }
    public void virarEsquerda(){
        orientacao = (orientacao + 1 - 4) % 4;
    }
    public void virarDireita(){
        orientacao = (orientacao + 1) % 4;
    }
    public boolean isCaneta() {
        return caneta;
    }
    public int getOrientacao() {
        return orientacao;
    }
    public int[] getPosicao() {
        return posicao;
    }
    public void setPosicao(int x, int y) {
        posicao[0] = x;
        posicao[1] = y;
    }
    public int[] getComandos() {
        return comandos;
    }
}

