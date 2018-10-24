package ezmart.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

public class TrataNumero {

	private static int ARREDONDA = 1;
	private static int TRUNCA = 2;
	private static int ARREDONDA_PRA_CIMA = 3;

	/**
	 * "So completa com zero a esquerda se o tamanho da String for menor que o numCaracteres"
	 * 
	 * @param String
	 *            Dado
	 * @param int
	 *            numCaracteres
	 * @return String
	 **/
	public static String completaZerosEsquerdaSeMenor(String dado, int numCaracteres) {

		if (dado == null) {
			return null;
		}

		if (dado.length() < numCaracteres)
			return String.format("%0" + numCaracteres + "d", Long.parseLong(tiraZeroEsquerda(dado)));
		else
			return dado;
	}

	public static String completaZerosEsquerdaSeMenorHexadecimal(String dado, int numCaracteres) {

		if (dado == null) {
			return null;
		}

		return String.format("%0" + numCaracteres + "x", Long.parseLong(tiraZeroEsquerda(dado)));
	}

	public static String numeroPuro(String txt) {

		if (txt == null) {
			return null;
		}

		return txt.replaceAll("[^0-9]", "").trim();
	}

	public static String tiraLetra(String txt) {
		return txt.replaceAll("[A-Za-z]", "").trim();
	}

	public static String getPrimeiraParte(String dado, String separador) {

		if (dado == null || separador == null) {
			return null;
		}

		int pos = dado.indexOf(separador);
		if (pos > 0) {
			dado = dado.substring(0, pos);
		}
		return dado.trim();
	}

	public static String getSegundaParte(String dado, String separador) {

		if (dado == null || separador == null) {
			return null;
		}

		int pos = dado.indexOf(separador);
		if (pos > 0) {
			dado = dado.substring(pos + separador.length(), dado.length());
			dado = getPrimeiraParte(dado, separador);
		}
		return dado.trim();
	}

	public static String getSegundaParteRestante(String dado, String separador) {

		if (dado == null || separador == null) {
			return null;
		}

		int pos = dado.indexOf(separador);
		if (pos > 0) {
			dado = dado.substring(pos, dado.length());
		}
		return dado.trim();
	}

	public static String getTerceiraParte(String dado, String separador) {

		if (dado == null || separador == null) {
			return null;
		}

		int pos = dado.indexOf(separador);
		if (pos > 0) {
			dado = dado.substring(pos + separador.length(), dado.length());
			dado = getSegundaParte(dado, separador);
		}
		return dado.trim();
	}

	public static String getTerceiraParteRestante(String dado, String separador) {

		if (dado == null || separador == null) {
			return null;
		}

		int pos = dado.indexOf(separador);
		if (pos > 0) {
			dado = dado.substring(pos, dado.length());
			dado = getSegundaParteRestante(dado, separador);
		}
		return dado.trim();
	}

	public static String aplicaMascaraMonetaria(Object dado) {

		DecimalFormat formatoDois = new DecimalFormat(Mascara.MASCARA_MONETARIA, new DecimalFormatSymbols(new Locale("pt", "BR")));
		formatoDois.setMinimumFractionDigits(2);
		formatoDois.setParseBigDecimal(true);

		return formatoDois.format(dado);

	}

	public static String aplicaMascaraMonetaria(Object value, String pattern) {

		int minimumFractionDigits = 2;
		if (!pattern.contains("0.")) {
			String MASCARA = "##,###,###,##0.";
			if (pattern.contains(",")) {
				pattern = MASCARA + TrataNumero.getSegundaParte(pattern, ",").replace("#", "0");
				minimumFractionDigits = TrataNumero.getSegundaParte(pattern, ".").length();
			} else {
				pattern = MASCARA + "00";
			}
		}

		DecimalFormat formatoDois = new DecimalFormat(pattern, new DecimalFormatSymbols(new Locale("pt", "BR")));
		formatoDois.setMinimumFractionDigits(minimumFractionDigits);
		formatoDois.setParseBigDecimal(true);

		return formatoDois.format(value);

	}

	public static String aplicaMascara(Object value, String pattern) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static double div0(String dado) {
		return Operacoes.divide(new Double(preparaNumero(dado)), 1);
	}

