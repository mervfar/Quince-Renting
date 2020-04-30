import axios from "axios";
import { BASE_URL } from "../components/base/Constants";

export async function getUser(token) {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/api/user`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    });
}
