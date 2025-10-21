public class Equipamento extends Item {
    private int ataque;
    private int defesa;
    private int magia;
    private int velocidade;

    Equipamento(String nome, float preco, String marca,
                int ataque, int defesa, int magia, int velocidade){
        super(nome, preco, marca, ataque, defesa, magia, velocidade);
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
    }

    public void exibirDados(){
        super.exibir_dados();
        System.out.println("Modificadores: atq+"+ataque+", def+"+defesa+
                ", mag+"+magia+", vel+"+velocidade);
    }

    public int getAtaque() {
        return this.ataque;
    }
    public int getDefesa() {
        return this.defesa;
    }
    public int getMagia() {
        return this.magia;
    }
    public int getVelocidade() {
        return this.velocidade;
    }

    @Override
    public String toString() {
        return getNome();
    }
}