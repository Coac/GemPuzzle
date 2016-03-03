package utils;

public class DataStructStats {
	private int addCount;
	private int removeCount;
	private int maximumSize;
	
	public DataStructStats(){
		this(0,0,0);
	}
	
	public DataStructStats(int addCount, int removeCount, int maxSize){
		this.addCount=addCount;
		this.removeCount=removeCount;
		this.maximumSize=maxSize;
	}
	
	public void addInc(){
		this.addCount++;
		this.maximumSize=Math.max(this.maximumSize, this.addCount);
	}
	
	public int getAddCount(){
		return this.addCount;
	}
	
	public void removeInc(){
		this.removeCount++;
	}
	
	public int getRemoveCount(){
		return this.removeCount;
	}
	
	public int getMaximumSize(){
		return this.maximumSize;
	}
	
	@Override
	public String toString() {
		String repr = "";
		repr += "Add count: "+this.addCount+"\n";
		repr += "Remove count: "+this.removeCount+"\n";
		repr += "Max struct size: "+this.maximumSize+"\n";
		return repr;
	}
}
