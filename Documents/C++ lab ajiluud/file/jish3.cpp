#include<stdio.h>
#include<stdlib.h>

int main() {
	int n, i = 1, too;
	double s =1 , sum =0, dund;
	printf("n toog oruul");
	scanf("%d", &n);
	while(i <= n){
		printf("duriin too oruul");
		scanf("%d", &too);
	sum += too;
	s *=too;
	i++;	
	}
	dund = sum / n;
	printf("n toonii niilber%.2f\nurjver%.2f\ndundaj%.2f\n", sum, s, dund);
	return 0;
}
