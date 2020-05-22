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
    })
    .catch((error) => console.log("error", error));
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
    })
    .catch((error) => console.log("error", error));
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
    })
    .catch((error) => console.log("error", error));
}

export async function checkIdentity(identityData) {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/api/validation`,
    headers: {
      Authorization: `Bearer ${gtoken}`,
    },
    data: identityData,
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    })
    .catch((error) => console.log("error", error));
}

export async function rentCars(rentingData) {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/api/invoice/save`,
    headers: {
      Authorization: `Bearer ${gtoken}`,
    },
    data: rentingData,
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    })
    .catch((error) => console.log("error", error));
}
export async function getInvoice() {
  let requestOptions = {
    method: "POST",
    url: `${BASE_URL}/api/invoice/byUser`,
    headers: {
      Authorization: `Bearer ${gtoken}`,
    },
  };
  return await axios(requestOptions)
    .then((response) => response.data)
    .then((result) => {
      return result;
    })
    .catch((error) => console.log("error", error));
}
