import Base from "../components/Base";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Col, Container, Row } from "reactstrap";
import CategorySideMenu from "../components/CategorySideMenu";
import { loadPostCategoryWise } from "../services/post-service";
import { toast } from "react-toastify";
import Post from "../components/Post";

function Categories() {
    console.log("Categories component rendering");
    const { cid } = useParams();

    const [posts, setPosts] = useState([]);

    useEffect(() => {
        console.log("CID from URL parameter:", cid);

        loadPostsByCategory(cid)
            .then((data) => {
                setPosts(data);
            })
            .catch((error) => {
                console.log(error);
                toast.error("Error in Loading the Posts");
            });
    }, [cid]);

    const loadPostsByCategory = async (categoryId) => {
        try {
            const response = await loadPostCategoryWise(categoryId);
            return response.data; // Return the response data
        } catch (error) {
            console.log(error);
            toast.error("Error in Loading the Posts");
            throw error; // Throw the error to handle it outside the function
        }
    };


    return (
        <Base>
            <Container className="pt-3">
                <Row>
                    <Col md={2} className="border">
                        <CategorySideMenu />
                    </Col>
                    <Col md={10}>
                        <h2>Blog count ({posts && posts.length})</h2>
                        {posts && posts.length > 0 ? (
                            posts.map((post, index) => (
                                <Post post={post} key={index} />
                            ))
                        ) : (
                            <h1>No Posts Found</h1>
                        )}

                    </Col>
                </Row>
            </Container>
        </Base>
    );
}

export default Categories;
