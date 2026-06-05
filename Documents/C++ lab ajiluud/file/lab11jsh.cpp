#include <stdio.h> 
#include <stdlib.h> 

int main(){ 
    FILE *fin, *fout;  
    double d, max;    
    fin=fopen("input.txt", "rt"); 

    fscanf(fin, "%lf", &max);  

    while(fscanf(fin, "%lf", &d) == 1){ 
        if(d>max)  
           max=d;  
    } 
    
	fclose(fin);   

    fout=fopen("output.txt", "wt");
    fprintf(fout, "%lf", max); 
    fclose(fout);   
    
	system("pause"); 
    return 0; 
} 

