#include <iostream>
#include <cstdio>

using namespace std;

int main(){
int N;
cin >> N;
float num[N];
float maior = 0;
float posicao = 0;

for(int i = 0; i < N; i++){
cin >> num[i];
if(num[i] > maior){
maior = num[i];
posicao = i;
}
}
printf("%.1f\n", maior);
printf("%.f", posicao);

return 0;

}