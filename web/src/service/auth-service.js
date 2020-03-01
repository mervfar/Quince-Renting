import axios from 'axios'

const signInURL = 'http://mervfar.com:8080/oauth/token?grant_type=password'
const signUpURL = 'http://mervfar.com:8080/registration/'

export const signIn = (values) => {
    return axios({
      method: 'POST',
      url: signInURL + '&username=' + values.username + '&password=' + values.password,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic cXVpbmNlOjEyMzQ='
      },
    });
  };
  
export const signUp = (body) => {
    return axios({
      method: 'POST',
      url: signUpURL,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });
};
