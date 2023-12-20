import React, { useEffect, useState } from "react";
import { Button, Card, CardBody, Form, FormGroup, Input, Label } from "reactstrap";
import JoditEditor from "jodit-react";
import { loadAllCategories } from "../services/category-service";
import { toast } from "react-toastify";
import {creatPost as doCreatePost, uploadPostImage} from "../services/post-service";
import { getCurrentUserDetails } from "../auth";
import {upload} from "@testing-library/user-event/dist/upload";

const AddPost = () => {
    const [categories, setCategories] = useState([]);
    const [content, setContent] = useState("");
    const [user, setUser] = useState(undefined);

    const [post, setPost] = useState({
        title: "",
        content: "",
        categoryId: "",
        userId: user?.id, // Set the userId from the user object
    });

    const [image,setImage] = useState(null);

    const handleFileChange = (event) => {
        console.log(event.target.files[0])
        setImage(event.target.files[0])
       };


    // const fieldChange = (event) => {
    //     if (event.target.name === "categoryId") {
    //         // Handle category selection here and set post.categoryId
    //         const selectedCategoryTitle = event.target.value;
    //         const selectedCategoryId = categories.find(category => category.categoryTitle === selectedCategoryTitle)?.categoryId;
    //         setPost({ ...post, categoryId: selectedCategoryId });
    //     } else {
    //         // Handle other input changes
    //         setPost({ ...post, [event.target.name]: event.target.value });
    //     }
    // };

    const fieldChange = (event) => {
        if (event.target.name === "categoryId") {
            const selectedCategoryTitle = event.target.value;
            const selectedCategoryId = categories.find(category => category.categoryTitle === selectedCategoryTitle)?.categoryId;
            setPost({ ...post, categoryId: selectedCategoryId || '' }); // Set it to '' when not provided
        } else {
            setPost({ ...post, [event.target.name]: event.target.value });
        }
    };


    useEffect(() => {
        const currentUser = getCurrentUserDetails();
        setUser(currentUser);

        setPost(prevPost => ({ ...prevPost, userId: currentUser?.id })); // Set userId here

        loadAllCategories()
            .then((data) => {
                console.log("Categories:", data);
                setCategories(data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    const createPost = (event) => {
        event.preventDefault();

        if (post.title.trim() === "") {
            alert("Post title is required!");
            return;
        }

        if (post.content.trim() === "") {
            alert("Post content is required!");
            return;
        }

        // // Check if categoryId is null or empty
        // if (!post.categoryId) {
        //     alert("Select a valid category!");
        //     return;
        // }

        // Add your code to send the post data to your API for creation here
        // Ensure you have the correct API endpoint and method for creating posts

        doCreatePost({ ...post })
            .then((data) => {

                uploadPostImage(image,data.postId).then(
                    data=>{
                        toast.success("Image Uploaded!!")
                    }
                ).catch(error=>{
                    toast.error("Failed to upload image")
                    console.log(error+"Error")
                })

                toast.success("Post Created");
                console.log(data);
                // Optionally, reset the form fields here
                setPost({
                    title: "",
                    content: "",
                    categoryId: "",
                    userId: user?.id,
                });
            })
            .catch((error) => {
                toast.error("Failed to create post");
                console.error("Error:", error);
                console.error("Response:", error.response);
            });
    };

    return (
        <div className={"wrapper"}>
            <Card className="mt-5 mb-3 shadow">
                <CardBody>
                    {/*{JSON.stringify(post)}*/}
                    <h3 className="mb-4">What's on your mind?</h3>
                    <Form onSubmit={createPost}>
                        <FormGroup>
                            <Label for="title">Post Title</Label>
                            <Input
                                name="title"
                                type="text"
                                id="title"
                                placeholder="Enter here"
                                onChange={fieldChange}
                                value={post.title}
                            />
                        </FormGroup>
                        <FormGroup>
                            <Label for="content">Post Content</Label>
                            <JoditEditor
                                value={content}
                                tabIndex={1}
                                onBlur={(newContent) => setContent(newContent)}
                                onChange={(newContent) => setPost({ ...post, content: newContent })}
                            />

                        {/*    Image Uploding*/}
                            <div className={"mt-3"}>
                                <label className={"mb-2"}>Select a Post </label>
                                <Input
                                    id={"image"}
                                    type="file" multiple
                                    onChange={handleFileChange}
                                />
                            </div>

                        </FormGroup>
                        <FormGroup>
                            <Label for="category">Post Category</Label>
                            <Input
                                type="select"
                                name="categoryId"
                                id="category"
                                placeholder="Select a category"
                                onChange={fieldChange}
                                value={post.cid}
                            >
                                <option key="default" value="">
                                    Select category
                                </option>
                                {categories.map((category) => (
                                    <option key={category.cid} value={category.categoryTitle}>
                                        {category.categoryTitle}
                                    </option>
                                ))}
                            </Input>

                        </FormGroup>
                        <div className="text-center ms-2">
                            <Button type="submit" color="dark" className="me-4">
                                Create Post
                            </Button>
                            <Button type="reset" color="danger">
                                Reset Content
                            </Button>
                        </div>
                    </Form>
                </CardBody>
            </Card>
        </div>
    );
};

export default AddPost;
