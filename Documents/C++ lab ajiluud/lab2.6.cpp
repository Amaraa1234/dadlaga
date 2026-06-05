#include <stdio.h>

int main() {
    int M;
    printf("natural too M oruul: ");
    scanf("%d", &M);

    for (int i = 1; i <= 10; i++) {
        printf("%d x %d = %d\n", M, i, M * i);
    }
    return 0;
}
