import java.io.*;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements Controllable,Visualisable
{
	private int turnsLeft = 0;
	private Game game;
    private PlayerManager playerManager;
    private Map map;


    public GameState(Game game_in)
    {
        game = game_in;
        playerManager = game.getPlayerManager();
        map = game.getMap();
    }
    
    public Boolean initialiseGame(Integer players)
    {
    	newGameInitialise(players);
    	return true;
    }

	public void newGameInitialise(int players)
	{
        //reset player objects
        playerManager.newGameInit();


		turnsLeft = 24;


		generateStartPosition();
		
	}

    public int getNumberOfTurnsLeft() {return turnsLeft;}
	
	private void generateStartPosition()
	{
        int nodesInGraph = map.getGraph().nodeNumber();
        List<Integer> mrXs = playerManager.getMrXIdList();
        List<Integer> detectives = playerManager.getDetectiveIdList();

        ArrayList<Integer> taken =  new ArrayList<Integer>();

        for (Integer mrXId : mrXs)
        {
            int position = (int)(Math.random() * (nodesInGraph-0.00001));
            nodesInGraph--;
            for (Integer j : taken)
            {
                if (j<=position)
                    position++;
            }

            int nodeId = map.getGraph().nodes().get(position).name();
            playerManager.setPlayerPosition(mrXId, nodeId);
            taken.add(position);
            Collections.sort(taken);
        }

        for (Integer detectiveId : detectives)
        {
            int position = (int)(Math.random() * (nodesInGraph-0.00001));
            nodesInGraph--;
            for (Integer j : taken)
            {
                if (j<=position)
                    position++;
            }

            int nodeId = map.getGraph().nodes().get(position).name();
            playerManager.setPlayerPosition(detectiveId, nodeId);
            taken.add(position);
            Collections.sort(taken);
        }
	}


    /**
     * Function that will cause a player to move given a player id, a target position and a type of ticket.
     * @param playerId This is the id of the player that is going to be moved.
     * @param targetNodeId This is the position or nodeId that you wish the player to move to
     * @param ticketType The type of ticket that is being used to move the player.
     * @return True if the player is able to move to the target using the given ticket type. False if not.
     * the reasons that they may not be able to move are that the target is too far away or the player, the ticket is
     * not the correct type to make that move, or the player does not have any of that ticket left.
     */
    public Boolean movePlayer(Integer playerId, Integer targetNodeId, TicketType ticketType)
    {
        return false; // NOT DONE
    }

    /**
     * Function that provides the Id of a node given a location. In this function you will need to
     * find the node whose location is closest to the location provided
     * @param xPosition The x position
     * @param yPosition The y position
     * @return The id of the node that is closest to the given position
     */
    public Integer getNodeIdFromLocation(Integer xPosition, Integer yPosition)
    {
        return -1; // NOT DONE
    }


    /**
     * Function that when called will save the game to a file
     * @param filename The filename that you wish the game to be saved to
     * @return True if the file was saved OK. False if there was a problem saving the file
     */
    public Boolean saveGame(String filename)
    {
        try
        {
            FileOutputStream buffer = new FileOutputStream(filename);
            ByteArrayOutputStream subBuffers[] = {map.save(),playerManager.save()};


            buffer.write(ByteBuffer.allocate(4).putInt(turnsLeft).array(),0,4);

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

            return true;
        }
        catch (IOException ioe)
        {
            return false;
        }
    }

    /**
     * Function that when called will load the game state from a file
     * @param filename The filename that contains the saved game state
     * @return True if the game was loaded OK, false if there was a problem
     */
    public Boolean loadGame(String filename)
    {
        byte []buffer;
        try
        {
            FileInputStream loadFile = new FileInputStream(filename);
            buffer = new byte[loadFile.available()];
            loadFile.read(buffer,0,loadFile.available());
        }
        catch (IOException ioe)
        {
            return false;
        }

        ///if (buffer.length<12)
            ///return false;

        byte bytesInt[] = new byte[4];
        bytesInt[0] = buffer[0];
        bytesInt[1] = buffer[1];
        bytesInt[2] = buffer[2];
        bytesInt[3] = buffer[3];
        int turnsLeftTmp = ByteBuffer.wrap(bytesInt).getInt();

        Integer []size = new Integer[2];
        bytesInt[0] = buffer[0+4];
        bytesInt[1] = buffer[1+4];
        bytesInt[2] = buffer[2+4];
        bytesInt[3] = buffer[3+4];
        size[0] = ByteBuffer.wrap(bytesInt).getInt();
        bytesInt[0] = buffer[0+8];
        bytesInt[1] = buffer[1+8];
        bytesInt[2] = buffer[2+8];
        bytesInt[3] = buffer[3+8];
        size[1] = ByteBuffer.wrap(bytesInt).getInt();

        map.load(new ByteArrayInputStream(buffer,12,size[0]));
        playerManager.load(new ByteArrayInputStream(buffer,12+size[0],size[1]));

        turnsLeft = turnsLeftTmp;

        return true;
    }


    /**
     * Function to retrieve the number of tickets of a certain type held by a player
     * @param type The type of ticket you are checking for
     * @param playerId The id of the player you are looking up
     * @return The number of tickets of that type that the player has
     */
    public Integer getNumberOfTickets(TicketType type, Integer playerId)
    {
        return playerManager.getNumberOfTickets(type,playerId);
    }


    /**
     * Function to retrieve the list of previous move types that a player has taken
     * @param playerId The id of player you are looking up
     * @return The list of moves that a player has made
     */
    public List<TicketType> getMoveList(Integer playerId)
    {
        return playerManager.getMoveList(playerId);
    }


    /**
     * Check if a player is visible. The detectives are going to be visible at all times, Mr X is not..
     * @param playerId The id of the player you are checking
     * @return If that player is visible
     */
    public Boolean isVisible(Integer playerId)
    {
        return playerManager.isVisible(playerId);
    }

    /**
     * Function to check if the game is over
     * @return True if the game is over, false if not
     */
    public Boolean isGameOver() // NOT DONE
    {
        if (turnsLeft<=0)
            return true;
        else
        {
            boolean over = true;
            List<Integer> mrXes = playerManager.getMrXIdList();

            for (int i=0; i<mrXes.size()&&over; i++)
            {
                ///if ()
                //{
                    over = false;
                //}
            }

            if (!over)
            {
                //check if detectives can't move
                return false;
            }
            return over;
        }
    }

    /**
     * Function to find our who is the next player to move. i.e. whos go it is
     * @return The id of the player whos go it is
     */
    public Integer getNextPlayerToMove()
    {
        return -1; // NOT DONE
    }

    /**
     * Function to get the id of the winning player (if the game has been won)
     * @return The id of the winning player
     */
    public Integer getWinningPlayerId()
    {
        return -1; // NOT DONE
    }




    public String getMapFilename() {return map.getMapFilename();}

    public Integer getLocationX(Integer nodeId)
    {
        try {return map.getNodePos(nodeId).data[0];}
        catch(NullPointerException e)
        {
            return 0;
        }
    }
    public Integer getLocationY(Integer nodeId)
    {
        try {return map.getNodePos(nodeId).data[1];}
        catch(NullPointerException e)
        {
            return 0;
        }
    }

    public List<Integer> getDetectiveIdList() {return playerManager.getDetectiveIdList();}
    public List<Integer> getMrXIdList() {return playerManager.getMrXIdList();}
    public Integer getNodeId(Integer playerId) {return playerManager.getNodeId(playerId);}

}
