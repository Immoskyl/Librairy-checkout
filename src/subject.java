public interface subject {
	
	public void register(Observer c);
	public void unregister(Observer c);
	public void notifyObservers();

}
