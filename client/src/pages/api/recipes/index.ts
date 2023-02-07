import { FIRESTORE_COL_NAMES, PAGE_LIMIT } from "@/constants";
import { Recipe, RecipePageApiData } from "@/types/recipes";
import db from "@/utils/db";
import { NextApiRequest, NextApiResponse } from "next/types";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse<RecipePageApiData>
) {
  const entries = await db.collection(FIRESTORE_COL_NAMES.recipes).limit(PAGE_LIMIT).get();
  const entriesData = entries.docs.map((entry): Recipe => ({...(entry.data() as Omit<Recipe, 'id'>), id: entry.id}));
  res.status(200).json({ recipes: entriesData });
}
