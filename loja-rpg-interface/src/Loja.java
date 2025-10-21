import java.util.ArrayList;
public class Loja {
    private ArrayList<Item> produtos;
    private String nome;

    Loja(String nome){
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

    //adição de item à loja
    public void adicionarProduto(Item novo){
        this.produtos.add(novo);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Item> getProdutos(){
        return produtos;
    }

    //catálogo
    public void exibirMenu(){
        System.out.println("Loja: " + nome);
        for(Item I:this.produtos){
            I.exibir_dados();
            System.out.println("\n------------------------");
        }
        System.out.println("\n\n");
    }

    //compra
    public Item comprar(String nome){
        for(Item I:produtos){
            if(I.getNome().equals(nome)){
                produtos.remove(I);
                return I;
            }
        }
        return null;
    }
    public Item buscarItem(String nome) {
        for (Item I : produtos) {
            if (I.getNome().equals(nome)) {
                return I;
            }
        }
        return null;
    }

    public static ArrayList<Loja> criarLojasPredefinidas() {
        ArrayList<Loja> lojas = new ArrayList<>();

        Loja I = new Loja("Sombra da Lua");
        I.adicionarProduto(new Consumível(5,"Espelho da Raposa", 30.50f, "Tsukikage Originals", 1));
        I.adicionarProduto(new Consumível(6, "Flor de Izanami", 60.50f,"Hana-To Works", 3));
        I.adicionarProduto(new Arma("Espada Curta Cerimonial", 100f, "Amatsukami Forge", 25, 5, 3, 5, "destreza"));
        I.adicionarProduto(new Equipamento("Armadura do Tigre Branco", 200f, "Hokuto Workshop", 7, 50, 10, -2));
        lojas.add(I);

        Loja II = new Loja("A Catedral do Ferro");
        II.adicionarProduto(new Consumível(2,"Cálice de Ossos", 50f, "Cathedral Relics", 5));
        II.adicionarProduto(new Consumível(3,"Rosário Quebrado", 60f, "Sanctum Obscura", 2));
        II.adicionarProduto(new Arma("Espada do Lamento", 250f, "D'Arques Forge", 40, 20, 5, -2, "sagrado"));
        II.adicionarProduto(new Equipamento("Escudo do Sepulcro", 110f, "Ebony Forge", 5, 30, 10, -2));
        lojas.add(II);

        Loja III = new Loja("Elixirium Arcana");
        III.adicionarProduto(new Consumível(2, "Coágulo de Mercúrio Vivo", 45f, "Valerius Creations", 2));
        III.adicionarProduto(new Consumível(15,"Pó de Enxofre", 25f, "Aurum & Ignis", 8));
        III.adicionarProduto(new Arma("Lança Ouroboros", 150f, "Mercurialis Lab", 25, 0, 8, 15, "alquímico"));
        III.adicionarProduto(new Equipamento("Elmo Transmutatio Mentis", 70f, "Tria Prima", 6, 15, 9, 0));
        lojas.add(III);

        return lojas;
    }
}