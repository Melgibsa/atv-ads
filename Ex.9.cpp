#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    int N;
    cin >> N;

    double mercadoria[N];
    string nome[N];
    double custo[N];
    double venda[N];

    for(int i = 0; i < N; i++){
        cin >> nome[i] >> custo[i] >> venda[i];
        mercadoria[i] = (venda[i] - custo[i]) / custo[i] * 100;
    }

    int qtdMenor = 0;
    int qtdMedio = 0;
    int qtdMaior = 0;
    for(int i = 0; i < N; i++){
        if(mercadoria[i] < 10) {
            qtdMenor++;
        } else if(mercadoria[i] >= 10 && mercadoria[i] <= 20) {
            qtdMedio++;
        } else {
            qtdMaior++;
        }
    }
    printf("Lucro abaixo de 10%%: %d\n", qtdMenor);
    printf("Lucro entre 10%% e 20%%: %d\n", qtdMedio);
    printf("Lucro acima de 20%%: %d\n", qtdMaior);

    double somaCusto = 0;
    for(int i = 0; i < N; i++){
        somaCusto += custo[i];
    }
    double somaVenda = 0;
    for(int i = 0; i < N; i++){
        somaVenda += venda[i];
    }
    printf("Valor total da compra: %.2f\n", somaCusto);
    printf("Valor total da venda: %.2f\n", somaVenda);

    double Lucro = somaVenda - somaCusto;
    printf("Lucro total: %.2f\n", Lucro);

    return 0;
}