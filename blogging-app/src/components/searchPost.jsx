import React, { useState, useEffect } from 'react';
import axios from 'axios';

const SearchPosts = () => {
    const [searchKeyword, setSearchKeyword] = useState('');
    const [searchResults, setSearchResults] = useState([]);

    const handleSearch = () => {
        axios.get(`/api/posts/search/${searchKeyword}`)
            .then((response) => {
                setSearchResults(response.data);
            })
            .catch((error) => {
                console.error('Error searching for posts:', error);
            });
    };

    return (
        <div>
            <input
                type="text"
                placeholder="Search by title"
                value={searchKeyword}
                onChange={(e) => setSearchKeyword(e.target.value)}
            />
            <button onClick={handleSearch}>Search</button>

            <div>
                {searchResults.map((post) => (
                    <div key={post.postId}>
                        <h3>{post.title}</h3>
                        <p>{post.content}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default SearchPosts;
