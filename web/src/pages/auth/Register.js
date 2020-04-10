import React, { useState } from "react";
import Layout from "../../components/Layout";
import {
  Card,
  Input,
  Button,
  Checkbox,
  Steps,
  Form,
  Select,
  Tooltip,
  Divider,
  Result,
} from "antd";
import { QuestionCircleOutlined } from "@ant-design/icons";
import { Link } from "react-router-dom";
import styles from "./register.module.scss";
import { v4 as uuid } from "uuid";
const { Step } = Steps;
const { Option } = Select;
const steps = [
  {
    title: "Kişisel bilgiler",
    content: "First-content",
  },
  {
    title: "Ehliyet bilgileri",
    content: "Second-content",
  },
  {
    title: "Her şey hazır!",
    content: "Last-content",
  },
];
const formItemLayout = {
  labelCol: {
    xs: { span: 16 },
    sm: { span: 8 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 8 },
  },
};
const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 16,
      offset: 8,
    },
  },
};
export default function Register() {
  const [current, setcurrent] = useState(0);
  const prefixSelector = (
    <Form.Item name="prefix" noStyle>
      <Select style={{ width: 70 }}>
        <Option value="90">+90</Option>
      </Select>
    </Form.Item>
  );
  const onFinish = (values) => {
    console.log("Received values of form: ", values);
  };
  const next = () => {
    setcurrent(current + 1);
  };

  const prev = () => {
    setcurrent(current - 1);
  };
  return (
    <Layout>
      <Card
        className="card card-bg"
        title="Kayıt Ol"
        extra={
          current < steps.length - 1 && current <= 0 ? (
            <Button type="primary" onClick={() => next()}>
              İleri
            </Button>
          ) : current < steps.length - 1 && current > 0 ? (
            <>
              <Button style={{ marginRight: 8 }} onClick={() => prev()}>
                Geri
              </Button>
              <Button type="primary" onClick={() => next()}>
                İleri
              </Button>
            </>
          ) : current === steps.length - 1 ? (
            <Button
              type="primary"
              onClick={() => console.log("Processing complete!")}
            >
              Bitti
            </Button>
          ) : null
        }
      >
        <Steps current={current}>
          {steps.map((item) => (
            <Step key={uuid()} title={item.title} />
          ))}
        </Steps>
        <div className={styles.content}>
          {current === 0 ? (
            <Form
              {...formItemLayout}
              name="register"
              onFinish={onFinish}
              initialValues={{
                prefix: "90",
              }}
              scrollToFirstError
              style={{ overflow: "scroll" }}
            >
              <Form.Item
                name="email"
                label="E-Posta"
                rules={[
                  {
                    type: "email",
                    message: "Lütfen geçerli bir e-posta adresi giriniz!",
                  },
                  {
                    required: true,
                    message: "Lütfen e-posta adresinizi giriniz!",
                  },
                ]}
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="password"
                label="Parola"
                rules={[
                  {
                    required: true,
                    message: "Lütfen parola giriniz!",
                  },
                ]}
                hasFeedback
              >
                <Input.Password />
              </Form.Item>
              <Form.Item
                name="confirm"
                label="Parola Doğrula"
                dependencies={["password"]}
                hasFeedback
                rules={[
                  {
                    required: true,
                    message: "Lütfen parolanızı yeniden yazın!",
                  },
                  ({ getFieldValue }) => ({
                    validator(rule, value) {
                      if (!value || getFieldValue("password") === value) {
                        return Promise.resolve();
                      }
                      return Promise.reject("Girilen parolalar eşleşmiyor!");
                    },
                  }),
                ]}
              >
                <Input.Password />
              </Form.Item>
              <Form.Item
                name="nickname"
                label={
                  <span>
                    Kullanıcı Adı&nbsp;
                    <Tooltip title="Size nasıl seslenelim?">
                      <QuestionCircleOutlined />
                    </Tooltip>
                  </span>
                }
                rules={[
                  {
                    required: true,
                    message: "Lütfen bir kullanıcı adı giriniz!",
                    whitespace: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="phone"
                label="Telefon Numarası"
                rules={[
                  {
                    required: true,
                    message: "Lütfen telefon numaranızı giriniz!",
                  },
                ]}
              >
                <Input addonBefore={prefixSelector} style={{ width: "100%" }} />
              </Form.Item>

              <Form.Item
                name="agreement"
                valuePropName="checked"
                {...tailFormItemLayout}
              >
                <Checkbox>
                  Kullanım sözleşmesini okudum. <a href="!#">Kabul ediyorum.</a>
                </Checkbox>
              </Form.Item>

              {/* <Form.Item>
                                <Button type="primary" htmlType="submit">
                                    Kayıt Ol!
                                </Button>
                            </Form.Item> */}
              <Divider />
              <p>
                Zaten bir hesabınız var mı?{" "}
                <Button type="link">Giriş yapın!</Button>
              </p>
            </Form>
          ) : current === 1 ? (
            <Form
              {...formItemLayout}
              name="register"
              onFinish={onFinish}
              initialValues={{
                prefix: "90",
              }}
              scrollToFirstError
              style={{ overflow: "scroll" }}
            >
              <Form.Item
                name="name"
                label="İsim"
                rules={[
                  {
                    type: "name",
                    message: "Lütfen geçerli bir isim ve soyisim giriniz!",
                  },
                  {
                    required: true,
                    message: "Lütfen isminizi ve soyisminizi giriniz!",
                  },
                ]}
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="birthdate"
                label="Doğum Tarihi"
                rules={[
                  {
                    required: true,
                    message: "Lütfen doğum tarihinizi giriniz!",
                  },
                ]}
                hasFeedback
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="expirydate"
                label={
                  <span>
                    Geçerlilik Süresi&nbsp;
                    <Tooltip title="Ehliyetinizin üzerindeki 4b numaralı alan. :)">
                      <QuestionCircleOutlined />
                    </Tooltip>
                  </span>
                }
                rules={[
                  {
                    required: true,
                    message:
                      "Lütfen ehliyetinizin geçerlilik tarihini giriniz!",
                    whitespace: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="serialno"
                label={
                  <span>
                    Seri No&nbsp;
                    <Tooltip title="Ehliyetinizin üzerindeki 5 numaralı alan. :)">
                      <QuestionCircleOutlined />
                    </Tooltip>
                  </span>
                }
                rules={[
                  {
                    required: true,
                    message: "Lütfen ehliyetinizin seri numarasını giriniz!",
                    whitespace: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="identityno"
                label="Kimlik No"
                rules={[
                  {
                    required: true,
                    message: "Lütfen TC kimlik numaranızı giriniz!",
                  },
                ]}
                hasFeedback
              >
                <Input />
              </Form.Item>
              <Form.Item>
                <Button type="primary" htmlType="submit">
                  Kayıt Ol!
                </Button>
              </Form.Item>
              <Divider />
              <p>
                Zaten bir hesabınız var mı?{" "}
                <Button type="link">Giriş yapın!</Button>
              </p>
            </Form>
          ) : current === 2 ? (
            <Result
              status="success"
              title="Artık aramızdasın!"
              subTitle="Merhaba Anıl! Üyeliğini oluşturduk, artık kampanyalarımız ve size özel tekliflerimizden yararlanabileceksin. Tebrikler!"
              extra={[
                <Link to="/renting">
                  <Button type="primary">Hemen Araç Kirala!</Button>
                </Link>,
                <Link to="/profile">
                  <Button>Hesabım</Button>
                </Link>,
              ]}
            />
          ) : null}
        </div>
      </Card>
    </Layout>
  );
}
