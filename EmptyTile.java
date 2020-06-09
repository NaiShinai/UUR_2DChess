package board;

import pieces.Piece;

/**
 * Trida reprezentujici prazdne pole sachovnice
 * @author Jan Janis
 *
 */
public class EmptyTile extends Tile {

	public EmptyTile(int tileIndex) {
		super(tileIndex);
	}

	@Override
	public boolean isOccupied() {
		return false;
	}

	@Override
	public Piece getPiece() {
		return null;
	}
}
