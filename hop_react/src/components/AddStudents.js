import React from "react";
import { Card,CardBody,CardSubtitle,CardFooter,Container, Button, CardTitle, CardText, Row, Col } from 'reactstrap';


const AddStudent=()=>{
    return(
        <div>
            <h1>Student Registration</h1>
            <Card className="my-3">
            <form>
               
               <label for="userId">Student Id : <br/>
               <input type="text" placeholder=" Enter your Id" name="id" id="userId"/></label><br/><br/>

               <label for="userName">Student Name : <br/>
               <input type="text" placeholder=" Enter your Name" name="name" id="userName"/></label><br/><br/>

               <label for="address">Student Address : <br/>
               <input type="text" placeholder=" Enter your address" name="address" id="userAddress"/></label><br/><br/>
          
               <label for="userAge">Student Age : <br/>
               <input type="text" placeholder=" Enter your age" name="age" id="userAge"/></label><br/><br/>
            </form>
            <Container>
                <Button color="success">Register</Button>
            </Container></Card>
        </div>
    )
}

export default AddStudent;
