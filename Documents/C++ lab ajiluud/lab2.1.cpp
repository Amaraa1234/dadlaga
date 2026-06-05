#include <stdio.h>
#include <stdlib.h>

int main() {
    double x, M;
    printf("x toog oruul: ");
    scanf("%lf", &x);

    M = x / 100.0;
    printf("M = %.2f\n", M);
    return 0;
}
