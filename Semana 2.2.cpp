#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    string str = "123";
    int num = stoi(str);//conversão string para int
    float fnum = stof(str);//conversão string para float

    printf("Inteiro: %d\n", num);
    printf("Float: %.2f\n", fnum);
    
    return 0;
}