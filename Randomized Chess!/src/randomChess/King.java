//Srikar Indrakanti, 112265102
package randomChess;

public class King extends Piece{
	
	public King(boolean c) {
		super(c);
		this.movesOne = true;
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
		return "k";
	}

}
