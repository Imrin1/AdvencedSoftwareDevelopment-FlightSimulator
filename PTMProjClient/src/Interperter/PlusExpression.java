package Interperter;

public class PlusExpression extends BinaryExpression{

	
	
	
	
	public PlusExpression(Expression left,Expression right) {
		super(left,right);
	}
	
	@Override
	public Double calculate() {
	
		return this.left.calculate()+this.right.calculate();
	}
	
	
}
