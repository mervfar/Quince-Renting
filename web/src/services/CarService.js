import axios from "axios";
import { BASE_URL } from "../components/base/Constants";

export async function getLocations(location) {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/avis/locations/${location}`,
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    });
}

export async function getCars(carData) {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/avis/availableCars`,
    data: carData,
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    });
}
