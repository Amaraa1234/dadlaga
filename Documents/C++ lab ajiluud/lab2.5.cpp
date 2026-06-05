#include <stdio.h>
#include <math.h>

int main() {
    int m, n;
    printf("m, n toonuudiig oruul: ");
    scanf("%d %d", &m, &n);

    printf("%d ^ %d = %.0f\n", m, n, pow(m, n));
    return 0;
}
