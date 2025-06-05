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


        userRepository=mock(UserRepository.class);
        passwordEncoder=mock(IPasswordEncoder.class);
        userService=new UserService(userRepository,passwordEncoder);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(passwordEncoder.encode("12314")).thenReturn("aajbfafb");
        when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");

        when(userRepository.findById("U01")).thenReturn(user);
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
}