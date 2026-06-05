#include <stdio.h>

int main() {
    long long N;
    printf("N natural too oruul: ");
    scanf("%ld", &N);

    while (N >= 10) {
        N = N / 10;   
    }

    printf(" ehnii stipr = %ld\n", N);
    return 0;
}
