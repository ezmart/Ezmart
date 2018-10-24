package ezmart.model.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class Numero {

	private static final long serialVersionUID = -4437462041597407944L;
    private BigDecimal big;

    protected Numero(BigDecimal big) {
        this.big = big;
    }

    protected BigDecimal getBigDecimal(){
        return big;
    }

    public Numero( double val ) {
//        this.big = new BigDecimal(val, new MathContext(16));
//        this.big = new BigDecimal(val, new MathContext(16)).round(new MathContext(16));
//        this.big = new BigDecimal(val, new MathContext(17)).round(new MathContext(16));
//        this.big = new BigDecimal(val, MathContext.DECIMAL128).round(new MathContext(15));
//        this.big = new BigDecimal(val, MathContext.UNLIMITED).round(new MathContext(16));
//        this.big = new BigDecimal(val).round(new MathContext(15));
    	this.big = new BigDecimal(val, new MathContext(15, RoundingMode.HALF_UP));
    }


    public Numero soma( double val ){
        return soma(new Numero(val));
    }

    public Numero soma( Numero val ){
        return new Numero(big.add(val.getBigDecimal()));
    }

    public Numero subtrai( double val ){
        return subtrai(new Numero(val));
    }

    public Numero subtrai( Numero val ){
        return new Numero(big.subtract(val.getBigDecimal()));
    }

    public Numero multiplica( double val ){
        return multiplica(new Numero(val));
    }

    public Numero multiplica( Numero val ){
        return new Numero(big.multiply(val.getBigDecimal()));
    }

    public Numero divide( double val ){
        return divide(new Numero(val));
    }

    public Numero divide( Numero arg0 ){
    	return new Numero(big.divide(arg0.getBigDecimal(), new MathContext(15, RoundingMode.HALF_UP)));
    }

    /**
    *
    * @param arg0
    * @return Retorna mais algarismos que o divide() mas estes não contém tanta precisão.
    */
    public Numero divideAlternativo( double val ){
        return divideAlternativo(new Numero(val));
    }

    /**
     *
     * @param arg0
     * @return Retorna mais algarismos que o divide() mas estes não contém tanta precisão.
     */
    public Numero divideAlternativo( Numero arg0 ){
        return new Numero(big.divide(arg0.getBigDecimal(), MathContext.DECIMAL128));
    }

    public Numero divideValorEmInteiro( double val ){
        return divideValorEmInteiro(new Numero(val));
    }

    public Numero divideValorEmInteiro( Numero arg0 ){
        return new Numero(big.divideToIntegralValue(arg0.getBigDecimal()));
    }

    public Numero resto( double val ){
        return resto(new Numero(val));
    }

    public Numero resto( Numero arg0 ){
        return new Numero(big.remainder(arg0.getBigDecimal()));
    }

    public Numero[] divideEResto( double val ){
        return divideEResto(new Numero(val));
    }

    public Numero[] divideEResto( Numero arg0 ){
        BigDecimal[] bigArray = big.divideAndRemainder(arg0.getBigDecimal());
        Numero[] numArray = new Numero[2];
        numArray[0] = new Numero(bigArray[0]);
        numArray[1] = new Numero(bigArray[1]);
        return numArray;
    }

    public Numero tiraSinal(){
        return new Numero(big.abs());
    }

    public int compareTo( double val ){
        return compareTo(new Numero(val));
    }

    public int compareTo( Numero val ){
        return big.compareTo(val.getBigDecimal());
    }

    public double doubleValue(){
        return big.doubleValue();
    }

    public Numero negate(){
        return new Numero(big.negate());
    }

    public Numero pow( int arg0 ){
        return new Numero(big.pow(arg0));
    }

    /**
     *
     * @return -1, 0 ou 1 se o Numero for negativo, zero ou positivo.
     */
    public int signum(){
        return big.signum();
    }

    public String toString(){
        return big.stripTrailingZeros().toPlainString();
    }

    public boolean equals( Object x ){
        return big.equals(x);
    }

    public int hashCode(){
        return big.hashCode();
    }

    public Numero maior( double val ){
        return maior(new Numero(val));
    }

    public Numero maior( Numero val ){
        return new Numero(big.max(val.getBigDecimal()));
    }

    public Numero menor( double val ){
        return menor(new Numero(val));
    }

    public Numero menor( Numero val ){
        return new Numero(big.min(val.getBigDecimal()));
    }

    public Numero round( MathContext arg0 ){
        return new Numero(big.round(arg0));
    }

    public Numero tiraZerosDireita(){
        return new Numero(big.stripTrailingZeros());
    }

    public String toEngineeringString(){
        return big.toEngineeringString();
    }

    public String toDoubleString(){
        return big.toString();
    }
    
}
