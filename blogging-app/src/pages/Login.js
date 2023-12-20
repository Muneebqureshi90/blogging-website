import React, {useContext, useState} from "react";
import { Button, Card, CardBody, CardHeader, Col, Container, FormGroup, Row } from "reactstrap";
import { toast } from "react-toastify";
import { loginUser } from "../services/user-services";
import { doLogin } from "../auth";
import { useNavigate } from "react-router-dom";
import userContext from "../context/userContext";
import Base from "../components/Base";

const Login = () => {
    const navigate = useNavigate();

    const userContextData = useContext(userContext)
    const [user, setUser] = useState(undefined);
    const [loginDetail, setLoginDetails] = useState({
        userName: "",
        password: "",
    });

    const handleChange = (event, field) => {
        let actualValue = event.target.value;

        setLoginDetails({
            ...loginDetail,
            [field]: actualValue,
        });
    };

    const handleFormSubmit = (event) => {
        event.preventDefault();

        if (loginDetail.userName.trim() === "") {
            toast.error("Username is required");
            return;
        }

        if (loginDetail.password.trim() === "") {
            toast.error("Password is required");
            return;
        }

        loginUser(loginDetail)
            .then((data) => {
                console.log("User logged in");
                console.log(data);

                doLogin(data, () => {
                    console.log("Login Data is Saved in local storage");

                    userContextData.setUser({
                        data: data,
                        login: true
                    });

                    navigate("/user/dashboard");
                });

                toast.success("Login Success");
            })
            .catch((error) => {
                console.log(error);

                if (error.response && error.response.status === 401) {
                    toast.error("Incorrect username or password");
                } else if (error.response && (error.response.status === 404 || error.response.status === 400)) {
                    toast.error(error.response.data.message);
                    toast.error("Something went wrong on the server");
                } else {
                    toast.error("Something went wrong on the server");
                    console.error("Error:", error);
                    console.error("Response:", error.response);
                }
            });
    };

    return (

        <Base>
        <Container className={"mt-5 mb-5"}>
            <Row className={"mt-5 mb-5"}>
                <Col sm={{ size: 6, offset: 3 }}>
                    <div className="d-flex justify-content-center align-items-center">
                        <Card color={"dark"} inverse>
                            <CardHeader className="text-center">
                                <h3>Login</h3>
                            </CardHeader>
                            <CardBody className="d-flex justify-content-center align-items-center">
                                <form onSubmit={handleFormSubmit}>
                                    <FormGroup>
                                        <input
                                            type="text"
                                            id="userName"
                                            placeholder="Username"
                                            className="form-control"
                                            style={{ width: "300px" }}
                                            value={loginDetail.userName}
                                            onChange={(e) => handleChange(e, "userName")}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <input
                                            type="password"
                                            id="password"
                                            placeholder="Password"
                                            className="form-control"
                                            style={{ width: "300px" }}
                                            value={loginDetail.password}
                                            onChange={(e) => handleChange(e, "password")}
                                        />
                                    </FormGroup>
                                    <div className="d-flex justify-content-center align-items-center mt-5">
                                        <Button outline color="light">
                                            Login
                                        </Button>
                                    </div>
                                </form>
                            </CardBody>
                        </Card>
                    </div>
                </Col>
            </Row>
        </Container>
        </Base>
    );
};

export default Login;