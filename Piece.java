package pieces;

import java.util.ArrayList;
import board.Board;
import board.Move;

/**
 * Trida reprezentujici sachovou figuru
 * @author Jan Janis
 *
 */
public abstract class Piece {
	/** index sachove figury */
	protected final int pieceIndex;
	/** barva figury */
	protected final Colors pieceColor;
	
	/**
	 * Konstruktor sachove figury
	 * @param pieceIndex index figury
	 * @param pieceColor barva figury
	 */
	public Piece(int pieceIndex, Colors pieceColor) {
		this.pieceIndex = pieceIndex;
		this.pieceColor = pieceColor;
	}
	
	/**
	 * Metoda, ktera vrati barvu figury
	 * @return barva figury
	 */
	public Colors getPieceColor() {
		return this.pieceColor;
	}
	
	/**
	 * Metoda, ktera vygeneruje mozne pohyby s figurami (dle pravidel)
	 * @param board
	 * @return
	 */
	public abstract ArrayList<Move> generateViableMoves(Board board);
}
