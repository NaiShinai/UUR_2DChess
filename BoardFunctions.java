package board;
/**
 * Trida predstavujici funkce ktere vyuziva sachovnice
 * @author Jan Janis
 *
 */

public class BoardFunctions {
	/** pole booleanu reprezentujici prvni, druhy, sedmy a osmy sloupec
	 *  pole jsou velikost 64 (jako sachovnice)
	 *  pole FIRST_COLUMN obsahuje na indexech prvniho sloupce hodnoty true, na vsech ostatnich false
	 *  obdobne je to pro ostatni sloupce
	 */
	public static final boolean[] FIRST_COLUMN = generateColumnArray(0);
	public static final boolean[] SECOND_COLUMN = generateColumnArray(1);
	public static final boolean[] SEVENTH_COLUMN = generateColumnArray(6);
	public static final boolean[] EIGHTH_COLUMN = generateColumnArray(7);
	
	public static final int TILES_COUNT = 64;
	public static final int TILES_IN_ROW = 8;

	/**
	 * Metoda, ktera vygeneruje boolean hodnoty pro zadane cislo sloupce
	 * @param columnNumber cislo sloupce
	 * @return pole booleanu reprezentujici dany sloupec
	 */
	private static boolean[] generateColumnArray(int columnNumber) {
		final boolean[] column = new boolean[TILES_COUNT];
		
		while(columnNumber < TILES_COUNT) {
			column[columnNumber] = true;
			columnNumber += TILES_IN_ROW;
		}
		return column;
	}
	
	/**
	 * Metoda, ktera urci, zda je zadany index platny
	 * @param index index pole
	 * @return true, pokud je index pole platny, false, pokud neni
	 */
	public static boolean isValidTileIndex(int index) {
		if(index >= 0 && index < TILES_COUNT) {
			return true;
		}
		return false;
	}
}

