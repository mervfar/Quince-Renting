import React from "react";
import logo from "../assets/logo.svg";
import { PageHeader, Button, Layout as LayoutAnt } from "antd";
import styles from "./layout.module.scss";
import { Link } from "react-router-dom";
import { UserOutlined, UserAddOutlined } from "@ant-design/icons";

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
              <Link to="/auth/login">
                <Button
                  size="large"
                  type="link"
                  className={styles.signInButton}
                >
                  <UserOutlined /> Giriş Yap
                </Button>
              </Link>
              <Link to="/auth/register">
                <Button size="large" className={styles.signUpButton}>
                  <UserAddOutlined /> Üye Ol!
                </Button>
              </Link>
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
