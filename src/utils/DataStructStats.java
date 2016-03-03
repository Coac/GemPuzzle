package utils;

public class DataStructStats {
	private int addCount;
	private int removeCount;
	private int maximumSize;
	private String name;

	public DataStructStats(String structName) {
		this(structName, 0, 0, 0);
	}

	public DataStructStats(String structName, int addCount, int removeCount, int maxSize) {
		this.addCount = addCount;
		this.removeCount = removeCount;
		this.maximumSize = maxSize;
		this.name = structName;
	}

	public void addInc() {
		this.addCount++;
		this.maximumSize = Math.max(this.maximumSize, this.addCount);
	}

	public int getAddCount() {
		return this.addCount;
	}

	public void removeInc() {
		this.removeCount++;
	}

	public int getRemoveCount() {
		return this.removeCount;
	}

	public int getMaximumSize() {
		return this.maximumSize;
	}

	@Override
	public String toString() {
		String repr = "Statistics of "+this.name+"\n";
		repr += "Add count: " + this.addCount + "\n";
		repr += "Remove count: " + this.removeCount + "\n";
		repr += "Max struct size: " + this.maximumSize + "\n";
		return repr;
	}
}
