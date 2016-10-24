run: CalculatorFrame.java CalculatorEngine.java
	javac CalculatorEngine.java
	javac CalculatorFrame.java
	java CalculatorFrame
clean: 
	rm *.class
