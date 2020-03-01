import React, { Component } from 'react'
import { Card, Input, Button, Checkbox, Steps, Form, AutoComplete, Row, Col, Cascader, Select, Tooltip, Divider } from 'antd';
import { QuestionCircleOutlined } from '@ant-design/icons';
import './Register.css'
const { Step } = Steps;
const { Option } = Select;
const AutoCompleteOption = AutoComplete.Option;
const steps = [
    {
        title: 'Kişisel bilgiler',
        content: 'First-content',
    },
    {
        title: 'Ehliyet bilgileri',
        content: 'Second-content',
    },
    {
        title: 'Her şey hazır!',
        content: 'Last-content',
    },
];
const formItemLayout = {
    labelCol: {
        xs: { span: 16 },
        sm: { span: 8 },
    },
    wrapperCol: {
        xs: { span: 24 },
        sm: { span: 8 },
    },
};
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 16,
            offset: 8,
        },
    },
};
class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            current: 0,
        };
    }
    prefixSelector = (
        <Form.Item name="prefix" noStyle>
            <Select style={{ width: 70 }}>
                <Option value="90">+90</Option>
            </Select>
        </Form.Item>
    );
    onFinish = values => {
        console.log('Received values of form: ', values);
    };
    next = () => {
        const current = this.state.current + 1;
        this.setState({ current });
    }

    prev = () => {
        const current = this.state.current - 1;
        this.setState({ current });
    }
    render() {
        const { current } = this.state
        return (
            <Card 
                title="Kayıt Ol" 
                className='register-card'
                extra={
                    current < steps.length - 1 ? (
                        <Button type="primary" onClick={() => this.next()}>
                            Next
                    </Button>
                    ): null,
                    current === steps.length - 1 ? (
                        <Button type="primary" onClick={() => console.log('Processing complete!')}>
                            Done
                    </Button>
                    ): null,
                    current > 0 ? (
                        <Button style={{ marginLeft: 8 }} onClick={() => this.prev()}>
                            Previous
                    </Button>
                    ):null}
            >
                <Steps current={current}>
                    {steps.map(item => (
                        <Step key={item.title} title={item.title} />
                    ))}
                </Steps>
                <div className="steps-content">
                    {current === 0 && (
                        <Form
                            {...formItemLayout}
                            name="register"
                            onFinish={this.onFinish}
                            initialValues={{
                                prefix: '90',
                            }}
                            scrollToFirstError
                            style = {{overflow: 'scroll'}}
                        >
                            <Form.Item
                                name="email"
                                label="E-Posta"
                                rules={[
                                    {
                                        type: 'email',
                                        message: 'Lütfen geçerli bir e-posta adresi giriniz!',
                                    },
                                    {
                                        required: true,
                                        message: 'Lütfen e-posta adresinizi giriniz!',
                                    },
                                ]}
                            >
                                <Input />
                            </Form.Item>
                            <Form.Item
                                name="password"
                                label="Parola"
                                rules={[
                                    {
                                        required: true,
                                        message: 'Lütfen parola giriniz!',
                                    },
                                ]}
                                hasFeedback
                            >
                                <Input.Password />
                            </Form.Item>
                            <Form.Item
                                name="confirm"
                                label="Parola Doğrula"
                                dependencies={['password']}
                                hasFeedback
                                rules={[
                                    {
                                        required: true,
                                        message: 'Lütfen parolanızı yeniden yazın!',
                                    },
                                    ({ getFieldValue }) => ({
                                        validator(rule, value) {
                                            if (!value || getFieldValue('password') === value) {
                                                return Promise.resolve();
                                            }
                                            return Promise.reject('Girilen parolalar eşleşmiyor!');
                                        },
                                    }),
                                ]}
                            >
                                <Input.Password />
                            </Form.Item>
                            <Form.Item
                                name="nickname"
                                label={
                                    <span>
                                        Kullanıcı Adı&nbsp;
                              <Tooltip title="Size nasıl seslenelim?">
                                            <QuestionCircleOutlined />
                                        </Tooltip>
                                    </span>
                                }
                                rules={[{ required: true, message: 'Lütfen bir kullanıcı adı giriniz!', whitespace: true }]}
                            >
                                <Input />
                            </Form.Item>
                            <Form.Item
                                name="phone"
                                label="Telefon Numarası"
                                rules={[{ required: true, message: 'Lütfen telefon numaranızı giriniz!' }]}
                            >
                                <Input addonBefore={this.prefixSelector} style={{ width: '100%' }} />
                            </Form.Item>

                            <Form.Item name="agreement" valuePropName="checked" {...tailFormItemLayout}>
                                <Checkbox>
                                    Kullanım sözleşmesini okudum. <a href="">Kabul ediyorum.</a>
                                </Checkbox>
                            </Form.Item>

                            <Form.Item>
                                <Button type="primary" htmlType="submit">
                                    Kayıt Ol!
                          </Button>
                            </Form.Item>
                            <Divider />
                            <p>
                                Zaten bir hesabınız var mı? <Button type="link">Giriş yapın!</Button>
                            </p>
                        </Form>
                    )
                    }
                </div>
                <div className="steps-action">
                    
                </div>
            </Card>
        )
    }
}
export default Register
