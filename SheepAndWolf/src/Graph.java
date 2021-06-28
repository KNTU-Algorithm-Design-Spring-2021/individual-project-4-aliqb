import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int v;
    private int [][]adjacentMatrix;
    private ArrayList<String> disjointPaths;
    public Graph(int v){
        this.v= v;
        this.adjacentMatrix = new int[v][v];
        this.disjointPaths = new ArrayList<>();
    }
    public Graph(int v,int [][]adjacentMatrix){
        this.v= v;
        this.adjacentMatrix = new int[v][v];
        this.setAdjacentMatrix(adjacentMatrix);
    }
    public void addEdge(int v1,int v2){
        this.adjacentMatrix[v1][v2] = 1;
    }

    public boolean hasEdge (int source, int target){
        return (this.adjacentMatrix[source][target] > 0);
    }

    private void setWeight(int source,int target,int weight){
        this.adjacentMatrix[source][target] = weight;
    }
    private int getWeight(int source,int target){
        return this.adjacentMatrix[source][target];
    }

    public void setAdjacentMatrix(int[][] adjacentMatrix) {
        for(int i=0;i<adjacentMatrix.length;i++){
            for (int j = 0;j<adjacentMatrix[i].length;j++){
                this.adjacentMatrix[i][j] = adjacentMatrix[i][j];
            }
        }
    }
    public boolean isCompleted(){
        int sumOfZeros = 0;
        for(int i=0;i<adjacentMatrix.length;i++){
            for (int j = 0;j<adjacentMatrix[i].length;j++){
                if(!this.hasEdge(i,j)){
                    sumOfZeros++;
                }
            }
        }
        return sumOfZeros == 0;
    }

    public boolean bfs(int source, int target, int []path){
        boolean []visited =  new boolean[this.v];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        path[source] = -1;
        while (!queue.isEmpty()){
            int u = queue.peek();
            queue.remove();
            for (int v = 0; v < this.v; v++) {
                if (visited[v] == false &&
                       this.hasEdge(u,v)) {
                    queue.add(v);
                    path[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[target] == true);

    }
    public void findDisjointPath(int source,int target){
        int u,v;
        Graph rGraph = new Graph(this.v,this.adjacentMatrix);
        int []path = new int [this.v];
        int maxFlow = 0;
        while(rGraph.bfs(source,target,path)){
            int pathFlow = Integer.MAX_VALUE;
            for(v = target; v !=source;v=path[v]){
                u = path[v];
                pathFlow=Math.min(pathFlow,rGraph.getWeight(u,v));
            }
            for(v = target; v !=source;v=path[v]){
                u = path[v];
                rGraph.setWeight(u,v,rGraph.getWeight(u,v)-pathFlow);
                rGraph.setWeight(v,u,rGraph.getWeight(v,u)+pathFlow);
            }
            maxFlow += pathFlow;
            this.addToPaths(source,target,path);
        }
        if(maxFlow >1){
            for(int i = 0;i<this.disjointPaths.size();i++){
                System.out.println(this.disjointPaths.get(i));
            }
        }else{
            System.out.print("There is not any disjoint path");
        }
//        return maxFlow;
    }
    private void addToPaths(int source,int target,int []path){
        boolean isAllow = true;
        String pathString = "";
        for(int v = target; v !=source;v=path[v]){
            int u = path[v];
            if(!this.hasEdge(u,v)){
                isAllow = false;
                break;
            }
            pathString = " "+ v + pathString;
        }
        if(isAllow){
            pathString = source + pathString;
            this.disjointPaths.add(pathString);
        }
    }
}
