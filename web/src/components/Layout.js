import React from "react";
import logo from "../assets/logo.svg";
import { PageHeader, Layout as LayoutAnt } from "antd";
import styles from "./layout.module.scss";
import { Link } from "react-router-dom";
import AuthButton from "./AuthButton";
import UserButton from "./UserButton";

const { Content } = LayoutAnt;
export default function Layout(props) {
  return (
    <React.Fragment>
      <LayoutAnt className={styles.body}>
        <PageHeader
          title={
            <Link to="/">
              <img src={logo} className={styles.logo} alt="site-logo" />
            </Link>
          }
          extra={
            <div className={styles.authButtonContainer}>
              {props.userCredentials["user_inf"]["username"] ? (
                <UserButton userInf={props.userCredentials["user_inf"]} />
              ) : (
                <AuthButton />
              )}
              {/* {console.log(props)} */}
            </div>
          }
        />

        <Content className={styles.content}>{props.children}</Content>
      </LayoutAnt>
    </React.Fragment>
  );
}

// <Avatar
//               shape="square"
//               size="large"
//               style={{
//                 color: "#f56a00",
//                 backgroundColor: "#fde3cf",
//                 marginRight: "2em",
//               }}
//             >
//               A
//             </Avatar>
