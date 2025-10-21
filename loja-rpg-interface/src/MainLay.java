import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainLay extends JFrame {
    private Player player;
    private ArrayList<Loja> lojas;
    private JTextArea areaTexto;

    public MainLay(Player player, ArrayList<Loja> lojas) {
        this.player = player;
        this.lojas = lojas;

        setTitle("Sistema RPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        criarInterface();
        setVisible(true);
    }

    private void criarInterface() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//seleção por clique

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(8, 1, 5, 5));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelInfo = new JPanel(new BorderLayout());
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        //botões
        JButton btnDados = new JButton("Exibir Dados");
        JButton btnMochila = new JButton("Abrir Mochila");
        JButton btnComprar = new JButton("Comprar Item");
        JButton btnVender = new JButton("Vender Item");
        JButton btnEquipar = new JButton("Equipar Item");
        JButton btnDesequipar = new JButton("Desequipar Item");
        JButton btnSair = new JButton("Sair");

        //ações
        btnDados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { exibirDados(); }
        });
        btnMochila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { abrirMochila(); }
        });
        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { comprarItem(); }
        });
        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { venderItem(); }
        });
        btnEquipar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { equiparItem(); }
        });
        btnDesequipar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { desequiparItem(); }
        });
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });

        panelMenu.add(btnDados);
        panelMenu.add(btnMochila);
        panelMenu.add(btnComprar);
        panelMenu.add(btnVender);
        panelMenu.add(btnEquipar);
        panelMenu.add(btnDesequipar);
        panelMenu.add(btnSair);

        panelInfo.add(new JLabel("Informações:"), BorderLayout.NORTH);
        panelInfo.add(scrollPane, BorderLayout.CENTER);

        splitPane.setLeftComponent(panelMenu);
        splitPane.setRightComponent(panelInfo);
        splitPane.setDividerLocation(200);

        add(splitPane);
    }

    private void exibirDados() {
        StringBuilder dados = new StringBuilder();
        dados.append("=== DADOS DO PERSONAGEM ===\n\n");
        dados.append("Nome: ").append(player.getNome()).append("\n");
        dados.append("Dinheiro: ").append(player.getDinheiro()).append("\n");
        dados.append("Ataque: ").append(player.getAtaque()).append("\n");
        dados.append("Defesa: ").append(player.getDefesa()).append("\n");
        dados.append("Magia: ").append(player.getMagia()).append("\n");
        dados.append("Velocidade: ").append(player.getVelocidade()).append("\n");

        areaTexto.setText(dados.toString());
    }

    private void abrirMochila() {
        areaTexto.setText(player.listarMochila());
    }

    private void comprarItem() {
        String[] nomesLojas = new String[lojas.size()];
        for (int i = 0; i < lojas.size(); i++) {
            nomesLojas[i] = lojas.get(i).getNome();
        }

        String lojaEscolhida = (String) JOptionPane.showInputDialog(
                this,
                "Escolha uma loja:",
                "Comprar Item",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nomesLojas,
                nomesLojas[0]
        );

        if (lojaEscolhida != null) {
            Loja lojaSelecionada = null;
            for (Loja loja : lojas) {
                if (loja.getNome().equals(lojaEscolhida)) {
                    lojaSelecionada = loja;
                    break;
                }
            }

            if (lojaSelecionada != null) {
                StringBuilder itens = new StringBuilder();
                itens.append("=== ").append(lojaSelecionada.getNome().toUpperCase()).append(" ===\n\n");
                itens.append("Itens disponíveis:\n");

                if (lojaSelecionada.getProdutos() != null) {
                    for (Item item : lojaSelecionada.getProdutos()) {
                        itens.append("- ").append(item.getNome())
                                .append(" | Preço: ").append(item.getPreco())
                                .append(" ouro\n");
                    }
                }

                itens.append("\nDigite o nome exato do item desejado:");
                areaTexto.setText(itens.toString());

                //input para item
                String itemNome = JOptionPane.showInputDialog(this, "Digite o nome do item:");
                if (itemNome != null && !itemNome.trim().isEmpty()) {
                    Item item = lojaSelecionada.buscarItem(itemNome);
                    if (item != null) {
                        if (player.comprar(item, lojaSelecionada)) {
                            areaTexto.append("\n\nCompra realizada com sucesso!");
                        } else {
                            areaTexto.append("\n\nFalha na compra!");
                        }
                    } else {
                        areaTexto.append("\n\nItem não encontrado!");
                    }
                }
            }
        }
    }

    private void venderItem() {
        areaTexto.setText("=== VENDER ITEM ===\n\n" + player.listarMochila() + "\n\nDigite o nome do item para vender:");

        String itemNome = JOptionPane.showInputDialog(this, "Nome do item:");
        if (itemNome != null && !itemNome.trim().isEmpty()) {
            if (player.vender(itemNome)) {
                areaTexto.append("\n\nVenda realizada com sucesso!");
            } else {
                areaTexto.append("\n\nItem não encontrado na mochila!");
            }
        }
    }

    private void equiparItem() {
        areaTexto.setText("=== EQUIPAR ITEM ===\n\n" + player.listarMochila() + "\n\nDigite o nome do item para equipar:");

        String itemNome = JOptionPane.showInputDialog(this, "Nome do item:");
        if (itemNome != null && !itemNome.trim().isEmpty()) {
            Item item = player.buscarNaMochila(itemNome);
            if (item != null) {
                if (player.equipar(item)) {
                    areaTexto.append("\n\nItem equipado com sucesso!");
                } else {
                    areaTexto.append("\n\nErro ao equipar item!");
                }
            } else {
                areaTexto.append("\n\nItem não encontrado na mochila!");
            }
        }
    }

    private void desequiparItem() {
        StringBuilder equipados = new StringBuilder();
        equipados.append("=== DESEQUIPAR ITEM ===\n\n");
        equipados.append("Itens atualmente equipados:\n\n");

        boolean temEquipado = false;

        if (player.getArma() != null) {
            equipados.append("Arma: ").append(player.getArma().getNome()).append("\n");
            temEquipado = true;
        }
        if (player.getEquipamento() != null) {
            equipados.append("Equipamento: ").append(player.getEquipamento().getNome()).append("\n");
            temEquipado = true;
        }
        if (player.getConsumivel() != null) {
            equipados.append("Consumível: ").append(player.getConsumivel().getNome()).append("\n");
            temEquipado = true;
        }

        if (!temEquipado) {
            equipados.append("Nenhum item equipado!\n");
            areaTexto.setText(equipados.toString());
            return;
        }

        areaTexto.setText(equipados.toString());

        String[] opcoes = {"Arma", "Equipamento", "Consumível"};
        String tipo = (String) JOptionPane.showInputDialog(
                this,
                "O que deseja desequipar?",
                "Desequipar Item",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (tipo != null) {
            boolean x = false;
            String mensagem = "";

            switch(tipo) {
                case "Arma":
                    x = player.desequiparArma();
                    mensagem = x ? "Arma desequipada!" : "Nenhuma arma equipada!";
                    break;
                case "Equipamento":
                    x = player.desequiparEquipamento();
                    mensagem = x ? "Equipamento desequipado!" : "Nenhum equipamento equipado!";
                    break;
                case "Consumível":
                    x = player.desequiparConsumivel();
                    mensagem = x ? "Consumível desequipado!" : "Nenhum consumível equipado!";
                    break;
            }
            areaTexto.append("\n" + mensagem);
        }
    }

}