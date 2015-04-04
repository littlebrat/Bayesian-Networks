package LinkedList;

public class ListNode {
	protected String s;
	protected ListNode next=null;
	
	public ListNode(String myString)
	{
		s=myString;
	}
	
	public ListNode(String myString, ListNode previousNode)
	{
		previousNode.next=this;
		s=myString;
	}

	
	@Override
	public String toString() {
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ListNode)) {
			return false;
		}
		ListNode other = (ListNode) obj;
		if (next == null) {
			if (other.next != null) {
				return false;
			}
		} else if (!next.equals(other.next)) {
			return false;
		}
		if (s == null) {
			if (other.s != null) {
				return false;
			}
		} else if (!s.equals(other.s)) {
			return false;
		}
		return true;
	}

	
}
