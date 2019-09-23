//Srikar Indrakanti, 112265102
package randomChess;

public class Knight extends Piece{
	
	public Knight(boolean c) {
		super(c);
		this.movesOne = true;
	}
	
	public Moves[] getMoves() {
		Moves[] x = {
				Moves.NUL,
				Moves.NUR,
				Moves.NLU,
				Moves.NRU,
				
				Moves.NDL,
				Moves.NDR,
				Moves.NLD,
				Moves.NRD,
		};
		return x;
	}

	public String getType() {
		return "n";
	}

}
