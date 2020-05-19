import React from "react";
import { Link } from "react-router-dom";
import { Button } from "antd";
import styles from "./layout.module.scss";
import { UserOutlined, UserAddOutlined } from "@ant-design/icons";

export default function AuthButton() {
  return (
    <React.Fragment>
      <Link to="/auth/login">
        <Button size="large" type="link" className={styles.signInButton}>
          <UserOutlined /> Giriş Yap
        </Button>
      </Link>
      <Link to="/auth/register">
        <Button size="large" className={styles.signUpButton}>
          <UserAddOutlined /> Üye Ol!
        </Button>
      </Link>
    </React.Fragment>
  );
}
