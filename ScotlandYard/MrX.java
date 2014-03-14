import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by devsh on 10/03/14.
 */
public class MrX implements SerializableSY
{
    private Integer id;
    private Integer xHiddenMove;
    private Integer xDoubleMove;
    private ArrayList<Integer> nodePosLog;

    public MrX(Integer ID_in)
    {
        id = ID_in;
        nodePosLog = new ArrayList<Integer>();
        xHiddenMove = 0;
        xDoubleMove = 0;
    }

    public void newGameInit()
    {
        //this just feels perverted,disgusting and wrong, much memory leak after going back to c++
        nodePosLog = new ArrayList<Integer>();

        xHiddenMove = 2;
        xDoubleMove = 4;
    }

    public Integer getId() {return id;}
    public Integer getNodePosition()
    {
        if (nodePosLog.size()>0)
            return nodePosLog.get(nodePosLog.size()-1);
        else
            return -1;
    }
    public Integer getNodePosition(int i)
    {
        if (i<nodePosLog.size())
            return nodePosLog.get(i);
        else
            return -1;
    }
    public ArrayList<Integer> getNodePosLog() {return nodePosLog;}


    // no validation takes place in this class if tickets can be used or if node can be moved
    public void setNodePosition(Integer newPos)
    {
        nodePosLog.add(newPos);
    }

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write(ByteBuffer.allocate(4).putInt(id).array(),0,4);
        buffer.write(ByteBuffer.allocate(4).putInt(xHiddenMove).array(),0,4);
        buffer.write(ByteBuffer.allocate(4).putInt(xDoubleMove).array(),0,4);


        buffer.write(ByteBuffer.allocate(4).putInt(nodePosLog.size()).array(),0,4);

        for (Integer pos : nodePosLog)
            buffer.write(ByteBuffer.allocate(4).putInt(pos).array(),0,4);

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        if (buffer.available()<16)
            return;

        byte bytesInt[] = new byte[8];
        buffer.read(bytesInt,0,4);
        Integer idTmp = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        Integer xHiddenMoveTmp = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        Integer xDoubleMoveTmp = ByteBuffer.wrap(bytesInt).getInt();



        buffer.read(bytesInt,0,4);
        Integer pastMoves = ByteBuffer.wrap(bytesInt).getInt();

        if (buffer.available()<pastMoves*4)
            return;

        nodePosLog.clear();

        for (int i=0; i<pastMoves; i++)
        {
            buffer.read(bytesInt,0,4);
            Integer tmpInt = ByteBuffer.wrap(bytesInt).getInt();
            nodePosLog.add(tmpInt);
        }

        id = idTmp;
        xDoubleMove = xDoubleMoveTmp;
        xHiddenMove = xHiddenMoveTmp;
    }
}
