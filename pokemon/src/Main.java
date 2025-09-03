import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("SISTEMA DE TREINADORES POKÉMON");

        // Cadastro do treinador
        System.out.print("\nNome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        Treinador treinador = new Treinador(nome, idade);
        System.out.println("Treinador " + nome + " cadastrado!" + "\n");

        boolean executando = true;
        while (executando) {
            System.out.println("\nMENU");
            System.out.println("1. Mostrar dados do treinador");
            System.out.println("2. Adicionar Pokémon");
            System.out.println("3. Remover Pokémon");
            System.out.println("4. Batalhar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    treinador.mostrarDados();
                    break;

                case 2:
                    System.out.println("\nADICIONAR POKÉMON");
                    if (treinador.equipe.size() >= 6) {
                        System.out.println("Equipe cheia! Máximo de 6 pokémons");
                        break;
                    }

                    System.out.print("Espécie: ");
                    String especie = scanner.nextLine();

                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();

                    System.out.print("Nível: ");
                    int nivel = scanner.nextInt();
                    scanner.nextLine();

                    if (nivel < 1 || nivel > 100) {
                        System.out.println("Nível inválido! Deve ser entre 1 e 100");
                    } else {
                        Pokemon novoPokemon = new Pokemon(especie, tipo, nivel);
                        treinador.addPoke(novoPokemon);
                    }
                    break;

                case 3:
                    System.out.println("\nREMOVER POKÉMON");
                    if (treinador.equipe.isEmpty()) {
                        System.out.println("Nenhum Pokémon na equipe");
                        break;
                    }

                    System.out.println("Pokémon na equipe:");
                    for (int i = 0; i < treinador.equipe.size(); i++) {
                        Pokemon p = treinador.equipe.get(i);
                        System.out.println((i + 1) + ". " + p.getEspecie() + " (" + p.getTipo() + ")");
                    }

                    System.out.print("Digite o número do Pokémon a ser removido: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine();

                    if (numero < 1 || numero > treinador.equipe.size()) {
                        System.out.println("Número inválido!");
                    } else {
                        Pokemon removido = treinador.equipe.remove(numero - 1);
                        System.out.println(removido.getEspecie() + " foi removido da equipe!");
                    }
                    break;

                case 4:
                    System.out.println("\nDUELO!");
                    treinador.conquistarProximaMedalha();
                    break;

                case 5:
                    executando = false;
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha de 1 a 5.");
            }
        }

        scanner.close();
    }
}