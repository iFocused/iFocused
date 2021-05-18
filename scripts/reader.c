#include <stdio.h>


const char filename[50] = "test.txt";

int main() {
   FILE *fp;
   char c = 'a';
   fp = fopen(filename, "r");  
  
  

  for (int i = 0; i < 19; i++) {
      while (c != '\n') {
        fread(&c, sizeof(char) ,1, fp);
        printf("%c", c);
    }
    c = 'a';
  }
  
  
  
  
  
  
    
   fclose(fp);
}