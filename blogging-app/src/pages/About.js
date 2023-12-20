import React from "react";
import { Container, Row, Col } from "reactstrap";
import Base from "../components/Base";

const About = () => {
    return (
        <Base>
            <Container className="mt-5">
                <Row>
                    <Col md={8}>
                        <h1 className="mb-4">Welcome to Generalize Que Blogging</h1>
                        <p>
                            Generalize Que is a platform for sharing your thoughts, ideas, and stories with the world.
                            Whether you're a seasoned blogger or just starting, we welcome you to our community of writers and creators.
                        </p>
                        <p>
                            Our mission is to provide a space where you can express yourself, inspire others, and engage in meaningful discussions.
                        </p>
                        <h2 className="mt-4">What We Offer</h2>
                        <ul>
                            <li>Personal Blogging: Share your personal experiences, stories, and reflections.</li>
                            <li>Professional Writing: Showcase your expertise and insights in your field.</li>
                            <li>Community Engagement: Connect with fellow bloggers, readers, and enthusiasts.</li>
                            <li>User-Friendly Interface: Easy-to-use tools to create and manage your blog posts.</li>
                        </ul>
                        <h2 className="mt-4">Our Vision</h2>
                        <p>
                            Our vision is to empower individuals to share their knowledge, creativity, and passions with the world.
                            We believe in the power of storytelling and aim to provide a platform where diverse voices can be heard.
                        </p>
                    </Col>
                    <Col md={4}>
                        <div className="text-center">
                            <img
                                src="/your-image-path.jpg" // Add an image URL for your platform or logo
                                alt="Generalize Que Logo"
                                className="img-fluid rounded-circle"
                            />
                        </div>
                    </Col>
                </Row>
            </Container>
        </Base>
    );
};

export default About;
