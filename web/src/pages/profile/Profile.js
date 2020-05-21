import React from "react";
import { Card } from "antd";
import Layout from "../../components/Layout";

export default function index(props) {
  return (
    <Layout userCredentials={props.userCredentials}>
      <Card className="card card-bg">
        <h2>Hazırlanıyor...</h2>
      </Card>
    </Layout>
  );
}
