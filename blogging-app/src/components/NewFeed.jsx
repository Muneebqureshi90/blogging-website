import React, { useEffect, useState } from "react";
import { loadAllPosts } from "../services/post-service";
import { Col, Row, Pagination, PaginationItem, PaginationLink, Container } from "reactstrap";
import Post from "./Post";
import { toast } from "react-toastify";

const NewFeed = () => {
    const [postContent, setPostContent] = useState({
        content: [],
        totalPages: 0,
        totalElements: 0,
        pageSize: 5,
        lastPage: false,
        pageNumber: 0,
    });

    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        changePage(currentPage);
    }, [currentPage]);

    // Changing Page
    const changePage = (pageNumber) => {
        if (pageNumber > postContent.pageNumber && postContent.lastPage) {
            return;
        }
        if (pageNumber < postContent.pageNumber && postContent.pageNumber === 0) {
            return;
        }
        loadAllPosts(pageNumber, postContent.pageSize)
            .then((data) => {
                console.log(data);
                window.scrollTo(0, 0); // Corrected the function name from 'scroll' to 'scrollTo'
                setPostContent(data);
            })
            .catch((error) => {
                console.log(error);
                toast.error("Error in Loading");
            });
    };

    // Load more posts when clicking "Next"
    const loadMore = () => {
        changePage(postContent.pageNumber + 1);
    };

    return (
        <div className="container-fluid mt-5">
            <Row>
                <Col md={{ size: 12}}>
                    <h1>Blogs Count ({postContent.totalElements})</h1>

                    <Container className="text-center mt-3 justify-content-center d-flex">
                        <Pagination>
                            <PaginationItem
                                onClick={() => changePage(postContent.pageNumber - 1)}
                                disabled={postContent.pageNumber === 0}
                            >
                                <PaginationLink previous>Previous</PaginationLink>
                            </PaginationItem>

                            {[...Array(postContent.totalPages)].map((_, index) => (
                                <PaginationItem
                                    onClick={() => changePage(index)}
                                    active={index === postContent.pageNumber}
                                    key={index}
                                >
                                    <PaginationLink>{index + 1}</PaginationLink>
                                </PaginationItem>
                            ))}

                            <PaginationItem onClick={loadMore} disabled={postContent.lastPage}>
                                <PaginationLink next>Next</PaginationLink>
                            </PaginationItem>
                        </Pagination>
                    </Container>

                    {postContent.content.map((postData) => (
                        <Post key={postData.postId} post={postData} />
                    ))}
                </Col>
            </Row>
        </div>
    );
};

export default NewFeed;
