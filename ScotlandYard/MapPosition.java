import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MapPosition {
	Integer[] xCoordinates;
	Integer[] yCoordinates;
	
	public void read(String filename) throws IOException
    {

        // load the file
        File file = new File(filename);
        Scanner in = new Scanner(file);

        // get the top line

        String[] topLine = in.nextLine().split(" ");

        Integer numberOfNodes = Integer.parseInt(topLine[0]);
        Integer[] xCoordinates = new Integer[numberOfNodes];
        Integer[] yCoordinates = new Integer[numberOfNodes];

        while (in.hasNextLine())
        {
            String line = in.nextLine();

            String[] names = line.split(" ");
            int node = Integer.parseInt(names[0]);
            int xCoordinate = Integer.parseInt(names[1]);
            int yCoordinate = Integer.parseInt(names[2]);
            xCoordinates[node] = xCoordinate;
            yCoordinates[node] = yCoordinate;
        }
        in.close();
    }
	
	public void changeCoordinates(Integer x, Integer y, Integer newNode)
	{
		x = xCoordinates[newNode];
		y = yCoordinates[newNode];
	}

}

