#include <stdio.h>

int main() {
   FILE *fp;
   char buf[1000];
   fp = fopen("test.txt", "r");
   fscanf(fp, "%[^\n]", buf);
   printf("Data from the file:\n%s", buf);

   fclose(fp);
}