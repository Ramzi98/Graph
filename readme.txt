========= Graph Algorithms and Combinatorics =========
========= PW 2 - Graph Library =========
========= BENAKMOUME Yacine & TCHOULAK Ramzi  =========
 
=== How to use ===
 
1) Write the command "make" to compile the program in the folder src/
 
2) Import the project on IntelliJ or Eclipse
 
 
=== Content ===
 
1- Class Node.java: The file that represente the implementation of the class Node, it represente a Node of a graph who's identified by his number and facultatif name.
 
2- Class Edge.java: The file that represente the implementation of the class Edge, it represente an edge between two Nodes, the source node and the the node destination. An edge can have a weidgt, and can be unweighted.
 
3- Class Graf.java: the file that represente the implementation of the class Graf, it represente a graph with all it's options, it can be a weighted graph or symmetric graph, this file use the Node and the Edge files.
 
4- Class UndirectedGraf.java: the file that represente the implementation of the class UndirectedGraf, it represnete an undirected graph, it can be a weighted undirected graph, or symmetric graph, this file use the Node and the Edge and the Graf files.
 
5- ClassExceptiongraf.java: this file represente the implementation of the class Exceptiongraf, it represente an exception that's used in the files Graf.java and UndirectedGraf.java.
 
6- Class RandomGraf.java: this file represente the creation of Random graphs, it use the Graf.java and UndirectedGraf.java files.
 
6- Class GrafTreeDisplay.java: this file represente the JTree view of the graph.
 
8- Class Interface.java: this file represente the creation of the graphical interface using the itnerface.form file, it represent the interactive menu of the manipulation of the library, it use RandomGraf and both of Graf.java and UndirectedGraf.java files.
 
 
=== Library Usage ===
 
The Graf library is a library that can be used by all the programmers just on importing the graf package. It's sort of library that make the user able to create and manage a graph with adding Nodes, Edges, or even getting the BFS or DFS format and more functionality. The class Graf is the implementation of the API, so all the functionality are stored in there for the directed graph.The undirected graph is managed by the UndirectedGraf class.
 
A simple interface is also avaible to use, it's gives an interactive menu to the user for the manipulation of the Library, To launch the programe interface, you have only to launch the makefile that's stored with the files. You can also use the Library by compiling the class Graf.java or UndirectedGraf.java and then importing them to your project.
 
To use the IHM, all what u have to do is to run the commande "make" into your terminal, to delete the generated class, you have to run "make clean" commande into your terminal.
 
 
=== Folders ===
 
 
1)DOT: the repository where we store the graph in the DOT format.
 
2)PDF: The repository where we store the graph in the PDF format.
 
3)Javadoc: the repository that store the documentation of the library in the format javadoc.