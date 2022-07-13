import React from "react";
import {Card, CardBody} from "reactstrap";
import { ListGroup, ListGroupItem } from 'reactstrap';
import { useNavigate } from "react-router-dom";

const Menu=()=>{
    let navigate = useNavigate();
    const Logout=()=>{
        localStorage.clear();
        navigate("/");
    }
    return(
        <ListGroup>
            <ListGroupItem tag="a" href="/" action style={{backgroundColor: "#CCE3DE"}}>Home</ListGroupItem>
            <ListGroupItem tag="a" href="/add-student" action style={{backgroundColor: "#CCE3DE"}}>Registration</ListGroupItem>                    
            {
                localStorage.getItem("access_token") ? (
                    <>
                    <ListGroupItem tag="a" href="/viewStudents" action style={{backgroundColor: "#CCE3DE"}}>View All Students</ListGroupItem> 
                    <ListGroupItem tag="a"  onClick={Logout} style={{backgroundColor: "#CCE3DE"}}>Logout</ListGroupItem>    
                    </>
                )
                :(  <ListGroupItem tag="a" href="/Login" action style={{backgroundColor: "#CCE3DE"}}>Login</ListGroupItem>)
            }

        </ListGroup>      
    )
}
export default Menu;