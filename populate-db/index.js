const { db } = require('./credentials.js');
const axios = require("axios");

const transformDoc = async (doc) => {
    const ingredients = [];
    for (let i = 1; i <= 20; i++) {
        let ingredient = doc[`strIngredient${i}`]?.toLowerCase();
        const measure = doc[`strMeasure${i}`];
        if (ingredient) {
            ingredients.push({
                ingredient,
                measure,
            });
        }
        delete doc[`strIngredient${i}`];
        delete doc[`strMeasure${i}`];
    }
    doc.ingredients = ingredients;
    return doc;
}

const main = async () => {
  const recipesColRef = db.collection("recipes");

  const allLeters = "abcdefghijklmnopqrstuvwxyz".split("");
  const batch = db.batch();

  for (const letter of allLeters) {
    const { data } = await axios(
      `http://www.themealdb.com/api/json/v1/1/search.php?f=${letter}`
    );
    if (!data.meals) continue;
    console.log(`Fetched ${letter} data: ${data.meals.length} meals`)
    const meals = data.meals;
    for (const meal of meals) {
      var docRef = recipesColRef.doc(); //automatically generate unique id
      batch.set(docRef, await transformDoc(meal));
    }
  }

  await batch.commit();
};
main();
