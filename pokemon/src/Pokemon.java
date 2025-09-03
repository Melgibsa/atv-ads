import java.util.Scanner;
public class Pokemon {
    Scanner entrada = new Scanner(System.in);

    String especie;
    String tipo;
    int nivel;
    int count = 0;

    Pokemon(String especie, String tipo, int nivel) {
        this.especie = especie;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getEspecie() {
        return especie;
    }
    public String getTipo() {
        return tipo;
    }
    public int getNivel() {
        return nivel;
    }
}