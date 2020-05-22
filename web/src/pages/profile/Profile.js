import React, { useState, useEffect } from "react";
import {
  Card,
  Menu,
  PageHeader,
  Spin,
  Tag,
  Row,
  Col,
  Progress,
  Typography,
  Divider,
  Avatar,
  Button,
  Upload,
  message,
} from "antd";
import Layout from "../../components/Layout";
import {
  SettingOutlined,
  LoadingOutlined,
  UserOutlined,
  EditOutlined,
  UploadOutlined,
} from "@ant-design/icons";
import styles from "./profile.module.scss";
import { userCredentialsModel } from "../../models/Models";
import { v4 as uuid } from "uuid";
import { setUserPhoto, checkIdentity } from "../../services/UserService";
import DriverLicenceForm from "../../components/base/DriverLicenceForm";
import { registerDriverLicenceModel } from "./../../models/Models";
import {
  registerDriverLicenceService,
  getInvoice,
} from "../../services/UserService";

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
const { SubMenu } = Menu;
const { Title } = Typography;

// eslint-disable-next-line
String.prototype.turkishToLower = function () {
  var string = this;
  var letters = { İ: "i", I: "ı", Ş: "ş", Ğ: "ğ", Ü: "ü", Ö: "ö", Ç: "ç" };
  string = string.replace(/(([İIŞĞÜÇÖ]))/g, function (letter) {
    return letters[letter];
  });
  return string.toLowerCase();
};
// eslint-disable-next-line
String.prototype.turkishToUpper = function () {
  var string = this;
  var letters = { i: "İ", ş: "Ş", ğ: "Ğ", ü: "Ü", ö: "Ö", ç: "Ç", ı: "I" };
  string = string.replace(/(([iışğüçö]))/g, function (letter) {
    return letters[letter];
  });
  return string.toUpperCase();
};

