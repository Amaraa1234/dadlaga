#include <stdio.h>

int main() {
    int N;
    long long urjver = 1;
    printf("N oruul: ");
    scanf("%d", &N);

    for (int i = 1; i <= N; i += 2) {
        urjver *= i;
    }
    printf("sondgoi toonuudiin urjver = %lld\n", urjver);
    return 0;
}
