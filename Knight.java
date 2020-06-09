package pieces;

import java.util.ArrayList;

import board.Board;
import board.Move;
import board.Tile;

/**
 * Trida reprezentujici figuru jezdce
 * @author Jan Janis
 *
 */
public class Knight extends Piece {
	/** mozne indexy poli v sachovnici, na ktere se jezdec muze pohnout */
	private final static int[] POSSIBLE_MOVE_INDEXES = {-17, -15, -10 -6, 6, 10, 15, 17};

	/**
	 * Konstruktor jezdce
	 * @param pieceIndex index jezdce
	 * @param pieceColor barva jezdce
	 */
	public Knight(int pieceIndex, Colors pieceColor) {
		super(pieceIndex, pieceColor);
	}

	@Override
	public ArrayList<Move> generateViableMoves(Board board) {
		//mozny index pole jezdce po tahu
		int possibleDestinationIndex;
		// kolekce moznych tahu
		final ArrayList<Move> viableMoves = new ArrayList<>();

		
		for (int possibleMoveIndex : POSSIBLE_MOVE_INDEXES) {
			possibleDestinationIndex = this.pieceIndex + possibleMoveIndex;

			//pokud je index dostupny
			if(true) {
				
				final Tile possibleDestinationTile = board.getTile(possibleDestinationIndex);

				//pokud neni pole obsazeno, prida mozny tah do kolekce
				if(!possibleDestinationTile.isOccupied()) {
					viableMoves.add(new Move());
				}
				else {
					//figura na poli o moznem indexu tahu
					final Piece pieceAtDestination = possibleDestinationTile.getPiece();
					//barva teto figury
					final Colors pieceColor = pieceAtDestination.getPieceColor();
					
					//pokud je figura na poli o moznem indexu jine barvy, nez aktualni figura, prida mozny tah do kolekce
					if(this.pieceColor != pieceColor) {
						viableMoves.add(new Move());
					}
				}
			}

		}
		return viableMoves;
	}

}
