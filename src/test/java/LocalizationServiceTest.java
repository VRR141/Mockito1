import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceTest {

    private LocalizationService sut;

    @BeforeEach
    public void testStart(){
        System.out.println("run");
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void testFinished(){
        sut = null;
        System.out.println("finish");
    }

    @BeforeAll
    public static void start(){
        System.out.println("Tests start");
    }

    @AfterAll
    public static void end(){
        System.out.println("End");
    }

    @Test
    public void testRussianLocale(){
        Country country = Country.RUSSIA;
        String actual = sut.locale(country);
        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource()
    public void testAnyLocale(Country country){
        String actual = sut.locale(country);
        String expected = "Welcome";

        Assertions.assertEquals(expected, actual);
    }

    static Stream<Country> testAnyLocale(){
        return Stream.of(Country.BRAZIL, Country.USA, Country.GERMANY);
    }

}
