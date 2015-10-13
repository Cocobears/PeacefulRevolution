

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static JPanel startUpScreen;
    static JButton ok = new JButton("OK");
    static LinkedList<String> identities;
    static JComboBox numberOfPlayers = new JComboBox();
    static LinkedList<PlayerData> playerData = new LinkedList<PlayerData>();
    static JFrame mainFrame = new JFrame();
    static JPanel[][] cityStreets = new JPanel[9][5];
    static JLabel[][] street = new JLabel[3][3];

    public static void main(String[] args) {
        GameData game = new GameData();
        //define panels for the citymap
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 5; y++) {
                cityStreets[x][y] = new JPanel();
            }
        }
        //create the streets
        int TESTADDRESS = (int) (Math.random() * 45);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 5; y++) {
                Color n;
                street[0][0] = new JLabel(game.routeShape[TESTADDRESS].substring(0, 1));

                System.out.println("streetbit1:" + game.routeShape[TESTADDRESS].substring(0, 1)+":");
                System.out.println("streetbit2:"+game.routeShape[TESTADDRESS].substring(1, 2)+":");
                if (game.routeShape[TESTADDRESS].substring(0, 1).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }
                street[0][0].setOpaque(true);
                street[0][0].setBackground(n);
                street[0][1] = new JLabel(game.routeShape[TESTADDRESS].substring(1, 2));
                if (game.routeShape[TESTADDRESS].substring(1, 2).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }
                street[0][1].setOpaque(true);
                street[0][1].setBackground(n);
                street[0][2] = new JLabel(game.routeShape[TESTADDRESS].substring(2, 3));
                if (game.routeShape[TESTADDRESS].substring(2, 3).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }
                street[0][2].setOpaque(true);
                street[0][2].setBackground(n);
                street[1][0] = new JLabel(game.routeShape[TESTADDRESS].substring(3, 4));
                if (game.routeShape[TESTADDRESS].substring(3, 4).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }
                street[1][0].setOpaque(true);
                street[1][0].setBackground(n);
                street[1][1] = new JLabel(game.routeShape[TESTADDRESS].substring(4, 5));
                if (game.routeShape[TESTADDRESS].substring(4, 5).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }street[1][1].setOpaque(true);
                street[1][1].setBackground(n);
                street[1][2] = new JLabel(game.routeShape[TESTADDRESS].substring(5, 6));
                if (game.routeShape[TESTADDRESS].substring(5, 6).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }
                street[1][2].setOpaque(true);
                street[1][2].setBackground(n);
                street[2][0] = new JLabel(game.routeShape[TESTADDRESS].substring(6, 7));
                if (game.routeShape[TESTADDRESS].substring(6, 7).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }
                street[2][0].setOpaque(true);
                street[2][0].setBackground(n);
                street[2][1] = new JLabel(game.routeShape[TESTADDRESS].substring(7, 8));
                if (game.routeShape[TESTADDRESS].substring(7, 8).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }street[2][1].setOpaque(true);
                street[2][1].setBackground(n);
                street[2][2] = new JLabel(game.routeShape[TESTADDRESS].substring(8, 9));
                if (game.routeShape[TESTADDRESS].substring(8, 9).equals(" ")) n = Color.BLACK;
                else {
                    n = Color.red;
                }
                street[2][2].setOpaque(true);
                street[2][2].setBackground(n);
                street[0][0].setPreferredSize(new Dimension(35, 35));
                street[0][1].setPreferredSize(new Dimension(35,35));
                street[0][2].setPreferredSize(new Dimension(35, 35));
                street[1][0].setPreferredSize(new Dimension(35,35));
                street[1][1].setPreferredSize(new Dimension(35, 35));
                street[1][2].setPreferredSize(new Dimension(35,35));
                street[2][0].setPreferredSize(new Dimension(35, 35));
                street[2][1].setPreferredSize(new Dimension(35,35));
                street[2][2].setPreferredSize(new Dimension(35, 35));

                JPanel givenStreet = new JPanel();
                givenStreet.setLayout(new GridLayout(3,3));
                givenStreet.add(street[0][0]);
                givenStreet.add(street[0][1]);
                givenStreet.add(street[0][2]);
                givenStreet.add(street[1][0]);
                givenStreet.add(street[1][1]);
                givenStreet.add(street[1][2]);
                givenStreet.add(street[2][0]);
                givenStreet.add(street[2][1]);
                givenStreet.add(street[2][2]);
                cityStreets[x][y].add(givenStreet);
            }
        }
        new Main();

    }

    public Main() {
        startUpScreen = new JPanel();
        display(startUpScreen);

    }

    public void display(JPanel startUpScreen) {

        startUpScreen.setLayout(new GridLayout(5, 2));
        JLabel name = new JLabel("Name : ");
        JTextField nameField = new JTextField();
        JLabel numberOfPlayersLabel = new JLabel("Players : ");
        String[] players = new String[]{"3", "4", "5", "6", "7", "8", "9", "10", "11"};
        numberOfPlayers = new JComboBox(players);

        OkListener okListener = new OkListener();
        ok.addActionListener(okListener);
        mainFrame.setSize(800, 600);
        startUpScreen.add(name);
        startUpScreen.add(nameField);
        startUpScreen.add(numberOfPlayersLabel);
        startUpScreen.add(numberOfPlayers);
        startUpScreen.add(ok);
        startUpScreen.setVisible(true);
        mainFrame.add(startUpScreen);
        mainFrame.pack();
        mainFrame.setVisible(true);


    }

    private void createPlayerData(int nofPlayers) {

        for (int i = 0; i < nofPlayers; i++) {
            playerData.add(new PlayerData());
        }
    }

    private LinkedList<String> identityRoller() {
        LinkedList<String> ids = new LinkedList<String>();
        ids.add("loyalist");
        ids.add("loyalist");
        ids.add("loyalist");
        ids.add("loyalist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        Collections.shuffle(ids);
        return ids;
    }

    public class OkListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ok) {
                System.out.println("MSG: reached the button");
                int nofPlayers = numberOfPlayers.getSelectedIndex() + 3;
                createPlayerData(nofPlayers);
                LinkedList<String> playerID = identityRoller();
                for (int i = 0; i < nofPlayers; i++) {
                    PlayerData pd = playerData.get(i);
                    PlayerData.allegiance = playerID.get(i);
                }
                mainFrame.remove(startUpScreen);
                createGUI();
            }


        }

    }

    public static void createGUI() {
        //main
        JPanel mainPanel = new JPanel(new BorderLayout());
        //board
        JPanel board = new JPanel(new GridLayout(5, 9));
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 5; y++) {
                board.add(cityStreets[x][y]);
                board.setVisible(true);
            }
        }
        board.setPreferredSize(new Dimension(400,300));
        //data table
        JPanel data = new JPanel(new GridLayout(2, 8));
        JLabel labelSUPPORT = new JLabel("SUPPORT:");
        JLabel infoSupport;
        infoSupport = new JLabel(Integer.toString(PlayerData.supportGained));
        data.add(labelSUPPORT);
        data.add(infoSupport);

        JLabel labelALLEGIANCE = new JLabel("ALLEGIANCE:");
        JLabel infoALLEGIANCE = new JLabel(PlayerData.allegiance);


        data.add(labelALLEGIANCE);
        data.add(infoALLEGIANCE);
        /*data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();
        data.add();*/
        //player screen

        JPanel playerScreenNorth = new JPanel(new GridLayout(1, 3));
        JPanel playerScreenWest = new JPanel(new GridLayout(3, 1));
        JPanel playerScreenSouth = new JPanel(new GridLayout(3, 1));
        JPanel playerScreenEast = new JPanel(new GridLayout(1, 3));

        //boardContents
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 5; y++) {
                board.add(cityStreets[x][y]);
            }
        }


        mainFrame.setTitle("Peaceful Power");
        mainPanel.setPreferredSize(new Dimension(800, 640));
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(playerScreenNorth, BorderLayout.NORTH);
        mainPanel.add(playerScreenEast, BorderLayout.EAST);
        mainPanel.add(playerScreenWest, BorderLayout.WEST);
        playerScreenSouth.add(data);
        mainPanel.add(playerScreenSouth, BorderLayout.SOUTH);
        mainPanel.setVisible(true);
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

}
