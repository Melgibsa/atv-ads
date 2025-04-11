#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    string login = "Lara";
    string pass = "456";

    printf("%s\n", (login == "Lara" && pass == "456") ? "Acesso permitido" : "Acesso negado");//Uso de operador ternário

    return 0;
}