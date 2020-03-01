import React, { Component } from 'react'
import { Card, Input, Button, Form, Checkbox, Divider } from 'antd';
import { signIn } from '../../service/auth-service'
import './SignIn.css'
const layout = {
    labelCol: {
        span: 8,
    },
    wrapperCol: {
        span: 16,
    },
};
const tailLayout = {
    wrapperCol: {
        offset: 8,
        span: 16,
    },
};

class SignIn extends Component {
    onFinish = values => {
        console.log('Success:', values);
        signIn(values).then(res => {
            console.log(res)
        })
    };

    onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
    };
    render() {
        const { saveState } = this.props
        return (
            <Card title="Giriş Yap" className='login-card'>
            <Form
                {...layout}
                name="basic"
                initialValues={{
                    remember: true,
                }}
                onFinish={this.onFinish}
                onFinishFailed={this.onFinishFailed}
            >
                <Form.Item
                    label="Kullanıcı Adı"
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'Lütfen kullanıcı adınızı giriniz!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Parola"
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Lütfen parolanızı giriniz!',
                        },
                    ]}
                >
                    <Input.Password />
                </Form.Item>

                <Form.Item {...tailLayout} name="remember" valuePropName="checked">
                    <Checkbox>Beni Hatırla</Checkbox>
                </Form.Item>

                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">
                        Giriş yap
                    </Button>
                </Form.Item>
                <Divider />
                <p>
                    Hala hesabınız yok mu? <Button type="link" onClick={() => saveState('register')}>Kayıt Ol!</Button>
                </p>
            </Form>
        </Card>

        )
    }
}
export default SignIn