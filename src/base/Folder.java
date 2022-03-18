package base;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Folder implements Comparable<Folder>,java.io.Serializable{
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID=1L;
	public Folder(String name) {
		this.name=name;
		notes = new ArrayList<Note> ();
	}
	public void addNote(Note note) {
		notes.add(note);
	}
	public String getName() {
		return name;
	}
	public ArrayList<Note> getNotes(){
		return notes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		int nText=0;
		int nImage=0;
		for(Note note:notes) {
			if(note instanceof TextNote) {nText++;}
			if(note instanceof ImageNote) {nImage++;}
		}
		return name+":"+nText+":"+nImage;
	}
	
	@Override
	public int compareTo(Folder o) {
		if(o.getName().compareTo(this.name)<0) {return 1;}
		else if(o.getName().compareTo(this.name)>0) {return -1;}
		else {return 0;}
	}
	public void sortNotes() {
		Collections.sort(notes);
	}
	public List<Note> searchNote(String keywords){
		ArrayList<Note> result=new ArrayList<Note>();
		String[] words=keywords.split(" ");
		ArrayList<String> and_list=new ArrayList<String>();
		for (int i=0;i<words.length;i++) {
			if(i+1<words.length && words[i+1].equalsIgnoreCase("or")) {
				continue;
			}
			else if(words[i].equalsIgnoreCase("or")) {
				continue;
			}
			else if(i-1>=0 && words[i-1].equalsIgnoreCase("or")) {
				continue;
			}
			else {
				and_list.add(words[i]);
			}
		}
		if(and_list.size()>=1) {
			result=searchKeyword(and_list.get(0));
			for(int i=1;i<and_list.size();i++) {
				result.retainAll(searchKeyword(and_list.get(i)));
			}
		}
		int i=0;
		boolean flag=false;
		while(i<words.length){
			if(i+1<words.length && !words[i+1].equalsIgnoreCase("or")) {
				i++;continue;
			}
			ArrayList<Note> temp=new ArrayList<Note>();
			if(i+1<words.length && words[i+1].equalsIgnoreCase("or")) {
				temp=searchKeyword(words[i]);
			}
			i=i+2;
			while(words[i-1].equalsIgnoreCase("or")){
				temp.addAll(searchKeyword(words[i]));
				if(i+2<words.length && words[i+1].equalsIgnoreCase("or")) {
					i=i+2;
				}
				else {
					i++;
					break;
				}
			}
			if(!flag && result.size()==0) {result=temp;}
			flag=true;
			result.retainAll(temp);
		}
		return result;
	}
	private ArrayList<Note> searchKeyword(String word){
		ArrayList<Note> result=new ArrayList<Note>();
		for(Note note: notes) {
			if(note instanceof ImageNote) {
				if(note.getTitle().toLowerCase().contains(word.toLowerCase())) {
					result.add(note);
				}
			}else if (note instanceof TextNote){
				if(note.getTitle().toLowerCase().contains(word.toLowerCase())
						|| ((TextNote) note).getContent().toLowerCase().contains(word.toLowerCase())) {
					result.add(note);
				}
			}
		}
		return result;
	}
}
