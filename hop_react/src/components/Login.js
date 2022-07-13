import { type } from "@testing-library/user-event/dist/type";
import React from "react";
import { Card,CardBody,CardSubtitle,CardFooter,Container, Button, CardTitle, CardText, Row, Col } from 'reactstrap';
import axios from "axios";
import { useNavigate } from "react-router-dom";


const Login=()=>{
    const [name,setName] = React.useState("");
    const [password,setPassword] = React.useState("");
    let navigate = useNavigate();

    const loginHandle=()=>{
        const formData= new FormData();
        formData.append("name",name);
        formData.append("password",password);
        let axiosConfig = {
            headers: {            
                "Access-Control-Allow-Origin": "*",
            }
          };
        try{

            axios.post(`http://localhost:3000/controller/login`,formData, axiosConfig).
            then((Response)=>{
                localStorage.setItem("access_token",Response?.data?.access_token);
                console.log(Response)
                navigate("/viewStudents")
            })

        }catch(e){

        }
    }

    return(
        <div>         
            <Card className="my-3">
                <form>
                <h1>Authentication:</h1>
                <h5>username:</h5>
                <input type="text" onChange={(event)=>setName(event.target.value)}/><br/><br/>
                <h5>password:</h5>
                <input type="password" onChange={(event)=>setPassword(event.target.value)}/><br/><br/>
                <Button color="success" onClick={loginHandle}>Login</Button>           
                </form>
            </Card>
        </div>
    )
}

export default Login;
