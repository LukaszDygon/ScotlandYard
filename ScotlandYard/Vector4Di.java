/**
 * Created by devsh on 10/03/14.
 */
public class Vector4Di
{
    public int []data = new int[4];

    public Vector4Di()
    {
    }
    public Vector4Di(Vector4Di other)
    {
        set(other.data[0],other.data[1],other.data[2],other.data[3]);
    }
    public Vector4Di(int a, int b, int c, int d)
    {
        set(a,b,c,d);
    }

    public void set(int a, int b, int c, int d)
    {
        data[0] = a;
        data[1] = b;
        data[2] = c;
        data[3] = d;
    }
}
