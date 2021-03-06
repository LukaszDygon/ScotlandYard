import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Class representing a node of the graph
 */
public class Node implements SerializableSY
{
    private Integer name;
    Vector4Di pos;

    /**
     * Node constructor
     * @param n Name that will be given to the node
     */
    public Node(Integer n)
    {
        name = n;
        pos = new Vector4Di(-1,-1,-1,-1);
    }

    /**
     * Function that gets the name that has been assigned to the node
     * @return The name of the node
     */
    public Integer name()
    {
        return name;
    }

    public void setPos(Vector4Di inPos)
    {
        pos = inPos;
    }
    public Vector4Di getPos()
    {
        return pos;
    }

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write(ByteBuffer.allocate(4).putInt(name).array(),0,4);

        buffer.write(ByteBuffer.allocate(4).putInt(pos.data[0]).array(),0,4);
        buffer.write(ByteBuffer.allocate(4).putInt(pos.data[1]).array(),0,4);
        buffer.write(ByteBuffer.allocate(4).putInt(pos.data[2]).array(),0,4);
        buffer.write(ByteBuffer.allocate(4).putInt(pos.data[3]).array(),0,4);

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        if (buffer.available()<20)
            return;

        byte bytesInt[] = new byte[8];
        buffer.read(bytesInt,0,4);
        name = ByteBuffer.wrap(bytesInt).getInt();


        buffer.read(bytesInt,0,4);
        pos.data[0] = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        pos.data[1] = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        pos.data[2] = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        pos.data[3] = ByteBuffer.wrap(bytesInt).getInt();
    }
}