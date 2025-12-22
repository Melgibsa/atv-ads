import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainLay extends JFrame {
    private Lista lista;
    private JTextArea areaTexto;
    private JTextField campoTitulo, campoAno, campoDiretor;

    public MainLay(Lista lista) {
        this.lista = lista;
        configurarJanela();
        criarComponentes();
        limparCampos();
        atualizarLista();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastro e Listagem de Filmes");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void criarComponentes() {
        JPanel painelEntrada = new JPanel(new GridLayout(4, 2, 5, 5));
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelEntrada.add(new JLabel("Título:"));
        campoTitulo = new JTextField();
        painelEntrada.add(campoTitulo);

        painelEntrada.add(new JLabel("Ano:"));
        campoAno = new JTextField();
        painelEntrada.add(campoAno);

        painelEntrada.add(new JLabel("Diretor:"));
        campoDiretor = new JTextField();
        painelEntrada.add(campoDiretor);

        JButton btnAdicionar = new JButton("Adicionar Filme");
        btnAdicionar.addActionListener(e -> adicionarFilme());
        painelEntrada.add(btnAdicionar);

        JButton btnRemover = new JButton("Remover Filme");
        btnRemover.addActionListener(e -> deletarFilme());
        painelEntrada.add(btnRemover);

        add(painelEntrada, BorderLayout.NORTH);

        //rolagem
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void adicionarFilme() {
        try {//captura de erros que podem ocorrer durante a execução
            String titulo = campoTitulo.getText();
            int ano = Integer.parseInt(campoAno.getText());
            String diretor = campoDiretor.getText();

            if (titulo.isEmpty() || diretor.isEmpty()) {//garantia de que todos os campos sejam preenchidos
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            Filme novoFilme = new Filme(titulo, ano, diretor);//criação do objeto
            lista.cadastrarFilme(novoFilme);//salva automaticamente no arquivo

            limparCampos();
            atualizarLista();
            JOptionPane.showMessageDialog(this, "Filme adicionado e salvo no arquivo!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ano deve ser um número válido!");
        }
    }

    private void deletarFilme() {
        if (lista.estaVazia()) {
            JOptionPane.showMessageDialog(this,
                    "A lista está vazia! Não há filmes para remover.",
                    "Lista Vazia",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //criar array de nomes dos filmes para a caixa de diálogo
        String[] nomesFilmes = new String[lista.getFilmes().size()];
        for (int i = 0; i < lista.getFilmes().size(); i++) {
            Filme f = lista.getFilmes().get(i);
            nomesFilmes[i] = f.getTitulo() + " (" + f.getAno() + ")";
        }

        //mostrar caixa de diálogo para selecionar filme
        String filmeSelecionado = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o filme a ser removido:",
                "Remover Filme",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nomesFilmes,
                nomesFilmes[0]
        );

        //se usuário cancelou ou fechou a janela
        if (filmeSelecionado == null) {
            return;
        }
        //encontrar o filme correspondente ao nome selecionado
        Filme filmeParaRemover = null;//inicialização
        for (Filme f : lista.getFilmes()) {//a cada iteração recebe um filme diferente
            String nomeCompleto = f.getTitulo() + " (" + f.getAno() + ")";
            if (nomeCompleto.equals(filmeSelecionado)) {
                filmeParaRemover = f;
                break;
            }
        }
        //confirmar remoção
        if (filmeParaRemover != null) {
            int confirmacao = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja remover:\n" +
                            "\"" + filmeParaRemover.getTitulo() + "\"?",
                    "Confirmar Remoção",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (confirmacao == JOptionPane.YES_OPTION) {
                lista.removerFilme(filmeParaRemover);
                atualizarLista();
                JOptionPane.showMessageDialog(this,
                        "Filme removido com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //método para inserir visualmente os filmes na lista
    private void atualizarLista() {
        areaTexto.setText("=== LISTA DE FILMES ===\n\n");

        if (lista.estaVazia()) {
            areaTexto.append("Nenhum filme cadastrado.");
        } else {
            for (Filme filme : lista.getFilmes()) {
                areaTexto.append("• " + filme.getTitulo() +
                        " (" + filme.getAno() + ")\n" +
                        "  Diretor: " + filme.getDirecao() + "\n\n");
            }
        }
        areaTexto.append("\nTotal: " + lista.getFilmes().size() + " filmes");
    }

    private void limparCampos() {
        campoTitulo.setText("");
        campoAno.setText("");
        campoDiretor.setText("");
        campoTitulo.requestFocus();
    }
}