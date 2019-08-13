package sutar.innovation.msscbreweryclient.web.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import sutar.innovation.msscbreweryclient.web.model.BeerDTO;

import java.net.URI;
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

    void testSaveNewBeer()
    {
        BeerDTO beerDTO = BeerDTO.builder().beerName("black mango").build();
        URI uri = client.saveNewBeer(beerDTO);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }
    @Test
    void testUpdateBeer()
    {
        BeerDTO beerDTO = BeerDTO.builder().beerName("Backpiper").build();
        client.updateBeer(UUID.randomUUID(),beerDTO);
    }
    @Test
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void testDeleteBeer()
    {
        client.deleteBeer(UUID.randomUUID());
    }
}