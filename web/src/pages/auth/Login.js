import React, { useState } from "react";
import Layout from "../../components/Layout";
import { Form, Input, Button, Checkbox, Divider, Card, Spin } from "antd";
import { Link, useHistory } from "react-router-dom";
import { signIn } from "../../services/AuthService";

const layout = {
  labelCol: {
    span: 8,
  },
  wrapperCol: {
    span: 16,
  },
};
const tailLayout = {
  wrapperCol: {
    offset: 8,
    span: 16,
  },
};
export default function Login(props) {
  let history = useHistory();
  const [loginState, setLoginState] = useState(false);
  const onFinish = (values) => {
    console.log("Success:", values);
    signIn(values)
      .then((res) => {
        props.setLoginCredentials(res);
        setLoginState(true);
      })
      .then((res) => {
        setTimeout(() => {
          history.push("/");
        }, 1500);
      })
      .catch((res) => {
        history.push("/profile");
      });
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };
  return (
    <Layout userCredentials={props.userCredentials}>
      {console.log(props.userCredentials)}
      <Card
        className="card card-sm"
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
          Giriş Yap
        </Divider>
        {!loginState ? (
          <Form
            {...layout}
            name="basic"
            initialValues={{
              remember: true,
            }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
          >
            <Form.Item
              label="Kullanıcı Adı"
              name="username"
              rules={[
                {
                  required: true,
                  message: "Lütfen kullanıcı adınızı giriniz!",
                },
              ]}
            >
              <Input />
            </Form.Item>

            <Form.Item
              label="Parola"
              name="password"
              rules={[
                {
                  required: true,
                  message: "Lütfen parolanızı giriniz!",
                },
              ]}
            >
              <Input.Password />
            </Form.Item>

            <Form.Item {...tailLayout} name="remember" valuePropName="checked">
              <Checkbox>Beni Hatırla</Checkbox>
            </Form.Item>

            <Form.Item {...tailLayout}>
              <Button type="primary" htmlType="submit">
                Giriş yap
              </Button>
            </Form.Item>
            <Divider />
            <p>
              Hala hesabınız yok mu?{" "}
              <Link to="/auth/register">
                <Button type="link">Kayıt Ol!</Button>
              </Link>
            </p>
          </Form>
        ) : (
          <Spin size="large" tip="Yönlendiriyorum..." />
        )}
      </Card>
    </Layout>
  );
}
