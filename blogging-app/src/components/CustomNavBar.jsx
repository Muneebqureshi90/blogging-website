import React, {useContext, useEffect, useState} from 'react';
import {NavLink as ReactLink, Link, useNavigate} from 'react-router-dom';

import {
    Collapse,
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavbarToggler,
    NavItem,
    NavLink,
    UncontrolledDropdown,
} from 'reactstrap';
import {doLogout, getCurrentUserDetails, isLoggedIn} from '../auth';
import userContext from "../context/userContext";

const CustomNavbar = () => {

    let navigate=useNavigate();

    const userContextData=useContext(userContext)
    const [isOpen, setIsOpen] = useState(false);

    // Navbar setting for user
    const [login, setLogin] = useState(false);
    const [user, setUser] = useState(undefined);
    //
    useEffect(() => {
        const userDataString = localStorage.getItem('data');

        if (userDataString) {
            const userData = JSON.parse(userDataString);
            setUser(userData.user);
            setLogin(true);
        } else {
            setUser(undefined);
            setLogin(false);
        }
    }, []);

    // logout
    const logout=()=>{

        doLogout(()=>
            {setLogin(false)

                userContextData.setUser({
                    data: null,
                    login: false
                })

            navigate("/")
            }
        )
    }


    const toggle = () => setIsOpen(!isOpen);

    const handleLogout = () => {
        // Clear user data from local storage or perform any other necessary logout tasks
        localStorage.removeItem('data');
        setLogin(false);
        setUser(undefined);
    };

    return (
        <div>
            <div>
                <Navbar color="dark" dark expand="md">
                    {!login && (
                        <>
                    <NavbarBrand tag={ReactLink} to="/" style={{ fontSize: '27px' }}>
                        Generalize Que
                    </NavbarBrand>
                    </>)}
                    {login && (
                        <>
                            <NavbarBrand tag={ReactLink} to="/" style={{ fontSize: '27px' }}>
                                Generalize Que
                            </NavbarBrand>
                        </>)}
                    <NavbarToggler onClick={toggle} />
                    <Collapse isOpen={isOpen} navbar>
                        <Nav className="me-auto text-center" navbar>
                            {/* Public navigation links */}
                            {!login && (
                                <>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            NewFeed
                                        </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/about" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            About
                                        </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/service" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            Service
                                        </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/login" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            Login
                                        </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/signup" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            SignUp
                                        </NavLink>
                                    </NavItem>
                                </>
                            )}
                            {/* If user is logged in */}
                            {login && (
                                <>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/user/dashboard" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            NewFeed
                                        </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/about" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            About
                                        </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/service" style={{ color: 'white', fontSize: '22px', margin: '0 15px' }}>
                                            Service
                                        </NavLink>
                                    </NavItem>
                                    <div className="d-flex justify-content-center mt-2">
                                        <div className="input-group" style={{ maxWidth: '800px' }}>
                                            <input
                                                type="text"
                                                placeholder="Search Course here"
                                                className="form-control"
                                                style={{ fontSize: '18px' }}
                                            />
                                            <button className="btn btn-secondary">Search</button>
                                        </div>
                                    </div>
                                    {login && user && (
                                        <NavItem>
                                            <NavLink tag={ReactLink} to={`/user/profile-info/${user.id}`} style={{ color: 'white', fontSize: '22px', margin: '0 15px' }} >
                                                Profile
                                            </NavLink>
                                        </NavItem>
                                    )}

                                    <NavItem>
                                        <NavLink style={{ color: 'white', fontSize: '22px', margin: '0 15px' }} onClick={logout}>
                                            Logout
                                        </NavLink>
                                    </NavItem>
                                    {user && (
                                        <NavItem>
                                            <NavLink style={{ color: 'white', fontSize: '19px', margin: '3 15px' }}>
                                                {user.email}
                                            </NavLink>
                                        </NavItem>

                                    )}
                                </>
                            )}
                            {!login && (
                                <UncontrolledDropdown nav inNavbar>
                                    <DropdownToggle nav caret style={{ fontSize: '22px', color: 'white' }}>
                                        More
                                    </DropdownToggle>
                                    <DropdownMenu right>
                                        <DropdownItem>
                                            <NavLink tag={ReactLink} to="mailto:muneebhaider564@gmail.com" style={{ color: 'inherit' }}>
                                                Contact Us
                                            </NavLink>
                                        </DropdownItem>
                                        <DropdownItem>
                                            <NavLink
                                                tag={ReactLink}
                                                to="https://instagram.com/muneebqureshi90?igshid=OGQ5ZDc2ODk2ZA=="
                                                style={{ color: 'inherit' }}
                                            >
                                                Facebook
                                            </NavLink>
                                        </DropdownItem>
                                        <DropdownItem>
                                            <NavLink
                                                tag={ReactLink}
                                                to="https://instagram.com/muneebqureshi90?igshid=OGQ5ZDc2ODk2ZA=="
                                                style={{ color: 'inherit' }}
                                            >
                                                Instagram
                                            </NavLink>
                                        </DropdownItem>
                                    </DropdownMenu>
                                </UncontrolledDropdown>


                            )}
                        </Nav>
                        {!login && (
                            <>
                        <div className="d-flex justify-content-center mt-2">
                            <div className="input-group" style={{ maxWidth: '800px' }}>
                                <input
                                    type="text"
                                    placeholder="Search Course here"
                                    className="form-control"
                                    style={{ fontSize: '18px' }}
                                />
                                <button className="btn btn-secondary">Search</button>
                            </div>
                        </div>
                            </>
                            )}
                    </Collapse>
                </Navbar>
            </div>
        </div>
    );
};

export default CustomNavbar;
