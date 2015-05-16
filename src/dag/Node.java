package dag;

/**
 * Class that represents a node
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 *
 * @param <T> represents type parameter
 */

public class Node<T> {
	
	/**
	 * Parameter of type T that represents the node
	 */
	private T val;
	
	/**
	 * Creates new Node object with the given element
	 * 
	 * @param element 	element of type T that is to be stored as a parameter of the node
	 */
	
	protected Node(T element){
		val=element;
	}
	
	/**
	 * Gets the value of the node
	 * 
	 * @return	value of type T of the node
	 */
	
	protected T getValue(){
		return val;
	}

	 /**
	 *{@inheritDoc}
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((val == null) ? 0 : val.hashCode());
		return result;
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Node<T> other = (Node<T>) obj;
		if (val == null) {
			if (other.val != null)
				return false;
		} else if (!val.equals(other.val))
			return false;
		return true;
	}
	
}
