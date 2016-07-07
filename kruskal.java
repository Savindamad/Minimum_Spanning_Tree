import java.util.*;
public class kruskal{

	private int numberOfVertices;
    private Queue<Integer> queue;

	int[][] matrix;	int[][] temp;	int[] parent;
	int min,n;
	int u = 0; 	int v = 0;
	int noOfEdges = 1;
	int total = 0;

	kruskal(int[][] matrix, int n){

		this.queue = new LinkedList<Integer>();
		this.matrix = new int[n][n];
		this.parent = new int[n];
		this.temp = new int[n][n];

		this.n=n;
		this.matrix=matrix;

		for(int i = 0; i<n; i++){
			for(int j = 0; j<n; j++){
				temp[i][j]=0;
			}
		}

		while(noOfEdges < n){
			
			min = 999;
			
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(matrix[i][j] <= min && matrix[i][j]!=0){	
						min = matrix[i][j];
						u = i;
						v = j;	
					}	
				}			
			}

			temp[u][v]=temp[v][u]=1;

			if(parent[u]==0 || parent[v]==0){
				parent[u]=1;
				parent[v]=1;
				noOfEdges++;
				System.out.println("Edge Found: " + (u+1) + "-->" + (v+1) +" Cost : " + min);
				total+=min;
			}
			else if(parent[u]==1 && parent[v]==1)
			{
				if(checkCycle(u)==false){
					temp[u][v]=temp[v][u]=0;
				}
				else{
					noOfEdges++;
					System.out.println("Edge Found: " + (u+1) + "-->" + (v+1)+" Cost : " + min);
					total+=min;
				}
			}
			
			matrix[u][v] = 0;	matrix[v][u] = 0;
		}
		System.out.println("Total : "+total);
	}
	public boolean checkCycle(int source)
        {
            int[] colored = new int[n +  1];
            for (int vertex = 1; vertex <= n; vertex++)
            {
                colored[vertex] = 0;
            }

            colored[source] = 1;
            queue.add(source);
            int element, neighbour;
            while (!queue.isEmpty())
            {
                element = queue.remove();
                neighbour = 1;
                while (neighbour < n)
                { 	
                    if (temp[element][neighbour] == 1 && colored[element]== colored[neighbour])
                    {
                        return false;
                    }
                    if (temp[element][neighbour] == 1 && colored[neighbour]== 0)
                    {
                        colored[neighbour] = (colored[element] == 1 ) ? 2 : 1;
                        queue.add(neighbour);
                    }
                    neighbour++;
                }
            }
            return true;
        }

	public static void main(String[] args){
		int[][] matrix={
			{0,3,0,0,0,0,4,0,0},
			{3,0,5,0,0,0,2,3,0},
			{0,5,0,4,0,0,0,3,0},
			{0,0,4,0,3,0,0,4,0},
			{0,0,0,3,0,0,0,0,2},
			{0,0,0,0,0,0,2,0,4},
			{4,2,0,0,0,2,0,0,6},
			{0,3,3,4,0,0,0,0,5},
			{0,0,0,0,2,4,6,5,0}};

		kruskal k = new kruskal(matrix,9);
	}			
}