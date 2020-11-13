	/*
	 * A CompSciCheckerBoard represents a playing board for a 
	 * CompSciChecker game.  In this game a board is 8x8 and starts 
	 * with black and white CompSciChecker pieces.  The fields
	 * numWhitePieces and numBlackPieces track the appropriate 
	 * number of pieces for either side.  There are 12 white 
	 * pieces starting at the top left square and fill every other space
	 * on the board for the first three rows.  No two white pieces 
	 * should be directly adjacent to start.  The same is true of the
	 * 12 black pieces but they start at the bottom right and fill the
	 * bottom three rows.  the pieces may move one place in any
	 * direction, forwards, backwards, left, right or diagonal.  The
	 * pieces may "jump" an opposing player in any direction as well.
	 * 
	 */
public class CompSciCheckerBoard 
{
	
	private CompSciChecker[][] board;
	private int numWhitePieces;
	private int numBlackPieces;
	
	/*
	 * Create a Constructor for the CompSciCheckerBoard
	 * Once created the board should look like this:
	 * 
	 *  _ _ _ _ _ _ _ _
	 * |W   W   W   W  |
	 * |  W   W   W   W|
	 * |W   W   W   W  |
	 * |               |
	 * |               |
	 * |  B   B   B   B|
	 * |B   B   B   B  |
	 * |_ B _ B _ B _ B|
	 * 
	 * Empty Spaces have a value of null
	 * 
	 * 
	 */
	public CompSciCheckerBoard()
	{
		for(int i=0; i<3; i++)
		{
			if(i==2)
			{
				for(int c=0; c<8; c+=2)
				{
					board[c+1][i]=null;
					board[c][i]= new CompSciChecker("B", c, i);
				}
			}
			else
			{
				for(int c=0; c<8; c+=2)
				{
					board[c][i]=null;
					board[c+1][i]= new CompSciChecker("B", c+1, i);
				}
			}
		}
		
		for(int i=7; i>4; i--)
		{
			if(i==6)
			{
				for(int c=0; c<8; c+=2)
				{
					board[c+1][i]=null;
					board[c][i]= new CompSciChecker("B", c, i);
				}
			}
			else
			{
				for(int c=0; c<8; c+=2)
				{
					board[c][i]=null;
					board[c+1][i]= new CompSciChecker("B", c+1, i);
				}
			}
		}
		
		for(int i=3; i<6; i++)
		{
			for(int c=0; c<8; c++)
			{
				board[c][i]=null;
			}
		}
	}
	
	
	
	/*
	 * Next you will write the method isValidMove which will
	 * take a CompSciChecker piece and a new x and y value
	 * and determine if the move to the new space is legal.
	 * 
	 * A CompSciChecker piece can move one space in any direction
	 * including diagonally, so long as the space is not occuppied.
	 * 
	 * A CompSciChecker piece can move two spaces in any direction
	 * ONLY IF there is an opposing piece being "jumped" and the new
	 * space is not occupied.
	 * 
	 * write the method isValidMove.  return true if the desired move
	 * is valid and false if it is not.
	 */
	
	public boolean isValidMove(CompSciChecker piece, int newX, int newY)
	{
		if(newX-piece.getX()>2 || newX-piece.getX()<-2)
			return false;
		if(board[newX][newY]!=null)
			return false;
		if(newX-piece.getX()==2 || newX-piece.getX()==-2 || newY+piece.getY()==2 || newY+piece.getY()==-2)
		{
			if(board[(newX+piece.getX())/2][(newY+piece.getY())/2]==null || piece.getColor().equals(board[(newX+piece.getX())/2][(newY+piece.getY())/2].getColor()))
				return false;
		}
		return true;
	}
	
	
	/*
	 * Finally you will write the method makeMove.  This method will
	 * take a CompSciChecker piece and a new x and y as arguments.  
	 * move the desired piece ONLY IF the move is valid.  If an opposing
	 * piece is "jumped" remove it from the board and update the
	 * appropriate field.
	 * 
	 */
	
	public void makeMove(CompSciChecker piece, int x, int y)
	{
		if(isValidMove(piece,x,y))
		{
			if(x-piece.getX()==2 || x-piece.getX()==-2 || y+piece.getY()==2 || x+piece.getY()==-2)
			{
				if(board[(x+piece.getX())/2][(y+piece.getY())/2]!=null && !(piece.getColor().equals(board[(x+piece.getX())/2][(y+piece.getY())/2].getColor())))
				{
					board[(x+piece.getX())/2][(y+piece.getY())/2]=null;
				}
			}
			board[x][y]=piece;
			board[piece.getX()][piece.getY()]=null;
		}
	}
	
	
	


	
	/**
	 * 
	 * @param x x coordinate of board
	 * @param y y coordinate of board
	 * @return the piece at (x,y) or null if no piece
	 *         is located at (x,y)
	 */
	public CompSciChecker getPiece(int x, int y)
	{
		return board[y][x];
	}
	
	/**
	 * 
	 * @return the field board
	 */
	public CompSciChecker[][] getBoard()
	{
		return board;
	}
	
	
	/**
	 * returns a string representation of the board
	 */
	public String toString()
	{
		String str = "  _ _ _ _ _ _ _ _\n";
		for(int i = 0; i<board.length; i++)
		{
			str+= i + "|";
			for(int j = 0; j<board[i].length; j++)
			{
				if(board[i][j] != null)
					str+=board[i][j].getColor();
				else
				{
					if(i<board.length-1) str+=" ";
					else str+= "_";
				}
				if(j<board[i].length-1) str+= " ";
			}
			str+="|\n";
		}
		str+= "  0 1 2 3 4 5 6 7\n";
		return str;
	}
	
	
	
	
	

}
