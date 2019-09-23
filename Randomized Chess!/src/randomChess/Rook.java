//Srikar Indrakanti, 112265102
package randomChess;

public class Rook extends Piece{
	
	public Rook(boolean c) {
		super(c);
		this.movesOne = false;
	}
	
	public Moves[] getMoves() {
		Moves[] x = {
				Moves.U,
				Moves.D,
				Moves.L,
				Moves.R
		};
		return x;
	}

	public String getType() {
		return "r";
	}
}
