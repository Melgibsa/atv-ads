#include <iostream>
#include <vector>

using namespace std;

int main() {
    int N;
    cin >> N;

    double vetor[N];

    double soma = 0;
    double media = 0;
    for (int i = 0; i < N; i++){
        cin >> vetor[i];
        soma += vetor[i];
        media = soma / N;
    }
    printf("%.2f\n", media);
    for (int i = 0; i < N; i++){
        if (vetor[i] < media){
            printf("%.1f \n", vetor[i]);
        }
    }

    return 0;
}