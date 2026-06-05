#include <stdio.h>
#include <math.h>

int main() {
    double a, b, c, p, S;
    printf("gurvaljnii 3 taliig oruul: ");
    scanf("%lf %lf %lf", &a, &b, &c);

    p = (a + b + c) / 2.0; // hagas perimetr
    S = sqrt(p * (p - a) * (p - b) * (p - c));

    printf("gurvaljnii talbai = %.2f\n", S);
    return 0;
}
