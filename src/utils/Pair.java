package utils;

public class Pair<S, T> {
	
	private S left;
	private T right;
	
	public Pair(S left, T right) {
		this.left  = left;
		this.right = right;
	}
	
	public S getLeft() {
		return left;
	}
	public void setLeft(S left) {
		this.left = left;
	}
	public T getRight() {
		return right;
	}
	public void setRight(T right) {
		this.right = right;
	}

}
