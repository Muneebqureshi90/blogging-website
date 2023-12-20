import React from "react";
import { Container, Row, Col } from "reactstrap";


const Footer = () => {
    return (
        <footer className="bg-dark text-light py-4">
            <Container>
                <Row>
                    <Col md={6}>
                        <h5>About Us</h5>
                        <p>
                            Your Generalize Que Application is a platform for sharing your thoughts and ideas with the world. Join our community of bloggers and start writing today!
                        </p>
                    </Col>
                    <Col md={6}>
                        <h5>Contact Information</h5>
                        <address>
                            <p>
                                Address: 123 Main Street, City, Country
                            </p>
                            <p>
                                Email: <a href="mailto:muneebhaider564@gmail.com" style={{ textDecoration: 'none' }}>muneebhaider564@gmail.com</a>
                            </p>


                            <p>
                                Phone: +923011116259
                            </p>
                        </address>
                    </Col>
                </Row>
            </Container>
            <div className="text-center mt-3">
                &copy; {new Date().getFullYear()} Your  Generalize Que Application
            </div>
        </footer>
    );
};

export default Footer;
