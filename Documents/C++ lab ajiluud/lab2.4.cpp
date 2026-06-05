#include <stdio.h>
#include <math.h>

int main() {
    double a, b, c, D, x1, x2;
    printf("a, b, c koep oruul: ");
    scanf("%lf %lf %lf", &a, &b, &c);

    if (a == 0) {
        printf("kvadrat bish tegshitgel!\n");
        return 0;
    }

    D = b*b - 4*a*c;

    if (D < 0) {
        printf("bodit shiidgui.\n");
    } else if (D == 0) {
        x1 = -b / (2*a);
        printf("davhar shiid: x = %.2f\n", x1);
    } else {
        x1 = (-b + sqrt(D)) / (2*a);
        x2 = (-b - sqrt(D)) / (2*a);
        printf("shiiduud: x1 = %.2f, x2 = %.2f\n", x1, x2);
    }
    return 0;
}
