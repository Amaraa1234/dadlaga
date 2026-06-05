#include<stdio.h>

int main(){
	int k, a, n, niilber;
	
	printf("k toog oruul");
	scanf("%d", &k);
	if(k >= 10 && k <= 20){
		a = k / 10;
		n = k % 10;
		niilber = a + n;
		printf("niilber%d", niilber);
	}
	else{
		int urvuu = 0 , temp = k;
		while(temp != 0){
			urvuu = urvuu * 10 + temp % 10;
			temp = temp / 10;
		}
		printf("urvuu %d", urvuu);
	}
	return 0;
}
