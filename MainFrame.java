package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.*;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	
	private static final String[] KEYS = {"CE","C","Back","%",
		                                  "7","8","9","/",
		                                  "4","5","6","*",
		                                  "1","2","3","+",
		                                  "0",".","=","-"
		};
	
	
	private JButton[] buttons = new JButton[KEYS.length];
	
	private boolean firstNum = true;
	
	private double result;
	
	private String operator = "=";
	
	private boolean operatorFlag = false;
	
	private JTextField resultText;
	
	public MainFrame(){
		super();
		setResizable(false);
		setTitle("My calculator");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		setBounds(100,100,270,230);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    resultText = new JTextField();
	    resultText.setHorizontalAlignment(SwingConstants.RIGHT);
	    resultText.setText("0");
	    resultText.setEditable(false);
	    c.add(resultText,BorderLayout.NORTH);
	    JPanel calcPanel = new JPanel();
	    calcPanel.setBackground(new Color(100,200,255));
	    final GridLayout layout = new GridLayout(5,4);
	    layout.setHgap(1);
	    layout.setVgap(1);
	    calcPanel.setLayout(layout);
	    for(int i=0;i<KEYS.length;i++){
	    	buttons[i] = new JButton(KEYS[i]);
	    	buttons[i].addActionListener(this);
	    	calcPanel.add(buttons[i]);
	    }
	    c.add(calcPanel,BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		if("0123456789.".indexOf(command)!=-1){
			numberKey(command);
		}
		else if("CE".equals(command)){
			resultText.setText("0");
		}
		else if("C".equals(command)){
			resultText.setText("0");
			result =0;
			firstNum =true;
			operator = "=";
		}
		else if("Back".equals(command)){
			backPress(command);
		}
		else{
			operatorPress(command);
		}
	}

	public void operatorPress(String key) {
		// TODO Auto-generated method stub
		if("+".equals(operator)){
			result+=getNumber();
		}
		else if ("-".equals(operator)){
			result-=getNumber();
		}
		else if("*".equals(operator)){
			result*=getNumber();
		}
		else if("/".equals(operator)){
			result/=getNumber();
		}
		else if("%".equals(operator)){
			result/=100;
		}
		else if("=".equals(operator)){
			result = getNumber();
		}
		if(operatorFlag){
			long I=(long)result;
			double temp =result-I;
			if(temp==0){
				resultText.setText(String.valueOf(I));
			}
			else{
				resultText.setText(String.valueOf(result));
			}
		}
		operatorFlag = true;
		operator =key;
		firstNum =true;
	}

	public double getNumber() {
		// TODO Auto-generated method stub
		double result = 0;
		try{
			result = Double.valueOf(resultText.getText()).doubleValue();
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return result;
	}

	public void backPress(String key) {
		// TODO Auto-generated method stub
		String temp = resultText.getText();
		if(temp.length()>0){
			temp = temp.substring(0,temp.length()-1);
			if(temp.length()==0){
				temp ="0";
				firstNum = true;
				operator ="=";
			}			
		}
		resultText.setText(temp);
	}

	public void numberKey(String key) {
		// TODO Auto-generated method stub
		if(firstNum){
			resultText.setText(key);
			firstNum = false;
		}
		else if(".".equals(key) && resultText.getText().indexOf(".")==-1){
			String temp = resultText.getText();
			resultText.setText(temp+".");
		}
		else if(!".".equals(key)){
			String temp = resultText.getText();
			resultText.setText(temp+key);
		}
	}

}
