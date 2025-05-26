#include &lt;iostream&gt;
#include &lt;cstdio&gt;

using namespace std;

int main(){
int N;
cin >> N;
int num[N];
int qtd = 0;

for(int i = 0; i < N; i++){
cin >> num[i];
if(num[i] % 2 == 0){
printf("%d", num[i]);
qtd++;
}
}
printf("%d", qtd);

return 0;
}