import { Card, DatePicker, Descriptions, Space, Typography } from "antd";
import Layout from "../../components/layout";
import AutoComplete from "../../components/base/auto-complete";
import styles from "./renting.module.css";
import { cities, carsDetails } from "../../components/base/constants";
import { useState } from "react";

const { RangePicker } = DatePicker;
export default function index() {
  const [city, setCity] = useState("");
  const [date, setDate] = useState();
  const onLocationSelect = (value) => {
    console.log(value);
    setCity(value);
  };
  const onDateSelect = (value, mode) => {
    console.log(mode);
    setDate(mode);
  };
  const generateCars = () => {
    return carsDetails.map((e) => {
      return (
        <Descriptions
          bordered
          title={e.brand + " " + e.model}
          size="default"
          className={styles.car}
        >
          <Descriptions.Item label="Motor">{e.engine}</Descriptions.Item>
          <Descriptions.Item label="Yakıt Tipi">{e.fuel}</Descriptions.Item>
          <Descriptions.Item label="Vites">{e.gear}</Descriptions.Item>
          <Descriptions.Item label="Teslim Ofisi">{e.office}</Descriptions.Item>
          <Descriptions.Item label="Depozito">{e.deposit} ₺</Descriptions.Item>
          <Descriptions.Item label="Fiyat">{e.dailyPrice} ₺</Descriptions.Item>
          <Descriptions.Item label="Detaylar">
            <img src={e.image} alt="car" />
          </Descriptions.Item>
        </Descriptions>
      );
    });
  };
  return (
    <Layout>
      <div className={styles.content}>
        <AutoComplete
          className={styles.searchButton}
          options={cities}
          placeholder="Aracınızı nereden teslim almak istersiniz?"
          onSelect={onLocationSelect}
        />
        {city && (
          // {1 && (
          <RangePicker
            className={styles.item}
            format="YYYY-MM-DD HH:mm"
            onChange={onDateSelect}
            size="large"
            showTime
          />
        )}
        {city && date && (
          // {1 && (
          <Card className="card-bg item">
            <Typography.Title level={4}>
              İşte size en uygun araçları aşağıda listeledik.
            </Typography.Title>
            <Space size="large" direction="vertical" style={{ margin: "2em" }}>
              {generateCars()}
            </Space>
          </Card>
        )}
      </div>
    </Layout>
  );
}
