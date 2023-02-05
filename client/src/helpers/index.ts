import { API_BASE_URL } from "@/constants";
import urlJoin from "url-join";

export const combineUrl = (
  urlSegments: string[] | string,
  baseUrl = API_BASE_URL
) => {
  return urlJoin(
    baseUrl,
    ...(Array.isArray(urlSegments) ? urlSegments : [urlSegments])
  );
};
