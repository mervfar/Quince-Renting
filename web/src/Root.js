import React from "react";
import { Provider } from "react-redux";
import Router from "./Router";
import PropTypes from "prop-types";

export default function Root({ store }) {
  return (
    <Provider store={store}>
      <Router />
    </Provider>
  );
}

Root.propTypes = {
  store: PropTypes.object.isRequired,
};
