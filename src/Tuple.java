/**
 * 
 * A type of data structure that stores two objects, a and b, into the form (a, b)
 * The two objects can be of any type and can be accessed in the data structure using the getter functions getLeft()
 * and getRight(). 
 *
 * @param <L> Can be any Object
 * @param <R> Can be any Object
 */
public class Tuple<L,R>{

	private L left;
	private R right;
	
	/**
	 * Instantiates a new tuple object with L Object and R Object
	 * 
	 * Pre: The objects passed are not of type null.
	 * Post: returns a tuple object with (Object, Object)
	 * @param 	left Can be any Object
	 * @param 	right Can be any Object
	 */
	public Tuple (L left, R right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * This method returns the object that is classified to be on the "left" of the Tuple object.
	 * 
	 * @return The left Object in the Tuple data structure
	 */
	public L getLeft() {
		return left;
	}
	
	/**
	 * This method returns the object that is classified to be on the "right" of the Tuple object.
	 * 
	 * @return The right Object in the Tuple data structure
	 */
	public R getRight() {
		return right;
	}
	
	/**
	 * Formats how Tuple gets printed out.
	 */
	@Override
	public String toString() {
		return "[" + getLeft() + ", " + getRight() + "]";
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
	  if (!(o instanceof Tuple)) return false;
	  @SuppressWarnings("unchecked")
	Tuple<L,R> tupleo = (Tuple<L,R>) o;
	  return this.left.equals(tupleo.getLeft()) &&
	         this.right.equals(tupleo.getRight());
	  }
	
	/**
	 * Creates a copy of a Tuple
	 */
	@Override
	public Tuple<L,R> clone() {
		Tuple<L,R> t = new Tuple<L,R>(this.left, this.right);
		return t;
	}
}
