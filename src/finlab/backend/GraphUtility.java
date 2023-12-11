package finlab.backend;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * GraphUtility class
 */
public class GraphUtility {

    // Class variable
    private Graph graph;

    /**
     * Default constructor for class
     */
    public GraphUtility() {
        graph = null;
    }

    /**
     * Getter for graph
     * @return graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Setter for graph
     * @param graph
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * TODO: Documentation
     * @param file given file path
     * @throws Exception if error or exception occurs
     */
    public void readFile(File file) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            graph = new Graph();
            String line;
            int y = 0;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (graph.getCount() == 0) {
                    populateVertices(tokens.length);
                }

                for (int x = 0; x < tokens.length; x++) {
                    int weight = Integer.parseInt(tokens[x].trim());
                    if (weight != 0) {
                        Vertex sourceVertex = graph.getNodes().get(y);
                        Vertex destinationVertex = graph.getNodes().get(x);
                        graph.addEdge(sourceVertex, destinationVertex, weight);
                    }
                }
                y++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error reading the file.");
        }
    }



    /**
     * TODO: Documentation
     * @param verticesCount given number of nodes
     * @return ArrayList of nodes.
     */
    private void populateVertices(int verticesCount) {
        if (graph != null) {
            List<Vertex> vertices = new ArrayList<>();
            String labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char symbol;

            for (int i = 0; i < verticesCount; i++) {
                symbol = labels.charAt(i);
                Vertex vertex = new Vertex(String.valueOf(symbol));
                vertex.setId(i);  // Assign unique ID to each vertex
                vertices.add(vertex);
                graph.addVertex(vertex);
            }
            graph.setNodes(vertices);
        } else {
            graph = new Graph();
        }
    }


    public void depthFirstSearch(Vertex start) {
        // TODO: write corresponding code here
    }

    /**
     * The following code returns the Breadth-First Search traversal (ArrayList) of a graph given a starting node.
     * @param start
     * @return
     */
    public ArrayList<Vertex> breadthFirstSearch(Vertex start) {
        Queue<Vertex> queue = new LinkedList<>(); // Adjacent nodes of current vertex
        ArrayList<Vertex> visited = new ArrayList<>(); // Visited Nodes

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex curr = queue.poll();


            List<Edge> edges = graph.getEdges(); // Get the edges of the current graph

            for (Edge edge : edges){
                if (edge.getStart().equals(curr)){ // If it is a neighbour
                    Vertex neighbor = edge.getEnd(); // Get the adjacent node

                    if (!visited.contains(neighbor) && !queue.contains(neighbor)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }

            }

        }

        return visited;
    }

    public ArrayList<Vertex> dijkstraShortestPath(Graph graph, Vertex start, Vertex end) {

        //Will hold the distances
        double[] distance = new double[graph.getNodes().size()];

        //Will hold the previous nodes to get to curr node
        Vertex[] predecessor = new Vertex[graph.getNodes().size()];

        // Will hold all the visited paths
        boolean[] visited = new boolean[graph.getNodes().size()];

        // 1. Initialize distances to all equal infinity
        Arrays.fill(distance, Double.POSITIVE_INFINITY);

        // 2. Set start vertex to 0
        distance[start.getId()] = 0;

        // Use a PriorityQueue to get the minimum distance vertex
        PriorityQueue<VertexDistancePair> pq = new PriorityQueue<>();
        pq.add(new VertexDistancePair(start, 0));

        while (!pq.isEmpty()) {
            VertexDistancePair currentPair = pq.poll();
            Vertex currentVertex = currentPair.getVertex();

            if (!visited[currentVertex.getId()]) {
                visited[currentVertex.getId()] = true;

                for (Edge neighbor : currentVertex.getNeighbors()) {
                    int neighborIndex = neighbor.getEnd().getId();

                    if (!visited[neighborIndex]) {
                        double newDistance = distance[currentVertex.getId()] + neighbor.getWeight();
                        if (newDistance < distance[neighborIndex]) {
                            distance[neighborIndex] = newDistance;
                            predecessor[neighborIndex] = currentVertex;
                            pq.add(new VertexDistancePair(neighbor.getEnd(), (int) newDistance));
                        }
                    }
                }
            }
        }
        System.out.println("Vertex IDs: " + Arrays.toString(graph.getNodes().stream().map(Vertex::getId).toArray()));
        System.out.println("Edge Weights: " + graph.getEdges().stream().map(Edge::getWeight).collect(Collectors.toList()));
        System.out.println("Distances: " + Arrays.toString(distance));


        // Construct the shortest path
        ArrayList<Vertex> shortestPath = new ArrayList<>();
        Vertex current = end;
        while (current != null) {
            shortestPath.add(current);
            current = predecessor[current.getId()];
        }

        // Reverse the path to get it from start to end
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}

/**
 * Class to be used in dijkstraShortestPath since it requires a means to store the distance.
 */
class VertexDistancePair implements Comparable<VertexDistancePair> {
    private Vertex vertex;
    private double distance;
    private Vertex predecessor;

    public VertexDistancePair(Vertex vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
        this.predecessor = null;
    }

    // Getter and setter for predecessor
    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    // Getter and setter for vertex
    public Vertex getVertex() {
        return vertex;
    }

    public double getDistance() {
        return distance;
    }

    // Compare to method to compare by distance
    @Override
    public int compareTo(VertexDistancePair other) {
        return Double.compare(this.distance, other.distance);
    }
}
