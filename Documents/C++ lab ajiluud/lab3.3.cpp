#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    srand(time(0));
    int secret = rand() % 10 + 5;
    int guess;

    printf("1-10 hoorond too taa: ");
    scanf("%d", &guess);

    if (guess == secret) {
        printf("Bayar hurge. Zuv taalaa.\n");
    } else if (guess < secret) {
        printf("Bagadaj baina.\n");
    } else {
        printf("Ih baina.\n");
    }
    printf("(nuust too ni %d baina )\n", secret);
    return 0;
}
