import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { Avatar, Badge, Menu, Dropdown } from "antd";
import {
  UserOutlined,
  LogoutOutlined,
  SolutionOutlined,
} from "@ant-design/icons";
import styles from "./userbutton.module.scss";
import { signOut } from "../services/AuthService";
import { v4 as uuid } from "uuid";

const ColorList = ["#f56a00", "#7265e6", "#ffbf00", "#00a2ae"];
export default function UserButton(props) {
  const [color, setColor] = useState(ColorList[0]);
  const [menuFlag, setMenuFlag] = useState(false);
  const [notifications, setNotifications] = useState([{}]);
  const [count, setCount] = useState(0);
  let history = useHistory();
  useEffect(() => {
    setColor(ColorList[Math.floor(4 * Math.random())]);

    if (!props.userInf["driverLicence"]) {
      const notifications = [
        {
          text: "Ehliyet bilgileriniz eksik!",
        },
      ];
      setNotifications(notifications);
    }
  }, [props]);
  useEffect(() => {
    if (notifications[0].text) setCount(notifications.length);
  }, [notifications]);
  const handleMenuClick = (e) => {
    if (e.key !== "1") {
      setMenuFlag(false);
    }
    if (e.key === "2") {
      history.push("/profile");
    } else if (e.key === "3") {
      signOut();
      history.push("/");
      window.location.reload();
    } else console.log(e.key);
  };
  const handleVisibleChange = (flag) => {
    setMenuFlag(flag);
  };
  const getNotifications = () => {
    if (notifications[0].text)
      return notifications.map((n) => {
        return (
          <Menu.Item key={uuid()}>
            <Badge status="warning" text={`${n.text}`} />
          </Menu.Item>
        );
      });
  };
  const menu = (
    <Menu onClick={handleMenuClick} className={styles.menu}>
      <Menu.Item key="1">
        <Badge status="success" text={`Merhaba, ${props.userInf["name"]}`} />
      </Menu.Item>
      {getNotifications()}

      <Menu.Divider />
      <Menu.Item key="2">
        <SolutionOutlined /> Profil
      </Menu.Item>
      <Menu.Item key="3">
        <LogoutOutlined /> Çıkış
      </Menu.Item>
    </Menu>
  );

  return (
    <React.Fragment>
      <Dropdown
        overlay={menu}
        visible={menuFlag}
        onVisibleChange={handleVisibleChange}
      >
        <Badge count={count} onClick={(e) => e.preventDefault()}>
          <Avatar
            shape="square"
            size="large"
            className={styles.userButton}
            style={{
              backgroundColor: color,
            }}
          >
            <UserOutlined /> {props.userInf["name"]}
          </Avatar>
        </Badge>
      </Dropdown>
    </React.Fragment>
  );
}
