#include <stdio.h> 
#include <stdlib.h> 
struct Anket {  
char name[30]; 
int age; 
char huis[15];
int angi;
}; 
int main() {
int i = 1; 
struct Anket student; 
while( i <= 3){
printf("ner = "); 
scanf("%s", student.name); 
printf("nas = "); 
scanf("%d", &student.age); 
printf("huis = ");
scanf("%s",student.huis );
printf("angi = ");
scanf("%d", &student.angi);
i = i + 1;
printf("%s\t%d\t%s\t%d\n ", student.name, student.age, student.huis , student.angi );
}
system("pause"); 
return 0; 
}
