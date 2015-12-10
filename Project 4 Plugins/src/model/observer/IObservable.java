package model.observer;

/**
 * This interface represents an Observable objects 
 * @param <O> Type of object parameter passed to observers
 */
public interface IObservable<O> {
	
	/**
	 * Notifies all observers of changes
	 */
	public void notifyObservers();
	
	/**
	 * 	Notifies all observers and passes an object in parameter
	 * @param object
	 */
	public void notifyObservers(O object);
	
//	public void error(String title, String message);
//	public void warning(String title, String message);

}
