/**
 * Created by devsh on 10/03/14.
 */
public class Vector4D<T>
{
    public T[] data;

    public Vector4D()
    {
        data = (T[])(new Object[4]); //heheh I'm casting spells
    }
    public Vector4D(Vector4D<T> other)
    {
        data = (T[])(new Object[4]); //heheh I'm casting spells
        set(other.data[0],other.data[1],other.data[2],other.data[3]);
    }
    public Vector4D(T a, T b, T c, T d)
    {
        data = (T[])(new Object[4]); //heheh I'm casting spells
        set(a,b,c,d);
    }

    public void set(T a, T b, T c, T d)
    {
        data[0] = a;
        data[1] = b;
        data[2] = c;
        data[3] = d;
    }
}
