package ezmart.model.util;

public class Mascara {

	public static String getMascara(int casasDecimais) {
		if (casasDecimais > 16)
			casasDecimais = 16;

		String mascara = getMascaraZeroCasa();
		if (casasDecimais < 0)
			throw new IllegalArgumentException("Número de casas decimais deve ser maior ou igual a zero");

		if (casasDecimais > 0) {
			mascara += ",";
			for (int cont = 1; cont <= casasDecimais; cont++) {
				mascara += "#";
			}
		}

		return mascara;
	}

	/**
	 *
	 * @return "###.###.###.###.###.###.###.###"
	 */
	public static String getMascaraZeroCasa() {
		return "###.###.###.###.###.###.###.###";
	}

	/**
	 *
	 * @return ###.###.###.###.###.###.###.###,#
	 */
	public static String getMascaraUmaCasa() {
		return getMascara(1);
	}

	/**
	 *
	 * @return ###.###.###.###.###.###.###.###,##
	 */
	public static String getMascaraDuasCasas() {
		return getMascara(2);
	}

	/**
	 *
	 * @return ###.###.###.###.###.###.###.###,###
	 */
	public static String getMascaraTresCasas() {
		return getMascara(3);
	}

	/**
	 *
	 * @return ###.###.###.###.###.###.###.###,####
	 */
	public static String getMascaraQuatroCasas() {
		return getMascara(4);
	}
	/**
	 *
	 * @return ###.###.###.###.###.###.###.###,####
	 */
	public static String getMascaraCincoCasas() {
		return getMascara(5);
	}

	/**
	 *
	 * @return ###.###.###.###.###.###.###.###,####
	 */
	public static String getMascaraSeisCasas() {
		return getMascara(6);
	}

	public static String MASCARA_CEP = "#####-###";

	public static String MASCARA_CPF = "###.###.###-##";

	public static String MASCARA_CNPJ = "##.###.###/####-##";

	public static String MASCARA_MONETARIA = "##,###,###,##0.00";

}
