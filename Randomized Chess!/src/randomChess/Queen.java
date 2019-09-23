//Srikar Indrakanti, 112265102
package randomChess;

public class Queen extends Piece{
	
	public Queen(boolean c) {
		super(c);
		this.movesOne = false;
	}
	
	public Moves[] getMoves() {
		Moves[] x = {
				Moves.U,
				Moves.D,
				Moves.L,
				Moves.R,
				
				Moves.UR,
				Moves.UL,
				Moves.DR,
				Moves.DL
		};
		return x;
	}

	public String getType() {
		return "q";
	}

}
