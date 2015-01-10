package stuff;
/* 
 * Name: Samantha Puder
 * 
 * */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {
    private JTextField textField;
    private JTextField entireComputation;
    private double leftHandValue;
    private double result;
    private char currentOperation = ' ';
    
    public Calculator() {
    	setTitle("EECS3461 Calculator");
    	this.setMinimumSize(new Dimension(330,370));
        getContentPane().setLayout(null);
        getContentPane().addKeyListener(new KeyController(this));
        
        entireComputation = new JTextField();
        entireComputation.setHorizontalAlignment(SwingConstants.RIGHT);
        entireComputation.setEditable(false);
        entireComputation.setBounds(10, 18, 290, 25);
        getContentPane().add(entireComputation);
        entireComputation.setColumns(10);
        
        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBounds(10, 42, 290, 32);
        getContentPane().add(textField);
        textField.setColumns(10);
        textField.addKeyListener(new KeyController(this));
        
        JButton btn7 = new JButton("7");
        btn7.addMouseListener(new MouseController(this));
        btn7.setBounds(10, 85, 50, 50);
        getContentPane().add(btn7);
        
        JButton btn8 = new JButton("8");
        btn8.addMouseListener(new MouseController(this));
        btn8.setBounds(70, 85, 50, 50);
        getContentPane().add(btn8);
        
        JButton btn9 = new JButton("9");
        btn9.addMouseListener(new MouseController(this));
        btn9.setBounds(130, 85, 50, 50);
        getContentPane().add(btn9);
        
        JButton btnAdd = new JButton("+");
        btnAdd.addMouseListener(new MouseController(this));
        btnAdd.setBounds(190, 85, 50, 50);
        getContentPane().add(btnAdd);
        
        JButton btnClear = new JButton("C");
        btnClear.addMouseListener(new MouseController(this));
        btnClear.setBounds(250, 85, 50, 50);
        getContentPane().add(btnClear);
        
        JButton btn4 = new JButton("4");
        btn4.addMouseListener(new MouseController(this));
        btn4.setBounds(10, 146, 50, 50);
        getContentPane().add(btn4);
        
        JButton btn5 = new JButton("5");
        btn5.addMouseListener(new MouseController(this));
        btn5.setBounds(70, 146, 50, 50);
        getContentPane().add(btn5);
        
        JButton btn6 = new JButton("6");
        btn6.addMouseListener(new MouseController(this));
        btn6.setBounds(130, 146, 50, 50);
        getContentPane().add(btn6);
        
        JButton btnMinus = new JButton("-");
        btnMinus.addMouseListener(new MouseController(this));
        btnMinus.setBounds(190, 146, 50, 50);
        getContentPane().add(btnMinus);
        
        JButton btnNP = new JButton("+/-");
        btnNP.addMouseListener(new MouseController(this));
        btnNP.setBounds(130, 268, 50, 50);
        getContentPane().add(btnNP);
        
        JButton btn1 = new JButton("1");
        btn1.addMouseListener(new MouseController(this));
        btn1.setBounds(10, 207, 50, 50);
        getContentPane().add(btn1);
        
        JButton btn2 = new JButton("2");
        btn2.addMouseListener(new MouseController(this));
        btn2.setBounds(70, 207, 50, 50);
        getContentPane().add(btn2);
        
        JButton btn3 = new JButton("3");
        btn3.addMouseListener(new MouseController(this));
        btn3.setBounds(130, 207, 50, 50);
        getContentPane().add(btn3);
        
        JButton btnMult = new JButton("*");
        btnMult.addMouseListener(new MouseController(this));
        btnMult.setBounds(190, 207, 50, 50);
        getContentPane().add(btnMult);
        
        JButton btnDiv = new JButton("/");
        btnDiv.addMouseListener(new MouseController(this));
        btnDiv.setBounds(190, 268, 50, 50);
        getContentPane().add(btnDiv);
        
        JButton btn0 = new JButton("0");
        btn0.addMouseListener(new MouseController(this));
        btn0.setBounds(10, 268, 50, 50);
        getContentPane().add(btn0);
        
        JButton btnDec = new JButton(".");
        btnDec.addMouseListener(new MouseController(this));
        btnDec.setBounds(70, 268, 50, 50);
        getContentPane().add(btnDec);
        
        JButton btnEqual = new JButton("=");
        btnEqual.addMouseListener(new MouseController(this));
        btnEqual.setBounds(250, 268, 50, 50);
        getContentPane().add(btnEqual);
        
        JButton btnBksp = new JButton("<--");
        btnBksp.addMouseListener(new MouseController(this));
        btnBksp.setBounds(250, 207, 50, 50);
        getContentPane().add(btnBksp);
        
        JButton btnCe = new JButton("CE");
        btnCe.addMouseListener(new MouseController(this));
        btnCe.setBounds(250, 146, 50, 50);
        getContentPane().add(btnCe);
        
        setVisible(true);
    }

    public void setCurrentItemField(String contents) {
    	this.textField.setText(contents);
    }
    
    public String getCurrentItemField() {
    	return this.textField.getText();
    }
    
    public void setEntireComputationField(String contents) {
    	this.entireComputation.setText(contents);
    }
    
    public String getEntireComputationField() {
    	return this.entireComputation.getText();
    }
    
    public double getLeftHand() {
    	return this.leftHandValue;
    }
    
    public void setLeftHand(double value) {
    	this.leftHandValue = value;
    }
    
    public void setOperationMode(char op) {
    	this.currentOperation = op;
    }
    
    public char getOperationMode() {
    	return this.currentOperation;
    }
    
    public double getResult(){
    	return this.result;
    }
    
    public void setResult(double value){
    	this.result = value;
    }
    
    public static void main(String[] args) {
        Calculator c = new Calculator();  
    }
}
