import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        System.out.println("=== CRIAÇÃO DE PERSONAGEM ===");

        ArrayList<Classes> classesDisponiveis = Classes.criarClasses();
        System.out.println("Escolha sua classe:");
        for (int i = 0; i < classesDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + classesDisponiveis.get(i).getNome());
        }

        System.out.print("Digite o número da classe: ");
        int escolhaClasse = sc.nextInt();
        sc.nextLine();

        if (escolhaClasse < 1 || escolhaClasse > classesDisponiveis.size()) {
            System.out.println("Classe inválida! Encerrando programa.");
            return;
        }

        Classes classeEscolhida = classesDisponiveis.get(escolhaClasse - 1);

        System.out.print("Nome do personagem: ");
        String nome = sc.nextLine();

        Player player = new Player(nome, 1000f,
                classeEscolhida.getAtaque(),
                classeEscolhida.getDefesa(),
                classeEscolhida.getMagia(),
                classeEscolhida.getVelocidade(),
                null, null, null);

        ArrayList<Loja> lojas = Loja.criarLojasPredefinidas();

        System.out.println("\nPersonagem criado com sucesso!");
        classeEscolhida.mostrarDados();

        boolean exe = true;
        while(exe){
            System.out.println("\nMENU");
            System.out.println("1. Exibir dados");
            System.out.println("2. Abrir mochila");
            System.out.println("3. Comprar item");
            System.out.println("4. Vender item");
            System.out.println("5. Equipar item");
            System.out.println("6. Desequipar item");
            System.out.println("7. Utilizar consumível");
            System.out.println("8. Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    player.exibirInfos();
                    break;

                case 2:
                    player.listarMochila();
                    break;

                case 3:
                    for (Loja loja : lojas) {
                        loja.exibirMenu();
                    }

                    System.out.print("Digite o nome exato do item que quer comprar: ");
                    String itemCompra = sc.nextLine();

                    boolean itemEncontrado = false;
                    for (Loja loja : lojas) {
                        Item item = loja.buscarItem(itemCompra);
                        if (item != null) {
                            player.comprar(item, loja);
                            itemEncontrado = true;
                            break;
                        }
                    }

                    if (!itemEncontrado) {
                        System.out.println("Item não encontrado em nenhuma loja!");
                    }
                    break;

                case 4:
                    player.listarMochila();
                    System.out.print("Digite o nome do item a ser vendido: ");
                    String itemVenda = sc.nextLine();
                    player.vender(itemVenda);
                    break;

                case 5:
                    player.listarMochila();
                    System.out.print("Digite o nome do item a ser equipado: ");
                    String itemEquip = sc.nextLine();

                    Item itemParaEquipar = player.buscarNaMochila(itemEquip);
                    if (itemParaEquipar != null) {
                        player.equipar(itemParaEquipar);
                    } else {
                        System.out.println("Item não encontrado na mochila!");
                    }
                    break;

                case 6:
                    System.out.println("\n=== DESEQUIPAR ITEM ===");

                    System.out.println("Itens atualmente equipados:");
                    if (player.getArma() != null) {
                        System.out.println("- Arma: " + player.getArma().getNome());
                    }
                    if (player.getEquipamento() != null) {
                        System.out.println("- Equipamento: " + player.getEquipamento().getNome());
                    }
                    if (player.getConsumivel() != null) {
                        System.out.println("- Consumível: " + player.getConsumivel().getNome());
                    }

                    System.out.println("\n1. Desequipar Arma");
                    System.out.println("2. Desequipar Equipamento");
                    System.out.println("3. Desequipar Consumível");
                    System.out.print("Escolha: ");

                    int opcaoDesequipar = sc.nextInt();
                    sc.nextLine();

                    switch(opcaoDesequipar) {
                        case 1:
                            if (player.desequiparArma()) {
                                System.out.println("Arma desequipada!");
                            } else {
                                System.out.println("Nenhuma arma equipada!");
                            }
                            break;
                        case 2:
                            if (player.desequiparEquipamento()) {
                                System.out.println("Equipamento desequipado!");
                            } else {
                                System.out.println("Nenhum equipamento equipado!");
                            }
                            break;
                        case 3:
                            if (player.desequiparConsumivel()) {
                                System.out.println("Consumível desequipado!");
                            } else {
                                System.out.println("Nenhum consumível equipado!");
                            }
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;


                case 7:
                    System.out.println("\n=== USAR CONSUMÍVEL ===");
                    player.listarMochila();
                    System.out.print("Digite o nome do consumível para usar: ");
                    String consumivelUsar = sc.nextLine();

                    Item itemUsar = player.buscarNaMochila(consumivelUsar);
                    if (itemUsar != null && itemUsar instanceof Consumível) {
                        Consumível consumivel = (Consumível) itemUsar;
                        int dosesRestantes = consumivel.getUsos();

                        if (dosesRestantes >= 0) {
                            System.out.println("Consumível usado! Doses restantes: " + dosesRestantes);

                            if (dosesRestantes == 0) {
                                player.getMochila().remove(consumivel);
                                System.out.println("Consumível removido da mochila (doses esgotadas)");
                            }
                        } else {
                            System.out.println("Este consumível já foi totalmente usado!");
                        }
                    } else {
                        System.out.println("Consumível não encontrado na mochila!");
                    }
                    break;

                case 8:
                    exe = false;
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha de 1 a 6.");
            }
        }
        sc.close();
    }
}