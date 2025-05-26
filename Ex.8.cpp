#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    int N;
    cin >> N;

    float altura[N];
    char sexo[N];

    for (int i = 0; i < N; i++) {
        cin >> altura[i] >> sexo[i];
    }

    float MaiorH = altura[0];
    float MenorH = altura[0];
    for (int i = 0; i < N; i++) {
        if(altura[i] < MenorH){
            MenorH = altura[i];
        }
        if(altura[i] > MaiorH){
            MaiorH = altura[i];
        }
    }
    printf("Menor altura: %.2f\n", MenorH);
    printf("Maior altura: %.2f\n", MaiorH);

    int qtdF = 0;
    float soma = 0;
    for (int i = 0; i < N; i++) {
        if(sexo[i] == 'F'){
            qtdF++;
            soma += altura[i];
        }
    }
    float media = 0;
    if(qtdF > 0) {
        media = soma / qtdF;
    }
    printf("Média das alturas das mulheres: %.2f\n", media);

    int qtdM = 0;
    for (int i = 0; i < N; i++) {
        if(sexo[i] == 'M'){
            qtdM++;
        }
    }
    printf("Número de homens: %d\n", qtdM);

    return 0;
}