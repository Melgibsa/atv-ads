#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    string login = "Lara";
    string pass = "456";

    bool acessoPermitido = login == "Lara" && pass == "456";

    printf("%d\n", acessoPermitido);

    return 0;
}