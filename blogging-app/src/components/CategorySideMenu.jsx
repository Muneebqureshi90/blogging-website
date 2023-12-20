import React, { useEffect, useState } from "react";
import { ListGroup, ListGroupItem } from "reactstrap";
import { loadAllCategories } from "../services/category-service";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";

function CategorySideMenu() {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        loadAllCategories()
            .then((data) => {
                console.log("Loading Categories");
                console.log(data);
                setCategories([...data]);
            })
            .catch((error) => {
                console.log(error);
                toast.error("Error in loading");
            });
    }, []);

    return (
        <div className="shadow p-3 mb-5 bg-white rounded">
            <h5 className="mb-3 mt-2 text-center">Categories</h5>
            <ListGroup>
                <ListGroupItem action className="border-0" tag={Link} to="/">
                    All Blogs
                </ListGroupItem>
                {categories &&
                    categories.map((category, index) => {
                        return (
                            <ListGroupItem
                                tag={Link}
                                to={"/categories/" + category.cid}
                                key={index}
                                action
                                className="border-0 shadow mt-1"
                            >
                                {category.categoryTitle}
                            </ListGroupItem>
                        );
                    })}
            </ListGroup>
        </div>
    );
}

export default CategorySideMenu;
