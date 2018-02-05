JAVAC=javac
JAVAC_FLAGS=-sourcepath src/

.PHONY : all clean

all : Driver.class

clean :
	rm -rf src/*.class

%.class : src/%.java
	${JAVAC} ${JAVAC_FLAGS} $^
