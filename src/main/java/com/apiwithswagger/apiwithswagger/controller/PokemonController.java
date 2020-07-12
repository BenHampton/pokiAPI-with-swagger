package com.apiwithswagger.apiwithswagger.controller;

import com.apiwithswagger.apiwithswagger.model.Pokemon;
import com.apiwithswagger.apiwithswagger.model.Pokemons;
import com.apiwithswagger.apiwithswagger.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Operation(summary = "Finds Pokemon by name")
    @GetMapping("/{pokemon}")
    public ResponseEntity<Pokemon> findPokemon(@PathVariable("pokemon") String pokemon) {
        return new ResponseEntity<>(pokemonService.findPokemon(pokemon), HttpStatus.OK);
    }

    @Operation(summary = "Finds list of Pokemon")
    @GetMapping
    public  ResponseEntity<Pokemons> findAllOriginals() {
        return new ResponseEntity<>(pokemonService.findAllOriginals(), HttpStatus.OK);
    }
}
