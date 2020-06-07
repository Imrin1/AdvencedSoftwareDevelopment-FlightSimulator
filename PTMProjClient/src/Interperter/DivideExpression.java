package Interperter;

public class DivideExpression extends BinaryExpression{

	public DivideExpression(Expression left,Expression right) {
		super(left,right);
	}
	
	@Override
	public Double calculate() {
		return this.left.calculate()/this.right.calculate();
	}
}

