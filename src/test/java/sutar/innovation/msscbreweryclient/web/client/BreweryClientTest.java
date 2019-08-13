package sutar.innovation.msscbreweryclient.web.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sutar.innovation.msscbreweryclient.web.model.BeerDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;
    @Test
    void getBeerById() {
        BeerDTO dto = client.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
        System.out.println(dto);
        System.out.println("this is working perfect");
    }

    @Test
    void testUpdateBeer()
    {
        BeerDTO beerDTO = BeerDTO.builder().beerName("Bike Piper").build();

    }
}