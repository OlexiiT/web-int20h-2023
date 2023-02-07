import { initializeApp, cert } from "firebase-admin/app";
import admin from "firebase-admin";
import { getFirestore } from "firebase-admin/firestore";
import { dirname, join } from "path";
import { fileURLToPath } from "url";

const __dirname = dirname(fileURLToPath(import.meta.url));
if (!admin.apps.length) {
  initializeApp({
    credential: cert(join(__dirname, "serviceAccountKey.json")),
    databaseURL:
      "https://hakaton2023-76ac3-default-rtdb.europe-west1.firebasedatabase.app",
  });
}
export default getFirestore();