	public static double div1(String dado) {
		return Operacoes.divide(new Double(preparaNumero(dado)), 10);
	}

	public static double div2(String dado) {
		return Operacoes.divide(new Double(preparaNumero(dado)), 100);
	}

	public static double div3(String dado) {
		return Operacoes.divide(new Double(preparaNumero(dado)), 1000);
	}

	public static double div4(String dado) {
		return Operacoes.divide(new Double(preparaNumero(dado)), 10000);
	}

	public static double div5(String dado) {
		return Operacoes.divide(new Double(preparaNumero(dado)), 100000);
	}

	private static String preparaNumero(String dado) {
		return tiraZeroEsquerda(dado.replace(".", "").replace(",", "."));
	}

	public static String tiraZeroEsquerda(String dado) {
		String temp = "";
		if (!dado.replaceAll("0", "").trim().isEmpty()) {
			for (int i = 0; i < dado.length(); i++) {
				if (!dado.substring(i, i + 1).equals("0")) { // checa se chegou no primeiro caracter q não é 0
					temp += dado.substring(i, dado.length()); // temp fica com os valores correspondentes à substring da posição atual ate o fim
					break; // sai do laço
				}
			}
		} else
			temp = "0";
		return temp;
	}

	public static double mult0(String dado) {
		return Operacoes.multiplica(new Double(preparaNumero(dado)), 1);
	}

	public static double mult1(String dado) {
		return Operacoes.multiplica(new Double(preparaNumero(dado)), 10);
	}

	public static double mult2(String dado) {
		return Operacoes.multiplica(new Double(preparaNumero(dado)), 100);
	}

	public static double mult3(String dado) {
		return Operacoes.multiplica(new Double(preparaNumero(dado)), 1000);
	}

	public static String multPadrao(double numero) {
		DecimalFormat casas = new DecimalFormat("0.0###############", new DecimalFormatSymbols(Locale.GERMAN));
		return casas.format(new Numero(numero).doubleValue());
	}

	public static String multGenericoHalfUp(double numero, int casasDecimais) {
		numero = new Numero(numero).doubleValue();

		double multiplicador = Math.pow(10, casasDecimais);
		numero = numero * multiplicador;
		DecimalFormat casas01 = new DecimalFormat("0", new DecimalFormatSymbols(Locale.GERMAN));
		casas01.setRoundingMode(RoundingMode.HALF_UP);
		String num = casas01.format(numero);
		return num;
	}

	public static String multGenerico(double numero, int casasDecimais) {
		numero = new Numero(numero).doubleValue();

		double multiplicador = Math.pow(10, casasDecimais);
		numero = numero * multiplicador;
		DecimalFormat casas01 = new DecimalFormat("0", new DecimalFormatSymbols(Locale.GERMAN));
		String num = casas01.format(numero);
		return num;
	}

	public static String mult0(double numero) {
		return multGenerico(numero, 0);
	}

	public static String mult1(double numero) {
		return multGenerico(numero, 1);
	}

	public static String mult2(double numero) {
		return multGenerico(numero, 2);
	}

	public static String mult3(double numero) {
		return multGenerico(numero, 3);
	}

	public static String mult4(double numero) {
		return multGenerico(numero, 4);
	}

	public static String mult5(double numero) {
		return multGenerico(numero, 5);
	}

	public static String mult6(double numero) {
		return multGenerico(numero, 6);
	}

	public static double divPadrao(String numero) {
		numero = numero.replaceAll("\\.", "").replaceAll("\\,", "\\.");
		if (numero.equalsIgnoreCase("."))
			numero = "0.0";
		return new Numero(Double.parseDouble(numero)).doubleValue();
	}

	public static String tiraZerosEsquerda(String numero) {
		int lengthNumero = numero.length();
		if (lengthNumero > 1) {
			for (int i = 1; i <= lengthNumero; i++) {
				if (numero.length() == 1 && numero.equalsIgnoreCase("0")) {
					numero = "";
					break;
				}
				if ((numero.substring(0, 1).equalsIgnoreCase(".") || numero.substring(0, 1).equalsIgnoreCase("0")) && !numero.substring(1, (2)).equalsIgnoreCase(","))
					numero = numero.substring(1, numero.length());
				else
					break;
			}
		}
		return numero;
	}

