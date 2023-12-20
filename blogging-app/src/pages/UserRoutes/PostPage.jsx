import React, { useEffect, useState } from "react";
import { Container, Button, Row, Col, Card, CardText, CardBody } from "reactstrap";
import { useParams, Link } from "react-router-dom";
import Base from "../../components/Base";
import {createComment, creatPost, loadPost} from "../../services/post-service";
import { toast } from "react-toastify";
import { BASE_URL } from "../../services/helper";
import data from "bootstrap/js/src/dom/data";
import {isLoggedIn} from "../../auth";

const PostPage = () => {
    const { postId } = useParams();
    const [post, setPost] = useState(null);
    const [comment, setComment] = useState({
        content: '', // Fixed the property name here
    });


    useEffect(() => {
        // Load Post by postId
        loadPost(postId)
            .then((data) => {
                console.log(data);
                setPost(data);
                toast.success("Comment is added")

                setPost({
                    ...post,comments:[...post.comments,data.data]
                })
                setComment({
                    content:''
                })
            })
            .catch((error) => {
                console.log(error);
                toast.error("Error in Loading the Post");
            });
    }, [postId]);

    // Submit the post

   const submitPost=()=>{

       if (!isLoggedIn()){
           toast.error("You need to login first !!")

           return;
       }

       // if (comment.content.trim() === '') {
       //     return;
       // }


       createComment(comment,post.postId).then(
           data=>{
               console.log(data)
           }
       ).catch(error=>{
           console.log(error);
           toast.error("Uploading Failed")
       })
   }

    return (
        <Base>
            <Container className={"mt-4"}>
                <Link to="/">Home</Link> / {post ? <Link to={post.title}>{post.title}</Link> : ""}

                <Row>
                    <Col md={{ size: 12, offset: 1 }}>
                        {post && (
                            <Card className={"mt-4 ps-2 border-0"}>
                                <CardBody>
                                    <CardText className={"mt-3"}>
                                        Post By <b>{post.user.name}</b> on{" "}
                                        <b>{new Date(post.addedDate).toLocaleString()}</b>
                                    </CardText>
                                    <h3>{post.title}</h3>

                                    <div className=" mt-3   shadow" style={{ maxWidth: '50%' }}>
                                        <img className={"img-fluid"} src={`${BASE_URL}/post/image/${post.imageName}`} alt="Post Image" />
                                    </div>

                                    <CardText dangerouslySetInnerHTML={{ __html: post.content }}>
                                        {/* Additional content if needed */}
                                    </CardText>
                                </CardBody>
                            </Card>
                        )}
                    </Col>
                </Row>

            {/*    Comments*/}
                <Row className={"mt-4"}>
                    <Col md={{ size: 8, offset: 2 }}>
                        <h2>Comments ({post ? post.comments.length : 0})</h2>
                        {post && post.comments && Array.isArray(post.comments) ? (
                            post.comments.map((c, index) => (
                                <Card key={index} className="mb-3">
                                    <CardBody>
                                        <CardText>
                                            {c.content}
                                        </CardText>
                                    </CardBody>
                                </Card>
                            ))
                        ) : (
                            <p>No comments available.</p>
                        )}


                        <Card className="mb-3 border-0">
                            <CardBody>
                                <div className="input-group">
                                    <input
                                        type="textarea"
                                        className="form-control"
                                        placeholder="Enter your comment here"
                                        value={comment.content} // Corrected property name
                                        onChange={(event) => setComment({ content: event.target.value })} // Corrected property name
                                    />


                                    <div className="input-group-append">
                                        <button onClick={submitPost} className="btn btn-secondary" type="button">
                                            Submit
                                        </button>
                                    </div>
                                </div>
                            </CardBody>
                        </Card>

                    </Col>
                </Row>



            </Container>
        </Base>
    );
};

export default PostPage;
