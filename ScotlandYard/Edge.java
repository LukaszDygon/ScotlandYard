import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Class that represents a graph edge. The edges 
 * define the structure of the graph as they constitute
 * the connections between nodes. 
 * 
 * In addition, they have both an edge type and an edge weight.
 * It is important that you understand this class.
 */
public class Edge implements SerializableSY {

	/**
	 * Enum type which defines the different
	 * types of edge. (similar to the different types
	 * of transport in the game 'Scotland Yard')
	 */
	public enum EdgeType {
		Taxi, Bus, Underground;
	}
	
	
	Integer id1;
    Integer id2;
	double weight;
	EdgeType type;
	
	
	/**
	 * Returns the id of the first node that this edge connects
	 * @return The id of the first node
	 */
	public Integer id1()
	{
		return id1;
	}

	/**
	 * Returns the id of the second node that this edge connects
	 * @return The id of the second node
	 */
	public Integer id2()
	{
		return id2;
	}
	
	/**
	 * Function to check if the edge connects to a certain node
	 * @param n The name of the node
	 * @return true if it connects, false if not
	 */
	public boolean connectsNode(String n)
	{
		if(id1.equals(n) || id2.equals(n))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Function to find out given one node, what the other node name is
	 * This function also checks if the node passed in extaully is 
	 * connected by this edge. If it isn't it will return null
	 * @param n The node name that we want to find the neighbour of
	 * @return The connecting node or null if n does is not connected by this 
	 * edge
	 */
	public Integer connectedTo(String n)
	{
		if(connectsNode(n))
		{
			if(id1.equals(n))
			{
				return id2;
			}
			else
			{
				return id1;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * Function to get the weight of the edge
	 * @return The weight assigned to the edge
	 */
	public double weight()
	{
		return weight;
	}
	
	
	/**
	 * Function to get the type of the edge
	 * @return The type of the edge
	 */
	public EdgeType type()
	{
		return type;
	}
	
	
	/**
	 * Class constructor. All of the information about the edge is set in 
	 * this function and cannot be changed afterwards
	 * @param id1 The id of the first node that this edge connects
	 * @param id2 The id of the second node that this edge connects
	 * @param weight The weight of the edge
	 * @param type The type of the edge
	 */
	public Edge(Integer id1, Integer id2, double weight, EdgeType type)
	{
		this.id1 = id1;
		this.id2 = id2;
		this.weight = weight;
		this.type = type;
	}


    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write(ByteBuffer.allocateDirect(4).putInt(id1).array(),0,4);
        buffer.write(ByteBuffer.allocateDirect(4).putInt(id2).array(),0,4);
        buffer.write(ByteBuffer.allocateDirect(8).putDouble(weight).array(),0,8);

        Integer edgetypeint;
        switch (type)
        {
            case Taxi:
                edgetypeint = 0;
                break;
            case Bus:
                edgetypeint = 1;
                break;
            case Underground:
                edgetypeint = 2;
                break;
            default:
                edgetypeint = -1;
        }
        buffer.write(ByteBuffer.allocateDirect(4).putInt(edgetypeint).array(),0,4);

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        byte bytesInt[] = new byte[8];
        buffer.read(bytesInt,0,4);
        id1 = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        id2 = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,8);
        weight = ByteBuffer.wrap(bytesInt).getDouble();

        buffer.read(bytesInt,0,4);
        Integer edgetypeint = ByteBuffer.wrap(bytesInt).getInt();

        switch (edgetypeint)
        {
            case 0:
                type = EdgeType.Taxi;
                break;
            case 1:
                type = EdgeType.Bus;
                break;
            case 2:
                type = EdgeType.Underground;
                break;
            default:
                type = EdgeType.Taxi;
        }
    }
}
