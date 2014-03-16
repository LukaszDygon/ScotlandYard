import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by devsh on 10/03/14.
 */
public class Detective extends Player
{
    public Detective(Integer ID_in)
    {
        init(ID_in);
    }

    public void newGameInit()
    {
        clearLogsAndTickets();

        tickets[getNumericEnum(Initialisable.TicketType.Taxi)] = 11;
        tickets[getNumericEnum(Initialisable.TicketType.Bus)] = 8;
        tickets[getNumericEnum(Initialisable.TicketType.Underground)] = 4;
    }
}
