import React, { useState } from 'react';
import { Button, Card, CardBody, CardHeader, Col, Container, FormGroup, Row } from 'reactstrap';
import { signUp } from '../services/user-services';
import Base from '../components/Base';
import { toast } from 'react-toastify';
import {Link, useNavigate} from 'react-router-dom';
// import { useHistory } from 'react-router-dom'; // Import useHistory
const SignUp = () => {

    // const history = useHistory();
    const navigate = useNavigate();

    const [data, setData] = useState({
        name: '',
        email: '',
        password: '',
        about: ''
    });

    const [errors, setErrors] = useState({
        name: '',
        email: '',
        password: '',
        about: '',
        serverError: '' // To capture server-side errors
    });

    const handleChange = (event, field) => {
        // Clear the error message for the field being edited
        setErrors({ ...errors, [field]: '' });
        setData({ ...data, [field]: event.target.value });
    };

    const resetData = () => ({
        name: '',
        email: '',
        password: '',
        about: ''
    });

    const submitForm = (event) => {
        event.preventDefault();

        signUp(data)
            .then((response) => {
                console.log(response.data);
                console.log(response);
                console.log('Success');
                toast.success('Registration is Successfully Done' + response.id);
                setData(resetData());

                // Redirect to the login page
                // history.push('/login');
                navigate('/login'); // Replace '/login' with the actual login page path
            })
            .catch((error) => {
                console.error('Error:', error);
                console.error('Response:', error.response);

                if (error.response) {
                    if (error.response.status === 400) {
                        // Server-side validation errors
                        const errorData = error.response.data;
                        setErrors({
                            name: errorData.name || '',
                            email: errorData.email || '',
                            password: errorData.password || '',
                            about: errorData.about || '',
                            serverError: errorData.message || 'Registration failed'
                        });
                    } else {
                        setErrors({
                            serverError: 'An error occurred while processing your request.'
                        });
                    }
                } else {
                    setErrors({
                        serverError: 'An unexpected error occurred.'
                    });
                }
            });
    };

    return (
        <Base>
            <Container>
                <Row className="mt-5 mb-5">
                    <Col sm={{ size: 6, offset: 3 }}>
                        <div className="d-flex justify-content-center align-items-center">
                            <Card color="dark" inverse>
                                <CardHeader>
                                    <h3>Fill in the Information to Register</h3>
                                </CardHeader>
                                <CardBody className="d-flex justify-content-center align-items-center">
                                    <form onSubmit={submitForm}>
                                        <FormGroup>
                                            <input
                                                type="text"
                                                placeholder="Enter Your Name Here"
                                                className={`form-control ${
                                                    errors.name ? 'is-invalid' : ''
                                                }`}
                                                id="name"
                                                style={{ width: '300px' }}

                                                onChange={(e) => handleChange(e, 'name')}
                                                value={data.name}
                                            />
                                            {errors.name && (
                                                <div className="invalid-feedback">{errors.name}</div>
                                            )}
                                        </FormGroup>

                                        <FormGroup>
                                            <input
                                                type="email"
                                                id="email"
                                                placeholder="Enter Your Email Here"
                                                className={`form-control ${
                                                    errors.email ? 'is-invalid' : ''
                                                }`}
                                                style={{ width: '300px' }}
                                                onChange={(e) => handleChange(e, 'email')}
                                                value={data.email}
                                            />
                                            {errors.email && (
                                                <div className="invalid-feedback">{errors.email}</div>
                                            )}
                                        </FormGroup>

                                        <FormGroup>
                                            <input
                                                type="password"
                                                id="password"
                                                placeholder="Enter Your Password Here"
                                                className={`form-control ${
                                                    errors.password ? 'is-invalid' : ''
                                                }`}
                                                style={{ width: '300px' }}

                                                onChange={(e) => handleChange(e, 'password')}
                                                value={data.password}
                                            />
                                            {errors.password && (
                                                <div className="invalid-feedback">{errors.password}</div>
                                            )}
                                        </FormGroup>

                                        <FormGroup>
                                            <textarea
                                                id="about"
                                                placeholder="Enter About Yourself"
                                                className={`form-control ${
                                                    errors.about ? 'is-invalid' : ''
                                                }`}
                                                style={{ height: '150px', width: '300px' }}

                                                onChange={(e) => handleChange(e, 'about')}
                                                value={data.about}
                                            />
                                            {errors.about && (
                                                <div className="invalid-feedback">{errors.about}</div>
                                            )}
                                        </FormGroup>

                                        {errors.serverError && (
                                            <div className="alert alert-danger mt-3">{errors.serverError}</div>
                                        )}

                                        <div className="d-flex justify-content-center align-items-center">
                                            <Button outline color="light" type="submit">
                                                SignUp
                                            </Button>
                                            <Button
                                                onClick={() => {
                                                    setData(resetData());
                                                    setErrors({
                                                        name: '',
                                                        email: '',
                                                        password: '',
                                                        about: '',
                                                        serverError: ''
                                                    });
                                                }}
                                                outline
                                                color="light"
                                                className="ms-2"
                                                type="reset"
                                            >
                                                Reset
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

export default SignUp;
