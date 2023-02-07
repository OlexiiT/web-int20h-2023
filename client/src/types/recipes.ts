export type Recipe = {
  id: string;
  ingredients: Ingredient[];
  strInstructions: string;
  strMeal: string;
  strMealThumb: string;
};

export type Ingredient = {
  ingredient: string;
  measure: string;
}

export type RecipePageApiData = {
  recipes: Recipe[];
};

export type RecipeApiData = {
  recipe: Recipe;
}
