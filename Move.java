package board;

import pieces.Piece;

/**
 * Abstraktni trida reprezentujici pohyb figury
 * @author Jan Janis
 *
 */
public abstract class Move {
	public final Board board;
	public final Piece movedPiece;
	public final int destinationIndex;
	
	/**
	 * Konstruktor tridy Move
	 * @param board sachovnice
	 * @param movedPiece figura, se kterou se hybe
	 * @param destinationIndex index na ktery se presune
	 */
	public Move(Board board, Piece movedPiece, int destinationIndex) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationIndex = destinationIndex;
	}
}
