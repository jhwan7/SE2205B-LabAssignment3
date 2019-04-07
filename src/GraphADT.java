public interface GraphADT<V> {
    int numVertices();
    Iterable<Vertex<V>> vertices();
    int numEdges();
    Iterable<Edges<Integer>> edges();
    Edges<Integer> getEdge(Vertex<V> u, Vertex<V> v);
    Iterable<Vertex<V>> endVertices(Edges<Integer> e);
    Vertex<V> opposite(Vertex<V> v, Edges<Integer> e);
    int outDegree(Vertex<V> v);
    int inDegree(Vertex<V> v);
    Iterable<Edges<Integer>> outgoingEdges(Vertex<V> v);
    Iterable<Edges<Integer>> incomingEdges(Vertex<V> v);
    void insertVertex(Vertex<V> v);
    void insertEdge(Vertex<V> u, Vertex<V> v, Integer x);
    void removeVertex(Vertex<V> v);
    void removeEdge(Edges<Integer> e);
    Vertex<V> getVertex(int label);
}
