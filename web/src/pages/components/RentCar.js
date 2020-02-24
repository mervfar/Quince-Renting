import React from 'react'
import { Layout, Menu, Breadcrumb, Select, DatePicker, Button, Radio, Icon } from 'antd';
const { Header, Content, Footer } = Layout;
const { RangePicker } = DatePicker;
const { Option } = Select;
function onChange(date, dateString) {
    console.log(date, dateString);
}
// function onChange(value) {
//     console.log(`selected ${value}`);
// }

function onBlur() {
    console.log('blur');
}

function onFocus() {
    console.log('focus');
}

function onSearch(val) {
    console.log('search:', val);
}

export default function RentCar() {
    return (
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
                        onChange={onChange}
                        onFocus={onFocus}
                        onBlur={onBlur}
                        onSearch={onSearch}
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
                        onChange={onChange}
                        style={{ margin: '0 0 1.5em 0' }} />

                    <p className="blog-slider__button">En iyi fiyatı bul!</p>
                </div>
            </div>



        </div>
        <div className="blog-slider__pagination"></div>
    </div>

    )
}
