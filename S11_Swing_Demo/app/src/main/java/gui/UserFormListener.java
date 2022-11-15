package gui;

@FunctionalInterface
public interface UserFormListener {
	public void formSubmitted(String username, String password);
}
