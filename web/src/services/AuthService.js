import axios from "axios";

export function SignIn(loginData) {
  let requestOptions = {
    method: "GET",
    url: `http://mervfar.com:8080/oauth/token?grant_type=password&username=${loginData.username}&password=${loginData.password}`,
    headers: {
      Authorization: "Basic cXVpbmNlOjEyMzQ=",
    },
  };

  axios(requestOptions)
    .then((response) => response.data)
    .then((result) => console.log(result))
    .catch((error) => console.log("error", error));
}
