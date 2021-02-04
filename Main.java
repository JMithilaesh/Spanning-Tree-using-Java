//The main class which creates a graph and performs BFS, Dijkstra and MST Implementation
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
 
public class Main 
{
  //Calling of the main function 
      public static void main(String[] args) 
    {
        int vertex, edge, counter = 1, start_vertex, end_vertex, weight;
        Scanner sc = new Scanner(System.in);
        
        try 
        {
          //Get the vertex and edge count for the graph from the user
            System.out.println("Enter the total number of vertices for the graph: ");
            vertex = sc.nextInt();
            System.out.println("Enter the total number of edges for the graph: ");
            edge = sc.nextInt();
            //Create object for the Graph class
 
            Graph graph = new Graph(vertex);
            //Get the edges of the graph from the user and add it to the adjacency matrix
 
            System.out.println("<FROM> <TO> <WEIGHT>");
            while (counter <= edge) 
            {
                start_vertex = sc.nextInt();
                end_vertex = sc.nextInt();
                weight = sc.nextInt();
                graph.graphAddEdge(start_vertex, end_vertex, weight);
                counter++;
            }
            //Displaying the graph entered by the user
 
            System.out.println("\n\n<-----Representing the given graph as adjacency matrix--------->");
            System.out.print("\t");
            for (int i = 1; i <= vertex; i++)
                System.out.print(i + "\t");
            System.out.println();
 
            for (int i = 1; i <= vertex; i++) 
            {
                System.out.print(i + "\t");
                for (int j = 1; j <= vertex; j++) 
                    System.out.print(graph.graphGetEdge(i, j) + "\t");
                System.out.println();
            }
            //Enter a source and destination for the BFS and Dijkstra Implementation

            System.out.println("\n\nEnter the source and the destination vertex for which path needs to be found ");
            int source = sc.nextInt();
            int destination = sc.nextInt();


           //BFS Implementation
            System.out.println("\n\n<-----Breadth First Search of Graph for given source and destination------->");
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            bfs.breadthFirstSearch(graph.adjacency_matrix, source, destination);
            
            //Dijkstra Implementation 
            System.out.println("\n\n<-----Dijkstras Shortest Path Implementation for given source and destination----->");
            Dijkstras dijkstras = new Dijkstras(vertex);
            dijkstras.dijkstra_algorithm(graph.adjacency_matrix, source);
            System.out.println("The Shorted Path from " + source + " to " + destination + " is of weight ");
            for (int i = 1; i <= dijkstras.total_cost.length - 1; i++)
            {
                if (i == destination)
                    System.out.println(dijkstras.total_cost[i]);
            }

            //MST Implementation
            System.out.println("\n\n<------Minimum Spanning Tree Implementation------>");
            MinimumSpanningTree mst = new MinimumSpanningTree(vertex);
            mst.prims_Implementation(graph.adjacency_matrix);
            mst.displayMST();
 
        }
        catch (Exception E) 
        {
            System.out.println(E.getMessage());
        }
        
        sc.close();
    }
}