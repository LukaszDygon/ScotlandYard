import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by devsh on 10/03/14.
 */

public class PlayerManager implements SerializableSY
{
    private DetectiveManager detectiveManager;
    private MrXManager mrXManager;

    public PlayerManager()
    {
    }

    public void init(Integer numberOfDetectives,GameState gameState)
    {
        detectiveManager = new DetectiveManager(numberOfDetectives);
        mrXManager = new MrXManager(1,numberOfDetectives,gameState);
    }
    
    public void newGameInit()
    {
        detectiveManager.newGameInit();
        mrXManager.newGameInit();
    }

    /**
     * Function to return a list of detective IDS from the game. These IDs will
     * be unique identifiers given to the player
     * @return The list of detective IDs
     */
    public List<Integer> getDetectiveIdList()
    {
        return detectiveManager.getIdList();
    }

    /**
     * Function to get the list of Mr X IDs. In this version of the game you will really only
     * have one Mr X so this function will return a list with only one entry. However this
     * may change down the line...
     * @return The list of Mr X IDs
     */
    public List<Integer> getMrXIdList()
    {
        return mrXManager.getIdList();
    }

    /**
     * Function to retireve the current location of a player. The location is an id of a graph node
     * @param playerId The id of the player
     * @return The node id that the player is on
     */
    public Integer getNodeId(Integer playerId)
    {
        if (playerId<detectiveManager.getDetectiveCount())
        {
            return detectiveManager.getNodeId(playerId);
        }
        else
            return mrXManager.getNodeId(playerId);
    }

    public void setPlayerPosition(Integer playerId,Integer nodeId)
    {
        //some validation here


        if (playerId<detectiveManager.getDetectiveCount())
        {
            detectiveManager.setPlayerPosition(playerId,nodeId);
        }
        else
            mrXManager.setPlayerPosition(playerId,nodeId);
    }

    public Integer getNumberOfTickets(Initialisable.TicketType type, Integer playerId)
    {
        if (playerId<detectiveManager.getDetectiveCount())
            return detectiveManager.getNumberOfTickets(type,playerId);
        else
            return mrXManager.getNumberOfTickets(type, playerId);
    }

    public List<Initialisable.TicketType> getMoveList(Integer playerId)
    {
        if (playerId<detectiveManager.getDetectiveCount())
            return detectiveManager.getMoveList(playerId);
        else
            return mrXManager.getMoveList(playerId);
    }


    public Boolean isVisible(Integer playerId)
    {
        if (playerId<detectiveManager.getDetectiveCount())
        {
            return true;
        }
        else
            return mrXManager.isVisible(playerId);
    }

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ByteArrayOutputStream subBuffers[] = {mrXManager.save(), detectiveManager.save()};

        for (ByteArrayOutputStream subBuffer : subBuffers)
        {
            Integer size = subBuffer.size();
            buffer.write(ByteBuffer.allocate(4).putInt(size).array(),0,4);
        }

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

        Integer []size = new Integer[2];
        byte bytesInt[] = new byte[4];
        buffer.read(bytesInt,0,4);
        size[0] = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesInt,0,4);
        size[1] = ByteBuffer.wrap(bytesInt).getInt();


        if (buffer.available()<(size[0]+size[1]))
            return;

        byte bytesData[] = new byte[size[0]+size[1]];
        buffer.read(bytesData,0,size[0]);
        mrXManager.load(new ByteArrayInputStream(bytesData,0,size[0]));


        buffer.read(bytesData,0,size[1]);
        detectiveManager.load(new ByteArrayInputStream(bytesData,0,size[1]));
    }
}
