public class Item {
    private String nome;
    private float preco;
    private String marca;
    private int qtd;
    private int ataque;
    private int defesa;
    private int magia;
    private int velocidade;

    public Item(String nome, float preco, String marca,
                int ataque, int defesa, int magia, int velocidade){
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
        this.qtd = 1;
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
    }

    public Item(String nome, float preco, String marca, int qtd){
        this(nome,preco,marca, 0,0,0,0);
        this.qtd = qtd;
    }

    public float getPreco() {
        return preco;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void exibir_dados(){
        System.out.println("Produto: "+nome+
                "\nPreço: "+preco+
                "\nMarca: "+marca+
                "\nEm estoque: "+qtd);
    }

    public String getNome() {
        return nome;
    }

    public int getQtd(){
        return qtd;
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
}