package guru.springframework.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Difficulty;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
	}

	private List<Recipe> getRecipes() {
		
		log.debug("Creating recipes.");
		
		List<Recipe> recipes = new ArrayList<>();
		
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		if(!teaspoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		
		if(!tablespoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		if(!cupUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		
		if(!dashUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
		
		if(!pintUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure teaSpoonUom = teaspoonUomOptional.get();
		UnitOfMeasure tableSpoonUom = tablespoonUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category not found");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category not found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirection("Some directions");
		
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("Some notes");
		guacRecipe.setNotes(guacNotes);
		
		guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(2), teaSpoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("serano chilies, stems and seeds removed, minced", new BigDecimal(2), eachUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("Cilandro", new BigDecimal(2), tableSpoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(.5), eachUom, guacRecipe));
		
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(guacRecipe);
		
		return recipes;

	}

}
