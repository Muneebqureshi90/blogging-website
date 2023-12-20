import React, { useEffect, useState } from "react";
import Base from "../../components/Base";
import AddPost from "../../components/AddPost";
import { Container } from "reactstrap";
import { getCurrentUserDetails } from "../../auth";
import { loadPostUserWise, deletePost as deletePostApi } from "../../services/post-service";
import { toast } from "react-toastify";
import Post from "../../components/Post";

const UserDashBoard = () => {
    const [posts, setPosts] = useState([]);
    const [user, setUser] = useState({});

    useEffect(() => {
        console.log("Current User:", getCurrentUserDetails());
        setUser(getCurrentUserDetails());

        const currentUser = getCurrentUserDetails();
        if (currentUser && currentUser.id) {
            loadPostUserWise(currentUser.id)
                .then(data => {
                    console.log(data);
                    setPosts(data);
                })
                .catch(error => {
                    console.log(error);
                    toast.error("Error in Loading the Data");
                });
        }
    }, []);

    // Function to delete a post
    function deletePost(postId) {
        // Make an API request to delete the post with the given postId
        deletePostApi(postId)
            .then(response => {
                console.log(response);
                // Update the list of posts to remove the deleted post
                setPosts(posts.filter(post => post.id !== postId));
                toast.success("Post is deleted");
            })
            .catch(error => {
                console.error(error);
                toast.error("Error in deleting the post");
            });
    }

    return (
        <Base>
            <Container>
                <AddPost />
                <h1 className={'my-3'}>Post Counts: ({posts.length})</h1>
                {posts.map((post, index) => (
                    <Post post={post} key={index} onDelete={() => deletePost(post.id)} />
                ))}

            </Container>
        </Base>
    );
};

export default UserDashBoard;
