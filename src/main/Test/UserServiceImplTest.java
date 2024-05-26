import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    public void testUpdateUser() {
        // Przygotowanie danych testowych
        User existingUser = new User("Jan", "Kowalski", LocalDate.now(), "jan.kowalski@example.com");
        User updatedUser = new User("Janekk", "Kowalskiie", LocalDate.of(1990, 1, 2), "janek.kowalski@example.com");

        // Symulacja zachowania repozytorium
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);




        User result = userService.updateUser(1L, updatedUser);
        assertThat(result.getFirstName()).isEqualTo("Janekk");
        assertThat(result.getLastName()).isEqualTo("Kowalskiie");


    }
}