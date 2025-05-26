#include &lt;iostream&gt;
#include &lt;cstdio&gt;

using namespace std;

int main () {
int N;
cin >> N;

int num[N];

for (int i = 0; i < N; i++){
cin >> num[i];
}

int soma = 0;
int cont = 0;
for (int i = 0; i < N; i++){
if(num[i] % 2 == 0){
soma += num[i];
cont++;
}
}

float média = 0;
if(cont > 0){

média = (float)soma / cont;
printf("%.1f", média);
}

return 0;
}