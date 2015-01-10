package stuff;
/* 
 * Name: Samantha Puder
 * 
 * */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;


public class MouseController implements MouseListener {
	private Calculator calculator; // The calculator this listener is associated with
	public MouseController(Calculator parent) {
		this.calculator = parent;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		JButton btnClicked = (JButton) evt.getSource();
		String buttonText = btnClicked.getText();
		
		if (buttonText.equals("C")) {
			// Clears all the users entries
			calculator.setEntireComputationField("");
			calculator.setCurrentItemField("");
			calculator.setOperationMode(' ');
		} else if (buttonText.equals("CE")) {
			// Clears the users last entry
			calculator.setCurrentItemField("");			
		}else if (buttonText.equals("=")) {
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
			newEntireComputationField += calculator.getCurrentItemField() + buttonText;
			calculator.setEntireComputationField(newEntireComputationField);
			
			// cleanup for a completely new calculation:
			calculator.setLeftHand(0); // resets the left hand as it no longer exists
			calculator.setOperationMode(' '); // resets the operation mode so the next time we do anything we know its a new calculation
			
			// need to show the user the result here, lets use the text field they're used to seeing input in!
			calculator.setCurrentItemField(Double.toString(result));
		} else if(buttonText.equals("<--")){
			// As long as the user entry isn't empty, clear the last number
			String text = calculator.getCurrentItemField();
			if (text.length() > 0 ){
				text = text.substring(0, text.length()-1);
				calculator.setCurrentItemField(text);
			}
		}else if (buttonText.equals("+/-")) {
			// Take the number in the user entry field and multiply it by -1 to get the result.
			String newCurrentItemField = calculator.getCurrentItemField().trim();
			if (newCurrentItemField.charAt(0) == '-') {
				newCurrentItemField = newCurrentItemField.substring(1);
			} else {
				newCurrentItemField = "-" + newCurrentItemField;
			}
			calculator.setCurrentItemField(newCurrentItemField);
			
		} else if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("/") || buttonText.equals("*")) {
			// Set the operation mode, so that we don't have redundancies in our code.
			char operation = calculator.getOperationMode();
			if (operation == ' ') { // No operation was seen yet
				double leftHand = Double.parseDouble(calculator.getCurrentItemField());
				calculator.setLeftHand(leftHand);
				calculator.setOperationMode(buttonText.charAt(0));
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
				newEntireComputationField += currentItemString + buttonText;
				calculator.setLeftHand(result);
				calculator.setOperationMode(buttonText.charAt(0));
				calculator.setEntireComputationField(newEntireComputationField);
				calculator.setCurrentItemField("");
			}
			
		} else { // number entered
			String text = calculator.getCurrentItemField();
			calculator.setCurrentItemField(text + buttonText);
		}
	}

	@Override
	// IGNORE. We don't care about this event.
	public void mouseEntered(MouseEvent evt) {}

	@Override
	// IGNORE. We don't care about this event.
	public void mouseExited(MouseEvent evt) {}

	@Override
	// IGNORE. We don't care about this event.
	public void mousePressed(MouseEvent evt) {}

	@Override
	// IGNORE. We don't care about this event.
	public void mouseReleased(MouseEvent evt) {}
}