import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of vertexes:");
        int v = scanner.nextInt();
        Graph graph = new Graph(v);

        while (true){
            if(graph.isCompleted()){
                break;
            }
            System.out.println("enter new Edge or -1 for finish");
            int v1 = scanner.nextInt();
            if(v1==-1){
                break;
            }
            int v2 = scanner.nextInt();
            graph.addEdge(v1,v2);
        }
//        int matrix[][] = {{0, 1, 1, 1, 0, 0, 0, 0},
//                {0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 1, 0, 0, 1, 0},
//                {0, 0, 0, 0, 0, 0, 1, 0},
//                {0, 0, 1, 0, 0, 0, 0, 1},
//                {0, 1, 0, 0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 0, 1, 0, 1},
//                {0, 0, 0, 0, 0, 0, 0, 0}};
//        graph.setAdjacentMatrix(matrix);
        System.out.print("Enter beginnig:");
        int source = scanner.nextInt();
        System.out.print("Enter destination:");
        int target = scanner.nextInt();
        graph.findDisjointPath(source,target);

    }
}
