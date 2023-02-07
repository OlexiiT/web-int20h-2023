export const API_BASE_URL =
  process.env.API_BASE_URL ?? "http://localhost:3000/api";
export const API_RESOURCE_NAMES = {
  products: "products",
  recipes: "recipes",
};

export const FIRESTORE_COL_NAMES = {
  ingredients: "ingredients",
  recipes: "recipes",
};

export const PAGE_LIMIT = 50;