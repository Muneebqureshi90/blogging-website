import Base from "../components/Base";
import NewFeed from "../components/NewFeed";
import {useEffect} from "react";
import {Col, Container, Row} from "reactstrap";
import CategorySideMenu from "../components/CategorySideMenu";


const Home=()=>{


    return(
        <Base>
            <Container className={"pt-3"}>
               <Row>
                   <Col md={2} className={"border"}>
                       <CategorySideMenu/>
                   </Col>
                   <Col md={10}>
                       <NewFeed/>
                   </Col>
               </Row>
            </Container>

        </Base>
    );

}

export default Home;