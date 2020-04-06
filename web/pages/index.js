import AppLayout from "../components/layout";
import { Button, Space, Card, Divider } from "antd";
import Link from "next/link";

const buttons = [
  { location: "/auth/login", title: "Giriş" },
  { location: "/auth/register", title: "Kayıt Ol" },
  { location: "/renting", title: "Araç Kirala" },
  { location: "/profile", title: "Profil" },
];

export default function Index() {
  const generateButtons = () => {
    return buttons.map((e, index) => {
      return (
        <Link href={e.location} key={index}>
          <Button type="primary" size="large" block>
            {e.title}
          </Button>
        </Link>
      );
    });
  };
  return (
    <AppLayout>
      <Card
        className="card-md"
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
          Sayfalar
        </Divider>
        <Space size="middle" direction="vertical" style={{ width: 300 }}>
          {generateButtons()}
        </Space>
      </Card>
    </AppLayout>
  );
}
