#include<stdio.h>

int main () {
int i=1 , grade, passe=0, pail=0;

while(i <= 10 ){
	printf("dun oruul");
	scanf("%d", &grade);

if(grade >= 6){
	printf("1\n");
	passe++;
}
else{
	printf("2\n");
	pail = pail + 1;
}
i =i + 1;
}
if(passe > 8){
	printf("rais tuition");
}

return 0;

}
