import React, { useContext, useEffect, useState } from "react";
import { Table } from "reactstrap"; // Import the Table component
import Base from "../../components/Base";
import UserContext from "../../context/userContext";
import { useParams } from "react-router-dom";
import { getUser } from "../../services/user-services";
import { Card, CardBody, Col, Container, Row } from "reactstrap"; // Import the necessary components

const ProfileInfo = () => {
    const object = useContext(UserContext);
    const [user, setUser] = useState();
    const { userId } = useParams();

    useEffect(() => {
        getUser(userId).then((data) => {
            console.log(data);
            setUser({ ...data });
        });
    }, [userId]);

    const userView = () => {
        return (
            <Row>
                <Col md={{ size: 7, offset: 3 }}>
                    <Card className={"mt-5 rounded-0 shadow mb-5" }>
                        <CardBody className={"text-center"}>
                            <h3>User Information</h3>
                            <Container>
                                <img
                                    style={{ maxWidth: "250px", maxHeight: "250px" }}
                                    src="https://media.istockphoto.com/id/871752462/vector/default-gray-placeholder-man.jpg?s=612x612&w=0&k=20&c=4aUt99MQYO4dyo-rPImH2kszYe1EcuROC6f2iMQmn8o="
                                    alt="user profile image"
                                    className="img-fluid rounded-circle"
                                />
                                <Table
                                    bordered
                                    borderless
                                    hover
                                    responsive
                                    size="sm"
                                    striped
                                >
                                    <tbody>
                                    <tr>
                                        <th>Blog Id</th>
                                        <td>{user?.id || "N/A"}</td>
                                    </tr>
                                    <tr>
                                        <th>UserName</th>
                                        <td>{user?.name || "N/A"}</td>
                                    </tr>
                                    <tr>
                                        <th>Email</th>
                                        <td>{user?.email || "N/A"}</td>
                                    </tr>
                                    <tr>
                                        <th>About</th>
                                        <td>{user?.about || "N/A"}</td>
                                    </tr>
                                    {/*<tr>*/}
                                    {/*    <th>Role</th>*/}
                                    {/*    <td>{user?.role ? user.role.map((role) => {*/}
                                    {/*        return (*/}
                                    {/*            <span key={role.id}>{role.name}</span>*/}
                                    {/*        );*/}
                                    {/*    }) : "N/A"}</td>*/}
                                    {/*</tr>*/}
                                    </tbody>
                                </Table>
                            </Container>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
        );
    };

    return (
        <Base>
            {user ? userView() : 'loading the data'}
        </Base>
    );
};

export default ProfileInfo;
