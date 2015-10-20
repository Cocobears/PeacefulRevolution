

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static JPanel startUpScreen;
    static JButton ok = new JButton("OK");
    static LinkedList<String> identities;
    static JComboBox numberOfPlayers = new JComboBox();
    static LinkedList<PlayerData> playerData = new LinkedList<PlayerData>();
    static JFrame mainFrame = new JFrame();
    static JPanel[] cityStreets = new JPanel[45];
    static JLabel[][] street = new JLabel[3][3];
    static JTextField nameField = new JTextField();
    static JPanel cardPanel = new JPanel();
    static String currentCard ;
    static JPanel board = new JPanel(new GridLayout(5, 9));
    static JPanel data;
    static boolean clickable = true;
    static JButton endTurn;
    static LinkedList<String>  currentHand = new LinkedList<String>();
    static LinkedList<String>  deck = GameData.generateDeck();
    static LinkedList<String> currentTechHand = new LinkedList<String>();
    static JButton infoCards;

    public static void main(String[] args) {
        GameData game = new GameData();
        //define panels for the citymap

        for (int x = 0; x < 45; x++) {

             cityStreets[x] = new JPanel();

        }
        //create the streets as a test
        //setup the streets
        int cardValue = -1;
        int index = 0;
        for (int x = 0; x < 45; x++) {

            Color n;
            //

            System.out.println(cardValue);
            cardValue = getRandomCard();
            JPanel givenStreet = drawStreet(GameData.routeShape[cardValue]);
            //cityStreets[x][y].setLocation(35*x,35*y);
            cityStreets[x].add(givenStreet);

        }
        new Main();

    }

    private static JPanel drawStreet(String s) {
        Color n;
        JLabel[][] street = new JLabel[3][3];

        for(int x = 0;x<3;x++){
            for(int y = 0; y< 3;y++){
                int stringIndex = x*3+y;
                street[x][y] = new JLabel();
                if(s.charAt(0)=='#') {//if it is a mappiece
                    if (s.charAt(stringIndex) == (' ')) n = Color.BLACK;
                    else {
                        n = Color.red;
                    }
                    street[x][y].setOpaque(true);
                    street[x][y].setBackground(n);
                    street[x][y].setPreferredSize(new Dimension(35, 35));
                }
            }
        }
        JPanel givenStreet = new JPanel();
        givenStreet.setLayout(new GridLayout(3, 3));
        givenStreet.add(street[0][0]);
        givenStreet.add(street[0][1]);
        givenStreet.add(street[0][2]);
        givenStreet.add(street[1][0]);
        givenStreet.add(street[1][1]);
        givenStreet.add(street[1][2]);
        givenStreet.add(street[2][0]);
        givenStreet.add(street[2][1]);
        givenStreet.add(street[2][2]);
        return givenStreet;

    }

    private static int getRandomCard() {
        return (int) (Math.random() * GameData.routeShape.length);
    }

    public Main() {
        deck = GameData.generateDeck();
        currentCard = GameData.drawCard(deck);
        startUpScreen = new JPanel();
        display(startUpScreen);
    }

    public void display(JPanel startUpScreen) {

        startUpScreen.setLayout(new GridLayout(5, 2));
        JLabel name = new JLabel("Name : ");

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

    private static void createPlayerData(int nofPlayers) {
        //adds a copy of playerDAta to a list of players
        for (int i = 0; i < nofPlayers; i++) {
            playerData.add(new PlayerData());
        }
    }

    private static LinkedList<String> identityRoller() {
        LinkedList<String> ids = new LinkedList<String>();
        ids.add("lojalist");
        ids.add("lojalist");
        ids.add("lojalist");
        ids.add("lojalist");
        ids.add("aktivist");
        ids.add("aktivist");
        ids.add("aktivist");
        ids.add("aktivist");
        ids.add("aktivist");
        ids.add("aktivist");
        ids.add("aktivist");
        Collections.shuffle(ids);
        return ids;
    }

    public static class OkListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ok) {

                int nofPlayers = numberOfPlayers.getSelectedIndex() + 3;
                createPlayerData(nofPlayers);
                LinkedList<String> playerID = identityRoller();
                for (int i = 0; i < nofPlayers; i++) {
                    PlayerData pd = playerData.get(i);
                    PlayerData.allegiance = playerID.get(i);
                }
                PlayerData.name = nameField.getText();
                mainFrame.remove(startUpScreen);
                createGUI();
            }
            if(e.getSource() == infoCards){
                //presents a selection of cards and then a selection of opposing players

            }

        }

    }
    public static class EndListener implements MouseListener{

        public void  mouseClicked(MouseEvent e) {
            if (e.getSource() == endTurn) {
                clickable = true;
                currentCard = GameData.drawCard(deck);
                if(currentCard.charAt(0)!='#') {
                    currentTechHand.add(currentCard.substring(0, 1));
                    infoCards.setText(currentTechHand.toString());
                }
                //new card

                cardPanel.removeAll();
                cardPanel.add(drawStreet(currentCard));
                cardPanel.repaint();
                mainFrame.pack();
                cardPanel.setVisible(true);
            }

        }
        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }

    public static class MListener implements MouseListener{


        public void  mouseClicked(MouseEvent e) {
            if(e.getSource()==cardPanel) {

                currentCard = turnCard(currentCard);
                cardPanel.remove(0);
                cardPanel.add(drawStreet(currentCard));
                cardPanel.repaint();
                mainFrame.pack();
                cardPanel.setVisible(true);

            }
            for (int i = 0; i < 45; i++) {

                if (e.getComponent() == cityStreets[i] &&
                        clickable
                        ) {
                    cardPanel.setVisible(false);
                    cityStreets[i].removeAll();
                    cityStreets[i].add(drawStreet(currentCard));
                    board.repaint();
                    mainFrame.pack();
                    cityStreets[i].setVisible(true);
                    //currentCard = null;
                    clickable = false;
                    break;
                }
            }
        }
        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }
    public static void createGUI() {
        //main
        JPanel mainPanel = new JPanel(new BorderLayout());
        //board
        board = new JPanel(new GridLayout(5, 9));
        board.setPreferredSize(new Dimension(800, 500));


        for (int x = 0; x < 45; x++) {
            if(x%9==0){
                JPanel street = drawStreet(GameData.mapPieceHouses);
                cityStreets[x].remove(0);
                cityStreets[x].add(street);

            }else{}
            if(x==18) {
                JPanel street = drawStreet(GameData.mapPieceStart);
                cityStreets[x].remove(0);
                cityStreets[x].add(street);
            }
            else{

            }
            MListener el = new MListener();
            cityStreets[x].addMouseListener(el);
        }
        for (int x = 0; x < 45; x++) {
            JPanel street = cityStreets[x];
            board.add(street);

        }board.setVisible(true);



        //data table
        data = new JPanel(new GridLayout(4, 2));

        JLabel labelName = new JLabel("NAME: ");
        JLabel infoName = new JLabel(PlayerData.name);
        data.add(labelName);
        data.add(infoName);

        JLabel labelSUPPORT = new JLabel("SUPPORT:");
        JLabel infoSupport;
        infoSupport = new JLabel(Integer.toString(PlayerData.supportGained));
        data.add(labelSUPPORT);
        data.add(infoSupport);

        JLabel labelALLEGIANCE = new JLabel("ALLEGIANCE:");
        JLabel infoALLEGIANCE = new JLabel(PlayerData.allegiance);

        data.add(labelALLEGIANCE);
        data.add(infoALLEGIANCE);

        endTurn = new JButton("End Turn");
        EndListener ml = new EndListener();
        endTurn.addMouseListener(ml);

        infoCards = new JButton();
        OkListener icl = new OkListener();
        infoCards.addActionListener(icl);
        data.add(infoCards);
        data.add(endTurn);

        //player screen

        JPanel playerScreenNorth = new JPanel(new GridLayout(1, 3));
        JPanel playerScreenWest = new JPanel(new GridLayout(3, 1));
        JPanel playerScreenSouth = new JPanel(new GridLayout(1, 3));
        JPanel playerScreenEast = new JPanel(new GridLayout(3, 1));

        //boardContents


        mainFrame.setTitle("Peaceful Power");
        mainPanel.setPreferredSize(new Dimension(800, 640));
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(playerScreenNorth, BorderLayout.NORTH);
        mainPanel.add(playerScreenEast, BorderLayout.EAST);
        mainPanel.add(playerScreenWest, BorderLayout.WEST);
        playerScreenSouth.setPreferredSize(new Dimension(70,120));
        playerScreenSouth.add(data);

        if(currentCard.charAt(0)=='#') {
            cardPanel.add(drawStreet(currentCard));
        }
        else{
            PlayerData.techHand.add(currentCard.substring(0,1));
            infoCards.setText(PlayerData.techHand.toString());
        }
        MListener el = new MListener();
        cardPanel.addMouseListener(el);
        playerScreenSouth.add(cardPanel);
        mainPanel.add(playerScreenSouth, BorderLayout.SOUTH);
        mainPanel.setVisible(true);
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


    public static String turnCard(String card){
        String twistedCard ="";
        twistedCard += card.substring(6,7);
        twistedCard += card.substring(3,4);
        twistedCard += card.substring(0,1);
        twistedCard += card.substring(7,8);
        twistedCard += card.substring(4,5);
        twistedCard += card.substring(1,2);
        twistedCard += card.substring(8);
        twistedCard += card.substring(5,6);
        twistedCard += card.substring(2,3);

        return twistedCard;
    }
}
