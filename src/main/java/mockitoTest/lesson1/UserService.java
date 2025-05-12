package mockitoTest.lesson1;

public class UserService {
    private final UserRepository userRepository;
    private final IPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, IPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //Tuong tu nhu login
    public boolean isValidUser(String id, String password) {
        User user = userRepository.findById(id);
        return isEnabledUser(user) &&
                isValidPassword(user, password);
    }

    private boolean isEnabledUser(User user) {
        return user != null && user.isEnabled();
    }

    private boolean isValidPassword(User user, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        if(encodedPassword==null)
            return false;
        return encodedPassword.equals(user.getPasswordHash());
    }
}
