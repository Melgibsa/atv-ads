import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();  //carrega o arquivo

        SwingUtilities.invokeLater(() -> {
            new MainLay(lista);
        });
    }
}