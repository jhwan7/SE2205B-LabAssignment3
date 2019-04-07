public abstract class Graph<V> implements GraphADT<V>{

    //Junseo Hwang : 250971763
    //Jeongwon Song: 250966253


    int[] parent = new int[numVertices()];

    public int breadthFirstPathSearch(Graph<V> FN, int s, int d){
        LinkedListQueue<Vertex<V>> llq = new LinkedListQueue<>(); //List to add the initial node and adjacent node
        int [] visited = new int[FN.numVertices()]; //Indicated whether the node the visited or not

        for(int v : visited){
            v = 0; // Initialize all of the array content to 0 to indicate unvisited (visited = 1, unvisited = 0)
        }
        for(int v : parent){
            v = -1; // first set parent list to all -1
        }
        llq.enqueue(getVertex(s)); // Enqueue the initial node into the QueueLinkedLIst
        Vertex<V> vertex; // Reference Variable to hold a vertex
        int result = 0; //Indicates if the vertex matches the destination vertex
        while(!llq.isEmpty()){ // Iterates until llq is empty
            vertex = llq.dequeue(); // Remove the Initial node to the reference variable
            parent[vertex.getLabel()] = 1; // Add the current vertex into the parent
            for(Edges e: outgoingEdges(vertex)){ // For each outgoing edge of the vertex
                Vertex<V> op = opposite(vertex,e); //Get the opposite Vertex
                if(visited[op.getLabel()] == 0) {// First Check if opposite node is unvisited
                    if (e.flowCapcity() - e.flow() > 0) { //Second check if flow capacity is greater than the flow
                        llq.enqueue(op); //If all cases are true then enqueue the opposite vertex into the LinkedListQueue
                        visited[op.getLabel()] = 1; //Indicate that the Opposite Vertex is now visited
                    }
                }
            }
            if(vertex.getLabel()==d) result=1; //If the vertex is equal to the destination vertex it indicates 1
        }
        return result; //Return result to notify if such path was found or not.
    }
    public void maximizeFlowNetwork(Graph fN, int s, int t){
        int bottleneck = Integer.MAX_VALUE; //First set bottleneck to a high number

        while(breadthFirstPathSearch(fN,s,t)==1){ //iterates until there is no path available

            for(int i = parent.length-1;parent[i]!=s;){ //iterate through from the end node until the node matches the start node

                Vertex<V> j  = getVertex(parent[i]); //Set vertex j to the parent node of i

                System.out.println(j+"->"+i); //Shows what the parent is

                if(getEdge(j,getVertex(i)).flowCapcity() - getEdge(j,getVertex(i)).flow() < bottleneck){ //If the possible flow in the edge is less than the bottle cap
                    bottleneck = getEdge(j,getVertex(i)).flowCapcity() - getEdge(j,getVertex(i)).flow(); //Reset the bottle cap to the possible flow. This sets the bottleneck value to the lowest possible value
                }
                i = j.getLabel(); // set i index to j index so the for loop iterates the next parent of j
            }
            for(int i = parent.length-1;i!=s;) { //iterate through until i matches the start vertex

                Vertex<V> j = getVertex(parent[i]); //Set the parent vertex of i to j
                int flow = getEdge(j, getVertex(i)).flow();
                getEdge(j,getVertex(i)).setFlow(flow+bottleneck); //increment the all edges in the path by the bottle neck

                insertEdge(getVertex(i), j, -bottleneck);//Insert edge to take consideration of back flow
                getEdge(getVertex(i),j).setFlow(-bottleneck);
                i = j.getLabel();  // set i index to j index so the for loop iterates the next parent of j
            }

        }
    }

}
