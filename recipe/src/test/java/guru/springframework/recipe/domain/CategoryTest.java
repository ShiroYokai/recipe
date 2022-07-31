package guru.springframework.recipe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

	Category category;
	
	@BeforeEach
	void setUp() {
		category = new Category();
	}
	
	@Test
	void getId() throws Exception {
		Long idValue = 4L;
		category.setId(idValue);
		assertThat(category.getId()).isEqualTo(idValue);
	}
	
	@Test
	void getDescription() throws Exception {
		
	}
	
	@Test
	void getRecipes() throws Exception {
		
	}
}
