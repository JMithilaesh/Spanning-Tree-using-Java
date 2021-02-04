import java.util.InputMismatchException;
import java.util.Scanner;
 
public class MinimumSpanningTree
{
    private boolean before_minimum_edge_compute[];
    private boolean after_minimum_edge_compute[];
    private int no_of_vertices;
    private int Adjacency_Matrix[][];
    private int key[];
    public static final int INFINITE = 999;
    private int parentMST[];

    //Constructor to initialize all the variables
 
    public MinimumSpanningTree(int no_of_vertices)
    {
        this.no_of_vertices = no_of_vertices;
        before_minimum_edge_compute = new boolean[no_of_vertices + 1];
        after_minimum_edge_compute = new boolean[no_of_vertices + 1];
        Adjacency_Matrix = new int[no_of_vertices + 1][no_of_vertices + 1];
        key = new int[no_of_vertices + 1];
        parentMST = new int[no_of_vertices + 1];
    }

    //Function to count the number of vertices that are still not included in the MST
 
    public int countVerticesNotInMST(boolean before_minimum_edge_compute[])
    {
        int count = 0;
        for (int index = 0; index < before_minimum_edge_compute.length; index++)
        {
            if (before_minimum_edge_compute[index])
            {
                count++;
            }
        }
        return count;
    }

    //Implementation of Prims Algorithm to find the MST for the given graph
 
    public void prims_Implementation(int adjacency_matrix[][])
    {
        int minimum_edge_vertex;

        //Create an adjacency matrix from the given one by assuming the cost to be infinite for //all pairs of vertices except for the ones with edge weight given
        for (int source = 1; source <= no_of_vertices; source++)
        {
            for (int destination = 1; destination <= no_of_vertices; destination++)
            {
                Adjacency_Matrix[source][destination] = adjacency_matrix[source][destination];
                if (source == destination)
                    {
                        Adjacency_Matrix[source][destination] = 0;
                        continue;
                    }
                    if (Adjacency_Matrix[source][destination] == 0)
                    {
                        Adjacency_Matrix[source][destination] = INFINITE;
                    }
            }
        }
        //Initialize the minimum edge weight to Infinity for all vertices
        for (int index = 1; index <= no_of_vertices; index++)
        {
            key[index] = INFINITE;
        }
        //Consider the minimum edge to be 0 for the first vertex of the graph
        key[1] = 0;
        before_minimum_edge_compute[1] = true;
        parentMST[1] = 1;

        //Find the adjacent vertex with minimum edge weight from the given vertex and repeat the procedure until all the vertices are included in the MST
 
        while (countVerticesNotInMST(before_minimum_edge_compute) != 0)
        {
            minimum_edge_vertex = getMinimumEdgeVertex(before_minimum_edge_compute);
            before_minimum_edge_compute[minimum_edge_vertex] = false;
            after_minimum_edge_compute[minimum_edge_vertex] = true;
            compareNeighborVertices(minimum_edge_vertex);
        }
    } 
    //Function to find the minimum edge weight vertex from any given vertex
    private int getMinimumEdgeVertex(boolean[] before_minimum_edge_compute)
    {
        int min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 1; vertex <= no_of_vertices; vertex++)
        {
            if (before_minimum_edge_compute[vertex] == true && key[vertex] < min)
            {
                node = vertex;
                min = key[vertex];
            }
        }
        return node;
    }
    //Function to compare edge weight of adjacent vertices from the given minimum edge weight vertex to traverse to the next minimum edge weight vertex
    public void compareNeighborVertices(int minimum_edge_vertex)
    {
 
        for (int dest_vertex = 1; dest_vertex <= no_of_vertices; dest_vertex++)
        {
            if (after_minimum_edge_compute[dest_vertex] == false)
            {
                if (Adjacency_Matrix[minimum_edge_vertex][dest_vertex] != INFINITE)
                {
                    if (Adjacency_Matrix[minimum_edge_vertex][dest_vertex] < key[dest_vertex])
                    {
                        key[dest_vertex] = Adjacency_Matrix[minimum_edge_vertex][dest_vertex];
                        //Add the next minimum edge weight vertex to the MST
                        parentMST[dest_vertex] = minimum_edge_vertex;
                    }
                    before_minimum_edge_compute[dest_vertex] = true;
                }
            }
        }
    }
    
    //Function to display to the MST obtained
    public void displayMST()
    {
        System.out.println("(SOURCE  ===>  DESTINATION) = WEIGHT");
        for (int vertex = 2; vertex <= no_of_vertices; vertex++)
        {
            System.out.println(parentMST[vertex] + "\t===>\t" + vertex +"\t=\t"+ Adjacency_Matrix[parentMST[vertex]][vertex]);
        }
    }
}