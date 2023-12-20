import React, { useState } from "react";
import UserContext from "./userContext";

function UserProvider({ children }) {
    const [user, setUser] = useState({
        data: {}, // You should replace this with actual user data
        login: false,
    });

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
}

export default UserProvider;