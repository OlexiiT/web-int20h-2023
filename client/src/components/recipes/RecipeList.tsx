import { Recipe } from "@/types/recipes";
import RecipeListCard from "./RecipeListCard";

type Props = {
  recipes: Recipe[];
};

export default function RecipeList({ recipes }: Props) {
  return (
    <div>
      {recipes.map((recipe) => (
        <RecipeListCard recipe={recipe} key={recipe.id} />
      ))}
    </div>
  );
}
