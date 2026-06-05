#include <stdio.h>
#include <stdlib.h>


int main () {
	int a, b, c;
	
	printf("a, b, c ");
	scanf("%d %d %d" ,&a, &b, &c);
	
	if (a >= b && a >= c) {
		printf("hamgiin ih too: %.2ld\n" , a );
	}
	if (b >= a && b >= c ) {
		printf("hamgiin ih too: %.2ld\n", b );
	}
	if (c >= a && c >= b ) {
		printf("hamgiin ih too: %.2ld\n", c);
	}
	return 0;
}
