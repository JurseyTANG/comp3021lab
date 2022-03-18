package base;
import java.util.Date;
public class Note implements Comparable<Note>,java.io.Serializable{
	private String title;
	private Date date;
	private static final long serialVersionUID=1L;
	public Note(String t) {
		this.title=t;
		this.date = new Date(System.currentTimeMillis());
	}
	public String getTitle() {return title;
	}
	public Date getDate() {return date;}
	public String toString() {
		return date.toString()+'\t'+title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public int compareTo(Note o) {
		if(o.getDate().compareTo(this.date)>0) {return 1;}
		else if(o.getDate().compareTo(this.date)<0) {return -1;}
		else {return 0;}
	}
}
