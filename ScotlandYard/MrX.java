import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by devsh on 10/03/14.
 */
public class MrX extends Player
{

    public MrX(Integer ID_in)
    {
        init(ID_in);
    }

    public void newGameInit()
    {
        clearLogsAndTickets();

        tickets[getNumericEnum(Initialisable.TicketType.Bus)] = 24;
        tickets[getNumericEnum(Initialisable.TicketType.Taxi)] = 24;
        tickets[getNumericEnum(Initialisable.TicketType.Underground)] = 24;
        tickets[getNumericEnum(Initialisable.TicketType.DoubleMove)] = 2;
        tickets[getNumericEnum(Initialisable.TicketType.SecretMove)] = 4;
    }
}
