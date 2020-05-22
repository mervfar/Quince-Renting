import axios from "axios";
import { BASE_URL } from "../components/base/Constants";
let gtoken;
export async function getUser(token) {
  gtoken = token;
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/api/user/current`,
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

export async function registerDriverLicenceService(driverLicenceData) {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/api/license/save`,
    headers: {
      Authorization: `Bearer ${gtoken}`,
    },
    data: driverLicenceData,
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    });
}

export async function setUserPhoto(formData) {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/api/user/edit`,
    headers: {
      Authorization: `Bearer ${gtoken}`,
    },
    data: formData,
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    });
}
