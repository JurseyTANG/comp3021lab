package lab6.java;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
public class Account {
	public int id;
	public int balance;
	public Account(int id, int balance) {
		this.id = id;
		this.balance = balance;
	}
	
	public void increment(int N) {
		balance+=N;
	}

	public static Consumer<Account> add100 = e->e.increment(100);

	public static Predicate<Account> lowerBound = a -> a.balance >=0;
	public static Predicate<Account> upperBound = a -> a.balance <=10000;
	public static Predicate<Account> checkBound = lowerBound.and(upperBound);
	
	interface AddMaker {
		Consumer<Account> make(int N);
	}
	public static AddMaker maker = N->(A->A.increment(N));

	public static int getMaxAccountID(List<Account> accounts) {
		Account maxOne = accounts.stream().reduce(new Account(0, -100), (a,b)->(a.balance>b.balance)?a:b);
		return maxOne.id;
	}
}