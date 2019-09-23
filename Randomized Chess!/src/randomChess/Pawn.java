//Srikar Indrakanti, 112265102
package randomChess;

public class Pawn extends Piece{
	
	
	public Pawn(boolean c) {
		super(c);
		this.movesOne = true;
	}
	
	public Moves[] getMoves() {
		
		Moves[] x;
		if(!this.hasMoved) {
			x = new Moves[4];
			x[3] = this.isWhite?Moves.TWO_UP:Moves.TWO_DOWN;
		}
		else {
			x = new Moves[3];
		}
		
		x[0] = this.isWhite?Moves.U:Moves.D;
		x[1] = this.isWhite?Moves.UR:Moves.DR;
		x[2] = this.isWhite?Moves.UL:Moves.DL;
		
        return x;
        
	}

	public String getType() {
		return "p";
	}
	
}
