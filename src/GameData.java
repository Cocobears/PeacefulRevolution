import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by muse on 27-Sep-15.
 */
public class GameData {

    static int currentPlayer =0;
    //MAP bits
    static String mapPiece4Exits = "# #   # #";

    static String mapPiece3Exits = "#»#   # #";

    static String mapPiece2ExitsStraight = "#»#   #»#";

    static String mapPiece2ExitsTurn = "#»#  »# #";

    static String mapPiece1Exit = "#»#  »#»#";

    static String mapPieceNoExit = "# # » # #";

    static String mapPieceStart = "# # S # #";

    static String mapPiecePalace = "# # ¤ # #";

    static String mapPiecePresident = "# # P # #";

    static String mapPieceHouses = "#########";

    public static String[] routeShape = {


            //home
            mapPieceStart,
            mapPieceHouses,

//5 with 4 exits
            mapPiece4Exits,
            mapPiece4Exits,
            mapPiece4Exits,
            mapPiece4Exits,
            mapPiece4Exits,
//10 with 3 exits
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
            mapPiece3Exits,
//7 with straight
            mapPiece2ExitsStraight,
            mapPiece2ExitsStraight,
            mapPiece2ExitsStraight,
            mapPiece2ExitsStraight,
            mapPiece2ExitsStraight,
            mapPiece2ExitsStraight,
            mapPiece2ExitsStraight,
//9 with a turn
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
            mapPiece2ExitsTurn,
//with dead ends
            mapPiece1Exit,
            mapPiece1Exit,
//with a block
            mapPieceNoExit,
            mapPieceNoExit,
            mapPieceNoExit,
            mapPieceNoExit,
            mapPieceNoExit,
            mapPieceNoExit,
            mapPieceNoExit,

            mapPiecePresident,

            mapPiecePalace,
            mapPiecePalace


    };

    public static String[] techCards = {
            "corruption",
            "corruption",
            "violence",
            "violence",
            "ignorance",
            "ignorance",

            //solutions
            "reporter",
            "reporter",
            "police",
            "police",
            "lawyer",
            "lawyer",
            "reporter&police",
            "lawyer&reporter",
            "police&lawyer"

    };

    public static int getCurrentPlayer() {

        return currentPlayer;
    }

    public static int nextPlayer(){
        currentPlayer++;
        return getCurrentPlayer();
    }

    public GameData() {
        LinkedList<String> deck = new LinkedList<String>();
        deck = generateDeck();
    }

    public static LinkedList<String> generateDeck() {
        LinkedList<String> d = new LinkedList<String>();
        for (String s : routeShape) {
            d.add(s);
        }
        for (String t : techCards) {
            d.add(t);
        }
        Collections.shuffle(d);
        return d;
    }

    public static String drawCard(LinkedList<String> deck) {
        String topCard = deck.getFirst();
        deck.removeFirst();
        return topCard;
    }
}
