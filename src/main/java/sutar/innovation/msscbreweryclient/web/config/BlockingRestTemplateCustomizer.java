package sutar.innovation.msscbreweryclient.web.config;

import net.bytebuddy.description.field.FieldDescription;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private final Integer maxTotalConnection;
    private final Integer defaultMxTotalConnections;
    private final Integer connectionRequestTimeOut;
    private final Integer sockeTimeOut;

    public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotalconnections}") Integer maxTotalConnection,
                                          @Value("${sfg.defaultmaxtotalconnections}") Integer defaultMxTotalConnections,
                                          @Value("${sfg.connectionrequesttimeout}") Integer connectionRequestTimeOut,
                                          @Value("${sfg.sockettimeout}") Integer sockeTimeOut) {
        this.maxTotalConnection = maxTotalConnection;
        this.defaultMxTotalConnections = defaultMxTotalConnections;
        this.connectionRequestTimeOut = connectionRequestTimeOut;
        this.sockeTimeOut = sockeTimeOut;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnection);
        connectionManager.setDefaultMaxPerRoute(defaultMxTotalConnections);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeOut)
                .setSocketTimeout(sockeTimeOut)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
