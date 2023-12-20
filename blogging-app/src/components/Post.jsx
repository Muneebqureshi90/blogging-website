import React, { useContext, useEffect, useState } from "react";
import { Button, Card, CardBody, CardText } from "reactstrap";
import { Link } from "react-router-dom";
import { getCurrentUserDetails, isLoggedIn } from "../auth";
import { deletePost } from "../services/post-service";
import { toast } from "react-toastify";
import userContext from "../context/userContext";

const Post = ({ post, onDelete }) => {
    const { id, title, content, postId, user } = post;

    const userContextData = useContext(userContext);
    const [login, setLogin] = useState(null);
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        setCurrentUser(getCurrentUserDetails());
        setLogin(isLoggedIn());
    }, []);

    const handleDeletePost = () => {
        // Ensure that post.id is a valid post ID
        deletePost(post.id)
            .then(() => {
                toast.success("Post deleted successfully");
                // Trigger the parent component's onDelete function if provided
                if (onDelete) {
                    onDelete(post.id);
                }
            })
            .catch((error) => {
                console.error(error);
                toast.error("Error in deleting the post");
            });
    };

    return (
        <Card className={"border-0 shadow-sm mt-4 mb-3"}>
            <CardBody>
                <h2>{title}</h2>
                <CardText dangerouslySetInnerHTML={{ __html: content.substring(0, 20) + "...." }}></CardText>
                <div>
                    <Link className={'btn btn-secondary border-0'} to={"/post/" + postId}>
                        Read More
                    </Link>
                    { userContextData.user.login && login && currentUser && user && currentUser.id === user.id && (
                        <Button onClick={handleDeletePost} className={'ms-3'} color={'danger'}>
                            Delete
                        </Button>
                    )}
                </div>
            </CardBody>
        </Card>
    );
};

export default Post;
