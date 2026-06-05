#include <stdio.h>

int main() {
    int N, sum = 0, digit;

    printf("N too oruul: ");
    scanf("%d", &N);

    while (N > 0) {
        digit = N % 10;     
        sum += digit;       
        N = N / 10;         
    }

    printf(" stipruudiin niilber = %d\n", sum);

    return 0;
}
