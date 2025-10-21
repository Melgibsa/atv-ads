import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Classes> classesDisponiveis = Classes.criarClasses();

            //escolha classe
            String[] nomesClasses = new String[classesDisponiveis.size()];
            for (int i = 0; i < classesDisponiveis.size(); i++) {
                nomesClasses[i] = classesDisponiveis.get(i).getNome();
            }

            String classeEscolhidaNome = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha sua classe:",
                    "Criação de Personagem",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nomesClasses,
                    nomesClasses[0]
            );

            if (classeEscolhidaNome != null) {
                Classes classeEscolhida = null;
                for (Classes c : classesDisponiveis) {
                    if (c.getNome().equals(classeEscolhidaNome)) {
                        classeEscolhida = c;
                        break;
                    }
                }

                String nomePersonagem = JOptionPane.showInputDialog("Digite o nome do personagem:");

                if (nomePersonagem != null && !nomePersonagem.trim().isEmpty()) {
                    Player player = new Player(nomePersonagem, 1000f,
                            classeEscolhida.getAtaque(),
                            classeEscolhida.getDefesa(),
                            classeEscolhida.getMagia(),
                            classeEscolhida.getVelocidade(),
                            null, null, null);

                    ArrayList<Loja> lojas = Loja.criarLojasPredefinidas();

                    //inicia a interface
                    new MainLay(player, lojas);
                }
            }
        });
    }
}