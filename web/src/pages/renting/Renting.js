import React, { useState } from "react";
import {
  Card,
  DatePicker,
  Space,
  Typography,
  Row,
  Col,
  Button,
  Empty,
  Skeleton,
  Spin,
  Select as SelectAntd,
  Statistic,
  message,
} from "antd";
import moment from "moment";
import Layout from "../../components/Layout";
import AutoComplete from "../../components/base/AutoComplete";
import styles from "./renting.module.scss";
import { cities } from "../../components/base/Constants";
import { CarOutlined } from "@ant-design/icons";
import { v4 as uuid } from "uuid";
import { getLocations, getCars } from "../../services/CarService";
import { LoadingOutlined } from "@ant-design/icons";
// eslint-disable-next-line
import {
  // eslint-disable-next-line
  availableCarsModel,
  locationsModel,
  carDetailsModel,
} from "../../models/Models";
import AvisLogo from "../../assets/avis_logo.svg";
import BudgetLogo from "../../assets/budget_logo.svg";
import Select from "../../components/base/Select";
const { RangePicker } = DatePicker;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
export default function Renting(props) {
  // eslint-disable-next-line
  const [city, setCity] = useState("");
  const [date, setDate] = useState();
  const [loading, setLoading] = useState(false);
  const [carCardFlag, setCarCardFlag] = useState(false);
  const [loadingCar, setLoadingCar] = useState(false);
  const [locations, setLocations] = useState(locationsModel);
  const [carDetails, setCarDetails] = useState(carDetailsModel);
  const [selectedLocation, setSelectedLocation] = useState("");
  const [imageStatus, setImageStatus] = useState(true); // TODO: Skeleton ayarlanacak!
  const renderItem = (name, code, brand) => {
    // eslint-disable-next-line
    const AvisLogo2 = AvisLogo;
    // eslint-disable-next-line
    const BudgetLogo2 = BudgetLogo;
    return (
      <SelectAntd.Option value={`${brand} ${code}`} key={uuid()}>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
          }}
        >
          {name}
          <img
            // eslint-disable-next-line
            src={eval(brand + "Logo2")}
            style={{ height: 12, marginTop: 6 }}
            alt={`${brand}-logo-alt`}
          />
        </div>
      </SelectAntd.Option>
    );
  };
  const disabledBeforeMomentDate = (current) => {
    // Can not select days before today and today
    return current < moment().startOf("day");
  };
  const onCitySelect = (value) => {
    setLoading(true);
    getLocations(value)
      .then((res) => {
        console.log(res);

        const location = res.map((r) => {
          return {
            name: r.name,
            code: r.code,
            brand: r.brand,
          };
        });
        const locationItems = location.map((l) => {
          return renderItem(l.name, l.code, l.brand);
        });
        setLocations(locationItems);
        console.log(location);
      })
      .catch((err) => {
        message.info("Sunucu Hatası! Oluşan bu aksaklık için özür dileriz."); //TODO: Hata mesajları
      })
      .finally(() => {
        setLoading(false);
      });

    setCity(value);
  };
  const onLocationSelect = (value) => {
    setSelectedLocation(value);
  };
  const onDateSelect = (value, mode) => {
    console.log(mode);
    setDate(mode);
  };
  const findCars = () => {
    setLoadingCar(true);
    setLoading(true);
    setCarCardFlag(true);
    // eslint-disable-next-line
    const locationDetails = selectedLocation.split(" ");
    let pickUpDate = date[0].split(" ");
    pickUpDate = pickUpDate[0] + "T" + pickUpDate[1] + ":00";
    let dropOffDate = date[1].split(" ");
    dropOffDate = dropOffDate[0] + "T" + dropOffDate[1] + ":00";
    console.log(pickUpDate + " " + dropOffDate);
    // const carData = {
    //   ...availableCarsModel,
    //   brand: locationDetails[0],
    //   dropoff_date: dropOffDate,
    //   dropoff_location: locationDetails[1],
    //   pickup_date: pickUpDate,
    //   pickup_location: locationDetails[1],
    // };
    const carData = {
      country_code: "GB",
      brand: "Avis",
      dropoff_date: dropOffDate,
      dropoff_location: "K9N",
      pickup_date: pickUpDate,
      pickup_location: "K9N",
    };
    console.log(carData);
    getCars(carData)
      .then((res) => {
        console.log(res);
        const carsDetail = res.map((r) => {
          const price = r.price.split(" ");
          const e = {
            ...r,
            price: price[0],
          };
          console.log(e);
          return e;
        });
        setCarDetails(carsDetail);
      })
      .catch((err) => {
        message.info("Sunucu Hatası! Oluşan bu aksaklık için özür dileriz."); //TODO: Hata mesajları
      })
      .finally(() => {
        setLoadingCar(false);
        setLoading(false);
      });
  };
  const handleImageLoaded = () => {
    setImageStatus(true);
    console.log(imageStatus);
  };
  const generateCars = () => {
    return carDetails.map((e) => {
      return (
        <div className={styles.car} key={uuid()}>
          {!loadingCar ? (
            <>
              <Row>
                <Col span={8}>
                  <Statistic title={e.brand + " " + e.series} value={" "} />
                </Col>
                <Col span={16}>
                  <img
                    src={e.imageUrl}
                    style={{
                      width: "200px",
                      marginLeft: "-30px",
                      marginRight: "30px",
                    }}
                    onLoad={() => handleImageLoaded()}
                    onError={() => handleImageLoaded()}
                    alt="car"
                  />
                </Col>
              </Row>
              <Row>
                <Col span={6}>
                  <Statistic title="Fiyat" value={e.price * 8} suffix="₺" />
                </Col>
                <Col span={6}>
                  <Statistic title="Motor" value={e.carBody} />
                </Col>
                <Col span={6}>
                  <Statistic
                    title="Yakıt Tipi"
                    value={e.fuel ? e.fuel : "Benzin"}
                  />
                </Col>
                <Col span={6}>
                  <Statistic
                    title="Vites"
                    value={e.gear === "Manual" ? "Manuel" : "Otomatik"}
                  />
                </Col>
              </Row>
              <Button
                type="primary"
                shape="round"
                size="large"
                className={styles.rentButton}
              >
                Kirala
              </Button>
            </>
          ) : null}
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
          <Col span={6} style={{ marginTop: "8%" }}>
            <Spin indicator={antIcon} spinning={loading} size="large">
              <Card className={styles.information} title="Günlük Kiralama">
                <AutoComplete
                  options={cities}
                  placeholder="Sizin için hangi şehire bakalım?"
                  onSelect={onCitySelect}
                  className={styles.options}
                />
                {locations !== locationsModel && (
                  <Select
                    options={locations}
                    placeholder="Aracınızı nereden teslim almak istersiniz?"
                    onChange={onLocationSelect}
                    style={{ marginTop: "1em", width: "100%" }}
                  />
                )}
                <RangePicker
                  className={styles.datepicker}
                  format="YYYY-MM-DD HH:mm"
                  onChange={onDateSelect}
                  size="large"
                  showTime
                  disabledDate={disabledBeforeMomentDate}
                  placeholder={[`Alış Tarihi`, `Bırakış Tarihi`]}
                />

                <Button
                  type="primary"
                  onClick={findCars}
                  style={{ float: "right" }}
                >
                  En uygun aracı bul!
                </Button>
              </Card>
            </Spin>
          </Col>
          <Col span={16} offset={1}>
            {!carCardFlag && (
              <div className={styles.empty}>
                <Empty description="Size en uygun araçları bulabilmemiz için lütfen yandaki bilgileri doldurabilir misiniz? :)" />
              </div>
            )}
            {carCardFlag && (
              <Card
                className={styles.cars}
                title="İşte size en uygun araçları aşağıda listeledik."
              >
                {" "}
                <Skeleton
                  className={styles.skeleton}
                  active={true}
                  loading={loadingCar}
                >
                  <Space
                    size="large"
                    direction="vertical"
                    style={{ margin: "2em", marginLeft: "15%" }}
                  >
                    {carDetails !== carDetailsModel && generateCars()}{" "}
                    {/* Araç listeleme yapılan yer */}
                  </Space>
                </Skeleton>
              </Card>
            )}
          </Col>
        </Row>

        <div className={styles.content}></div>
      </Card>
    </Layout>
  );
}
