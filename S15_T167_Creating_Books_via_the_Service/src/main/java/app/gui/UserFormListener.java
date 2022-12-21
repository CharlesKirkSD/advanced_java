package app.gui;

@FunctionalInterface
public interface UserFormListener {
	public void formSubmitted(String author, String title);
}
