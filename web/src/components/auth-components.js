import React from 'react';
import { signIn, signUp } from '../service/auth-service'
import { Button } from 'antd';

const { useCallback } = React;

export const SignInButton = ({ username, password, onSignIn }) => {
  const signInf = useCallback(() => {
    signIn({ username, password }).then(onSignIn);
  }, [username, password, onSignIn]);

  return (
    <Button type="primary" htmlType="submit">
        Giriş yap
    </Button>
  );
};

export const SignUpButton = ({ username, password, verifiedPass, onSignUp }) => {
  const signUpf = useCallback(() => {
    signUp({ username, password, verifiedPass }).then(onSignUp);
  }, [username, password, verifiedPass, onSignUp]);

  return (
    <button onClick={signUp}>
      Sign-Up
    </button>
  );
};