const { initializeApp, cert } = require("firebase-admin/app");
const { getFirestore } = require("firebase-admin/firestore");

const serviceAccount = require("./credentials.json");
initializeApp({
  credential: cert(serviceAccount),
  databaseURL:
    "https://hakaton2023-76ac3-default-rtdb.europe-west1.firebasedatabase.app",
});

module.exports = { db: getFirestore() };
