//Srikar Indrakanti, 112265102
package randomChess;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;


public class Tile extends Button{
    
	public int x;
    public int y;
    public Piece piece; // piece currently on space
    public String originalColor;

    public Tile(boolean light, int x, int y){
        super();
        this.x = x;
        this.y = y;
        
        this.setStyle("-fx-min-height: 75px;");
        this.addStyle("-fx-min-width: 75px;");
        this.addStyle("-fx-pref-height: 75px;");
        this.addStyle("-fx-pref-width: 75px;");
        this.addStyle("-fx-max-height: 75px;");
        this.addStyle("-fx-max-width: 75px;");
        
        this.originalColor = (light)?"azure":"darkgray";
        this.addStyle("-fx-background-color: " + this.originalColor + ";");        
    }

    public boolean isEmpty(){
        return (this.piece == null);
    }

    public Piece popPiece(){
        Piece temp = this.piece;
        this.setPiece(null);
        return temp;
    }
    
    public String getPieceColor(){
        if (!this.isEmpty())
            return this.piece.getColor();
        else 
            return "";
    }
    
    public void setPiece(Piece piece){
        this.piece = piece;

        if (!this.isEmpty()) {
        	ImageView chess = new ImageView(piece.img);
        	chess.setFitWidth(75); 
	    	chess.setPreserveRatio(true);
            this.setGraphic(chess);
        }
        else
            this.setGraphic(new ImageView());
    }
    
    public final void addStyle(String s) {
    	String temp = this.getStyle();
    	this.setStyle(temp+s);
    }
    
    public void setColor(String s) {
    	String temp = this.getStyle();
    	int index = temp.indexOf("color: ");
    	temp = temp.substring(0, index+7);
    	this.setStyle(temp + s +";");
    }
    

}
