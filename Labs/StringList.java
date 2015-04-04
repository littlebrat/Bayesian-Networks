package LinkedList;

public class StringList 
{
	protected ListNode root; 
	protected int length = 0;
	
	public StringList(){}
	
	public StringList(String s)
	{
		ListNode node = new ListNode(s);
		root = node;
	}
	 
	public void insert(String s)
	{
		ListNode aux=root;
		ListNode node = new ListNode(s);
		if(length==0)
		{
			root=node;
			length++;
			return;
		}
		while(aux.next!=null)
		{
			aux=aux.next;
		}
		aux.next=node;
		length++;
	}
	
	public int remove(String s)
	{
		ListNode aux=root;
		int occurrences=0;
		if(length==0)return 0;
		if(length==1)
		{
			if(aux.toString().equals(s))
			{
				root=null;
				length--;
				return 1;
			}
			else return 0;
		}
		ListNode previous=aux;
		aux=aux.next;
		while(aux.next!=null)
		{
			if(previous.toString().equals(s) && previous.equals(root)) 
			{
				root=aux;
				occurrences++;
				length--;
				previous=aux;
				aux=aux.next;
			}
			else if(aux.toString().equals(s))
			{
				aux=aux.next;
				previous.next=aux;
				occurrences++;
				length--;
			}
			else
			{
			previous=aux;
			aux=aux.next;
			}
		}
		if(aux.toString().equals(s))
		{
			previous.next=null;
			occurrences++;
			length--;
		}
		return occurrences;
	}
	
	public int length()
	{
		return length;
	}
	
	@Override
	public String toString() {
		String result = new String();
		ListNode aux = root;
		while(aux!=null)
		{
			result = result + aux.toString();
			if(aux.next==null)return result;
			else result=result+',';
			aux=aux.next;
			
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + ((root == null) ? 0 : root.hashCode());
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
		if (!(obj instanceof StringList)) {
			return false;
		}
		StringList other = (StringList) obj;
		if (length != other.length) {
			return false;
		}
		if (root == null) {
			if (other.root != null) {
				return false;
			}
		} else if (!root.equals(other.root)) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		StringList rec = new StringList();
		// First test
		rec.insert("one");
		rec.insert("two");
		rec.insert("three");
		System.out.println(rec.toString());
		//Second test
		StringList rec2 = new StringList();
		rec2.insert("one");
		rec2.insert("two");
		rec2.insert("three");
		System.out.println(rec==rec2);
		System.out.println(rec.equals(rec2));
	}
}
