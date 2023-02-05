import { API_RESOURCE_NAMES } from "@/constants";
import { combineUrl } from "@/helpers";
import { Recipe } from "@/types/recipes";
import { GetStaticPaths, GetStaticProps } from "next";

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
  const res = await fetch(combineUrl(API_RESOURCE_NAMES.recipes, id));
  const data: Recipe = await res.json();
  return {
    props: {
      recipe: data,
    },
  };
};

type Props = {
  recipe: Recipe;
};

export default function RecipePage({ recipe }: Props) {
  return (
    <div>
      <h1>{recipe.title}</h1>
      <p>{recipe.content}</p>
    </div>
  );
}
