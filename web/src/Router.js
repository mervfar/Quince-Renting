import React, { useState, useEffect } from "react";
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import Renting from "./pages/renting/Renting";
import Profile from "./pages/profile/Profile";
import { getUser } from "./services/UserService";
import { userCredentialsModel } from "./models/Models";

export default function Router() {
  const [loginCredentials, setLoginCredentials] = useState({});
  const [userCredentials, setUserCredentials] = useState(userCredentialsModel);
  const [localUserCredits, setLocalUserCredits] = useState(
    userCredentialsModel
  );
  async function checkTokenValidity() {
    if (localUserCredits && localUserCredits !== userCredentialsModel) {
      await getUser(localUserCredits["access_inf"]["access_token"])
        .then((res) => {
          console.log("başarılı");
          setUserCredentials(localUserCredits);
        })
        .catch((error) => {
          setUserCredentials(userCredentialsModel);
          console.log(localUserCredits);
          console.log(error.response);
        });
    }
  }
  function prepareUserDetails() {
    setLocalUserCredits(JSON.parse(localStorage.getItem("userCredentials")));
    if (loginCredentials["access_token"]) {
      getUser(loginCredentials["access_token"]).then((res) => {
        console.log(res);
        let userCredits = {
          access_inf: loginCredentials,
          user_inf: res,
        };
        setUserCredentials(userCredits);
        localStorage.setItem("userCredentials", JSON.stringify(userCredits));
      });
    }
  } // Configuring User Information
  useEffect(() => {
    prepareUserDetails();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [loginCredentials]);
  useEffect(() => {
    checkTokenValidity();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [localUserCredits]);
  return (
    <BrowserRouter>
      {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
      <Switch>
        <Route exact path="/">
          <Renting userCredentials={userCredentials} />
        </Route>
        <Route path="/auth/login">
          {userCredentials !== userCredentialsModel ? (
            <Redirect to="/" />
          ) : (
            <Login
              userCredentials={userCredentials}
              setLoginCredentials={setLoginCredentials}
            />
          )}
        </Route>
        <Route path="/auth/register">
          {userCredentials !== userCredentialsModel ? (
            <Redirect to="/" />
          ) : (
            <Register
              userCredentials={userCredentials}
              setLoginCredentials={setLoginCredentials}
            />
          )}
        </Route>
        <Route path="/profile">
          <Profile userCredentials={userCredentials} />
        </Route>
      </Switch>
    </BrowserRouter>
  );
}
