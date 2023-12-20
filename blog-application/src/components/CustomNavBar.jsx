import React, { useState } from 'react';
import {
    Navbar,
    NavbarBrand,
    NavbarToggler,
    Collapse,
    Nav,
    NavItem,
    NavLink,
    Container
} from 'reactstrap';

const CustomNavbar = () => {
    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => setIsOpen(!isOpen);

    const navbarStyle = {
        backgroundColor: '#333', // Dark background color
        color: 'white', // Text color
        fontSize: '24px' // Font size
    };

    const brandStyle = {
        fontSize: '24px' // Increase the font size here
    };

    return (
        <Navbar style={navbarStyle} dark expand="md" fixed="top">
            <Container>
                <Collapse isOpen={isOpen} navbar>
                    <Nav className="mx-auto" navbar>
                        <NavbarBrand href="/" style={brandStyle} >Generalized Que</NavbarBrand>
                        <NavbarToggler onClick={toggle} />
                        <NavItem>
                            <NavLink href="#" className="nav-link">Home</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="#" className="nav-link">About</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="#" className="nav-link">Services</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="#" className="nav-link">Contact</NavLink>
                        </NavItem>
                    </Nav>
                </Collapse>
            </Container>
        </Navbar>
    );
};

export default CustomNavbar;
