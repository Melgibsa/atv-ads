import java.util.ArrayList;
import java.util.Scanner;

public class Treinador {
    Scanner entrada = new Scanner(System.in);
    String nome;
    int idade;
    String[] medalhas = {"Pedra", "Cascata", "Trovão", "Arco-Íris", "Pântano", "Alma",
                "Vulcão", "Terra"};
    ArrayList<Pokemon> equipe = new ArrayList<Pokemon>();
    int medCount = 0;

    public Treinador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.equipe = new ArrayList<Pokemon>();
    }

    void conquistarProximaMedalha() {
        if (medCount >= 8) {
            System.out.println("Todas as medalhas já foram conquistadas");
            return;
        }

        String medalhaConquistada = medalhas[medCount];
        System.out.println("Você conquistou a Medalha " + medalhaConquistada + "!");
        medCount++;

        if (medCount == 8) {
            System.out.println("Parabéns! Você conquistou todas as medalhas!");
        } else {
            System.out.println("Próxima medalha: " + medalhas[medCount]);
        }
    }
    void addPoke(Pokemon poke){
        if (equipe.size() > 6) {
            System.out.println("Equipe cheia! Máximo de 6 pokémons");
            return;
        }
        equipe.add(poke);
        System.out.println(poke.getEspecie() + " foi adicionado à equipe!");
    }
    void mostrarDados(){
        System.out.println("FICHA DO TREINADOR");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);

        System.out.println("\nMedalhas Conquistadas (" + medCount + "/8):");
        if (medCount == 0) {
            System.out.println("Nenhuma medalha conquistada");
        } else {
            for (int i = 0; i < medCount; i++) {
                System.out.println((i + 1) + ". " + medalhas[i]);
            }
        }

        System.out.println("\nEquipe(" + equipe.size() + "/6):");
        if (equipe.isEmpty()) {
            System.out.println("Nenhum Pokémon capturado");
        } else {
            for (int i = 0; i < equipe.size(); i++) {
                Pokemon p = equipe.get(i);
                System.out.println((i + 1) + ". " + p.getEspecie() +
                        " (" + p.getTipo() + ") - Nível " + p.getNivel());
            }
        }
    }

}
