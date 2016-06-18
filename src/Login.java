import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JFrame Rframe;
	JFrame winnerBoard;
	JFrame timeStas;
	JFrame stas;
	JTextField field;
	JLabel l;
	JPasswordField pass;
	JTextField time;
	JButton button;
	JButton REGbutton;
	JButton GOreg;
	JButton HighScores;
	JButton timeButt;
	JButton back;
	JButton StasButt;
	JComboBox<String> gen;
	JComboBox<String> dif;
	JComboBox<String> player;
	JTextField age;
	static int com=1;
	Map<String, String> HighSocre;
	Map<String, String> maxTime;
	Map<String, String> firstTIME;
	Map<String, String> mapAge;
	Map<String, String> mapGender;
	
	public Login() {
		
		frame = new JFrame("Pacman");
		frame.getContentPane().setBackground(Color.yellow);
		frame.setSize(550, 400);
		frame.setLocation(300, 200);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		REGbutton=new JButton("Register");
		REGbutton.setSize(REGbutton.getPreferredSize());
		REGbutton.setLocation(265, 20);
		frame.add(REGbutton);
		REGbutton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				register();
				
			}
		});
		
		HighScores=new JButton("High Scores");
		HighScores.setSize(HighScores.getPreferredSize());
		HighScores.setLocation(150, 20);
		frame.add(HighScores);
		HighScores.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				getHighScores();
				
			}
		});
		
		timeButt=new JButton("Time Stas");
		timeButt.setSize(timeButt.getPreferredSize());
		timeButt.setLocation(50, 20);
		frame.add(timeButt);
		timeButt.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				getTime();
				
			}
		});
		
		StasButt=new JButton("Age and gender Stas");
		StasButt.setSize(StasButt.getPreferredSize());
		StasButt.setLocation(360, 20);
		frame.add(StasButt);
		StasButt.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				getStas();
				
			}
		});
		
		
		
		
		button=new JButton("GO");
		button.setSize(button.getPreferredSize());
		button.setLocation(220, 300);
		frame.add(button);
		button.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				playerInfo gameNow;
				if (field.getText().equals("Computer")){
				 gameNow=new playerInfo(field.getText()+com, "Computer", "Computer", null);
				 
				while (gameNow.checkPlayer()==0){
					com++;
					gameNow.name=field.getText()+com;
				}
				gameNow.writeToDatabase();
				frame.setVisible(false);
				new GameFrame(gameNow.name,time.getText(),dif.getSelectedItem().toString());
					
				}
				else{
				 gameNow=new playerInfo(field.getText(), pass.getText(), null, null);
				if (gameNow.checkPlayer()==1){
					JOptionPane.showMessageDialog(null, "User name not exsice", "Warning", JOptionPane.INFORMATION_MESSAGE);
				   
				}
				else if(gameNow.getPlayer()==0)
					JOptionPane.showMessageDialog(null, "Password in correct", "Warning", JOptionPane.INFORMATION_MESSAGE);
					
				
				else{
					frame.setVisible(false);
					new GameFrame(gameNow.name,time.getText(),dif.getSelectedItem().toString());
				}
				}
				
			}
		});
		
		

		l = new JLabel("Gamd settings");
		l.setLocation(200,70);
		l.setSize(l.getPreferredSize());
		frame.add(l);
		
		l = new JLabel("Player");
		l.setLocation(10,100);
		l.setSize(l.getPreferredSize());
		frame.add(l);
		
		player=new JComboBox<String>();
		player.addItem("Human");
		player.addItem("Computer");
		player.setSize(player.getPreferredSize());
		player.setLocation(100,100);
		player.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (player.getSelectedItem().toString().equals("Computer")){
		        field.setText("Computer");
		        field.setEditable(false);
		        pass.setText("Computer");
		        pass.setEditable(false);
		    	}
		    	else{
		    		field.setText("");
		    		 field.setEditable(true);
			        pass.setText("");
			        pass.setEditable(true);
		    		
		    	}
		    }
		});
		
		
		
		
		frame.add(player);
		
		l = new JLabel("Difficult");
		l.setLocation(10,200);
		l.setSize(l.getPreferredSize());
		frame.add(l);
		
		dif=new JComboBox<String>();
		dif.addItem("Eazy");
		dif.addItem("Hard");
		dif.setSize(dif.getPreferredSize());
		dif.setLocation(100,200);
		frame.add(dif);
		
		l = new JLabel("Enter name");
		l.setLocation(10,140);
		l.setSize(l.getPreferredSize());
		frame.add(l);
		
		
		field=new JTextField();
		field.setColumns(25);
		field.setSize(field.getPreferredSize());
	
		field.setLocation(100,140);
		frame.add(field);
		

		l = new JLabel("Password");
		l.setLocation(10,170);
		l.setSize(l.getPreferredSize());
		frame.add(l);
		
		l = new JLabel("Ghost time");
		l.setLocation(10,230);
		l.setSize(l.getPreferredSize());
		frame.add(l);
		
		
		time=new JTextField();
		time.setColumns(5);
		time.setText("0");
		time.setSize(time.getPreferredSize());
	
		time.setLocation(100,230);
		frame.add(time);
		
		pass=new JPasswordField();
		pass.setColumns(25);
		pass.setSize(pass.getPreferredSize());
	
		pass.setLocation(100,170);
	
		frame.add(pass);
		
		frame.setVisible(true);
	}
	
	protected void getStas() {

		stas = new JFrame("Age and gender Stas");
		stas.getContentPane().setBackground(Color.yellow);
		stas.setSize(400,350);
		stas.setLocation(300, 200);
		stas.setLayout(null);
		stas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		double man=0,women=0,com=0;
		int TEN=0,TWENTY=0,TWENTPLUS=0;
		back=new JButton("Back");
		back.setSize(back.getPreferredSize());
		back.setLocation(130, 250);
		stas.add(back);
		back.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				stas.setVisible(false);
				frame.setVisible(true);
				
			}
		});
		
		l = new JLabel("Gender");
		l.setLocation(150,10);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel("Age");
		l.setLocation(150,100);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		
		mapAge=new HashMap<String, String>();
		mapGender=new HashMap<String, String>();
		getPlayerInfo(mapAge,"Players.txt",3);
		getPlayerInfo(mapGender,"Players.txt",2);
		
		for (Map.Entry<String, String> entry : mapAge.entrySet())
		{
			if(!entry.getValue().equals("Computer") && Integer.parseInt(entry.getValue())<=10)
				TEN++;
			else if(!entry.getValue().equals("Computer") && Integer.parseInt(entry.getValue())<=20)
				TWENTY++;
			else if(!entry.getValue().equals("Computer"))
				TWENTPLUS++;
						
		}
		for (Map.Entry<String, String> entry : mapGender.entrySet())
		{
			if(entry.getValue().equals("Male"))
				man++;
			else if(entry.getValue().equals("Female"))
				women++;
			else
				com++;
						
		}
		
		
		l = new JLabel("Man");
		l.setLocation(80,30);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel(Double.toString((man/(man+women+com))*100)+ "%");
		l.setLocation(80,60);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel("Woman");
		l.setLocation(150,30);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel(Double.toString((women/(man+women+com))*100)+ "%");
		l.setLocation(150,60);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel("Computer");
		l.setLocation(220,30);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel(Double.toString((com/(man+women+com))*100)+ "%");
		l.setLocation(220,60);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		
		l = new JLabel("X<=10");
		l.setLocation(80,120);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel(Integer.toString(TEN));
		l.setLocation(80,150);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel("10<=X<20");
		l.setLocation(150,120);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel(Integer.toString(TWENTY));
		l.setLocation(150,150);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel("X>=20");
		l.setLocation(220,120);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		l = new JLabel(Integer.toString(TWENTPLUS));
		l.setLocation(220,150);
		l.setSize(l.getPreferredSize());
		stas.add(l);
		
		
			stas.setVisible(true);
	}

	protected void getTime() {

		timeStas = new JFrame("Time Stas");
		timeStas.getContentPane().setBackground(Color.yellow);
		timeStas.setSize(400,600);
		timeStas.setLocation(300, 200);
		timeStas.setLayout(null);
		timeStas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String highName="",highScore="0",firsthighScore="0";
		
		back=new JButton("Back");
		back.setSize(back.getPreferredSize());
		back.setLocation(130, 500);
		timeStas.add(back);
		back.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				timeStas.setVisible(false);
				frame.setVisible(true);
				
			}
		});
		
		l = new JLabel("Name");
		l.setLocation(80,10);
		l.setSize(l.getPreferredSize());
		timeStas.add(l);
		
		l = new JLabel("Longest Game");
		l.setLocation(160,10);
		l.setSize(l.getPreferredSize());
		timeStas.add(l);
		
		l = new JLabel("Time for first life lost");
		l.setLocation(260,10);
		l.setSize(l.getPreferredSize());
		timeStas.add(l);
		
		maxTime=new HashMap<String, String>();
		firstTIME=new HashMap<String, String>();
		getPlayerInfo(maxTime,"TimeDead.txt",1);
		getPlayerInfo(firstTIME,"firstTimeDead.txt",1);
		for (int i=1;i<=1000;i++){
		for (Map.Entry<String, String> entry : maxTime.entrySet())
		{
			if(Integer.parseInt(entry.getValue())>Integer.parseInt(highScore)){
					highName=entry.getKey();
					highScore=entry.getValue();
			}
		}
		
		
		
		if (highScore.equals("0"))
			break;
		l = new JLabel(highName);
		l.setLocation(80,10+30*i);
		l.setSize(l.getPreferredSize());
		timeStas.add(l);
		
		l = new JLabel(highScore + "Sec");
		l.setLocation(160,10+30*i);
		l.setSize(l.getPreferredSize());
		timeStas.add(l);
		highScore="0";
		maxTime.put(highName, "0");
		
		l = new JLabel(firstTIME.get(highName) + "Sec");
		l.setLocation(260,10+30*i);
		l.setSize(l.getPreferredSize());
		timeStas.add(l);
		
		firstTIME.put(highName, "0");
		}
		
		timeStas.setVisible(true);
		
	}

	protected void getHighScores() {
		
		winnerBoard = new JFrame("High Scores");
		winnerBoard.getContentPane().setBackground(Color.yellow);
		winnerBoard.setSize(300,400);
		winnerBoard.setLocation(300, 200);
		winnerBoard.setLayout(null);
		winnerBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String highName="",highScore="0";
		
		back=new JButton("Back");
		back.setSize(back.getPreferredSize());
		back.setLocation(120, 300);
		winnerBoard.add(back);
		back.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				winnerBoard.setVisible(false);
				frame.setVisible(true);
				
			}
		});
		
		l = new JLabel("Name");
		l.setLocation(80,10);
		l.setSize(l.getPreferredSize());
		winnerBoard.add(l);
		
		l = new JLabel("Score");
		l.setLocation(160,10);
		l.setSize(l.getPreferredSize());
		winnerBoard.add(l);
		
		HighSocre=new HashMap<String, String>();
		getPlayerInfo(HighSocre,"Points.txt",1);
		for (int i=1;i<=10;i++){
		for (Map.Entry<String, String> entry : HighSocre.entrySet())
		{
			if(Integer.parseInt(entry.getValue())>Integer.parseInt(highScore)){
					highName=entry.getKey();
					highScore=entry.getValue();
			}
		}
		if (highScore.equals("0"))
			break;
		l = new JLabel(highName);
		l.setLocation(80,10+30*i);
		l.setSize(l.getPreferredSize());
		winnerBoard.add(l);
		
		l = new JLabel(highScore);
		l.setLocation(160,10+30*i);
		l.setSize(l.getPreferredSize());
		winnerBoard.add(l);
		highScore="0";
		HighSocre.put(highName, "0");
		}
		
		winnerBoard.setVisible(true);
	}

	private void register (){
		
		Rframe = new JFrame("Regitser");
		Rframe.getContentPane().setBackground(Color.yellow);
		Rframe.setSize(500, 200);
		Rframe.setLocation(300, 200);
		Rframe.setLayout(null);
		Rframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l = new JLabel("Enter name");
		l.setLocation(10,10);
		l.setSize(l.getPreferredSize());
		Rframe.add(l);
		
		back=new JButton("Back");
		back.setSize(back.getPreferredSize());
		back.setLocation(300, 130);
		Rframe.add(back);
		back.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				Rframe.setVisible(false);
				frame.setVisible(true);
				
			}
		});
		
		field=new JTextField();
		field.setColumns(25);
		field.setSize(field.getPreferredSize());
	
		field.setLocation(150,10);
		Rframe.add(field);
		

		l = new JLabel("Enter password");
		l.setLocation(10,40);
		l.setSize(l.getPreferredSize());
		Rframe.add(l);
		
		pass=new JPasswordField();
		pass.setColumns(25);
		pass.setSize(pass.getPreferredSize());
	
		pass.setLocation(150,40);
	
		Rframe.add(pass);
		
		
		
		l = new JLabel("Gender");
		l.setLocation(10,70);
		l.setSize(l.getPreferredSize());
		Rframe.add(l);
		
		gen=new JComboBox<String>();
		gen.addItem("Female");
		gen.addItem("Male");
		gen.setSize(pass.getPreferredSize());
		gen.setLocation(150,70);
		Rframe.add(gen);
		
		l = new JLabel("Age");
		l.setLocation(10,100);
		l.setSize(l.getPreferredSize());
		Rframe.add(l);
		
		
		age=new JTextField();
		age.setColumns(25);
		age.setSize(age.getPreferredSize());
	
		age.setLocation(150,100);
		Rframe.add(age);
		
		GOreg=new JButton("Save");
		GOreg.setSize(REGbutton.getPreferredSize());
		GOreg.setLocation(200, 130);
		Rframe.add(GOreg);
		GOreg.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
							
				playerInfo player= new playerInfo(field.getText(), pass.getText(), age.getText(), gen.getSelectedItem().toString());
				if (player.checkPlayer()==1){
				player.writeToDatabase();
				Rframe.setVisible(false);
				frame.setVisible(true);
				}
				else if (player.name.contains(("Computer")))
					JOptionPane.showMessageDialog(null, "The name Computer is not allowed", "Warning", JOptionPane.INFORMATION_MESSAGE);
				else{
			        JOptionPane.showMessageDialog(null, "User name exsice", "Warning", JOptionPane.INFORMATION_MESSAGE);

				}
				
				
			}
		});
		
		Rframe.setVisible(true);
	}
	
	private void getPlayerInfo(Map<String, String> map, String path,int info){
		if (!Files.exists(Paths.get(path))){
			JOptionPane.showMessageDialog(null, "No socres", "Warning", JOptionPane.INFORMATION_MESSAGE);
			}
		else{
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    
			
		    String line = br.readLine();

		    while (line != null) {
		        if (!map.containsKey(line.split(" ")[0]))
		        	map.put(line.split(" ")[0], line.split(" ")[info]);
		        else{
		        	if(Integer.parseInt(map.get(line.split(" ")[0]))<Integer.parseInt(line.split(" ")[info]))
		        		map.put(line.split(" ")[0], line.split(" ")[info]);
		        }
		        line = br.readLine();
		    }
		 
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}}
		
		
	}
	public static void main(String args[]) {
		new Login();
	}
	

}
