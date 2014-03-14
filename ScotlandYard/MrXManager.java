import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devsh on 10/03/14.
 */
public class MrXManager implements SerializableSY
{
    private ArrayList<MrX> mrXs;

    public MrXManager(int numMrXs,int idOffset)
    {
        mrXs  = new ArrayList<MrX>();
        for (int i=0; i<numMrXs; i++)
            mrXs.add(new MrX(i+idOffset));
    }

    public void newGameInit()
    {
        for (MrX mrX : mrXs) mrX.newGameInit();
    }

    /**
     * Function to get the list of Mr X IDs. In this version of the game you will really only
     * have one Mr X so this function will return a list with only one entry. However this
     * may change down the line...
     * @return The list of Mr X IDs
     */
    public List<Integer> getIdList()
    {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (MrX mrX : mrXs) ids.add(mrX.getId());
        return ids;
    }

    /**
     * Function to retireve the current location of a player. The location is an id of a graph node
     * @param playerId The id of the player
     * @return The node id that the player is on
     */
    public Integer getNodeId(Integer playerId)
    {
        for (MrX mrX: mrXs)
        {
            if (mrX.getId().equals(playerId)) return mrX.getNodePosition();
        }

        return -1;
    }

    public void setPlayerPosition(Integer playerId,Integer nodeId)
    {
        for (MrX mrX: mrXs)
        {
            if (mrX.getId().equals(playerId)) mrX.setNodePosition(nodeId);
        }
    }

    public Integer getXCount()
    {
        return mrXs.size();
    }


    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        ByteArrayOutputStream subBuffers[] = new ByteArrayOutputStream[mrXs.size()];
        for (int i=0; i<mrXs.size(); i++)
            subBuffers[i] = mrXs.get(i).save();

        buffer.write(ByteBuffer.allocate(4).putInt(mrXs.size()).array(),0,4);
        if (mrXs.size()>0)
            buffer.write(ByteBuffer.allocate(4).putInt(subBuffers[0].size()).array(),0,4);

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
        if (buffer.available()<4)
            return;

        byte bytesInt[] = new byte[4];
        buffer.read(bytesInt,0,4);
        Integer numX = ByteBuffer.wrap(bytesInt).getInt();

        if (numX==0)
            mrXs.clear();

        if (buffer.available()<4)
            return;

        Integer stride = 0;
        if (numX>0)
        {
            buffer.read(bytesInt,0,4);
            stride = ByteBuffer.wrap(bytesInt).getInt();
        }

        if (buffer.available()<stride*numX)
            return;

        byte bytesData[] = new byte[stride*numX];

        mrXs.clear();

        for (int i=0; i<numX; i++)
        {
            buffer.read(bytesData,0,stride);
            MrX mrX = new MrX(-1);
            mrX.load(new ByteArrayInputStream(bytesData,0,stride));
            mrXs.add(mrX);
        }
    }
}