	public static String tiraZerosDireita(String numero, int nCasasDecimaisMask) {
		int lengthNumero = numero.length();
		if (lengthNumero > 1) {
			for (int i = lengthNumero; i > 1; i--) {
				if (nCasasDecimaisMask == 0) {
					if (numero.substring(numero.length() - 1, numero.length()).equalsIgnoreCase(".") || numero.substring(numero.length() - 1, numero.length()).equalsIgnoreCase("0") || numero.substring(numero.length() - 1, numero.length()).equalsIgnoreCase(",")) {
						if (numero.substring(numero.length() - 1, numero.length()).equalsIgnoreCase(",")) {
							numero = numero.substring(0, numero.length() - 1);
							break;
						} else
							numero = numero.substring(0, numero.length() - 1);
					} else {
						break;
					}
				}
			}
		}
		return numero;
	}

	public static int getNumeroCasasDecimais(String numero) {
		int nCasasDecimaisNumero = 0;
		if (numero.indexOf(',') >= 0)
			nCasasDecimaisNumero = numero.length() - numero.indexOf(",") - 1;

		return nCasasDecimaisNumero;
	}

	public static double divGenerico(String numero, int casasDecimais) {
		double numeroD;

		if (numero.trim().compareTo("") == 0) {
			numero = "0";
		}

		boolean temVirgulas = numero.indexOf(',') >= 0;
		if (temVirgulas) {
			numeroD = divPadrao(numero);
		} else {
			numeroD = Double.valueOf(numeroPuro(numero)).doubleValue();
			double divisor = 1;
			for (int i = 0; i < casasDecimais; i++) {
				divisor *= 10;
			}
			numeroD = numeroD / divisor;
		}
		return new Numero(numeroD).doubleValue();
	}

	public static String editarNumero(String numero, String mask) {
		boolean isNegativo = false;
		if (numero.indexOf("-") >= 0) {
			numero = numero.substring(1);
			isNegativo = true;
		}
		int nCasasDecimaisMask = 0;
		if (mask.indexOf(",") >= 0)
			nCasasDecimaisMask = mask.length() - mask.indexOf(",") - 1;

		boolean temVirgulas = numero.indexOf(',') >= 0;

		if (temVirgulas) {
			int posVirgula = numero.indexOf(',');
			int tamanhoNumero = numero.length();
			int numeroCasasDecimais = tamanhoNumero - posVirgula - 1;

			for (int i = numeroCasasDecimais; i < nCasasDecimaisMask; i++) {
				numero = numero + "0";
			}

			numero = numero.substring(0, posVirgula + nCasasDecimaisMask + 1);
			numero = numero.replaceAll(",", "");
		}

		if (temVirgulas) {
			DecimalFormat casas = new DecimalFormat("#,##0.0###############", new DecimalFormatSymbols(Locale.GERMAN));
			numero = casas.format(divPadrao(numero));
			int nCasasDecimaisNumero = numero.length() - numero.indexOf(",") - 1;
			int diferencaCasasDecimais = nCasasDecimaisMask - nCasasDecimaisNumero;
			if (diferencaCasasDecimais < 0)
				numero = tiraZerosDireita(numero, nCasasDecimaisMask);
			else
				for (int i = 0; i < diferencaCasasDecimais; i++)
					numero += "0";

			numero = tiraZerosEsquerda(numero);
			if (isNegativo) {
				if (divGenerico(numero, getNumeroCasasDecimais(numero)) == 0)
					return numero;
				else
					return "-" + numero;
			} else {
				return numero;
			}
		} else {
			numero = numero.replaceAll("\\.", "");

			if (numero.compareToIgnoreCase("") == 0) {
				numero = "0";
			}
			String numeroEditado = "";

			int nCountDecimais = 0;
			if (mask.indexOf(",") >= 0)
				nCountDecimais = (mask.length()) - mask.indexOf(",");
			int nCount = numero.length();
			if (nCountDecimais > nCount) {
				nCountDecimais = nCountDecimais - nCount;
				for (nCount = 0; nCount < nCountDecimais; nCount++) {
					numero = "0" + numero;
				}
			}
			nCount = numero.length();
			if (nCount > 0) {

				int nMask = mask.length();
				boolean fim = true;
				while (fim) {
					String p = numero.substring(nCount - 1, nCount);
					int nMaskAnterior = (nMask == 0) ? nMask : nMask - 1;
					String m = mask.substring(nMaskAnterior, nMask);
					if (m.compareTo(".") == 0 || m.compareTo(",") == 0) {
						numeroEditado = m + numeroEditado;
						nMask = nMask - 1;
					}
					numeroEditado = p + numeroEditado;
					nCount = nCount - 1;
					nMask = nMask - 1;
					if (nCount <= 0) {
						fim = false;
					}
				}
			}
			numeroEditado = tiraZerosEsquerda(numeroEditado);
			if (isNegativo) {
				if (divGenerico(numeroEditado, getNumeroCasasDecimais(numeroEditado)) == 0)
					return numeroEditado;
				else
					return "-" + numeroEditado;
			} else {
				return numeroEditado;
			}
		}
	}

