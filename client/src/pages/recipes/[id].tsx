import { API_RESOURCE_NAMES } from "@/constants";
import { combineUrl } from "@/helpers";
import { Recipe, RecipeApiData } from "@/types/recipes";
import { GetStaticPaths, GetStaticProps } from "next";
import Image from "next/image";

export const getStaticPaths: GetStaticPaths = async (context) => {
  return {
    paths: [],
    fallback: "blocking",
  };
};

export const getStaticProps: GetStaticProps<Props, { id: string }> = async (
  context
) => {
  const id = context.params?.id;
  if (!id) {
    return {
      notFound: true,
    };
  }
  const res = await fetch(combineUrl([API_RESOURCE_NAMES.recipes, id]));
  if (res.status === 404) {
    return {
      notFound: true,
    };
  }
  const data: RecipeApiData = await res.json();
  return {
    props: {
      recipe: data.recipe,
    },
  };
};

type Props = {
  recipe: Recipe;
};

export default function RecipePage({ recipe }: Props) {
  return (
    <div className="flex flex-col gap-4">
      <h1 className="text-3xl self-center">{recipe.strMeal}</h1>
      <div className="flex justify-center bg-gray-900 h-[200px] relative border-y-2 border-gray-500">
        <Image
          src={recipe.strMealThumb}
          alt={recipe.strMeal}
          fill
          style={{ objectFit: "contain" }}
        />
      </div>
      <h2 className="text-xl self-center">Ingredients</h2>
      <ul className="list-disc">
        {recipe.ingredients.map((ingredient) => (
          <li key={ingredient.ingredient}>
            {ingredient.ingredient} - {ingredient.measure}
          </li>
        ))}
      </ul>
      <hr className="h-px" />
      <h2 className="text-xl self-center">Instructions</h2>
      <p className="border-dashed border-gray-400 border-[1px] p-4">
        {recipe.strInstructions}
      </p>
    </div>
  );
}
