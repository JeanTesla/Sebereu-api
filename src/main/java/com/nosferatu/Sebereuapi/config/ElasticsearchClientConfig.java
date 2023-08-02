package com.nosferatu.Sebereuapi.config;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages= "com.nosferatu.Sebereuapi.domain.repository")
@ComponentScan(basePackages = "com.nosferatu.Sebereuapi")
public class ElasticsearchClientConfig extends
        AbstractElasticsearchConfiguration {

    @Value("${application.elasticsearch-url}")
    private String elasticSearchUrl;

    @Value("${application.elasticsearch-user}")
    private String elasticSearchUser;
    @Value("${application.elasticsearch-password}")
    private String elasticSearchPassword;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

//        final ClientConfiguration clientConfiguration =
//                ClientConfiguration
//                        .builder()
//                        .connectedTo(elasticSearchUrl)
//                        .withBasicAuth(elasticSearchUser, elasticSearchPassword)
//                        .withDefaultHeaders(
//                                //new Header[]{new BasicHeader(HttpHeaders.ACCEPT,"application/vnd.elasticsearch+json;compatible-with=7")}
//                                new HttpHeaders()
//                        )
//                        .build();

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticSearchUser, elasticSearchPassword));

        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost",9200,"http"))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.
                        setDefaultCredentialsProvider(credentialsProvider))
                .setDefaultHeaders(compatibilityHeaders());

        return new RestHighLevelClient(builder);




        //return RestClients.create(clientConfiguration).rest();
    }

    private Header[] compatibilityHeaders() {
        return new Header[]{new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"), new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")};
    }
}
