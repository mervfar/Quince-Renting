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
  Spin,
  Alert,
  DatePicker,
} from "antd";
import { QuestionCircleOutlined, LoadingOutlined } from "@ant-design/icons";
import { Link, useHistory } from "react-router-dom";
import moment from "moment";
import styles from "./register.module.scss";
import { v4 as uuid } from "uuid";
import {
  registerUserInformationModel,
  registerDriverLicenceModel,
} from "./../../models/Models";
import { signIn, register } from "../../services/AuthService";
import { registerDriverLicenceService } from "../../services/UserService";

const { Step } = Steps;
const { Option } = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
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

export default function Register(props) {
  let history = useHistory();
  const [current, setcurrent] = useState(0);
  const [loading, setLoading] = useState(false);
  const [errorFlag, setErrorFlag] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");
  const [userFormValues, setUserFormValues] = useState(
    registerUserInformationModel
  );
  const [driverLicenceFormValues, setDriveLicenceFormValues] = useState(
    registerDriverLicenceModel
  );
  let registerData = {};
  const disabledBeforeMomentDate = (current) => {
    // Can not select days before today and today
    return current && current < moment().endOf("day");
  };
  const disabledAfterMomentDate = (current) => {
    // Can not select days before today and today
    return current && current > moment().endOf("day");
  };
  const prefixSelector = (
    <Form.Item name="prefix" noStyle>
      <Select style={{ width: 70 }}>
        <Option value="90">+90</Option>
      </Select>
    </Form.Item>
  );
  const registerUserInformation = () => {
    return (
      <Form
        {...formItemLayout}
        name="register"
        onValuesChange={onUserFormValuesChange}
        id="userForm"
        initialValues={{
          prefix: "90",
        }}
        scrollToFirstError
        style={{ overflow: "scroll" }}
      >
        {errorFlag ? (
          <Alert
            message="Kaydınız Başarısız!"
            description={errorMsg}
            type="error"
            showIcon
            style={{
              marginBottom: "3em",
              width: "60%",
              marginLeft: "20%",
            }}
          />
        ) : null}
        <Form.Item
          name="name"
          label="Adınız"
          rules={[
            {
              required: true,
              message: "Lütfen isim soyisim bilgisi giriniz!",
            },
          ]}
        >
          <Input placeholder="Reyhan Aydoğmuş" />
        </Form.Item>
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
          <Input placeholder="reyhan@quincerenting.com" />
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
          name="username"
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
          <Input placeholder="reyhanaydogmus" />
        </Form.Item>
        <Form.Item
          name="phoneNumber"
          label="Telefon Numarası"
          rules={[
            {
              required: true,
              message: "Lütfen telefon numaranızı giriniz!",
            },
          ]}
        >
          <Input
            addonBefore={prefixSelector}
            style={{ width: "100%" }}
            placeholder="554 400 0000"
          />
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
          Zaten bir hesabınız var mı? <Button type="link">Giriş yapın!</Button>
        </p>
      </Form>
    );
  };
  const registerDriverLicence = () => {
    return (
      <Form
        {...formItemLayout}
        name="register"
        onValuesChange={onDriverLicenceFormValuesChange}
        initialValues={{
          prefix: "90",
        }}
        scrollToFirstError
        style={{ overflow: "scroll" }}
      >
        <Alert
          message="Kaydınız Başarılı !"
          description="Ehliyet bilgilerinizi de tamamlayarak hemen araç kiralamaya başlayabilirsiniz!"
          type="success"
          showIcon
          style={{
            marginBottom: "3em",
            width: "60%",
            marginLeft: "20%",
          }}
        />
        <Form.Item
          name="name"
          label="İsim"
          rules={[
            {
              required: true,
              message: "Lütfen isminizi ve soyisminizi giriniz!",
            },
          ]}
        >
          <Input
            disabled
            placeholder={`${props.userCredentials["user_inf"].name} ${props.userCredentials["user_inf"].surname}`}
          />
        </Form.Item>

        <Form.Item
          name="birthDate"
          label="Doğum Tarihi"
          rules={[
            {
              required: true,
              message: "Lütfen doğum tarihinizi giriniz!",
            },
          ]}
          hasFeedback
        >
          <DatePicker
            placeholder="17.06.1999"
            size="large"
            format="DD.MM.YYYY"
            disabledDate={disabledAfterMomentDate}
            onChange={(date, dateString) =>
              handleDatePickerDateChange("birthDate", dateString)
            }
          />
        </Form.Item>
        <Form.Item
          name="birthLocation"
          label="Doğum Yeri"
          rules={[
            {
              required: true,
              message: "Lütfen doğum yerinizi giriniz!",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="dateOfIssue"
          label={
            <span>
              Belge Veriliş Tarihi&nbsp;
              <Tooltip title="Ehliyetinizin üzerindeki 4a numaralı alan. :)">
                <QuestionCircleOutlined />
              </Tooltip>
            </span>
          }
          rules={[
            {
              required: true,
              message: "Lütfen ehliyetinizin veriliş tarihini giriniz!",
            },
          ]}
          hasFeedback
        >
          <DatePicker
            placeholder="19.05.2020"
            size="large"
            format="DD.MM.YYYY"
            disabledDate={disabledAfterMomentDate}
            onChange={(date, dateString) =>
              handleDatePickerDateChange("dateOfIssue", dateString)
            }
          />
        </Form.Item>
        <Form.Item
          name="validTime"
          label={
            <span>
              Belge Geçerlilik Tarihi&nbsp;
              <Tooltip title="Ehliyetinizin üzerindeki 4b numaralı alan. :)">
                <QuestionCircleOutlined />
              </Tooltip>
            </span>
          }
          rules={[
            {
              required: true,
              message: "Lütfen ehliyetinizin geçerlilik tarihini giriniz!",
            },
          ]}
          hasFeedback
        >
          <DatePicker
            placeholder="19.05.2030"
            size="large"
            format="DD.MM.YYYY"
            disabledDate={disabledBeforeMomentDate}
            onChange={(date, dateString) =>
              handleDatePickerDateChange("validTime", dateString)
            }
          />
        </Form.Item>
        <Form.Item
          name="office"
          label={
            <span>
              Verildiği Yer&nbsp;
              <Tooltip title="Ehliyetinizin üzerindeki 4c numaralı alan. :)">
                <QuestionCircleOutlined />
              </Tooltip>
            </span>
          }
          rules={[
            {
              required: true,
              message:
                "Lütfen ehliyetinizin verildiği yer bilgisini tam olarak giriniz!",
              whitespace: true,
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="documentNo"
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
          name="tcno"
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
        <Divider />
        <p>
          Zaten bir hesabınız var mı? <Button type="link">Giriş yapın!</Button>
        </p>
      </Form>
    );
  };

  const registerAllDone = () => {
    return (
      <Result
        status="success"
        title="Artık aramızdasın!"
        subTitle={`Merhaba ${props.userCredentials["user_inf"].name}! Üyeliğini oluşturduk, artık kampanyalarımız ve size özel tekliflerimizden yararlanabileceksin. Tebrikler!"`}
        extra={[
          <Link to="/">
            <Button type="primary">Hemen Araç Kirala!</Button>
          </Link>,
          <Link to="/profile">
            <Button>Hesabım</Button>
          </Link>,
        ]}
      />
    );
  };
  const userRegister = () => {
    const name = userFormValues.name.split(" ");
    registerData = {
      ...registerData,
      email: userFormValues.email,
      name: name[0],
      password: userFormValues.password,
      phoneNumber: userFormValues.phoneNumber,
      surname: name[1],
      username: userFormValues.username,
    };
    if (
      registerData.email === "" ||
      registerData.name === "" ||
      registerData.password === "" ||
      registerData.phoneNumber === "" ||
      registerData.username === "" ||
      userFormValues.agreement === false ||
      userFormValues.confirm === ""
    ) {
      setLoading(false);
      setErrorFlag(true);
      if (registerData.name === "") {
        setErrorMsg("Devam edebilmek için isminize ihtiyacımız var! :)");
      } else if (registerData.email === "") {
        setErrorMsg(
          "Devam edebilmek için e-posta adresinize ihtiyacımız var! :)"
        );
      } else if (registerData.password === "") {
        setErrorMsg("Devam edebilmek için parola girmenize ihtiyacımız var! :");
      } else if (userFormValues.confirm === "") {
        setErrorMsg(
          "Devam edebilmek için formu tam olarak doldurmanıza ihtiyacımız var! :"
        );
      } else if (registerData.username === "") {
        setErrorMsg(
          "Devam edebilmek için eşsiz bir kullanıcı adı seçmek istemez misiniz? :)"
        );
      } else if (registerData.phoneNumber === "") {
        setErrorMsg(
          "Devam edebilmek için telefon numaranıza ihtiyacımız var! :)"
        );
      } else if (userFormValues.agreement === false) {
        setErrorMsg(
          "Devam edebilmek için kullanım sözleşmemizi kabul etmenize ihtiyacımız var! :"
        );
        console.log(userFormValues);
      }
    } else {
      console.log(registerData);
      register(registerData).then((res) => {
        console.log(res);
        if (res["result"] === "True") {
          const loginData = {
            username: userFormValues.username,
            password: userFormValues.password,
          };
          signIn(loginData).then((res) => {
            props.setLoginCredentials(res);
            setcurrent(current + 1);
          });
          setTimeout(() => {
            setLoading(false);
          }, 2000);
        } else {
          setErrorFlag(true);
          if (
            res["info"] ===
            "There is already a user registered with the username provided!"
          )
            setErrorMsg(
              `'${userFormValues.username}' kullanıcı adı alınmış. Yeni eşsiz bir kullanıcı adı seçebilir misiniz? :)`
            );
          else if (
            res["info"] ===
            "There is already a user registered with the email provided!"
          )
            setErrorMsg(
              `'${userFormValues.email}' adresi ile bir üyelik bulunuyor. Giriş yapmayı unutmuş olabilir misiniz? :)`
            );
          else if (
            res["info"] ===
            "There is already a user registered with the credentials provided!"
          )
            setErrorMsg(
              `Verdiğiniz bilgiler ile bir üyelik bulunuyor. Giriş yapmayı unutmuş olabilir misiniz? :)`
            );
        }
        setLoading(false);
      });
    }
  };
  const driverLicenceRegister = () => {
    const name = props.userCredentials["user_inf"].name;
    const surname = props.userCredentials["user_inf"].surname;
    const driverLicenceData = {
      birthDate: driverLicenceFormValues.birthDate,
      birthLocation: driverLicenceFormValues.birthLocation,
      name: name,
      surname: surname,
      dateOfIssue: driverLicenceFormValues.dateOfIssue,
      validTime: driverLicenceFormValues.validTime,
      documentNo: driverLicenceFormValues.documentNo,
      tcno: driverLicenceFormValues.tcno,
      office: driverLicenceFormValues.office,
    };
    console.log(driverLicenceData);
    registerDriverLicenceService(driverLicenceData).then((res) => {
      console.log(res);
      if (res["result"] === "OK") {
        setTimeout(() => {}, 2000);
        setcurrent(current + 1);
        setLoading(false);
      } else {
        setErrorFlag(true);
      }
      setLoading(false);
    });
  };
  const onUserFormValuesChange = (values) => {
    const userFormValue = {
      ...userFormValues,
      ...values,
    };
    setUserFormValues(userFormValue);
  };
  const onDriverLicenceFormValuesChange = (values) => {
    const driverLicenceFormValue = {
      ...driverLicenceFormValues,
      ...values,
    };
    setDriveLicenceFormValues(driverLicenceFormValue);
    console.log(driverLicenceFormValues);
    console.log(driverLicenceFormValue);
    console.log(values);
  };
  const next = (step) => {
    if (step === 1) {
      setLoading(true);
      userRegister();
    } else if (step === 2) {
      setLoading(true);
      driverLicenceRegister();
    }
  };

  const skip = () => {
    history.push("/");
  };
  const handleDatePickerDateChange = (key, value) => {
    console.log(value);
    setDriveLicenceFormValues({ ...driverLicenceFormValues, [key]: value });
    console.log(driverLicenceFormValues);
  };
  return (
    <Layout userCredentials={props.userCredentials}>
      <Card
        className="card card-bg"
        title="Kayıt Ol"
        extra={
          current < steps.length - 1 && current <= 0 ? (
            <Button type="primary" onClick={() => next(1)}>
              İleri
            </Button>
          ) : current < steps.length - 1 && current > 0 ? (
            <>
              <Button style={{ marginRight: 8 }} onClick={() => skip()}>
                Atla
              </Button>
              <Button type="primary" onClick={() => next(2)}>
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
        <Spin indicator={antIcon} spinning={loading}>
          <div className={styles.content}>
            {current === 0
              ? registerUserInformation()
              : current === 1
              ? registerDriverLicence()
              : current === 2
              ? registerAllDone()
              : null}
          </div>
        </Spin>
      </Card>
    </Layout>
  );
}
