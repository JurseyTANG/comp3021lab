package base;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Collections;
public class NoteBook implements java.io.Serializable {
	private ArrayList<Folder> folders;
	private static final long serialVersionUID=1L;
	NoteBook(){
		folders= new ArrayList<Folder>();
	}
	public boolean createTextNote(String folderName,String title) {
		TextNote note=new TextNote(title);
		return insertNote(folderName,note);
	}
	public boolean createImageNote(String folderName,String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName,note);
	}
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	public boolean insertNote(String folderName,Note note) {
		Folder f=null;
		for(Folder f1:folders) {
			if(f1.getName().equals(folderName)) {f=f1;break;}
		}
		if(f==null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		for(Note n:f.getNotes()) {
			if(note.getTitle().equals(n.getTitle())){
				System.out.println("Creating note "+note.getTitle()+" under folder "+folderName+" failed");
				return false;
			}
		}
		f.addNote(note);
		return true; 
	}
	public void sortFolders() {
		for(Folder f:folders) {f.sortNotes();}
		Collections.sort(folders);
	}
	public ArrayList<Note> searchNotes(String keywords){
		ArrayList<Note> result=new ArrayList<>();
		for(Folder f:folders) {
			result.addAll(f.searchNote(keywords));
		}
		return result;
	}
	public boolean createTextNote(String folderName,String title,String content) {
		TextNote note=new TextNote(title,content);
		return insertNote(folderName,note);
	}
	public boolean save(String file) {
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream out=new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public NoteBook(String file){
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream in=new ObjectInputStream(fis);
			NoteBook n=(NoteBook) in.readObject();
			this.folders=n.getFolders();
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
