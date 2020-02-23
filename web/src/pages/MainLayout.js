import React, { Component } from 'react'
// eslint-disable-next-line
import { Layout, Menu, Breadcrumb, Select, DatePicker } from 'antd';

import './MainLayout.scss'
import logo from './logo.png'
// eslint-disable-next-line
const { Header, Content, Footer } = Layout;
const { RangePicker } = DatePicker;
const { Option } = Select;
class MainLayout extends Component {
    onChange(date, dateString) {
        console.log(date, dateString);
    }
    onChange(value) {
        console.log(`selected ${value}`);
    }

    onBlur() {
        console.log('blur');
    }

    onFocus() {
        console.log('focus');
    }

    onSearch(val) {
        console.log('search:', val);
    }



    render() {
        return (
            <Layout
                style={{ background: '#FFF649', height: '100vh' }}
            >
                <Header style={{ position: 'fixed', zIndex: 1, width: '100%', background: '#FFF649' }}>
                    <Menu
                        theme="light"
                        mode="horizontal"
                        defaultSelectedKeys={['1']}
                        style={{ lineHeight: '5em', background: '#FFF649' }}
                    >
                        <Menu.Item><img className="logo" src={logo} alt="" /></Menu.Item>
                        <Menu.Item key="1">nav1</Menu.Item>
                        <Menu.Item key="2">nav2</Menu.Item>
                    </Menu>
                </Header>
                <Content style={{ padding: '0 50px', marginTop: 80 }}>
                    {/* <Breadcrumb style={{ margin: '16px 0' }}>
                        <Breadcrumb.Item>Home</Breadcrumb.Item>
                        <Breadcrumb.Item>List</Breadcrumb.Item>
                        <Breadcrumb.Item>App</Breadcrumb.Item>
                    </Breadcrumb> */}

                    <div className="blog-slider">
                        <div className="blog-slider__wrp">
                            <div className="blog-slider__item swiper-slide-active">
                                <div className="blog-slider__img">

                                    <img src="https://res.cloudinary.com/baykatre/image/upload/v1582465154/sabbir-ahmed-d9bhNxfY15w-unsplash_g4rrso.jpg" alt="" />
                                </div>
                                <div className="blog-slider__content">
                                    <div className="blog-slider__title">Size en uygun aracı kiralamak için doğru yerdesiniz!</div>
                                    <span className="blog-slider__code">Günlük Kiralama</span>
                                    <Select
                                        showSearch
                                        style={{ width: 200, margin: '0 0 1.5em 0' }}
                                        size='large'
                                        placeholder="Nereden alacaksınız?"
                                        optionFilterProp="children"
                                        onChange={this.onChange}
                                        onFocus={this.onFocus}
                                        onBlur={this.onBlur}
                                        onSearch={this.onSearch}
                                        filterOption={(input, option) =>
                                            option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                                        }
                                    >
                                        <Option value="IST">İstanbul Havalimanı</Option>
                                        <Option value="SAW">Sabiha Gökçen Havalimanı</Option>
                                        <Option value="ESB">Esenboğa Havalimanı</Option>
                                    </Select>
                                    <RangePicker
                                        size='large'
                                        onChange={this.onChange}
                                        style={{ margin: '0 0 1.5em 0' }} />

                                    <p className="blog-slider__button">En iyi fiyatı bul!</p>
                                </div>
                            </div>



                        </div>
                        <div className="blog-slider__pagination"></div>
                    </div>

                </Content>
                {/* <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer> */}
            </Layout>
        )
    }
}

export default MainLayout
