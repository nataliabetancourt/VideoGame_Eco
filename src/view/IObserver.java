package view;

public interface IObserver {
	void notifyMessage(Session session, String message);
}
