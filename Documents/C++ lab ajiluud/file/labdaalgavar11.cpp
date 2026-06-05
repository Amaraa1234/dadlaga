#include <stdio.h>
#include <stdlib.h>

FILE *fin, *fout;
int d, tegsh, sond ;

fin=fopen("i.txt", "rt");

fscanf(fin, "%d", &tegsh);
fscanf(fin, "%d", &sond);

while(fscanf(fin, "%d", &d) == 1){
	
	if(d / 2 ==0){
		printf("%d", tegsh);
		else{"%d", sond
		}
	}
	
}
fclose(fin);
fout=fopen("even.txt", "wt");
fprintf(fout, "%d", tegsh);
fclose(fout);

fout=fopen("odd.txt", "wt");
fprintf(fout, "%d", sond);
fclose(fout);

