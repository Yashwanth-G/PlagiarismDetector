JCC = javac

JFLAGS = -g

JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JCC) $(JFLAGS) $*.java

CLASSES = Main.java

main = Main

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(main)

clean:
	$(RM) *.class