FLAGS= -Wall -Werror -fsanitize=address -g
OBJ = reader.o 
DEPENDENCIES = reader.h

all: reader 


reader : ${OBJ}
	gcc ${FLAGS} -o $@ $^ 

run :
	sudo ./reader
	
%.o : %.c ${DEPENDENCIES}
	gcc ${FLAGS} -c $< 


clean :
	rm -f *.o reader 
