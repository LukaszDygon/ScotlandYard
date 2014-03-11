import java.nio.ByteBuffer;
import java.io.*;

/**
 * Created by devsh on 10/03/14.
 */
public interface SerializableSY
{
    public ByteArrayOutputStream save();
    public void load(ByteArrayInputStream buffer);
}
