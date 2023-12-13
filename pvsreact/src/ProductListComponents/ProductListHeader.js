import { useEffect, useState } from "react";
import styles from "../CSS/List.module.css"
import logo from "../images/logo.png"
import { FaUser } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

export default function ProductListHeader({ token, decode }) {
    const [name, setName] = useState("");
    const navigate = useNavigate();
    useEffect(() => {
        fetch(`http://localhost:8080/api/getUserByEmail`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                "Accept": 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: decode.sub,
            }),
        })
            .then(response => response.json())
            .then(data => {
                setName(data.user_name);
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    })
    const handleHome = () => {
        navigate('../Home', { replace: true, state: { token: token, decode: decode } });
    }
    const handleAnimals = () => {
        navigate('../Animals', { replace: true, state: { token: token, decode: decode } });
    }
    const handleProducts = () => {
        navigate('../ProductList', { replace: true, state: { token: token, decode: decode } });
    }
    const handleUser = () => {
        navigate('/Profile');
    }
    return (
        <div className={styles.header}>
            <div className={styles.welcome}>
                <p className={styles.semititle}>PVS</p>
                <div className={styles.user} onClick={handleUser}><FaUser /> {name}</div>
            </div>
            <ul>
                <li><a onClick={handleHome}>Home</a></li>
                <li><a onClick={handleAnimals}>Animals</a></li>
                <li><a onClick={handleProducts}>Products</a></li>
                <li><a>Add Product</a></li>
                <li><a>Cart</a></li>
            </ul>
        </div>
    )
}