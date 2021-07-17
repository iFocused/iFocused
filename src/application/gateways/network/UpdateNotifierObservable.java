package application.gateways.network;

public interface UpdateNotifierObservable {

	public void addObserver(ObserverNotifier observer);

	public void removeObserver(ObserverNotifier observer);

	public void setChanged();

}
