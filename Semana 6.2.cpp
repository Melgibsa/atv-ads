#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    int n = 7;
    int contador = 1;

    for(int i = 0; i < 10; i++) {
        int produto = n * contador;
        printf("%d x %d = %d\n", n, contador, produto);
        contador += 1;
    }

    return 0;
}