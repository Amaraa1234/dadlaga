#include <stdio.h> 
#include <stdlib.h> 
int main(){ 
 FILE *fout = fopen("integers.dat", "wb");   
 int i; 
 printf("i = "); 
 scanf("%d", &i); 
  
 while(i!=-1){    
  fwrite(&i, 4, 1, fout); 
  printf("i = ");    
  scanf("%d", &i);
  } 
 fclose(fout);       
 system("pause"); 
 return 0; 
}

