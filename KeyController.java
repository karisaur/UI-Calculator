package stuff;
/* 
 * Name: Samantha Puder
 * 
 * */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {
private Calculator calculator;
	
	public KeyController(Calculator parent) {
		this.calculator = parent;
	}
	@Override
	// IGNORE. We don't care about this event.
	public void keyPressed(KeyEvent evt) {}

	@Override
	// TextFields add in the last key pressed, do this to remove the additional operand
	public void keyReleased(KeyEvent evt) {
		Character keyPressed = evt.getKeyChar();
		if (	 keyPressed == '.'
				|| keyPressed == '+'
				|| keyPressed == '-'
				|| keyPressed == '*'
				|| keyPressed == '/') {
			calculator.setCurrentItemField(" ");
		} else if (keyPressed == '='){
			String currentField = calculator.getCurrentItemField().substring(0, (calculator.getCurrentItemField().length())-1);
			calculator.setCurrentItemField(currentField);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent evt) {
		// What key did the user enter? Is it a valid option?
		Character keyPressed = evt.getKeyChar();
		
		if (Character.isDigit(keyPressed)) {
			// user hit a digit (0-9). Do the same thing we did for digits in the mouse

		} else if (Character.isDigit(keyPressed)
				|| keyPressed == '.'
				|| keyPressed == '+'
				|| keyPressed == '-'
				|| keyPressed == '*'
				|| keyPressed == '/') {
			// User hit an operation key (that wasn't equal). Do what we did in the mouse
			// listener for operation keys...
			// Set the operation mode, so that we don't have redundancies in our code.
			char operation = calculator.getOperationMode();
			if (operation == ' ') { // No operation was seen yet
				double leftHand = Double.parseDouble(calculator.getCurrentItemField());
				calculator.setLeftHand(leftHand);
				calculator.setOperationMode(keyPressed);
				String currentItemString = calculator.getCurrentItemField();
				if (currentItemString.startsWith("-")) { // Check for negative numbers, so we can display them nicely
					currentItemString = "(" + currentItemString + ")";
				}
				String newEntireComputationString = currentItemString + calculator.getOperationMode();
				calculator.setEntireComputationField(newEntireComputationString);
				calculator.setCurrentItemField(" ");
				
			} else { // Some operation was in progress
				double result = 0;
				double leftHand = calculator.getLeftHand();
				double rightHand = Double.parseDouble(calculator.getCurrentItemField().trim());
				switch (operation) {
					case '*': result = leftHand * rightHand; break;
					case '/': result = leftHand / rightHand;  break;
					case '+': result = leftHand + rightHand; break;
					case '-': result = leftHand - rightHand; break;
					default: break;
				}
				
				String currentItemString = Double.toString(rightHand);
				if (currentItemString.startsWith("-")) { // Check for negative numbers, so we can display them nicely
					currentItemString = "(" + currentItemString + ")";
				}
				// display the current computation string and clears out the operation and input field for new entries
				String newEntireComputationField = calculator.getEntireComputationField();
				newEntireComputationField += currentItemString;
				calculator.setLeftHand(result);
				calculator.setOperationMode(keyPressed);
				calculator.setEntireComputationField(newEntireComputationField);
				calculator.setCurrentItemField("");
			}
		} else if (keyPressed.equals('=')
				|| evt.getKeyCode() == 10 
				|| evt.getKeyCode()	== 13) {
			// User hit the enter or equals keys. Do the same thing as we did for the = button 
			double leftHand = calculator.getLeftHand();
			char finalOperation = calculator.getOperationMode();
			double rightHand = Double.parseDouble(calculator.getCurrentItemField());
			System.out.println(rightHand);
			double result = 0.0f;
			switch (finalOperation) {
				case '*': result = leftHand * rightHand; break;
				case '/': result = leftHand / rightHand; break;
				case '+': result = leftHand + rightHand; break;
				case '-': result = leftHand - rightHand; break;
				default: break;
			}
			// add the users last entry to the display box
			String newEntireComputationField = calculator.getEntireComputationField();
			newEntireComputationField += calculator.getCurrentItemField() + keyPressed;
			calculator.setEntireComputationField(newEntireComputationField);
			
			// cleanup for a completely new calculation:
			calculator.setLeftHand(0); // resets the left hand as it no longer exists
			calculator.setOperationMode(' '); // resets the operation mode so the next time we do anything we know its a new calculation
			
			// need to show the user the result here, lets use the text field they're used to seeing input in!
			calculator.setCurrentItemField(Double.toString(result));
		} else if(keyPressed.equals("<--")){
			// As long as the user entry isn't empty, clear the last number
			String text = calculator.getCurrentItemField();
			if (text.length() > 0 ){
				text = text.substring(0, text.length()-1);
				calculator.setCurrentItemField(text);
			}
			
		} else if (evt.getKeyCode() == 8) { // BACKSPACE
			// User hit the backspace key. Do the same thing we did for the <-- button in the 
			// apparently backspace is built in
			
		} // Done. There is no appropriate key to map to the clear function, really.
	}
}
