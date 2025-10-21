public class Arma extends Equipamento{
    String tipo;
    Arma(String nome, float preco, String marca,
         int ataque, int defesa, int magia, int velocidade, String tipo){
        super(nome, preco, marca, ataque, defesa, magia, velocidade);
        this.tipo = tipo;
    }

    @Override
    public void exibirDados() {
        super.exibir_dados();
        System.out.println("Tipo de arma: " + tipo);
    }
    @Override
    public String toString() {
        return getNome();
    }
}