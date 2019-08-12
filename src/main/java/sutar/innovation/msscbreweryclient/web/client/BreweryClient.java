package sutar.innovation.msscbreweryclient.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sutar.innovation.msscbreweryclient.web.model.BeerDTO;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value="sfg.brewery", ignoreInvalidFields = false)
public class BreweryClient {
    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apihost;
    private final RestTemplate restTemplate;
    public BreweryClient(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDTO getBeerById(UUID uuid)
    {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString()  , BeerDTO.class);
    }

    public void setApihost(String apihost)
    {
        this.apihost=apihost;
    }

    public URI saveNewBeer(BeerDTO beerDTO)
    {
        System.out.println(apihost+BEER_PATH_V1);
        return restTemplate.postForLocation(apihost + BEER_PATH_V1,beerDTO);
    }

    public void updateBeer(UUID uuid, BeerDTO beerDTO)
    {
        restTemplate.put(apihost+BEER_PATH_V1 +"/"+ uuid.toString(),beerDTO);
    }

    public void deleteBeer(UUID uiid)
    {
        restTemplate.delete(apihost+BEER_PATH_V1+"/"+uiid);

    }
}