	public static double arredondaValorParaCima(Double precoDouble) {
		return arredondaValorParaCima(precoDouble, 2);
	}

	public static double arredondaValorParaCima(Double numero, int nCasas) {

		if (numero == null)
			numero = 0d;

		try {
			String mascara = "0." + String.format("%0" + nCasas + "d", 0);
			DecimalFormat df = new DecimalFormat(mascara);
			df.setRoundingMode(RoundingMode.UP);
			String string = df.format(numero);
			return df.parse(string).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return numero;
		}

	}

	public static double arredondaValorParaBaixo(Double precoDouble) {
		return arredondaValorParaBaixo(precoDouble, 2);
	}

	public static double arredondaValorParaBaixo(Double numero, int nCasas) {

		if (numero == null)
			numero = 0d;

		try {
			String mascara = "0." + String.format("%0" + nCasas + "d", 0);
			DecimalFormat df = new DecimalFormat(mascara);
			df.setRoundingMode(RoundingMode.DOWN);
			String string = df.format(numero);
			return df.parse(string).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return numero;
		}

	}

	public static double arredondaValorMonetario(Double numero) {

		BigDecimal auxiliar = new BigDecimal(numero);
		auxiliar = auxiliar.setScale(2, RoundingMode.HALF_UP);
		numero = auxiliar.doubleValue();
		return numero;

	}

	public static double arredondaValor(Double numero, int qtdCasaDecimal) {

		BigDecimal auxiliar = new BigDecimal(numero);
		auxiliar = auxiliar.setScale(qtdCasaDecimal, RoundingMode.HALF_UP);
		numero = auxiliar.doubleValue();
		return numero;

	}

	public static double arredondaValorCeiling(Double numero, int qtdCasaDecimal) {

		BigDecimal auxiliar = new BigDecimal(numero);
		auxiliar = auxiliar.setScale(qtdCasaDecimal, RoundingMode.CEILING);
		numero = auxiliar.doubleValue();
		return numero;

	}

	private static String inserirPontos(String numero) {
		DecimalFormat casas = new DecimalFormat("#,##0.0#########", new DecimalFormatSymbols(Locale.GERMAN));
		return casas.format(divPadrao(numero));
		// return numero;
	}

	public static String arredondaValorMonetario(String numero) {
		return editarNumeroArredonda(numero, Mascara.getMascaraDuasCasas());
	}

	public static String editarNumeroArredonda(String numero, String mask) {
		return editarNumeroArredonda(numero, mask, ARREDONDA);
	}

