package com.apiwithswagger.apiwithswagger.client;

import com.apiwithswagger.apiwithswagger.model.Pokemon;
import com.apiwithswagger.apiwithswagger.model.Pokemons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Component
@Slf4j
public class PokemonClient {

    private RestTemplate restTemplate;

    private static String pokemonDomain = "";

    private static String pokemonURI = "";

    private static String limitFirstOriginals = "";

    public PokemonClient(RestTemplate restTemplate,
                         @Value("${pokemon.domain}") String pokemonDomain,
                         @Value("${pokemon.URI}") String pokemonURI,
                         @Value("${pokemon.allOriginalsParam}") String limitFirstOriginals) {
        this.restTemplate = restTemplate;
        this.pokemonDomain = pokemonDomain;
        this.pokemonURI = pokemonURI;
        this.limitFirstOriginals = limitFirstOriginals;
    }

    public Pokemon findPokemon(String pokemon) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl(pokemonDomain + pokemonURI + "/" + pokemon);

        HttpEntity<String> entity = createHttpHeaders();

        log.info("findPokemon request: " + uriBuilder.toUriString());

        ResponseEntity<Pokemon> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Pokemon.class);
        return response.getBody();
    }

    public Pokemons findAllOriginals () {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(pokemonDomain + pokemonURI)
                        .queryParam("limit", 151);

        HttpEntity<String> entity = createHttpHeaders();

        log.info("retrieveOriginals request: " + uriBuilder.toUriString());

        ResponseEntity<Pokemons> response =
                restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Pokemons.class);

        return response.getBody();
    }

    private HttpEntity<String> createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }
}
