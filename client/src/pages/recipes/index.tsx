import RecipeList from "@/components/recipes/RecipeList";
import { API_RESOURCE_NAMES } from "@/constants";
import { combineUrl } from "@/helpers";
import { Recipe, RecipePageApiData } from "@/types/recipes";
import { GetServerSideProps } from "next";

// This gets called on every request
export const getServerSideProps: GetServerSideProps<
  RecipesProps
> = async () => {
  // Fetch data from external API
  const res = await fetch(combineUrl(API_RESOURCE_NAMES.recipes));
  const data: RecipePageApiData = await res.json();

  // Pass data to the page via props
  return { props: { recipes: data.recipes } };
};

type RecipesProps = {
  recipes: Recipe[];
};
export default function Recipes({ recipes }: RecipesProps) {
  return <RecipeList recipes={recipes} />;
}
