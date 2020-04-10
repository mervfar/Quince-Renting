import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import App from "./App";
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import Renting from "./pages/renting/Renting";
import Profile from "./pages/profile/Profile";

export default function Router() {
  return (
    <BrowserRouter>
      {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
      <Switch>
        <Route exact path="/" component={Renting} />
        <Route path="/auth/login" component={Login} />
        <Route path="/auth/register" component={Register} />
        <Route path="/renting" component={Renting} />
        <Route path="/profile" component={Profile} />
      </Switch>
    </BrowserRouter>
  );
}
