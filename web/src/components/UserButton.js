import React from "react";
import { Avatar } from "antd";

export default function UserButton(props) {
  return (
    <React.Fragment>
      <Avatar
        shape="square"
        size="large"
        style={{
          color: "#f56a00",
          backgroundColor: "#fde3cf",
          marginRight: "2em",
        }}
      >
        {props.name}
      </Avatar>
    </React.Fragment>
  );
}
