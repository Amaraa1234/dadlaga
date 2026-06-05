#include <stdio.h>

int main() {
    int too = 1, count = 0;
    while (count < 15) {
        printf("(%d, %d)\n", too, too + 2);
        too += 2;
        count++;
    }
    return 0;
}
