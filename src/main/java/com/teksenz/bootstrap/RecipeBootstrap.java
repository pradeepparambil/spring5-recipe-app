package com.teksenz.bootstrap;

import com.teksenz.domain.*;
import com.teksenz.repositories.CategoryRepository;
import com.teksenz.repositories.RecipeRepository;
import com.teksenz.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeBootstrap implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        getRecipes().forEach(recipeRepository::save);



    }
    public Set<Recipe> getRecipes(){
        Set<Recipe> recipes = new HashSet<>();
        Category category1 = categoryRepository.findByDescription("American").orElseThrow(RuntimeException::new);
        Category category2 = categoryRepository.findByDescription("Italian").orElseThrow(RuntimeException::new);
        Set<Category> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(RuntimeException::new);
        Ingredient ingredient1 = Ingredient.builder()
                .description("Chilly")
                .amount(new BigDecimal(2))
                .uom(unitOfMeasure)
                .build();
        Ingredient ingredient2 = Ingredient.builder()
                .description("Salt")
                .amount(new BigDecimal(2))
                .uom(unitOfMeasure)
                .build();
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        Notes notes = Notes.builder()
                .RecipeNotes("Yummy")
                .build();
        Recipe tacoRecipe = Recipe.builder()
                .url("www.teksenz.com")
                .cookTime(20)
                .prepTime(10)
                .servings(2)
                .desciption("Delicious Spicy Taco")
                .difficulty(Difficulty.EASY)
                .directions("Mix all ingradients well and cook in low flame :)")
                .notes(notes)
                .categories(categories)
                .ingredients(ingredients)
                .source("My house")
                .build();
        recipes.add(tacoRecipe);
        ingredient1.setRecipe(tacoRecipe);
        ingredient2.setRecipe(tacoRecipe);

        return recipes;
    }
}
