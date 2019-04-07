public class Assignment3 {

    //Junseo Hwang : 250971763
    //Jeongwon Song:

    public int breadthFirstPathSearch(Graph FN, int s, int d){
        LinkedListQueue<Vertex<V>> llq = new LinkedListQueue(); //List to add the initial node and adjacent node
        int [] visited = new int[FN.numVertices()]; //Indicated whether the node the visited or not
        List <Vertex<V>> snapshot = new ArrayList<Vertex<V>>(); // Holds the picture of visited nodes
        for(int b : visited){
            b = 0; // Initialize all of the array content to 0 to indicate unvisited (visited = 1, unvisited = 0)
        }
        for(int nodeKey : snapshot){
            nodeKey = -1; // first set snapshot list to all -1
        }
        llq.enqueue(s); // Enqueue the initial node into the QueueLinkedLIst
        int vertex; // Reference Variable to hold a vertex
        int result = 0; //Indicates if the vertex matches the destination vertex
        while(!llq.isEmpty()){ // Iterates until llq is empty
            vertex = llq.remove(); // Remove the Initial node to the reference variable
            snapshot.add(vertex); // Add the current vertex into the snapshot
            for(Edge e: outgoingEdges(vertex)){ // For each outgoing edge of the vertex
                Vertex<V> op = opposite(vertex,e); //Get the opposite Vertex
                if(visited[op.getLabel()] == 0) {// First Check if opposite node is visited
                    if (e.flowCap - e.flow > 0) { //Second check if flow capacity is greater than the flow
                        llq.enqueue(op); //If all cases are true then enqueue the opposite vertex into the LinkedListQueue
                        visited[op.getLabel()] = 1; //Indicate that the Opposite Vertex is now visited
                    }
                }
            }
            if(vertex==d) result=1; //If the vertex is equal to the destination vertex it indicates 1
        }
        System.out.println("\nPossible Augmented Path: ");
        for(Vertex<V> v : snapshot){
            System.out.print(v.getLabel +" "); //Print the path found with their vertex labels
        }
        return result; //Return result to notify if such path was found or not.
    }


    public void maximizeFlowNetwork(Graph fN, int s, int t){
        int bottleneck = Integer.MAX_VALUE;
        boolean flag = true;
        while(breadthFristPathSearch(fN,s,t)==1){
            //System.out.println(parent[2]);
            //System.out.println(parent[parent.length-1]);
            //System.out.println(getEdge(1,parent[parent.length-1]).flowCap);
            //if(breadthFristPathSearch(fN,s,t)==1)flag = false;
            System.out.println();
            for(int i = parent.length-1;parent[i]!=s;){
                //if(getEdge(parent[i],parent[i+1])==null) continue;
                int j  = parent[i];
                //if(j==s)break;
                System.out.println(j+"->"+i);
                if(getEdge(j,i).flowCap<bottleneck){
                    bottleneck = getEdge(j,i).flowCap;
                }
                i = j;
            }
            for(int i = parent.length-1;i!=s;){
                //if(getEdge(parent[i],parent[i+1])==null) continue;
                int j  = parent[i];
                //if(getEdge(j,i).flow+bottleneck<=getEdge(j,i).flowCap)
                //if(getEdge(j,i).flowCap<bottleneck+getEdge(j,i).flow){
                getEdge(j,i).flow += bottleneck;
                //}
                insertEdge(i,j,-bottleneck,-bottleneck);
                i = j;
            }
            //print();

        }


        //System.out.println("\nThe total Flow added is "+flowAdded);
    }

}
