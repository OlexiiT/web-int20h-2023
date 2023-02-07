import { FIRESTORE_COL_NAMES, PAGE_LIMIT } from "@/constants";
import { Recipe, RecipeApiData, RecipePageApiData } from "@/types/recipes";
import db from "@/utils/db";
import { NextApiRequest, NextApiResponse } from "next/types";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse<RecipeApiData>
) {
    const id = req.query.id as string;
    const entry = await db.collection(FIRESTORE_COL_NAMES.recipes).doc(id).get();
    if (!entry.exists) {
        res.status(404).end();
        return;
    }
    const entryData = entry.data() as Omit<Recipe, 'id'>;
    res.status(200).json({ recipe: {...entryData, id: entry.id} });
}
