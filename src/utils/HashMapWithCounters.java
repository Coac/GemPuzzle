package utils;

import java.util.HashMap;

public class HashMapWithCounters<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 348408764236186110L;

	private DataStructStats statistics;

	public HashMapWithCounters() {
		super();
		this.statistics = new DataStructStats("HashMap");
	}

	@Override
	public V put(K key, V value) {
		this.statistics.addInc();
		return super.put(key, value);
	}

	@Override
	public V remove(Object key) {
		this.statistics.removeInc();
		return super.remove(key);
	}

	public DataStructStats getStatistics() {
		return statistics;
	}
}
