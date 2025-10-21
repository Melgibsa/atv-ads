import java.util.ArrayList;

public class Player{
    private String nome;
    private float dinheiro;
    private int ataque;
    private int defesa;
    private int magia;
    private int velocidade;
    private ArrayList<Item> mochila;
    private Arma arma;
    private Equipamento equipamento;
    private Consumível consumivel;


    Player(String nome, float dinheiro, int ataque, int defesa, int magia, int velocidade,
           Arma arma, Equipamento equipamento, Consumível consumivel){
        this.nome = nome;
        this.dinheiro = 1000.0f;//quantidade inicial fixada pelo programa
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
        this.mochila = new ArrayList<>();
        this.arma = null;
        this.equipamento = null;
        this.consumivel = null;
    }

    //uso dos dados do player
    public String getNome() { return nome; }
    public float getDinheiro() { return dinheiro; }

    //alteração nos atributos pelos equipamentos
    public int getAtaque() {
        return ataque + (equipamento != null ? equipamento.getAtaque() : 0);
    }
    public int getDefesa() {
        return defesa + (equipamento != null ? equipamento.getDefesa() : 0);
    }
    public int getMagia() {
        return magia + (equipamento != null ? equipamento.getMagia() : 0);
    }
    public int getVelocidade() {
        return velocidade + (equipamento != null ? equipamento.getVelocidade() : 0);
    }

