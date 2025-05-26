#include <iostream>
#include <vector>

using namespace std;

int main() {
    int x;

    cin >> x;

    int A[x];
    int B[x];
    int C[x];
    
    for (int i = 0; i < x; i++){
        cin >> A[i];
    }
    for (int i = 0; i < x; i++){
        cin >> B[i];
    }
    for (int i = 0; i < x; i++){
        C[i] = A[i] + B[i];
        printf("%d ", C[i]);
    }

    return 0;
}