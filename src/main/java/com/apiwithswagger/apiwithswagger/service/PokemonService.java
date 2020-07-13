package com.apiwithswagger.apiwithswagger.service;

import com.apiwithswagger.apiwithswagger.client.PokemonClient;
import com.apiwithswagger.apiwithswagger.model.Pokemon;
import com.apiwithswagger.apiwithswagger.model.Pokemons;
import com.apiwithswagger.apiwithswagger.util.PokemonUtil;
import org.springframework.stereotype.Component;

@Component
public class PokemonService {

    private final PokemonUtil pokemonUtil;

    private final PokemonClient pokemonClient;

    public PokemonService(PokemonUtil pokemonUtil,
                          PokemonClient pokemonClient) {
        this.pokemonUtil = pokemonUtil;
        this.pokemonClient = pokemonClient;
    }

    public Pokemon findPokemon(String pokemon) {
        return pokemonClient.findPokemon(pokemon);
    }

    public Pokemons findAllOriginals() {
        return pokemonUtil.extractPokemonNumberFromUrl(pokemonClient.findAllOriginals());
    }
}
