import React, { Component } from 'react'
import { Layout, PageHeader } from 'antd';
import logo from '../logo.svg'
import SignIn from './auth/SignIn';
import './AppLayout.css'
import Register from './auth/Register';

const { Content } = Layout;

class AppLayout extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
            location: 'sign-in'
        }
    }
    saveState = value => {
        this.setState({location: value})
    }
    
    render() {
        const { location } = this.state
        return (
                <Layout
                    className='app-layout'
                >
                    <PageHeader
                        className="site-page-header"
                        title={<img src={logo} height='50' alt='site-logo' />}
                    />
                    <Content
                        style={{ height: '85%', width: '100%' }}
                    >
                    {
                        location === 'sign-in'
                        ?
                            <SignIn saveState={this.saveState}/>
                        : 
                        location === 'register'
                        ?
                            <Register saveState={this.saveState}/>
                        : 
                        null
                    }
                        
                    </Content>
                </Layout>
        )
    }
}
export default AppLayout