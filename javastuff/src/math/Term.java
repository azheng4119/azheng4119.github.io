package math;

import java.text.DecimalFormat;

public class Term{
	double coefficient;
	String variable;
	int exponent;
	boolean constant;//this is true if a Term has no variable expression
	//TODO: Write getters for all of the above fields

	public Term(double coefficient, String var, int exp){
		this.coefficient = coefficient;
		this.variable = var;
		this.exponent = exp;
		if (!var.equals("")){
			constant = false;
		}
		else {
			constant = true;
		}
		if (coefficient == 0){
			variable = "";
			exponent = 0;
		}
		//set the value of all the fields
		//if variable is non-empty, sets constant to 'false'
	}

	/**
	 *a constructor for constant: 
	 */
	Term(double constant){
		variable = "";
		exponent = 0;
		this.constant = true;
		coefficient = constant;
		//sets coefficient to constant, variable to "", exponent to "0" and constant to 'true')
	}
	/**
	 *Write getters for each field
	 *Note that the getter for the boolean should be called 'isConstant'
	 */ 

	public Term getAddInverse(){
		return new Term(coefficient*-1,variable,exponent);
		//returns the additive inverse of this Term
	}

	public int getDegree(){
		return exponent;
		//returns the exponent
	}

	public boolean isPositive(){
		if (coefficient >= 0) return true;
		return false;
		//returns true if the coefficient is positive (or zero), false otherwise
	}	


	public String toString(){
		DecimalFormat df = new DecimalFormat("###.#");
		String coeString = "";
		if (coefficient != 0) coeString = df.format(coefficient)+"";
		String varString = variable;
		String expString = "";
		if (exponent != 0) expString = "^"+exponent;
		if(coeString.equals("1")){
			coeString = "";
		}
		if(coeString.equals("-1")){
			coeString = "-";
		}
		if(expString.equals("^1")){
			expString = "";
		}
		return coeString + varString + expString;
	}

	public static boolean areLikeTerms(Term s, Term t){
		if (s.getDegree() == t.getDegree() && s.variable.equals(t.variable)) return true;
		return false;
	}
	/*	
	 * returns a new Term with same variable and exponent as s and t but combined coefficient
	 * @param s
	 * @param t
	 * @return
	 */

	public static Term combine(Term s, Term t){
		return new Term(s.coefficient + t.coefficient, s.variable, s.exponent);
	}



	public void setCoefficient(double d) {
		this.coefficient = d;
	}
}