import { truncateText } from "@/helpers";
import { Recipe } from "@/types/recipes";
import Image from "next/image";
import CookIcon from "@/assets/icons/cooking.svg";
import Link from "next/link";

type Props = {
  recipe: Recipe;
};

export default function RecipeListCard({ recipe }: Props) {
  return (
    <div className="flex flex-col bg-gray-800 radius border border-gray-700 rounded-lg shadow text-white p-4 gap-4">
      <h1 className="text-2xl">{truncateText(recipe.strMeal, 50)}</h1>
      <hr className="h-px dark:bg-gray-700"></hr>
      <p>{truncateText(recipe.strInstructions, 200)}</p>
      <Link href={`/recipes/${recipe.id}`} className="mt-auto">
        <CookIcon
          alt="Cook"
          width={44}
          className="hover:bg-gray-600 bg-gray-700 p-2 rounded-lg"
          height={44}
          style={{ color: "white" }}
        />
      </Link>
    </div>
  );
}
