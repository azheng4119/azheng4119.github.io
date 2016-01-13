package math;
import java.util.ArrayList;


public class Equation{

	private ArrayList<Term> leftSide;
	private ArrayList<Term> rightSide;

	public ArrayList<Term> getLeftSide() {
		return leftSide;
	}

	public ArrayList<Term> getRightSide() {
		return rightSide;
	}

	private boolean cancelRight;
	private ArrayList<Double> solution;
	//TODO: Make getters for all of the above
	
	public Equation(ArrayList<Term> leftSide, ArrayList<Term> rightSide){
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		cancelRight();
	}

	public void toZeroOnOneSide(ArrayList<Term> sideBeingCanceled){
		int s = sideBeingCanceled.size();
		for (int i = 0; i < s; i++){
			Term t = sideBeingCanceled.get(i);
			leftSide.add(t.getAddInverse());
			rightSide.add(t.getAddInverse());
		}
		simplify(rightSide);
		simplify(leftSide);
	}
	
	public void simplify(ArrayList<Term> side){
		for (int i = 0; i < side.size()-1; i++){
			Term t = side.get(i);
			for (int j = i+1; j < side.size(); j++){
				Term s = side.get(j);
				if (t!=s){
					if(Term.areLikeTerms(t, s)){
						Term newTerm = Term.combine(s, t);
						side.remove(s);
						side.remove(t);
						side.add(0,newTerm);
					}
				}
			}
		}
	}
	public void removeZeros(ArrayList<Term> side){
		simplify(rightSide);
		simplify(leftSide);
		for (int i = 0; i < side.size(); i++){
			if (side.get(i).coefficient == 0){
				side.remove(i);
				i--;
			}
		}
	}
	public boolean isLinear(){
		boolean temp = false;
		if ((getHighestDegreeTerm(leftSide).getDegree() == 1 
				|| getHighestDegreeTerm(rightSide).getDegree() == 1)
				&& (getLowestDegreeTerm(leftSide).getDegree() == 0
				|| getLowestDegreeTerm(leftSide).getDegree() == 1)){
			temp = true;
		}

		return temp;
		//returns 'true' if this equation is linear.
	
		//and every term is either constant or has the same variable
	}

	public boolean isQuadratic(){
		boolean temp = false;
		for (Term t : leftSide){
			if (t.getDegree() == 2) temp = true;
		}
		for (Term t : rightSide){
			if (t.getDegree() == 2) temp = true;
		}
		return temp;
		//returns 'true' if this equation is quadratic
		//and every term is either constant or has the same variable
	}

	public boolean isSolveable(){
		if (isLinear() || isQuadratic()) return true;
		return false;
		//returns 'true' if this equation is linear or quadratic, 'false' otherwise 
		//(because in this project we are not programming a means of solving anything other than linear and quadratic equations)
	}

	public boolean cancelRight(){
		Term temp1 = getHighestDegreeTerm(leftSide);
		Term temp2 = getHighestDegreeTerm(rightSide);
		if (temp1.getDegree() > temp2.getDegree()){
			cancelRight = true; 
			return cancelRight;
		}
		if (temp1.getDegree() < temp2.getDegree()) {
			cancelRight = false; 
			return cancelRight;
		}
		if (temp1.getDegree() == temp2.getDegree()){
			if (temp1.coefficient > temp2.coefficient) {
				cancelRight = true;
			}
			else{
				cancelRight = false;
			}
		}
		return cancelRight;
		//sets the value of cancelRight and
		//returns 'true' if the equation should be solved by subtracting terms from the right side, false if it is better to subtract terms on the left side
	}

	public String toString(){
		String temp = "";
		boolean left = true;
		boolean right = true;
		for (Term t : leftSide){
			if (t.coefficient != 0){
				if (t!= leftSide.get(0) && t.coefficient > 0 )temp+= "+";
				temp += t;
				left = false;
			}
		}
		if (left) temp += "0";
		temp += "=";
		for (Term t : rightSide){
			if (t.coefficient != 0){
				if (t!= rightSide.get(0) && t.coefficient > 0 )temp+= "+";
				temp += t;
				right = false;
			}
		}
		if (right) temp += "0";
		return temp;
		/**
		 *Take your time on this method!
		 *There are many things to consider:
		 *Every terms should be separated by a '+' UNLESS it has a negative coefficient. 
		 *When a term is negative, the negative sign will appear as a minus.
		 */
	}


	public static Term getHighestDegreeTerm(ArrayList<Term> side){
		int max = 0;
		int index = 0;
		for (int i = 0; i < side.size(); i++){
			if (side.get(i).getDegree() > max) index = i;
			max = side.get(index).getDegree();
		}
		return side.get(index);
		//returns the term in the ArrayList that is the highest degree.
		//example
		//3x^2 + 4x^3 - 12x + x^2
		//will return 4x^3 
	}

	public static Term getLowestDegreeTerm(ArrayList<Term> side){
		int low = 0;
		int index = 0;
		for (int i = 0; i < side.size(); i++){
			if (side.get(i).getDegree() < low) index = i;
			low = side.get(index).getDegree();
		}
		return side.get(index);
	}

	public Term makeConstant(ArrayList<Term> sideWithVariable){
		for(int i = 0; i < sideWithVariable.size(); i++){
			Term t = sideWithVariable.get(i);
			if (t.constant){
				return t;
			}
		}
		return null;
	}

	public Term makeTerm(ArrayList<Term> sideWithVariable){
		for(int i = 0; i < sideWithVariable.size(); i++){
			Term t = sideWithVariable.get(i);
			if (t.variable.equals("x")){
				return t;
			}
		}
		return null;
	}

	public void solveLinear(ArrayList<Term> sideWithVariable){
		Term constant = makeConstant(sideWithVariable);
		Term term = makeTerm(sideWithVariable);
		double termCoe = term.coefficient;
		sideWithVariable.remove(constant);
		if (cancelRight){
			rightSide.add(constant.getAddInverse());
			leftSide.remove(constant);
		}
		else {
			leftSide.add(constant.getAddInverse());
			rightSide.remove(constant);
		}
		if (term.coefficient!= 1 && term.coefficient != 0) {
			multiplyScalar(rightSide,(1/termCoe));
			multiplyScalar(leftSide,(1/termCoe));
		}
		removeZeros(leftSide);
		removeZeros(rightSide);
	}
	
	public void multiplyScalar(ArrayList<Term> side, double scalar){
		for (Term t :side){
			t.setCoefficient(scalar*t.coefficient);
		}
	}

	public boolean isCancelRight() {
		return cancelRight;
	}
}

