package com.apiwithswagger.apiwithswagger.util;

import com.apiwithswagger.apiwithswagger.model.Pokemon;
import com.apiwithswagger.apiwithswagger.model.Pokemons;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PokemonUtil {

    private static String pokemonDomain = "";

    private static String pokemonURI = "";

    public PokemonUtil(@Value("${pokemon.domain}") String pokemonDomain,
                        @Value("${pokemon.URI}") String pokemonURI) {
        this.pokemonDomain = pokemonDomain;
        this.pokemonURI = pokemonURI;

    }

    public Pokemons extractPokemonNumberFromUrl(Pokemons pokemons) {

        Pokemons pokemonsCopy = new Pokemons();
        BeanUtils.copyProperties(pokemons, pokemonsCopy);

        pokemonsCopy.getResults()
                .forEach(pokemon -> pokemon.setId(extractNumber(pokemon.getId())));

        return pokemonsCopy;
    }

    private String extractNumber(String id) {

        String newId = id
                .replace(pokemonDomain, "")
                .replace(pokemonURI, "");

        return newId.substring(1, newId.length() -1);
    }



}
