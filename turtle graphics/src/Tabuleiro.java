public class Tabuleiro {
    private boolean[][] grade;
    private Turtle turtle;

    public Tabuleiro(String arquivo, int x0, int y0) {
        this.grade = new boolean[20][20];
        this.turtle = new Turtle(arquivo,x0,y0);
        processarComandos();
    }

    public void processarComandos() {
        int[] comandos = turtle.getComandos();
        int i = 0;

        while (i < comandos.length) {
            int comando = comandos[i];

            switch (comando) {
                case 1:
                    turtle.ativarCaneta();
                    i++;
                    break;

                case 2:
                    turtle.desativarCaneta();
                    i++;
                    break;

                case 3:
                    turtle.virarEsquerda();
                    i++;
                    break;

                case 4:
                    turtle.virarDireita();
                    i++;
                    break;

                case 5:
                    if (i + 1 < comandos.length) {
                        int passos = comandos[i + 1];
                        andar(passos);
                        i += 2;
                    } else {
                        i++;
                    }
                    break;

                case 6:
                    mostrarTabuleiro();
                    i++;
                    break;

                default:
                    i++;
                    break;
            }
        }
    }

    private void andar(int passos) {
        int[] posicaoAtual = turtle.getPosicao();
        int x = posicaoAtual[0];
        int y = posicaoAtual[1];
        int orientacao = turtle.getOrientacao();

        for (int i = 0; i < passos; i++) {
            if (turtle.isCaneta()) {
                grade[y][x] = true;
            }

            switch (orientacao) {
                case 0: // norte
                    y--;
                    break;
                case 1: // leste
                    x++;
                    break;
                case 2: // sul
                    y++;
                    break;
                case 3: // oeste
                    x--;
                    break;
            }

            // Atualizar posição do robô
            turtle.setPosicao(x, y);

            // Marcar nova posição se a caneta estiver ativa
            if (turtle.isCaneta()) {
                grade[y][x] = true;
            }
        }
    }

    public void mostrarTabuleiro() {
        int[] posicaoRobo = turtle.getPosicao();
        int turtleX = posicaoRobo[0];
        int turtleY = posicaoRobo[1];

        System.out.println("Tabuleiro:");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == turtleY && j == turtleX) {
                    System.out.print("R ");
                } else if (grade[i][j]) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}