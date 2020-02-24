import React, { Component } from 'react'
// eslint-disable-next-line
import { Layout, Menu, Breadcrumb, Select, DatePicker, Button, Radio, Icon } from 'antd';

import './MainLayout.scss'
import logo from './logo.png'
import RentCar from './components/RentCar';
import SignIn from './components/SignIn';
// eslint-disable-next-line
const { Header, Content } = Layout;
class MainLayout extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
            location: 'rent-car'
        }
    }
    changePage = (location) => {
        this.setState({location: location})
    }
    
    render() {
        const {location} = this.state
        console.log(location)
        return (
            <Layout
                style={{ background: '#FEE715FF', height: '100vh' }}
            >
                <Header style={{ position: 'fixed', zIndex: 1, width: '100%', background: '#FEE715FF' }}>
                    <img className="logo" src={logo} alt="" />
                    <Menu
                        theme="light"
                        mode="horizontal"
                        defaultSelectedKeys={['1']}
                        style={{ lineHeight: '5em', background: '#FEE715FF', float: "right" }}
                    >
                            <Button type="primary" shape="round" icon="login" size="large" onClick={() => this.changePage("sign-in")}>
                                Giriş Yap
                            </Button>
                            <Button type="link">Kayıt Ol!</Button>
                    </Menu>
                </Header>
                <Content>
                    {
                        location == 'sign-in'
                        ?
                        <SignIn/>
                        : location == 'rent-car'
                        ?
                        <RentCar/>
                        : null
                    }
                    
                </Content>
            </Layout>
        )
    }
}

export default MainLayout
