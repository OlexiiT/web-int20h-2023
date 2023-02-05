import { Recipe } from "@/types/recipes";

type Props = {
  recipe: Recipe;
};

export default function RecipeListCard({ recipe }: Props) {
  return (
    <div>
      <h1>{recipe.title}</h1>
      <p>{recipe.content}</p>
    </div>
  );
}
