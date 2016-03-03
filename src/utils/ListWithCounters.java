package utils;
import java.util.LinkedList;

public class ListWithCounters<E> extends LinkedList<E>{
	private DataStructStats statistics;
	
	public ListWithCounters(){
		super();
		this.statistics = new DataStructStats();
	}
	
	@Override
	public void push(E e) {
		super.push(e);
		this.statistics.addInc();
	}
	
	@Override
	public E remove() {
		this.statistics.removeInc();
		return super.remove();
	}
	
	@Override
	public E poll() {
		this.statistics.removeInc();
		return super.poll();
	}
	
	@Override
	public E pop() {
		this.statistics.removeInc();
		return super.pop();
	}
	
	public DataStructStats getStatistics(){
		return statistics;
	}
}
