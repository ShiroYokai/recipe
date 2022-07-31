package guru.springframework.recipe.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

	RecipeService recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	void testGetRecipes() {
		Recipe recipe = new Recipe();
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(recipe);
		Mockito.when(recipeRepository.findAll()).thenReturn(recipesData);
		Set<Recipe> recipes = recipeService.getRecipes();
		assertThat(recipes.size()).isEqualTo(1);
		Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
	}

}
