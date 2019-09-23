//Srikar Indrakanti, 112265102
package randomChess;

public class Bishop extends Piece{
	
	public Bishop(boolean c) {
		super(c);
		this.movesOne = false;
	}
	
	public Moves[] getMoves() {
		Moves[] x = {
				Moves.UR,
				Moves.UL,
				Moves.DR,
				Moves.DL
		};
		return x;
	}

	public String getType() {
		return "b";
	}

}
