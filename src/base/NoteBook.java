package base;
import java.util.*;
import java.util.Collections;
public class NoteBook {
	private ArrayList<Folder> folders;
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
}
