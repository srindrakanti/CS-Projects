//Srikar Indrakanti, 112265102
package randomChess;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class Board extends GridPane{
    
	public Tile[][] tiles = new Tile[8][8];
    public Tile lastTile;
    public boolean isWhiteTurn = true; 
    public boolean whiteKing;
    public boolean blackKing;
    
    public Board(){
        super();

        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                boolean darkTile = ((i + j) % 2 == 0 ); // tile colors; similar algorithm as tartan pattern
                tiles[i][j] = new Tile(!darkTile, i, j);
                this.add(tiles[i][j], i, 7 - j); //7-j for inverted y axis
                
                int x = i;
                int y = j;
                tiles[x][y].setOnAction( e -> onClick(x, y) );
            }
        }
        //put pieces in start positions
        this.initialize();
    }
    
    public void initialize() {
    	
    	int rand;
    	for(int row = 0; row<2; row++) {
    		for (int i = 0; i < this.tiles[0].length; i++) {
        		rand = (int)(Math.random()*(5+(2*row)));
        		if(rand==0 && row ==0)
        			++rand;
        		switch(rand) {
        		case 0:
        			this.tiles[i][row].setPiece(new Pawn(true));
        			this.tiles[7-i][7-row].setPiece(new Pawn(false));
        			break;
        		case 1:
        			this.tiles[i][row].setPiece(new Rook(true));
        			this.tiles[7-i][7-row].setPiece(new Rook(false));
        			break;
        		case 2: 
        			this.tiles[i][row].setPiece(new Knight(true));
        			this.tiles[7-i][7-row].setPiece(new Knight(false));
                    break; 
                case 3: 
                	this.tiles[i][row].setPiece(new Bishop(true));
                	this.tiles[7-i][7-row].setPiece(new Bishop(false));
                    break; 
                case 4: 
                	this.tiles[i][row].setPiece(new Queen(true));
                	this.tiles[7-i][7-row].setPiece(new Queen(false));
                    break;
                case 5:
        			this.tiles[i][row].setPiece(new Pawn(true));
        			this.tiles[7-i][7-row].setPiece(new Pawn(false));
        			break;
                case 6:
        			this.tiles[i][row].setPiece(new Pawn(true));
        			this.tiles[7-i][7-row].setPiece(new Pawn(false));
        			break;
        		}
        	}
    	}
    	
    	this.tiles[4][0].setPiece(new King(true));
    	this.whiteKing = true;
    	this.tiles[4][7].setPiece(new King(false));
    	this.blackKing = true;
    }
    
    public void newGame() {
    	for (Tile[] tileArr: this.tiles) {
    		for (Tile t: tileArr) {
    			t.setPiece(null);
    		}
    	}
    	this.isWhiteTurn = true;
    	this.initialize();
    }
    
    public void setLastTile(Tile s){
    	if (this.lastTile != null)
        	this.lastTile.setColor(this.lastTile.originalColor);
    	
    	if ((s==null)||(s.piece.isWhite == this.isWhiteTurn))
        	this.lastTile = s;

        if (this.lastTile != null)
            this.lastTile.setColor("firebrick");
    }
    
    public void onClick(int x, int y) {
        Tile newTile = tiles[x][y];
        
        if (lastTile != null && lastTile.piece != null &&			// Must be in this order or may cause 
        (newTile.getPieceColor() != lastTile.getPieceColor()) &&	// null pointer exception
        lastTile.piece.isWhite == this.isWhiteTurn)
        {
            MakeMove p = new MakeMove(lastTile.x, lastTile.y, x, y);
            executeMove(p);
            this.setLastTile(null);
        }
        else if(newTile.piece != null)
        {
            this.setLastTile(newTile);
        }
    }

    public void executeMove(MakeMove p){
    	Tile tile1 = tiles[p.x1][p.y1];
        Tile tile2 = tiles[p.x2][p.y2]; 
    	
        if(moveIsValid(p)) {        	
        	this.isWhiteTurn = !this.isWhiteTurn;
        	Piece capturedPiece = tile2.piece;
        	tile2.setPiece(tile1.popPiece());
        	if (capturedPiece instanceof King) {
        		if (capturedPiece.isWhite)
        			this.whiteKing = false;        		
        		else
        			this.blackKing = false;
        		checkKings();
        	}
    	}   
    }

    public boolean moveIsValid(MakeMove mm){
        Piece piece = tiles[mm.x1][mm.y1].piece;
        Moves[] moves = piece.getMoves();

        int moveCount;
        int nextTilex;
        int nextTiley;

        for (Moves m : moves)
        {
            moveCount = (piece.movesOne)?1:8;
            boolean stop = false;

            for(int i = 1; i <= moveCount; i++)
            {
                if (stop)
                	break;
                
                nextTilex = m.x * i;
                nextTiley = m.y * i;
                Tile nextTile;
                
                //checks if the next tile is in the board
                try{
                    nextTile = tiles[mm.x1 + nextTilex][mm.y1 + nextTiley];
                }catch (Exception e) {  
                	break; 
                }
                
                //deals with pieces colliding
                if(!nextTile.isEmpty()){
                    stop = true;
                    if (piece.getColor() == nextTile.piece.getColor()) 
                    	break;
                }
                
                //if next tile is equal to length of the move
                if (mm.getxDiff() == nextTilex && mm.getyDiff() == nextTiley)
                {                    
                    if ((piece instanceof Pawn) && (pMoveCheck(mm) == false))	                    
	                    return false;
                    piece.hasMoved = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pMoveCheck(MakeMove p) {
        Tile tile1 = tiles[p.x1][p.y1];
        Tile tile2 = tiles[p.x2][p.y2];
        Piece piece = tile1.piece;
        
        //for vertical move
        if (p.getxDiff() == 0)
        {
            //direction modifier for colors
            int direction = Integer.compare(p.getyDiff(),0);

            //no capturing/jumping vertically
            for(int i = 1; i <= Math.abs(p.getyDiff()); i++)
            {
                if (!tiles[p.x1][p.y1+(i*direction)].isEmpty())
                	return false;
            }
        }
        //for diagonal move: only capturing
        else  
        {
            if (tile2.isEmpty() || (piece.isWhite == tile2.piece.isWhite))
            	return false;
        }
        return true;
    }
    
    public void checkKings() {
    	if(!this.blackKing) {
    		Alert win = new Alert(AlertType.INFORMATION);
            win.setTitle("Good Game!");
            win.setHeaderText("White Wins!");
            win.setContentText("Congratulations!");
            win.showAndWait();
    	}
    	else if(!this.whiteKing) {
    		Alert win = new Alert(AlertType.INFORMATION);
            win.setTitle("Good Game!");
            win.setHeaderText("Black Wins!");
            win.setContentText("Congratulations!");
            win.showAndWait();
    	}
    }
    
}

