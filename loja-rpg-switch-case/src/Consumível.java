public class Consumível extends Item{
    private int doses;

    Consumível(int doses, String nome, float preco, String marca, int qtd){
        super(nome, preco, marca, qtd);
        this.doses = doses;
    }

    public void exibirInfos(){
        super.exibir_dados();
        System.out.println("Doses: " + this.doses);
    }

    public int getUsos() {
        if(doses > 0){
            doses -= 1;
            System.out.println("Dose aplicada! ainda te restam " + doses + "doses.");
            return doses;
        } else {
            System.out.println("Você já utilizou o limite de doses.");
            return -1;
        }
    }

    public int getAtaque() {
        return super.getAtaque();
    }

    public int getDefesa() {
        return super.getDefesa();
    }

    public int getMagia() {
        return super.getMagia();
    }

    public int getVelocidade() {
        return super.getVelocidade();
    }
    @Override
    public String toString() {
        return getNome();
    }
}