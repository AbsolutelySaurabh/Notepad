package First;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.CaretListener;

import com.sun.javafx.tk.Toolkit;
import com.sun.prism.paint.Color;

import javafx.scene.layout.Border;

public class Flesch_Index1 extends JFrame {
	
	protected JLabel l1;
	protected JTextArea t1;
	protected JTextField t2;
	protected JButton b1;
	private JScrollPane scpane;
	
	Flesch_Index1(){
		//need this if no arguement passed;
	}
	Flesch_Index1(String s){
		
		//need this to overflow;
		super(s);
	}
	
	private int NumWords(String s){
		
		String[] l = s.split(" ");
		return l.length;
		
	}
	
	private int NumLines(String s ){
		
		String[] l = s.split("\n\r|\r|\n");
		return l.length;
	}
	
	private int NumSyllables(String s){
		
		String[] words = s.toLowerCase().split("[a-zA-Z]+");
		    int count=0;
		    List <String> tokens = new ArrayList<String>();
		    for(String word: words){
		            tokens = Arrays.asList(word.split("[bcdfghjklmnpqrstvwxyz]*[aeiou]+[bcdfghjklmnpqrstvwxyz]*"));
		            count+= tokens.size();

		            }
		    return count;
		
	}
	

	class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			JFrame j = new JFrame();
			j.setVisible(true);
			j.setSize(400, 400);
			
			String s = (t1.getText());
			int numWords = NumWords(s);
			int numLines = NumLines(s);
			int numSyllables = NumSyllables(s);
			
		    double Flesch_Score =  (206.835 - 1.015 * ((double)numWords/(double)numLines) - 84.6 * ((double)numSyllables/(double)numWords));
		    t2.setText(String.format( "%.2f", Flesch_Score ));		
		    
		    //t2.setText(Integer.toString(numLines));
		}
		
	}
	
	public void setComponents(){
		
		l1 = new JLabel("Flesch Index");
		t1 = new JTextArea( );//Use JtextArea if you want to write more and more texts as it allows start cursor from first line and press 
		
		b1 = new JButton("Flesch Index");
		t2 = new JTextField();
		t1.setLineWrap(true);//Why used these Lines; 
	    t1.setWrapStyleWord(true);
	    scpane = new JScrollPane(t1);
		
		setLayout(null);
		
		l1.setBounds(20,20,100,20);
		t1.setBounds(20, 40, 420, 440);
		b1.setBounds(445,40,120,40);
		t2.setBounds(20,500,70,30);
		
		//t1.addKeyListener(new tHandler());
		b1.addActionListener(new Handler());
		
		add(l1);
		add(b1);
		add(t1);
		add(t2);
		
	}
	
	public static void main(String[] args){
		
		
		Flesch_Index1 jf= new Flesch_Index1("Flesch Index Calculator");
		
		jf.setComponents();
		jf.setSize(600, 600);
		
		//jf.setBackground(Color.BLACK);
		//jf.setColor(Color.RED);
		jf.setVisible(true);
		//j.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private void setBackground(Color red) {
		// TODO Auto-generated method stub
		
	}
	

}
