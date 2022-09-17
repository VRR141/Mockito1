import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.Assert.*;

public class GeoServiceTest {

    private GeoService sut;

    @BeforeEach
    public void testStart(){
        System.out.println("run");
        sut = new GeoServiceImpl();
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
    public void byMoscowIP(){
        String ip = "172.0.32.11";

        Location actualLocation = sut.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        String actualStreet = actualLocation.getStreet();
        int actualBuiling = actualLocation.getBuiling();

        Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        String expectedCity = expectedLocation.getCity();
        Country expectedCountry = expectedLocation.getCountry();
        String expectedStreet = expectedLocation.getStreet();
        int expectedBuiling = expectedLocation.getBuiling();

        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertEquals(expectedStreet, actualStreet);
        assertEquals(expectedBuiling, actualBuiling);
    }

    @Test
    public void byNYip(){
        String ip = "96.44.183.149";

        Location actualLocation = sut.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        String actualStreet = actualLocation.getStreet();
        int actualBuiling = actualLocation.getBuiling();

        Location expectedLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        String expectedCity = expectedLocation.getCity();
        Country expectedCountry = expectedLocation.getCountry();
        String expectedStreet = expectedLocation.getStreet();
        int expectedBuiling = expectedLocation.getBuiling();

        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertEquals(expectedStreet, actualStreet);
        assertEquals(expectedBuiling, actualBuiling);

    }

    @Test
    public void byLocalHostIP(){
        String ip = "127.0.0.1";

        Location actualLocation = sut.byIp(ip);
        Location expectedLocation = new Location(null, null, null, 0);

        int actualBuiling = actualLocation.getBuiling();
        int expectedBuiling = expectedLocation.getBuiling();


        assertNull(actualLocation.getCountry());
        assertNull(actualLocation.getCity());
        assertNull(actualLocation.getStreet());
        assertEquals(expectedBuiling, actualBuiling);
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.23.245.223", "172.0.124.755", "172.88.432.443"})
    public void startsWithMoscow(String ip){
        Location actualLocation = sut.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        int actualBuiling = actualLocation.getBuiling();

        Location expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        String expectedCity = expectedLocation.getCity();
        Country expectedCountry = expectedLocation.getCountry();
        int expectedBuiling = expectedLocation.getBuiling();

        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertEquals(expectedBuiling, actualBuiling);
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.23.245.223", "96.0.124.755", "96.88.432.443"})
    public void startsWithNY(String ip){
        Location actualLocation = sut.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        int actualBuiling = actualLocation.getBuiling();

        Location expectedLocation = new Location("New York", Country.USA, null,  0);
        String expectedCity = expectedLocation.getCity();
        Country expectedCountry = expectedLocation.getCountry();
        int expectedBuiling = expectedLocation.getBuiling();

        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertEquals(expectedBuiling, actualBuiling);
    }


}
