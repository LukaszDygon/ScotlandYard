import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by devsh on 10/03/14.
 */
public class Detective implements SerializableSY
{
    private Integer id;
    private Integer nodePos;
    private Integer taxiTickets;
    private Integer busTickets;
    private Integer tubeTickets;

    public Detective(Integer ID_in)
    {
        id = ID_in;
        nodePos = -1;
        taxiTickets = 0;
        busTickets = 0;
        tubeTickets = 0;
    }

    public void newGameInit()
    {
        nodePos = -1; //common value used to mean INVALID when 0 cant be used

        taxiTickets = 11;
        busTickets = 8;
        tubeTickets = 4;
    }

    public Integer getId() {return id;}
    public Integer getNodePosition() {return nodePos;}
    // no validation takes place in this class if tickets can be used or if node can be moved
    public void setNodePosition(Integer newPos) {nodePos = newPos;}

    public Integer getTaxiTickets() {return taxiTickets;}
    public void useTaxiTicket() {taxiTickets--;}

    public Integer getBusTickets() {return busTickets;}
    public void useBusTickets() {busTickets--;}

    public Integer getTubeTickets() {return tubeTickets;}
    public void useTubeTickets() {tubeTickets--;}

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write(ByteBuffer.allocateDirect(4).putInt(id).array(),0,4);

        buffer.write(ByteBuffer.allocateDirect(4).putInt(nodePos).array(),0,4);
        buffer.write(ByteBuffer.allocateDirect(4).putInt(taxiTickets).array(),0,4);
        buffer.write(ByteBuffer.allocateDirect(4).putInt(busTickets).array(),0,4);
        buffer.write(ByteBuffer.allocateDirect(4).putInt(tubeTickets).array(),0,4);

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        byte bytesInt[] = new byte[8];
        buffer.read(bytesInt,0,4);
        id = ByteBuffer.wrap(bytesInt).getInt();


        buffer.read(bytesInt,0,4);
        nodePos = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        taxiTickets = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        busTickets = ByteBuffer.wrap(bytesInt).getInt();

        buffer.read(bytesInt,0,4);
        tubeTickets = ByteBuffer.wrap(bytesInt).getInt();
    }
}
