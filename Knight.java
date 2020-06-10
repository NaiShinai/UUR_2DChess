package pieces;

import java.util.ArrayList;

import board.AttackMove;
import board.Board;
import board.Move;
import board.Tile;
import board.BoardFunctions;
import board.MajorMove;

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
			if(BoardFunctions.isValidTileIndex(possibleDestinationIndex)) {

				if(firstColumnExclusion(this.pieceIndex, possibleMoveIndex) || secondColumnExclusion(this.pieceIndex, possibleMoveIndex) ||
						seventhColumnExclusion(this.pieceIndex, possibleMoveIndex) || eighthColumnExclusion(this.pieceIndex, possibleMoveIndex)) {
					continue;
				}

				final Tile possibleDestinationTile = board.getTile(possibleDestinationIndex);

				//pokud neni pole obsazeno, prida mozny tah do kolekce
				if(!possibleDestinationTile.isOccupied()) {
					viableMoves.add(new MajorMove(board, this, possibleDestinationIndex));
				}
				else {
					//figura na poli o moznem indexu tahu
					final Piece pieceAtDestination = possibleDestinationTile.getPiece();
					//barva teto figury
					final Colors pieceColor = pieceAtDestination.getPieceColor();

					//pokud je figura (na poli o moznem indexu) jine barvy, nez aktualni figura, prida mozny utocny tah do kolekce
					if(this.pieceColor != pieceColor) {
						viableMoves.add(new AttackMove(board, this, possibleDestinationIndex, pieceAtDestination));
					}
				}
			}

		}
		return viableMoves;
	}
	/**
	 * Tyto metody slouzi k vylouceni neplatnych tahu (tzn. neplatnych indexu cilovych poli) jezdce
	 * @param currentIndex soucasny index pole, na kterem je figura
	 * @param possibleOffset mozny index pole destinace
	 * @return true, pokud se nachazi figura v prislusnem sloupci (0,1,6,7) a zaroven se mozny index destinace nerovna jednomu ze "zakazanych" indexu (takovych, ktere jsou mimo dosah tahu jezdce)
	 * 		   jinak vraci false    
	 */
	private static boolean firstColumnExclusion(int currentIndex, int possibleOffset) {
		return BoardFunctions.FIRST_COLUMN[currentIndex] && (possibleOffset == -17 || possibleOffset == -10 || possibleOffset == 6 || possibleOffset == 15);
	}

	private static boolean secondColumnExclusion(int currentIndex, int possibleOffset) {
		return BoardFunctions.SECOND_COLUMN[currentIndex] && (possibleOffset == -10 || possibleOffset == 6);
	}

	private static boolean seventhColumnExclusion(int currentIndex, int possibleOffset) {
		return BoardFunctions.SEVENTH_COLUMN[currentIndex] && (possibleOffset == -6 || possibleOffset == 10);
	}

	private static boolean eighthColumnExclusion(int currentIndex, int possibleOffset) {
		return BoardFunctions.EIGHTH_COLUMN[currentIndex] && (possibleOffset == -15 || possibleOffset == -6 || possibleOffset == 10 || possibleOffset == 17);
	}
}
