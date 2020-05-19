import React, { useState } from "react";
import {
  Card,
  DatePicker,
  Descriptions,
  Space,
  Typography,
  Row,
  Col,
  Button,
  Empty,
  Skeleton,
} from "antd";
import Layout from "../../components/Layout";
import AutoComplete from "../../components/base/AutoComplete";
import styles from "./renting.module.scss";
import { cities, carsDetails } from "../../components/base/Constants";
import { CarOutlined } from "@ant-design/icons";
import { v4 as uuid } from "uuid";

const { RangePicker } = DatePicker;
export default function Renting(props) {
  const [city, setCity] = useState("");
  const [date, setDate] = useState();
  const [imageStatus, setImageStatus] = useState(true); // TODO: Skeleton ayarlanacak!
  const onLocationSelect = (value) => {
    console.log(value);
    setCity(value);
  };
  const onDateSelect = (value, mode) => {
    console.log(mode);
    setDate(mode);
  };
  const handleImageLoaded = () => {
    setImageStatus(true);
    console.log(imageStatus);
  };
  const generateCars = () => {
    return carsDetails.map((e) => {
      return (
        <div className={styles.car} key={uuid()}>
          {imageStatus ? (
            <>
              <Descriptions
                bordered
                title={e.brand + " " + e.model}
                size="default"
              >
                <Descriptions.Item label="Motor">{e.engine}</Descriptions.Item>
                <Descriptions.Item label="Yakıt Tipi">
                  {e.fuel}
                </Descriptions.Item>
                <Descriptions.Item label="Vites">{e.gear}</Descriptions.Item>
                <Descriptions.Item label="Teslim Ofisi">
                  {e.office}
                </Descriptions.Item>
                <Descriptions.Item label="Depozito">
                  {e.deposit} ₺
                </Descriptions.Item>
                <Descriptions.Item label="Fiyat">
                  {e.dailyPrice} ₺
                </Descriptions.Item>
                <Descriptions.Item label="Detaylar">
                  <img
                    src={e.image}
                    onLoad={() => handleImageLoaded()}
                    onError={() => handleImageLoaded()}
                    alt="car"
                  />
                </Descriptions.Item>
              </Descriptions>
              <Button
                type="primary"
                shape="round"
                size="large"
                className={styles.rentButton}
              >
                Kirala
              </Button>
            </>
          ) : (
            <Skeleton
              className={styles.skeleton}
            /> /* Veriler gelene kadar kullanıcıya skeleton gösterilmesi gerekiyor! */
          )}
        </div>
      );
    });
  };
  return (
    <Layout userCredentials={props.userCredentials}>
      <Card className={`card card-bg ${styles.card}`}>
        <div className={styles.overlay}></div>
        <Row>
          <Col span={24}>
            <Card className={styles.textCard}>
              <Typography.Title
                level={3}
                style={{ textAlign: "center", color: "white" }}
              >
                <CarOutlined /> Size en uygun aracı bulmak için buradayız. Hemen
                Araç Kirala!
              </Typography.Title>
            </Card>
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={6} style={{ marginTop: "11%" }}>
            <AutoComplete
              options={cities}
              placeholder="Aracınızı nereden teslim almak istersiniz?"
              onSelect={onLocationSelect}
            />
            <RangePicker
              className={styles.datepicker}
              format="YYYY-MM-DD HH:mm"
              onChange={onDateSelect}
              size="large"
              showTime
              placeholder={[`Alış Tarihi`, `Bırakış Tarihi`]}
            />
          </Col>
          <Col span={16} offset={1}>
            {!(city && date) && (
              <div className={styles.empty}>
                <Empty description="Size en uygun araçları bulabilmemiz için lütfen yandaki bilgileri doldurabilir misiniz? :)" />
              </div>
            )}
            {city && date && (
              // {1 && (
              <Card className={styles.cars}>
                <Typography.Title level={4}>
                  İşte size en uygun araçları aşağıda listeledik.
                </Typography.Title>
                <Space
                  size="large"
                  direction="vertical"
                  style={{ margin: "2em", marginLeft: "15%" }}
                >
                  {generateCars()} {/* Araç listeleme yapılan yer */}
                </Space>
              </Card>
            )}
          </Col>
        </Row>
        <div className={styles.content}></div>
      </Card>
    </Layout>
  );
}
