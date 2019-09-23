//Srikar Indrakanti, 112265102
package randomChess;

public class MakeMove {
	
	public int x1;
    public int y1;
    public int x2;
    public int y2;

    //For dealing with coordinates of a move (one Tile to another)
    public MakeMove(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getxDiff(){
    	return this.x2 - this.x1;
    }
    
    public int getyDiff(){
    	return this.y2 - this.y1;
    }

}
