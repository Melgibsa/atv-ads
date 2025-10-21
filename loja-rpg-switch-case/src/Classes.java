import java.util.ArrayList;

public class Classes {
    private String nome;
    private int ataque;
    private int defesa;
    private int magia;
    private int velocidade;

    Classes(String nome, int ataque, int defesa, int magia, int velocidade) {
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
    }

    public String getNome() {
        return nome;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getMagia() {
        return magia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void mostrarDados() {
        System.out.println("Classe: " + nome);
        System.out.println("------------------------");
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Magia: " + magia);
        System.out.println("Velocidade: " + velocidade);
    }

    public static ArrayList<Classes> criarClasses() {
        ArrayList<Classes> classes = new ArrayList<>();

        Classes samurai = new Classes("Samurai", 65, 45, 7, 15);
        classes.add(samurai);

        Classes escudeiro = new Classes("Escudeiro", 50, 70, 5, 10);
        classes.add(escudeiro);

        Classes alquimista = new Classes("Alquimista", 35, 10, 60, 20);
        classes.add(alquimista);

        Classes hierofante = new Classes("Hierofante", 30, 15, 55, 20);
        classes.add(hierofante);

        Classes cacador = new Classes("Caçador", 40, 20, 10, 70);
        classes.add(cacador);

        Classes corsario = new Classes("Corsário", 45, 25, 15, 55);
        classes.add(corsario);

        return classes;
    }
}
