package dag;

public class Node {
	private int val;
	
	protected Node(int n){
		val=n;
	}
	
	public int getValue(){
		return val;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + val;
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
		Node other = (Node) obj;
		if (val != other.val)
			return false;
		return true;
	}
	
}
