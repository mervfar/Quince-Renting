import React from 'react'
import { Input, Col, Row, Select, InputNumber, DatePicker, AutoComplete, Cascader } from 'antd';

const InputGroup = Input.Group;
const { Option } = Select;

export default class SignIn extends React.Component {
    state = {
        dataSource: [],
      };
    
      handleChange = value => {
        this.setState({
          dataSource:
            !value || value.indexOf('@') >= 0
              ? []
              : [`${value}@gmail.com`, `${value}@163.com`, `${value}@qq.com`],
        });
      };




    render(){
        return (
            <div className="blog-slider">
                <div className="blog-slider__wrp">
                    <div className="blog-slider__item swiper-slide-active">
                        <div className="blog-slider__img">
    
                            <img src="https://res.cloudinary.com/baykatre/image/upload/v1582465154/sabbir-ahmed-d9bhNxfY15w-unsplash_g4rrso.jpg" alt="" />
                        </div>
                        <div className="blog-slider__content">
                            <div className="blog-slider__title">Giriş Yap!</div>
                            <span className="blog-slider__code">E-Mail</span>
                            <InputGroup compact
                                style={{margin: "2em"}}
                            >
                                <AutoComplete
                                    dataSource={this.state.dataSource}
                                    style={{ width: 200, margin: 10 }}
                                    onChange={this.handleChange}
                                    placeholder="Email"
                                />
                                <Input placeholder="Parola" style={{ width: 200, margin: 10 }}></Input>
                            </InputGroup>
                            <p className="blog-slider__button">Giriş Yap</p>
                        </div>
                    </div>
    
    
    
                </div>
                <div className="blog-slider__pagination"></div>
            </div>
    
        )
    }
    
}
