package First;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Handler;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import javafx.scene.layout.Border;
public class TextEditor extends JFrame {
	
	private JTextArea t;
	private JMenuBar menuBar;
	private JMenu fileM,editM,viewM;
	private JScrollPane scpane;
	private JMenuItem exitI,cutI,copyI,pasteI,selectI,saveI,countWords;
	private String pad;//the selected ones
	private JToolBar toolBar;
	private JTextField t1;
    private JLabel l1,l2;//l2 used for error
	
	//constructor needed
	TextEditor()
	{
		
	}
	TextEditor(String s){
		
		super(s);
	}
	
	public void setComponents(){
	
		setLocationRelativeTo(null);
	    Container pane = getContentPane();
	    pane.setLayout(new BorderLayout());
	    pad=" ";
	    
		t = new JTextArea();
		menuBar = new JMenuBar();
		fileM = new JMenu("File");
		editM = new JMenu("Edit");
		viewM = new JMenu("View");
		
		scpane = new JScrollPane(t);
		exitI = new JMenuItem("Exit");
		pasteI = new JMenuItem("Paste");
		cutI = new JMenuItem("Cut");
		copyI = new JMenuItem("Copy");
		selectI = new JMenuItem("Select");
		saveI = new JMenuItem("Save");
		countWords = new JMenuItem("Count Words");
		toolBar = new JToolBar();
		
		t.setLineWrap(true);//these are to allow neline when line completed
		t.setWrapStyleWord(true);
		
		setJMenuBar(menuBar);
	    menuBar.add(fileM);
	    menuBar.add(editM);
	    menuBar.add(viewM);
		
		fileM.add(saveI);
		fileM.add(exitI);
		
		editM.add(cutI);
		editM.add(pasteI);
		editM.add(copyI);
		
		viewM.add(countWords);
		
		pane.add(scpane,BorderLayout.CENTER);
		pane.add(toolBar,BorderLayout.SOUTH);
		
		copyI.addActionListener(new Handler());
		pasteI.addActionListener(new Handler());
		cutI.addActionListener(new Handler());
		exitI.addActionListener(new Handler());
		
		countWords.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				try{	
				
				    pad = t.getSelectedText();
				    if(pad!=" "){
				    
				    TextEditor j = new TextEditor("Count Words");			
				    j.setSize(270,150);
			    
				    int n = getNumWords(pad);
		
				    j.jsetComponents(n);
				    j.setVisible(true);
				    }
				}
				catch(RuntimeException e1){
			
					TextEditor error = new TextEditor("ERROR");
					
					error.setLocationRelativeTo(null);//This will make thw window to appear in the center;
				    error.setLayout(null);
				    
					error.setSize(250,100);
					error.setVisible(true);
					l2 = new JLabel("Select Text");
					l2.setBounds(80,10,100,40);
					error.add(l2);
					
				}		
			}
		});
	}
   
	public int getNumWords(String s){
		
		String[] l = s.split(" ");
		return l.length;
		
	}
   public void jsetComponents(int n){
		
	    setLocationRelativeTo(null);//This will make thw window to appear in the center;
	    setLayout(null);
		//t1 = null;
	    l1 = new JLabel("Total Words :");
	    
	    l1.setBounds(60,10,100,10);
		t1= new JTextField();
		t1.setBounds(60,25,120,40);
		t1.setText(Integer.toString(n));
		
		add(t1);
		add(l1);
		

	}
	
	
	class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			JMenuItem choice = (JMenuItem)e.getSource();
			if(choice==copyI){
				
				pad= t.getSelectedText();
			}else
				if(choice==pasteI){
					t.insert(pad, t.getCaretPosition());
				}else
					if(choice== cutI){
						pad = t.getSelectedText();
						t.replaceRange("",t.getSelectionStart(),t.getSelectionEnd());
						
					}else
						if(choice==exitI){
							System.exit(0);
							
						}
		}
	}
	
	public static void main(String[] args){
		
		TextEditor jf = new TextEditor("Text Editor");
		jf.setSize(600,600);
		jf.setComponents();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//writing as jf. because this function is static;
		
		
	}
	

}
