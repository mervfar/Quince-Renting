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
import { setUserPhoto } from "../../services/UserService";

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
const { SubMenu } = Menu;
const { Title } = Typography;

export default function Profile(props) {
  const [userDetails, setUserDetails] = useState(userCredentialsModel.user_inf);
  const [loading, setLoading] = useState(true);
  const [uploadProcess, setUploadProcess] = useState(false);
  const handleClick = (e) => {
    console.log("click ", e);
    console.log(userDetails);
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
  useEffect(() => {
    setUserDetails(props.userCredentials["user_inf"]);
    setTimeout(() => {
      setLoading(false);
    }, 1000);
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
          <Menu.Item key="3">Araç Bilgileri</Menu.Item>
        </SubMenu>
      </Menu>
    );
  };
  const generateContent = () => {
    return (
      <Card className={styles.content}>
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
      </Card>
    );
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
                {generateContent()}
              </Col>
            </Row>
          </div>
        </Spin>
      </Card>
    </Layout>
  );
}
