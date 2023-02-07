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

export const truncateText = (text: string, length: number) => {
  return text.length > length ? `${text.slice(0, length)}...` : text;
};