	/**
	 * @param numero
	 * @param mask
	 * @param opcao
	 *            Use as constantes ARREDONDA, TRUNCA, ARREDONDA_PRA_CIMA
	 * @return
	 */
	private static String editarNumeroArredonda(String numero, String mask, int opcao) {

		boolean isNegativo = false;
		if (numero.indexOf("-") >= 0) {
			numero = numero.substring(1);
			isNegativo = true;
		}

		int nCasasDecimaisMascara = 0;
		if (mask.indexOf(",") >= 0)
			nCasasDecimaisMascara = mask.length() - mask.indexOf(",") - 1;

		if (numero.equalsIgnoreCase(""))
			numero = "0";

		if (numero.equalsIgnoreCase("0")) {
			if (nCasasDecimaisMascara > 0)
				numero += ",";
			for (int i = 0; i < nCasasDecimaisMascara; i++)
				numero += "0";
		}

		int posVirgulaNumero = numero.indexOf(",");

		if (String.valueOf(numero.charAt(0)).equalsIgnoreCase(","))
			numero = "0" + numero;

		if (posVirgulaNumero != -1) {
			numero = inserirPontos(numero);
			int nCasasDecimaisNumero = numero.length() - numero.indexOf(",") - 1;
			if (nCasasDecimaisMascara > nCasasDecimaisNumero) {
				int diferencaCasasDecimais = nCasasDecimaisMascara - nCasasDecimaisNumero;
				for (int i = 0; i < diferencaCasasDecimais; i++)
					numero += "0";

				if (isNegativo) {
					if (divGenerico(numero, getNumeroCasasDecimais(numero)) == 0)
						return numero;
					else
						return "-" + numero;
				} else {
					return numero;
				}
			} else if (nCasasDecimaisMascara < nCasasDecimaisNumero) {
				int diferencaCasasDecimais = nCasasDecimaisNumero - nCasasDecimaisMascara;
				String numArredonda = numero.substring(numero.length() - diferencaCasasDecimais, numero.length());
				if (opcao == ARREDONDA_PRA_CIMA) {
					if (div0(numArredonda.substring(0, numArredonda.length())) > 0) {
						double fator = Math.pow(10, nCasasDecimaisMascara);
						numero = multGenerico((Math.ceil((fator * divGenerico(numero.substring(0, numero.length()) + "1", nCasasDecimaisMascara))) / fator), nCasasDecimaisMascara);
					} else
						numero = numero.substring(0, numero.length() - diferencaCasasDecimais);
				} else {
					if (opcao == TRUNCA)
						numero = numero.substring(0, numero.length() - diferencaCasasDecimais);
					else {
						if (opcao == ARREDONDA) {
							if (div0(numArredonda.substring(0, 1)) >= 5) {
								double fator = Math.pow(10, nCasasDecimaisMascara);
								numero = multGenerico((Math.rint((fator * divGenerico(numero.substring(0, numero.length()) + "1", nCasasDecimaisMascara))) / fator), nCasasDecimaisMascara);
							} else {
								numero = numero.substring(0, numero.length() - diferencaCasasDecimais);
							}
						}
					}
				}
				numero = editarNumero(numero, mask);

				if (isNegativo) {
					if (divGenerico(numero, getNumeroCasasDecimais(numero)) == 0)
						return numero;
					else
						return "-" + numero;
				} else {
					return numero;
				}
			} else {
				if (isNegativo) {
					if (divGenerico(numero, getNumeroCasasDecimais(numero)) == 0)
						return numero;
					else
						return "-" + numero;
				} else {
					return numero;
				}
			}
		} else {
			numero = editarNumero(numero, mask);
			numero = tiraZerosEsquerda(numero);

			if (isNegativo) {
				if (divGenerico(numero, getNumeroCasasDecimais(numero)) == 0)
					return numero;
				else
					return "-" + numero;
			} else {
				return numero;
			}
		}

	}

	/**
	 * Adiciona espaços à Direita da palavra para ter o número de caracteres especificado no parâmetro <i>nCaracteres<i>.
	 *
	 * @param palavra
	 * @param nCaracteres
	 * @return
	 */
	public static String completaEspacosDireita(String palavra, int nCaracteres) {
		String prefixo = "";
		for (int i = 0; i < (nCaracteres - palavra.length()); i++)
			prefixo += " ";
		return palavra + prefixo;
	}

	public static double converterDoubleDuasCasas(Double precoDouble) {
		return converterDouble(precoDouble, 2);
	}

	public static double converterDoubleQuatroCasas(Double precoDouble) {
		return converterDouble(precoDouble, 4);
	}

