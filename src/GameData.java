import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by muse on 27-Sep-15.
 */
public class GameData {


    public String[] routeShape = {
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",

        "# #   # #",
        "# #   # #",
        "# #   # #",
        "# #   # #",
        "#########"

    };

    public GameData() {
        LinkedList<String> deck = new LinkedList<String>();
        deck = generateDeck();
    }

    public LinkedList<String> generateDeck() {
        LinkedList<String> d = new LinkedList<String>();
        for(String s: routeShape){
            d.add(s);
        }
        Collections.shuffle(d);
        return d;
    }

}
