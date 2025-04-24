#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    int soma = 0;

    for(int n = 0; n <= 100; n++) {
        if (n % 2 == 0) {
            soma += n;
        }
    }

    printf("%d\n", soma);

    return 0;
}