import java.awt.*;

/**
 * Created by diku on 10/16/15.
 */
public class Model {
    public boolean routeFinder(int[][] cityMap) {
        boolean demonstrationPathSecured = false;
        return demonstrationPathSecured;
    }

    public boolean isAccepted(Point location, String mappiece) {
        getNearbyMappieces(location);
        return false;
    }

    public String[] getNearbyMappieces(Point location) {
        String[] mappieces = {"", "", "", ""};
        int index = toIndex(location);
        //calculate each index around the mappiece
        int indextop = 0;
        int indexbottom = 0;
        int indexleft = 0;
        int indexright = 0;
        final int NONEXISTENT = -1;//indicates offmap zone

        if((location.y -1 == -1) && (location.x -1 ==-1 )){
            //LT corner
            indextop = NONEXISTENT;
            indexright = 1;
            indexbottom = 9;
            indexleft = NONEXISTENT;

        }
        else if((location.y -1 == -1) &&(location.x +1 ==9)){
            //RT corner
            indextop = NONEXISTENT;
            indexright = NONEXISTENT;
            indexbottom = 17;
            indexleft =7;
        }
        else if((location.y +1 == 5) && (location.x -1 == -1)){
            //LB corner
            indextop = 27;
            indexright = 37;
            indexbottom = NONEXISTENT;
            indexleft =NONEXISTENT;
        }
        else if((location.y +1 == 5) && (location.x +1 == 9)){
            //RB corner
            indextop = 35;
            indexright = NONEXISTENT;
            indexbottom = NONEXISTENT;
            indexleft =43;
        }else{
            indextop = (location.y-1)*9 + location.x;
            indexright = location.y*9 + location.x+1;
            indexbottom = (location.y+1)*9 + location.x;
            indexleft =(location.y)*9 + location.x-1;
        }

        if (indextop == NONEXISTENT) mappieces[0] = "";
        else {
            mappieces[0] = GameData.boardMap[indextop];
        }
        if (indexright == NONEXISTENT) mappieces[1] = "";
        else {
            mappieces[1] = GameData.boardMap[indexright];
        }
        if (indexbottom == NONEXISTENT) mappieces[2] = "";
        else {
            mappieces[2] = GameData.boardMap[indexbottom];
        }
        if (indexleft == NONEXISTENT) mappieces[3] = "";
        else {
            mappieces[3] = GameData.boardMap[indexleft];
        }


        return mappieces;
    }

    private int toIndex(Point location) {
        return location.y * 9 + location.x;
    }
    public void distributeBounty(String winnerSide, int nofPlayers){

        if(winnerSide == GameData.side[0]){
            //share out the gold

        }
        else if(winnerSide == GameData.side[1]){

        }
    }

}
