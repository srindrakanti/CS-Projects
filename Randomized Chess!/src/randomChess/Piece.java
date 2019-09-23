//Srikar Indrakanti, 112265102
package randomChess;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public abstract class Piece {
	
	public boolean isWhite;
	public Image img;
	public boolean movesOne;
	public boolean hasMoved; //for Pawn class, false by default
	
	public Piece(boolean isWhite) {
		
		this.isWhite = isWhite;
		this.hasMoved = false;
		FileInputStream imageLoc;
		try {
			
			String imgLocStr = "src/randomChess/resources/images/" + this.toString() + ".png";
			imageLoc = new FileInputStream(imgLocStr);
			this.img = new Image(imageLoc);
			
		} catch (FileNotFoundException e) {
			System.out.println("Piece image not found.");
		}
		
	}
	
		public String getColor() {
			return this.isWhite ? "w":"b"; 
		}
		public abstract String getType();
		public abstract Moves[] getMoves();
		
		public String toString() {
			return this.getColor() + this.getType();
		}
		
		/*
		public Piece swapColor() {
			
		}
		*/
		
	
}
