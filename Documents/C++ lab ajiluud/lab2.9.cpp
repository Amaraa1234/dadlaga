#include <stdio.h>
#include <math.h>

int main() {
    double a, b, c;
    printf("2 katet oruul: ");
    scanf("%lf %lf", &a, &b);

    c = sqrt(a*a + b*b);
    printf("giptonuz = %.2f\n", c);
    return 0;
}
