public class Assignment3 implements GraphADT{

    //Junseo Hwang : 250971763
    //Jeongwon Song: 250966253


    List <Vertex<V>> parent = new ArrayList<Vertex<V>>(); // Holds the picture of visited nodes

    public int breadthFirstPathSearch(Graph FN, int s, int d){
        LinkedListQueue<Vertex<V>> llq = new LinkedListQueue(); //List to add the initial node and adjacent node
        int [] visited = new int[FN.numVertices()]; //Indicated whether the node the visited or not

        for(int b : visited){
            b = 0; // Initialize all of the array content to 0 to indicate unvisited (visited = 1, unvisited = 0)
        }
        for(int nodeKey : parent){
            nodeKey = -1; // first set parent list to all -1
        }
        llq.enqueue(s); // Enqueue the initial node into the QueueLinkedLIst
        int vertex; // Reference Variable to hold a vertex
        int result = 0; //Indicates if the vertex matches the destination vertex
        while(!llq.isEmpty()){ // Iterates until llq is empty
            vertex = llq.dequeue(); // Remove the Initial node to the reference variable
            parent.add(vertex); // Add the current vertex into the parent
            for(Edge e: outgoingEdges(vertex)){ // For each outgoing edge of the vertex
                Vertex<V> op = opposite(vertex,e); //Get the opposite Vertex
                if(visited[op.getLabel()] == 0) {// First Check if opposite node is unvisited
                    if (e.flowCap - e.flow > 0) { //Second check if flow capacity is greater than the flow
                        llq.enqueue(op); //If all cases are true then enqueue the opposite vertex into the LinkedListQueue
                        visited[op.getLabel()] = 1; //Indicate that the Opposite Vertex is now visited
                    }
                }
            }
            if(vertex==d) result=1; //If the vertex is equal to the destination vertex it indicates 1
        }
        System.out.println("\nPossible Augmented Path: ");
        for(Vertex<V> v : parent){
            System.out.print(v.getLabel +" "); //Print the path found with their vertex labels
        }
        return result; //Return result to notify if such path was found or not.
    }


    public void maximizeFlowNetwork(Graph fN, int s, int t){
        int bottleneck = Integer.MAX_VALUE; //First set bottleneck to a high number

        while(breadthFirstPathSearch(fN,s,t)==1){ //iterates until there is no path available

            for(int i = parent.length-1;parent[i]!=s;){ //iterate through from the end node until the node matches the start node

                Vertex<v> j  = parent[i]; //Set vertex j to the parent node of i

                System.out.println(j+"->"+i); //Shows what the parent is

                if(getEdge(j,i).flowCap - getEdge(j,i).flow < bottleneck){ //If the possible flow in the edge is less than the bottle cap
                    bottleneck = getEdge(j,i).flowCap - getEdge(j,i).flow; //Reset the bottle cap to the possible flow. This sets the bottleneck value to the lowest possible value
                }
                i = j.getLabel(); // set i index to j index so the for loop iterates the next parent of j
            }
            for(int i = parent.length-1;i!=s;) { //iterate through until i matches the start vertex

                Vertex<v> j = parent[i]; //Set the parent vertex of i to j

                getEdge(j, i).flow += bottleneck; //increment the all edges in the path by the bottle neck

                insertEdge(i, j, -bottleneck, -bottleneck); //Insert edge to take consideration of back flow

                i = j.getLabel();  // set i index to j index so the for loop iterates the next parent of j
            }

        }


        //System.out.println("\nThe total Flow added is "+flowAdded);
    }

}
