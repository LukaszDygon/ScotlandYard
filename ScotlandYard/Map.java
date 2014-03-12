import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Map implements MapVisualisable, SerializableSY
{
    /**
     * Vairable that will hold the filename for the map
     */
    private String mapFilename;
    private Graph graph;

    public Map(String mapGraphFilename, String nodePosFilename, String mapImageFilename)
    {
        mapFilename = mapImageFilename;

        Reader reader = new Reader();
        try
        {
            reader.read(mapGraphFilename);
        }
        catch (IOException e)
        {
            Game.fail("Bad Graph File!.");
        }

        graph = reader.graph();



        File file = new File(nodePosFilename);
        Scanner in = null;
        try
        {
            in = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            Game.fail("Bad node positions file.");
        }

        // get the top line

        String[] topLine = in.nextLine().split(" ");


        while (in.hasNextLine())
        {
            String line = in.nextLine();

            String[] nameAndCoord = line.split(" ");

            Node to_set_pos_node = null;

            for (Node node : graph.nodes())
            {
                if (Integer.parseInt(nameAndCoord[0])==node.name())
                {
                    to_set_pos_node = node;
                    break;
                }
            }

            if (to_set_pos_node!=null)
                to_set_pos_node.setPos(new Vector4Di(Integer.parseInt(nameAndCoord[1]),Integer.parseInt(nameAndCoord[2]),0,0));
        }

        in.close();
    }


    public Graph getGraph() {return graph;}

    public Vector4Di getNodePos(Integer nodeId)
    {
        Node node = binarySearchNode(graph.nodes(),new Node(nodeId));
        return node.getPos();
    }

    /**
     * Concrete implementation of the MapVisualisable getMapFilename function
     * @return The map filename
     */
    public String getMapFilename()
    {
        return mapFilename;
    }


    private static Node binarySearchNode(List<Node> nodes,Node node)
    {
        int start = 0;
        int end = nodes.size()-1;
        if (start>end)
        {
            return null;
        }

        Node node1 = nodes.get(start);
        int cmp = node1.name().compareTo(node.name());
        if (cmp>0)
        {
            return null;
        }
        else if (cmp==0)
        {
            return node1;
        }
        node1 = nodes.get(end);
        cmp = node1.name().compareTo(node.name());
        if (cmp==0)
        {
            return node1;
        }
        else if (cmp<0)
        {
            return null;
        }

        while (start<=end)
        {
            int middle = (start+end)/2;
            node1 = nodes.get(middle);
            cmp = node1.name().compareTo(node.name());
            if (cmp>0) // node is below middle
            {
                end = middle-1;
            }
            else if (cmp==0)
            {
                return node1;
            }
            else
            {
                start = middle+1;
            }
        }

        return null;
    }

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ByteArrayOutputStream subBuffers[] = {graph.save()};

        for (ByteArrayOutputStream subBuffer : subBuffers)
        {
            Integer size = subBuffer.size();
            buffer.write(ByteBuffer.allocateDirect(4).putInt(size).array(),0,4);
            buffer.write(subBuffer.toByteArray(),0,size);
        }

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        byte bytesInt[] = new byte[4];
        buffer.read(bytesInt,0,4);
        Integer size = ByteBuffer.wrap(bytesInt).getInt();

        byte bytesData[] = new byte[4096];
        buffer.read(bytesData,0,size);
        graph.load(new ByteArrayInputStream(bytesData,0,size));
    }
}
