import React from "react";
import Layout from "./components/Layout";
import { Button, Space, Card, Divider } from "antd";
import { Link } from "react-router-dom";
import { v4 as uuid } from "uuid";

const buttons = [
  { location: "/auth/login", title: "Giriş" },
  { location: "/auth/register", title: "Kayıt Ol" },
  { location: "/renting", title: "Araç Kirala" },
  { location: "/profile", title: "Profil" },
];
export default function App() {
  const generateButtons = () => {
    return buttons.map((e) => {
      return (
        <Link to={e.location} key={uuid()}>
          <Button type="primary" size="large" block>
            {e.title}
          </Button>
        </Link>
      );
    });
  };
  return (
    <Layout>
      <Card
        className="card card-md"
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Divider
          orientation="left"
          style={{ position: "absolute", left: 0, top: 10 }}
        >
          Sayfalar
        </Divider>
        <Space size="middle" direction="vertical" style={{ width: 300 }}>
          {generateButtons()}
        </Space>
      </Card>
    </Layout>
  );
}
