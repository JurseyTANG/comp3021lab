package base;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TextNote extends Note{
	String content;
	TextNote(String s){
		super(s);
	}
	public TextNote(File f) {
		super(f.getName());
		this.content=getTextFromFile(f.getAbsolutePath());
	}
	private String getTextFromFile(String absolutePath) {
		String result="";
		result=this.getContent();
		return result;
	}
	TextNote(String title,String content){
		super(title);
		this.content=content;
	}
	public String getContent() {
		return this.content;
	}

	public void exportTextToFile(String pathFolder) {
		if(pathFolder=="") {pathFolder=".";}
		File file = new File(pathFolder+ File.separator+this.getTitle().replaceAll(" ", "_")+".txt");
		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
		fos = new FileOutputStream(pathFolder+ File.separator+this.getTitle().replaceAll(" ", "_")+".txt");
		out = new ObjectOutputStream(fos);
		out.writeObject(file);
		out.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}


	}
}
