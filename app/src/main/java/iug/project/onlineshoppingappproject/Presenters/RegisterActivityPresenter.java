package iug.project.onlineshoppingappproject.Presenters;

public interface RegisterActivityPresenter {

    void checkUserRegistrationInput(String email,
                      String username,
                      String password,
                      String confirmPassword);

}
