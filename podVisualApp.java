/*
 * This class represents a visual application for the "pod chase" game.
 */
package PodGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project1 extends JFrame implements ActionListener {

    // Member variables for visual objects.
    private JLabel[][] board; // 2D array of labels. Displays either # for player,
                              // * for pod, or empty space
    private JButton northButton, // player presses to move up
                    southButton, // player presses to move down
                    eastButton,  // player presses to move right
                    westButton;  // player presses to move left
    
    // Current width and height of board (will make static later).
    private int width = 15;
    private int height = 9;
    
    // Current location of player
    private int playerX = 7;
    private int playerY = 4;
    
    // Pod object stored in array for efficiency
    private Pod[] pods;
    int podCount = 4;
    
    public Project1() {
        
        // Construct a panel to put the board on and another for the buttons
        JPanel boardPanel = new JPanel(new GridLayout(height, width));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        
        // Use a loop to construct the array of labels, adding each to the
        // board panel as it is constructed. Note that we create this in
        // "row major" fashion by making the y-coordinate the major 
        // coordinate. We also make sure that increasing y means going "up"
        // by building the rows in revers order.
        board = new JLabel[height][width];
        for (int y = height-1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                
                // Construct a label to represent the tile at (x, y)
                board[y][x] = new JLabel(" ", JLabel.CENTER);
                
                // Add it to the 2D array of labels representing the visible board
                boardPanel.add(board[y][x]);
            }
        }
        
        // Construct the buttons, register to listen for their events,
        // and add them to the button panel
        northButton = new JButton("N");
        southButton = new JButton("S");
        eastButton = new JButton("E");
        westButton = new JButton("W");
        
        // Listen for events on each button
        northButton.addActionListener(this);
        southButton.addActionListener(this);
        eastButton.addActionListener(this);
        westButton.addActionListener(this);
        
        // Add each to the panel of buttons
        buttonPanel.add(northButton); 
        buttonPanel.add(southButton); 
        buttonPanel.add(eastButton); 
        buttonPanel.add(westButton);
        
        // Add everything to a main panel attached to the content pane
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Size the app and make it visible
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Auxiliary method to create game setup
        createGame();
    }
    
    // Auxiliary method used to create board. Sets player, treasure, and walls.
    public void createGame() {
        
        // Construct array of Pod objects
        pods = new Pod[podCount];
        
        // Construct each Pod in the array, passing it its initial location,
        // direction of movement, and the width and heigh of board. This will
        // later be modified to be done at random.
        pods[0] = new Pod(1, 5, "NE", width, height);
        pods[1] = new Pod(2, 1, "SW", width, height);
        pods[2] = new Pod(12, 2, "NW", width, height);
        pods[3] = new Pod(13, 6, "SE", width, height);
        
        // Call method to draw board
        drawBoard();
        
    }
    
    // Auxiliary method to display player and pods in labels.
    public void drawBoard() {
        
        // "Erase" previous board by writing " " in each label
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x].setText(" ");
            }
        }
        
        // Get location of each pod and write * into that label. We only
        // do this for pods not yet caught.
        for (int p = 0; p < podCount; p++) {
            if (pods[p].isVisible()) {
                board[pods[p].getY()][pods[p].getX()].setText("*");
            }
        }
        
        // Write the player onto the board.
        board[playerY][playerX].setText("#");
    }
    
    
    public void actionPerformed(ActionEvent e) {
        
        // Determine which button was pressed, and move player in that
        // direction (making sure they don't leave the board).
        if (e.getSource() == southButton && playerY > 0) {
            playerY--;
        }
        if (e.getSource() == northButton && playerY < height-1) {
            playerY++;
        }
        if (e.getSource() == eastButton && playerX < width-1) {
            playerX++;
        }
        if (e.getSource() == westButton && playerX > 0) {
            playerX--;
        }
        
        // Move the pods and notify the pods about player location.        
        for (int p = 0; p < podCount; p++) {
            pods[p].move();
            pods[p].playerAt(playerX, playerY);
        }
        
        // Redraw the board
        drawBoard();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Project1 a = new Project1();
    }
}
