package interpreter;

public class Number implements Expression {
  
	public Number(Double x) {
		this.value=x;
	}
	private Double value;
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public Double calculate() {
		return this.value;
	}
}
