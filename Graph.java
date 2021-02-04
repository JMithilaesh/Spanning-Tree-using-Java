//Importing all the required packages
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
public class Graph
{
  int vertex_count;
  int[][] adjacency_matrix;
  //Constructor to initialize the adjacency matrix for the given number of vertices
 
    public Graph(int no_of_vertex) 
    {
        vertex_count = no_of_vertex;
        adjacency_matrix = new int[vertex_count + 1][vertex_count + 1];
    }
 //Function to add a edge in the graph
    public void graphAddEdge(int start_v, int end_v, int weight) 
    {
        try 
        {
            adjacency_matrix[start_v][end_v] = weight;
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("No such vertices exist in the given graph");
        }
    }
    //Function to get the weight of the given edge
    public int graphGetEdge(int start_v, int end_v) 
    {
        try 
        {
            return adjacency_matrix[start_v][end_v];
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("No such vertices exist in the given graph");
        }
        return -1;
    }
}