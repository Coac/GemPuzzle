package utils;

import java.util.PriorityQueue;

public class PriorityQueueWithCounters<E> extends PriorityQueue<E>{
private DataStructStats statistics;
	
	public PriorityQueueWithCounters(){
		super();
		this.statistics = new DataStructStats("Priority Queue");
	}
	
	@Override
	public boolean add(E e) {
		this.statistics.addInc();
		return super.add(e);
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
	
	public DataStructStats getStatistics(){
		return statistics;
	}
}
