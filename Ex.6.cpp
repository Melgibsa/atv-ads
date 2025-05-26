#include<iostream>
#include<cstdio>

using namespace std;

int main() {
    int x;
    cin >> x;

    int pessoa[x];
    string nome;
    int idade;
    int maiorIdade = 0;

    for (int i = 0; i < x; i++){
        cin >> nome >> idade;
        if (pessoa[i] > maiorIdade){
            maiorIdade = pessoa[i];
            printf("Pessoa mais velha: %s\n", nome.c_str());
        }
    }

    return 0;
}