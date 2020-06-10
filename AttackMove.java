package board;

import pieces.Piece;

/**
 * Trida reprezentujici tah s vyhozenim figury na cilovem poli
 * @author Jan Janis
 *
 */
public class AttackMove extends Move {
	/** figura, ktera je napadena */
	final Piece attackedPiece;
	
	public AttackMove(Board board, Piece movedPiece, int destinationIndex, Piece attackedPiece) {
		super(board, movedPiece, destinationIndex);
		this.attackedPiece = attackedPiece;

	}

}
