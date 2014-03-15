import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements SerializableSY
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
            playerManager.setPlayerPosition(mrXId, position);
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
            playerManager.setPlayerPosition(detectiveId,position);
            taken.add(position);
            Collections.sort(taken);
        }
	}


    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ///ByteArrayOutputStream subBuffers[] = {playerManager.save()};

        buffer.write(ByteBuffer.allocateDirect(4).putInt(turnsLeft).array(),0,4);
/*
        for (ByteArrayOutputStream subBuffer : subBuffers)
        {
            Integer size = subBuffer.size();
            buffer.write(ByteBuffer.allocateDirect(4).putInt(size).array(),0,4);
            buffer.write(subBuffer.toByteArray(),0,size);
        }*/

        return buffer;

    }
        // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        byte bytesInt[] = new byte[4];
        buffer.read(bytesInt,0,4);
        turnsLeft = ByteBuffer.wrap(bytesInt).getInt();/*

        byte bytesData[] = new byte[512];
        buffer.read(bytesInt,0,4);
        Integer size = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesData,0,size);
        playerManager.load(new ByteArrayInputStream(bytesData));*/
    }
}
