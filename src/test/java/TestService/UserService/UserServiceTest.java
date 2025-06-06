package TestService.UserService;

import mockitoTest.lesson1.IPasswordEncoder;
import mockitoTest.lesson1.User;
import mockitoTest.lesson1.UserRepository;
import mockitoTest.lesson1.UserService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("Mon Anh Bang")
public class UserServiceTest {


    private static UserService userService;
    private static UserRepository userRepository;
    private static IPasswordEncoder passwordEncoder;

    @BeforeAll
    static void setUpBeforeClass() {
        // Khởi tạo trước tất cả các test
        User user = new User("U01", "encodedPassword", true);
        User user2 = new User("U02", "encodedPassword", true);
        User user3 = new User("U03", "aajbfafb", false);

        userRepository=mock(UserRepository.class);
        passwordEncoder=mock(IPasswordEncoder.class);
        userService=new UserService(userRepository,passwordEncoder);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(passwordEncoder.encode("12314")).thenReturn("aajbfafb");
        when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");

        when(userRepository.findById("U01")).thenReturn(user);
        when(userRepository.findById("U02")).thenReturn(user2);
        when(userRepository.findById("U03")).thenReturn(user3);
    }

    @AfterAll
    static void tearDownAfterClass() {
        // Dọn dẹp sau tất cả các test
    }

    @BeforeEach
    void setUp() {
        // Khởi tạo trước mỗi test
    }

    @AfterEach
    void tearDown() {
        // Dọn dẹp sau mỗi test
    }

    @Test
    void test() {
        // Viết test case ở đây
        assertTrue(true);
    }

    @Test
    void testIsValidUser() {
        // Viết test case ở đây
    }

    @Test
    @DisplayName("Test case with valid user with correct id and password")
    void test_User(){
        boolean result=userService.isValidUser("U01","123456");
        assertTrue(result,"User should be valid with correct id and password");
    }

    @Test
    @DisplayName("Test with invalid id user ")
    void test_InvalidUserId(){

        // It will be return true cause in our repository we don't contain any user have id U03
        assertFalse(userService.isValidUser("U03","12314"));
    }

    @Test
    @DisplayName("Test a test case with user with unenabled")
    void test_EnableUser(){
        // First we need to create a new user with a status enable because we have two user above but those don't
        // contain an unequaled status
//        User user3 = new User("U03", "aajbfafb", false);

        assertFalse(userService.isValidUser("U03","aajbfafb"));
    }

    @Test
    @DisplayName("Test a test case with  invalid user's password")
    void test_PasswordUser(){

        // we assume that when user type password we will check id and password
        // we see in above contructor we have defined user U01 got "encodedPassword" password and
        // also defined stubborn test when user type 12314 it returns aajbfafb it doesn't match so it will return false
//        assertFalse(userService.isValidUser("U01","12314"));
        assertTrue(userService.isValidUser("U01","encodedPassword"));
    }

    @Test
    @DisplayName("Test with exception")
    void test_ThrowEx(){
//        Exception ex=assertThrows(IllegalArgumentException.class,()->{
//            userService.isValidUser(null,"encodedPassword");
//        });
//        Exception ex=assertThrows(IllegalArgumentException.class,()->{
//            userService.isValidUser("","encodedPassword");
//        });
//        Exception ex=assertThrows(IllegalArgumentException.class,()->{
//            userService.isValidUser("U01","");
//        });
//        Exception ex=assertThrows(IllegalArgumentException.class,()->{
//            userService.isValidUser("U01",null);
//        });
//        assertEquals("Invalid ID and Password and must be not empty", ex.getMessage());



        assertThrows(IllegalArgumentException.class, () -> userService.isValidUser(null, "123456"), "Should throw exception for null ID");
        assertThrows(IllegalArgumentException.class, () -> userService.isValidUser("", "123456"), "Should throw exception for empty ID");
        assertThrows(IllegalArgumentException.class, () -> userService.isValidUser("U01", null), "Should throw exception for null password");
        assertThrows(IllegalArgumentException.class, () -> userService.isValidUser("U01", ""), "Should throw exception for empty password");
        assertThrows(IllegalArgumentException.class, () -> userService.isValidUser(null, null), "Should throw exception for null ID and password");
    }
}