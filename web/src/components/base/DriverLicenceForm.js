import React from "react";
import { Input, Button, Form, Tooltip, Divider, Alert, DatePicker } from "antd";
import moment from "moment";
import { QuestionCircleOutlined } from "@ant-design/icons";
import { registerDriverLicenceModel } from "./../../models/Models";

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

export default function DriverLicenceForm(props) {
  const localDriverLicenceFormValues = registerDriverLicenceModel;
  const disabledBeforeMomentDate = (current) => {
    // Can not select days before today and today
    return current && current < moment().endOf("day");
  };
  const disabledAfterMomentDate = (current) => {
    // Can not select days before today and today
    return current && current > moment().endOf("day");
  };
  const handleDatePickerDateChange = (key, value) => {
    console.log(value);
    props.setDriveLicenceFormValues({
      ...props.driverLicenceFormValues,
      [key]: value,
    });
    console.log(localDriverLicenceFormValues);
  };
  const onDriverLicenceFormValuesChange = (values) => {
    const driverLicenceFormValue = {
      ...props.driverLicenceFormValues,
      ...values,
    };
    props.setDriveLicenceFormValues(driverLicenceFormValue);
    console.log(localDriverLicenceFormValues);
    console.log(driverLicenceFormValue);
    console.log(values);
  };
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
      {props.page === "profile" ? null : (
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
      )}

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
      {props.page === "profile" ? null : (
        <>
          <Divider />
          <p>
            Zaten bir hesabınız var mı?{" "}
            <Button type="link">Giriş yapın!</Button>
          </p>
        </>
      )}
    </Form>
  );
}
