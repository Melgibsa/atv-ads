#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    double salBruto = 8000.00;
    double INSS = 0.12 * salBruto;
    double IRRF = 0.15 * salBruto;
    double FGTS = 0.08 * salBruto;

    double salLiquido = salBruto - (INSS + IRRF + FGTS);

    printf("Salário Líquido: R$ %.2f\n", salLiquido);
    
    return 0;
}