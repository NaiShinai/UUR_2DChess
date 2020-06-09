package board;

import pieces.Piece;

/**
 * Trida reprezentujici pole s figurou
 * @author Jan Janis
 *
 */
public class OccupiedTile extends Tile{
	// sachova figura
	private final Piece piece;
	
	public OccupiedTile(int tileIndex, Piece piece) {
		super(tileIndex);
		this.piece = piece;
	}

	@Override
	public boolean isOccupied() {
		return true;
	}

	@Override
	public Piece getPiece() {
		return this.piece;
	}

}
