package project1;
import java.util.*;

/**
 *
 * @author
 */

//The Pod class controls the movement, the location, and the visibility of a Pod.
//This class also holds the evasive manuever for the Pod.
public class Pod {
    //Member Variables
    private int X; //x location on board
    private int Y; //y location on board
    private String direction = new String( ); //direction of the pod
    private boolean isvisible = true; //is the pod visible on the game board
    private final int WIDTH; //made constant width
    private final int HEIGHT; //made constant height
    private boolean hasEvasive = false; //check pod for evasive maneuver

  //Constructor- takes the location of the pod, the direction the pod is going,
  // and the board size as parameters. Initializes the appropriate member variables.
    public Pod(int x, int y, String direction, int width, int height) {
        X = x;
        Y = y;
        this.direction = direction;
        WIDTH = width;
        HEIGHT = height;

    }
    //Required Methods
    //Inspectors

    //returns the x location of the pod on the board
    public int getX( ) {
      return X;
    }

    //returns the y location of the pod on the board
    public int getY( ) {
      return Y;
    }

    //returns the appropriate boolean value, whether the pod is visible on the game board
    public boolean isVisible( ) {
       return isvisible;
    }

    //Move Method
    //moves the pod in the appropriate direction and allows for the pod to bounce off of walls.
    public void move( ) {
        switch(direction) {
            case "NE": //If the pod is going NE then it will go NE unless it hits a wall.
                       //The pod will change to SE if a north wall occurs. The pod will change to SW
                       //if the northern and eastern walls occur.
                       //The pod will change to NW if the eastern wall occurs.
                    if(Y+1 < HEIGHT) {
                        Y++;
                    }
                    else {
                        direction = "SE";
                        Y--;
                    }
                    if(X+1 < WIDTH ) {
                        X++;
                        break;
                    }
                    else {
                        if (direction.equals("SE")) direction = "SW";
                        else direction = "NW";
                        X--;
                        break;
                    }
            case "NW"://If the pod is going NW then it will go NW unless it hits a wall.
                       //The pod will change to SW if a north wall occurs. The pod will change to SE
                       //if the northern and western walls occur.
                       //The pod will change to NE if the western wall occurs.
                    if(Y+1 < HEIGHT) {
                        Y++;
                    }
                    else {
                        direction = "SW";
                        Y--;
                    }
                    if(X > 0 ) {
                        X--;
                        break;
                    }
                    else {
                        if (direction.equals("SW")) direction = "SE";
                        else direction = "NE";
                        X++;
                        break;
                    }
            case "SE": //If the pod is going SE then it will go SE unless it hits a wall.
                       //The pod will change to NE if a southern wall occurs. The pod will change to NW
                       //if the southern and eastern walls occur.
                       //The pod will change to SW if the eastern wall occurs.
                    if(Y > 0) {
                        Y--;
                    }
                    else {
                        direction = "NE";
                        Y++;
                    }
                    if(X+1 < WIDTH ) {
                        X++;
                        break;
                    }
                    else {
                        if (direction.equals("NE")) direction = "NW";
                        else direction = "SW";
                        X--;
                        break;
                    }
            case "SW": //If the pod is going SW then it will go SW unless it hits a wall.
                       //The pod will change to NW if a southern wall occurs. The pod will change to NE
                       //if the southern and western walls occur.
                       //The pod will change to SE if the western wall occurs.
                  if(Y > 0 ) {
                    Y--;
                  }
                  else {
                    direction = "NW";
                    Y++;
                  }
                  if(X > 0 ) {
                    X--;
                    break;
                  }
                  else {
                    if (direction.equals("NW")) direction = "NE";
                    else direction = "SE";
                    X++;
                    break;
                }
        }

    }
    //Caught
    /*If the pod has not used its Evasive maneuver and the player is within a distance of 1 of the pod,
    then the pod will teleport to a random spot at least a distance of 3 from the player.
    Otherwise, if the player is at the same spot as the pod, the pod will be captured and will no
    longer be visible on the screen.*/
    public void playerAt(int x, int y) {
        Random rand = new Random( );
        if (hasEvasive == false && isClose(x,y)) {
            do {
               X = rand.nextInt(WIDTH);
               Y = rand.nextInt(HEIGHT);
            }
            while (Math.sqrt((X-x)* (X-x) + (Y-y)*(Y-y)) <= 3);
            hasEvasive = true;

        }
        if (X == x && Y == y ) {
            isvisible = false;
        }
    }
    //Extra Methods
    //Will return the appropriate boolean value whether the player is within a distance
    //of 1 of the pod.
    public boolean isClose(int x, int y) {
        boolean isclose = false;
        if (Math.sqrt((X-x)* (X-x) + (Y-y)*(Y-y)) <= 1) isclose = true;
        return isclose;
    }
    //Unit Testing
    //Will return a string list of all of the member variables and their values.
    public String toString( ) {
        String MemberVar = new String("X: ");
        MemberVar += X;
        MemberVar += " Y: " + Y;
        MemberVar += " Direction: " + direction;
        MemberVar += " isVisible: " + isvisible;
        MemberVar += " hasEvasive: " + hasEvasive;
        MemberVar += " Width: " + WIDTH;
        MemberVar += " Height: " + HEIGHT;
        return MemberVar;
    }

    //This main is a test of the location/direction of the pod during 50 calls to move.
    public static void main(String[] args) {
      Pod p = new Pod( 1, 2, "NE", 15, 9); //creates a pod object
      for (int i = 0; i < 50; i++){ //loops 50 times
          p.move( ); //moves the pod 50 times
          System.out.println(p.toString( )); //prints the member variables at every move
      }
    }
}
