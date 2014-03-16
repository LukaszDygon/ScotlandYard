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

    public Vector4Di sub(Vector4Di other)
    {
        Vector4Di outRes = new Vector4Di();
        outRes.data[0] = data[0]-other.data[0];
        outRes.data[1] = data[1]-other.data[1];
        outRes.data[2] = data[2]-other.data[2];
        outRes.data[3] = data[3]-other.data[3];
        return outRes;
    }

    public int length()
    {
        return (int)lengthd();
    }

    public float lengthf()
    {
        return (float)lengthd();
    }

    public double lengthd()
    {
        double a = data[0]*data[0];
        double b = data[1]*data[1];
        double c = data[2]*data[2];
        double d = data[3]*data[3];
        return java.lang.Math.sqrt(a+b+c+d);
    }
}
