import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
 
public class Dijkstras
{
    int total_cost[];
    private Set<Integer> after_cost_computation;
    private Set<Integer> before_cost_computation;
    private int number_of_nodes;
    private int AdjacencyMatrix[][];
    //Constructor to initialize the variables
 
    public Dijkstras(int number_of_nodes)
    {
        this.number_of_nodes = number_of_nodes;
        total_cost = new int[number_of_nodes + 1];
        after_cost_computation = new HashSet<Integer>();
        before_cost_computation = new HashSet<Integer>();
        AdjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
    }
 
    public void dijkstra_algorithm(int adjacency_matrix[][], int source)
    {
        int minimum_cost_vertex;
        //Create an adjacency matrix from the given one by assuming the cost to be infinite for //all pairs of vertices except for the ones with edge weight given
        for (int i = 1; i <= number_of_nodes; i++)
        { 
            for (int j = 1; j <= number_of_nodes; j++)
            { 
                AdjacencyMatrix[i][j] = adjacency_matrix[i][j];
                if (i == j)
                    {
                        AdjacencyMatrix[i][j] = 0;
                        continue;
                    }
                    if (AdjacencyMatrix[i][j] == 0)
                    {
                        AdjacencyMatrix[i][j] = Integer.MAX_VALUE;
                    }
            }
        }
        //Initialize the total cost to reach all the vertices as infinity
 
        for (int i = 1; i <= number_of_nodes; i++)
        {
            total_cost[i] = Integer.MAX_VALUE;
        }
        //Add the source vertex to the queue and update the total cost of that vertex to 0
 
        before_cost_computation.add(source);
        total_cost[source] = 0;
        //Find the adjacent vertex to the source vertex with the minimum cost, add it to the queue and repeat the procedure till all the vertices are reached from the source
        while (!before_cost_computation.isEmpty())
        {
            minimum_cost_vertex = find_minimum_cost_vertex();
            before_cost_computation.remove(minimum_cost_vertex);
            after_cost_computation.add(minimum_cost_vertex);
            //update the total cost for all the vertices from source vertex as we traverse //through the minimum cost vertices
            compareCostNeighbors(minimum_cost_vertex);
        }
    }
    //Function to find the adjacent minimum cost vertex for a given vertex
    private int find_minimum_cost_vertex()
    {
        int minimum;
        int vertex = 0;
 
        Iterator<Integer> iterator = before_cost_computation.iterator();
        vertex = iterator.next();
        minimum = total_cost[vertex];
        for (int i = 1; i <= total_cost.length; i++)
        {
            if (before_cost_computation.contains(i))
            {
                if (total_cost[i] <= minimum)
                {
                    minimum = total_cost[i];
                    vertex = i;
                }
            }
        }
        return vertex;
    }
    //Function to find and update the total minimum cost to reach a vertex from the given vertex
    private void compareCostNeighbors(int minimum_cost_vertex)
    {
        int edge_cost = -1;
        int new_total_cost = -1;
 
        for (int dest_vertex = 1; dest_vertex <= number_of_nodes; dest_vertex++)
        {
            if (!after_cost_computation.contains(dest_vertex))
            {
                if (AdjacencyMatrix[minimum_cost_vertex][dest_vertex] != Integer.MAX_VALUE)
                {
                    edge_cost = AdjacencyMatrix[minimum_cost_vertex][dest_vertex];
                    new_total_cost = total_cost[minimum_cost_vertex] + edge_cost;
                    if (new_total_cost < total_cost[dest_vertex])
                    {
                        total_cost[dest_vertex] = new_total_cost;
                    }
                    before_cost_computation.add(dest_vertex);
                }
            }
        }
    }
}