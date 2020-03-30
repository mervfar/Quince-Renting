import AppLayout from '../components/app-layout'
import { Button, Space, Card, Divider } from 'antd'
import Link from 'next/link'

export default function Index() {
  return (
    <AppLayout>
      <Card
        className="card-md"
        style={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center'
        }}
      >
        <Divider
          orientation="left"
          style={{ position: 'absolute', left: 0, top: 10 }}
        >
          Sayfalar
        </Divider>
        <Space size="middle" direction="vertical" style={{ width: 300 }}>
          <Link href="/auth/login">
            <Button type="primary" size="large" block>
              Giriş
            </Button>
          </Link>
          <Link href="/auth/register">
            <Button type="primary" size="large" block>
              Kayıt Ol
            </Button>
          </Link>
          <Link href="/renting">
            <Button type="primary" size="large" block>
              Araç Kirala
            </Button>
          </Link>
          <Link href="/profile">
            <Button type="primary" size="large" block>
              Profil
            </Button>
          </Link>
        </Space>
      </Card>
    </AppLayout>
  )
}