export default function Profile(props) {
  const [userDetails, setUserDetails] = useState(userCredentialsModel.user_inf);
  const [loading, setLoading] = useState(true);
  const [uploadProcess, setUploadProcess] = useState(false);
  const [menuKey, setMenuKey] = useState("1");
  const [driverLicenceFormValues, setDriveLicenceFormValues] = useState(
    registerDriverLicenceModel
  );
  const [invoices, setInvoices] = useState();
  const handleClick = (e) => {
    console.log(e.key);
    setMenuKey(e.key);
    console.log(menuKey);
  };

  const handleSubmit = ({ fileList }) => {
    if (!uploadProcess) {
      let formData = new FormData();
      // add one or more of your files in FormData
      // again, the original file is located at the `originFileObj` key
      formData.append("file", fileList[0].originFileObj);
      setUserPhoto(formData).then((res) => {
        console.log(res);
        const userInfo = {
          ...props.userCredentials["user_inf"],
          imageUrl: res.data,
        };
        props.updateUserDetails({
          ...props.userCredentials,
          user_inf: userInfo,
        });
        setUploadProcess(true);
        message.success("Fotoğrafınızı başarıyla güncelledik! :)");
      });
    }
  };
  const driverLicenceRegister = () => {
    setLoading(true);
    const name = props.userCredentials["user_inf"].name;
    const surname = props.userCredentials["user_inf"].surname;
    const birthYear = driverLicenceFormValues.birthDate.split(".");
    console.log(birthYear);
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
    const identityData = {
      name: name.turkishToUpper(),
      surname: surname.turkishToUpper(),
      tcNo: driverLicenceFormValues.tcno,
      year: birthYear[2],
    };
    console.log(identityData);
    checkIdentity(identityData)
      .then((res) => {
        console.log(res);
        if (res === true) {
          registerDriverLicenceService(driverLicenceData).then((res) => {
            console.log(res);
            if (res) {
              const newUserDetails = {
                ...props.userCredentials,
                user_inf: {
                  ...props.userCredentials["user_inf"],
                  driverLicence: res,
                },
              };
              console.log(newUserDetails);
              props.updateUserDetails(newUserDetails);
              setTimeout(() => {
                setLoading(false);
              }, 2000);

              message.success("Ehliyet bilgileriniz başarıyla eklendi. :)");
            } else {
            }
            setLoading(false);
          });
        } else if (res === false)
          message.warning(
            "Kimlik bilgileriniz doğrulanamadı, lütfen tekrar dener misiniz? :)"
          );
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    setUserDetails(props.userCredentials["user_inf"]);
    setTimeout(() => {
      setLoading(false);
    }, 1000);
    setLoading(true);
    getInvoice().then((res) => {
      console.log(res);
      setLoading(false);
      setInvoices(res);
    });
  }, [props.userCredentials]);
  const generateMenu = () => {
    return (
      <Menu
        onClick={handleClick}
        className={styles.menu}
        defaultSelectedKeys={["1"]}
        defaultOpenKeys={["sub4"]}
        mode="inline"
      >
        <SubMenu
          key="sub4"
          title={
            <span>
              <SettingOutlined />
              <span>Ayarlar</span>
            </span>
          }
        >
          <Menu.Item key="1">Kişisel Bilgiler</Menu.Item>
          <Menu.Item key="2">Ehliyet Bilgileri</Menu.Item>
          <Menu.Item key="3">Faturalarım</Menu.Item>
        </SubMenu>
      </Menu>
    );
  };
  const generateContent = () => {
    if (menuKey === "1") return generateUserContent();
    else if (menuKey === "2") return generateDriverLicenceContent();
    else if (menuKey === "3") {
      return generateInvoiceContent();
    }
  };
  const generateUserContent = () => {
    return (
      <>
        <div className={styles.profileStatus}>
          <Title level={4} style={{ marginBottom: "1em", textAlign: "right" }}>
            Profil Durumu
          </Title>
          {userDetails.driverLicence ? (
            <Progress
              type="circle"
              strokeColor={{
                "0%": "#108ee9",
                "100%": "#87d068",
              }}
              percent={100}
            />
          ) : (
            <Progress
              type="circle"
              strokeColor={{
                "0%": "#108ee9",
                "100%": "#87d068",
              }}
              percent={90}
            />
          )}
        </div>
        <div className={styles.profile}>
          <Divider orientation="left">Adınız</Divider>
          <div>
            <Tag color="geekblue" className={styles.infoTag}>
              {userDetails.name + " " + userDetails.surname}
            </Tag>
          </div>
          <Divider orientation="left">E-Posta Adresiniz</Divider>
          <div>
            <Tag color="purple" className={styles.infoTag}>
              {userDetails.email}
            </Tag>
            <Button
              shape="circle"
              icon={<EditOutlined />}
              style={{ float: "right" }}
            />
          </div>
          <Divider orientation="left">Telefon Numaranız</Divider>
          <div>
            <Tag color="volcano" className={styles.infoTag}>
              {userDetails.phoneNumber}
            </Tag>
            <Button
              shape="circle"
              icon={<EditOutlined />}
              style={{ float: "right" }}
            />
          </div>
          <Divider orientation="left">Fotoğrafınız</Divider>
          <div>
            {userDetails.imageUrl ? (
              <Avatar
                size={128}
                src={userDetails.imageUrl}
                className={styles.infoTag}
              ></Avatar>
            ) : (
              <Avatar
                size={64}
                icon={<UserOutlined />}
                className={styles.infoTag}
              ></Avatar>
            )}

            <Upload onChange={handleSubmit} beforeUpload={() => false}>
              <Button style={{ marginLeft: "1.5em" }}>
                <UploadOutlined /> Yükle
              </Button>
            </Upload>
          </div>
        </div>
      </>
    );
  };
  const generateDriverLicenceContent = () => {
    return (
      <>
        <div className={styles.profileStatus}>
          <Title level={4} style={{ marginBottom: "1em", textAlign: "right" }}>
            Ehliyet Bilgileri
          </Title>
          {userDetails.driverLicence ? (
            <Progress
              type="circle"
              strokeColor={{
                "0%": "#108ee9",
                "100%": "#87d068",
              }}
              percent={100}
            />
          ) : (
            <Progress
              type="circle"
              strokeColor={{
                "0%": "#108ee9",
                "100%": "#87d068",
              }}
              percent={30}
            />
          )}
        </div>
        <div className={styles.driverLicenceForm}>
          {userDetails.driverLicence ? (
            <>
              <div className={styles.profileStatus}></div>
              <div
                className={styles.profile}
                style={{ width: "70%", overflow: "scroll" }}
              >
                <Divider orientation="left">Adınız</Divider>
                <div>
                  <Tag color="geekblue" className={styles.infoTag}>
                    {userDetails.name + " " + userDetails.surname}
                  </Tag>
                </div>
                <Divider orientation="left">Kimlik Numaranız</Divider>
                <div>
                  <Tag color="geekblue" className={styles.infoTag}>
                    {userDetails["driverLicence"].tcno}
                  </Tag>
                </div>
                <Divider orientation="left">Doğum Tarihi ve Yeri</Divider>
                <div>
                  <Tag color="geekblue" className={styles.infoTag}>
                    {userDetails["driverLicence"].birthDate +
                      " - " +
                      userDetails["driverLicence"].birthLocation}
                  </Tag>
                </div>
                <Divider orientation="left">Belge Seri No</Divider>
                <div>
                  <Tag color="purple" className={styles.infoTag}>
                    {userDetails["driverLicence"].documentNo}
                  </Tag>
                </div>
                <Divider orientation="left">Verildiği Yer</Divider>
                <div>
                  <Tag color="volcano" className={styles.infoTag}>
                    {userDetails["driverLicence"].office}
                  </Tag>
                </div>
                <Divider orientation="left">Geçerlilik Tarihi</Divider>
                <div>
                  <Tag color="volcano" className={styles.infoTag}>
                    {userDetails["driverLicence"].dateOfIssue +
                      " - " +
                      userDetails["driverLicence"].validTime}
                  </Tag>
                </div>
              </div>
            </>
          ) : (
            <>
              <DriverLicenceForm
                userCredentials={props.userCredentials}
                driverLicenceFormValues={driverLicenceFormValues}
                setDriveLicenceFormValues={setDriveLicenceFormValues}
                page="profile"
              />
              <Button
                type="primary"
                style={{ float: "right" }}
                onClick={driverLicenceRegister}
              >
                Güncelle
              </Button>
            </>
          )}
        </div>
      </>
    );
  };
  const generateInvoiceContent = () => {
    return invoices.map((i) => {
      return (
        <>
          <div className={styles.invoiceData} key={uuid()}>
            <Card>
              <div>
                <div
                  className={styles.invoice}
                  style={{ width: "70%", overflow: "scroll" }}
                >
                  <Divider orientation="left">Araç Bilgileri</Divider>
                  <div>
                    <Tag color="geekblue" className={styles.infoTag}>
                      {i.carInfo}
                    </Tag>
                  </div>
                  <Divider orientation="left">Alış Ofisi</Divider>
                  <div>
                    <Tag color="geekblue" className={styles.infoTag}>
                      {i.pickUpLocation}
                    </Tag>
                  </div>
                  <Divider orientation="left">Bırakış Ofisi</Divider>
                  <div>
                    <Tag color="geekblue" className={styles.infoTag}>
                      {i.dropOffLocation}
                    </Tag>
                  </div>
                  <Divider orientation="left">
                    Kiralama Başlangıç - Bitiş Tarihi
                  </Divider>
                  <div>
                    <Tag color="purple" className={styles.infoTag}>
                      {i.rentingStart + " - " + i.rentingEnd}
                    </Tag>
                  </div>
                  <Divider orientation="left">Toplam Fiyat</Divider>
                  <div>
                    <Tag color="volcano" className={styles.infoTag}>
                      {i.totalFee}
                    </Tag>
                  </div>
                </div>
              </div>
            </Card>
          </div>
        </>
      );
    });
  };
  return (
    <Layout userCredentials={props.userCredentials}>
      <Card className="card card-bg">
        <Spin indicator={antIcon} spinning={loading} size="large">
          <div className={styles.layout}>
            <PageHeader
              className={styles.pageHeader}
              avatar={
                userDetails.imageUrl
                  ? { size: 64, src: userDetails.imageUrl }
                  : { size: 64, icon: <UserOutlined /> }
              }
              title={userDetails.name + " " + userDetails.surname}
              subTitle={userDetails.email}
              tags={
                userDetails.driverLicence ? (
                  <Tag color="blue" key={uuid()}>
                    Ehliyet Onaylı
                  </Tag>
                ) : (
                  <Tag color="red" key={uuid()}>
                    Ehliyet Bilgileri Eksik!
                  </Tag>
                )
              }
              extra={[
                <Tag color="cyan" key={uuid()}>
                  {userDetails.username}
                </Tag>,
              ]}
            />
            <Row
              gutter={32}
              style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              <Col style={{ flexGrow: 1, alignSelf: "flex-start" }}>
                {generateMenu()}
              </Col>
              <Col
                style={{ flexGrow: 400, alignSelf: "center", height: "40em" }}
              >
                <Card className={styles.content}>{generateContent()}</Card>
              </Col>
            </Row>
          </div>
        </Spin>
      </Card>
    </Layout>
  );
}