	public static double converterDouble(Double precoDouble) {
		return converterDouble(precoDouble, 2);
	}

	public static double converterDouble(Double precoDouble, int nCasas) {
		if (precoDouble == null)
			precoDouble = 0d;
		try {
			String mascara = "0." + String.format("%0" + nCasas + "d", 0);
			DecimalFormat fmt = new DecimalFormat(mascara);
			String string = fmt.format(precoDouble);
			return fmt.parse(string).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return precoDouble;
		}
	}

	/**
	 * Converte Double para String
	 * 
	 * @param Object
	 * @param String
	 *            mascara " Exemplo : "0.00" Coloque a quantidade de zeros necessários após o "."(Ponto)
	 * @return Sera retornado o valor em double com "," no lugar do "."
	 */
	public static String doubleConverter(Object obj, String mascara) {
		if (obj == null) {
			return mascara;
		}
		DecimalFormat format = new DecimalFormat(mascara);
		return format.format(obj).replace(".", ",");
	}

	public static String doubleConverter(Object obj) {
		return doubleConverter(obj, "0.00");
	}

	/**
	 * "Caso o tamanho da String seja menor que o numCaracteres é completado com Zeros a esquerda, Caso seja maior e truncado o dado com o tamanho do numCaracteres."
	 * 
	 * @param String
	 *            int
	 * 
	 * @return String
	 **/
	public static String completaZerosEsquerdaTrunca(String dado, int numCaracteres) {

		if (dado == null) {
			return null;
		}

		if (dado.length() < numCaracteres)
			return String.format("%0" + numCaracteres + "d", Long.parseLong(tiraZeroEsquerda(dado)));
		else
			return dado.substring(0, numCaracteres);
	}

	/**
	 * "Caso o tamanho da String seja menor que o numCaracteres é completado com Zeros a direita."
	 * 
	 * @param String
	 *            int
	 * 
	 * @return String
	 **/
	public static String completaZerosDireita(String dado, int numCaracteres) {
		String prefixo = "";
		for (int i = 0; i < (numCaracteres - dado.length()); i++)
			prefixo += "0";
		return dado + prefixo;
	}

	public static String aplicaMascaraTelefone(String telefone) {

		telefone = numeroPuro(telefone);

		if (telefone == null || telefone.trim().isEmpty())
			return telefone;

		if (telefone.length() == 8)
			telefone = Pattern.compile("(\\d{4})(\\d{4})").matcher(telefone).replaceAll("$1-$2");
		else if (telefone.length() == 9)
			telefone = Pattern.compile("(\\d{5})(\\d{4})").matcher(telefone).replaceAll("$1-$2");
		else if (telefone.length() == 10)
			telefone = Pattern.compile("(\\d{2})(\\d{4})(\\d{4})").matcher(telefone).replaceAll("($1) $2-$3");
		else if (telefone.length() == 11)
			telefone = Pattern.compile("(\\d{2})(\\d{5})(\\d{4})").matcher(telefone).replaceAll("($1) $2-$3");
		else if (telefone.length() == 12)
			telefone = Pattern.compile("(\\d{2})(\\d{2})(\\d{4})(\\d{4})").matcher(telefone).replaceAll("+$1 ($2) $3-$4");
		else if (telefone.length() == 13)
			telefone = Pattern.compile("(\\d{2})(\\d{2})(\\d{5})(\\d{4})").matcher(telefone).replaceAll("+$1 ($2) $3-$4");

		return telefone;
	}

	public static String getDadoNumerico(int numCaracteres) {
		return getDadoNumerico("0", numCaracteres);
	}

	public static String getDadoNumerico(Object dado, int numCaracteres) {
		if (dado == null || dado.toString().trim().isEmpty())
			dado = "0";
		dado = numeroPuro(dado.toString());
		dado = tiraLetra(dado.toString());
		return completaZerosEsquerdaTrunca(dado.toString(), numCaracteres);
	}

	public static boolean isLong(String str) {
		try {

			Long.parseLong(str);
			return true;

		} catch (NumberFormatException ex) {

			return false;
		}

	}
}
