const { db } = require("./credentials.js");

const recipesColRef = db.collection("recipes");
const ingredientsColRef = db.collection("ingredients");

const main = async () => {
  const recipes = await recipesColRef.get();
  const ingredients = recipes.docs.reduce((acc, doc) => {
    const ingredients = doc
      .data()
      .ingredients.map((ingredient) => ingredient.ingredient);
    return [...acc, ...ingredients];
  }, []);
  const uniqueIngredients = new Set(ingredients);
  // commit new ingredients to db in batches of 500
  let batch = db.batch();
  let counter = 0;
  for (const ingredient of uniqueIngredients) {
    var docRef = ingredientsColRef.doc(); //automatically generate unique id
    batch.set(docRef, { name: ingredient });
    counter++;
    if (counter % 500 === 0) {
      await batch.commit();
      batch = db.batch();
    }
  }
  await batch.commit();
};
main();
