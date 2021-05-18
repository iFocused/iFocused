FLAGS= -Wall -Werror -fsanitize=address -g
OBJ = reader.o 
DEPENDENCIES = reader.h

all: reader 

.PHONY: reader  
reader: ${OBJ}
	gcc ${FLAGS} -o $@ $^
	rm *.o 

run:
	sudo ./reader
	
%.o: %.c ${DEPENDENCIES}
	gcc ${FLAGS} -c $< 


clean:
	rm reader 
	rm *.o
