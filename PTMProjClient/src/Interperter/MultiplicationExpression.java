package Interperter;

public class MultiplicationExpression extends BinaryExpression{

	
	
	
	

	public MultiplicationExpression(Expression left,Expression right) {
		super(left,right);
	}
	
	@Override
	public Double calculate() {
		return this.left.calculate()*this.right.calculate();
	}
}
