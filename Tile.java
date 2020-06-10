package board;

import java.util.HashMap;
import java.util.Map;

import pieces.Piece;

/**
 * Abstraktni trida reprezentujici pole na sachovnici
 * @author Jan Janis
 *
 */
public abstract class Tile {
	/** index pole (0-63) */
	protected final int tileIndex;
	/** mapa prazdnych poli */
	private static final Map<Integer, EmptyTile> EMPTY_TILES = createEmptyTiles();
	
	/**
	 * Metoda, ktera vytvori mapu prazdnych poli
	 * @return mapa prazdnych poli
	 */
	private static Map<Integer, EmptyTile> createEmptyTiles() {
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

		for (int i = 0; i < BoardFunctions.TILES_COUNT; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}

		return emptyTileMap;
	}

	/**
	 * Konstruktor pole
	 * @param tileIndex index pole
	 */
	public Tile(int tileIndex) {
		this.tileIndex = tileIndex;
	}
	
	/**
	 * Metoda, ktera vytvori pole sachovnice na zadanem indexu se zadanou figurou
	 * Pokud je figura rovna null, vrati prazdne pole na zadanem indexu
	 * Pokud neni figura rovna null, vytvori nove obsazene pole se zadanou figurou na zadanem indexu
	 * @param tileIndex index pole
	 * @param piece sachova figura
	 * @return prazdne pole na zadanem indexu pokud neni zadana konkretni figura, jinak vrati na zadanem indexu obsazene pole se zadanou figurou
	 */
	public static Tile createTile(int tileIndex, Piece piece) {
		if (piece != null) {
			return new OccupiedTile(tileIndex, piece);
		}
		return EMPTY_TILES.get(tileIndex);
	}
	
	/**
	 * Metoda urcujici, zda je pole prazdne nebo je na nem nejaka figura
	 * @return true, pokud pole obsahuje figuru, false, pokud je prazdne
	 */
	public abstract boolean isOccupied();

	/**
	 * Metoda, ktera vrati typ figury na poli
	 * @return typ figury na poli
	 */
	public abstract Piece getPiece();

}
