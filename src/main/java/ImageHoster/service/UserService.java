package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

    private static Pattern PASSWORD_PATTERN;
    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public boolean registerUser(User newUser) {
        String PASSWORD_REGEX ="^(?=.*?[A-Za-z])(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{3,}$";

        PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
        // Validate an password
        if (PASSWORD_PATTERN.matcher(newUser.getPassword()).matches()) {
            userRepository.registerUser(newUser);
            return true;
        }
        else {
            return false;
        }

    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

}
