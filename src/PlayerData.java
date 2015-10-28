import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by muse on 27-Sep-15.
 */
public class PlayerData {
    String allegiance = "";
    String name = "";
    int supportGained = 0;
    boolean pass = false;
    LinkedList<String> techHand = new LinkedList<String>();
    LinkedList<String> hand = new LinkedList<String>();
     int id;

    //names
    public void setName() {

        for (int i = 1; i < Main.nofPlayers; i++) {
            this.name = "AI" + Integer.toString(i);
        }
    }

    public PlayerData(){
        //setAllegiance();
        setName();
    }


}
