import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class playerInfo {

	public String name,password,age,gender;
	File file;
	
	public playerInfo(String _name, String _password, String _age, String _gender){
		name=_name;
		password=_password;
		age=_age;
		gender=_gender;		
		
	}
	
	
	
	public void writeToDatabase(){
		
		String info= name+ " " +password+ " " +gender+ " " +age + " \n";
		try {
			
			
		Files.write(Paths.get("Players.txt"), info.getBytes(), StandardOpenOption.APPEND);
		
		
		
		}
		catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	 public int checkPlayer() {
		 if (!Files.exists(Paths.get("Players.txt")))
			try {
				Files.createFile(Paths.get("Players.txt"));
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		try(BufferedReader br = new BufferedReader(new FileReader("Players.txt"))) {
		    
			
		    String line = br.readLine();

		    while (line != null) {
		        if(line.split(" ")[0].equals(name))
		        	return 0;
		        line = br.readLine();
		    }
		 
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
			
		return 1;
	}



	public int getPlayer() {
		
try(BufferedReader br = new BufferedReader(new FileReader("Players.txt"))) {
		    
		    String line = br.readLine();

		    while (line != null) {
		        if(line.split(" ")[0].equals(name)){
		        	if (!password.equals(line.split(" ")[1]))
		        		return 0;
		        	age= line.split(" ")[3];
		        	gender=line.split(" ")[2];
		        	break;
		        }
		        line = br.readLine();
		    }
		 
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
			
		return 1;
		
		
		
	}
	
	
}
