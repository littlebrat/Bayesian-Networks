package dag;

/**
 * Subclass of Node that represents a node as being the 
 * destination of another node (parent)
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 *
 * @param <T> represents type parameter
 */

public class Destination<T> extends Node<T>{
	
	/**
	 * Creates a new Destination object with the given element
	 * 
	 * @param n element of type T that is to be stored as a parameter of the destination node
	 */

	protected Destination(T element) {
		super(element);
		// TODO Auto-generated constructor stub
	}
	
}
