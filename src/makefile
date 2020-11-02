FLAGS = -cp
JC = javac -cp
CLASSPATH = forms_rt.jar
.SUFFIXES: .java .class
.java.class:
	 $(JC) $(JFLAGS) $ forms_rt.jar: $*.java

CLASSES = m1graf2020/Node.java m1graf2020/Edge.java m1graf2020/Graf.java m1graf2020/UndirectedGraf.java m1graf2020/TreeExample.java m1graf2020/RandomGraph.java m1graf2020/Exceptiongraf.java m1graf2020/Interface.java

run: m1graf2020/Interface.class
	java -cp $(CLASSPATH): m1graf2020/Interface
	make clean

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) m1graf2020/*.class





