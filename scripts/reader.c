#include <stdio.h>
#include <string.h>

char *filename = "test.txt";
// char *filename = "/etc/hosts";

// Function source code taken from:  
// https://www.geeksforgeeks.org/c-program-find-size-file/ 
long int findSize(char *file_name)
{
    // opening the file in read mode
    FILE* fp = fopen(file_name, "r");
  
    // checking if the file exist or not
    if (fp == NULL) {
        printf("File Not Found!\n");
        return -1;
    }
  
    fseek(fp, 0L, SEEK_END);
  
    // calculating the size of the file
    long int res = ftell(fp);
  
    // closing the file
    fclose(fp);
  
    return res;
}

int reader() {
   FILE *fp;
   char c = 'a';
   fp = fopen(filename, "r"); 
   long int size = findSize(filename);
  for (int i = 0; i < size; i++) {
        fread(&c, sizeof(char),1, fp);
        printf("%c", c);
    }
   fclose(fp);

   return 0;
}

/* Check if the file contains a url
* Return 1 if the file contains the url
* Return 0 if the file doesn't contain the url
*/
int checkUrl(char *filename, char *url) {
    FILE *fp;
    char temp[512];
    int line_num = 1;

	if((fp = fopen(filename, "r")) == NULL) {
		return(-1);
	}

    while(fgets(temp, 512, fp) != NULL) {
		if((strstr(temp, url)) != NULL) {
			printf("A match found on line: %d\n", line_num);
			printf("\n%s\n", temp);
			return 1;
		}
		line_num++;
	}

    fclose(fp);

    return 0;
}

/* Remove a line number from the host file
* Return 0 on success 
* Return 1 on failure
*/ 
int remove(int lineNumber) {
    
}

/* Write a url to a file
* Return 0 on success
* Return 1 if an error occurs
*/
int write(char *url) {
    FILE *fp = fopen(filename, "a");
    fputs(url, fp);
    fclose(fp);
    return 0;
}

int main () {
    write("youtube.com");
    return 0;
}
