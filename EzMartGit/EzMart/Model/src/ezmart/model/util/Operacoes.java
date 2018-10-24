package ezmart.model.util;


public class Operacoes {

	public static double soma(double val1, double val2) {
		return new Numero(val1).soma(val2).doubleValue();
	}

	public static double soma(double val1, double val2, double val3) {
		return (new Numero(val1).soma(val2)).soma(val3).doubleValue();
	}

	public static double soma(double val1, double val2, double val3, double val4) {
		return (new Numero(val1).soma(val2).soma(val3).soma(val4)).doubleValue();
	}

	public static double soma(double val1, double val2, double val3, double val4, double val5) {
		return (new Numero(val1).soma(val2).soma(val3).soma(val4).soma(val5)).doubleValue();
	}

	public static double soma(double val1, double val2, double val3, double val4, double val5, double val6) {
		return (new Numero(val1).soma(val2).soma(val3).soma(val4).soma(val5).soma(val6)).doubleValue();
	}
	public static double soma(double val1, double val2, double val3, double val4, double val5, double val6, double val7) {
		return (new Numero(val1).soma(val2).soma(val3).soma(val4).soma(val5).soma(val6).soma(val7)).doubleValue();
	}

	public static double subtrai(double val1, double val2) {
		return new Numero(val1).subtrai(val2).doubleValue();
	}

	public static double subtrai(double val1, double val2, double val3) {
		return (new Numero(val1).subtrai(val2)).subtrai(val3).doubleValue();
	}

	public static double subtrai(double val1, double val2, double val3, double val4) {
		return (new Numero(val1).subtrai(val2).subtrai(val3).subtrai(val4)).doubleValue();
	}

	public static double subtrai(double val1, double val2, double val3, double val4, double val5) {
		return (new Numero(val1).subtrai(val2).subtrai(val3).subtrai(val4).subtrai(val5)).doubleValue();
	}

	public static double subtrai(double val1, double val2, double val3, double val4, double val5, double val6) {
		return (new Numero(val1).subtrai(val2).subtrai(val3).subtrai(val4).subtrai(val5).subtrai(val6)).doubleValue();
	}

	public static double multiplica(double val1, double val2) {
		return new Numero(val1).multiplica(val2).doubleValue();
	}

	public static double multiplica(double val1, double val2, double val3) {
		return (new Numero(val1).multiplica(val2)).multiplica(val3).doubleValue();
	}

	public static double multiplica(double val1, double val2, double val3, double val4) {
		return (new Numero(val1).multiplica(val2).multiplica(val3).multiplica(val4)).doubleValue();
	}

	public static double multiplica(double val1, double val2, double val3, double val4, double val5) {
		return (new Numero(val1).multiplica(val2).multiplica(val3).multiplica(val4).multiplica(val5)).doubleValue();
	}

	public static double multiplica(double val1, double val2, double val3, double val4, double val5, double val6) {
		return (new Numero(val1).multiplica(val2).multiplica(val3).multiplica(val4).multiplica(val5).multiplica(val6)).doubleValue();
	}

	public static double divide(double val1, double val2) {
		return new Numero(val1).divide(val2).doubleValue();
	}

	public static double divide(double val1, double val2, double val3) {
		return (new Numero(val1).divide(val2)).divide(val3).doubleValue();
	}

	public static double divide(double val1, double val2, double val3, double val4) {
		return (new Numero(val1).divide(val2).divide(val3).divide(val4)).doubleValue();
	}

	public static double divide(double val1, double val2, double val3, double val4, double val5) {
		return (new Numero(val1).divide(val2).divide(val3).divide(val4).divide(val5)).doubleValue();
	}

	public static double divide(double val1, double val2, double val3, double val4, double val5, double val6) {
		return (new Numero(val1).divide(val2).divide(val3).divide(val4).divide(val5).divide(val6)).doubleValue();
	}

	public static int compareTo(double val1, double val2) {
		return new Numero(val1).compareTo(val2);
	}

}
