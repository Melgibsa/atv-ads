#include <cstdio>
#include <iostream>

using namespace std;

int main() {
    double valorBase = 200.00;
    double desconto = 0.20;
    double taxa = 0.08;

    double fatura = valorBase * (1 - desconto) * (1 + taxa);

    printf("R$%.2f\n", fatura);S

    return 0;
}