import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by devsh on 15/03/14.
 */
public class Player implements SerializableSY
{
    protected Integer []tickets = {0,0,0,0,0};
    protected Integer id;
    private ArrayList<Integer> nodePosLog;
    private ArrayList<Initialisable.TicketType> ticketLog;

    public void init(Integer ID_in)
    {
        nodePosLog = new ArrayList<Integer>();
        ticketLog = new ArrayList<Initialisable.TicketType>();
        id = ID_in;

        for (int i=0; i<5; i++)
            tickets[i] = 0;
    }

    public void clearLogsAndTickets()
    {
        nodePosLog.clear();
        ticketLog.clear();

        for (int i=0; i<5; i++)
            tickets[i] = 0;
    }

    public Integer getNumberOfTickets(Initialisable.TicketType type)
    {
        return tickets[getNumericEnum(type)];
    }

    public static int getNumericEnum(Initialisable.TicketType type)// why, in C++ I wouldnt have to do this shit
    {
        switch(type)
        {
            case Bus:
                return 0;
            case Taxi:
                return 1;
            case Underground:
                return 2;
            case DoubleMove:
                return 3;
            case SecretMove:
                return 4;
        }
        return -1;
    }

    public static Initialisable.TicketType getTicketEnumFromNum(int num)
    {
        switch (num)
        {
            case 0:
                return Initialisable.TicketType.Bus;
            case 1:
                return Initialisable.TicketType.Taxi;
            case 2:
                return Initialisable.TicketType.Underground;
            case 3:
                return Initialisable.TicketType.DoubleMove;
            case 4:
                return Initialisable.TicketType.SecretMove;
            default:
                return null;
        }
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

    public ArrayList<Initialisable.TicketType> getTicketLog() {return ticketLog;}


    // no validation takes place in this class if tickets can be used or if node can be moved
    public void setNodePosition(Integer newPos)
    {
        nodePosLog.add(newPos);
    }
    public void logTicket(Initialisable.TicketType type)
    {
        ticketLog.add(type);
    }

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write(ByteBuffer.allocate(4).putInt(id).array(),0,4);

        for (int i=0; i<5; i++)
        {
            buffer.write(ByteBuffer.allocate(4).putInt(tickets[i]).array(),0,4);
        }


        buffer.write(ByteBuffer.allocate(4).putInt(nodePosLog.size()).array(),0,4);
        buffer.write(ByteBuffer.allocate(4).putInt(ticketLog.size()).array(),0,4);

        for (Integer pos : nodePosLog)
            buffer.write(ByteBuffer.allocate(4).putInt(pos).array(),0,4);
        for (Initialisable.TicketType type : ticketLog)
            buffer.write(ByteBuffer.allocate(4).putInt(getNumericEnum(type)).array(),0,4);

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        if (buffer.available()<32)
            return;

        byte bytesInt[] = new byte[8];
        buffer.read(bytesInt,0,4);
        Integer idTmp = ByteBuffer.wrap(bytesInt).getInt();

        Integer []tmpTickets = {0,0,0,0,0};

        for (int i=0; i<5; i++)
        {
            buffer.read(bytesInt,0,4);
            tmpTickets[i] = ByteBuffer.wrap(bytesInt).getInt();
        }

        buffer.read(bytesInt,0,4);
        Integer pastMoves = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesInt,0,4);
        Integer usedTickets = ByteBuffer.wrap(bytesInt).getInt();

        if (buffer.available()<pastMoves*4+usedTickets*4)
            return;

        nodePosLog.clear();
        ticketLog.clear();

        for (int i=0; i<pastMoves; i++)
        {
            buffer.read(bytesInt,0,4);
            Integer tmpInt = ByteBuffer.wrap(bytesInt).getInt();
            nodePosLog.add(tmpInt);
        }

        for (int i=0; i<usedTickets; i++)
        {
            buffer.read(bytesInt,0,4);
            Integer tmpInt = ByteBuffer.wrap(bytesInt).getInt();
            ticketLog.add(getTicketEnumFromNum(tmpInt));
        }

        id = idTmp;
        for (int i=0; i<5; i++)
            tickets[i] = tmpTickets[i];
    }
}
