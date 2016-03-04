package utils;

import java.util.LinkedList;

public class ListWithCounters<E> extends LinkedList<E> {

	private static final long serialVersionUID = -6279518338758836957L;

	private DataStructStats statistics;

	public ListWithCounters() {
		super();
		this.statistics = new DataStructStats("List");
	}

	@Override
	public void push(E e) {
		super.push(e);
		this.statistics.addInc();
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

	@Override
	public E pop() {
		this.statistics.removeInc();
		return super.pop();
	}

	public DataStructStats getStatistics() {
		return statistics;
	}
}
