package model.observer;


/**
 * This class represents an Observers
 * @param <O> The type of object passed in parameter to update
 */
public interface Observer<O> {
	
	/**
	 * Does action when observable notify this observer
	 * @param object The object to proccess (may be null)
	 */
	public void update(O object);

}
