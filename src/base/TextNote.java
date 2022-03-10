package base;

public class TextNote extends Note{
	String content;
	TextNote(String s){
		super(s);
	}
	TextNote(String title,String content){
		super(title);
		this.content=content;
	}
	public String getContent() {
		return this.content;
	}
}
