FLAGS = -cp
JC = javac -cp
CLASSPATH = forms_rt.jar
.SUFFIXES: .java .class
.java.class:
	 $(JC) $(JFLAGS) $ forms_rt.jar: $*.java
 
CLASSES = Node.java Edge.java Graf.java UndirectedGraf.java TreeExample.java RandomGraph.java Exceptiongraf.java Interface.java
 
run: Interface.class
	java -cp $(CLASSPATH): Interface
 
default: classes
 
classes: $(CLASSES:.java=.class)
 
clean:
	$(RM) *.class