package board;

import pieces.Piece;

/**
 * Trida reprezentujici tah na prazdne pole
 * @author Jan Janis
 *
 */
public class MajorMove extends Move{

	public MajorMove(Board board, Piece movedPiece, int destinationIndex) {
		super(board, movedPiece, destinationIndex);
	}

}
