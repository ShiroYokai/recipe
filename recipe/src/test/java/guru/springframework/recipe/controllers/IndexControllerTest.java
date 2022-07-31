package guru.springframework.recipe.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {
	
	private IndexController indexController;
	
	@Mock
	private Model model;
	
	@Mock
	private RecipeService recipeService;

	@BeforeEach
	void setUp() throws Exception {
		indexController = new IndexController(recipeService);
	}
	
	@Test
	void testMochMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("index"));
		
	}

	@Test
	void testGetIndexPage() {
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		recipes.add(recipe);

		Mockito.when(recipeService.getRecipes()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		assertThat(indexController.getIndexPage(model)).isEqualTo("index");
		Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
		Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
		
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertThat(setInController.size()).isEqualTo(2);
	}

}
