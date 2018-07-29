package org.example.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import org.junit.Test;

public class DoubleTest {

//	private static final double VALUE = 1234567890.0987654321;
	private static final double VALUE = -1.01;
//	private static final double VALUE = Double.MAX_VALUE;
	
	@Test
	public void test01() {
		String r;
		
		r = BigDecimal.valueOf(VALUE).toPlainString().replace('.', ',');
		
		System.out.println("test01: " + r);
	}
	
	@Test
	public void test02() {
		String r;
		
		r = BigDecimal.valueOf(VALUE).toString().replace('.', ',');
		
		System.out.println("test02: " + r);
	}
	
	@Test
	public void test03() {
		String r;
		
		r = Double.toString(VALUE).replace('.', ',');
		
		System.out.println("test03: " + r);
	}
	
	@Test
	public void test04() {
		String r;
		DecimalFormat decimalFormat;
		
		decimalFormat = new DecimalFormat("#.##");
		r = decimalFormat.format(VALUE).replace('.', ',');
		
		System.out.println("test04: " + r);
	}
	
	@Test
	public void test05() {
		String r;
		
		r = String.format( "%.2f", VALUE );
		
		System.out.println("test05: " + r);
	}
	
	@Test
	public void test06() {
		String r;
		

		long bits = Double.doubleToLongBits(VALUE);
        
		boolean negative = (bits & 0x8000000000000000L) != 0; 
		long exponent = bits & 0x7ff0000000000000L;
		long mantissa = bits & 0x000fffffffffffffL;

		
		r = negative + " " + Double.longBitsToDouble(exponent) + " " + Double.longBitsToDouble(mantissa);
		
		System.out.println("test06: " + r);
	}
	
	@Test
	public void test07() {
		String r;
		
		BigDecimal res = BigDecimal.ZERO;
		MathContext context = MathContext.UNLIMITED;
		final BigDecimal a = new BigDecimal( Math.E, context );
		final BigDecimal b = new BigDecimal( Math.E, context );
		final BigDecimal c = new BigDecimal( Math.E, context );
		for ( int i = 0; i < 10000000; ++i )
		{
		    final BigDecimal val = a.multiply( b, context ).add( c, context );
		    res = res.add( val, context );
		}
		
		r = res.toString();
		
		System.out.println("test07: " + r);
	}
	
	@Test
	public void test08() {
		String r;

		r = new BigDecimal(VALUE, MathContext.UNLIMITED).toString() + " " + new BigDecimal(VALUE, MathContext.DECIMAL32).toString() + " " + new BigDecimal(VALUE, MathContext.DECIMAL64).toString() + " "  + new BigDecimal(VALUE, MathContext.DECIMAL128).toString() + " ";

		
		System.out.println("test08: " + r);
	}
	
	@Test
	public void test09() {
		String r;

		double fractionalPart = VALUE % 1;
		long integralPart = (long) (VALUE - fractionalPart);

		r = integralPart + " " + fractionalPart;
		
		System.out.println("test09: " + r);
	}
	
	@Test
	public void test10() {
		String r;

		long bits = Double.doubleToLongBits(VALUE);
		System.out.format("%-20s    %-20s    %s%n%n", "hex bits:", "Double.toString(d):", "BigDecimal:");
		for (int i = -3; i <= 3; i++) {
			long newBits = bits + i;
			double newD = Double.longBitsToDouble(newBits);
			System.out.format("%-20x    %-20s    %s%n", newBits, newD, new BigDecimal(newD));
		}
		r = "";
		
		System.out.println("test10: " + r);
	}


}
