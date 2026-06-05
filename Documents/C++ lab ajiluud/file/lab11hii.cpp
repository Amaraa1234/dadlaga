#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *input, *even, *odd;
    int num;
    input = fopen("input.txt", "r");
    even = fopen("even.txt", "w");
    odd = fopen("odd.txt", "w");
    
    while (fscanf(input, "%d", &num) == 1) {
        if (num % 2 == 0)
            fprintf(even, "%d ", num);
        else
            fprintf(odd, "%d ", num);
    }

   
    fclose(input);
    fclose(even);
    fclose(odd);

    system("pause");
    return 0;
}
