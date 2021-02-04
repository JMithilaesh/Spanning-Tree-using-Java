import java.util.*;
//BFS algorithm to find all possible paths from given source to destination
public class BreadthFirstSearch
{
    ArrayList<Integer>[] adjacency_list ;
    int i, j; 
    public void breadthFirstSearch(int[][] adjacency_matrix, int source, int destination)  
    { 
        boolean[] visited = new boolean[adjacency_matrix[0].length + 1]; 
        ArrayList<Integer> list_of_paths = new ArrayList<>(); 
        adjacency_list = new ArrayList[adjacency_matrix[0].length + 1];
        //Store the adjacency matrix of the graph into a list

        for(int i = 0; i < adjacency_matrix[0].length; i++) 
        { 
            adjacency_list[i] = new ArrayList<>(); 
        }
          
        for (i = 0; i < adjacency_matrix[0].length; i++) { 
            for (j = 0; j < adjacency_matrix.length; j++) { 
                if (adjacency_matrix[i][j] != 0) { 
                    adjacency_list[i].add(j); 
                } 
            } 
        } 
          
        //add the source as first element to the list
        list_of_paths.add(source); 
          
        //function to find all the paths from the source to destination and store them to a list
        display_all_paths(source, destination, visited, list_of_paths); 
    } 
  
    private void display_all_paths(Integer src, Integer dest, boolean[] visited, List<Integer> path_list) { 
          
        //The current vertex is assigned true in visited array
        visited[src] = true; 
          
        if (src.equals(dest))  
        { 
            //condition to stop traversing further once the destination is reached and print that path
            System.out.println(path_list); 
            visited[src]= false; 
            return ; 
        } 
          
        // Logic to recursively traverse from the current vertex to all the adjacent //vertices  
        for (Integer i : adjacency_list[src])  
        { 
            if (!visited[i]) 
            { 
                path_list.add(i); 
                display_all_paths(i, dest, visited, path_list); 
                path_list.remove(i); 
            } 
        } 
        visited[src] = false; 
    } 
  
}