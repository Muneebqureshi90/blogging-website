import React from "react";
import Base from "../components/Base";
import { Button, Card, CardBody, Container } from "reactstrap";
import { NavLink as ReactLink } from "react-router-dom";


const services = [
    {
        id: 1,
        title: "Web Development",
        description: "Professional web development services.",
        link: "mailto:muneebhaider564@gmail.com",
        imageSrc: "./images/pexels-christina-morillo-1181467.jpg", // Relative path to the image
    },
    {
        id: 2,
        title: "Graphic Designing",
        description: "High-quality graphic design solutions.",
        link: "mailto:muneebhaider564@gmail.com",
        imageSrc: "./images/pexels-lukas-590016.jpg", // Relative path to the image
    },
    {
        id: 3,
        title: "Marketing",
        description: "Effective marketing strategies and campaigns.",
        // link: "mailto:muneebhaider564@gmail.com",
        link: "https://wa.me/923011116259",
        imageSrc: "./images/pexels-christina-morillo-1181467.jpg", // Relative path to the image
    },
    {
        id: 4,
        title: "Digital Creator",
        description: "Creative digital content creation.",
        link: "mailto:muneebhaider564@gmail.com",
        imageSrc: "./images/pexels-your-image-name.jpg", // Relative path to the image
    },
    // Add more services as needed
];

const Service = () => {
    return (
        <Base>
            <div className={"mt-3 mb-3 text-center"}>
                <h2>Courses Are Available</h2>
                <div className="services-container">
                    <Container>
                        {services.map((service) => (
                            <Card key={service.id} className="service-card">
                                <CardBody>
                                    <h2>{service.title}</h2>
                                    <img
                                        src={service.imageSrc}
                                        alt={service.title}
                                        className="service-image"
                                        style={{ width: "150px", height: "150px", objectFit: "cover" }}
                                    />
                                    <p>{service.description}</p>
                                    <Button tag={ReactLink} to={service.link}>
                                        Contact Us
                                    </Button>
                                </CardBody>
                            </Card>
                        ))}
                    </Container>
                </div>
            </div>
        </Base>
    );
};

export default Service;
