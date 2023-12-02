package finlab.backend;

import java.io.File;

public class Test {
    private static final GraphUtility graphUtility = new GraphUtility();
    public static void main(String[] args) {
        try {
            graphUtility.readFile(new File("graphs/unweighted-undirected-matrix"));
            System.out.println("V={" + graphUtility.getGraph().getNodes().toString() + "}");
            System.out.println("E={" + graphUtility.getGraph().getEdges().toString() + "}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