    //uso de itens
    public Arma getArma() {
        return arma;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public Consumível getConsumivel() {
        return consumivel;
    }

    public ArrayList<Item>  getMochila(){
        return mochila;
    }

    //exibir informações
    public void exibirInfos(){
        System.out.println("Nome: " + nome + "\n" + "Saldo: " + dinheiro + "\n" + "Ataque: " + ataque +
                "\n" + "Defesa: " + defesa + "\n" + "Magia: " + magia + "\n" + "Velocidade: " + velocidade);
        System.out.println("EQUIPAMENTOS");
        System.out.println("- Arma: " + (arma != null ? arma.toString() : "Nenhuma"));
        System.out.println("- Equipamento: " + (equipamento != null ? equipamento.toString() : "Nenhum"));
        System.out.println("- Consumível: " + (consumivel != null ? consumivel.toString() : "Nenhum"));
    }

    //compra e venda
    public boolean comprar(Item item, Loja loja) {
        if (dinheiro >= item.getPreco()) {
            Item itemComprado = loja.comprar(item.getNome());
            if (itemComprado != null) {
                dinheiro -= item.getPreco();
                mochila.add(itemComprado);
                System.out.println("Compra realizada com sucesso!");
                System.out.println("Saldo restante: R$ " + dinheiro);
                return true;
            }
        } else {
            System.out.println("Dinheiro insuficiente!");
        }
        return false;
    }

    public boolean vender(String nomeItem) {
        Item itemEncontrado = buscarNaMochila(nomeItem);
        if (itemEncontrado == null) {//verifica se o item está no inventário
            System.out.println("Item não encontrado na mochila!");
            return false;
        }
        if (mochila.remove(itemEncontrado)) {
            float precoVenda = itemEncontrado.getPreco();
            dinheiro += precoVenda;
            System.out.println("Venda realizada com sucesso! Recebeu R$ " + precoVenda);
            System.out.println("Saldo atual: R$ " + dinheiro);
            return true;
        }
        return false;
    }

    //equipar item
    public boolean equipar(Item item) {
        if (!mochila.contains(item)) {
            System.out.println("Item não encontrado na mochila!");
            return false;
        }

        if (item instanceof Arma) {
            return equiparArma((Arma) item);
        } else if (item instanceof Equipamento) {
            return equiparEquipamento((Equipamento) item);
        } else if (item instanceof Consumível){
            return equiparConsumivel((Consumível) item);
        }
        else {
            System.out.println("Este item não pode ser equipado!");
            return false;
        }
    }

    //equipar itens
    private boolean equiparArma(Arma novaArma) {
        Arma armaAntiga = this.arma;

        this.arma = novaArma;
        mochila.remove(novaArma);

        if (armaAntiga != null) {
            mochila.add(armaAntiga);
            this.ataque -= armaAntiga.getAtaque();
            this.defesa -= armaAntiga.getDefesa();
            this.magia -= armaAntiga.getMagia();
            this.velocidade -= armaAntiga.getVelocidade();
            System.out.println("Desequipou: " + armaAntiga.getNome());
        }
        this.ataque += novaArma.getAtaque();
        this.defesa += novaArma.getDefesa();
        this.magia += novaArma.getMagia();
        this.velocidade += novaArma.getVelocidade();

        System.out.println("Equipou: " + novaArma.getNome());
        return true;
    }

    private boolean equiparEquipamento(Equipamento novoEquip) {
        Equipamento equipAntigo = this.equipamento;

        this.equipamento = novoEquip;
        mochila.remove(novoEquip);

        if (equipAntigo != null) {
            mochila.add(equipAntigo);
            this.ataque -= equipAntigo.getAtaque();
            this.defesa -= equipAntigo.getDefesa();
            this.magia -= equipAntigo.getMagia();
            this.velocidade -= equipAntigo.getVelocidade();
            System.out.println("Desequipou: " + equipAntigo.getNome());
        }
        this.ataque += novoEquip.getAtaque();
        this.defesa += novoEquip.getDefesa();
        this.magia += novoEquip.getMagia();
        this.velocidade += novoEquip.getVelocidade();

        System.out.println("Equipou: " + novoEquip.getNome());
        return true;
    }

    private boolean equiparConsumivel(Consumível novoCons){
        Equipamento consAntigo = this.equipamento;

        this.consumivel = novoCons;
        mochila.remove(novoCons);

        if (consAntigo != null) {
            mochila.add(consAntigo);
            this.ataque -= consAntigo.getAtaque();
            this.defesa -= consAntigo.getDefesa();
            this.magia -= consAntigo.getMagia();
            this.velocidade -= consAntigo.getVelocidade();
            System.out.println("Desequipou: " + consAntigo.getNome());
        }
        this.ataque += novoCons.getAtaque();

        System.out.println("Equipou: " + novoCons.getNome());
        return true;
    }

    //desequipar itens
    public boolean desequiparArma() {
        if (arma != null) {
            Arma armaAntiga = this.arma;
            mochila.add(armaAntiga);
            this.ataque -= armaAntiga.getAtaque();
            this.defesa -= armaAntiga.getDefesa();
            this.magia -= armaAntiga.getMagia();
            this.velocidade -= armaAntiga.getVelocidade();
            this.arma = null;
            return true;
        }
        return false;
    }

    public boolean desequiparEquipamento() {
        if (equipamento != null) {
            Equipamento equipAntigo = this.equipamento;
            mochila.add(equipAntigo);
            this.ataque -= equipAntigo.getAtaque();
            this.defesa -= equipAntigo.getDefesa();
            this.magia -= equipAntigo.getMagia();
            this.velocidade -= equipAntigo.getVelocidade();
            this.equipamento = null;
            return true;
        }
        return false;
    }

    public boolean desequiparConsumivel() {
        if (consumivel != null && consumivel instanceof Consumível) {
            mochila.add(consumivel);
            System.out.println("Consumível desequipado: " + consumivel.getNome());
            this.consumivel = null;
            return true;
        }
        return false;
    }

    //verificação
    public Item buscarNaMochila(String nome) {
        for (Item item : mochila) {
            if (item.getNome().equals(nome)) {
                return item;
            }
        }
        return null;
    }

    //exibir itens na mochila
    public String listarMochila() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== MOCHILA ===\n\n");
        sb.append("Dinheiro: ").append(dinheiro).append("\n\n");
        sb.append("Itens:\n");

        if (mochila.isEmpty()) {
            sb.append("Mochila vazia!\n");
        } else {
            for (Item item : mochila) {
                sb.append("- ").append(item.getNome())
                        .append(" | Preço: ").append(item.getPreco())
                        .append(" | Ataque: ").append(item.getAtaque())
                        .append(" | Defesa: ").append(item.getDefesa())
                        .append(" | Magia: ").append(item.getMagia())
                        .append(" | Velocidade: ").append(item.getVelocidade())
                        .append("\n");
            }
        }
        return sb.toString();
    }

}