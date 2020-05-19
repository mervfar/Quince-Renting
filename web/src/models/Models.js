export const userCredentialsModel = {
  access_inf: {
    access_token: null,
    expires_in: null,
    scope: null,
    token_type: null,
  },
  user_inf: {
    birthDate: null,
    email: null,
    id: null,
    imageUrl: null,
    tcno: null,
    userRole: null,
    username: null,
    name: null,
    surname: null,
  },
};

export const registerUserInformationModel = {
  email: "",
  name: "",
  password: "",
  confirm: "",
  phoneNumber: "",
  surname: "",
  username: "",
  agreement: false,
};
export const registerDriverLicenceModel = {
  birthDate: "",
  birthLocation: "",
  dateOfIssue: "",
  documentNo: 0,
  name: "",
  office: "",
  surname: "",
  tcno: 0,
  validTime: "",
};
