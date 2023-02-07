import { Recipe } from "@/types/recipes";
import RecipeListCard from "./RecipeListCard";

type Props = {
  recipes: Recipe[];
};

export default function RecipeList({ recipes }: Props) {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
      {recipes.map((recipe) => (
        <RecipeListCard recipe={recipe} key={recipe.id} />
      ))}
    </div>
  );
}
