package com.apiwithswagger.apiwithswagger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemons {

    private List<Pokemon> results = new ArrayList();

    private Integer count;
}
