
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class that represents a graph. This class is based around a
 * List of nodes and a List of edges. The nodes are very simple 
 * classes that only contain the name of the node. The edges are 
 * more important as they define the structure of the graph. 
 */
public class Graph implements SerializableSY
{
    private List<Node> nodes;
    private List<Edge> edges;

    /**
     * Graph constructor. This initialises the lists that 
     * will hold the nodes and edges
     */
    public Graph()
    {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    /**
     * Function that returns the list of nodes from the graph
     * @return The list of nodes
     */
    public List<Node> nodes()
    {
        return nodes;
    }
    
    /**
     * Function that returns the list of edges from the graph
     * @return The list of edges
     */
	public List<Edge> edges() 
	{
		return edges;
	}

	/**
	 * Function to find a node in the graph given the nodes name. 
	 * This function will search through the list of nodes and check
	 * each of their names. If it finds a matching node, it will be 
	 * returned. If not, it will return null.
	 * @param name The name of the node that you wish to find
	 * @return The found node or null
	 */
    public Node find(String name)
    {
        for (Node n : nodes)
        {
            if (n.name().equals(name)) return n;
        }
        return null;
    }
    
    /**
     * Finds a node given an id. The id represents the position of the
     * node in the list of nodes
     * If the id is out of bounds, null is returned
     * @param index The index of the node
     * @return The desired node or null
     */
    public Node find(int index)
    {
    	if(index < 0 || index >= nodeNumber())
    	{
    		return null;
    	}
    	return nodes.get(index);
    }
    
    /**
     * Returns the number of nodes in the graph
     * @return The number of nodes in the graph
     */
    public int nodeNumber()
    {
    	return nodes.size();
    }
    
    /**
     * Function to add a new node to the graph
     * @param node The node you wish to add
     */
    public void add(Node node)
    {
        nodes.add(node);
    }
    
    /**
     * Function to add a new edge to the graph
     * @param edge The edge you wish to add
     */
    public void add(Edge edge)
    {
    	edges.add(edge);
    }
    
    
    /**
     * Function to get the set of edges that blong to a given node
     * @param nodeName The name of the node
     * @return List of edges
     */
    public List<Edge> edges(String nodeName)
    {
    	List<Edge> output = new ArrayList<Edge>();
    	for(Edge e : edges)
    	{
    		if(e.connectsNode(nodeName))
    		{
    			output.add(e);
    		}
    	}
    	
    	return output;
    }

    public List<Integer> getNodeNeighbours(Integer nodeId)
    {
        ArrayList<Integer> nodes_out = new ArrayList<Integer>();

        for (Edge edge : edges)
        {
            if (edge.id1().equals(nodeId))
            {
                nodes_out.add(edge.id2());
            }
            if (edge.id2().equals(nodeId))
            {
                nodes_out.add(edge.id1());
            }
        }

        return nodes_out;
    }

    public List<Edge> getNodeEdges(Integer nodeId)
    {
        List<Edge> edges_out = new ArrayList<Edge>();

        for (Edge edge : edges)
        {
            if (edge.id1().equals(nodeId)||edge.id2().equals(nodeId))
            {
                edges_out.add(edge);
            }
        }

        return edges_out;
    }

    public List<Edge> getEdges(Integer nodeId_1,Integer nodeId_2)
    {
        List<Edge> edges_out = new ArrayList<Edge>();

        for (Edge edge : edges)
        {
            if ((edge.id1().equals(nodeId_1)&&edge.id2().equals(nodeId_2))&&
                    (edge.id2().equals(nodeId_1)&&edge.id1().equals(nodeId_2)))
            {
                edges_out.add(edge);
            }
        }

        return edges_out;
    }



    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        ByteArrayOutputStream subBuffers[] = new ByteArrayOutputStream[nodes.size()+edges.size()];
        for (int i=0; i<nodes.size(); i++)
            subBuffers[i] = nodes.get(i).save();
        for (int i=0; i<edges.size(); i++)
            subBuffers[i+nodes.size()] = edges.get(i).save();


        buffer.write(ByteBuffer.allocate(4).putInt(nodes.size()).array(),0,4);
        buffer.write(ByteBuffer.allocate(4).putInt(edges.size()).array(),0,4);
        if (nodes.size()>0)
            buffer.write(ByteBuffer.allocate(4).putInt(subBuffers[0].size()).array(),0,4);
        if (edges.size()>0)
            buffer.write(ByteBuffer.allocate(4).putInt(subBuffers[nodes.size()].size()).array(),0,4);

        for (ByteArrayOutputStream subBuffer : subBuffers)
        {
            Integer size = subBuffer.size();
            buffer.write(subBuffer.toByteArray(),0,size);
        }

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        if (buffer.available()<8)
            return;

        byte bytesInt[] = new byte[4];
        buffer.read(bytesInt,0,4);
        Integer numNodes = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesInt,0,4);
        Integer numEdges = ByteBuffer.wrap(bytesInt).getInt();

        if (buffer.available()<((numNodes>0 ? 4:0)+(numEdges>0 ? 4:0)))
            return;


        Integer nodeStride = 0;
        Integer edgeStride = 0;
        if (numNodes>0)
        {
            buffer.read(bytesInt,0,4);
            nodeStride = ByteBuffer.wrap(bytesInt).getInt();
        }
        if (numEdges>0)
        {
            buffer.read(bytesInt,0,4);
            edgeStride = ByteBuffer.wrap(bytesInt).getInt();
        }

        byte bytesData[] = new byte[nodeStride*numNodes+edgeStride*numEdges];

        if (buffer.available()<(nodeStride*numNodes+edgeStride*numEdges))
            return;

        Game.fastprintln("Graph Nodes: "+numNodes+" Edges: "+numEdges+" strideNode: "+nodeStride+" strideEdge: "+edgeStride);

        nodes.clear();
        edges.clear();

        for (int i=0; i<numNodes; i++)
        {
            buffer.read(bytesData,0,nodeStride);
            Node node = new Node(-1);
            node.load(new ByteArrayInputStream(bytesData,0,nodeStride));
            nodes.add(node);
        }
        Collections.sort(nodes,
                new Comparator<Node>()
                {
                    public int compare(Node o1, Node o2)
                    {
                        return o1.name().compareTo(o2.name());
                    }
                }
        );

        for (int i=0; i<numEdges; i++)
        {
            buffer.read(bytesData,0,edgeStride);
            Edge edge = new Edge(-1,-2, Double.MAX_VALUE, Edge.EdgeType.Underground);
            edge.load(new ByteArrayInputStream(bytesData,0,edgeStride));
            edges.add(edge);
        }
    }



}
