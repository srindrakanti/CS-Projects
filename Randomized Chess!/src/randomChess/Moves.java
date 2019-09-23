//Srikar Indrakanti, 112265102
package randomChess;

//To declare the types of moves allowed in chess, with each Piece subclass having its own subset
public enum Moves {
	U (0,1),
    UR(1, 1),
    R(1, 0),
    DR(1, -1),
    D(0,-1),
    DL(-1, -1),
    L(-1, 0),
    UL(-1, 1),
    
    TWO_UP(0, 2),
    TWO_DOWN(0, -2),
    
    NUL(-1, 2),
    NUR(1, 2),
    NRU(2, 1),
    NLU(-2, 1),
    
    
    NDR(1, -2),
    NDL(-1, -2),
    NRD(2, -1),
    NLD(-2, -1);

    public int x;
    public int y;
    
    private Moves(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(int x, int y) {
    	return (this.x == x) && (this.y == y); 
    }
    
    public boolean equals(Moves o) {
    	return (this.x == o.x) && ( this.y == o.y); 
    }

    public void setx(int x) {
    	this.x = x;
    }
    
    public void sety(int y){
    	this.y = y;
    
    }
    
}
