import Head from "next/head";
import logo from "../assets/logo.svg";
import { PageHeader, Avatar, Layout } from "antd";
import Link from "next/link";
import styles from "./layout.module.css";
const { Content } = Layout;
export default function AppLayout({ children }) {
  return (
    <React.Fragment>
      <Head>
        <title>Quince Renting</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta charSet="utf-8" />
      </Head>
      <Layout className={styles.layout}>
        <PageHeader
          title={
            <Link href="/">
              <a>
                <img src={logo} className={styles.logo} alt="site-logo" />
              </a>
            </Link>
          }
          extra={
            <Avatar
              shape="square"
              size="large"
              style={{
                color: "#f56a00",
                backgroundColor: "#fde3cf",
                marginRight: "2em",
              }}
            >
              A
            </Avatar>
          }
        />

        <Content className={styles.content}>{children}</Content>
      </Layout>
    </React.Fragment>
  );
}
