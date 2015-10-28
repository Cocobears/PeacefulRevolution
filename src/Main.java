

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
    static JTextField nameField = new JTextField();
    static JButton ok = new JButton("OK");

    static LinkedList<String> identities;
    static JComboBox numberOfPlayers = new JComboBox();
    static int nofPlayers = 3;
    static LinkedList<PlayerData> players;
    private static String name;
    public static String[] map;
    String[] playernames;
    static JFrame mainFrame = new JFrame();
    static JPanel[] cityStreets = new JPanel[9*5];
    static JLabel[][] street = new JLabel[3][3];


    static JPanel cardPanel = new JPanel();
    static String currentCard;
    static JPanel board = new JPanel(new GridLayout(5, 9));
    static JPanel data;
    static boolean clickable = true;
    static JButton endTurn;
    static LinkedList<String> currentHand = new LinkedList<String>();
    static LinkedList<String> deck = GameData.generateDeck();
    static LinkedList<String> currentTechHand = new LinkedList<String>();
    static JButton infoCards;
    static int winnerID = -1;
    private final int nofRounds = 3;

    public static void main(String[] args) {
        GameData game = new GameData();
        //define panels for the citymap


        new Main();

    }

    private static JPanel drawStreet(String s) {
        Color n;
        JLabel[][] street = new JLabel[3][3];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int stringIndex = x * 3 + y;
                street[x][y] = new JLabel();
                if (s.charAt(0) == '#') {//if it is a mappiece
                    if (s.charAt(stringIndex) == (' ')) n = Color.BLACK;
                    else if (s.charAt(stringIndex) == ('H'))n = Color.gray;
                    else if (s.charAt(stringIndex) == ('¤'))n =  Color.white;
                    else if (s.charAt(stringIndex) == ('P'))n =  Color.yellow;
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

    public void display(JPanel startUpScreen) {

        startUpScreen.setLayout(new GridLayout(5, 2));
        JLabel name = new JLabel("Name : ");

        JLabel numberOfPlayersLabel = new JLabel("Players : ");
        String[] players = new String[]{"3", "4", "5", "6", "7", "8", "9", "10"};
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

    public Main() {
        //initial
        deck = GameData.generateDeck();
        currentCard = GameData.drawCard(deck);
        startUpScreen = new JPanel();
        display(startUpScreen);
        players = createPlayerData(nofPlayers);
        Model gameMechanics = new Model();
        ///////////////////////
        //game routine
        //////////////////////
        for (int i = 0; i < nofRounds; i++) {
            do {
                //game play

            } while (isEndOfRound());
            gameMechanics.distributeBounty();
        }
        //find the winner of the games
        int max = 0;
        for (int i = 0; i < nofPlayers; i++) {
            if(max<players.get(i).supportGained){
                max = players.get(i).supportGained;
            }
        }
        //VICTORY MESSAGE
        data.removeAll();
        JLabel victoryMessage =  new JLabel();

        data.add(victoryMessage);
        for (int i =0 ; i< nofPlayers;i++) {
            if(players.get(i).supportGained == max)victoryMessage.setText("Winner: " + players.get(i).name);

        }
    }

    private boolean isEndOfRound() {
        boolean b = true;
        if(GameData.deckDepleted && isAllPassing()){
            //loyalists win
            b = false;
        }else{}
        if (Model.isCompletedRoute()){}else{}
        return b;
    }

    private boolean isAllPassing() {
        boolean b = false;
        boolean passingToken = true;
        for (int i = 0; i < nofPlayers; i++) {
            passingToken = passingToken && players.get(i).pass;
        }
        b = passingToken;
        return b;
    }





    private static LinkedList<PlayerData> createPlayerData(int nofPlayers) {
        //adds a copy of playerDAta to a list of players
        players = new LinkedList<PlayerData>();
        for (int i = 0; i < GameData.numberOfPlayers; i++) {
            PlayerData n = new PlayerData();
            players.add(n);
        }

        players.get(0).name = name;
        //set allegiance
        LinkedList allegianceDeck = setAllegianceDeck();
        for (int i = 0; i < nofPlayers; i++) {
            players.get(i).allegiance = (String) allegianceDeck.getFirst();
            allegianceDeck.removeFirst();
        }
        //
        for (int i = 0; i < nofPlayers; i++) {
            players.get(i).id = i;
        }
        return players;
    }

    private static LinkedList setAllegianceDeck() {
        LinkedList allegianceDeck = new LinkedList();
        for (int i = 0; i < GameData.nofActivists[Main.nofPlayers]; i++) {
            allegianceDeck.add("activist");
        }
        for (int i = 0; i < GameData.nofLoyalists[Main.nofPlayers]; i++) {
            allegianceDeck.add("loyalist");
        }
        Collections.shuffle(allegianceDeck);
        return allegianceDeck;
    }



    public static class OkListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ok) {

                nofPlayers = numberOfPlayers.getSelectedIndex() + 3;
                GameData.numberOfPlayers = nofPlayers;
                name = nameField.getText();
                mainFrame.remove(startUpScreen);
                createGUI();
            }
            if (e.getSource() == infoCards) {
                //presents a selection of cards and then a selection of opposing players
                JFrame optionsFrame = new JFrame();
                //contents for options frame
                JComboBox<String> players = new JComboBox<String>();
                
                optionsFrame.pack();
                optionsFrame.setVisible(true);

            }

        }

    }

    public static class EndListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == endTurn) {
                clickable = true;
                currentCard = GameData.drawCard(deck);
                if (currentCard.charAt(0) != '#') {
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

    public static class MListener implements MouseListener {


        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == cardPanel) {

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

        //contents of the board
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

            cityStreets[x].add(givenStreet);

        }

        for (int x = 0; x < 45; x++) {
            //first row
            if (x % 9 == 0) {
                JPanel street = drawStreet(GameData.mapPieceHouses);
                cityStreets[x].removeAll();
                cityStreets[x].add(street);

            } else {
            }
            //add start
            if (x == (9+9)-1+1) {
                JPanel street = drawStreet(GameData.mapPieceStart);
                cityStreets[x].removeAll();
                cityStreets[x].add(street);
            } else {
            }
            //last row
            if (x == 9-1) {
               JPanel street = drawStreet(GameData.mapPiecePalace);
                cityStreets[x].removeAll();
                cityStreets[x].add(street);
            }else{}
            if (x == 3*9-1){
                JPanel street = drawStreet(GameData.mapPiecePalace);
                cityStreets[x].removeAll();
                cityStreets[x].add(street);
            }else{}
            if(x == 5*9 -1){
                JPanel street = drawStreet(GameData.mapPiecePalace);
                cityStreets[x].removeAll();
                cityStreets[x].add(street);
            }else{}

            MListener el = new MListener();
            cityStreets[x].addMouseListener(el);
        }
        for (int x = 0; x < 9*5; x++) {
            JPanel street = cityStreets[x];
            board.add(street);
        }

        board.setVisible(true);


        //data table
        data = new JPanel(new GridLayout(4, 2));

        JLabel labelName = new JLabel("NAME: ");
        JLabel infoName = new JLabel(name);
        data.add(labelName);
        data.add(infoName);

        JLabel labelSUPPORT = new JLabel("SUPPORT:");
        JLabel infoSupport;
        infoSupport = new JLabel(Integer.toString(players.get(0).supportGained));
        data.add(labelSUPPORT);
        data.add(infoSupport);

        JLabel labelALLEGIANCE = new JLabel("ALLEGIANCE:");
        JLabel infoALLEGIANCE = new JLabel(players.get(0).allegiance);

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

        //mainFrame is set


        mainFrame.setTitle("Peaceful Power");
        mainPanel.setPreferredSize(new Dimension(800, 640));
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(playerScreenNorth, BorderLayout.NORTH);
        mainPanel.add(playerScreenEast, BorderLayout.EAST);
        mainPanel.add(playerScreenWest, BorderLayout.WEST);
        playerScreenSouth.setPreferredSize(new Dimension(70, 120));
        playerScreenSouth.add(data);
        //Current Card Panel
        if (currentCard.charAt(0) == '#') {
            cardPanel.add(drawStreet(currentCard));
        } else {
            players.get(0).techHand.add(currentCard.substring(0, 1));
            infoCards.setText(players.get(0).techHand.toString());
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


    public static String turnCard(String card) {
        String twistedCard = "";
        twistedCard += card.substring(6, 7);
        twistedCard += card.substring(3, 4);
        twistedCard += card.substring(0, 1);
        twistedCard += card.substring(7, 8);
        twistedCard += card.substring(4, 5);
        twistedCard += card.substring(1, 2);
        twistedCard += card.substring(8);
        twistedCard += card.substring(5, 6);
        twistedCard += card.substring(2, 3);

        return twistedCard;
    }
}
