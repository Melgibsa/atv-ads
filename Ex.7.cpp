#include<iostream>
#include<cstdio>

using namespace std;

int main() {
    int N;
    cin >> N;

    int aluno[N];
    string nome;
    float nota1, nota2;

    printf("Alunos aprovados:\n");

    float media = 0;
    for (int i = 0; i < N; i++){
        cin >> nome >> nota1 >> nota2;
        media = (nota1 + nota2) / 2;
        if (media >= 6.0){
            printf("%s\n", nome.c_str());
        }
    }

    return 0;
}