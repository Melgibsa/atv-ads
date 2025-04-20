 #include <iostream>
 #include <cstdio>

 using namespace std;

 int main() {
    int notas[2] = {4, 6};
    int faltas = 5;
    double media = (notas[0] * 2 + notas[1] * 3) / 5.0;

    if (media >= 7.0 && faltas <= 10) {
        printf("Aprovado\n");
    } else {
        printf("Reprovado\n");
    }

    return 0;
 }