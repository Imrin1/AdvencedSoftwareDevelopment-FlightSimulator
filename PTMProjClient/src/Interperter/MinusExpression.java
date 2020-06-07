package Interperter;

public class MinusExpression extends BinaryExpression {

	
	
	public MinusExpression(Expression left,Expression right) {
		super(left,right);
	}
	
	@Override
	public Double calculate() {
		return this.left.calculate()-this.right.calculate();
	}
}
