import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by devsh on 10/03/14.
 */
public class DetectiveManager implements SerializableSY
{
    private ArrayList<Detective> detectives;

    public DetectiveManager(int numDets)
    {
        detectives  = new ArrayList<Detective>();
        for (int i=0; i<numDets; i++)
            detectives.add(new Detective(i));
    }

    public boolean isVisible(Integer playerID)
    {
        return detectives.get(playerID).getNodePosLog().size()>0;
    }

    public void newGameInit()
    {
        for (Detective detective : detectives) detective.newGameInit();
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
        for (Detective detective : detectives) ids.add(detective.getId());
        return ids;
    }

    /**
     * Function to retireve the current location of a player. The location is an id of a graph node
     * @param playerId The id of the player
     * @return The node id that the player is on
     */
    public Integer getNodeId(Integer playerId)
    {
        return detectives.get(playerId).getNodePosition();
    }

    public Integer getDetectiveCount()
    {
        return detectives.size();
    }

    public void setPlayerPosition(Integer playerId,Integer nodeId, Initialisable.TicketType type)
    {
        detectives.get(playerId).setNodePosition(nodeId);
        detectives.get(playerId).logTicket(type);
    }
    public void initPlayerPosition(Integer playerId,Integer nodeId)
    {
        detectives.get(playerId).setNodePosition(nodeId);
    }


    public Integer getNumberOfTickets(Initialisable.TicketType type, Integer playerId)
    {
        return detectives.get(playerId).getNumberOfTickets(type);
    }

    public void giveTicket(Initialisable.TicketType type, Integer playerId)
    {
        detectives.get(playerId).giveTicket(type);
    }

    public void useTicket(Initialisable.TicketType type, Integer playerId)
    {
        detectives.get(playerId).useTicket(type);
    }

    public List<Initialisable.TicketType> getMoveList(Integer playerId)
    {
        return detectives.get(playerId).getTicketLog();
    }

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        ByteArrayOutputStream subBuffers[] = new ByteArrayOutputStream[detectives.size()];
        for (int i=0; i<detectives.size(); i++)
            subBuffers[i] = detectives.get(i).save();

        buffer.write(ByteBuffer.allocate(4).putInt(detectives.size()).array(),0,4);
        if (detectives.size()>0)
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
        Integer numDetectives = ByteBuffer.wrap(bytesInt).getInt();

        if (numDetectives==0)
            detectives.clear();

        if (buffer.available()<4)
            return;

        Integer stride = 0;
        if (numDetectives>0)
        {
            buffer.read(bytesInt,0,4);
            stride = ByteBuffer.wrap(bytesInt).getInt();
        }

        if (buffer.available()<stride*numDetectives)
            return;

        detectives.clear();

        byte bytesData[] = new byte[stride*numDetectives];

        for (int i=0; i<numDetectives; i++)
        {
            buffer.read(bytesData,0,stride);
            Detective detective = new Detective(-1);
            detective.load(new ByteArrayInputStream(bytesData,0,stride));
            detectives.add(detective);
        }
    }
}
